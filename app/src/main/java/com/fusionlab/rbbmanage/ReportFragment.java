package com.fusionlab.rbbmanage;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class ReportFragment extends Fragment {

    private CardView green_import_day;
    private CardView green_import_month;
    private CardView green_customer_sold_day;
    private CardView green_customer_sold_month;

    private CardView black_import_day;
    private CardView black_import_month;
    private CardView black_customer_sold_day;
    private CardView black_customer_sold_month;

    private CardView green_profit_day;
    private CardView green_profit_month;
    private CardView black_profit_day;
    private CardView black_profit_month;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);

        green_import_day = view.findViewById(R.id.id_green_import_day);
        green_import_month = view.findViewById(R.id.id_green_import_month);
        green_customer_sold_day = view.findViewById(R.id.id_green_customer_sold_day);
        green_customer_sold_month = view.findViewById(R.id.id_green_customer_sold_month);

        black_import_day = view.findViewById(R.id.id_black_import_day);
        black_import_month = view.findViewById(R.id.id_black_import_month);
        black_customer_sold_day = view.findViewById(R.id.id_black_customer_sold_day);
        black_customer_sold_month = view.findViewById(R.id.id_black_customer_sold_month);

        green_profit_day = view.findViewById(R.id.id_green_profit_day);
        green_profit_month = view.findViewById(R.id.id_green_profit_month);
        black_profit_day = view.findViewById(R.id.id_black_profit_day);
        black_profit_month = view.findViewById(R.id.id_black_profit_month);

        green_import_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_green_import_day");
                intent.putExtras(bundle);
                getContext().startActivity(intent);

            }
        });

        green_import_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_green_import_month");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        green_customer_sold_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_green_customer_sold_day");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        green_customer_sold_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_green_customer_sold_month");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        black_import_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_black_import_day");
                intent.putExtras(bundle);
                getContext().startActivity(intent);

            }
        });

        black_import_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_black_import_month");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        black_customer_sold_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_black_customer_sold_day");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        black_customer_sold_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_black_customer_sold_month");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        green_profit_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_green_profit_day");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        green_profit_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_green_profit_month");
                intent.putExtras(bundle);
                getContext().startActivity(intent);

            }
        });

        black_profit_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_black_profit_day");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });

        black_profit_month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ReportActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("Report","id_black_profit_month");
                intent.putExtras(bundle);
                getContext().startActivity(intent);
            }
        });


        return view;
    }
}
