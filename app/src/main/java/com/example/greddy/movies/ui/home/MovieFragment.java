package com.example.greddy.movies.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.greddy.movies.R;
import com.example.greddy.movies.base.AppBaseFragment;
import com.example.greddy.movies.databinding.ActivityMainContentBinding;

public class MovieFragment extends AppBaseFragment<ActivityMainContentBinding, MovieViewModel> {

    private MovieAdapter mMovieAdapter;

    @Override
    protected ActivityMainContentBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return ActivityMainContentBinding.inflate(inflater, container, false);
    }

    @Override
    protected MovieViewModel getViewModel() throws Exception {
        return new ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel.class);
    }

    @Override
    protected void initUi(@Nullable Bundle savedInstanceState) {
        loadView();
        loadMovies();
        attachObservers();
    }

    private void attachObservers() {
        viewModel.getPopularMoviesMutableLiveData().observe(this, movieResponse -> {
            if (movieResponse != null && movieResponse.getResults().size() > 0) {
                mMovieAdapter = new MovieAdapter(this.getActivity(), movieResponse.getResults());
                binding.mostPopularMoviesCardView.posterList.setAdapter(mMovieAdapter);
                binding.mostPopularMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
        viewModel.getNowPlayingMoviesMutableLiveData().observe(this, movieResponse -> {
            if (movieResponse != null && movieResponse.getResults().size() > 0) {
                mMovieAdapter = new MovieAdapter(this.getActivity(), movieResponse.getResults());
                binding.nowPlayingMoviesCardView.posterList.setAdapter(mMovieAdapter);
                binding.nowPlayingMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
        viewModel.getTopRatedMoviesMutableLiveData().observe(this, movieResponse -> {
            if (movieResponse != null && movieResponse.getResults().size() > 0) {
                mMovieAdapter = new MovieAdapter(this.getActivity(), movieResponse.getResults());
                binding.topRatedMoviesCardView.posterList.setAdapter(mMovieAdapter);
                binding.topRatedMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
        viewModel.getUpcomingMoviesMutableLiveData().observe(this, movieResponse -> {
            if (movieResponse != null && movieResponse.getResults().size() > 0) {
                mMovieAdapter = new MovieAdapter(this.getActivity(), movieResponse.getResults());
                binding.upcomingMoviesCardView.posterList.setAdapter(mMovieAdapter);
                binding.upcomingMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
    }

    private void loadView() {
        binding.nowPlayingMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.now_playing));
        binding.mostPopularMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.most_popular));
        binding.topRatedMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.top_rated));
        binding.upcomingMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.upcoming));
    }

    private void loadMovies() {
        viewModel.getPopularMovies();
        viewModel.getNowPlayingMovies();
        viewModel.getTopRatedMovies();
        viewModel.getUpComingMovies();
    }
}
