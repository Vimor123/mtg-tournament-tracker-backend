package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cardlegality {
    @EmbeddedId
    private CardlegalityKey id;

    @ManyToOne
    @MapsId("idformat")
    @JoinColumn(name = "idformat")
    private Format format;

    @ManyToOne
    @MapsId("idcard")
    @JoinColumn(name = "idcard")
    private Card card;

    @NotNull
    private boolean legal;

    public Cardlegality() {}

    public Cardlegality(CardlegalityKey id, Format format, Card card, boolean legal) {
        this.id = id;
        this.format = format;
        this.card = card;
        this.legal = legal;
    }

    public CardlegalityKey getId() {
        return id;
    }

    public void setId(CardlegalityKey id) {
        this.id = id;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }
}
