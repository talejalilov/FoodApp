package com.example.foodapp.activity.domain;

import java.io.Serializable;

public class FoodDomain implements Serializable {

    private String title;
    private String description;
    private String pic;
    private Double fee;
    private int numberInCart;

    public FoodDomain(String title, String description, String pic, Double fee) {
        this.title = title;
        this.description = description;
        this.pic = pic;
        this.fee = fee;
    }

    public FoodDomain(String title, String description, String pic, Double fee, int numberInCart) {
        this.title = title;
        this.description = description;
        this.pic = pic;
        this.fee = fee;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}
