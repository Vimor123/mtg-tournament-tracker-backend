package mttb.service;

import mttb.domain.Tournament;
import mttb.dto.LoginDTO;
import mttb.dto.PlayerInTournamentDTO;
import mttb.dto.TournamentInDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TournamentService {
    // Dodavanje novog turnira
    Tournament create(LoginDTO dto, String nametournament, String tournamentstart,
                      String tournamentend, String format, Long idleague,
                      List<PlayerInTournamentDTO> players);
}
