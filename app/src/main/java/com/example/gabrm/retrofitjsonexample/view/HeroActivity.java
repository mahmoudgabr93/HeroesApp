package com.example.gabrm.retrofitjsonexample.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.gabrm.retrofitjsonexample.R;

import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.util.HeroAdapter;
import com.example.gabrm.retrofitjsonexample.viewmodel.HeroViewModel;

import java.util.ArrayList;
import java.util.List;

public class HeroActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HeroViewModel heroViewModel;
    //LiveData<List<HeroModel>> listLiveData;
    List<HeroModel> heroModelList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        subscribeLiveData();
    }

    private void subscribeLiveData() {
        heroViewModel= ViewModelProviders.of(this).get(HeroViewModel.class);
        heroViewModel.getLiveData().observe(this, new Observer<List<HeroModel>>() {
            @Override
            public void onChanged(@Nullable List<HeroModel> heroModels) {
                heroModelList.addAll(heroModels);
                HeroAdapter adapter = new HeroAdapter(heroModelList,HeroActivity.this);
                recyclerView.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        }

    private void initView() {
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}