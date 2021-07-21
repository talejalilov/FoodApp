package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodapp.Helper.ManagementCard;
import com.example.foodapp.R;
import com.example.foodapp.activity.domain.FoodDomain;

public class ShowDetailActivity extends AppCompatActivity {

    private TextView addToCard,titleTxt,feeTxt,description,numberOrderTxt;
    private ImageView plusBtn,minusBtn,picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagementCard managementCard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);

        managementCard = new ManagementCard(this);
        initView();
        getBundle();

    }

    private void getBundle() {
        //Bu kod tıklanan şəkilin id-sini intenlə bura göndərir
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        //bu kod göndərilmiş id-yə görə şəkili "drawable" dan tapır
        int drawableResourceId = this.getResources().getIdentifier(object.getPic(),"drawable",this.getPackageName());
        //və ekranda göstərir

        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText(String.valueOf(object.getFee()));
        description.setText(object.getDescription());
        numberOrderTxt.setText(String.valueOf(numberOrder));


        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberOrder = numberOrder+1;
                numberOrderTxt.setText(""+numberOrder);
            }
        });

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(numberOrder>1){
                    numberOrder = numberOrder-1;
                }
                numberOrderTxt.setText(""+numberOrder);
            }
        });

        addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCard.insertFood(object);
                startActivity(new Intent(ShowDetailActivity.this,CardListActivity.class));
            }
        });



    }

    private void initView() {

        addToCard = findViewById(R.id.addToCard);
        titleTxt = findViewById(R.id.titleTxt);
        feeTxt = findViewById(R.id.priceTxt);
        description = findViewById(R.id.description);
        numberOrderTxt = findViewById(R.id.numberOrderTxt);
        plusBtn = findViewById(R.id.plusBtn);
        minusBtn = findViewById(R.id.minusBtn);
        picFood = findViewById(R.id.foodPic);


    }
}