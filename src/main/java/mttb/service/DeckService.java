package mttb.service;

import mttb.domain.Deck;
import mttb.dto.CardindeckDTO;
import mttb.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DeckService {
    // Dohvaćanje deka
    Deck getById(Long id);

    // Dohvaćanje igračevih dekova
    List<Deck> getDecksForPlayerId(Long id);

    // Dodavanje novog deka
    Deck create(LoginDTO loginDTO, String namedeck, Long iddecktype, List<CardindeckDTO> cards);

    // Mijenjanje deka
    Deck edit(LoginDTO loginDTO, String namedeck, Long iddecktype, List<CardindeckDTO> cards);

    // Brisanje deka
    void delete(Long id);
}
