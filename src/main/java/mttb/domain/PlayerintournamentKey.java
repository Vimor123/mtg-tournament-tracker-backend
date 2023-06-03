package mttb.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class PlayerintournamentKey implements Serializable {
    @Column(name = "idtournament")
    private Long idtournament;

    @Column(name = "idplayer")
    private Long idplayer;

    public PlayerintournamentKey() {}

    public PlayerintournamentKey(Long idtournament, Long idplayer) {
        this.idtournament = idtournament;
        this.idplayer = idplayer;
    }

    public Long getIdtournament() {
        return idtournament;
    }

    public void setIdtournament(Long idtournament) {
        this.idtournament = idtournament;
    }

    public Long getIdplayer() {
        return idplayer;
    }

    public void setIdplayer(Long idplayer) {
        this.idplayer = idplayer;
    }
}
