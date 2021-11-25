package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class User_StockInfo_Sold implements Serializable {

    private int id;
    private int buy_avg;
    private int user_sold_price;
    private int user_sold_total_price;
    private int profit;
    private float bag_count;
    private String date,t_date;

    public String getT_date() {
        return t_date;
    }

    public void setT_date(String t_date) {
        this.t_date = t_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuy_avg() {
        return buy_avg;
    }

    public void setBuy_avg(int buy_avg) {
        this.buy_avg = buy_avg;
    }

    public int getUser_sold_price() {
        return user_sold_price;
    }

    public void setUser_sold_price(int user_sold_price) {
        this.user_sold_price = user_sold_price;
    }

    public int getUser_sold_total_price() {
        return user_sold_total_price;
    }

    public void setUser_sold_total_price(int user_sold_total_price) {
        this.user_sold_total_price = user_sold_total_price;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public float getBag_count() {
        return bag_count;
    }

    public void setBag_count(float bag_count) {
        this.bag_count = bag_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
