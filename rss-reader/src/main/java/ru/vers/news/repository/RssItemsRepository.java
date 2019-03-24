package ru.vers.news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vers.news.domain.RssItems;

public interface RssItemsRepository extends JpaRepository<RssItems, Long> {

}
