package mttb.service.impl;

import mttb.dao.LeagueRepository;
import mttb.dao.PlayerRepository;
import mttb.dao.TournamentRepository;
import mttb.domain.League;
import mttb.domain.Player;
import mttb.domain.Playerintournament;
import mttb.domain.Tournament;
import mttb.dto.LoginDTO;
import mttb.errors.EntityMissingException;
import mttb.errors.RequestDeniedException;
import mttb.errors.UnauthorizedAccessException;
import mttb.service.LeagueService;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LeagueServiceJpa implements LeagueService {

    @Autowired
    private LeagueRepository leagueRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private TournamentRepository tournamentRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<League> getAll() {
        return leagueRepo.findAll();
    }

    @Override
    public League getById(Long id) {
        Optional<League> league = leagueRepo.findByIdleague(id);
        if (league.isEmpty()) {
            throw new EntityMissingException(League.class, id);
        }
        return league.get();
    }

    @Override
    public League create(LoginDTO loginDTO, String name, String timestart) {
        Assert.notNull(loginDTO, "Login must be set");
        Assert.notNull(name, "Name must be set");
        Assert.notNull(timestart, "League start date must be set");

        Optional<Player> player = playerRepo.findByUsername(loginDTO.getUsername());
        if (player.isEmpty()) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), player.get().getPasswordhash())) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!player.get().isAdminprivileges()) {
            throw new UnauthorizedAccessException("Unauthorized user");
        }

        League league = new League(name, Date.valueOf(timestart));
        leagueRepo.save(league);
        return league;
    }

    @Override
    public List<Tournament> getLeagueTournaments(Long idleague) {
        Optional<League> league = leagueRepo.findByIdleague(idleague);
        if (league.isEmpty()) {
            throw new EntityMissingException(League.class, idleague);
        }
        List<Tournament> tournaments = tournamentRepo.findByLeague(league.get());
        return tournaments;
    }

    @Override
    public List<League> searchByNameleague(String nameleague) {
        List<League> allLeagues = leagueRepo.findAll();
        List<League> matchingLeagues = new ArrayList<>();
        for (League league : allLeagues) {
            if (league.getNameleague().toLowerCase().contains(nameleague.toLowerCase())) {
                matchingLeagues.add(league);
            }
        }
        return matchingLeagues;
    }

    @Override
    public void delete(Long id, LoginDTO loginDTO) {
        Optional<Player> player = playerRepo.findByUsername(loginDTO.getUsername());
        if (player.isEmpty()) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), player.get().getPasswordhash())) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!player.get().isAdminprivileges()) {
            throw new UnauthorizedAccessException("Unauthorized user");
        }

        Optional<League> leagueCheck = leagueRepo.findByIdleague(id);
        if (leagueCheck.isEmpty()) {
            throw new EntityMissingException(League.class, id);
        }

        League league = leagueCheck.get();

        List<Tournament> tournaments = tournamentRepo.findByLeague(league);

        for (Tournament tournament : tournaments) {
            tournamentRepo.delete(tournament);
        }

        leagueRepo.delete(league);
    }
}
