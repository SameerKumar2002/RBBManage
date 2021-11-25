package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class ReportImportTable implements Serializable {
    private String date;
    private int no_of_people;
    private float bag_count;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNo_of_people() {
        return no_of_people;
    }

    public void setNo_of_people(int no_of_people) {
        this.no_of_people = no_of_people;
    }

    public float getBag_count() {
        return bag_count;
    }

    public void setBag_count(float bag_count) {
        this.bag_count = bag_count;
    }
}
