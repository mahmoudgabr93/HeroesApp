package com.example.gabrm.retrofitjsonexample.view;

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
import com.example.gabrm.retrofitjsonexample.util.HeroAdapter;
import com.example.gabrm.retrofitjsonexample.viewmodel.HeroViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link recyclerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link recyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class recyclerFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerView;
    private HeroViewModel heroViewModel;
    private List<HeroModel> heroModelList = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public recyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment recyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static recyclerFragment newInstance(String param1, String param2) {
        recyclerFragment fragment = new recyclerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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
        heroViewModel= ViewModelProviders.of(this).get(HeroViewModel.class);
        heroViewModel.getLiveData().observe(this, new Observer<List<HeroModel>>() {
            @Override
            public void onChanged(@Nullable List<HeroModel> heroModels) {
                heroModelList.addAll(heroModels);
                HeroAdapter adapter = new HeroAdapter(heroModelList,getActivity());
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new HeroAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClicked(HeroModel heroModel) {
                        BioFragment bioFragment=new BioFragment();
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("hero",heroModel);
                        bioFragment.setArguments(bundle);
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
