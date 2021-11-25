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
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

import com.fusionlab.rbbmanage.Services.ReportImportService;
import com.fusionlab.rbbmanage.adapters.ReportImportMonthAdapter;
import com.fusionlab.rbbmanage.dto.ReportImport;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Arrays;


public class Report_Black_Import_MonthFragment extends Fragment {

    private BarChart barChart;
    private Spinner spinner;
    private ReportImportService service;
    private RecyclerView recyclerView;
    private TextView no_data_lbl,choose_year_lbl;
    private TableLayout table;


    public Report_Black_Import_MonthFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report__black__import__month, container, false);

        recyclerView = view.findViewById(R.id.id_recycler);
        spinner = view.findViewById(R.id.id_spinner);
        barChart = view.findViewById(R.id.id_barchart);
        no_data_lbl = view.findViewById(R.id.id_no_data_lbl);
        choose_year_lbl = view.findViewById(R.id.id_choose_year_lbl);
        table = view.findViewById(R.id.id_tableLayout);

        service = new ReportImportService(getContext());
        ArrayList<String> years = service.getYear("BLACK BEAN");

        if(years.size()==0){

            no_data_lbl.setVisibility(View.VISIBLE);
            barChart.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            choose_year_lbl.setVisibility(View.GONE);
            table.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);

        }else {

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,years);
            spinner.setAdapter(arrayAdapter);

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    String year = parent.getItemAtPosition(position).toString();
                    year=year+"-10-10";

                    final String[] label ={"Jan","Fab","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

                    ReportImport[] entries = service.getTotalBagByMonth(year,"BLACK BEAN");
                    BarEntry[] barEntries= new BarEntry[12];

                    for(int i=0;i<12;i++){

                        if(entries[i]==null) barEntries[i]=new BarEntry(i,0);
                        else barEntries[i]=new BarEntry(i,entries[i].getTotal());

                    }

                    BarDataSet dataSet = new BarDataSet(Arrays.asList(barEntries),"Day to Green Bean Count");
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

                    ReportImportMonthAdapter adapter = new ReportImportMonthAdapter(getContext(),entries);
                    RecyclerView.LayoutManager manger = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(manger);
                    recyclerView.setAdapter(adapter);


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


        }

        return view;
    }
}
