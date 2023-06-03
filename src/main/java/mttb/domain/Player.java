package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long idplayer;

    @Column(unique = true)
    @NotNull
    private String username;

    @NotNull
    private String passwordhash;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private boolean adminprivileges;

    public Player() {}

    public Player(String username, String passwordHash, String name, String surname) {
        this.username = username;
        this.passwordhash = passwordHash;
        this.name = name;
        this.surname = surname;
        this.adminprivileges = false;
    }

    public Long getIdplayer() {
        return idplayer;
    }

    public void setIdplayer(Long idPlayer) {
        this.idplayer = idPlayer;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordhash() {
        return passwordhash;
    }

    public void setPasswordhash(String passwordHash) {
        this.passwordhash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isAdminprivileges() {
        return adminprivileges;
    }

    public void setAdminprivileges(boolean adminPrivileges) {
        this.adminprivileges = adminPrivileges;
    }
}
