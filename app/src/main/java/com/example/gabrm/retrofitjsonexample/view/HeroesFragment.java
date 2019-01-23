package com.example.gabrm.retrofitjsonexample.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabrm.retrofitjsonexample.R;
import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.adapter.HeroAdapter;
import com.example.gabrm.retrofitjsonexample.viewmodel.HeroesViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HeroesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HeroesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeroesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private RecyclerView recyclerView;
    private HeroesViewModel heroesViewModel;
    private List<HeroModel> heroModelList = new ArrayList<>();
    private int lastFirstVisiblePosition;
    // TODO: Rename and change types of parameters


    public HeroesFragment() {
        // Required empty private constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment HeroesFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static HeroesFragment newInstance() {
        HeroesFragment fragment = new HeroesFragment();
        return fragment;
    }

    /**
     * This is a method for Fragment.
     * You can do the same in onCreate or onRestoreInstanceState
     */


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        lastFirstVisiblePosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
        outState.putInt("position",lastFirstVisiblePosition);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_recycler, container, false);
            if(savedInstanceState!=null)
                lastFirstVisiblePosition = savedInstanceState.getInt("position");

        initRecyclerView(view);
            subscribeLiveData();
            setRetainInstance(true);
        return view;
    }


    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void subscribeLiveData() {
        heroesViewModel= ViewModelProviders.of(this).get(HeroesViewModel.class);
        heroesViewModel.getLiveData().observe(this, new Observer<List<HeroModel>>() {
            @Override
            public void onChanged(@Nullable List<HeroModel> heroModels) {
                heroModelList.addAll(heroModels);
                HeroAdapter adapter = new HeroAdapter(heroModelList,getActivity());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new HeroAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(HeroModel heroModel) {
                        BioFragment bioFragment=BioFragment.newInstance(heroModel);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .add(R.id.frameLayout,bioFragment).addToBackStack("BioTag").commit();
                    }
                });
                if(lastFirstVisiblePosition!=0)
                {
                    recyclerView.getLayoutManager().scrollToPosition(lastFirstVisiblePosition);
                }
            }
        });

        heroModelList.clear();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
