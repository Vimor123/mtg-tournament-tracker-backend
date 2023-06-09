package mttb.rest;

import mttb.domain.Cardindeck;
import mttb.domain.League;
import mttb.domain.Playerintournament;
import mttb.domain.Tournament;
import mttb.dto.*;
import mttb.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("")
    public List<LeagueOutDTO> getAll() {
        List<League> leagues = leagueService.getAll();
        List<LeagueOutDTO> outDTOs = new ArrayList<>();
        for (League league : leagues) {
            List<Tournament> tournaments = leagueService.getLeagueTournaments(league.getIdleague());
            String endDate = null;
            if (league.getLeagueend() != null) {
                endDate = league.getLeagueend().toString();
            }
            List<TournamentOutDTO> tournamentOutDTOs = new ArrayList<>();
            for (Tournament tournament : tournaments) {
                List<PlayerInTournamentOutDTO> playerInTournamentOutDTOList = new ArrayList<>();
                for (Playerintournament playerintournament : tournament.getPlayersintournament()) {
                    List<CardOutDTO> cardOutDTOs = new ArrayList<>();
                    for (Cardindeck cardindeck : playerintournament.getDeck().getCardsindeck()) {
                        cardOutDTOs.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
                    }
                    DeckOutDTO deckOutDTO = new DeckOutDTO(playerintournament.getDeck().getIddeck(),
                            playerintournament.getDeck().getNamedeck(), playerintournament.getPlayer(),
                            playerintournament.getDeck().getDecktype(), cardOutDTOs);
                    playerInTournamentOutDTOList.add(new PlayerInTournamentOutDTO(playerintournament.getPlayer(),
                            deckOutDTO, playerintournament.getPosition(), playerintournament.getPoints(),
                            playerintournament.getPointsleague(), playerintournament.getWins(), playerintournament.getLosses(),
                            playerintournament.getDraws()));
                }
                TournamentOutDTO outDTO = new TournamentOutDTO(tournament.getNametournament(), tournament.getTournamentstart().toString(),
                        tournament.getTournamentend().toString(), tournament.getFormat().getNameformat(), tournament.getLeague().getIdleague(),
                        playerInTournamentOutDTOList);
                outDTO.setIdtournament(tournament.getIdtournament());
                tournamentOutDTOs.add(outDTO);
            }
            outDTOs.add(new LeagueOutDTO(league.getIdleague(), league.getNameleague(),
                    league.getLeaguestart().toString(), endDate,
                    tournamentOutDTOs));
        }
        return outDTOs;
    }

    @GetMapping("/{id}")
    public LeagueOutDTO getById(@PathVariable Long id) {
        League league = leagueService.getById(id);
        List<Tournament> tournaments = leagueService.getLeagueTournaments(league.getIdleague());
        String endDate = null;
        if (league.getLeagueend() != null) {
            endDate = league.getLeagueend().toString();
        }

        List<TournamentOutDTO> tournamentOutDTOs = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            List<PlayerInTournamentOutDTO> playerInTournamentOutDTOList = new ArrayList<>();
            for (Playerintournament playerintournament : tournament.getPlayersintournament()) {
                List<CardOutDTO> cardOutDTOs = new ArrayList<>();
                for (Cardindeck cardindeck : playerintournament.getDeck().getCardsindeck()) {
                    cardOutDTOs.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
                }
                DeckOutDTO deckOutDTO = new DeckOutDTO(playerintournament.getDeck().getIddeck(),
                        playerintournament.getDeck().getNamedeck(), playerintournament.getPlayer(),
                        playerintournament.getDeck().getDecktype(), cardOutDTOs);
                playerInTournamentOutDTOList.add(new PlayerInTournamentOutDTO(playerintournament.getPlayer(),
                        deckOutDTO, playerintournament.getPosition(), playerintournament.getPoints(),
                        playerintournament.getPointsleague(), playerintournament.getWins(), playerintournament.getLosses(),
                        playerintournament.getDraws()));
            }
            TournamentOutDTO outDTO = new TournamentOutDTO(tournament.getNametournament(), tournament.getTournamentstart().toString(),
                    tournament.getTournamentend().toString(), tournament.getFormat().getNameformat(), tournament.getLeague().getIdleague(),
                    playerInTournamentOutDTOList);
            outDTO.setIdtournament(tournament.getIdtournament());
            tournamentOutDTOs.add(outDTO);
        }

        return new LeagueOutDTO(league.getIdleague(), league.getNameleague(),
                league.getLeaguestart().toString(), endDate,
                tournamentOutDTOs);
    }

    @PostMapping("/create")
    public LeagueOutDTO create(@RequestBody LeagueInDTO dto) {
        League league = leagueService.create(dto.getLogin(), dto.getNameleague(), dto.getLeaguestart());
        List<Tournament> tournaments = leagueService.getLeagueTournaments(league.getIdleague());
        String endDate = null;
        if (league.getLeagueend() != null) {
            endDate = league.getLeagueend().toString();
        }

        List<TournamentOutDTO> tournamentOutDTOs = new ArrayList<>();
        for (Tournament tournament : tournaments) {
            List<PlayerInTournamentOutDTO> playerInTournamentOutDTOList = new ArrayList<>();
            for (Playerintournament playerintournament : tournament.getPlayersintournament()) {
                List<CardOutDTO> cardOutDTOs = new ArrayList<>();
                for (Cardindeck cardindeck : playerintournament.getDeck().getCardsindeck()) {
                    cardOutDTOs.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
                }
                DeckOutDTO deckOutDTO = new DeckOutDTO(playerintournament.getDeck().getIddeck(),
                        playerintournament.getDeck().getNamedeck(), playerintournament.getPlayer(),
                        playerintournament.getDeck().getDecktype(), cardOutDTOs);
                playerInTournamentOutDTOList.add(new PlayerInTournamentOutDTO(playerintournament.getPlayer(),
                        deckOutDTO, playerintournament.getPosition(), playerintournament.getPoints(),
                        playerintournament.getPointsleague(), playerintournament.getWins(), playerintournament.getLosses(),
                        playerintournament.getDraws()));
            }
            TournamentOutDTO outDTO = new TournamentOutDTO(tournament.getNametournament(), tournament.getTournamentstart().toString(),
                    tournament.getTournamentend().toString(), tournament.getFormat().getNameformat(), tournament.getLeague().getIdleague(),
                    playerInTournamentOutDTOList);
            outDTO.setIdtournament(tournament.getIdtournament());
            tournamentOutDTOs.add(outDTO);
        }

        return new LeagueOutDTO(league.getIdleague(), league.getNameleague(),
                league.getLeaguestart().toString(), endDate,
                tournamentOutDTOs);
    }

    @GetMapping("/search/{nameleague}")
    public List<LeagueOutDTO> searchByNameleague(@PathVariable String nameleague) {
        List<League> leagues = leagueService.searchByNameleague(nameleague);
        List<LeagueOutDTO> outDTOs = new ArrayList<>();
        for (League league : leagues) {
            List<Tournament> tournaments = leagueService.getLeagueTournaments(league.getIdleague());
            String endDate = null;
            if (league.getLeagueend() != null) {
                endDate = league.getLeagueend().toString();
            }
            List<TournamentOutDTO> tournamentOutDTOs = new ArrayList<>();
            for (Tournament tournament : tournaments) {
                List<PlayerInTournamentOutDTO> playerInTournamentOutDTOList = new ArrayList<>();
                for (Playerintournament playerintournament : tournament.getPlayersintournament()) {
                    List<CardOutDTO> cardOutDTOs = new ArrayList<>();
                    for (Cardindeck cardindeck : playerintournament.getDeck().getCardsindeck()) {
                        cardOutDTOs.add(new CardOutDTO(cardindeck.getCard(), cardindeck.isInsideboard(), cardindeck.getQuantity()));
                    }
                    DeckOutDTO deckOutDTO = new DeckOutDTO(playerintournament.getDeck().getIddeck(),
                            playerintournament.getDeck().getNamedeck(), playerintournament.getPlayer(),
                            playerintournament.getDeck().getDecktype(), cardOutDTOs);
                    playerInTournamentOutDTOList.add(new PlayerInTournamentOutDTO(playerintournament.getPlayer(),
                            deckOutDTO, playerintournament.getPosition(), playerintournament.getPoints(),
                            playerintournament.getPointsleague(), playerintournament.getWins(), playerintournament.getLosses(),
                            playerintournament.getDraws()));
                }
                TournamentOutDTO outDTO = new TournamentOutDTO(tournament.getNametournament(), tournament.getTournamentstart().toString(),
                        tournament.getTournamentend().toString(), tournament.getFormat().getNameformat(), tournament.getLeague().getIdleague(),
                        playerInTournamentOutDTOList);
                outDTO.setIdtournament(tournament.getIdtournament());
                tournamentOutDTOs.add(outDTO);
            }
            outDTOs.add(new LeagueOutDTO(league.getIdleague(), league.getNameleague(),
                    league.getLeaguestart().toString(), endDate,
                    tournamentOutDTOs));
        }
        return outDTOs;
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Long> deleteLeague(@PathVariable Long id, @RequestBody LoginDTO loginDTO) {
        leagueService.delete(id, loginDTO);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
