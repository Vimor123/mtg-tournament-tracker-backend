package mttb.service;

import mttb.domain.Player;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlayerService {

    // Dohvat igrača
    Player getById(Long id);

    // Dodavanje novog igrača
    Player createPlayer(String username, String password, String name, String surname);

    // Provjera ispravnosti korisničkog imena i lozinke
    Player login(String username, String password);

    // Promjena podataka igrača
    Player editPlayer(Long id, String username, String password, String name, String surname);

    // Pretraživanje igrača
    List<Player> searchByUsername(String username);

    // Dohvat svih igrača
    List<Player> getAll();

}
