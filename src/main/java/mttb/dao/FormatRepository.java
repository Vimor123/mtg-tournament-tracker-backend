package mttb.dao;

import mttb.domain.Format;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FormatRepository extends JpaRepository<Format, Long> {
    Optional<Format> findByNameformat(String name);
}
