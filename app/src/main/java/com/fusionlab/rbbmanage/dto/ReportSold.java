package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class ReportSold implements Serializable {

   private int id,bag_total,price,total_price;
   private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBag_total() {
        return bag_total;
    }

    public void setBag_total(int bag_total) {
        this.bag_total = bag_total;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
