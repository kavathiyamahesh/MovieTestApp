package com.android.mayojava.trivago.search.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mayojava.trivago.R;
import com.android.mayojava.trivago.custom.CustomLeadingMarginSpan2;
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

    public SearchMoviesRecyclerAdapter(Context context, List<SearchResult> searchResults) {
        this.mContext = context;
        this.mSearchResults = searchResults;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View searchResultView = inflater.inflate(R.layout.items_layout_search_result,
                parent, false);
        return new ViewHolder(searchResultView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SearchResult result = mSearchResults.get(position);

        String url = result.getMovie().getImages().getPoster().getThumb();

        holder.mMovieTitleTextView.setText(result.getMovie().getTitle());
        holder.mMovieReleaseYearTextView.setText(result.getMovie().getYear().toString());
        holder.mRatingsTextView.setText(String.valueOf(result.getMovie().getRating()));

        Picasso.with(mContext)
                .load(url)
                .into(holder.mMovieImageView);

        SpannableString overview = new SpannableString(result.getMovie().getOverview());
        overview.setSpan(new CustomLeadingMarginSpan2(3, 220), 0, overview.length(), 0);
        holder.mMovieOverview.setText(overview);
    }

    @Override
    public int getItemCount() {
        return mSearchResults.size();
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view_movie_title) TextView mMovieTitleTextView;
        @BindView(R.id.text_view_release_year) TextView mMovieReleaseYearTextView;
        @BindView(R.id.text_view_ratings) TextView mRatingsTextView;
        @BindView(R.id.text_view_movie_overview) TextView mMovieOverview;
        @BindView(R.id.image_view_movie_thumb_nail) ImageView mMovieImageView;

        public ViewHolder (View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
