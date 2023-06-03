package mttb.dao;

import mttb.domain.Decklegality;
import mttb.domain.DecklegalityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecklegalityRepository extends JpaRepository<Decklegality, DecklegalityKey> {
}
