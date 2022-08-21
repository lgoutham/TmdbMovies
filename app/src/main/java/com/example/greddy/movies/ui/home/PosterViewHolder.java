package com.example.greddy.movies.ui.home;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greddy.movies.R;

public class PosterViewHolder extends RecyclerView.ViewHolder {

    AppCompatImageView poster;

    public PosterViewHolder(@NonNull View itemView) {
        super(itemView);
        poster = itemView.findViewById(R.id.movie_poster);
    }
}
