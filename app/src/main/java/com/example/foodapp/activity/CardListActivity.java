package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.foodapp.Helper.ChangeNumberItemsListener;
import com.example.foodapp.Helper.ManagementCard;
import com.example.foodapp.R;
import com.example.foodapp.activity.adapter.CardListAdapter;

public class CardListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCard managementCard;
    private TextView totalFeeTxt,taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        managementCard = new ManagementCard(this);

        initView();
        initList();
        calculateCard();
    }

    private void initList() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CardListAdapter(managementCard.getListCard(), this, new ChangeNumberItemsListener() {
            @Override
            public void changed() {

            calculateCard();
            }
        });

        recyclerView.setAdapter(adapter);
        if(managementCard.getListCard().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }

    }

    public void calculateCard(){
        double percentTax = 0.02;
        double delivery= 10;

        tax = Math.round((managementCard.getTotalFee()*percentTax)*100.0)/100.0;
        double total = Math.round((managementCard.getTotalFee()+tax + delivery)*100.0)/100.0;
        double itemTotal  = Math.round(managementCard.getTotalFee() + 100.0)/100.0;

        totalFeeTxt.setText("$"+itemTotal);
        taxTxt.setText("$" + tax);
        deliveryTxt.setText("$" + delivery);
        totalTxt.setText("$" + total);

    }


    private void initView() {

        recyclerView = findViewById(R.id.recyclerView55);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);

    }
}