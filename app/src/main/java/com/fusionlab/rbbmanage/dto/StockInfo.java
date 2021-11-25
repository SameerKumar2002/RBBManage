package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class StockInfo implements Serializable {

    private String date,type,size,name,phone,address;
    private int id,serial_number,T_date,bag_count;
    private float viss_count;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(int serial_number) {
        this.serial_number = serial_number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public float getViss_count() {
        return viss_count;
    }

    public void setViss_count(float viss_count) {
        this.viss_count = viss_count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getT_date() {
        return T_date;
    }

    public void setT_date(int t_date) {
        T_date = t_date;
    }

    public int getBag_count() {
        return bag_count;
    }

    public void setBag_count(int bag_count) {
        this.bag_count = bag_count;
    }


}
