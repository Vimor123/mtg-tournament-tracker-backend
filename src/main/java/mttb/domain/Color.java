package mttb.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Color {
    @Id
    private Long idcolor;

    @NotNull
    private String namecolor;

    public Color() {}

    public Long getIdcolor() {
        return idcolor;
    }

    public void setIdcolor(Long idcolor) {
        this.idcolor = idcolor;
    }

    public String getNamecolor() {
        return namecolor;
    }

    public void setNamecolor(String namecolor) {
        this.namecolor = namecolor;
    }
}
