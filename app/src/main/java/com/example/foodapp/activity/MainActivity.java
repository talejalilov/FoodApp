package com.example.foodapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.foodapp.R;
import com.example.foodapp.activity.adapter.CategoryAdapter;
import com.example.foodapp.activity.adapter.PopularAdapter;
import com.example.foodapp.activity.domain.CategoryDomain;
import com.example.foodapp.activity.domain.FoodDomain;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private  RecyclerView.Adapter adapter,adapter2;
    private RecyclerView recyclerViewCategoryList,recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategory();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
    FloatingActionButton floatingActionButton = findViewById(R.id.cart_btn123);
        LinearLayout homeBtn = findViewById(R.id.home_btn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CardListActivity.class));

            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CardListActivity.class));

            }
        });

    }

    private void recyclerViewPopular() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(layoutManager);

        ArrayList<FoodDomain> foodDomainArrayList = new ArrayList<>();
        foodDomainArrayList.add(new FoodDomain("Pepperoni Pizza","Pepperoni is characteristically soft, slightly smoky, and bright red in color","pizza123", 9.5));
        foodDomainArrayList.add(new FoodDomain("Cheese Burger","As with other hamburgers, a cheeseburger may include toppings such as lettuce, tomato, onion, pickles, bacon, mayonnaise, ketchup, and mustard.","burger", 3.5));
        foodDomainArrayList.add(new FoodDomain("Vegetable Pizza","As characteristically soft, slightly smoky, and bright red","pizza_cat", 11.5));

        adapter2 = new PopularAdapter(foodDomainArrayList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategory() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView55);
        recyclerViewCategoryList.setLayoutManager(layoutManager);

        ArrayList<CategoryDomain> categoryList  = new ArrayList<>();
        categoryList.add(new CategoryDomain("pizza","cat_1"));
        categoryList.add(new CategoryDomain("Burger","cat_2"));
        categoryList.add(new CategoryDomain("Hotdog","cat_3"));
        categoryList.add(new CategoryDomain("Drink","cat_4"));
        categoryList.add(new CategoryDomain("Donut","cat_6"));

        adapter = new CategoryAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);

    }
}