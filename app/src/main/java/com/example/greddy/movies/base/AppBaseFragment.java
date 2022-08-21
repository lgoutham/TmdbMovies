package com.example.greddy.movies.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;

import com.example.greddy.movies.di.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public abstract class AppBaseFragment<VB extends ViewBinding, VM extends BaseViewModel> extends DaggerFragment {

    @Inject
    public ViewModelProviderFactory viewModelProviderFactory;

    protected VB binding;
    protected VM viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = getViewBinding(inflater, container);
        try {
            this.viewModel = getViewModel();
        } catch (Exception e) {

        }
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(savedInstanceState);
    }

    protected void showProgressBar() {

    }

    protected void showProgressBar(String message) {

    }

    protected void hideProgressBar() {

    }

    protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract VM getViewModel() throws Exception;

    protected abstract void initUi(@Nullable Bundle savedInstanceState);
}
