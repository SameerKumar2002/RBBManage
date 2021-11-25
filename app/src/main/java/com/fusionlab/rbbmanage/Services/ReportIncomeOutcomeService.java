package com.fusionlab.rbbmanage.Services;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.fusionlab.rbbmanage.Databasehelper.DataBaseHelper2;
import com.fusionlab.rbbmanage.dto.ReportIncomeOutcome;

import java.util.ArrayList;

public class ReportIncomeOutcomeService {

    Context context;

    public ReportIncomeOutcomeService(Context context){
        this.context = context;
    }

    public ArrayList<ReportIncomeOutcome> getData(String date,String type){
        ArrayList<ReportIncomeOutcome> arrayList = new ArrayList<>();
        ReportIncomeOutcome income_outcome ;
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();

        Cursor cursor;
        if(type.equals("GREEN BEAN"))
        cursor = db.rawQuery("SELECT TABLE1.ID,TABLE1.T_DATE,TABLE1.INCOME,TABLE2.ID AS ID2,TABLE2.T_DATE AS T_DATE2,TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,BAG_COUNT,VISS_COUNT,DATE,T_DATE,PRICE,TOTAL_PRICE FROM SOLD_BAG_GREEN_BEAN UNION SELECT * FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE2 ON TABLE1.T_DATE=TABLE2.T_DATE UNION SELECT TABLE1.ID,TABLE1.T_DATE,TABLE1.INCOME,TABLE2.ID,TABLE2.T_DATE,TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,BAG_COUNT,VISS_COUNT,DATE,T_DATE,PRICE,TOTAL_PRICE FROM SOLD_BAG_GREEN_BEAN UNION SELECT * FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_GREEN_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE1 ON TABLE1.T_DATE =TABLE2.T_DATE ",new String[]{date,date,date,date});
        else cursor = db.rawQuery("SELECT TABLE1.ID,TABLE1.T_DATE,TABLE1.INCOME,TABLE2.ID AS ID2,TABLE2.T_DATE AS T_DATE2,TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,BAG_COUNT,VISS_COUNT,DATE,T_DATE,PRICE,TOTAL_PRICE FROM SOLD_BAG_BLACK_BEAN UNION SELECT * FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE2 ON TABLE1.T_DATE=TABLE2.T_DATE UNION SELECT TABLE1.ID,TABLE1.T_DATE,TABLE1.INCOME,TABLE2.ID,TABLE2.T_DATE,TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,BAG_COUNT,VISS_COUNT,DATE,T_DATE,PRICE,TOTAL_PRICE FROM SOLD_BAG_BLACK_BEAN UNION SELECT * FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_BLACK_BEAN GROUP BY T_DATE HAVING strftime('%Y-%m',T_DATE)=strftime('%Y-%m',?))AS TABLE1 ON TABLE1.T_DATE =TABLE2.T_DATE",new String[]{date,date,date,date});
        if(cursor.moveToFirst()){
            do{
                income_outcome = new ReportIncomeOutcome();

                if(!cursor.isNull(1)) income_outcome.setDate(cursor.getString(1));
                else income_outcome.setDate(cursor.getString(4));

                if(cursor.isNull(2)) income_outcome.setIncome(0);
                else income_outcome.setIncome(cursor.getInt(2));

                if(cursor.isNull(5)) income_outcome.setIncome(0);
                else income_outcome.setIncome(cursor.getInt(5));

                Toast.makeText(context,String.valueOf(income_outcome.getIncome()),Toast.LENGTH_LONG).show();

             arrayList.add(income_outcome);

            }while (cursor.moveToNext());
        }

        return arrayList;
    }

    public ArrayList<String> getMonthYear(String type){

        ArrayList<String> month_years = new ArrayList<>();
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();

        Cursor cursor;
        if(type.equals("GREEN BEAN"))
        cursor = db.rawQuery("SELECT TABLE1.ID,strftime('%Y-%m',TABLE1.T_DATE) AS T_DATE,TABLE2.ID AS ID2,strftime('%Y-%m',TABLE2.T_DATE) AS T_DATE2 FROM (SELECT ID,T_DATE FROM USER_SOLD_GREEN_BEAN GROUP BY strftime('%Y-%m',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_GREEN_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY strftime('%Y-%m',T_DATE)  ORDER by datetime(T_DATE))AS TABLE2 ON strftime('%Y-%m',TABLE1.T_DATE)=strftime('%Y-%m',TABLE2.T_DATE) UNION SELECT TABLE1.ID,strftime('%Y-%m',TABLE1.T_DATE),TABLE2.ID,strftime('%Y-%m',TABLE2.T_DATE) FROM (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_GREEN_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY strftime('%Y-%m',T_DATE) ORDER by datetime(T_DATE))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE FROM USER_SOLD_GREEN_BEAN GROUP BY strftime('%Y-%m',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 ON strftime('%Y-%m',TABLE1.T_DATE)=strftime('%Y-%m',TABLE2.T_DATE) ORDER BY T_DATE2 DESC",null);
        else cursor = db.rawQuery("SELECT TABLE1.ID,strftime('%Y-%m',TABLE1.T_DATE) AS T_DATE,TABLE2.ID AS ID2,strftime('%Y-%m',TABLE2.T_DATE) AS T_DATE2 FROM (SELECT ID,T_DATE FROM USER_SOLD_BLACK_BEAN GROUP BY strftime('%Y-%m',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_BLACK_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY strftime('%Y-%m',T_DATE)  ORDER by datetime(T_DATE))AS TABLE2 ON strftime('%Y-%m',TABLE1.T_DATE)=strftime('%Y-%m',TABLE2.T_DATE) UNION SELECT TABLE1.ID,strftime('%Y-%m',TABLE1.T_DATE),TABLE2.ID,strftime('%Y-%m',TABLE2.T_DATE) FROM (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_BLACK_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY strftime('%Y-%m',T_DATE) ORDER by datetime(T_DATE))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE FROM USER_SOLD_BLACK_BEAN GROUP BY strftime('%Y-%m',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 ON strftime('%Y-%m',TABLE1.T_DATE)=strftime('%Y-%m',TABLE2.T_DATE) ORDER BY T_DATE2 DESC",null);
        if(cursor.moveToFirst()){
            do{
                if(!cursor.isNull(1))  month_years.add(cursor.getString(1));
                else  month_years.add(cursor.getString(3));
            }while (cursor.moveToNext());
        }
        return month_years;
    }

  public ArrayList<String> getYear(String type){

        ArrayList<String> years = new ArrayList<>();
      DataBaseHelper2 helper2 = new DataBaseHelper2(context);
      SQLiteDatabase db = helper2.getReadableDatabase();

      Cursor cursor;

      if(type.equals("GREEN BEAN"))
      cursor = db.rawQuery("SELECT TABLE1.ID,strftime('%Y',TABLE1.T_DATE) AS T_DATE,TABLE2.ID AS ID2,strftime('%Y',TABLE2.T_DATE) AS T_DATE2 FROM (SELECT ID,T_DATE FROM USER_SOLD_GREEN_BEAN GROUP BY strftime('%Y',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_GREEN_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY strftime('%Y',T_DATE)  ORDER by datetime(T_DATE))AS TABLE2 ON strftime('%Y',TABLE1.T_DATE)=strftime('%Y',TABLE2.T_DATE) UNION " +
              "SELECT TABLE1.ID,strftime('%Y',TABLE1.T_DATE),TABLE2.ID,strftime('%Y',TABLE2.T_DATE) FROM (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_GREEN_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY strftime('%Y',T_DATE) ORDER by datetime(T_DATE))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE FROM USER_SOLD_GREEN_BEAN GROUP BY strftime('%Y',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 ON strftime('%Y',TABLE1.T_DATE)=strftime('%Y',TABLE2.T_DATE) ORDER BY T_DATE2 ",null);

      else cursor = db.rawQuery("SELECT TABLE1.ID,strftime('%Y',TABLE1.T_DATE) AS T_DATE,TABLE2.ID AS ID2,strftime('%Y',TABLE2.T_DATE) AS T_DATE2 FROM (SELECT ID,T_DATE FROM USER_SOLD_BLACK_BEAN GROUP BY strftime('%Y',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_BLACK_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY strftime('%Y',T_DATE)  ORDER by datetime(T_DATE))AS TABLE2 ON strftime('%Y',TABLE1.T_DATE)=strftime('%Y',TABLE2.T_DATE) UNION " +
              "SELECT TABLE1.ID,strftime('%Y',TABLE1.T_DATE),TABLE2.ID,strftime('%Y',TABLE2.T_DATE) FROM (SELECT ID,T_DATE FROM (SELECT ID,T_DATE FROM SOLD_BAG_BLACK_BEAN UNION SELECT ID,T_DATE FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY strftime('%Y',T_DATE) ORDER by datetime(T_DATE))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE FROM USER_SOLD_BLACK_BEAN GROUP BY strftime('%Y',T_DATE) ORDER by datetime(T_DATE))AS TABLE1 ON strftime('%Y',TABLE1.T_DATE)=strftime('%Y',TABLE2.T_DATE) ORDER BY T_DATE2 ",null);

      if(cursor.moveToFirst()){
          do{
              if(!cursor.isNull(1))
              years.add(cursor.getString(1));
              else years.add(cursor.getString(3));
          }while (cursor.moveToNext());
      }

      return years;
  }

  public ReportIncomeOutcome[] getTotalByMonth(String type,String date){

        ReportIncomeOutcome[] incomeOutcomes = new ReportIncomeOutcome[12];
        DataBaseHelper2 helper2 = new DataBaseHelper2(context);
        SQLiteDatabase db = helper2.getReadableDatabase();
        ReportIncomeOutcome income_outcome ;

        Cursor cursor;

       if(type.equals("GREEN BEAN"))
      cursor = db.rawQuery("SELECT TABLE1.ID,strftime('%m',TABLE1.T_DATE),TABLE1.INCOME,TABLE2.ID AS ID2,strftime('%m',TABLE2.T_DATE) AS T_DATE2,TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_GREEN_BEAN GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?)ORDER by datetime(T_DATE))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM SOLD_BAG_GREEN_BEAN UNION SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?) ORDER by datetime(T_DATE))AS TABLE2 ON TABLE1.T_DATE=TABLE2.T_DATE UNION SELECT TABLE1.ID,strftime('%m',TABLE1.T_DATE),TABLE1.INCOME,TABLE2.ID,strftime('%m',TABLE2.T_DATE),TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM SOLD_BAG_GREEN_BEAN UNION SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM DELETED_SOLD_BAG_GREEN_BEAN) GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?)ORDER by datetime(T_DATE))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_GREEN_BEAN GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?)ORDER by datetime(T_DATE))AS TABLE1 ON TABLE1.T_DATE =TABLE2.T_DATE",new String[]{date,date,date,date});
      else cursor = db.rawQuery("SELECT TABLE1.ID,strftime('%m',TABLE1.T_DATE),TABLE1.INCOME,TABLE2.ID AS ID2,strftime('%m',TABLE2.T_DATE) AS T_DATE2,TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_BLACK_BEAN GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?)ORDER by datetime(T_DATE))AS TABLE1 LEFT JOIN (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM SOLD_BAG_BLACK_BEAN UNION SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?) ORDER by datetime(T_DATE))AS TABLE2 ON TABLE1.T_DATE=TABLE2.T_DATE UNION SELECT TABLE1.ID,strftime('%m',TABLE1.T_DATE),TABLE1.INCOME,TABLE2.ID,strftime('%m',TABLE2.T_DATE),TABLE2.OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS OUTCOME FROM (SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM SOLD_BAG_BLACK_BEAN UNION SELECT ID,T_DATE,SUM(TOTAL_PRICE) AS TOTAL_PRICE FROM DELETED_SOLD_BAG_BLACK_BEAN) GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?)ORDER by datetime(T_DATE))AS TABLE2 LEFT JOIN (SELECT ID,T_DATE,SUM(USER_SOLD_TOTAL_PRICE) AS INCOME FROM USER_SOLD_BLACK_BEAN GROUP BY strftime('%Y-%m',T_DATE) HAVING strftime('%Y',T_DATE)=strftime('%Y',?)ORDER by datetime(T_DATE))AS TABLE1 ON TABLE1.T_DATE =TABLE2.T_DATE",new String[]{date,date,date,date});

      if(cursor.moveToFirst()){
          do{
              income_outcome = new ReportIncomeOutcome();

              if(!cursor.isNull(1)) income_outcome.setDate(cursor.getString(1));
              else income_outcome.setDate(cursor.getString(4));

              if(cursor.isNull(2)) income_outcome.setIncome(0);
              else income_outcome.setIncome(cursor.getInt(2));

              if(cursor.isNull(5)) income_outcome.setIncome(0);
              else income_outcome.setIncome(cursor.getInt(5));

              incomeOutcomes[Integer.parseInt(income_outcome.getDate())-1]= income_outcome;
          }while (cursor.moveToNext());
      }


      return incomeOutcomes;
  }

}
