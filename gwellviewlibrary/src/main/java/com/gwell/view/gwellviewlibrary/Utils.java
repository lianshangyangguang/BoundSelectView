package com.gwell.view.gwellviewlibrary;
import android.content.Context;
/**
 * Created by Administrator on 2017/4/11.
 */
public class Utils {


         public static int dip2px(Context context, float dipValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }

}
