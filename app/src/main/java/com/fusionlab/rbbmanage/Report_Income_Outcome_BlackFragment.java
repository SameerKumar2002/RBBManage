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
import android.widget.Toast;

import com.fusionlab.rbbmanage.Services.ReportIncomeOutcomeService;
import com.fusionlab.rbbmanage.adapters.ReportIncomeOutcomeAdapter;
import com.fusionlab.rbbmanage.dto.ReportIncomeOutcome;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;


public class Report_Income_Outcome_BlackFragment extends Fragment {


    private BarChart barChart;
    private ReportIncomeOutcomeService service;
    private Spinner spinner;
    private TextView no_data_lbl,choose;
    private TableLayout table;
    private RecyclerView recyclerView;

    public Report_Income_Outcome_BlackFragment() {
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
        View view = inflater.inflate(R.layout.fragment_report__income__outcome__black, container, false);

        barChart = view.findViewById(R.id.id_group_barChart);
        spinner = view.findViewById(R.id.id_spinner);
        recyclerView = view.findViewById(R.id.id_recycler);
        no_data_lbl = view.findViewById(R.id.id_no_data_lbl);
        choose = view.findViewById(R.id.id_choose);
        table = view.findViewById(R.id.tableLayout2);

        service = new ReportIncomeOutcomeService(getContext());
        ArrayList<String> month_years = service.getMonthYear("BLACK BEAN");
        if(month_years.size()==0){
            barChart.setVisibility(View.GONE);
            spinner.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            choose.setVisibility(View.GONE);
            table.setVisibility(View.GONE);
            no_data_lbl.setVisibility(View.VISIBLE);
        }else {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, month_years);
            spinner.setAdapter(arrayAdapter);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String str_date = (String) parent.getItemAtPosition(position);
                str_date=str_date+"-30";

                service = new ReportIncomeOutcomeService(getContext());
                ArrayList<ReportIncomeOutcome> data = service.getData(str_date,"BLACK BEAN");

                Toast.makeText(getContext(),str_date,Toast.LENGTH_LONG).show();

                ArrayList<BarEntry> first_bar = new ArrayList<>();
                ArrayList<BarEntry> second_bar = new ArrayList<>();
                ArrayList<String> all_date = new ArrayList<>();

                for(int i=0;i<data.size();i++){
                    first_bar.add(new BarEntry(i+1,data.get(i).getOutcome()));
                    second_bar.add(new BarEntry(i+1,data.get(i).getIncome()));
                    all_date.add(data.get(i).getDate());
                }

                BarDataSet dataSet = new BarDataSet(first_bar,"Data set 1");
                dataSet.setColors(Color.RED);
                dataSet.setValueTextColor(Color.BLACK);
                dataSet.setValueTextSize(9.5f);

                BarDataSet dataSet2 = new BarDataSet(second_bar,"Data set 2");
                dataSet2.setColors(Color.BLUE);
                dataSet2.setValueTextColor(Color.BLACK);
                dataSet2.setValueTextSize(9.5f);

                BarData barData = new BarData(dataSet,dataSet2);

                barChart.setData(barData);
                barChart.getDescription().setText("amount of green bean over days");
                barChart.animateY(2000);
                XAxis xAxis = barChart.getXAxis();
                xAxis.setValueFormatter(new IndexAxisValueFormatter(all_date));
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
                barChart.getXAxis().setAxisMaximum(0+barChart.getBarData().getGroupWidth(groupSpaces,barSpaces)*data.size());
                barChart.getAxisLeft().setAxisMinimum(0);
                barChart.getAxisRight().setAxisMinimum(0);
                barChart.groupBars(0,groupSpaces,barSpaces);


                barChart.getAxisRight().setEnabled(false);
                barChart.getAxisLeft().setGranularity(1000f);
                barChart.getAxisLeft().setGranularityEnabled(true);
                barChart.invalidate();

                ReportIncomeOutcomeAdapter adapter = new ReportIncomeOutcomeAdapter(getContext(),data);
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
