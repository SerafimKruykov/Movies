package com.example.movies.data;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movies.R;
import com.example.movies.activities.DetailsActivity;
import com.example.movies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private final Context context;
    private final ArrayList<Movie> movies;

    public MovieAdapter(Context context, ArrayList<Movie> movies){
        this.context = context;
        this.movies = movies;
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView posterImageView;
        TextView titleTextView;
        TextView yearTextView;
        TextView idTextView;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            posterImageView = itemView.findViewById(R.id.posterImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            yearTextView = itemView.findViewById(R.id.yearTextView);
            idTextView = itemView.findViewById(R.id.idTextView);
        }

        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            Movie currentMovie = movies.get(position);

            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("id", currentMovie.getImdb_id());

            context.startActivity(intent);

        }
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);

        String title = currentMovie.getTitle();
        String posterUrl = currentMovie.getPosterUrl();
        String year = currentMovie.getYear();
        String imdb_id = currentMovie.getImdb_id();

        holder.titleTextView.setText(title);
        holder.yearTextView.setText(year);
        holder.idTextView.setText(imdb_id);
        Picasso.get().load(posterUrl).fit().centerInside().into(holder.posterImageView);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }


}
