package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class Card {
    @Id
    private Long idcard;

    @NotNull
    private String namecard;

    @NotNull
    private String manacost;

    @NotNull
    private String picture;

    @ManyToMany
    @JoinTable(
            name = "cardcolor",
            joinColumns = @JoinColumn(name = "idcard"),
            inverseJoinColumns = @JoinColumn(name = "idcolor")
    )
    private Set<Color> cardcolors;

    @ManyToMany
    @JoinTable(
            name = "cardtypecard",
            joinColumns = @JoinColumn(name = "idcard"),
            inverseJoinColumns = @JoinColumn(name = "idcardtype")
    )
    private Set<Cardtype> cardtypes;

    public Card() {}

    public Card(Long idcard, String namecard, String manacost, String picture, Set<Color> cardcolors, Set<Cardtype> cardtypes) {
        this.idcard = idcard;
        this.namecard = namecard;
        this.manacost = manacost;
        this.picture = picture;
        this.cardcolors = cardcolors;
        this.cardtypes = cardtypes;
    }

    public Long getIdcard() {
        return idcard;
    }

    public void setIdcard(Long idcard) {
        this.idcard = idcard;
    }

    public String getNamecard() {
        return namecard;
    }

    public void setNamecard(String namecard) {
        this.namecard = namecard;
    }

    public String getManacost() {
        return manacost;
    }

    public void setManacost(String manacost) {
        this.manacost = manacost;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Set<Color> getCardcolors() {
        return cardcolors;
    }

    public void setCardcolors(Set<Color> cardcolors) {
        this.cardcolors = cardcolors;
    }

    public Set<Cardtype> getCardtypes() {
        return cardtypes;
    }

    public void setCardtypes(Set<Cardtype> cardtypes) {
        this.cardtypes = cardtypes;
    }
}
