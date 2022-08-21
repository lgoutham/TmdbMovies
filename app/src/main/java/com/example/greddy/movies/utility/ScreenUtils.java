package com.example.greddy.movies.utility;

import android.content.Context;

public class ScreenUtils {

    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
