package mttb.dto;

import java.util.List;

public class DeckInDTO {
    private LoginDTO login;
    private String namedeck;
    private Long iddecktype;
    private List<CardindeckDTO> cards;

    public DeckInDTO() {}

    public DeckInDTO(LoginDTO login, String namedeck, Long iddecktype, List<CardindeckDTO> cards) {
        this.login = login;
        this.namedeck = namedeck;
        this.iddecktype = iddecktype;
        this.cards = cards;
    }

    public LoginDTO getLogin() {
        return login;
    }

    public void setLogin(LoginDTO login) {
        this.login = login;
    }

    public String getNamedeck() {
        return namedeck;
    }

    public void setNamedeck(String namedeck) {
        this.namedeck = namedeck;
    }

    public Long getIddecktype() {
        return iddecktype;
    }

    public void setIddecktype(Long iddecktype) {
        this.iddecktype = iddecktype;
    }

    public List<CardindeckDTO> getCards() {
        return cards;
    }

    public void setCards(List<CardindeckDTO> cards) {
        this.cards = cards;
    }
}
