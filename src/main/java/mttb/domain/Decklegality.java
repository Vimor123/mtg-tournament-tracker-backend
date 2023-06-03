package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Decklegality {
    @EmbeddedId
    private  DecklegalityKey id;

    @ManyToOne
    @MapsId("idformat")
    @JoinColumn(name = "idformat")
    private Format format;

    @ManyToOne
    @MapsId("iddeck")
    @JoinColumn(name = "iddeck")
    private Deck deck;

    @NotNull
    private boolean legal;

    public Decklegality() {}

    public Decklegality(DecklegalityKey id, Format format, Deck deck, boolean legal) {
        this.id = id;
        this.format = format;
        this.deck = deck;
        this.legal = legal;
    }

    public DecklegalityKey getId() {
        return id;
    }

    public void setId(DecklegalityKey id) {
        this.id = id;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }
}
