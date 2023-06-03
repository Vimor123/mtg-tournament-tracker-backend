package mttb.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Cardtype {
    @Id
    private Long idcardtype;

    @NotNull
    private String namecardtype;

    public Cardtype() {}

    public Long getIdcardtype() {
        return idcardtype;
    }

    public void setIdcardtype(Long idcardtype) {
        this.idcardtype = idcardtype;
    }

    public String getNamecardtype() {
        return namecardtype;
    }

    public void setNamecardtype(String namecardtype) {
        this.namecardtype = namecardtype;
    }
}
