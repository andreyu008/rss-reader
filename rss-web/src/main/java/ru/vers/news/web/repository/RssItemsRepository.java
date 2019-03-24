package ru.vers.news.web.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.vers.news.web.domain.RssItems;

public interface RssItemsRepository extends JpaRepository<RssItems, Long> {

  List<RssItems> findByIdRssDetailOrderByIdRssItemsAsc(Long idRssDetail);
}
