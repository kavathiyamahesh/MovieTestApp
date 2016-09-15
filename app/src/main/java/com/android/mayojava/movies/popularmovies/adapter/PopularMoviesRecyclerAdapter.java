package com.android.mayojava.movies.popularmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.mayojava.movies.R;
import com.android.mayojava.movies.RecyclerViewItemClickListener;
import com.android.mayojava.movies.repository.models.PopularMovie;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Adapter for popular movies recycler view
 */
public class PopularMoviesRecyclerAdapter extends
        RecyclerView.Adapter<PopularMoviesRecyclerAdapter.ViewHolder> {

    private Context mContext;
    private List<PopularMovie> mPopularMovies;
    private RecyclerViewItemClickListener mRecyclerItemClickListener;

    public PopularMoviesRecyclerAdapter(Context context, List<PopularMovie> popularMovies) {
        this.mContext = context;
        this.mPopularMovies = popularMovies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View popularMovieView = inflater.inflate(R.layout.items_layout_popular_movies,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(popularMovieView, mRecyclerItemClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PopularMovie movie = mPopularMovies.get(position);

        String url = movie.getImages().getPoster().getThumb();

        holder.mMovieTitle.setText(movie.getTitle());
        holder.mMoviesRatings.setRating(computeRating(movie.getRating()));
        holder.mTextViewReleasedYear.setText(movie.getYear().toString());

        Picasso.with(mContext)
                .load(url)
                .into(holder.mMovieImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        mPopularMovies.get(position).setMovieImageLoadedCompletely(true);
                    }

                    @Override
                    public void onError() {

                    }
                });

        if (url == null) {
            mPopularMovies.get(position).setMovieImageLoadedCompletely(true);
        }
    }

    public void setRecyclerViewItemClickListener(RecyclerViewItemClickListener onClickListener) {
        this.mRecyclerItemClickListener = onClickListener;
    }

    public boolean hasItemImageLoadedCompletely(int position) {
        return mPopularMovies.get(position).isMovieImageLoadedCompletely();
    }

    @Override
    public int getItemCount() {
        return mPopularMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener {
        @BindView(R.id.image_view_movie_thumb_nail) ImageView mMovieImageView;
        @BindView(R.id.text_view_movie_title) TextView mMovieTitle;
        @BindView(R.id.text_view_movie_released_year) TextView mTextViewReleasedYear;
        @BindView(R.id.ratings_movie) RatingBar mMoviesRatings;

        private RecyclerViewItemClickListener onItemClickListener;

        public ViewHolder(View itemView, RecyclerViewItemClickListener itemClickListener) {
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
    public PopularMovie getItemAt(int position) {
        return mPopularMovies.get(position);
    }

    private float computeRating(Double movieRatings) {
        if (movieRatings != null) {
            String res = String.format("%.1f", (movieRatings/10.0) * 5.0);
            return Float.parseFloat(res);
        }

        return 0.0f;
    }
}
