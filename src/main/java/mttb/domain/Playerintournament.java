package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Playerintournament {
    @EmbeddedId
    private PlayerintournamentKey id;

    @ManyToOne
    @MapsId("idtournament")
    @JoinColumn(name = "idtournament")
    private Tournament tournament;

    @ManyToOne
    @MapsId("idplayer")
    @JoinColumn(name = "idplayer")
    private Player player;

    @ManyToOne
    @JoinColumn(name = "iddeck", nullable = true)
    private Deck deck;

    @NotNull
    private int position;

    @NotNull
    private int points;

    @NotNull
    private int pointsleague;

    @NotNull
    private int wins;

    @NotNull
    private int losses;

    @NotNull
    private int draws;

    public Playerintournament() {}

    public Playerintournament(PlayerintournamentKey id, Tournament tournament, Player player, Deck deck, int position, int points, int pointsleague, int wins, int losses, int draws) {
        this.id = id;
        this.tournament = tournament;
        this.player = player;
        this.deck = deck;
        this.position = position;
        this.points = points;
        this.pointsleague = pointsleague;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public PlayerintournamentKey getId() {
        return id;
    }

    public void setId(PlayerintournamentKey id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPointsleague() {
        return pointsleague;
    }

    public void setPointsleague(int pointsleague) {
        this.pointsleague = pointsleague;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public int getDraws() {
        return draws;
    }

    public void setDraws(int draws) {
        this.draws = draws;
    }
}
