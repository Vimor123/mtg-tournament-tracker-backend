package mttb.rest;

import mttb.domain.Player;
import mttb.dto.EditPlayerDTO;
import mttb.dto.LoginDTO;
import mttb.dto.NewPlayerDTO;
import mttb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public Player getById(@PathVariable Long id) {
        return playerService.getById(id);
    }

    @GetMapping("/search/{username}")
    public List<Player> getPlayersByUsername(@PathVariable String username) {
        return playerService.searchByUsername(username);
    }

    @PostMapping("/create")
    public Player createAccount(@RequestBody NewPlayerDTO dto) {
        return playerService.createPlayer(dto.getUsername(), dto.getPassword(), dto.getName(), dto.getSurname());
    }

    @PostMapping("/login")
    public Player login(@RequestBody LoginDTO dto) {
        return playerService.login(dto.getUsername(), dto.getPassword());
    }

    @PostMapping("/edit")
    public Player editAccount(@RequestBody EditPlayerDTO dto) {
        return playerService.editPlayer(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getName(), dto.getSurname());
    }

}
