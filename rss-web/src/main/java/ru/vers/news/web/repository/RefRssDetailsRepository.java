package ru.vers.news.web.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vers.news.web.domain.RefRssDetails;

public interface RefRssDetailsRepository extends JpaRepository<RefRssDetails, Long> {

  Optional<RefRssDetails> findByDescription(String description);

}
