package ru.vers.news.web.mvvm.modal;

import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import ru.vers.news.web.common.UiUtil;
import ru.vers.news.web.domain.RssItems;
import ru.vers.news.web.parse.HtmlHelper;
import ru.vers.news.web.service.ViewRssItemsService;

@VariableResolver(DelegatingVariableResolver.class)
public class ViewRssItemModalViewModel {

  @WireVariable
  private ViewRssItemsService viewRssItemsService;

  @Getter
  private RssItems item;

  @WireVariable
  private HtmlHelper htmlHelper;

  @Getter
  @Setter
  private String page;
  @Getter
  private Boolean flagVisibleDesription = true;
  @Getter
  private Boolean flagVisibleFullPage = false;

  @Init
  public void init(@ExecutionArgParam("idRssItems") Long idRssItems){
    this.item = viewRssItemsService.getItem(idRssItems);
  }

  public static void windowItem(Long idRssItems){
    HashMap<String, Object> params = new HashMap<>();
    params.put("idRssItems", idRssItems);
    UiUtil.showWindow("~./zul/itemModal.zul", params);
  }

  @Command
  @NotifyChange({"page", "flagVisibleDesription", "flagVisibleFullPage"})
  public void parsePageFull(){
    flagVisibleDesription = false;
    flagVisibleFullPage = true;
    page = htmlHelper.getElementshtml(item.getLink(), true);
    BindUtils.postNotifyChange(null, null, this, "page");

  }

  @Command
  @NotifyChange({"page", "flagVisibleDesription", "flagVisibleFullPage"})
  public void parsePageOnlyRext(){
    flagVisibleDesription = false;
    flagVisibleFullPage = true;
    page = htmlHelper.getElementshtml(item.getLink(), false);
    BindUtils.postNotifyChange(null, null, this, "page");

  }

  @Command
  @NotifyChange({"page","item", "flagVisibleDesription", "flagVisibleFullPage"})
  public void backPage(){
    flagVisibleDesription = true;
    flagVisibleFullPage = false;
    BindUtils.postNotifyChange(null, null, this, "page");

  }

}
