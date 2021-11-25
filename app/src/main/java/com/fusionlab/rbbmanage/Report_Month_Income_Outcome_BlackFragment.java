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

import com.fusionlab.rbbmanage.Services.ReportIncomeOutcomeService;
import com.fusionlab.rbbmanage.adapters.ReportMonthIncomeOutcomeAdapter;
import com.fusionlab.rbbmanage.dto.ReportIncomeOutcome;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.Arrays;


public class Report_Month_Income_Outcome_BlackFragment extends Fragment {

    private RecyclerView recyclerView;
    private Spinner spinner;
    private BarChart barChart;
    private TableLayout table;
    private TextView choose,no_data_lbl;
    private ReportIncomeOutcomeService service;


    public Report_Month_Income_Outcome_BlackFragment() {
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
        View view = inflater.inflate(R.layout.fragment_report__month__income__outcome__black, container, false);

        recyclerView = view.findViewById(R.id.id_recycler);
        spinner = view.findViewById(R.id.id_spinner);
        barChart = view.findViewById(R.id.id_barChart);
        table = view.findViewById(R.id.tableLayout3);
        choose = view.findViewById(R.id.id_choose);
        no_data_lbl = view.findViewById(R.id.id_no_data_lbl);
        service = new ReportIncomeOutcomeService(getContext());

        ArrayList<String> years = service.getYear("BLACK BEAN");

        if(years.size()==0){

            recyclerView.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            barChart.setVisibility(View.GONE);
            table.setVisibility(View.GONE);
            choose.setVisibility(View.GONE);
            no_data_lbl.setVisibility(View.VISIBLE);

        }else {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,years);
            spinner.setAdapter(arrayAdapter);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String date1 = (String) parent.getItemAtPosition(position);
                date1 = date1+"-10-11";

                final String[] label ={"Jan","Fab","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

                ReportIncomeOutcome[] incomeOutcomes = service.getTotalByMonth("BLACK BEAN",date1);
                BarEntry[] barEntry_1 = new BarEntry[12];
                BarEntry[] barEntry_2 = new BarEntry[12];

                for(int i=0;i<12;i++){

                    if(incomeOutcomes[i]==null){
                        barEntry_1[i] = new BarEntry(i,0);
                        barEntry_2[i] = new BarEntry(i,0);
                    }else {
                        barEntry_1[i] = new BarEntry(i,incomeOutcomes[i].getOutcome());
                        barEntry_2[i] = new BarEntry(i,incomeOutcomes[i].getIncome());
                    }

                }

                BarDataSet dataSet = new BarDataSet(Arrays.asList(barEntry_1),"OutCome");
                dataSet.setColors(Color.RED);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(9.5f);

                BarDataSet dataSet2 = new BarDataSet(Arrays.asList(barEntry_2),"Income");
                dataSet2.setColors(Color.GREEN);
                dataSet2.setValueTextColor(Color.BLACK);
                dataSet2.setValueTextSize(9.5f);

                BarData barData = new BarData(dataSet,dataSet2);

                barChart.setData(barData);
                barChart.getDescription().setText("");
                barChart.animateY(2000);
                XAxis xAxis = barChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
                xAxis.setCenterAxisLabels(true);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setGranularity(1);
                xAxis.setGranularityEnabled(true);
                xAxis.setDrawGridLines(false);

                barChart.setDragEnabled(true);
                barChart.setVisibleXRangeMaximum(3);

                float barSpaces = 0.05f;
                float groupSpaces = 0.58f;

                barData.setBarWidth(0.16f);
                barChart.getXAxis().setAxisMinimum(0);
                barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpaces,barSpaces)*incomeOutcomes.length);
                barChart.getAxisLeft().setAxisMinimum(0);
                barChart.getAxisRight().setAxisMinimum(0);
                barChart.groupBars(0,groupSpaces,barSpaces);


                barChart.getAxisRight().setEnabled(false);
                barChart.getAxisLeft().setGranularity(1000f);
                barChart.getAxisLeft().setGranularityEnabled(true);
                barChart.invalidate();

                ReportMonthIncomeOutcomeAdapter adapter = new ReportMonthIncomeOutcomeAdapter(getContext(),incomeOutcomes);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        return view;
    }
}
