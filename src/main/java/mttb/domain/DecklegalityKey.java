package mttb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class DecklegalityKey implements Serializable {
    @Column(name = "idformat")
    private Long idformat;

    @Column(name = "iddeck")
    private Long iddeck;

    public DecklegalityKey() {}

    public DecklegalityKey(Long idformat, Long iddeck) {
        this.idformat = idformat;
        this.iddeck = iddeck;
    }

    public Long getIdformat() {
        return idformat;
    }

    public void setIdformat(Long idformat) {
        this.idformat = idformat;
    }

    public Long getIddeck() {
        return iddeck;
    }

    public void setIddeck(Long iddeck) {
        this.iddeck = iddeck;
    }
}
