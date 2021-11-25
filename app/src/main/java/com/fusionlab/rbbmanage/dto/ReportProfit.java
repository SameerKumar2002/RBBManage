package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class ReportProfit implements Serializable {

    private float db_total_price;
    private float user_total_price;
    private float total_loss;
    private float user_avg_rate;
    private float remain_total_bag;
    private float remain_total_viss;
    private float remain_average_rate;
    private float difference_bag_count;
    private float difference_viss_count;
     private int  last_id,last_price;

    public int getLast_price() {
        return last_price;
    }

    public void setLast_price(int last_price) {
        this.last_price = last_price;
    }

    private String profit_type;

    public int getLast_id() {
        return last_id;
    }

    public void setLast_id(int last_id) {
        this.last_id = last_id;
    }

    public float getDb_total_price() {
        return db_total_price;
    }

    public void setDb_total_price(float db_total_price) {
        this.db_total_price = db_total_price;
    }

    public float getUser_total_price() {
        return user_total_price;
    }

    public void setUser_total_price(float user_total_price) {
        this.user_total_price = user_total_price;
    }

    public float getTotal_loss() {
        return total_loss;
    }

    public void setTotal_loss(float total_loss) {
        this.total_loss = total_loss;
    }

    public float getUser_avg_rate() {
        return user_avg_rate;
    }

    public void setUser_avg_rate(float user_avg_rate) {
        this.user_avg_rate = user_avg_rate;
    }

    public float getRemain_total_bag() {
        return remain_total_bag;
    }

    public void setRemain_total_bag(float remain_total_bag) {
        this.remain_total_bag = remain_total_bag;
    }

    public float getRemain_total_viss() {
        return remain_total_viss;
    }

    public void setRemain_total_viss(float remain_total_viss) {
        this.remain_total_viss = remain_total_viss;
    }

    public float getRemain_average_rate() {
        return remain_average_rate;
    }

    public void setRemain_average_rate(float remain_average_rate) {
        this.remain_average_rate = remain_average_rate;
    }

    public float getDifference_bag_count() {
        return difference_bag_count;
    }

    public void setDifference_bag_count(float difference_bag_count) {
        this.difference_bag_count = difference_bag_count;
    }

    public float getDifference_viss_count() {
        return difference_viss_count;
    }

    public void setDifference_viss_count(float difference_viss_count) {
        this.difference_viss_count = difference_viss_count;
    }

    public String getProfit_type() {
        return profit_type;
    }

    public void setProfit_type(String profit_type) {
        this.profit_type = profit_type;
    }
}
