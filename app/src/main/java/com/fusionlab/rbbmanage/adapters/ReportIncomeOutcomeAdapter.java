package com.fusionlab.rbbmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.dto.ReportIncomeOutcome;

import java.util.ArrayList;
import java.util.Arrays;

public class ReportIncomeOutcomeAdapter extends RecyclerView.Adapter<ReportIncomeOutcomeAdapter.ViewHolder> {
   Context context;
   ArrayList<ReportIncomeOutcome> arrayList;

    public ReportIncomeOutcomeAdapter(Context context,ArrayList<ReportIncomeOutcome> arrayList) {

        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_report_income_outcome,viewGroup,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        ReportIncomeOutcome incomeOutcome = arrayList.get(i);
        holder.date.setText(incomeOutcome.getDate());
        holder.income.setText(String.valueOf(incomeOutcome.getIncome()));
        holder.outcome.setText(String.valueOf(incomeOutcome.getOutcome()));
        holder.difference.setText(String.valueOf(incomeOutcome.getIncome()-incomeOutcome.getOutcome()));

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView date,income,outcome,difference;

        public ViewHolder(@NonNull View view) {
            super(view);

            date = view.findViewById(R.id.id_date);
            income = view.findViewById(R.id.id_income);
            outcome = view.findViewById(R.id.id_outcome);
            difference = view.findViewById(R.id.id_difference);

        }
    }
}
