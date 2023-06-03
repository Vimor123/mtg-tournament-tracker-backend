package mttb.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

@Entity
public class Decktype {
    @Id
    @GeneratedValue
    private Long iddecktype;

    @NotNull
    private String namedecktype;

    @ManyToMany
    @JoinTable(
            name = "decktypecolor",
            joinColumns = @JoinColumn(name = "iddecktype"),
            inverseJoinColumns = @JoinColumn(name = "idcolor")
    )
    private Set<Color> decktypecolors;

    public Decktype() {}

    public Decktype(Long iddecktype, String namedecktype, Set<Color> decktypecolors) {
        this.iddecktype = iddecktype;
        this.namedecktype = namedecktype;
        this.decktypecolors = decktypecolors;
    }

    public Long getIddecktype() {
        return iddecktype;
    }

    public void setIddecktype(Long iddecktype) {
        this.iddecktype = iddecktype;
    }

    public String getNamedecktype() {
        return namedecktype;
    }

    public void setNamedecktype(String namedecktype) {
        this.namedecktype = namedecktype;
    }

    public Set<Color> getDecktypecolors() {
        return decktypecolors;
    }

    public void setDecktypecolors(Set<Color> decktypecolors) {
        this.decktypecolors = decktypecolors;
    }
}
