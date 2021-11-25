package com.fusionlab.rbbmanage.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;
import com.fusionlab.rbbmanage.dto.StockInfo_Sold;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SellService {

    Context context;

    public SellService(Context context) {
        this.context = context;
    }

    public void InsertBag(StockInfo_Sold stock)  {

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("BAG_COUNT",stock.getBag_count());
        values.put("VISS_COUNT",stock.getViss_count());
        values.put("DATE",stock.getDate());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        try {
             date = format.parse(stock.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String t_date = format2.format(date);

        values.put("T_DATE",t_date);
        values.put("SIZE",stock.getSize());
        values.put("PRICE",stock.getPrice());
        values.put("TOTAL_PRICE",stock.getTotal_price());

        if(stock.getType().equals("Black Bean")) db.insert("SOLD_BAG_BLACK_BEAN",null,values);
        else  db.insert("SOLD_BAG_GREEN_BEAN",null,values);
    }

    public ArrayList<StockInfo_Sold> getAllBag(String type) {

        ArrayList<StockInfo_Sold> all_stockInfo = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();
        StockInfo_Sold stock ;


        if (type.equals("BLACK BEAN")) {

           Cursor cursor = db.rawQuery("SELECT * FROM SOLD_BAG_BLACK_BEAN", null);
            if (cursor.moveToFirst()) {
                do {
                    stock = new StockInfo_Sold();
                    stock.setId(cursor.getInt(0));
                    stock.setBag_count(cursor.getInt(1));
                    stock.setViss_count(cursor.getFloat(2));
                    stock.setDate(cursor.getString(3));
                    stock.setSize(cursor.getString(5));
                    stock.setPrice(cursor.getInt(6));
                    stock.setTotal_price(cursor.getInt(7));
                    stock.setType("BLACK BEAN");
                    all_stockInfo.add(stock);

                } while (cursor.moveToNext());
            }
        } else {

          Cursor  cursor = db.rawQuery("SELECT * FROM SOLD_BAG_GREEN_BEAN", null);
            if (cursor.moveToFirst()) {
            do {
                stock = new StockInfo_Sold();
                stock.setId(cursor.getInt(0));
                stock.setBag_count(cursor.getInt(1));
                stock.setViss_count(cursor.getFloat(2));
                stock.setDate(cursor.getString(3));
                stock.setSize(cursor.getString(5));
                stock.setPrice(cursor.getInt(6));
                stock.setTotal_price(cursor.getInt(7));
                stock.setType("GREEN BEAN");
                all_stockInfo.add(stock);

            } while (cursor.moveToNext());
        }
    }
        return all_stockInfo;
    }

    public void DeleteBag(StockInfo_Sold stock,String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID",stock.getId());
        values.put("BAG_COUNT",stock.getBag_count());
        values.put("VISS_COUNT",stock.getViss_count());
        values.put("DATE",stock.getDate());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        try {
            date = format.parse(stock.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String t_date = format2.format(date);

        values.put("T_DATE",t_date);
        values.put("PRICE",stock.getPrice());
        values.put("TOTAL_PRICE",stock.getTotal_price());

        if(type.equals("BLACK BEAN")){
            db.delete("SOLD_BAG_BLACK_BEAN","ID=?",new String[]{String.valueOf(stock.getId())});
            db.insert("DELETED_SOLD_BAG_BLACK_BEAN",null,values);
        }
        else{
            db.delete("SOLD_BAG_GREEN_BEAN","ID=?",new String[]{String.valueOf(stock.getId())});
            db.insert("DELETED_SOLD_BAG_GREEN_BEAN",null,values);
        }
    }

    public void UpdateBag(StockInfo_Sold stock){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("BAG_COUNT",stock.getBag_count());
        values.put("VISS_COUNT",stock.getViss_count());
        values.put("DATE",stock.getDate());

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date=null;
        try {
            date = format.parse(stock.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        String t_date = format2.format(date);

        values.put("T_DATE",t_date);
        values.put("SIZE",stock.getSize());
        values.put("PRICE",stock.getPrice());
        values.put("TOTAL_PRICE",stock.getTotal_price());

        if(stock.getType().equals("BLACK BEAN")) db.update("SOLD_BAG_BLACK_BEAN",values,"ID=?",new String[]{String.valueOf(stock.getId())});
        else  db.update("SOLD_BAG_GREEN_BEAN",values,"ID=?",new String[]{String.valueOf(stock.getId())});

    }
}
