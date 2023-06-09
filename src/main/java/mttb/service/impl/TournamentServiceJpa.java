package mttb.service.impl;

import mttb.dao.*;
import mttb.domain.*;
import mttb.dto.LoginDTO;
import mttb.dto.PlayerInTournamentDTO;
import mttb.errors.EntityMissingException;
import mttb.errors.RequestDeniedException;
import mttb.errors.UnauthorizedAccessException;
import mttb.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.*;

@Service
public class TournamentServiceJpa implements TournamentService {

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private LeagueRepository leagueRepo;

    @Autowired
    private DeckRepository deckRepo;

    @Autowired
    private FormatRepository formatRepo;

    @Autowired
    private TournamentRepository tournamentRepo;

    @Autowired
    private PlayerintournamentRepository playerintournamentRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Tournament create(LoginDTO loginDTO, String nametournament, String tournamentstart,
                             String tournamentend, String format, Long idleague,
                             List<PlayerInTournamentDTO> players) {

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

        Optional<League> league = leagueRepo.findByIdleague(idleague);
        if (league.isEmpty()) {
            throw new EntityMissingException(League.class, idleague);
        }

        Optional<Format> formatCheck = formatRepo.findByNameformat(format);
        if (formatCheck.isEmpty()) {
            throw new EntityMissingException(Format.class, format);
        }

        Tournament tournament = new Tournament();

        tournament.setNametournament(nametournament);
        tournament.setFormat(formatCheck.get());
        tournament.setLeague(league.get());
        tournament.setTournamentstart(Timestamp.valueOf(tournamentstart));
        tournament.setTournamentend(Timestamp.valueOf(tournamentend));

        Tournament newTournament = tournamentRepo.save(tournament);

        int positionIndex = 1;

        Set<Playerintournament> playersintournament = new HashSet<>();

        for (PlayerInTournamentDTO playerintournament : players) {
            Assert.isTrue(playerintournament.getWins() >= 0, "Players can't have a negative number of wins");
            Assert.isTrue(playerintournament.getDraws() >= 0, "Players can't have a negative number of draws");
            Assert.isTrue(playerintournament.getLosses() >= 0, "Players can't have a negative number of losses");

            if (!playerRepo.existsById(playerintournament.getIdplayer())) {
                throw new EntityMissingException(Player.class, playerintournament.getIdplayer());
            }
            //if (playerintournament.getPosition() != positionIndex) {
            //    throw new RequestDeniedException("Incorrect player order");
            //}
            if (!deckRepo.existsByIddeck(playerintournament.getIddeck())) {
                throw new EntityMissingException(Deck.class, playerintournament.getIddeck());
            }

            PlayerintournamentKey key = new PlayerintournamentKey(newTournament.getIdtournament(), playerintournament.getIdplayer());

            Playerintournament playerintournamentObject = new Playerintournament();

            playerintournamentObject.setId(key);
            playerintournamentObject.setTournament(newTournament);
            playerintournamentObject.setPlayer(playerRepo.findById(playerintournament.getIdplayer()).get());

            if (!deckRepo.existsByIddeck(playerintournament.getIddeck())) {
                playerintournamentObject.setDeck(null);
            } else {
                playerintournamentObject.setDeck(deckRepo.findById(playerintournament.getIddeck()).get());
            }

            playerintournamentObject.setPosition(playerintournament.getPosition());
            playerintournamentObject.setPoints(playerintournament.getPoints());
            playerintournamentObject.setPointsleague(playerintournament.getPointsleague());
            playerintournamentObject.setWins(playerintournament.getWins());
            playerintournamentObject.setLosses(playerintournament.getLosses());
            playerintournamentObject.setDraws(playerintournament.getDraws());

            playersintournament.add(playerintournamentObject);
            playerintournamentRepo.save(playerintournamentObject);
        }

        newTournament.setPlayersintournament(playersintournament);
        tournamentRepo.save(newTournament);
        return newTournament;
    }

    @Override
    public Tournament getById(Long id) {
        Optional<Tournament> tournament = tournamentRepo.findById(id);
        if (tournament.isEmpty()) {
            throw new EntityMissingException(Tournament.class, id);
        }
        return tournament.get();
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

        Optional<Tournament> tournament = tournamentRepo.findById(id);
        if (tournament.isEmpty()) {
            throw new EntityMissingException(Tournament.class, id);
        }

        for (Playerintournament playerInTournament : tournament.get().getPlayersintournament()) {
            playerintournamentRepo.delete(playerInTournament);
        }

        tournamentRepo.delete(tournament.get());
    }
}
