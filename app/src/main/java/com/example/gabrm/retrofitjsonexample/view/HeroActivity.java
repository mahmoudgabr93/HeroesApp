package com.example.gabrm.retrofitjsonexample.view;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gabrm.retrofitjsonexample.R;

import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.util.HeroAdapter;
import com.example.gabrm.retrofitjsonexample.viewmodel.HeroViewModel;

import java.util.ArrayList;
import java.util.List;

public class HeroActivity extends AppCompatActivity implements recyclerFragment.OnFragmentInteractionListener,BioFragment.OnFragmentInteractionListener {

    private final String RECYCLER_FRAGMENT_TAG="REC_FRAGMENT";
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerFragment recyclerFragment=new recyclerFragment();
        Fragment fragment=getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment ==null) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frameLayout, recyclerFragment,TAG).
                    addToBackStack(RECYCLER_FRAGMENT_TAG).commit();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}