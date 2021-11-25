package com.fusionlab.rbbmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fusionlab.rbbmanage.Services.SellService;
import com.fusionlab.rbbmanage.dto.StockInfo_Sold;

import java.util.Calendar;

public class Sell_Update_ButtonActivity extends AppCompatActivity {

    private EditText bag_count,viss_count,date,price;
    private ImageButton calendar;
    private Button clear,update;
    private DatePickerDialog dialog;
    private Button green_bean,black_bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell__update__button);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        green_bean = findViewById(R.id.id_input_green_bean_sell);
        black_bean = findViewById(R.id.id_input_black_bean_sell);
        bag_count = findViewById(R.id.id_sell_bag_count);
        viss_count = findViewById(R.id.id_sell_viss_count);
        date = findViewById(R.id.id_sell_date);
        price = findViewById(R.id.id_sell_price);
        clear = findViewById(R.id.id_sell_cancel);
        update = findViewById(R.id.id_sell_save);
        calendar =  findViewById(R.id.id_sell_image_btn);

        Calendar calendar1 = Calendar.getInstance();
        final int day = calendar1.get(Calendar.DAY_OF_MONTH);
        final int month = calendar1.get(Calendar.MONTH);
        final int year = calendar1.get(Calendar.YEAR);
        date.setText(day+"/"+(month+1)+"/"+year);

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new DatePickerDialog(Sell_Update_ButtonActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                dialog.show();
            }
        });


        final StockInfo_Sold stockInfo_sold = (StockInfo_Sold) getIntent().getExtras().getSerializable("StockInfo");
        bag_count.setText(String.valueOf(stockInfo_sold.getBag_count()));
        viss_count.setText(String.valueOf(stockInfo_sold.getViss_count()));
        date.setText(stockInfo_sold.getDate());
        price.setText(String.valueOf(stockInfo_sold.getPrice()));

        final SharedPreferences sp = getSharedPreferences("Bean_Type", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Type","Green Bean");
        editor.commit();

        green_bean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.putString("Type","Green Bean");
                editor.commit();
                green_bean.setBackground(getResources().getDrawable(R.drawable.type_button_1));
                black_bean.setBackground(getResources().getDrawable(R.drawable.type_button_4));

            }
        });

        black_bean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sp.edit();
                editor.clear();
                editor.putString("Type","Black Bean");
                editor.commit();
                green_bean.setBackground(getResources().getDrawable(R.drawable.type_button_3));
                black_bean.setBackground(getResources().getDrawable(R.drawable.type_button_2));
            }
        });

        if(stockInfo_sold.getType().equals("GREEN BEAN")){
            green_bean.performClick();
        }else{
            black_bean.performClick();
        }



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StockInfo_Sold stock = new StockInfo_Sold();
                stock.setType(sp.getString("Type", " "));
                stock.setId(stockInfo_sold.getId());
                stock.setBag_count(Integer.parseInt(bag_count.getText().toString()));
                stock.setViss_count(Float.parseFloat(viss_count.getText().toString()));
                stock.setPrice(Integer.parseInt(price.getText().toString()));
                stock.setTotal_price((int) (stock.getPrice() * stock.getBag_count() + stock.getPrice() * stock.getViss_count() / 30));
                stock.setDate(date.getText().toString());
                stock.setSize("Big");

                SellService service = new SellService(Sell_Update_ButtonActivity.this);
                service.UpdateBag(stock);
                finish();


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
