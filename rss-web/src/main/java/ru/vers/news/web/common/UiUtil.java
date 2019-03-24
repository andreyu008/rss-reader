package ru.vers.news.web.common;

import java.util.Map;
import lombok.experimental.UtilityClass;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

@UtilityClass
public class UiUtil {

  private static final int DURATION_INFO = 3000;
  private static final int DURATION_INFINITELE = -1;

  private static final String NOTIFICATION_POSITION_INFO = "overlap_after";

  public static void showNotificationInfo(String message) {
    Clients.showNotification(message, Clients.NOTIFICATION_TYPE_INFO, null,
        NOTIFICATION_POSITION_INFO, DURATION_INFO);
  }

  public static void showNotificationInfoInfinitely(String message) {
    Clients.showNotification(message, Clients.NOTIFICATION_TYPE_INFO, null, null,
        DURATION_INFINITELE);
  }

  public static void showNotificationError(String message) {
    Clients.showNotification(message, Clients.NOTIFICATION_TYPE_ERROR, null, null,
        DURATION_INFINITELE);
  }

  public static void showNotificationWarning(String message) {
    Clients.showNotification(message, Clients.NOTIFICATION_TYPE_WARNING, null, null,
        DURATION_INFINITELE);
  }

  /**
   * Вызов модального окна.
   *
   * @param uri zul окна
   * @param params передаваемые параметры
   * @return окно
   */
  public static Window showWindow(String uri, Map<String, Object> params) {
    Window win = (Window) Executions.createComponents(uri, null, params);
    win.doModal();
    return win;
  }



}
