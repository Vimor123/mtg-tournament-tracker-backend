package mttb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class CardlegalityKey implements Serializable {

    @Column(name = "idformat")
    private Long idformat;

    @Column(name = "idcard")
    private Long idcard;

    public CardlegalityKey() {}

    public CardlegalityKey(Long idformat, Long idcard) {
        this.idformat = idformat;
        this.idcard = idcard;
    }

    public Long getIdformat() {
        return idformat;
    }

    public void setIdformat(Long idformat) {
        this.idformat = idformat;
    }

    public Long getIdcard() {
        return idcard;
    }

    public void setIdcard(Long idcard) {
        this.idcard = idcard;
    }
}
