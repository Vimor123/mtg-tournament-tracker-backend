package mttb.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

@Entity
public class League {
    @Id
    @GeneratedValue
    private Long idleague;

    @NotNull
    private String nameleague;

    @NotNull
    private Date leaguestart;

    private Date leagueend;

    public League() {}

    public League(String nameleague, Date leaguestart) {
        this.nameleague = nameleague;
        this.leaguestart = leaguestart;
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

    public Date getLeaguestart() {
        return leaguestart;
    }

    public void setLeaguestart(Date leaguestart) {
        this.leaguestart = leaguestart;
    }

    public Date getLeagueend() {
        return leagueend;
    }

    public void setLeagueend(Date leagueend) {
        this.leagueend = leagueend;
    }
}
