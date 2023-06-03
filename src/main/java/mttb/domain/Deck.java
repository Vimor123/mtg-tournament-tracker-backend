package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class Deck {

    @Id
    @GeneratedValue
    private Long iddeck;

    @NotNull
    private String namedeck;

    @NotNull
    private boolean inuse;

    @ManyToOne
    @JoinColumn(name = "idplayer", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "iddecktype", nullable = false)
    private Decktype decktype;

    @ManyToMany
    @JoinTable(
            name = "deckcolor",
            joinColumns = @JoinColumn(name = "iddeck"),
            inverseJoinColumns = @JoinColumn(name = "idcolor")
    )
    private Set<Color> deckcolors;

    @OneToMany(mappedBy = "deck")
    private Set<Cardindeck> cardsindeck;

    public Deck() {}

    public Deck(String namedeck, Player player, Decktype decktype, Set<Color> deckcolors, Set<Cardindeck> cardsindeck) {
        this.namedeck = namedeck;
        this.player = player;
        this.decktype = decktype;
        this.deckcolors = deckcolors;
    }

    public Long getIddeck() {
        return iddeck;
    }

    public void setIddeck(Long iddeck) {
        this.iddeck = iddeck;
    }

    public String getNamedeck() {
        return namedeck;
    }

    public void setNamedeck(String namedeck) {
        this.namedeck = namedeck;
    }

    public boolean isInuse() {
        return inuse;
    }

    public void setInuse(boolean inuse) {
        this.inuse = inuse;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Decktype getDecktype() {
        return decktype;
    }

    public void setDecktype(Decktype decktype) {
        this.decktype = decktype;
    }

    public Set<Color> getDeckcolors() {
        return deckcolors;
    }

    public void setDeckcolors(Set<Color> deckcolors) {
        this.deckcolors = deckcolors;
    }

    public Set<Cardindeck> getCardsindeck() {
        return cardsindeck;
    }

    public void setCardsindeck(Set<Cardindeck> cardsindeck) {
        this.cardsindeck = cardsindeck;
    }
}
