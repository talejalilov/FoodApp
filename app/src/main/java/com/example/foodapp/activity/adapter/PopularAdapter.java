package com.example.foodapp.activity.adapter;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.R;
import com.example.foodapp.activity.ShowDetailActivity;
import com.example.foodapp.activity.domain.CategoryDomain;
import com.example.foodapp.activity.domain.FoodDomain;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<FoodDomain> foodDomains;

    public PopularAdapter(ArrayList<FoodDomain> foodDomains) {
        this.foodDomains = foodDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular,parent,false);

        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title.setText(foodDomains.get(position).getTitle());
        holder.fee.setText(String.valueOf(foodDomains.get(position).getFee()));


        int drawableResource = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        //Bu kod drawable file-dakı adı yazaraq o adla eyni olan şəkili götürüb ekran çap edir
         Glide.with(holder.itemView.getContext())
                .load(drawableResource)
                .into(holder.pic);

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailActivity.class);
                intent.putExtra("object",foodDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,fee,addBtn;
        ImageView pic;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title =itemView.findViewById(R.id.title);
            fee =itemView.findViewById(R.id.fee);
            addBtn =itemView.findViewById(R.id.addBtn);
            pic =itemView.findViewById(R.id.pic);


        }
    }
}