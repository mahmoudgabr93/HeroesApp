package com.example.gabrm.retrofitjsonexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.network.RequestFactory;

import java.util.List;

public class HeroViewModel extends ViewModel {
    private List<HeroModel> heroModelList;
    private RequestFactory retroClass=new RequestFactory();
    private LiveData<List<HeroModel>> liveData;
    private final String BUNDLE_TAG = "hero";

    /*
    deprecated
     */
    public List<HeroModel> getHeroes() {
        if(heroModelList ==null)
            heroModelList =retroClass.getHeroes();
        return heroModelList;
    }

    public LiveData<List<HeroModel>> getLiveData() {
        if(liveData == null)
            liveData= retroClass.getHeroesLiveData();
        return liveData;
    }

}
