package com.example.gabrm.retrofitjsonexample.view;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrm.retrofitjsonexample.R;

public class HeroActivity extends AppCompatActivity implements HeroesFragment.OnFragmentInteractionListener,BioFragment.OnFragmentInteractionListener {

    private final String RECYCLER_FRAGMENT_TAG="REC_FRAGMENT";
    private final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HeroesFragment heroesFragment = HeroesFragment.newInstance();
        Fragment fragment=getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment ==null) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frameLayout, heroesFragment,TAG).
                    addToBackStack(RECYCLER_FRAGMENT_TAG).commit();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}