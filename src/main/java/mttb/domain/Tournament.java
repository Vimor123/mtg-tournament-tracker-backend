package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.Set;

@Entity
public class Tournament {
    @Id
    @GeneratedValue
    private Long idtournament;

    @NotNull
    private String nametournament;

    @NotNull
    private Timestamp tournamentstart;

    @NotNull
    private Timestamp tournamentend;

    @ManyToOne
    @JoinColumn(name = "idformat", nullable = false)
    private Format format;

    @ManyToOne
    @JoinColumn(name = "idleague", nullable = false)
    private League league;

    @OneToMany(mappedBy = "tournament")
    private Set<Playerintournament> playersintournament;

    public Tournament() {}

    public Tournament(String nametournament, Timestamp tournamentstart, Timestamp tournamentend, Format format, League league, Set<Playerintournament> playersintournament) {
        this.nametournament = nametournament;
        this.tournamentstart = tournamentstart;
        this.tournamentend = tournamentend;
        this.format = format;
        this.league = league;
        this.playersintournament = playersintournament;
    }

    public Long getIdtournament() {
        return idtournament;
    }

    public void setIdtournament(Long idtournament) {
        this.idtournament = idtournament;
    }

    public String getNametournament() {
        return nametournament;
    }

    public void setNametournament(String nametournament) {
        this.nametournament = nametournament;
    }

    public Timestamp getTournamentstart() {
        return tournamentstart;
    }

    public void setTournamentstart(Timestamp tournamentstart) {
        this.tournamentstart = tournamentstart;
    }

    public Timestamp getTournamentend() {
        return tournamentend;
    }

    public void setTournamentend(Timestamp tournamentend) {
        this.tournamentend = tournamentend;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Set<Playerintournament> getPlayersintournament() {
        return playersintournament;
    }

    public void setPlayersintournament(Set<Playerintournament> playersintournament) {
        this.playersintournament = playersintournament;
    }
}
