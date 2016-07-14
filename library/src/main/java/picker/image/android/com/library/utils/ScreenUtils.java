package picker.image.android.com.library.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Kevin on 2016/7/14.
 */
public class ScreenUtils {

  private static DisplayMetrics metrics;

  public static DisplayMetrics getDisplayMetrics(Context context) {
    if (metrics == null) {
      metrics = context.getResources().getDisplayMetrics();
    }

    return metrics;
  }

  public static float getDisplayWidth(Context context) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    return dm.widthPixels;
  }

  public static float getDisplayHeight(Context context) {
    DisplayMetrics dm = context.getResources().getDisplayMetrics();
    return dm.heightPixels;
  }
}
