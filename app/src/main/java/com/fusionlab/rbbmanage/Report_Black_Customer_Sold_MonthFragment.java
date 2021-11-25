package com.fusionlab.rbbmanage;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.fusionlab.rbbmanage.Services.ReportSoldBlackService;
import com.fusionlab.rbbmanage.adapters.ReportSoldMonthRecyclerAdapter;
import com.fusionlab.rbbmanage.dto.ReportSold;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;


public class Report_Black_Customer_Sold_MonthFragment extends Fragment {

    private BarChart barChart;
    private Button compare_bag,compare_price,compare_total_price;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private String str_date;
    private TableLayout table;
    private TextView no_data_lbl,choose_month_lbl;
    private ReportSold[] entries;
    private ReportSoldBlackService service;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report__black__customer__sold__month, container, false);

        barChart =  view.findViewById(R.id.id_barChart);
        compare_bag =  view.findViewById(R.id.id_compare_bag);
        compare_price =  view.findViewById(R.id.id_compare_price);
        compare_total_price =  view.findViewById(R.id.id_compare_total_price);
        spinner =  view.findViewById(R.id.id_spinner);
        recyclerView = view.findViewById(R.id.id_recycler);
        table = view.findViewById(R.id.id_tablelayout);
        no_data_lbl = view.findViewById(R.id.id_no_data_lbl);
        choose_month_lbl =view.findViewById(R.id.id_choose_month_lbl);

        final String[] label ={"Jun","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

        service = new ReportSoldBlackService(getContext());
        ArrayList<String> year =service.getYear("BLACK BEAN");

        if(year.size()==0){

            barChart.setVisibility(View.GONE);
            choose_month_lbl.setVisibility(View.GONE);
            table.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            compare_bag.setVisibility(View.GONE);
            compare_price.setVisibility(View.GONE);
            compare_total_price.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            no_data_lbl.setVisibility(View.VISIBLE);

        }else {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, year);
            spinner.setAdapter(arrayAdapter);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                str_date = parent.getItemAtPosition(position).toString();
                service = new ReportSoldBlackService(getContext());
                str_date=str_date+"-10-10";
                entries = service.getEntriesByMonth(str_date,"BLACK BEAN");
                compare_bag.performClick();

                ReportSoldMonthRecyclerAdapter adapter = new ReportSoldMonthRecyclerAdapter(getContext(),entries);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        compare_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BarEntry[] barEntries = new BarEntry[12];

                for(int i = 0; i< entries.length; i++){

                    if(entries[i]==null){
                        BarEntry entry = new BarEntry(i, 0);
                        barEntries[i]=entry;
                    }else {
                        BarEntry entry = new BarEntry(i, entries[i].getBag_total());
                        barEntries[i]=entry;
                    }


                }

                BarDataSet dataSet = new BarDataSet(Arrays.<BarEntry>asList(barEntries),"Day to Green Bean Count");
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(14f);

                BarData barData1 = new BarData(dataSet);

                barChart.setData(barData1);
                barChart.animateY(2000);
                barChart.setFitBars(true);
                barChart.getAxisRight().setEnabled(false);

                XAxis xAxis = barChart.getXAxis();
                xAxis.setLabelCount(12);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelRotationAngle(270);
                xAxis.setDrawGridLines(false);

                barChart.setExtraOffsets(0,0,0,10);
                barChart.invalidate();
            }
        });

        compare_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarEntry[] barEntries = new BarEntry[12];

                for(int i = 0; i< entries.length; i++){

                    if(entries[i]==null){
                        BarEntry entry = new BarEntry(i, 0);
                        barEntries[i]=entry;
                    }else {
                        BarEntry entry = new BarEntry(i, entries[i].getPrice());
                        barEntries[i]=entry;
                    }


                }

                BarDataSet dataSet = new BarDataSet(Arrays.<BarEntry>asList(barEntries),"Day to Green Bean Count");
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(14f);

                BarData barData1 = new BarData(dataSet);

                barChart.setData(barData1);
                barChart.animateY(2000);
                barChart.setFitBars(true);
                barChart.getAxisRight().setEnabled(false);

                XAxis xAxis = barChart.getXAxis();
                xAxis.setLabelCount(12);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelRotationAngle(270);
                xAxis.setDrawGridLines(false);

                barChart.setExtraOffsets(0,0,0,10);
                barChart.invalidate();
            }
        });

        compare_total_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BarEntry[] barEntries = new BarEntry[12];

                for(int i = 0; i< entries.length; i++){

                    if(entries[i]==null){
                        BarEntry entry = new BarEntry(i, 0);
                        barEntries[i]=entry;
                    }else {
                        BarEntry entry = new BarEntry(i, entries[i].getTotal_price());
                        barEntries[i]=entry;
                    }


                }

                BarDataSet dataSet = new BarDataSet(Arrays.<BarEntry>asList(barEntries),"Day to Green Bean Count");
                dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(14f);

                BarData barData1 = new BarData(dataSet);

                barChart.setData(barData1);
                barChart.animateY(2000);
                barChart.setFitBars(true);
                barChart.getAxisRight().setEnabled(false);

                XAxis xAxis = barChart.getXAxis();
                xAxis.setLabelCount(12);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelRotationAngle(270);
                xAxis.setDrawGridLines(false);

                barChart.setExtraOffsets(0,0,0,10);
                barChart.invalidate();

            }
        });


        return view;
    }
}
