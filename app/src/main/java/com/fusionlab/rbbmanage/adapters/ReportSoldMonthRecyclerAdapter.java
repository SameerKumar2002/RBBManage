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
import com.fusionlab.rbbmanage.dto.StockInfo;

public class ReportSoldMonthRecyclerAdapter extends RecyclerView.Adapter<ReportSoldMonthRecyclerAdapter.ViewHolder> {

    Context context;
    ReportSold[] list;

    public ReportSoldMonthRecyclerAdapter(Context context, ReportSold[] list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_report_sold,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        ReportSold sold = list[i];
        final String[] label ={"January","Fabruary","March","April","May","June","July","Augus","September","October","November","December"};
        holder.month.setText(label[i]);
        if(sold == null){
            holder.bag.setText("0");
            holder.price.setText("0");
            holder.total_price.setText("0");
        }else {
            holder.bag.setText(String.valueOf(sold.getBag_total()));
            holder.price.setText(String.valueOf(sold.getPrice()));
            holder.total_price.setText(String.valueOf(sold.getTotal_price()));
        }

    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView month,bag,price,total_price;

        public ViewHolder(@NonNull View View) {
            super(View);
            month =View.findViewById(R.id.id_date);
            bag =View.findViewById(R.id.id_bag_count);
            price =View.findViewById(R.id.id_price);
            total_price =View.findViewById(R.id.id_total_price);
        }
    }
}
