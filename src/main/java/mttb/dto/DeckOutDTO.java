package mttb.dto;

import mttb.domain.Card;
import mttb.domain.Color;
import mttb.domain.Decktype;
import mttb.domain.Player;

import java.util.List;

public class DeckOutDTO {
    private Long iddeck;
    private String nameDeck;
    private Player player;

    private Decktype decktype;
    private List<Card> cards;

    private List<Color> colors;

    public DeckOutDTO() {}

    public DeckOutDTO(Long iddeck, String nameDeck, Player player, Decktype decktype, List<Card> cards) {
        this.iddeck = iddeck;
        this.nameDeck = nameDeck;
        this.player = player;
        this.decktype = decktype;
        this.cards = cards;
    }

    public Long getIddeck() {
        return iddeck;
    }

    public void setIddeck(Long iddeck) {
        this.iddeck = iddeck;
    }

    public String getNameDeck() {
        return nameDeck;
    }

    public void setNameDeck(String nameDeck) {
        this.nameDeck = nameDeck;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Decktype getDecktype() {
        return decktype;
    }

    public void setDecktype(Decktype decktype) {
        this.decktype = decktype;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }
}
