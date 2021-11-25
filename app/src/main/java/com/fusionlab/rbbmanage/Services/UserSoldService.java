package com.fusionlab.rbbmanage.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;
import com.fusionlab.rbbmanage.dto.User_StockInfo_Sold;

import java.util.ArrayList;

public class UserSoldService {

    Context context;

    public UserSoldService(Context context) {
        this.context = context;
    }

    public ArrayList<User_StockInfo_Sold> getStocks(String type){

        DataBaseHelper2 helper2= new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        ArrayList<User_StockInfo_Sold> stocks_info = new ArrayList<>();
        User_StockInfo_Sold stock ;

        Cursor cursor;

        if(type.equals("GREEN BEAN")) cursor = db.rawQuery("SELECT * FROM USER_SOLD_GREEN_BEAN",null);
        else cursor= db.rawQuery("SELECT * FROM USER_SOLD_BLACK_BEAN",null);

        if(cursor.moveToFirst()){
            do{
                stock = new User_StockInfo_Sold();
                stock.setId(cursor.getInt(0));
                stock.setBag_count(cursor.getFloat(1));
                stock.setDate(cursor.getString(2));
                stock.setT_date(cursor.getString(3));
                stock.setBuy_avg(cursor.getInt(4));
                stock.setUser_sold_price(cursor.getInt(5));
                stock.setUser_sold_total_price(cursor.getInt(6));
                stock.setProfit(cursor.getInt(7));

                stocks_info.add(stock);

            }while (cursor.moveToNext());
        }


        return stocks_info;
    }

    public void deleteUserSoldBag(int id,String type){
        DataBaseHelper2 helper2= new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();

        if(type.equals("GREEN BEAN")){
           db.delete("USER_SOLD_GREEN_BEAN","ID=?",new String[]{String.valueOf(id)});
        }else {
            db.delete("USER_SOLD_BLACK_BEAN","ID=?",new String[]{String.valueOf(id)});
        }

    }

}
