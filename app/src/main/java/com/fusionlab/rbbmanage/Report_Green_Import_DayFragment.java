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
import com.fusionlab.rbbmanage.adapters.ReportImportRecyclerAdapter;
import com.fusionlab.rbbmanage.dto.ReportImport;
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


public class Report_Green_Import_DayFragment extends Fragment {

    private BarChart barChart;
    private ReportImportService service ;
    private RecyclerView recyclerView;
    private Spinner sp;
    private TextView no_data_lbl,choose_month_lbl;
    private TableLayout table;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report__green__import__day, container, false);

        service = new ReportImportService(getContext());
        barChart = view.findViewById(R.id.barchart);
        sp = view.findViewById(R.id.id_spinner);
        recyclerView = view.findViewById(R.id.id_recycler);
        no_data_lbl =view.findViewById(R.id.id_no_data);
        choose_month_lbl = view.findViewById(R.id.id_choose_month_lbl);
        table =  view.findViewById(R.id.tableLayout);

        ArrayList<String> month_year = service.getMonthYear("GREEN BEAN");

        if(month_year.size()==0){
            no_data_lbl.setVisibility(View.VISIBLE);
            barChart.setVisibility(View.GONE);
            choose_month_lbl.setVisibility(View.GONE);
            sp.setVisibility(View.GONE);
            table.setVisibility(View.GONE);
        }else{

            ArrayAdapter<String> sp_adapter =new ArrayAdapter<>(getContext(),android.R.layout.simple_dropdown_item_1line,month_year);
            sp.setAdapter(sp_adapter);
            sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    String str_date = parent.getItemAtPosition(position).toString();

                    str_date="30/"+str_date;
                    SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date date = format.parse(str_date);
                        str_date = format2.format(date);
                        ArrayList<ReportImport>  barData = service.barEntries(str_date,"GREEN BEAN");
                        ArrayList<BarEntry> barEntries = new ArrayList<>();
                        ArrayList<String> all_date = new ArrayList<>();

                        for(int i=0;i<barData.size();i++){
                            barEntries.add(new BarEntry(i,barData.get(i).getTotal()));
                            all_date.add(barData.get(i).getDate());

                        }

                        BarDataSet dataSet = new BarDataSet(barEntries,"Day to Green Bean Count");
                        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                        dataSet.setValueTextColor(Color.BLACK);
                        dataSet.setValueTextSize(14f);

                        BarData barData1 = new BarData(dataSet);

                        barChart.setData(barData1);
                        barChart.getDescription().setText("amount of green bean over days");
                        barChart.animateY(2000);
                        XAxis xAxis = barChart.getXAxis();

                        if(barData.size()<=7){
                            xAxis.setLabelCount(barData.size());          // if we need to set fit bar,we must add this line and disable setVisibleRangeMaximum().
                        }
                        else{
                            barChart.setVisibleXRangeMaximum(7);
                            barChart.setDragEnabled(true);
                        }

                        xAxis.setValueFormatter(new IndexAxisValueFormatter(all_date));
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        xAxis.setLabelRotationAngle(270);
                        xAxis.setDrawGridLines(false);

                        barChart.getAxisRight().setEnabled(false);
                        barChart.setExtraOffsets(0,0,0,33);

                        barChart.invalidate();

                        ReportImportRecyclerAdapter adapter = new ReportImportRecyclerAdapter(getContext(),service.getTableData(str_date,"GREEN BEAN"));
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

        return view;
    }
}
