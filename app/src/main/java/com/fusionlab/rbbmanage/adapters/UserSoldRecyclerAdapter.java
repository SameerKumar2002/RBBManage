package com.fusionlab.rbbmanage.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.Services.UserSoldService;
import com.fusionlab.rbbmanage.dto.User_StockInfo_Sold;

import java.util.ArrayList;

public class UserSoldRecyclerAdapter extends RecyclerView.Adapter<UserSoldRecyclerAdapter.ViewHolder> {

    Context context;
    ArrayList<User_StockInfo_Sold> stocks_info;
    UserSoldService service;
    String type;
    TextView no_data_lbl;

    public UserSoldRecyclerAdapter(Context context, ArrayList<User_StockInfo_Sold> stocks_info,TextView no_data_lbl,String type) {
        this.context = context;
        this.stocks_info = stocks_info;
        this.no_data_lbl = no_data_lbl;
        this.type = type;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_user_sold, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {

        final User_StockInfo_Sold stock = stocks_info.get(i);

        if(stock.getProfit()>0) holder.title.setText("PROFIT");
        else holder.title.setText("LOSS");
        holder.bag_count.setText(String.valueOf(stock.getBag_count()));
        holder.profit.setText(String.valueOf(stock.getProfit()));
        holder.price.setText(String.valueOf(stock.getUser_sold_price()));
        holder.total_price.setText(String.valueOf(stock.getUser_sold_total_price()));
        holder.avg_rate.setText(String.valueOf(stock.getBuy_avg()));
        holder.date.setText(String.valueOf(stock.getDate()));

        if(stock.getProfit()>0) holder.profit_lbl.setText("Profit");
        else holder.title.setText("Loss");

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                service = new UserSoldService(context);
                service.deleteUserSoldBag(stock.getId(),type);
                stocks_info.remove(i);
                notifyItemRemoved(i);
                notifyDataSetChanged();
                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();

                if(stocks_info.size()==0){
                    no_data_lbl.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return stocks_info.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title,bag_count,profit,price,total_price,avg_rate,date,profit_lbl;
        Button delete;

        public ViewHolder(@NonNull View view) {
            super(view);

            title = view.findViewById(R.id.id_title);
            bag_count = view.findViewById(R.id.id_bag_count);
            profit = view.findViewById(R.id.id_profit);
            price = view.findViewById(R.id.id_price);
            total_price = view.findViewById(R.id.id_total_price);
            avg_rate = view.findViewById(R.id.id_avg_rate);
            date = view.findViewById(R.id.id_date);
            profit_lbl = view.findViewById(R.id.id_profit_lbl);
            delete = view.findViewById(R.id.id_delete);


        }
    }
}
