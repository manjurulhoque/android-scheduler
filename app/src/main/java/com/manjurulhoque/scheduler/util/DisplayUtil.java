package com.manjurulhoque.scheduler.util;

import android.content.Context;

public class DisplayUtil {
    public static int getPx(Context context, int dimensionDp) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dimensionDp * density + 0.5f);
    }
}
