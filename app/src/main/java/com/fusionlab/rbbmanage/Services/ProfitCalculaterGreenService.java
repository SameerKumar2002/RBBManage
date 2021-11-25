package com.fusionlab.rbbmanage.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;
import com.fusionlab.rbbmanage.dto.ReportProfit;
import com.fusionlab.rbbmanage.dto.User_StockInfo_Sold;

import java.text.DecimalFormat;

public class ProfitCalculaterGreenService {

    Context context;
    public ProfitCalculaterGreenService(Context context){
        this.context = context;
    }

    public ReportProfit getProfit(int user_total_bag,float user_total_viss,int user_price,String type){
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ReportProfit profit = new ReportProfit();
        float db_total_price=0,cal_profit,db_total_bag_count=0,
                difference_bag_count=0,difference_viss_count=0,user_total_price;

        float db_total_viss_count=0,db_add_viss_bag,user_add_viss_bag,difference_add_bag_viss,
                db_last_price=0,difference_price=0;

        int db_last_id=0;

        user_total_price =user_total_bag * user_price+(int)(user_total_viss*user_price/30);
        DecimalFormat format = new DecimalFormat("#.###");

        String x = format.format(user_total_bag+user_total_viss/30);

        String query = "SELECT * FROM (SELECT T.ID,sum(P.BAG_COUNT) AS TOTAL_BAG, sum(P.VISS_COUNT) AS TOTAL_VISS,T.PRICE,sum(P.TOTAL_PRICE) AS TOTAL_PRICE FROM (SELECT ID,BAG_COUNT AS BAG_COUNT,VISS_COUNT AS VISS_COUNT,T_DATE,TOTAL_PRICE AS TOTAL_PRICE,PRICE,T_DATE FROM SOLD_BAG_GREEN_BEAN ORDER BY datetime(T_DATE) ) AS T JOIN (SELECT ID,BAG_COUNT AS BAG_COUNT,VISS_COUNT AS VISS_COUNT,T_DATE,TOTAL_PRICE AS TOTAL_PRICE,PRICE,T_DATE FROM SOLD_BAG_GREEN_BEAN ORDER BY datetime(T_DATE)) AS P ON(T.ID>=P.ID) GROUP BY T.ID) AS S WHERE round(CAST(S.TOTAL_BAG+S.TOTAL_VISS/30 AS REAL),3) >= "+x+" ORDER BY S.ID LIMIT 1";
        String query2 ="SELECT * FROM (SELECT T.ID,sum(P.BAG_COUNT) AS TOTAL_BAG, sum(P.VISS_COUNT) AS TOTAL_VISS,T.PRICE,sum(P.TOTAL_PRICE) AS TOTAL_PRICE FROM (SELECT ID,BAG_COUNT AS BAG_COUNT,VISS_COUNT AS VISS_COUNT,T_DATE,TOTAL_PRICE AS TOTAL_PRICE,PRICE,T_DATE FROM SOLD_BAG_BLACK_BEAN ORDER BY datetime(T_DATE) ) AS T JOIN (SELECT ID,BAG_COUNT AS BAG_COUNT,VISS_COUNT AS VISS_COUNT,T_DATE,TOTAL_PRICE AS TOTAL_PRICE,PRICE,T_DATE FROM SOLD_BAG_BLACK_BEAN ORDER BY datetime(T_DATE)) AS P ON(T.ID>=P.ID) GROUP BY T.ID) AS S WHERE round(CAST(S.TOTAL_BAG+S.TOTAL_VISS/30 AS REAL),3) >= "+x+" ORDER BY S.ID LIMIT 1";


        Cursor cursor;
        if(type.equals("GREEN BEAN")) cursor= db.rawQuery(query,null);
        else cursor = db.rawQuery(query2,null);


        if(cursor.moveToFirst()){

             db_last_id = cursor.getInt(0);
             db_total_bag_count = cursor.getInt(1);
             db_total_viss_count= cursor.getFloat(2);
             db_last_price = cursor.getFloat(3);
             db_total_price = cursor.getFloat(4);

        }

        db_add_viss_bag = (float) ((float)db_total_bag_count+db_total_viss_count/30.0);
        user_add_viss_bag = (float) ((float)user_total_bag+user_total_viss/30.0);

        if(db_add_viss_bag >user_add_viss_bag){

            difference_add_bag_viss = db_add_viss_bag - user_add_viss_bag;

            difference_price =difference_add_bag_viss*db_last_price;
            db_total_price -=difference_price;

            difference_bag_count = (int)difference_add_bag_viss;
            difference_viss_count = (difference_add_bag_viss-difference_bag_count)*30;


        }
        cal_profit =  user_total_price - db_total_price ;


        if(cal_profit<0)
            profit.setProfit_type("LOSS");
        else
            profit.setProfit_type("Profit");

            profit.setTotal_loss(cal_profit);
            profit.setDb_total_price(db_total_price);
            profit.setUser_total_price(user_total_price);
            profit.setUser_avg_rate(db_total_price/user_add_viss_bag);
            profit.setDifference_bag_count(difference_bag_count);
            profit.setDifference_viss_count(difference_viss_count);
            profit.setLast_id( db_last_id);
            profit.setLast_price((int) db_last_price);

        String query3 = "SELECT sum(BAG_COUNT),sum(VISS_COUNT),sum(TOTAL_PRICE) FROM (SELECT * FROM SOLD_BAG_GREEN_BEAN WHERE ID>?)";
        String query4 = "SELECT sum(BAG_COUNT),sum(VISS_COUNT),sum(TOTAL_PRICE) FROM (SELECT * FROM SOLD_BAG_BLACK_BEAN WHERE ID>?)";

        if(type.equals("GREEN BEAN")) cursor= db.rawQuery(query3,new String[]{String.valueOf(db_last_id)});
        else cursor= db.rawQuery(query4,new String[]{String.valueOf(db_last_id)});

        if(cursor.moveToFirst()){

           profit.setRemain_total_bag(cursor.getInt(0)+difference_bag_count);
           profit.setRemain_total_viss(cursor.getFloat(1)+difference_viss_count);

           Toast.makeText(context,String.valueOf(difference_viss_count),Toast.LENGTH_LONG).show();
           if(profit.getRemain_total_bag()!=0 || profit.getRemain_total_viss()!=0)
                profit.setRemain_average_rate((float) ((cursor.getInt(2)+difference_price)/(profit.getRemain_total_bag()+profit.getRemain_total_viss()/30.0)));
           else
               profit.setRemain_average_rate(0);



        }

        return profit;
    }

    public void updateRemainInfo(int last_id,float last_price,int total_bag,float total_viss,String type){
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("BAG_COUNT",total_bag);
        values.put("VISS_COUNT",total_viss);
        values.put("PRICE",last_price);
        values.put("TOTAL_PRICE",(total_bag+(total_viss/30.0))*last_price);

        if(type.equals("GREEN BEAN")){
            db.delete("SOLD_BAG_GREEN_BEAN","ID<?",new String[]{String.valueOf(last_id)});
            db.update("SOLD_BAG_GREEN_BEAN",values,"ID=?",new String[]{String.valueOf(last_id)});

        }
        else{
            db.delete("SOLD_BAG_BLACK_BEAN","ID<?",new String[]{String.valueOf(last_id)});
            db.update("SOLD_BAG_BLACK_BEAN",values,"ID=?",new String[]{String.valueOf(last_id)});

        }

    }

    public void deleteRemainInfo(int last_id,String type){
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        if(type.equals("GREEN BEAN")) db.delete("SOLD_BAG_GREEN_BEAN","ID<=?",new String[]{String.valueOf(last_id)});
        else db.delete("SOLD_BAG_BLACK_BEAN","ID<=?",new String[]{String.valueOf(last_id)});
    }

    public void insertUserSold(User_StockInfo_Sold stock,String type){
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("BAG_COUNT",stock.getBag_count());
        values.put("DATE",stock.getDate());
        values.put("T_DATE",stock.getT_date());
        values.put("BUY_AVG",stock.getBuy_avg());
        values.put("USER_SOLD_PRICE",stock.getUser_sold_price());
        values.put("USER_SOLD_TOTAL_PRICE",stock.getUser_sold_total_price());
        values.put("PROFIT",stock.getProfit());

        if(type.equals("GREEN BEAN")) db.insert("USER_SOLD_GREEN_BEAN",null,values);
        else  db.insert("USER_SOLD_BLACK_BEAN",null,values);
    }
}
