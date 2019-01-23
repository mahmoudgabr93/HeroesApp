package com.example.gabrm.retrofitjsonexample.view;

import android.net.Uri;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.gabrm.retrofitjsonexample.R;

public class HeroActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HeroesFragment heroesFragment = HeroesFragment.newInstance();
        fragment=getSupportFragmentManager().findFragmentByTag(TAG);
        if(fragment ==null || !fragment.getClass().equals(HeroesFragment.class)) {
            getSupportFragmentManager().beginTransaction().
                    add(R.id.frameLayout, heroesFragment, TAG).commit();
        }
    }


   /* @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

    }

    */
}