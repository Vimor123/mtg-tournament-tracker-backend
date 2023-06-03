package mttb.dao;

import mttb.domain.Deck;
import mttb.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeckRepository extends JpaRepository<Deck, Long> {
    boolean existsByIddeck(Long id);
    Optional<Deck> findByPlayerAndNamedeckAndInuse(Player player, String namedeck, boolean inuse);
    List<Deck> findAllByPlayerAndInuse(Player player, boolean inuse);
}
