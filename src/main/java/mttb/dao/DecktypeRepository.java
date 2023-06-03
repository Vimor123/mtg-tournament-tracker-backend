package mttb.dao;

import mttb.domain.Decktype;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DecktypeRepository extends JpaRepository<Decktype, Long> {
    Optional<Decktype> findByNamedecktype(String name);
}
