package com.example.gabrm.retrofitjsonexample.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gabrm.retrofitjsonexample.R;
import com.example.gabrm.retrofitjsonexample.model.HeroModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
    private List<HeroModel> heroModelList;
    private Context context;

    public HeroAdapter(List<HeroModel> heroModelList, Context context) {
        this.heroModelList = heroModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_list,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HeroModel heroModel =  heroModelList.get(i);
        viewHolder.textname.setText(heroModel.getName());
        viewHolder.textrealname.setText(heroModel.getBio());
        Picasso.get().load(heroModel.getImageurl()).into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return heroModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView textname,textrealname;
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.nametext);
            textrealname = itemView.findViewById(R.id.realnametext);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
