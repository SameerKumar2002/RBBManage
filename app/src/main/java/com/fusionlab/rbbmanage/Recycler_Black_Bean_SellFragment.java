package com.fusionlab.rbbmanage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fusionlab.rbbmanage.Services.SellService;
import com.fusionlab.rbbmanage.adapters.SellRecyclerAdapter;

public class Recycler_Black_Bean_SellFragment extends Fragment {

    private RecyclerView recyclerView;
    private SellRecyclerAdapter adapter;
    TextView no_data_lbl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_recycler__black__bean__sell, container, false);
       recyclerView = view.findViewById(R.id.id_recycler);
       no_data_lbl = view.findViewById(R.id.id_no_data);

        SellService service = new SellService(getContext());
        if(service.getAllBag("BLACK BEAN").size()==0){

            no_data_lbl.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }else{

            adapter = new SellRecyclerAdapter(getContext(),service.getAllBag("BLACK BEAN"),no_data_lbl);
            RecyclerView.LayoutManager manger = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manger);
            recyclerView.setAdapter(adapter);
        }

       return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SellService service = new SellService(getContext());
        if(service.getAllBag("BLACK BEAN").size()==0){

            no_data_lbl.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

        }else{

            adapter = new SellRecyclerAdapter(getContext(),service.getAllBag("BLACK BEAN"),no_data_lbl);
            RecyclerView.LayoutManager manger = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manger);
            recyclerView.setAdapter(adapter);
        }


    }
}
