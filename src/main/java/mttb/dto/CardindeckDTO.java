package mttb.dto;

public class CardindeckDTO {
    private String cardname;
    private int quantity;
    private boolean insideboard;

    public CardindeckDTO(String cardname, int quantity, boolean insideboard) {
        this.cardname = cardname;
        this.quantity = quantity;
        this.insideboard = insideboard;
    }

    public String getCardname() {
        return cardname;
    }

    public void setCardname(String carname) {
        this.cardname = carname;
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
