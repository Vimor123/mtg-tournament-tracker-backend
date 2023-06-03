package mttb.service.impl;

import mttb.dao.ColorRepository;
import mttb.dao.DecktypeRepository;
import mttb.dao.PlayerRepository;
import mttb.domain.Color;
import mttb.domain.Decktype;
import mttb.domain.Player;
import mttb.dto.LoginDTO;
import mttb.errors.EntityMissingException;
import mttb.errors.RequestDeniedException;
import mttb.service.DecktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DecktypeServiceJpa implements DecktypeService {

    @Autowired
    private DecktypeRepository decktypeRepo;

    @Autowired
    private PlayerRepository playerRepo;

    @Autowired
    private ColorRepository colorRepo;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Decktype create(LoginDTO loginDTO, String namedecktype, List<String> colors) {
        Optional<Player> player = playerRepo.findByUsername(loginDTO.getUsername());
        if (player.isEmpty()) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), player.get().getPasswordhash())) {
            throw new RequestDeniedException("Incorrect user credentials");
        }

        Decktype decktype = new Decktype();

        if(!decktypeRepo.findByNamedecktype(namedecktype).isEmpty()) {
            throw new RequestDeniedException("Deck type already exists");
        }
        decktype.setNamedecktype(namedecktype);

        Set<Color> decktypecolors = new HashSet<>();

        for (String colorString : colors) {
            Optional<Color> color = colorRepo.findByNamecolor(colorString);
            if (color.isEmpty()) {
                throw new EntityMissingException(Color.class, colorString);
            }
            decktypecolors.add(color.get());
        }

        decktype.setDecktypecolors(decktypecolors);
        decktypeRepo.save(decktype);
        return decktype;
    }
}
