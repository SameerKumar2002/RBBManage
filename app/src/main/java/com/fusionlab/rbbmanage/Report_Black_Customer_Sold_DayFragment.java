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
import com.fusionlab.rbbmanage.adapters.ReportSoldRecyclerAdapter;
import com.fusionlab.rbbmanage.dto.ReportSold;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Report_Black_Customer_Sold_DayFragment extends Fragment {

    private BarChart barChart;
    private Button compare_bag,compare_price,compare_total_price;
    private Spinner spinner;
    private RecyclerView recyclerView;
    private String str_date;
    private ArrayList<ReportSold> entries;
    private TableLayout table;
    private TextView no_data_lbl,choose_month_lbl;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report__black__customer__sold__day, container, false);

        barChart =  view.findViewById(R.id.id_barChart);
        compare_bag =  view.findViewById(R.id.id_compare_bag);
        compare_price =  view.findViewById(R.id.id_compare_price);
        compare_total_price =  view.findViewById(R.id.id_compare_total_price);
        spinner =  view.findViewById(R.id.id_spinner);
        recyclerView = view.findViewById(R.id.id_recycler);
        table = view.findViewById(R.id.id_tablelayout);
        no_data_lbl = view.findViewById(R.id.id_no_data_lbl);
        choose_month_lbl =view.findViewById(R.id.id_choose_month_lbl);

        final ReportSoldBlackService service = new ReportSoldBlackService(getContext());
        ArrayList<String> month_years = service.getMonthYear("BLACK BEAN");

        if(month_years.size()==0){

            no_data_lbl.setVisibility(View.VISIBLE);
            barChart.setVisibility(View.GONE);
            choose_month_lbl.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            compare_bag.setVisibility(View.GONE);
            compare_total_price.setVisibility(View.GONE);
            compare_price.setVisibility(View.GONE);
            table.setVisibility(View.GONE);

        }else{

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,month_years);
            spinner.setAdapter(arrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    str_date = parent.getItemAtPosition(position).toString();
                    str_date="30/"+str_date;
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = null;
                    try {

                        date = format.parse( str_date);
                        str_date = format2.format(date);
                        entries = service.getEntries(str_date,"BLACK BEAN");
                        compare_bag.performClick();

                        ReportSoldRecyclerAdapter adapter = new ReportSoldRecyclerAdapter(getContext(), entries);
                        RecyclerView.LayoutManager manger = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(manger);
                        recyclerView.setAdapter(adapter);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }


        compare_bag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<BarEntry> barEntries = new ArrayList<>();
                ArrayList<String> date = new ArrayList<>();

                for(int i = 0; i< entries.size(); i++){

                    BarEntry entry = new BarEntry(i, entries.get(i).getBag_total());
                    barEntries.add(entry);
                    date.add(entries.get(i).getDate());
                }

                BarDataSet barDataSet = new BarDataSet(barEntries,"Day to Black Bean Bag Count");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(14f);

                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);

                barChart.getDescription().setText("amount of black bean over days");
                barChart.animateY(1000);

                XAxis xAxis = barChart.getXAxis();

                if(date.size()<=7){
                    xAxis.setLabelCount(date.size());
                }
                else{
                    barChart.setVisibleXRangeMaximum(7);
                    barChart.setDragEnabled(true);
                }

                xAxis.setValueFormatter(new IndexAxisValueFormatter(date));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelRotationAngle(270);
                xAxis.setDrawGridLines(false);
                barChart.getAxisRight().setEnabled(false);
                barChart.setExtraOffsets(0,0,0,33);

                barChart.invalidate();

            }
        });

        //   compare_bag.performClick();

        compare_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                ArrayList<String> date = new ArrayList<>();

                for(int i = 0; i< entries.size(); i++){

                    BarEntry entry = new BarEntry(i, entries.get(i).getPrice());
                    barEntries.add(entry);
                    date.add(entries.get(i).getDate());
                }

                BarDataSet barDataSet = new BarDataSet(barEntries,"Day to Black Bean Bag Price");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(14f);

                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);

                barChart.getDescription().setText("amount of black bean over days");
                barChart.animateY(1000);

                XAxis xAxis = barChart.getXAxis();

                if(date.size()<=7){
                    xAxis.setLabelCount(date.size());
                }
                else{
                    barChart.setVisibleXRangeMaximum(7);
                    barChart.setDragEnabled(true);
                }

                xAxis.setValueFormatter(new IndexAxisValueFormatter(date));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelRotationAngle(270);
                barChart.getAxisRight().setEnabled(false);

                barChart.invalidate();

            }
        });

        compare_total_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<BarEntry> barEntries = new ArrayList<>();
                ArrayList<String> date = new ArrayList<>();

                for(int i = 0; i< entries.size(); i++){

                    BarEntry entry = new BarEntry(i, entries.get(i).getTotal_price());
                    barEntries.add(entry);
                    date.add(entries.get(i).getDate());
                }

                BarDataSet barDataSet = new BarDataSet(barEntries,"Day to Black Bean Bag Total Price");
                barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                barDataSet.setValueTextColor(Color.BLACK);
                barDataSet.setValueTextSize(14f);

                BarData barData = new BarData(barDataSet);
                barChart.setData(barData);

                barChart.getDescription().setText("amount of black bean over days");
                barChart.animateY(1000);

                XAxis xAxis = barChart.getXAxis();

                if(date.size()<=7){
                    xAxis.setLabelCount(date.size());
                }
                else{
                    barChart.setVisibleXRangeMaximum(7);
                    barChart.setDragEnabled(true);
                }

                xAxis.setValueFormatter(new IndexAxisValueFormatter(date));
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setLabelRotationAngle(270);
                barChart.getAxisRight().setEnabled(false);

                barChart.invalidate();

            }
        });




        return view;
    }
}
