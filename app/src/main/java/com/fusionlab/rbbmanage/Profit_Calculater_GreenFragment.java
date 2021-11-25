package com.fusionlab.rbbmanage;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Services.ProfitCalculaterGreenService;
import com.fusionlab.rbbmanage.Services.ReportHomeService;
import com.fusionlab.rbbmanage.dto.ReportProfit;
import com.fusionlab.rbbmanage.dto.User_StockInfo_Sold;

import java.util.Calendar;


public class Profit_Calculater_GreenFragment extends Fragment {

   private TextView user_cal_total_bag,user_total_price,actual_avg_rate,actual_total_price,total_loss,remain_total_bag,remain_avg_rate,type,cal_info_lbl,profit_type_lbl,current_bag,current_avg;
   private EditText user_price,user_total_bag,user_viss_count;
   private Button calculate,submit;
   private ProfitCalculaterGreenService service;
   private ReportProfit profit;
   private CardView loss_card,remain_card;
   private ReportHomeService current_service;



    public Profit_Calculater_GreenFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profit__calculater__green, container, false);
        user_price = view.findViewById(R.id.id_price);
        user_total_bag = view.findViewById(R.id.id_total_bag);
        user_viss_count = view.findViewById(R.id.id_viss_count);
        user_cal_total_bag =view.findViewById(R.id.id_user_total_bag);
        user_total_price=view.findViewById(R.id.id_user_total_price);
        actual_avg_rate=view.findViewById(R.id.id_actual_avg_rate);
        actual_total_price=view.findViewById(R.id.id_actual_total_price);
        total_loss=view.findViewById(R.id.id_total_loss);
        remain_total_bag=view.findViewById(R.id.id_remain_total_bag);
        remain_avg_rate=view.findViewById(R.id.id_remina_avg_rate);
        submit = view.findViewById(R.id.id_submit);
        calculate = view.findViewById(R.id.id_calculate);
        type = view.findViewById(R.id.id_type);
        profit_type_lbl = view.findViewById(R.id.id_total_type);
        current_bag = view.findViewById(R.id.id_current_bag);
        current_avg = view.findViewById(R.id.id_current_avg_rate);

        cal_info_lbl = view.findViewById(R.id.id_cal_info_lbl);
        loss_card =  view.findViewById(R.id.id_loss_card);
        remain_card = view.findViewById(R.id.id_remain_card);

        service = new ProfitCalculaterGreenService(getContext());
        current_service = new ReportHomeService(getContext());

        current_bag.setText(String.valueOf(current_service.getCurrentBuy("GREEN BEAN")));
        current_avg.setText(String.valueOf(current_service.getCurrentAverage("GREEN BEAN")));


        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isValidate()) {

                    profit = service.getProfit(Integer.parseInt(user_total_bag.getText().toString()), Float.parseFloat(user_viss_count.getText().toString()), Integer.parseInt(user_price.getText().toString()),"GREEN BEAN");

                    user_cal_total_bag.setText(user_total_bag.getText().toString());
                    user_total_price.setText(String.valueOf(profit.getUser_total_price()));
                    actual_avg_rate.setText(String.valueOf(profit.getUser_avg_rate()));
                    actual_total_price.setText(String.valueOf(profit.getDb_total_price()));
                    total_loss.setText(String.valueOf(profit.getTotal_loss()));
                    type.setText(profit.getProfit_type());
                    remain_avg_rate.setText(String.valueOf(profit.getRemain_average_rate()));
                    remain_total_bag.setText(String.valueOf(profit.getRemain_total_bag()));
                    if(profit.getTotal_loss()<0){
                        profit_type_lbl.setText("Total Loss");
                    }else {
                        profit_type_lbl.setText("Total Profit");
                    }

                    cal_info_lbl.setVisibility(View.VISIBLE);
                    loss_card.setVisibility(View.VISIBLE);
                    remain_card.setVisibility(View.VISIBLE);
                    submit.setVisibility(View.VISIBLE);
                }

            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                User_StockInfo_Sold stock = new User_StockInfo_Sold();
                stock.setBag_count((float) (Integer.parseInt(user_total_bag.getText().toString())+Float.parseFloat(user_viss_count.getText().toString())/30.0));


                Calendar calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                stock.setDate(String.valueOf(day)+"/"+String.valueOf(month+1)+"/"+String.valueOf(year));
                stock.setT_date(String.valueOf(year)+'-'+String.valueOf(month+1)+"-"+String.valueOf(day));

                stock.setBuy_avg((int) profit.getUser_avg_rate());
                stock.setUser_sold_price(Integer.parseInt(user_price.getText().toString()));
                stock.setUser_sold_total_price((int) profit.getUser_total_price());
                stock.setProfit((int) profit.getTotal_loss());

                service.insertUserSold(stock,"GREEN BEAN");
                if(profit.getDifference_bag_count()!=0 || profit.getDifference_viss_count()!=0){

                    service.updateRemainInfo(profit.getLast_id(),profit.getLast_price(), (int) profit.getDifference_bag_count(),profit.getDifference_viss_count(),"GREEN BEAN");
                }else{
                    service.deleteRemainInfo(profit.getLast_id(),"GREEN BEAN");
                }

                cal_info_lbl.setVisibility(View.GONE);
                loss_card.setVisibility(View.GONE);
                remain_card.setVisibility(View.GONE);
                submit.setVisibility(View.GONE);
            }
        });


        return view;
    }

    private boolean isValidate(){
        boolean isValid = true;
        if(user_total_bag.getText().toString().length()==0){
            user_total_bag.setError(getResources().getString(R.string.error_bag_count));
            isValid=false;
        }
        if(user_viss_count.getText().toString().length()==0){
            user_viss_count.setError(getResources().getString(R.string.error_viss_count));
            isValid=false;
        }
        if(user_price.getText().toString().length()==0){
            user_price.setError(getResources().getString(R.string.error_price));
            isValid=false;
        }
        if(Integer.parseInt(user_total_bag.getText().toString())+Float.parseFloat(user_viss_count.getText().toString())/30.0>current_service.getCurrentBuy("GREEN BEAN")){
            user_total_bag.setError(getResources().getString(R.string.error_greater));
        }

        return isValid;
    }
}
