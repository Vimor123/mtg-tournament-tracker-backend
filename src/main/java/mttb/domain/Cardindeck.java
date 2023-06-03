package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cardindeck {
    @EmbeddedId
    private CardindeckKey id;

    @ManyToOne
    @MapsId("iddeck")
    @JoinColumn(name = "iddeck")
    private Deck deck;

    @ManyToOne
    @MapsId("idcard")
    @JoinColumn(name = "idcard")
    private Card card;

    @NotNull
    private int quantity;

    @NotNull
    private boolean insideboard;

    public Cardindeck() {}

    public Cardindeck(CardindeckKey id, Deck deck, Card card, int quantity, boolean insideboard) {
        this.id = id;
        this.deck = deck;
        this.card = card;
        this.quantity = quantity;
        this.insideboard = insideboard;
    }

    public CardindeckKey getId() {
        return id;
    }

    public void setId(CardindeckKey id) {
        this.id = id;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isInsideboard() {
        return insideboard;
    }

    public void setInsideboard(boolean insideboard) {
        this.insideboard = insideboard;
    }
}
