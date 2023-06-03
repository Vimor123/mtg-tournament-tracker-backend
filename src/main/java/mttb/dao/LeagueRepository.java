package mttb.dao;

import mttb.domain.League;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    League save(League league);
    Optional<League> findByIdleague(Long id);
}
