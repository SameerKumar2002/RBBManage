package com.fusionlab.rbbmanage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NavUtils;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fusionlab.rbbmanage.Services.SubmitService;
import com.fusionlab.rbbmanage.adapters.SubmitRecyclerAdapter;
import com.fusionlab.rbbmanage.dto.StockInfo;

import java.util.ArrayList;


public class Recycler_Green_Bean_SubmitFragment extends Fragment {

    private RecyclerView recyclerView;
    private SubmitRecyclerAdapter adapter;
    private TextView no_data_lbl;
    private ArrayList<StockInfo> info;

    public Recycler_Green_Bean_SubmitFragment() {

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_recycler__green__bean__submit, container, false);
        recyclerView = view.findViewById(R.id.id_recycler);
        no_data_lbl = view.findViewById(R.id.id_no_data);
        SubmitService service = new SubmitService(getContext());
        info =service.getAllBag("GREEN BEAN");

        if(info.size()==0){

            no_data_lbl.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }else {

            adapter = new SubmitRecyclerAdapter(getContext(),info,no_data_lbl);
            RecyclerView.LayoutManager manger = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manger);
            recyclerView.setAdapter(adapter);

        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SubmitService service = new SubmitService(getContext());
        info =service.getAllBag("GREEN BEAN");
        if(info.size()==0){

            no_data_lbl.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }else {

            adapter = new SubmitRecyclerAdapter(getContext(),info,no_data_lbl);
            RecyclerView.LayoutManager manger = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manger);
            recyclerView.setAdapter(adapter);

        }
    }
}
