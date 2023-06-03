package mttb.dao;

import mttb.domain.League;
import mttb.domain.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, Long> {
    Tournament save(Tournament tournament);
    List<Tournament> findByLeague(League league);
}
