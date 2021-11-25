package com.fusionlab.rbbmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Services.SubmitService;
import com.fusionlab.rbbmanage.dto.StockInfo;
import com.fusionlab.rbbmanage.dto.SubmitSellButtonImplement;

public class Submit_Sell_ButtonActivity extends AppCompatActivity {

    private Button green_bean,black_bean;
    private EditText name,address,phone,bag_count,viss_count,date,serial,sell_bag_count,sell_viss_count,price;
    private Button cancel,sell;
    private StockInfo stockInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__sell__button);

        green_bean = findViewById(R.id.id_input_green_bean_submit);
        black_bean = findViewById(R.id.id_input_black_bean_submit);

        name = findViewById(R.id.id_submit_name);
        phone = findViewById(R.id.id_submit_phone);
        address = findViewById(R.id.id_submit_address);
        bag_count = findViewById(R.id.id_submit_bag_count);
        viss_count = findViewById(R.id.id_submit_viss_count);
        sell_bag_count = findViewById(R.id.id_submit_bag_count_sold);
        sell_viss_count = findViewById(R.id.id_submit_viss_count_sold);
        date= findViewById(R.id.id_submit_date);
        price = findViewById(R.id.id_price);

        serial = findViewById(R.id.id_submit_serial);
        cancel = findViewById(R.id.id_submit_cancel);
        sell = findViewById(R.id.id_submit_save);

        Bundle bundle= getIntent().getExtras();
        stockInfo = (StockInfo) bundle.getSerializable("stockInfo");

        serial.setText(String.valueOf(stockInfo.getSerial_number()));
        name.setText(stockInfo.getName());
        phone.setText(stockInfo.getPhone());
        address.setText(stockInfo.getAddress());
        bag_count.setText(String.valueOf(stockInfo.getBag_count()));
        viss_count.setText(String.valueOf(stockInfo.getViss_count()));
        date.setText(stockInfo.getDate());

        if(stockInfo.getType().equals("GREEN BEAN")){
            green_bean.setBackground(getResources().getDrawable(R.drawable.type_button_1));
            black_bean.setBackground(getResources().getDrawable(R.drawable.type_button_4));
        }else {
            green_bean.setBackground(getResources().getDrawable(R.drawable.type_button_3));
            black_bean.setBackground(getResources().getDrawable(R.drawable.type_button_2));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()) {

                    SubmitSellButtonImplement info = new SubmitSellButtonImplement();
                    info.setDate(date.getText().toString());
                    info.setType(stockInfo.getType());
                    info.setPrice(Integer.parseInt(price.getText().toString()));
                    info.setSold_bag_count(Integer.parseInt(sell_bag_count.getText().toString()));
                    info.setSold_viss_count(Float.parseFloat(sell_viss_count.getText().toString()));
                    info.setUnsold_bag_count(stockInfo.getBag_count());
                    info.setUnsold_viss_count(stockInfo.getViss_count());
                    info.setId(stockInfo.getId());

                    SubmitService service = new SubmitService(Submit_Sell_ButtonActivity.this);
                    service.submitSellButtonImplement(info);

                    finish();


                }
            }
        });

    }

    private boolean isValidate(){
        boolean isValid = true;

        if(sell_bag_count.getText().toString().length() ==0){
            sell_bag_count.setError(getResources().getString(R.string.error_bag_count));
            isValid = false;
        }
        if(sell_viss_count.getText().toString().length() ==0){
            sell_viss_count.setError(getResources().getString(R.string.error_viss_count));
            isValid = false;
        }
        if(price.getText().toString().length() ==0){
            price.setError(getResources().getString(R.string.error_price));
            isValid = false;
        }


        return isValid;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

       if(item.getItemId() == android.R.id.home){
           finish();
       }
        return super.onOptionsItemSelected(item);

    }
}
