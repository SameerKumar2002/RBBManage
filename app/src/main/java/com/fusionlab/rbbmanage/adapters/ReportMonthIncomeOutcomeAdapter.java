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

public class ReportMonthIncomeOutcomeAdapter extends RecyclerView.Adapter<ReportMonthIncomeOutcomeAdapter.ViewHolder> {

    Context context;
    ReportIncomeOutcome[] List;

    public ReportMonthIncomeOutcomeAdapter(Context context, ReportIncomeOutcome[] arrayList) {
        this.context = context;
        this.List = arrayList;
    }

    @NonNull
    @Override
    public ReportMonthIncomeOutcomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_report_income_outcome,viewGroup,false);

        return new ReportMonthIncomeOutcomeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportMonthIncomeOutcomeAdapter.ViewHolder holder, int i) {

        final String[] label ={"Jan","Fab","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
        ReportIncomeOutcome incomeOutcome = List[i];
        if(incomeOutcome==null){
            holder.date.setText(label[i]);
            holder.income.setText("0");
            holder.outcome.setText("0");
            holder.difference.setText("0");

        }else{
            holder.date.setText(label[Integer.parseInt(incomeOutcome.getDate())-1]);
            holder.income.setText(String.valueOf(incomeOutcome.getIncome()));
            holder.outcome.setText(String.valueOf(incomeOutcome.getOutcome()));
            holder.difference.setText(String.valueOf(incomeOutcome.getIncome()-incomeOutcome.getOutcome()));

        }


    }

    @Override
    public int getItemCount() {
        return List.length;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

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
