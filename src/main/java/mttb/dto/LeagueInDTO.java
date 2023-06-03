package mttb.dto;

public class LeagueInDTO {
    private LoginDTO login;
    private String nameleague;
    private String leaguestart;

    public LeagueInDTO() {}

    public LeagueInDTO(LoginDTO login, String nameleague, String leaguestart) {
        this.login = login;
        this.nameleague = nameleague;
        this.leaguestart = leaguestart;
    }

    public LoginDTO getLogin() {
        return login;
    }

    public void setLogin(LoginDTO login) {
        this.login = login;
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
}
