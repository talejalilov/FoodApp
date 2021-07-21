package com.example.foodapp.activity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Helper.ChangeNumberItemsListener;
import com.example.foodapp.Helper.ManagementCard;
import com.example.foodapp.R;
import com.example.foodapp.activity.ShowDetailActivity;
import com.example.foodapp.activity.domain.FoodDomain;

import java.util.ArrayList;

public class CardListAdapter extends RecyclerView.Adapter<CardListAdapter.ViewHolder> {

    private ArrayList<FoodDomain> foodDomains;
    private ManagementCard managementCard;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public CardListAdapter(ArrayList<FoodDomain> foodDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {

        this.foodDomains = foodDomains;
        managementCard = new ManagementCard(context);
        this.changeNumberItemsListener = changeNumberItemsListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_card_list,parent,false);

        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.title2Txt.setText(foodDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(foodDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((foodDomains.get(position).getNumberInCart()*foodDomains.get(position).getFee()) * 100.0) / 100.0));
        holder.numberItemTxt.setText(foodDomains.get(position).getNumberInCart());



        int drawableResource = holder.itemView.getContext().getResources().getIdentifier(foodDomains.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());

        //Bu kod drawable file-dakı adı yazaraq o adla eyni olan şəkili götürüb ekran çap edir
         Glide.with(holder.itemView.getContext())
                .load(drawableResource)
                .into(holder.picCard);

         holder.plusItem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 managementCard.plusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                     @Override
                     public void changed() {
                         notifyDataSetChanged();
                         changeNumberItemsListener.changed();

                     }
                 });
             }
         });
         holder.minusItem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 managementCard.minusNumberFood(foodDomains, position, new ChangeNumberItemsListener() {
                     @Override
                     public void changed() {
                         notifyDataSetChanged();
                         changeNumberItemsListener.changed();
                     }
                 });
             }
         });

    }

    @Override
    public int getItemCount() {
        return foodDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title2Txt, feeEachItem,totalEachItem,numberItemTxt;
        ImageView picCard,plusItem,minusItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title2Txt =itemView.findViewById(R.id.title2Txt);
            feeEachItem =itemView.findViewById(R.id.feeEachItem);
            totalEachItem =itemView.findViewById(R.id.totalFeeTxt);
            numberItemTxt =itemView.findViewById(R.id.numberOrderTxt);
            picCard =itemView.findViewById(R.id.picCard);
            plusItem = itemView.findViewById(R.id.plusItem);
            minusItem = itemView.findViewById(R.id.minusItem);


        }
    }
}