package com.android.mayojava.movies;

import android.view.View;

/**
 * Onclick listener called when recyclerview is clicked
 */
public interface RecyclerViewItemClickListener {

    void onItemClick(View v, int position, float x, float y);
}
