package ru.vers.news.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vers.news.domain.RssItems;

public interface RssItemsRepository extends JpaRepository<RssItems, Long> {

  Optional<RssItems> findByTitle(String title);

}
