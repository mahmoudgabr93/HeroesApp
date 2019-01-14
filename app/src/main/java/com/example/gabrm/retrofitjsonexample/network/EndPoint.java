package com.example.gabrm.retrofitjsonexample.network;

import com.example.gabrm.retrofitjsonexample.model.HeroModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPoint {

    String BASE_URL="https://simplifiedcoding.net/demos/";

    @GET("marvel")
    Call<List<HeroModel>> getHeroes();
}
