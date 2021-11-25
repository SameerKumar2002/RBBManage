package com.fusionlab.rbbmanage.Databasehelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper2 extends SQLiteOpenHelper {

    private final static String name = "RB_Manage2.db";
    private final static int version = 1;

    public DataBaseHelper2(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String sql1 = "CREATE TABLE IF NOT EXISTS UNSOLD_BAG_GREEN_BEAN ( 'ID' INTEGER PRIMARY KEY AUTOINCREMENT," +
                "'SERIAL' INTEGER,'NAME' TEXT,'PHONE_NUMBER' TEXT,'ADDRESS' TEXT,'DATE' TEXT,'T_DATE' TEXT,'BAG_COUNT' INTEGER," +
                "'VISS_COUNT' FLOAT,'SIZE' TEXT)";
        db.execSQL(sql1);

        String sql2 = "CREATE TABLE IF NOT EXISTS UNSOLD_BAG_BLACK_BEAN ( 'ID' INTEGER PRIMARY KEY AUTOINCREMENT," +
                "'SERIAL' INTEGER,'NAME' TEXT,'PHONE_NUMBER' TEXT,'ADDRESS' TEXT,'DATE' TEXT,'T_DATE' TEXT,'BAG_COUNT' INTEGER," +
                "'VISS_COUNT' FLOAT,'SIZE' TEXT)";
        db.execSQL(sql2);

        String sql3 = "CREATE TABLE IF NOT EXISTS SOLD_BAG_GREEN_BEAN ( 'ID' INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "'BAG_COUNT' INTEGER, 'VISS_COUNT' FLOAT,'DATE' TEXT,'T_DATE' TEXT,'SIZE' TEXT,'PRICE' INTEGER,'TOTAL_PRICE' INTEGER)";
        db.execSQL(sql3);

        String sql4 = "CREATE TABLE IF NOT EXISTS SOLD_BAG_BLACK_BEAN ( 'ID' INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "'BAG_COUNT' INTEGER, 'VISS_COUNT' FLOAT,'DATE' TEXT,'T_DATE' TEXT,'SIZE' TEXT,'PRICE' INTEGER,'TOTAL_PRICE' INTEGER)";
        db.execSQL(sql4);

        String sql5 = "CREATE TABLE IF NOT EXISTS USER_SOLD_BLACK_BEAN ('ID' INTEGER PRIMARY KEY AUTOINCREMENT,'BAG_COUNT' FLOAT,'DATE' TEXT,'T_DATE' TEXT,'BUY_AVG' INTEGER,'USER_SOLD_PRICE' INTEGER,'USER_SOLD_TOTAL_PRICE' INTEGER,'PROFIT' INTEGER)";
        db.execSQL(sql5);

        String sql6 = "CREATE TABLE IF NOT EXISTS USER_SOLD_GREEN_BEAN ('ID' INTEGER PRIMARY KEY AUTOINCREMENT,'BAG_COUNT' FLOAT,'DATE' TEXT,'T_DATE' TEXT,'BUY_AVG' INTEGER,'USER_SOLD_PRICE' INTEGER,'USER_SOLD_TOTAL_PRICE' INTEGER,'PROFIT' INTEGER)";
        db.execSQL(sql6);



        String sql7 = "CREATE TABLE IF NOT EXISTS DELETED_UNSOLD_BAG_GREEN_BEAN ( 'ID' INTEGER," +
                "'SERIAL' INTEGER,'NAME' TEXT,'PHONE_NUMBER' TEXT,'ADDRESS' TEXT,'DATE' TEXT,'T_DATE' TEXT,'BAG_COUNT' INTEGER," +
                "'VISS_COUNT' FLOAT)";
        db.execSQL(sql7);

        String sql8 = "CREATE TABLE IF NOT EXISTS DELETED_UNSOLD_BAG_BLACK_BEAN ( 'ID' INTEGER," +
                "'SERIAL' INTEGER,'NAME' TEXT,'PHONE_NUMBER' TEXT,'ADDRESS' TEXT,'DATE' TEXT,'T_DATE' TEXT,'BAG_COUNT' INTEGER," +
                "'VISS_COUNT' FLOAT,'SIZE' TEXT)";
        db.execSQL(sql8);

        String sql9 = "CREATE TABLE IF NOT EXISTS DELETED_SOLD_BAG_GREEN_BEAN ( 'ID' INTEGER,"+
                "'BAG_COUNT' INTEGER, 'VISS_COUNT' FLOAT,'DATE' TEXT,'T_DATE' TEXT,'PRICE' INTEGER,'TOTAL_PRICE' INTEGER)";
        db.execSQL(sql9);

        String sql10 = "CREATE TABLE IF NOT EXISTS DELETED_SOLD_BAG_BLACK_BEAN ( 'ID' INTEGER,"+
                "'BAG_COUNT' INTEGER, 'VISS_COUNT' FLOAT,'DATE' TEXT,'T_DATE' TEXT,'SIZE' TEXT,'PRICE' INTEGER,'TOTAL_PRICE' INTEGER)";
        db.execSQL(sql10);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
