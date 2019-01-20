package com.example.gabrm.retrofitjsonexample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.network.RequestFactory;

import java.util.List;

public class HeroesViewModel extends ViewModel {

    private RequestFactory retroClass=new RequestFactory();
    private LiveData<List<HeroModel>> liveData;


    public LiveData<List<HeroModel>> getLiveData() {
        if(liveData == null)
            liveData= retroClass.getHeroesLiveData();
        return liveData;
    }

}
