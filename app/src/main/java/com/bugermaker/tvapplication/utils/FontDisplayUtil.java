package com.bugermaker.tvapplication.utils;

import android.content.Context;

/**
 * dp、sp 、 px之间的相互转化的工具类
 */
public class FontDisplayUtil {

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

}
