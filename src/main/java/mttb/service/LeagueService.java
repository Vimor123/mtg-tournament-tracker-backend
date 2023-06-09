package mttb.service;

import jakarta.transaction.Transactional;
import mttb.domain.League;
import mttb.domain.Tournament;
import mttb.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public interface LeagueService {
    // Dohvaćanje lige
    League getById(Long id);

    // Dodavanje nove lige
    League create(LoginDTO loginDTO, String name, String timestart);

    // Dohvaćanje turnira u ligi
    List<Tournament> getLeagueTournaments(Long idleague);

    // Pretraživanje ligi
    List<League> searchByNameleague(String nameleague);

    // Dohvaćanje svih ligi
    List<League> getAll();

    // Brisanje lige
    void delete(Long id, LoginDTO loginDTO);
}
