package com.example.greddy.movies.ui.tvseriesdetail.season;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greddy.movies.utility.ScreenUtils;

public class TvSeriesSeasonsItemDecoration extends RecyclerView.ItemDecoration {

    private final Context context;
    private final int spanCount;
    private final int space;

    public TvSeriesSeasonsItemDecoration(Context context, int spanCount, int space) {
        this.context = context;
        this.spanCount = spanCount;
        this.space = space;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        int column = position % spanCount;
        outRect.left = ScreenUtils.convertDpToPx(context, space - column * space / spanCount);
        outRect.right = ScreenUtils.convertDpToPx(context, (column + 1) * space / spanCount);
        outRect.bottom = ScreenUtils.convertDpToPx(context, space);
        if (position < spanCount) {
            outRect.top = ScreenUtils.convertDpToPx(context, space);
        }
    }
}
