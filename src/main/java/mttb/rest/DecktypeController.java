package mttb.rest;

import mttb.domain.Decktype;
import mttb.dto.DecktypeDTO;
import mttb.service.DecktypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/decktype")
public class DecktypeController {

    @Autowired
    private DecktypeService decktypeService;

    @PostMapping("/create")
    public Decktype create(@RequestBody DecktypeDTO dto) {
        return decktypeService.create(dto.getLogin(), dto.getNamedecktype(), dto.getColors());
    }
}
