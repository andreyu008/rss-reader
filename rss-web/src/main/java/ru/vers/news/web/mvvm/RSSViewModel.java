package ru.vers.news.web.mvvm;

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
  private ListModelList<RssItems> rssItems;
  @Getter
  @Setter
  private RssItems selectedItemEntry;
  @WireVariable
  private ViewRssDetailsService viewRssDetailsService;
  @WireVariable
  private ViewRssItemsService viewRssItemsService;


  @Init
  public void init() {
    this.contentList = new ListModelList<>(this.viewRssDetailsService.retrieveData());

  }

  @Command
  @NotifyChange({"rssItems", "selectedItemEntry"})
  public void test(){
    this.rssItems = new ListModelList<>(this.viewRssItemsService.retrieveRssItems(this.selectedEntryDetails.getIdRssDetail()));
    System.out.println("stop");

  }

  @Command
  public void openModalWin(@BindingParam("idRssItems") Long idRssItems){
    ViewRssItemModalViewModel.windowItem(idRssItems);
  }

}
