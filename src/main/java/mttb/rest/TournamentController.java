package mttb.rest;

import mttb.domain.Cardindeck;
import mttb.domain.Playerintournament;
import mttb.domain.Tournament;
import mttb.dto.*;
import mttb.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/{id}")
    public TournamentOutDTO getById(@PathVariable Long id) {
        Tournament tournament = tournamentService.getById(id);
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
        return outDTO;
    }

    @PostMapping("/create")
    public TournamentOutDTO create(@RequestBody TournamentInDTO dto) {
        Tournament tournament = tournamentService.create(dto.getLogin(), dto.getNametournament(),
                dto.getTournamentstart(), dto.getTournamentend(), dto.getFormat(),
                dto.getIdleague(), dto.getPlayers());
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
        return outDTO;
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<Long> deleteLeague(@PathVariable Long id, @RequestBody LoginDTO loginDTO) {
        tournamentService.delete(id, loginDTO);
        return new ResponseEntity<Long>(id, HttpStatus.OK);
    }
}
