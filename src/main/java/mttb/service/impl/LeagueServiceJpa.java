package mttb.service.impl;

import mttb.dao.LeagueRepository;
import mttb.dao.PlayerRepository;
import mttb.dao.TournamentRepository;
import mttb.domain.League;
import mttb.domain.Player;
import mttb.domain.Tournament;
import mttb.dto.LoginDTO;
import mttb.errors.EntityMissingException;
import mttb.errors.RequestDeniedException;
import mttb.errors.UnauthorizedAccessException;
import mttb.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
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
    public League getById(Long id) {
        return null;
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
}
