package com.fusionlab.rbbmanage.Services;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;
import com.fusionlab.rbbmanage.dto.StockInfo;
import com.fusionlab.rbbmanage.dto.SubmitSellButtonImplement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SubmitService {
    Context context;

    public SubmitService(Context context) {
        this.context = context;
    }

    public void InsertBag(StockInfo stock)  {

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();



        try {

            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stock.getDate());
            String s =new SimpleDateFormat("yyyy-MM-dd").format(date);

            ContentValues values = new ContentValues();

            values.put("NAME",stock.getName());
            values.put("PHONE_NUMBER",stock.getPhone());
            values.put("ADDRESS",stock.getAddress());
            values.put("BAG_COUNT",stock.getBag_count());
            values.put("VISS_COUNT",stock.getViss_count());
            values.put("DATE",stock.getDate());
            values.put("T_DATE",s);
            values.put("SIZE",stock.getSize());
            values.put("SERIAL",stock.getSerial_number());

            if(stock.getType().equals("Black Bean")) db.insert("UNSOLD_BAG_BLACK_BEAN",null,values);
            else  db.insert("UNSOLD_BAG_GREEN_BEAN",null,values);

        } catch (Exception e) {
            Toast.makeText(context,"catch",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }

    public ArrayList<StockInfo> getAllBag(String type){

        ArrayList<StockInfo> all_stockInfo = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();
        StockInfo stock;


        if(type.equals("BLACK BEAN")) {

            Cursor cursor = db.rawQuery("SELECT * FROM UNSOLD_BAG_BLACK_BEAN",null);
            if (cursor.moveToFirst()) {
                do {
                    stock = new StockInfo();
                    stock.setId(cursor.getInt(0));
                    stock.setSerial_number(cursor.getInt(1));
                    stock.setName(cursor.getString(2));
                    stock.setPhone(cursor.getString(3));
                    stock.setAddress(cursor.getString(4));
                    stock.setDate(cursor.getString(5));
                    stock.setBag_count(cursor.getInt(7));
                    stock.setViss_count(cursor.getFloat(8));
                    stock.setSize(cursor.getString(9));
                    stock.setType("BLACK BEAN");
                    all_stockInfo.add(stock);

                } while (cursor.moveToNext());
            }
        }
        else {
           Cursor cursor = db.rawQuery("SELECT * FROM  UNSOLD_BAG_GREEN_BEAN", null);
            if (cursor.moveToFirst()) {
                do {
                    stock = new StockInfo();
                    stock.setId(cursor.getInt(0));
                    stock.setSerial_number(cursor.getInt(1));
                    stock.setName(cursor.getString(2));
                    stock.setPhone(cursor.getString(3));
                    stock.setAddress(cursor.getString(4));
                    stock.setDate(cursor.getString(5));
                    stock.setBag_count(cursor.getInt(7));
                    stock.setViss_count(cursor.getFloat(8));
                    stock.setSize(cursor.getString(9));
                    stock.setType("GREEN BEAN");
                    all_stockInfo.add(stock);

                } while (cursor.moveToNext());
            }
        }
        return all_stockInfo;
    }

    public void DeleteBag(StockInfo stock,String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("ID",stock.getId());
        values.put("SERIAL",stock.getSerial_number());
        values.put("NAME",stock.getName());
        values.put("PHONE_NUMBER",stock.getPhone());
        values.put("ADDRESS",stock.getAddress());
        values.put("DATE",stock.getDate());

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(stock.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String s =new SimpleDateFormat("yyyy-MM-dd").format(date);

        values.put("T_DATE",s);
        values.put("BAG_COUNT",stock.getBag_count());
        values.put("VISS_COUNT",stock.getViss_count());


        if(type.equals("BLACK BEAN")){
            db.delete("UNSOLD_BAG_BLACK_BEAN","ID=?",new String[]{String.valueOf(stock.getId())});
            db.insert("DELETED_UNSOLD_BAG_BLACK_BEAN",null,values);
        }
        else{
            db.delete("UNSOLD_BAG_GREEN_BEAN","ID=?",new String[]{String.valueOf(stock.getId())});
            db.insert("DELETED_UNSOLD_BAG_GREEN_BEAN",null,values);
        }

    }

    public void submitSellButtonImplement(SubmitSellButtonImplement stock)  {
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("BAG_COUNT",stock.getUnsold_bag_count()-stock.getSold_bag_count());
        values.put("VISS_COUNT",stock.getUnsold_viss_count()-stock.getSold_viss_count());


        if (stock.getUnsold_bag_count() - stock.getSold_bag_count() == 0 || stock.getUnsold_viss_count()-stock.getSold_viss_count()== 0.0){

            if(stock.getType().equals("GREEN BEAN")) db.delete("UNSOLD_BAG_GREEN_BEAN","Id=?",new String[]{String.valueOf(stock.getId())});
            else db.delete("UNSOLD_BAG_BLACK_BEAN","Id=?",new String[]{String.valueOf(stock.getId())});

        }else{

            if(stock.getType().equals("GREEN BEAN")) db.update("UNSOLD_BAG_GREEN_BEAN",values,"Id=?",new String[]{String.valueOf(stock.getId())});
            else db.update("UNSOLD_BAG_BLACK_BEAN",values,"Id=?",new String[]{String.valueOf(stock.getId())});

        }


        ContentValues values2 = new ContentValues();
        values2.put("BAG_COUNT",stock.getSold_bag_count());
        values2.put("VISS_COUNT",stock.getSold_viss_count());
        values2.put("DATE",stock.getDate());

        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(stock.getDate());
            String s =new SimpleDateFormat("yyyy-MM-dd").format(date);
            values2.put("T_DATE",s);
            values2.put("SIZE","null");
            values2.put("PRICE",stock.getPrice());
            values2.put("TOTAL_PRICE",stock.getPrice()*(stock.getSold_bag_count()+stock.getSold_viss_count()/30.0));

            if(stock.getType().equals("GREEN BEAN")) db.insert("SOLD_BAG_GREEN_BEAN",null,values2);
            else  db.insert("SOLD_BAG_BLACK_BEAN",null,values2);


        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    public void SubmitUpdate(StockInfo stock){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        try {

            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(stock.getDate());
            String s =new SimpleDateFormat("yyyy-MM-dd").format(date);

            ContentValues values = new ContentValues();

            values.put("NAME",stock.getName());
            values.put("PHONE_NUMBER",stock.getPhone());
            values.put("ADDRESS",stock.getAddress());
            values.put("BAG_COUNT",stock.getBag_count());
            values.put("VISS_COUNT",stock.getViss_count());
            values.put("DATE",stock.getDate());
            values.put("T_DATE",s);
            values.put("SIZE",stock.getSize());
            values.put("SERIAL",stock.getSerial_number());

            if(stock.getType().equals("Black Bean")) db.update("UNSOLD_BAG_BLACK_BEAN",values,"ID=?",new String[]{String.valueOf(stock.getId())});
            else  db.update("UNSOLD_BAG_GREEN_BEAN",values,"ID=?",new String[]{String.valueOf(stock.getId())});

        } catch (Exception e) {
          //  Toast.makeText(context,"catch",Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }
}
