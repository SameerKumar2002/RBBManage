package com.fusionlab.rbbmanage;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

import com.fusionlab.rbbmanage.Services.SellService;
import com.fusionlab.rbbmanage.dto.StockInfo_Sold;

import java.util.Calendar;


public class Input_SellFragment extends Fragment {

    private EditText bag_count,viss_count,date,price;
    private RadioGroup group;
    private RadioButton radioButton;
    private ImageButton calendar;
    private Button cancel,save;
    private DatePickerDialog dialog;
    private Dialog anim_dialog;

    private Button green_bean,black_bean;

    public Input_SellFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_input__sell, container, false);



        green_bean = view.findViewById(R.id.id_input_green_bean_sell);
        black_bean = view.findViewById(R.id.id_input_black_bean_sell);
        bag_count = view.findViewById(R.id.id_sell_bag_count);
        viss_count = view.findViewById(R.id.id_sell_viss_count);
        group = view.findViewById(R.id.id_size_radio_group);
        date = view.findViewById(R.id.id_sell_date);
        price = view.findViewById(R.id.id_sell_price);
        cancel = view.findViewById(R.id.id_sell_cancel);
        save = view.findViewById(R.id.id_sell_save);
        calendar =  view.findViewById(R.id.id_sell_image_btn);


        Calendar calendar1 = Calendar.getInstance();
        final int day = calendar1.get(Calendar.DAY_OF_MONTH);
        final int month = calendar1.get(Calendar.MONTH);
        final int year = calendar1.get(Calendar.YEAR);
        date.setText(day+"/"+(month+1)+"/"+year);

        calendar.setOnClickListener(new View.OnClickListener() {
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
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateInput()) {

                    final StockInfo_Sold stock = new StockInfo_Sold();
                    stock.setType(sp.getString("Type", " "));

                    stock.setBag_count(Integer.parseInt(bag_count.getText().toString()));
                    stock.setViss_count(Float.parseFloat(viss_count.getText().toString()));
                    stock.setPrice(Integer.parseInt(price.getText().toString()));
                    stock.setTotal_price((int) (stock.getPrice() * stock.getBag_count() + stock.getPrice() * stock.getViss_count() / 30));

                    int id = group.getCheckedRadioButtonId();
                    radioButton = view.findViewById(id);
                    stock.setSize(radioButton.getText().toString());

                    stock.setDate(date.getText().toString());

                    anim_dialog = new Dialog(getContext());
                    anim_dialog.setContentView(R.layout.sell_dialog);
                    anim_dialog.getWindow().setBackgroundDrawable(getContext().getDrawable(R.drawable.dialog_background));
                    anim_dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    anim_dialog.setCancelable(false);
                    anim_dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;

                    Button save = anim_dialog.findViewById(R.id.id_save);
                    Button cancel = anim_dialog.findViewById(R.id.id_cancel);
                    TextView d_type = anim_dialog.findViewById(R.id.id_type);
                    TextView d_size = anim_dialog.findViewById(R.id.id_size);
                    TextView d_amount = anim_dialog.findViewById(R.id.id_amount);
                    TextView d_price = anim_dialog.findViewById(R.id.id_price);
                    TextView d_total_price = anim_dialog.findViewById(R.id.id_total_price);
                    TextView d_date = anim_dialog.findViewById(R.id.id_date);

                    d_type.setText(stock.getType());
                    d_size.setText(stock.getSize());
                    d_amount.setText(stock.getBag_count() + " Bag " + stock.getViss_count() + " Viss");
                    d_price.setText(String.valueOf(stock.getPrice()));
                    d_date.setText(stock.getDate());
                    d_total_price.setText(String.valueOf(stock.getPrice() * stock.getBag_count() + stock.getPrice() * stock.getViss_count() / 30));

                    save.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anim_dialog.dismiss();
                            SellService service = new SellService(getContext());
                            service.InsertBag(stock);

                            bag_count.setText("");
                            viss_count.setText("");
                            group.clearCheck();
                            price.setText("");
                            // date.setText(final_date[0]);

                        }
                    });

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            anim_dialog.dismiss();
                        }
                    });

                    anim_dialog.show();

                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bag_count.setText("");
                viss_count.setText("");
                group.clearCheck();
                price.setText("");
               // date.setText(final_date[0]);
            }
        });

        return view;
    }

    private boolean validateInput(){
        boolean valid = true;
        if(bag_count.getText().toString().length()==0){
            bag_count.setError(getResources().getString(R.string.error_bag_count));
            valid = false;
        }
        if(viss_count.getText().toString().length()==0){
            viss_count.setError(getResources().getString(R.string.error_viss_count));
            valid = false;
        }
        if(price.getText().toString().length()==0){
            price.setError(getResources().getString(R.string.error_price));
            valid = false;
        }
        return valid;
    }

}
