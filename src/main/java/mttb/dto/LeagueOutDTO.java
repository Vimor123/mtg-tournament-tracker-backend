package mttb.dto;

import mttb.domain.Tournament;

import java.util.List;

public class LeagueOutDTO {
    private Long idleague;
    private String nameleague;
    private String leaguestart;
    private String leagueend;
    private List<TournamentOutDTO> tournaments;

    public LeagueOutDTO(Long idleague, String nameleague, String leaguestart, String leagueend, List<TournamentOutDTO> tournaments) {
        this.idleague = idleague;
        this.nameleague = nameleague;
        this.leaguestart = leaguestart;
        this.leagueend = leagueend;
        this.tournaments = tournaments;
    }

    public Long getIdleague() {
        return idleague;
    }

    public void setIdleague(Long idleague) {
        this.idleague = idleague;
    }

    public String getNameleague() {
        return nameleague;
    }

    public void setNameleague(String nameleague) {
        this.nameleague = nameleague;
    }

    public String getLeaguestart() {
        return leaguestart;
    }

    public void setLeaguestart(String leaguestart) {
        this.leaguestart = leaguestart;
    }

    public String getLeagueend() {
        return leagueend;
    }

    public void setLeagueend(String leagueend) {
        this.leagueend = leagueend;
    }

    public List<TournamentOutDTO> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<TournamentOutDTO> tournaments) {
        this.tournaments = tournaments;
    }
}
