package mttb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CardindeckKey implements Serializable {
    @Column(name = "iddeck")
    private Long iddeck;

    @Column(name = "idcard")
    private Long idcard;

    public CardindeckKey() {}

    public CardindeckKey(Long iddeck, Long idcard) {
        this.iddeck = iddeck;
        this.idcard = idcard;
    }

    public Long getIddeck() {
        return iddeck;
    }

    public void setIddeck(Long iddeck) {
        this.iddeck = iddeck;
    }

    public Long getIdcard() {
        return idcard;
    }

    public void setIdcard(Long idcard) {
        this.idcard = idcard;
    }
}
