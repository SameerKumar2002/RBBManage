package com.fusionlab.rbbmanage.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;
import com.fusionlab.rbbmanage.dto.ReportSold;

import java.util.ArrayList;

public class ReportSoldBlackService {

    Context context;

    public ReportSoldBlackService(Context context){
        this.context = context;
    }

    public ArrayList<ReportSold> getEntries(String date,String type){
        ArrayList<ReportSold> entries = new ArrayList<>();
        ReportSold reportSold;

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();

        Cursor cursor;
        if(type.equals("GREEN BEAN"))
        cursor= db.rawQuery("SELECT ID,DATE,sum(BAG_COUNT),sum(VISS_COUNT),sum(TOTAL_PRICE),CAST(sum(TOTAL_PRICE)/(sum(BAG_COUNT)+sum(VISS_COUNT)/30.0) AS INTEGER) AS PRICE,T_DATE FROM(SELECT ID,DATE,SUM(BAG_COUNT) AS BAG_COUNT,SUM(VISS_COUNT) AS VISS_COUNT,SUM(TOTAL_PRICE) AS TOTAL_PRICE,T_DATE FROM SOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?) UNION " +
                "SELECT ID,DATE,SUM(BAG_COUNT) AS BAG_COUNT,SUM(VISS_COUNT) AS VISS_COUNT,SUM(TOTAL_PRICE) AS TOTAL_PRICE,T_DATE FROM DELETED_SOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?)) GROUP BY T_DATE ORDER BY datetime(T_DATE)",new String[]{date,date});

        else cursor= db.rawQuery("SELECT ID,DATE,sum(BAG_COUNT),sum(VISS_COUNT),sum(TOTAL_PRICE),CAST(sum(TOTAL_PRICE)/(sum(BAG_COUNT)+sum(VISS_COUNT)/30.0) AS INTEGER) AS PRICE,T_DATE FROM(SELECT ID,DATE,SUM(BAG_COUNT) AS BAG_COUNT,SUM(VISS_COUNT) AS VISS_COUNT,SUM(TOTAL_PRICE) AS TOTAL_PRICE,T_DATE FROM SOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?) UNION " +
                "SELECT ID,DATE,SUM(BAG_COUNT) AS BAG_COUNT,SUM(VISS_COUNT) AS VISS_COUNT,SUM(TOTAL_PRICE) AS TOTAL_PRICE,T_DATE FROM DELETED_SOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?)) GROUP BY T_DATE ORDER BY datetime(T_DATE)",new String[]{date,date});



        if(cursor.moveToFirst()){
            do{
                reportSold = new ReportSold();
                reportSold.setId(cursor.getInt(0));
                reportSold.setDate(cursor.getString(1));
                reportSold.setBag_total(cursor.getInt(2)+(int)(cursor.getFloat(3)/30));
                reportSold.setTotal_price(cursor.getInt(4));
                reportSold.setPrice((int)cursor.getFloat(5));
                entries.add(reportSold);

            }while (cursor.moveToNext());
        }

        return entries;
    }

    public ArrayList<String> getMonthYear(String type){
        ArrayList<String> month_years = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        Cursor cursor;
        if(type.equals("GREEN BEAN"))
        cursor= db.rawQuery("SELECT DISTINCT DATE1,T_DATE FROM(SELECT DISTINCT strftime('%m-%Y',T_DATE) AS DATE1,T_DATE FROM SOLD_BAG_GREEN_BEAN GROUP BY T_DATE  UNION SELECT DISTINCT strftime('%m-%Y',T_DATE) AS DATE1,T_DATE FROM DELETED_SOLD_BAG_GREEN_BEAN GROUP BY T_DATE) GROUP BY strftime('%m-%Y',T_DATE) ORDER BY datetime(T_DATE) DESC",null);

        else cursor= db.rawQuery("SELECT DISTINCT DATE1,T_DATE FROM(SELECT DISTINCT strftime('%m-%Y',T_DATE) AS DATE1,T_DATE FROM SOLD_BAG_BLACK_BEAN GROUP BY T_DATE  UNION SELECT DISTINCT strftime('%m-%Y',T_DATE) AS DATE1,T_DATE FROM DELETED_SOLD_BAG_BLACK_BEAN GROUP BY T_DATE) GROUP BY strftime('%m-%Y',T_DATE) ORDER BY datetime(T_DATE) DESC",null);

        if(cursor.moveToFirst()){
            do{
                month_years.add(cursor.getString(0).replace('-','/'));
            }while (cursor.moveToNext());
        }

        return month_years;
    }

    public ArrayList<String> getYear(String type){

        ArrayList<String> years = new ArrayList<>();

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        Cursor cursor;
        if(type.equals("GREEN BEAN"))
        cursor= db.rawQuery("SELECT DISTINCT * FROM(SELECT DISTINCT strftime('%Y',T_DATE) AS DATE1 FROM SOLD_BAG_GREEN_BEAN GROUP BY T_DATE  UNION " +
                               "SELECT DISTINCT strftime('%Y',T_DATE) AS DATE1 FROM DELETED_SOLD_BAG_GREEN_BEAN GROUP BY T_DATE) ORDER BY DATE1 DESC",null);

        else   cursor= db.rawQuery("SELECT DISTINCT * FROM(SELECT DISTINCT strftime('%Y',T_DATE) AS DATE1 FROM SOLD_BAG_BLACK_BEAN GROUP BY T_DATE  UNION " +
                "SELECT DISTINCT strftime('%Y',T_DATE) AS DATE1 FROM DELETED_SOLD_BAG_BLACK_BEAN GROUP BY T_DATE) ORDER BY DATE1 DESC",null);

        if(cursor.moveToFirst()){
            do{
                years.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }

        return years;
    }

    public ReportSold[] getEntriesByMonth(String date,String type){

        ReportSold[] list = new ReportSold[12];
        ReportSold reportSold = new ReportSold();

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        Cursor cursor;
        if(type.equals("BLACK BEAN"))
        cursor= db.rawQuery("SELECT ID,strftime('%m',T_DATE),SUM(BAG_COUNT),SUM(VISS_COUNT),SUM(TOTAL_PRICE),SUM(PRICE)/COUNT(PRICE),strftime('%m-%Y',T_DATE) " +
                "FROM SOLD_BAG_BLACK_BEAN GROUP BY strftime('%m-%Y',T_DATE) " +
                "HAVING strftime('%Y',T_DATE) = strftime('%Y',?)" +
                " ORDER BY datetime(T_DATE)",new String[]{date});

        else cursor= db.rawQuery("SELECT ID,strftime('%m',T_DATE),SUM(BAG_COUNT),SUM(VISS_COUNT),SUM(TOTAL_PRICE),SUM(PRICE)/COUNT(PRICE),strftime('%m-%Y',T_DATE) " +
                "FROM SOLD_BAG_GREEN_BEAN GROUP BY strftime('%m-%Y',T_DATE) " +
                "HAVING strftime('%Y',T_DATE) = strftime('%Y',?)" +
                " ORDER BY datetime(T_DATE)",new String[]{date});
        if(cursor.moveToFirst()){
            do{
                reportSold = new ReportSold();
                reportSold.setId(cursor.getInt(0));
                reportSold.setDate(cursor.getString(1));
                reportSold.setBag_total(cursor.getInt(2)+(int)(cursor.getFloat(3)/30));
                reportSold.setTotal_price(cursor.getInt(4));
                reportSold.setPrice((int)cursor.getFloat(5));
                list[Integer.parseInt(reportSold.getDate())-1]=reportSold;

            }while (cursor.moveToNext());
        }

        return list;
    }
}
