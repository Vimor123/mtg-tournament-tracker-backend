package mttb.dto;

public class PlayerInTournamentDTO {
    private Long idplayer;
    private Long iddeck;

    private int position;
    private int points;
    private int pointsleague;
    private int wins;
    private int losses;
    private int draws;

    public PlayerInTournamentDTO() {}

    public PlayerInTournamentDTO(Long idplayer, Long iddeck, int position, int points, int pointsleague, int wins, int losses, int draws) {
        this.idplayer = idplayer;
        this.iddeck = iddeck;
        this.position = position;
        this.points = points;
        this.pointsleague = pointsleague;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
    }

    public Long getIdplayer() {
        return idplayer;
    }

    public void setIdplayer(Long idplayer) {
        this.idplayer = idplayer;
    }

    public Long getIddeck() {
        return iddeck;
    }

    public void setIddeck(Long iddeck) {
        this.iddeck = iddeck;
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
