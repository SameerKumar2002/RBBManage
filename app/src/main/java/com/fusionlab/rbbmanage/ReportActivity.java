package com.fusionlab.rbbmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.transition.Transition;
import android.view.MenuItem;
import android.view.SurfaceControl;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String name = getIntent().getExtras().getString("Report");

        if(name.equals("id_green_import_day")){

            getSupportActionBar().setTitle(getResources().getString(R.string.daily_t)+" "+getResources().getString(R.string.imported_t)+" (Green)");
            ReplaceFragment(new Report_Green_Import_DayFragment(),true);

        }else if(name.equals("id_green_import_month")){

            getSupportActionBar().setTitle(getResources().getString(R.string.monthly_t)+" "+getResources().getString(R.string.imported_t)+" (Green)");
            ReplaceFragment(new Report_Green_Import_MonthFragment(),true);

        }else if(name.equals("id_green_customer_sold_day")){

            getSupportActionBar().setTitle(getResources().getString(R.string.daily_t)+" "+getResources().getString(R.string.sold_t)+" (Green)");
            ReplaceFragment(new Report_Green_Customer_Sold_DayFragment(),true);

        }else if(name.equals("id_green_customer_sold_month")){

            getSupportActionBar().setTitle(getResources().getString(R.string.monthly_t)+" "+getResources().getString(R.string.sold_t)+" (Green)");
            ReplaceFragment(new Report_Green_Customer_Sold_MonthFragment(),true);

        }else if(name.equals("id_black_import_day")){

            getSupportActionBar().setTitle(getResources().getString(R.string.daily_t)+" "+getResources().getString(R.string.imported_t)+" (Black)");
            ReplaceFragment(new Report_Black_Import_DayFragment(),true);

        }else if(name.equals("id_black_import_month")){

            getSupportActionBar().setTitle(getResources().getString(R.string.monthly_t)+" "+getResources().getString(R.string.imported_t)+" (Black)");
            ReplaceFragment(new Report_Black_Import_MonthFragment(),true);

        }else if(name.equals("id_black_customer_sold_day")){

            getSupportActionBar().setTitle(getResources().getString(R.string.daily_t)+" "+getResources().getString(R.string.sold_t)+" (Black)");
            ReplaceFragment(new Report_Black_Customer_Sold_DayFragment(),true);

        }else if(name.equals("id_black_customer_sold_month")){

            getSupportActionBar().setTitle(getResources().getString(R.string.monthly_t)+" "+getResources().getString(R.string.sold_t)+" (Black)");
            ReplaceFragment(new Report_Black_Customer_Sold_MonthFragment(),true);

        }else if(name.equals("id_green_profit_day")){

            getSupportActionBar().setTitle(getResources().getString(R.string.daily_t)+" "+getResources().getString(R.string.profit_t)+" (Green)");
            ReplaceFragment(new Report_Income_Outcome_GreenFragment(),true);

        }else if(name.equals("id_green_profit_month")){

            getSupportActionBar().setTitle(getResources().getString(R.string.monthly_t)+" "+getResources().getString(R.string.profit_t)+" (Green)");
            ReplaceFragment(new Report_Month_Income_Outcome_GreenFragment(),true);

        }else if(name.equals("id_black_profit_day")){

            getSupportActionBar().setTitle(getResources().getString(R.string.daily_t)+" "+getResources().getString(R.string.profit_t)+" (Black)");
            ReplaceFragment(new Report_Income_Outcome_BlackFragment(),true);

        }else if(name.equals("id_black_profit_month")){

            getSupportActionBar().setTitle(getResources().getString(R.string.monthly_t)+" "+getResources().getString(R.string.profit_t)+" (Black)");
            ReplaceFragment(new Report_Month_Income_Outcome_BlackFragment(),true);

        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void ReplaceFragment(Fragment fragment,boolean stack){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(stack)
        transaction.addToBackStack(fragment.getTag());
        transaction.replace(R.id.id_frame,fragment);
        transaction.commit();

    }
}
