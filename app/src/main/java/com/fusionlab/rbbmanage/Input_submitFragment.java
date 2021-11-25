package com.fusionlab.rbbmanage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Services.SubmitService;
import com.fusionlab.rbbmanage.dto.StockInfo;

import java.util.Calendar;


public class Input_submitFragment extends Fragment {

    private Button green_bean,black_bean;
    private EditText name,address,phone,bag_count,viss_count,date,serial;
    private RadioGroup group;
    private RadioButton rb;
    private Button cancel,save;
    private ImageButton cal;
    private DatePickerDialog dialog;



    public Input_submitFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       final View view = inflater.inflate(R.layout.fragment_input_submit, container, false);

        green_bean = view.findViewById(R.id.id_input_green_bean_submit);
        black_bean = view.findViewById(R.id.id_input_black_bean_submit);

        name = view.findViewById(R.id.id_submit_name);
        phone = view.findViewById(R.id.id_submit_phone);
        address =  view.findViewById(R.id.id_submit_address);
        bag_count = view.findViewById(R.id.id_submit_bag_count);
        viss_count = view.findViewById(R.id.id_submit_viss_count);
        group = view.findViewById(R.id.id_size_radio_group);
        date =  view.findViewById(R.id.id_submit_date);

        serial = view.findViewById(R.id.id_submit_serial);
        cancel = view.findViewById(R.id.id_submit_cancel);
        save = view.findViewById(R.id.id_submit_save);
        cal = view.findViewById(R.id.id_gb_submit_image_btn);

        Calendar calendar = Calendar.getInstance();
        final int year =calendar.get( Calendar.YEAR);
        final int month = calendar.get( Calendar.MONTH);
        final int day = calendar.get( Calendar.DAY_OF_MONTH);

        date.setText(day+"/"+(month+1)+"/"+year);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        date.setText(dayOfMonth+"/"+(month+1)+"/"+year);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        final SharedPreferences sp = getActivity().getSharedPreferences("Bean_Type", Context.MODE_PRIVATE);
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
                Toast.makeText(getContext(),sp.getString("Type"," "),Toast.LENGTH_LONG).show();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidate()) {

                    final StockInfo stock = new StockInfo();

                    stock.setSerial_number(Integer.parseInt(serial.getText().toString()));
                    stock.setName(name.getText().toString());
                    stock.setPhone(phone.getText().toString());
                    stock.setAddress(address.getText().toString());
                    stock.setType(sp.getString("Type", " "));

                    stock.setBag_count(Integer.parseInt(bag_count.getText().toString()));
                    stock.setViss_count(Float.parseFloat(viss_count.getText().toString()));

                    int id = group.getCheckedRadioButtonId();
                    rb = view.findViewById(id);
                    stock.setSize(rb.getText().toString());

                    stock.setDate(date.getText().toString());

                    SubmitService service = new SubmitService(getContext());
                    service.InsertBag(stock);

                    serial.setText("");
                    name.setText("");
                    phone.setText("");
                    address.setText("");
                    bag_count.setText("");
                    viss_count.setText("");

                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                serial.setText("");
                name.setText("");
                phone.setText("");
                address.setText("");
                bag_count.setText("");
                viss_count.setText("");
            }
        });


        return view;
    }

    private boolean isValidate(){
        boolean valid = true;
        if(serial.getText().toString().length()==0){
            serial.setError(getResources().getString(R.string.error_serial));
            valid=false;
        }
        if(name.getText().toString().length()==0){
           name.setError(getResources().getString(R.string.error_name));
            valid=false;
        }
        if(phone.getText().toString().length()==0){
            phone.setError(getResources().getString(R.string.error_phone));
            valid=false;
        }
        if(address.getText().toString().length()==0){
            address.setError(getResources().getString(R.string.error_address));
            valid=false;
        }
        if(bag_count.getText().toString().length()==0){
            bag_count.setError(getResources().getString(R.string.error_bag_count));
            valid=false;
        }
        if(viss_count.getText().toString().length()==0){
           viss_count.setError(getResources().getString(R.string.error_viss_count));
            valid=false;
        }

        return valid;
    }


}
