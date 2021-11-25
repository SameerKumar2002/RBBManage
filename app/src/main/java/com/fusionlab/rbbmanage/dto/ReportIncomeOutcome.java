package com.fusionlab.rbbmanage.dto;

import java.io.Serializable;

public class ReportIncomeOutcome implements Serializable {
    private int income;
    private int outcome;
    private String date;

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getOutcome() {
        return outcome;
    }

    public void setOutcome(int outcome) {
        this.outcome = outcome;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
