package mttb.rest;

import mttb.domain.League;
import mttb.domain.Tournament;
import mttb.dto.LeagueInDTO;
import mttb.dto.LeagueOutDTO;
import mttb.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/league")
public class LeagueController {

    @Autowired
    private LeagueService leagueService;

    @GetMapping("/{id}")
    public LeagueOutDTO getById(@PathVariable Long id) {
        League league = leagueService.getById(id);
        List<Tournament> tournaments = leagueService.getLeagueTournaments(league.getIdleague());
        return new LeagueOutDTO(league.getIdleague(), league.getNameleague(),
                league.getLeaguestart().toString(), league.getIdleague().toString(),
                tournaments);
    }

    @PostMapping("/create")
    public LeagueOutDTO create(@RequestBody LeagueInDTO dto) {
        League league = leagueService.create(dto.getLogin(), dto.getNameleague(), dto.getLeaguestart());
        List<Tournament> tournaments = leagueService.getLeagueTournaments(league.getIdleague());
        return new LeagueOutDTO(league.getIdleague(), league.getNameleague(),
                league.getLeaguestart().toString(), league.getIdleague().toString(),
                tournaments);
    }
}
