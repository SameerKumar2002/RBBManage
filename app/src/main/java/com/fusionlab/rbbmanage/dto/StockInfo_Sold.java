package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class StockInfo_Sold implements Serializable {

    private int id,bag_count,price,total_price;
    private float viss_count;
    String size,date,type;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBag_count() {
        return bag_count;
    }

    public void setBag_count(int bag_count) {
        this.bag_count = bag_count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public float getViss_count() {
        return viss_count;
    }

    public void setViss_count(float viss_count) {
        this.viss_count = viss_count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
