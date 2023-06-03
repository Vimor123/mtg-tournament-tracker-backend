package mttb.dto;

import java.util.List;

public class DecktypeDTO {
    private LoginDTO login;
    private String namedecktype;
    private List<String> colors;

    public DecktypeDTO() {}

    public DecktypeDTO(LoginDTO login, String namedecktype, List<String> colors) {
        this.login = login;
        this.namedecktype = namedecktype;
        this.colors = colors;
    }

    public LoginDTO getLogin() {
        return login;
    }

    public void setLogin(LoginDTO login) {
        this.login = login;
    }

    public String getNamedecktype() {
        return namedecktype;
    }

    public void setNamedecktype(String namedeck) {
        this.namedecktype = namedeck;
    }

    public List<String> getColors() {
        return colors;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }
}
