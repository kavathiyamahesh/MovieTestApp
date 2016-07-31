package com.android.mayojava.trivago.custom;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.mayojava.trivago.R;

/**
 * Applies an inset margin around each recycler view item
 */
public class InsetItemDecoration extends RecyclerView.ItemDecoration {
    private int mInsets;

    public InsetItemDecoration(Context context) {
        mInsets = context.getResources().getDimensionPixelSize(R.dimen.insets);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mInsets, mInsets, mInsets, mInsets);
    }
}
