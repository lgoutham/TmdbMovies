package com.example.greddy.movies.base;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewbinding.ViewBinding;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class AppBaseActivity<VB extends ViewBinding, VM extends BaseViewModel> extends DaggerAppCompatActivity {

    protected VB binding;
    protected VM viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = getViewBinding(getLayoutInflater());
        try {
            this.viewModel = getViewModel();
        } catch (Exception e) {

        }
        setContentView(binding.getRoot());
        initUi(savedInstanceState);
    }

    protected void replaceFragment(FragmentManager fragmentManager, int container, Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(container, fragment).addToBackStack(fragment.getClass().getSimpleName()).commit();
    }

    protected void showProgressBar() {

    }

    protected void showProgressBar(String message) {

    }

    protected void hideProgressBar() {

    }

    protected abstract VB getViewBinding(LayoutInflater layoutInflater);

    protected abstract VM getViewModel() throws Exception;

    protected abstract void initUi(@Nullable Bundle savedInstanceState);
}
