package com.fusionlab.rbbmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.dto.ReportImport;

import java.util.ArrayList;

public class ReportImportMonthAdapter extends RecyclerView.Adapter<ReportImportMonthAdapter.ViewHolder> {

    Context context;
    ReportImport[] list;
    public ReportImportMonthAdapter(Context context, ReportImport[] list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_report_import_month,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
       ReportImport report = list[i];
        final String[] label ={"January","Fabruary","March","April","May","June","July","Augus","September","October","November","December"};
        viewHolder.month.setText(label[i]);
       if(report == null)
           viewHolder.total_bag.setText("0");
       else
           viewHolder.total_bag.setText(String.valueOf(report.getTotal()));


    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView month;
        TextView total_bag;

        public ViewHolder(@NonNull View View) {
            super(View);
           month = View.findViewById(R.id.id_month);
           total_bag = View.findViewById(R.id.id_total_bag);
        }
    }
}
