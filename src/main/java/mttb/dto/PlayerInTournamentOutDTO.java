package mttb.dto;

import mttb.domain.Deck;
import mttb.domain.Player;

public class PlayerInTournamentOutDTO {
    private Player player;
    private DeckOutDTO deck;
    private int position;
    private int points;
    private int pointsleague;
    private int wins;
    private int losses;
    private int draws;

    public PlayerInTournamentOutDTO() {}

    public PlayerInTournamentOutDTO(Player player, DeckOutDTO deck, int position, int points, int pointsleague, int wins, int losses, int draws) {
        this.player = player;
        this.deck = deck;
        this.position = position;
        this.points = points;
        this.pointsleague = pointsleague;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public DeckOutDTO getDeck() {
        return deck;
    }

    public void setDeck(DeckOutDTO deck) {
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
