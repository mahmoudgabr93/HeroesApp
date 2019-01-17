package com.example.gabrm.retrofitjsonexample.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.gabrm.retrofitjsonexample.model.HeroModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RequestFactory {

    private static Retrofit getRetrofitInstance()
    {
        return new Retrofit.Builder().baseUrl(EndPoint.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static EndPoint getApi()
    {
        return getRetrofitInstance().create(EndPoint.class);
    }

    /**
     * deprecated
     * @return
     */
    public List<HeroModel> getHeroes()
    {
        final List<HeroModel> heroModels = new ArrayList<>();
        EndPoint endPoint = RequestFactory.getApi();
        Call<List<HeroModel>> call= endPoint.getHeroes();
        call.enqueue(new Callback<List<HeroModel>>() {
            @Override
            public void onResponse(Call<List<HeroModel>> call, Response<List<HeroModel>> response) {
                heroModels.addAll(response.body());
            }

            @Override
            public void onFailure(Call<List<HeroModel>> call, Throwable t) {

            }
        });
            return heroModels;
    }
    public LiveData<List<HeroModel>> getHeroesLiveData()
    {
        final MutableLiveData<List<HeroModel>> listMutableLiveData = new MutableLiveData<>();
        EndPoint endPoint = RequestFactory.getApi();
        Call<List<HeroModel>> call= endPoint.getHeroes();
        call.enqueue(new Callback<List<HeroModel>>() {
            @Override
            public void onResponse(Call<List<HeroModel>> call, Response<List<HeroModel>> response) {
                    listMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<HeroModel>> call, Throwable t) {

            }
        });
        return listMutableLiveData;
    }
}
