package mttb.service;

import mttb.domain.Decktype;
import mttb.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DecktypeService {

    // Dodavanje novog tipa deka
    Decktype create(LoginDTO loginDTO, String namedecktype, List<String> colors);
}
