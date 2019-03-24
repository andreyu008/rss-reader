package ru.vers.news.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vers.news.domain.RefRssDetails;

public interface RefRssDetailsRepository extends JpaRepository<RefRssDetails, Long> {

  Optional<RefRssDetails> findByDescription(String description);

}
