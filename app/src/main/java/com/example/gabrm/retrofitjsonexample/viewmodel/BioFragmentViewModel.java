package com.example.gabrm.retrofitjsonexample.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.view.BioFragment;


public class BioFragmentViewModel extends ViewModel {


    public HeroModel getBundle(HeroModel heroModel, String BUNDLE_TAG, Fragment fragment)
    {
        heroModel= (HeroModel) fragment.getArguments().getSerializable(BUNDLE_TAG);
        return heroModel;
    }
}
