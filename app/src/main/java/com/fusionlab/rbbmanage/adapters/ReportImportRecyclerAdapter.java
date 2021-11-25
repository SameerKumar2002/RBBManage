package com.fusionlab.rbbmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.dto.ReportImportTable;
import com.fusionlab.rbbmanage.dto.ReportSold;

import java.util.ArrayList;

public class ReportImportRecyclerAdapter extends RecyclerView.Adapter<ReportImportRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<ReportImportTable> data;

    public ReportImportRecyclerAdapter(Context context, ArrayList<ReportImportTable> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ReportImportRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_report_import,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportImportRecyclerAdapter.ViewHolder holder, int position) {

           ReportImportTable value = data.get(position);
           holder.date.setText(value.getDate());
           holder.no_of_people.setText(String.valueOf(value.getNo_of_people()));
           holder.bag_count.setText(String.valueOf(value.getBag_count()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView date,no_of_people,bag_count;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.id_dates);
            no_of_people = itemView.findViewById(R.id.id_people);
            bag_count = itemView.findViewById(R.id.id_amount);
        }
    }
}
