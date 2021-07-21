package com.example.foodapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.foodapp.activity.domain.FoodDomain;

import java.util.ArrayList;

public class ManagementCard {

    private Context context;
    private TinyDB tinyDB;

    public ManagementCard(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){

        ArrayList<FoodDomain> listFood = getListCard();
        boolean existAlready =false;
        int n = 0;
        for (int i = 0; i <listFood.size() ; i++) {
            if(listFood.get(i).getTitle().equals(item.getTitle())){
                existAlready= true;
                n=i;
                break;
            }
        }
        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else{
            listFood.add(item);
        }

        tinyDB.putListObject("CardList",listFood);
        Toast.makeText(context,"Added To Your Card",Toast.LENGTH_SHORT).show();

    }

    public ArrayList<FoodDomain> getListCard(){

        return tinyDB.getListObject("CardList");
    }

    public void plusNumberFood(ArrayList<FoodDomain> listFood,int position,ChangeNumberItemsListener changeNumberItemsListener ){

        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
        tinyDB.putObject("CardList",listFood);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain> listFood,int position,ChangeNumberItemsListener changeNumberItemsListener ) {

        if(listFood.get(position).getNumberInCart()==1){
            listFood.remove(position);
        }else{
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);

        }
        tinyDB.putObject("CardList",listFood);
        changeNumberItemsListener.changed();
    }

    public Double getTotalFee(){
        ArrayList<FoodDomain> listFood2 = getListCard();
        double fee= 0;
        for (int i = 0; i <listFood2.size() ; i++) {
            fee = fee + (listFood2.get(i).getFee() * listFood2.get(i).getNumberInCart());
        }
            return fee;
    }
    }
