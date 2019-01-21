package com.example.gabrm.retrofitjsonexample.view;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    // TODO: Rename and change types of parameters


    private OnFragmentInteractionListener mListener;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view = inflater.inflate(R.layout.fragment_recycler, container, false);
            initRecyclerView(view);
            subscribeLiveData();
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
                                .replace(R.id.frameLayout,bioFragment).addToBackStack(null).commit();
                    }
                });
            }
        });
        heroModelList.clear();
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
