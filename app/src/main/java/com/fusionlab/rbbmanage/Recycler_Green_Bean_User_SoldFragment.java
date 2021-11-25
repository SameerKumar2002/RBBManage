package com.fusionlab.rbbmanage;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fusionlab.rbbmanage.Services.UserSoldService;
import com.fusionlab.rbbmanage.adapters.UserSoldRecyclerAdapter;
import com.fusionlab.rbbmanage.dto.User_StockInfo_Sold;

import java.util.ArrayList;


public class Recycler_Green_Bean_User_SoldFragment extends Fragment {

    RecyclerView recyclerView;
    TextView no_data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_recycler__green__bean__user__sold, container, false);
        recyclerView = view.findViewById(R.id.id_recycler);
        no_data = view.findViewById(R.id.id_no_data);

        UserSoldService service = new UserSoldService(getContext());
        ArrayList<User_StockInfo_Sold> sold =service.getStocks("GREEN BEAN");

        if(sold.size()==0){
            no_data.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {

            UserSoldRecyclerAdapter adapter = new UserSoldRecyclerAdapter(getContext(), sold, no_data, "GREEN BEAN");
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }

       return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        UserSoldService service = new UserSoldService(getContext());
        ArrayList<User_StockInfo_Sold> sold =service.getStocks("BLACK BEAN");

        if(sold.size()==0){
            no_data.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {

            UserSoldRecyclerAdapter adapter = new UserSoldRecyclerAdapter(getContext(), sold, no_data, "BLACK BEAN");
            RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
        }
    }

}
