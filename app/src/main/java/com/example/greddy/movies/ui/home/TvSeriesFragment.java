package com.example.greddy.movies.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.greddy.movies.R;
import com.example.greddy.movies.base.AppBaseFragment;
import com.example.greddy.movies.databinding.ActivityMainContentBinding;

public class TvSeriesFragment extends AppBaseFragment<ActivityMainContentBinding, TvSeriesViewModel> {

    private TvSeriesAdapter tvSeriesAdapter;

    @Override
    protected ActivityMainContentBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return ActivityMainContentBinding.inflate(inflater, container, false);
    }

    @Override
    protected TvSeriesViewModel getViewModel() throws Exception {
        return new ViewModelProvider(this, viewModelProviderFactory).get(TvSeriesViewModel.class);
    }

    @Override
    protected void initUi(@Nullable Bundle savedInstanceState) {
        loadView();
        loadMovies();
        attachObservers();
    }

    private void attachObservers() {
        viewModel.getOnTheAirTvSeriesMutableLiveData().observe(this, tvSeriesResponse -> {
            if (tvSeriesResponse != null && tvSeriesResponse.getResults().size() > 0) {
                tvSeriesAdapter = new TvSeriesAdapter(this.getActivity(), tvSeriesResponse.getResults());
                binding.mostPopularMoviesCardView.posterList.setAdapter(tvSeriesAdapter);
                binding.mostPopularMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
        viewModel.getAiringTodayTvSeriesMutableLiveData().observe(this, tvSeriesResponse -> {
            if (tvSeriesResponse != null && tvSeriesResponse.getResults().size() > 0) {
                tvSeriesAdapter = new TvSeriesAdapter(this.getActivity(), tvSeriesResponse.getResults());
                binding.nowPlayingMoviesCardView.posterList.setAdapter(tvSeriesAdapter);
                binding.nowPlayingMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
        viewModel.getPopularTvSeriesMutableLiveData().observe(this, tvSeriesResponse -> {
            if (tvSeriesResponse != null && tvSeriesResponse.getResults().size() > 0) {
                tvSeriesAdapter = new TvSeriesAdapter(this.getActivity(), tvSeriesResponse.getResults());
                binding.topRatedMoviesCardView.posterList.setAdapter(tvSeriesAdapter);
                binding.topRatedMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
        viewModel.getTopRatedTvSeriesMutableLiveData().observe(this, tvSeriesResponse -> {
            if (tvSeriesResponse != null && tvSeriesResponse.getResults().size() > 0) {
                tvSeriesAdapter = new TvSeriesAdapter(this.getActivity(), tvSeriesResponse.getResults());
                binding.upcomingMoviesCardView.posterList.setAdapter(tvSeriesAdapter);
                binding.upcomingMoviesCardView.posterList.addItemDecoration(new LinearSpaceItemDecoration(this.getActivity(), 14));
            }
        });
    }

    private void loadView() {
        binding.nowPlayingMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.airing_today));
        binding.mostPopularMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.on_air));
        binding.topRatedMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.most_popular));
        binding.upcomingMoviesCardView.cardViewTitle.setText(getResources().getString(R.string.top_rated));
    }

    private void loadMovies() {
        viewModel.getOnTheAirTvSeries();
        viewModel.getAiringTodayTvSeries();
        viewModel.getTopRatedTvSeries();
        viewModel.getPopularTvSeries();
    }
}
