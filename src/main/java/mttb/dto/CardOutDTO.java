package mttb.dto;

import mttb.domain.Card;

public class CardOutDTO {
    private Card card;
    private boolean insideboard;

    private int quantity;

    public CardOutDTO(Card card, boolean insideboard, int quantity) {
        this.card = card;
        this.insideboard = insideboard;
        this.quantity = quantity;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public boolean isInsideboard() {
        return insideboard;
    }

    public void setInsideboard(boolean insideboard) {
        this.insideboard = insideboard;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
