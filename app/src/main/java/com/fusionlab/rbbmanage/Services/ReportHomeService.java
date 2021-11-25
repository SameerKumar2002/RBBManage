package com.fusionlab.rbbmanage.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;

public class ReportHomeService {
    Context context;
    public ReportHomeService(Context context){
        this.context = context;
    }

    public int getCurrentImport(String type){
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0 FROM UNSOLD_BAG_GREEN_BEAN",null);
        else
           cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0 FROM UNSOLD_BAG_BLACK_BEAN",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);
        return 0;
    }

    public int getCurrentBuy(String type){
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0 FROM SOLD_BAG_GREEN_BEAN",null);
        else
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0 FROM SOLD_BAG_BLACK_BEAN",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);
        return 0;
    }

    public int getCurrentAverage(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(TOTAL_PRICE)/(sum(BAG_COUNT)+sum(VISS_COUNT)/30.0) FROM SOLD_BAG_GREEN_BEAN",null);
        else
            cursor= db.rawQuery("SELECT sum(TOTAL_PRICE)/(sum(BAG_COUNT)+sum(VISS_COUNT)/30.0) FROM SOLD_BAG_BLACK_BEAN",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);
        return 0;
    }

    public int getTomorrowImport(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);
        else
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM UNSOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }

    public int getTomorrowBuy(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM SOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);
        else
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM SOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }

    public int getTomorrowSold(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT),T_DATE FROM USER_SOLD_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);
        else
            cursor= db.rawQuery("SELECT sum(BAG_COUNT),T_DATE FROM USER_SOLD_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }

    public int getTodayImport(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM UNSOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);
        else
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM UNSOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }

    public int getTodayBuy(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;
        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM SOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);
        else
            cursor= db.rawQuery("SELECT sum(BAG_COUNT)+sum(VISS_COUNT)/30.0,T_DATE FROM SOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }

    public int getTodaySold(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(BAG_COUNT),T_DATE FROM USER_SOLD_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);
        else
            cursor= db.rawQuery("SELECT sum(BAG_COUNT),T_DATE FROM USER_SOLD_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }

    public int getTomorrowIncome(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(USER_SOLD_TOTAL_PRICE) FROM USER_SOLD_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);
        else
            cursor= db.rawQuery("SELECT sum(USER_SOLD_TOTAL_PRICE) FROM USER_SOLD_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);

        if(cursor.moveToFirst()) return cursor.getInt(0);
        return 0;

    }

    public int getTomorrowOutcome(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(TOTAL_PRICE) FROM SOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);
        else
            cursor= db.rawQuery("SELECT sum(TOTAL_PRICE) FROM SOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now','-1 day')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }

    public int getTodayIncome(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(USER_SOLD_TOTAL_PRICE) FROM USER_SOLD_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);
        else
            cursor= db.rawQuery("SELECT sum(USER_SOLD_TOTAL_PRICE) FROM USER_SOLD_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);

        if(cursor.moveToFirst()) return cursor.getInt(0);
        return 0;

    }

    public int getTodayOutcome(String type){

        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        Cursor cursor;

        if(type.equals("GREEN BEAN"))
            cursor= db.rawQuery("SELECT sum(TOTAL_PRICE) FROM SOLD_BAG_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);
        else
            cursor= db.rawQuery("SELECT sum(TOTAL_PRICE) FROM SOLD_BAG_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m-%d',T_DATE)=strftime('%Y-%m-%d','now')",null);

        if(cursor.moveToFirst())
            return (int)cursor.getFloat(0);

        return 0;

    }
}
