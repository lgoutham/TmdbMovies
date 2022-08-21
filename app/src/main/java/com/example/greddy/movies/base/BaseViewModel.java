package com.example.greddy.movies.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    protected CompositeDisposable disposables = new CompositeDisposable();

    @Override
    protected void onCleared() {
        this.disposables.dispose();
        super.onCleared();
    }
}
