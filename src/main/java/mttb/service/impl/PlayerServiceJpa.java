package mttb.service.impl;


import mttb.dao.PlayerRepository;
import mttb.domain.Player;
import mttb.errors.EntityMissingException;
import mttb.errors.RequestDeniedException;
import mttb.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PlayerServiceJpa implements PlayerService {

    @Autowired
    private PlayerRepository playerRepo;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public List<Player> getAll() {
        return playerRepo.findAll();
    }

    @Override
    public Player getById(Long id) {
        Optional<Player> player = playerRepo.findById(id);
        if (player.isEmpty()) {
            throw new EntityMissingException(Player.class, id);
        }
        return player.get();
    }

    @Override
    public List<Player> searchByUsername(String username) {
        List<Player> players = playerRepo.findAll();
        List<Player> matching_players = new ArrayList<>();
        for (Player player : players) {
            if (player.getUsername().toLowerCase().contains(username.toLowerCase())) {
                matching_players.add(player);
            }
        }
        return matching_players;
    }

    @Override
    public Player createPlayer(String username, String password, String name, String surname) {
        Assert.notNull(username, "Username must be set");
        Assert.notNull(password, "Password must be set");
        Assert.notNull(name, "Name must be set");
        Assert.notNull(surname, "Surname must be set");

        if (playerRepo.existsByUsername(username)) {
            throw new RequestDeniedException("Username " + username + " already in use.");
        }

        Player newPlayer = new Player(username, passwordEncoder.encode(password), name, surname);
        playerRepo.save(newPlayer);
        return newPlayer;
    }

    @Override
    public Player editPlayer(Long id, String username, String password, String name, String surname) {
        Assert.notNull(id, "Id must be set");
        Assert.notNull(username, "Username must be set");
        Assert.notNull(password, "Password must be set");
        Assert.notNull(name, "Name must be set");
        Assert.notNull(surname, "Surname must be set");

        Optional<Player> playerCheck = playerRepo.findById(id);
        if (playerCheck.isEmpty()) {
            throw new EntityMissingException(Player.class, id);
        }
        Optional<Player> usernameCheck = playerRepo.findByUsername(username);
        if (!usernameCheck.isEmpty()) {
            throw new RequestDeniedException("Username " + username + " already in use.");
        }
        Player player = playerCheck.get();
        player.setUsername(username);
        player.setName(name);
        player.setSurname(surname);
        player.setPasswordhash(passwordEncoder.encode(password));
        playerRepo.save(player);
        return player;
    }

    @Override
    public Player login(String username, String password) {
        Optional<Player> playerCheck = playerRepo.findByUsername(username);
        if (!playerCheck.isPresent()) {
            throw new RequestDeniedException("Invalid login");
        }
        Player player = playerCheck.get();
        if (!passwordEncoder.matches(password, player.getPasswordhash())) {
            throw new RequestDeniedException("Invalid login");
        }
        return player;
    }
}
