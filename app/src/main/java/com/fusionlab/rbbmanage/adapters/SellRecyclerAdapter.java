package com.fusionlab.rbbmanage.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.Sell_Update_ButtonActivity;
import com.fusionlab.rbbmanage.Services.SellService;
import com.fusionlab.rbbmanage.dto.StockInfo_Sold;

import java.util.ArrayList;

public class SellRecyclerAdapter extends RecyclerView.Adapter<SellRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<StockInfo_Sold> stocks;
    TextView no_data_lbl;

    public SellRecyclerAdapter(Context context, ArrayList<StockInfo_Sold> stocks, TextView no_data_lbl) {
        this.context = context;
        this.stocks = stocks;
        this.no_data_lbl = no_data_lbl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_sell,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final StockInfo_Sold sold = stocks.get(position);
        holder.bean_lbl.setText(sold.getType() +" Bag");
        holder.bag_count.setText(String.valueOf(sold.getBag_count()));
        holder.viss_count.setText(String.valueOf(sold.getViss_count()));
        holder.size.setText(sold.getSize());
        holder.price.setText(String.valueOf(sold.getPrice()));
        holder.total_price.setText(String.valueOf(sold.getTotal_price()));
        holder.date.setText(sold.getDate());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SellService service = new SellService(context);
                service.DeleteBag(sold,sold.getType());
                stocks.remove(position);
                notifyItemRemoved(position);
                notifyDataSetChanged();
                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();

                if(stocks.size()==0){
                    no_data_lbl.setVisibility(View.VISIBLE);
                }
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Sell_Update_ButtonActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("StockInfo",sold);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView bean_lbl,size,price,total_price,date,bag_count,viss_count;
        Button delete,edit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bean_lbl = itemView.findViewById(R.id.id_bean_lbl);
            size = itemView.findViewById(R.id.id_bean_size);
            price = itemView.findViewById(R.id.id_price);
            total_price = itemView.findViewById(R.id.id_total_price);
            date = itemView.findViewById(R.id.id_bean_date);
            bag_count = itemView.findViewById(R.id.id_bean_bag_count);
            viss_count = itemView.findViewById(R.id.id_bean_viss_count);
            delete = itemView.findViewById(R.id.id_delete);
            edit = itemView.findViewById(R.id.id_edit);

        }
    }
}
