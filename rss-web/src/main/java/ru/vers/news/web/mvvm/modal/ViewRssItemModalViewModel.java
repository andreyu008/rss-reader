package ru.vers.news.web.mvvm.modal;

import java.util.HashMap;
import lombok.Getter;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import ru.vers.news.web.common.UiUtil;
import ru.vers.news.web.domain.RssItems;
import ru.vers.news.web.service.ViewRssItemsService;

@VariableResolver(DelegatingVariableResolver.class)
public class ViewRssItemModalViewModel {

  @WireVariable
  private ViewRssItemsService viewRssItemsService;

  @Getter
  private RssItems item;

  @Init
  public void init(@ExecutionArgParam("idRssItems") Long idRssItems){
    this.item = viewRssItemsService.getItem(idRssItems);
  }

  public static void windowItem(Long idRssItems){
    HashMap<String, Object> params = new HashMap<>();
    params.put("idRssItems", idRssItems);
    UiUtil.showWindow("~./zul/itemModal.zul", params);
  }

}
