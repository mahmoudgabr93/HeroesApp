package com.example.gabrm.retrofitjsonexample.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabrm.retrofitjsonexample.R;
import com.example.gabrm.retrofitjsonexample.databinding.FragmentBioBinding;
import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.viewmodel.BioFragmentViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BioFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BioFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private BioFragmentViewModel bioFragmentViewModel;
    private static final String BUNDLE_TAG = "hero";
    private HeroModel heroModel;
    // TODO: Rename and change types of parameters


    public BioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param heroModel Parameter 1.
     *
     * @return A new instance of fragment BioFragment.
     */
    // TODO: Rename and change types and number of parameters


    public static BioFragment newInstance(HeroModel heroModel) {
        BioFragment fragment = new BioFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_TAG,heroModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bioFragmentViewModel=ViewModelProviders.of(this).get(BioFragmentViewModel.class);
        if (getArguments() != null) {
            bioFragmentViewModel.getBundle(heroModel,BUNDLE_TAG,this);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FragmentBioBinding bioBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_bio, container, false);
        View view = bioBinding.getRoot();
        bioBinding.setHeroModel(getHeroObject());
        setRetainInstance(true);
        return view;
    }

    private HeroModel getHeroObject() {
        Bundle bundle = getArguments();
        HeroModel hero= (HeroModel) bundle.getSerializable("hero");
        return hero;
    }

}
