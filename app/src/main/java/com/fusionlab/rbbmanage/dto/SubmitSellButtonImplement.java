package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class SubmitSellButtonImplement implements Serializable {

    private int id,unsold_bag_count,sold_bag_count,price;
    private float unsold_viss_count,sold_viss_count;
    private String type,date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getUnsold_bag_count() {
        return unsold_bag_count;
    }

    public void setUnsold_bag_count(int unsold_bag_count) {
        this.unsold_bag_count = unsold_bag_count;
    }

    public int getSold_bag_count() {
        return sold_bag_count;
    }

    public void setSold_bag_count(int sold_bag_count) {
        this.sold_bag_count = sold_bag_count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getUnsold_viss_count() {
        return unsold_viss_count;
    }

    public void setUnsold_viss_count(float unsold_viss_count) {
        this.unsold_viss_count = unsold_viss_count;
    }

    public float getSold_viss_count() {
        return sold_viss_count;
    }

    public void setSold_viss_count(float sold_viss_count) {
        this.sold_viss_count = sold_viss_count;
    }
}
