package com.fusionlab.rbbmanage.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fusionlab.rbbmanage.MainActivity;
import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.Services.SubmitService;
import com.fusionlab.rbbmanage.Submit_Sell_ButtonActivity;
import com.fusionlab.rbbmanage.Submit_Update_ButtonActivity;
import com.fusionlab.rbbmanage.dto.StockInfo;

import java.util.ArrayList;

public class SubmitRecyclerAdapter extends RecyclerView.Adapter<SubmitRecyclerAdapter.ViewHolder> {
   Context context;
   ArrayList<StockInfo> stocks;
   TextView no_data_lbl;


    public SubmitRecyclerAdapter(Context context, ArrayList<StockInfo> stocks,TextView no_data_lbl) {
        this.context = context;
        this.stocks = stocks;
        this.no_data_lbl = no_data_lbl;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_submit,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        final StockInfo stockInfo = stocks.get(position);
        holder.serial.setText(String.valueOf(stockInfo.getSerial_number()));
        holder.name.setText(stockInfo.getName());
        holder.address.setText(stockInfo.getAddress());
        holder.phone.setText(stockInfo.getPhone());
        holder.bag_count.setText(String.valueOf(stockInfo.getBag_count()));
        holder.viss_count.setText(String.valueOf(stockInfo.getViss_count()));
        holder.size.setText(stockInfo.getSize());
        holder.date.setText(stockInfo.getDate());
        holder.bean_lbl.setText(stockInfo.getType()+" Bag");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SubmitService service = new SubmitService(context);
                service.DeleteBag(stockInfo,stockInfo.getType());
                stocks.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
                if(stocks.size()==0){
                    no_data_lbl.setVisibility(View.VISIBLE);
                }

            }
        });

        holder.sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("stockInfo",stocks.get(position));
                Intent intent = new Intent(context, Submit_Sell_ButtonActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putSerializable("stockInfo",stocks.get(position));
                Intent intent = new Intent(context, Submit_Update_ButtonActivity.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,address,phone,size,date,bag_count,viss_count,bean_lbl,serial;
        Button delete,edit,sell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.id_bean_name);
            address = itemView.findViewById(R.id.id_bean_address);
            phone = itemView.findViewById(R.id.id_bean_phone);
            size = itemView.findViewById(R.id.id_bean_size);
            date = itemView.findViewById(R.id.id_bean_date);
            bag_count = itemView.findViewById(R.id.id_bean_bag_count);
            viss_count = itemView.findViewById(R.id.id_bean_viss_count);
            delete = itemView.findViewById(R.id.id_bean_delete);
            edit = itemView.findViewById(R.id.id_bean_edit);
            sell = itemView.findViewById(R.id.id_bean_sell);
            bean_lbl = itemView.findViewById(R.id.id_bean_lbl);
            serial = itemView.findViewById(R.id.id_bean_id_2);

        }
    }
}
