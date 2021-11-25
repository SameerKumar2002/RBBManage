package com.fusionlab.rbbmanage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navi;


         public MainActivity(){

         }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        navi = findViewById(R.id.navi_drawer);
        toggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        // getActionBar().setTitle("Home");
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            replaceFragment(new Input_SellFragment(),true);
        }

        navi.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id){

                    case R.id.id_sell :
                        getSupportActionBar().setTitle(getResources().getString(R.string.sell_t));
                        replaceFragment(new Input_SellFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.id_submit :
                        getSupportActionBar().setTitle(getResources().getString(R.string.import_t));
                        replaceFragment(new Input_submitFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                        //customer

                    case R.id.id_import_black :
                        getSupportActionBar().setTitle(getResources().getString(R.string.imported_t)+" "+getResources().getString(R.string.black_t));
                        replaceFragment(new Recycler_Black_Bean_SubmitFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.id_import_green :
                        getSupportActionBar().setTitle(getResources().getString(R.string.imported_t)+" "+getResources().getString(R.string.green_t));
                        replaceFragment(new Recycler_Green_Bean_SubmitFragment(),true);
                        drawerLayout.closeDrawers();
                        break;


                    case R.id.id_sold_black:
                        getSupportActionBar().setTitle(getResources().getString(R.string.sold_t)+" "+getResources().getString(R.string.black_t));
                        replaceFragment(new Recycler_Black_Bean_SellFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.id_sold_green:
                        getSupportActionBar().setTitle(getResources().getString(R.string.sold_t)+" "+getResources().getString(R.string.green_t));
                        replaceFragment(new Recycler_Green_Bean_SellFragment(),true);
                        drawerLayout.closeDrawers();
                        break;


                        //Customer
                        //User

                    case R.id.profit_calculator_green :
                        getSupportActionBar().setTitle(getResources().getString(R.string.calculator_t)+" "+getResources().getString(R.string.green_t));
                        replaceFragment(new Profit_Calculater_GreenFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.profit_calculator_black :
                        getSupportActionBar().setTitle(getResources().getString(R.string.calculator_t)+" "+getResources().getString(R.string.green_t));
                        replaceFragment(new Profit_Calculator_BlackFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.id_reports:
                        getSupportActionBar().setTitle(getResources().getString(R.string.report_t));
                        replaceFragment(new ReportFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.user_sold_green :
                        getSupportActionBar().setTitle(getResources().getString(R.string.user_sold_t));
                        replaceFragment(new Recycler_Green_Bean_User_SoldFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.user_sold_black :
                        getSupportActionBar().setTitle(getResources().getString(R.string.user_sold_t));
                        replaceFragment(new Recycler_Black_Bean_User_SoldFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                    case R.id.report_home_green:
                        getSupportActionBar().setTitle(getResources().getString(R.string.home_t)+" "+getResources().getString(R.string.green_t));
                        replaceFragment(new Report_HomeFragment(),true);
                        drawerLayout.closeDrawers();
                        break;

                        //User
                    case R.id.report_home_black:
                        getSupportActionBar().setTitle(getResources().getString(R.string.home_t)+" "+getResources().getString(R.string.black_t));
                        replaceFragment(new Report_Home_BlackFragment(),true);
                        drawerLayout.closeDrawers();
                }
                return true;
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(Fragment fragment,boolean stack){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(stack)
        transaction.addToBackStack(fragment.getTag());
        transaction.replace(R.id.to_replace,fragment);
        transaction.commit();

    }


}
