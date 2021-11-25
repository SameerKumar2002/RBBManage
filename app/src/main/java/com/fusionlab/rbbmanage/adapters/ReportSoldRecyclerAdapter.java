package com.fusionlab.rbbmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.dto.ReportSold;

import java.util.ArrayList;

public class ReportSoldRecyclerAdapter extends RecyclerView.Adapter<ReportSoldRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<ReportSold> entries;

    public ReportSoldRecyclerAdapter(Context context,ArrayList<ReportSold> entries){
        this.context = context;
        this.entries = entries;
    }

    @NonNull
    @Override
    public ReportSoldRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_report_sold,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

              viewHolder.date.setText(entries.get(i).getDate());
              viewHolder.bag.setText(String.valueOf(entries.get(i).getBag_total()));
              viewHolder.price.setText(String.valueOf(entries.get(i).getPrice()));
              viewHolder.total_price.setText(String.valueOf(entries.get(i).getTotal_price()));


    }

    @Override
    public int getItemCount() {
        return entries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date,bag,price,total_price;
        public ViewHolder(@NonNull View View) {
            super(View);
            date =View.findViewById(R.id.id_date);
            bag =View.findViewById(R.id.id_bag_count);
            price =View.findViewById(R.id.id_price);
            total_price =View.findViewById(R.id.id_total_price);
        }
    }
}
