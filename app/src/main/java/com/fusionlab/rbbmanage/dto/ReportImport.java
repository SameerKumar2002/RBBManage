package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class ReportImport implements Serializable {

    private int id;
    private String Date;
    private int total;

    public String getDate() {
        return Date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
