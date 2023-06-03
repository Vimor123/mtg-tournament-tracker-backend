package mttb.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Format {
    @Id
    private Long idformat;

    @NotNull
    private String nameformat;

    public Format() {}

    public Format(Long idformat, String nameformat) {
        this.idformat = idformat;
        this.nameformat = nameformat;
    }

    public Long getIdformat() {
        return idformat;
    }

    public void setIdformat(Long idformat) {
        this.idformat = idformat;
    }

    public String getNameformat() {
        return nameformat;
    }

    public void setNameformat(String nameformat) {
        this.nameformat = nameformat;
    }
}
