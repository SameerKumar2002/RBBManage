package com.fusionlab.rbbmanage.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;
import com.fusionlab.rbbmanage.R;
import com.fusionlab.rbbmanage.dto.ReportImport;
import com.fusionlab.rbbmanage.dto.ReportImportTable;
import com.github.mikephil.charting.data.BarEntry;

import java.sql.Date;
import java.util.ArrayList;

public class ReportImportService  {
    Context context;

    public ReportImportService(Context context) {
        this.context = context;
    }

    public ArrayList<ReportImport> barEntries(String str_date,String type){

        ArrayList<ReportImport> entries = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();
        ReportImport values;
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
            cursor = db.rawQuery("SELECT T_DATE,sum(BAG_COUNT),sum(VISS_COUNT) FROM(SELECT T_DATE,sum(BAG_COUNT) AS BAG_COUNT,sum(VISS_COUNT) AS VISS_COUNT FROM UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?) UNION " +
                    "SELECT T_DATE,sum(BAG_COUNT) AS BAG_COUNT,sum(VISS_COUNT)  AS VISS_COUNT FROM DELETED_UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?)) GROUP BY T_DATE",new String[]{str_date,str_date});

        else cursor = db.rawQuery("SELECT T_DATE,sum(BAG_COUNT),sum(VISS_COUNT) FROM" +
                    " UNSOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?)" +
                    " ORDER BY datetime(T_DATE)",new String[]{str_date});

        if(cursor.moveToFirst())
            do{

                values = new ReportImport();
                String s = cursor.getString(0);
                values.setDate(s);
                values.setTotal(cursor.getInt(1)+(int)cursor.getFloat(2)/30);
                entries.add(values);

            }while (cursor.moveToNext());

        return entries;
    }

    public ArrayList<ReportImportTable> getTableData(String str_date,String type){

        ArrayList<ReportImportTable> data = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        ReportImportTable values;
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
         cursor = db.rawQuery("SELECT DATE,sum(BAG_COUNT),sum(VISS_COUNT),sum(NUM),T_DATE FROM(SELECT DATE,sum(BAG_COUNT) AS BAG_COUNT,sum(VISS_COUNT) AS VISS_COUNT,count(DATE) AS NUM,T_DATE FROM UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?)  UNION " +
                 "SELECT DATE,sum(BAG_COUNT) AS BAG_COUNT,sum(VISS_COUNT) AS VISS_COUNT,count(DATE) AS NUM,T_DATE FROM DELETED_UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?)) GROUP BY T_DATE",new String[]{str_date,str_date});

        else  cursor = db.rawQuery("SELECT DATE,sum(BAG_COUNT),sum(VISS_COUNT),count(DATE),T_DATE " +
                "FROM UNSOLD_BAG_BLACK_BEAN GROUP BY T_DATE " +
                "HAVING strftime('%m-%Y',T_DATE) = strftime('%m-%Y',?) ORDER BY datetime(T_DATE)",new String[]{str_date});

        if(cursor.moveToFirst())
            do{

               values = new ReportImportTable();
               values.setDate(cursor.getString(0));
               values.setBag_count((float)cursor.getInt(1)+cursor.getFloat(2)/30);
               values.setNo_of_people(cursor.getInt(3));
               data.add(values);

            }while (cursor.moveToNext());
        return data;
    }

    public ArrayList<String> getMonthYear(String type){
        ArrayList<String> month_years = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getWritableDatabase();

        Cursor cursor;

        if(type.equals("GREEN BEAN"))
         cursor = db.rawQuery("SELECT DISTINCT DATE1,T_DATE FROM(SELECT DISTINCT strftime('%m-%Y',T_DATE) AS DATE1,T_DATE FROM UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE  UNION " +
                 "SELECT DISTINCT strftime('%m-%Y',T_DATE) AS DATE1,T_DATE FROM DELETED_UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE) GROUP BY strftime('%m-%Y',T_DATE) ORDER BY datetime(T_DATE) DESC",null);

        else  cursor = db.rawQuery("SELECT DISTINCT strftime('%m-%Y',T_DATE) " +
                "FROM UNSOLD_BAG_BLACK_BEAN GROUP BY T_DATE ORDER BY datetime(T_DATE)",null);


        if(cursor.moveToFirst()){
        do{
            month_years.add(cursor.getString(0).replace('-','/'));
        }while (cursor.moveToNext());
        }

        return month_years;
    }

    public ReportImport[] getTotalBagByMonth(String date,String type){

        ReportImport[] entries =new ReportImport[12];
        ReportImport report ;
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();

        Cursor cursor;
         if(type.equals("GREEN BEAN"))
         cursor = db.rawQuery("SELECT * FROM (SELECT ID,SUM(BAG_COUNT) AS BAG_COUNT,SUM(VISS_COUNT) AS VISS_COUNT ,strftime('%m',T_DATE) AS MONTH,T_DATE FROM UNSOLD_BAG_GREEN_BEAN GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_date)=strftime('%Y',?)  UNION " +
                 "SELECT ID,SUM(BAG_COUNT) AS BAG_COUNT,SUM(VISS_COUNT) AS VISS_COUNT,strftime('%m',T_DATE) AS MONTH,T_DATE FROM DELETED_UNSOLD_BAG_GREEN_BEAN GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_date)=strftime('%Y',?)) ORDER BY datetime(T_DATE) ",new String[]{date,date});

         else  cursor = db.rawQuery("SELECT ID,SUM(BAG_COUNT),SUM(VISS_COUNT),strftime('%m',T_DATE),T_DATE " +
                 "FROM UNSOLD_BAG_BLACK_BEAN GROUP BY strftime('%Y-%m',T_DATE)" +
                 " HAVING strftime('%Y',T_date)=strftime('%Y',?) ORDER BY datetime(T_DATE)",new String[]{date});

        if(cursor.moveToFirst()){
            do{

                report = new ReportImport();
                report.setId(cursor.getInt(0));
                report.setTotal(cursor.getInt(1)+(int)cursor.getFloat(2)/30);
                int month = Integer.parseInt(cursor.getString(3));
                report.setDate(String.valueOf(month));
                entries[month-1]= report;

            }while (cursor.moveToNext());
        }
        return entries;
    }

    public ArrayList<String> getYear(String type){

        ArrayList<String> years = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
        cursor = db.rawQuery("SELECT DISTINCT * FROM(SELECT DISTINCT strftime('%Y',T_DATE) AS DATE1 FROM UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE  UNION " +
                "SELECT DISTINCT strftime('%Y',T_DATE) AS DATE1 FROM DELETED_UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE) ORDER BY DATE1 DESC",null);

        else cursor = db.rawQuery("SELECT DISTINCT strftime('%Y',T_DATE) " +
                "FROM UNSOLD_BAG_BLACK_BEAN GROUP BY T_DATE ORDER BY datetime(T_DATE)",null);

        if(cursor.moveToFirst()){
            do{
                years.add(cursor.getString(0));
            }while (cursor.moveToNext());
        }


        return years;
    }
}
