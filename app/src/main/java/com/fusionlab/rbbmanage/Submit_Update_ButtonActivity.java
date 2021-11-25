package com.fusionlab.rbbmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
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
import android.widget.Toast;

import com.fusionlab.rbbmanage.Services.SubmitService;
import com.fusionlab.rbbmanage.dto.ReportSold;
import com.fusionlab.rbbmanage.dto.StockInfo;

import java.util.Calendar;

public class Submit_Update_ButtonActivity extends AppCompatActivity {

    private Button green_bean,black_bean;
    private EditText name,address,phone,bag_count,viss_count,date,serial;
    private Button clear,update;
    private ImageButton cal;
    private DatePickerDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit__update__button);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        green_bean = findViewById(R.id.id_input_green_bean_submit);
        black_bean = findViewById(R.id.id_input_black_bean_submit);

        name = findViewById(R.id.id_submit_name);
        phone = findViewById(R.id.id_submit_phone);
        address =  findViewById(R.id.id_submit_address);
        bag_count = findViewById(R.id.id_submit_bag_count);
        viss_count = findViewById(R.id.id_submit_viss_count);
        date =  findViewById(R.id.id_submit_date);
        serial = findViewById(R.id.id_submit_serial);
        clear = findViewById(R.id.id_submit_cancel);
        update = findViewById(R.id.id_submit_save);
        cal = findViewById(R.id.id_gb_submit_image_btn);

        final StockInfo stockInfo = (StockInfo) getIntent().getExtras().getSerializable("stockInfo");

        name.setText(stockInfo.getName());
        phone.setText(stockInfo.getPhone());
        address.setText(stockInfo.getAddress());
        bag_count.setText(String.valueOf(stockInfo.getBag_count()));
        viss_count.setText(String.valueOf(stockInfo.getViss_count()));
        date.setText(stockInfo.getDate());
        serial.setText(String.valueOf(stockInfo.getSerial_number()));
        final int id = stockInfo.getId();

        Calendar calendar = Calendar.getInstance();
        final int year =calendar.get( Calendar.YEAR);
        final int month = calendar.get( Calendar.MONTH);
        final int day = calendar.get( Calendar.DAY_OF_MONTH);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new DatePickerDialog(Submit_Update_ButtonActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                dialog.show();
            }
        });

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



        if(stockInfo.getType().equals("GREEN BEAN")){
            green_bean.performClick();
        }else {
            black_bean.performClick();
        }


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StockInfo stock = new StockInfo();
                stock.setId(id);
                stock.setName(name.getText().toString());
                stock.setSerial_number(Integer.parseInt(serial.getText().toString()));
                stock.setName(name.getText().toString());
                stock.setPhone(phone.getText().toString());
                stock.setAddress(address.getText().toString());
                stock.setDate(date.getText().toString());
                stock.setType(sp.getString("Type", " "));
                stock.setSize("Big");
                stock.setBag_count(Integer.parseInt(bag_count.getText().toString()));
                stock.setViss_count(Float.parseFloat(viss_count.getText().toString()));

                SubmitService service = new SubmitService(Submit_Update_ButtonActivity.this);
                service.SubmitUpdate(stock);
                finish();


            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
