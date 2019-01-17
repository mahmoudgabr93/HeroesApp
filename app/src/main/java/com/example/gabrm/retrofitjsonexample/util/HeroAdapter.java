package com.example.gabrm.retrofitjsonexample.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrm.retrofitjsonexample.R;
import com.example.gabrm.retrofitjsonexample.databinding.FragmentBioBinding;
import com.example.gabrm.retrofitjsonexample.databinding.ItemHeroCardBinding;
import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.example.gabrm.retrofitjsonexample.view.BioFragment;
import com.squareup.picasso.Picasso;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
    private List<HeroModel> heroModelList;
    private OnItemClickListener mListener;
    private Context context;


    public HeroAdapter(List<HeroModel> heroModelList, Context context) {
        this.heroModelList = heroModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        //View v= LayoutInflater.from(viewGroup.getContext())
          //      .inflate(R.layout.item_hero_card,viewGroup,false);
        Context context = viewGroup.getContext();
        ItemHeroCardBinding itemHeroCardBinding= DataBindingUtil.inflate(inflater,R.layout.item_hero_card,
                viewGroup,false);
        return new ViewHolder(itemHeroCardBinding,mListener,context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HeroModel heroModel =  heroModelList.get(i);
        viewHolder.binding.setHeroModel(heroModel);
        Bundle bundle=new Bundle();
        bundle.putInt("i",i);
        Picasso.get().load(heroModel.getImageurl()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return heroModelList.size();
    }

    public interface OnItemClickListener{
        void onItemClicked(HeroModel heroModel);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener=listener;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder
    {
        private ItemHeroCardBinding binding;
        private ImageView imageView;
        private int position;
        private Context context;
        public ViewHolder(final ItemHeroCardBinding binding, final OnItemClickListener listener, final Context context1) {
            super(binding.getRoot());
            this.binding=binding;
            this.context=context1;
            binding.herocard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener!=null)
                    {
                        position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION) {
                            SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(context);
                            SharedPreferences.Editor editor=sharedPreferences.edit();
                            editor.putInt("position",position);
                            editor.commit();
                            listener.onItemClicked(heroModelList.get(position));
                        }
                    }
                }
            });
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
