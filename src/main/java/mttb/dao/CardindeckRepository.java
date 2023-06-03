package mttb.dao;

import mttb.domain.Cardindeck;
import mttb.domain.CardindeckKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardindeckRepository extends JpaRepository<Cardindeck, CardindeckKey> {
}
