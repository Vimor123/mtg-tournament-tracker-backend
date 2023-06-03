package mttb.dao;

import mttb.domain.Cardlegality;
import mttb.domain.CardlegalityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardlegalityRepository extends JpaRepository<Cardlegality, CardlegalityKey> {
}
