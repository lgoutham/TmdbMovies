package com.example.greddy.movies.ui.home;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greddy.movies.utility.ScreenUtils;

public class LinearSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final Context context;
    private final int spaceSize;

    public LinearSpaceItemDecoration(Context context, int spaceSize) {
        this.context = context;
        this.spaceSize = spaceSize;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) == 0) {
            outRect.left = ScreenUtils.convertDpToPx(context, spaceSize);
            outRect.right = ScreenUtils.convertDpToPx(context, (float) (spaceSize * 0.6));
        } else if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
            outRect.right = ScreenUtils.convertDpToPx(context, spaceSize);
        } else {
            outRect.right = ScreenUtils.convertDpToPx(context, (float) (spaceSize * 0.6));
        }
    }
}
