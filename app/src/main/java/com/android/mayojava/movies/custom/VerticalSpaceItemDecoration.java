package com.android.mayojava.movies.custom;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by mayowa.adegeye on 01/08/2016.
 */
public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private final  int mVerticalSpaceHeight;

    public VerticalSpaceItemDecoration(int verticalSpaceHeight) {
        this.mVerticalSpaceHeight = verticalSpaceHeight;

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildAdapterPosition(view) != parent.getAdapter().getItemCount() - 1) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }
}
