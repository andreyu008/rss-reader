package ru.vers.news.web.mvvm;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.ListModelList;
import ru.vers.news.web.domain.RefRssDetails;
import ru.vers.news.web.domain.RssItems;
import ru.vers.news.web.mvvm.modal.ViewRssItemModalViewModel;
import ru.vers.news.web.service.ViewRssDetailsService;
import ru.vers.news.web.service.ViewRssItemsService;

@VariableResolver(DelegatingVariableResolver.class)
public class RSSViewModel {

  @Getter
  @Setter
  private ListModelList<RefRssDetails> contentList;
  @Getter
  @Setter
  private RefRssDetails selectedEntryDetails;
  @Getter
  private List<RssItems> rssItems;
  @Getter
  @Setter
  private RssItems selectedItemEntry;
  @WireVariable
  private ViewRssDetailsService viewRssDetailsService;
  @WireVariable
  private ViewRssItemsService viewRssItemsService;
  @Getter
  @Setter
  private String keyword;


  @Init
  public void init() {
    this.contentList = new ListModelList<>(this.viewRssDetailsService.retrieveData());

  }

  @Command
  @NotifyChange("rssItems")
  public void search() {
    rssItems = viewRssItemsService.search(keyword);
  }

  @Command
  @NotifyChange({"rssItems", "keyword"})
  public void clear() {
    getingRssItems();
    this.keyword = null;
  }

  @Command
  @NotifyChange({"rssItems", "selectedItemEntry"})
  public void getingRssItems() {
    this.rssItems = new ListModelList<>(
        this.viewRssItemsService.retrieveRssItems(this.selectedEntryDetails.getIdRssDetail()));
  }

  @Command
  public void openModalWin(@BindingParam("idRssItems") Long idRssItems) {
    ViewRssItemModalViewModel.windowItem(idRssItems);
  }

}
