package mttb.rest;

import mttb.domain.Tournament;
import mttb.dto.TournamentInDTO;
import mttb.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/create")
    public Tournament create(@RequestBody TournamentInDTO dto) {
        Tournament tournament = tournamentService.create(dto.getLogin(), dto.getNametournament(),
                dto.getTournamentstart(), dto.getTournamentend(), dto.getFormat(),
                dto.getIdleague(), dto.getPlayers());
        return tournament;
    }
}
