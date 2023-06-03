package mttb.dto;

import java.util.List;

public class TournamentInDTO {
    private LoginDTO login;
    private String nametournament;
    private String tournamentstart;
    private String tournamentend;
    private String format;
    private Long idleague;
    private List<PlayerInTournamentDTO> players;

    public TournamentInDTO(LoginDTO login, String nametournament, String tournamentstart, String tournamentend, String format, Long idleague, List<PlayerInTournamentDTO> players) {
        this.login = login;
        this.nametournament = nametournament;
        this.tournamentstart = tournamentstart;
        this.tournamentend = tournamentend;
        this.format = format;
        this.idleague = idleague;
        this.players = players;
    }

    public LoginDTO getLogin() {
        return login;
    }

    public void setLogin(LoginDTO login) {
        this.login = login;
    }

    public String getNametournament() {
        return nametournament;
    }

    public void setNametournament(String nametournament) {
        this.nametournament = nametournament;
    }

    public String getTournamentstart() {
        return tournamentstart;
    }

    public void setTournamentstart(String tournamentstart) {
        this.tournamentstart = tournamentstart;
    }

    public String getTournamentend() {
        return tournamentend;
    }

    public void setTournamentend(String tournamentend) {
        this.tournamentend = tournamentend;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Long getIdleague() {
        return idleague;
    }

    public void setIdleague(Long idleague) {
        this.idleague = idleague;
    }

    public List<PlayerInTournamentDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerInTournamentDTO> players) {
        this.players = players;
    }
}
