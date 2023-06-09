package mttb.dao;

import mttb.domain.Playerintournament;
import mttb.domain.PlayerintournamentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerintournamentRepository extends JpaRepository<Playerintournament, PlayerintournamentKey> {
}
