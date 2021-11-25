package com.fusionlab.rbbmanage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.fusionlab.rbbmanage.Services.ReportHomeService;


public class Report_HomeFragment extends Fragment {

   private TextView current_import;
   private TextView current_buy;
   private TextView current_average;
   private TextView tomorrow_import;
   private TextView tomorrow_buy;
   private TextView tomorrow_sold;
   private TextView today_import;
   private TextView today_buy;
   private TextView today_sold;
   private TextView tomorrow_outcome;
   private TextView tomorrow_income;
   private TextView today_income;
   private TextView today_outcome;

   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report__home, container, false);
        current_import = view.findViewById(R.id.id_current_import);
        current_buy = view.findViewById(R.id.id_current_buy);
        current_average = view.findViewById(R.id.id_current_average);
        tomorrow_import = view.findViewById(R.id.id_tomorrow_import);
        tomorrow_buy = view.findViewById(R.id.id_tomorrow_buy);
        tomorrow_sold = view.findViewById(R.id.id_tomorrow_sold);
        today_import = view.findViewById(R.id.id_today_import);
        today_buy = view.findViewById(R.id.id_today_buy);
        today_sold = view.findViewById(R.id.id_today_sold);
        tomorrow_income =  view.findViewById(R.id.id_tomorrow_income);
        tomorrow_outcome =  view.findViewById(R.id.id_tomorrow_outcome);
        today_income =  view.findViewById(R.id.id_today_income);
        today_outcome =  view.findViewById(R.id.id_today_outcome );

        ReportHomeService service = new ReportHomeService(getContext());

        current_import.setText(String.valueOf(service.getCurrentImport("GREEN BEAN")));
        current_buy.setText(String.valueOf(service.getCurrentBuy("GREEN BEAN")));
        current_average.setText(String.valueOf(service.getCurrentAverage("GREEN BEAN")));

        tomorrow_import.setText(String.valueOf(service.getTomorrowImport("GREEN BEAN")));
        tomorrow_buy.setText(String.valueOf(service.getTomorrowBuy("GREEN BEAN")));
        tomorrow_sold.setText(String.valueOf(service.getTomorrowSold("GREEN BEAN")));

        today_import.setText(String.valueOf(service.getTodayImport("GREEN BEAN")));
        today_buy.setText(String.valueOf(service.getTodayBuy("GREEN BEAN")));
        today_sold.setText(String.valueOf(service.getTodaySold("GREEN BEAN")));

        tomorrow_income.setText(String.valueOf(service.getTomorrowIncome("GREEN BEAN")));
        tomorrow_outcome.setText(String.valueOf(service.getTomorrowOutcome("GREEN BEAN")));

        today_income.setText(String.valueOf(service.getTodayIncome("GREEN BEAN")));
        today_outcome.setText(String.valueOf(service.getTodayOutcome("GREEN BEAN")));

        return view;
    }
}
