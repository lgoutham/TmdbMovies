package com.example.greddy.movies.filter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.greddy.movies.R;
import com.example.greddy.movies.model.Genre;

import java.util.List;

/**
 * Created by greddy on 7/14/2017.
 */

public class GenreAdapter extends BaseAdapter {

    private LayoutInflater mLayoutInflater;
    private List<Genre> mGenreList;

    public GenreAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    public void UpdateGenreList(List<Genre> mGenreList) {
        this.mGenreList = mGenreList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mGenreList != null ? mGenreList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return mGenreList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        GenreHolder genreHolder = null;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.genre_item, null);
            genreHolder = new GenreHolder();
            genreHolder.mGenreImage = (ImageView) convertView.findViewById(R.id.genre_item_image);
            genreHolder.mGenre = (TextView) convertView.findViewById(R.id.genre_item_text);
            convertView.setTag(genreHolder);
        } else {
            genreHolder = (GenreHolder) convertView.getTag();
        }
//        genreHolder.mGenreImage.setImageResource(mGenreImages[position]);
        genreHolder.mGenre.setText(mGenreList.get(position).getName());

        return convertView;
    }

    public class GenreHolder {
        ImageView mGenreImage;
        TextView mGenre;
    }
}
