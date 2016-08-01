package com.android.mayojava.trivago.search.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.RecyclerViewItemClickListener;
import com.android.mayojava.trivago.custom.CustomLeadingMarginSpan2;
import com.android.mayojava.trivago.repository.models.search.Movie;
import com.android.mayojava.trivago.repository.models.search.SearchResult;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for search movies recycler view
 */
public class SearchMoviesRecyclerAdapter extends
        RecyclerView.Adapter<SearchMoviesRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<SearchResult> mSearchResults;
    private RecyclerViewItemClickListener recyclerViewItemClickListener;

    public SearchMoviesRecyclerAdapter(Context context, List<SearchResult> searchResults) {
        this.mContext = context;
        this.mSearchResults = searchResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View searchResultView = inflater.inflate(R.layout.items_layout_search_result,
                parent, false);
        return new ViewHolder(searchResultView, recyclerViewItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchResult result = mSearchResults.get(position);

        String url = result.getMovie().getImages().getPoster().getThumb();

        String title = String.format("%s: %s", mContext.
                getString(R.string.movie_title), result.getMovie().getTitle());

        holder.mMovieTitleTextView.setText(getBoldText(title , 5));

        Movie movie = result.getMovie();

        if (movie.getYear() != null) {
            String year = String.format("%s: %s",
                    mContext.getString(R.string.movie_year), String.valueOf(movie.getYear()));

            holder.mMovieReleaseYearTextView.setText(getBoldText(year, 4));
        }

        if (movie.getRating() != null) {
            String rating = String.format("%s: %.1f", mContext.
                    getString(R.string.text_ratings), movie.getRating());

            holder.mRatingsTextView.setText(getBoldText(rating, 5));
        } else {
            holder.mRatingsTextView.setText(String.format("%s %s",
                    mContext.getString(R.string.text_ratings), "n/r"));
        }


        if (url != null) {
            Picasso.with(mContext)
                    .load(url)
                    .into(holder.mMovieImageView);
        }

        String movieOverview = result.getMovie().getOverview();

        if (movieOverview != null) {
            SpannableString overview = new SpannableString(movieOverview);
            overview.setSpan(new CustomLeadingMarginSpan2(3, 220), 0, overview.length(), 0);
            holder.mMovieOverview.setText(overview);
        }

    }

    @Override
    public int getItemCount() {
        return mSearchResults.size();
    }

    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener onItemClickListener) {
        this.recyclerViewItemClickListener = onItemClickListener;
    }

    /**
     * clears search result list and loads new data
     */
    public void updateSearchResponse(List<SearchResult> results) {
        mSearchResults.clear();
        appendMatchingSearchResult(results);
    }

    /**
     * append new result from pagination
     */
    public void appendMatchingSearchResult(List<SearchResult> results) {
        mSearchResults.addAll(results);
        notifyDataSetChanged();
    }

    /**
     * clear search result list content
     */
    public void clearContent() {
        mSearchResults.clear();
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        @BindView(R.id.text_view_movie_title) TextView mMovieTitleTextView;
        @BindView(R.id.text_view_release_year) TextView mMovieReleaseYearTextView;
        @BindView(R.id.text_view_ratings) TextView mRatingsTextView;
        @BindView(R.id.text_view_movie_overview) TextView mMovieOverview;
        @BindView(R.id.image_view_movie_thumb_nail) ImageView mMovieImageView;

        private RecyclerViewItemClickListener onItemClickListener;

        public ViewHolder (View itemView, RecyclerViewItemClickListener itemClickListener) {
            super(itemView);
            this.onItemClickListener = itemClickListener;

            itemView.setOnTouchListener(this);

            ButterKnife.bind(this, itemView);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() !=
                    MotionEvent.ACTION_MOVE) {
                this.onItemClickListener.onItemClick(v, getAdapterPosition(),
                        event.getX(), event.getY());
            }
            return true;
        }
    }

    /**
     * returns the item at this position
     *
     * @param position
     * @return
     */
    public SearchResult getItemAt(int position) {
        return mSearchResults.get(position);
    }

    private SpannableString getBoldText(String text, int stop) {
        SpannableString string = new SpannableString(text);
        string.setSpan(new StyleSpan(Typeface.BOLD), 0, stop, 0);
        return string;
    }
}
