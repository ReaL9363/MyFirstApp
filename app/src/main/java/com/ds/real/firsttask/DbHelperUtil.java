package com.ds.real.firsttask;

/**
 * Created by ReaL on 6/20/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;

public class DbHelperUtil extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "sales.db";
    public static final String TABLE_NAME= "sales_table";
    public static final String COL_1= "ID";
    public static final String COL_2= "product_name";
    public static final String COL_3= "Price";
    public static final String COL_4= "Amount";

    public DbHelperUtil(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,product_name TEXT,Price INTEGER,Amount INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public boolean insertData(ProductModel myModel){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,myModel.getName());
        contentValues.put(COL_3,myModel.getPrice());
        contentValues.put(COL_4,0);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result==-1)
            return false;
        else
            return true;

    }

    public ArrayList<ProductModel> getAllData() {
        ArrayList<ProductModel> result = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ProductModel myModel = new ProductModel();
                myModel.setName(cursor.getString(1));
                myModel.setPrice(cursor.getString(2));
                myModel.setAmount(cursor.getString(3));
                result.add(myModel);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return result;

    }

    public boolean saveProductAmount(String amount,String name) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(COL_4,amount);
        long result = db.update(TABLE_NAME, data, COL_2 + " = ?", new String[]{name});
        if(result==-1)
            return false;
        else
            return true;


    }

    public ArrayList<ProductModel> getAllOrderData() {
        ArrayList<ProductModel> result = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                ProductModel myModel = new ProductModel();
                myModel.setName(cursor.getString(1));
                myModel.setPrice(cursor.getString(2));
                myModel.setAmount(cursor.getString(3));
                if((Integer.parseInt(cursor.getString(3))>0)){
                    result.add(myModel);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        return result;

    }

    public boolean updateOrderedData() {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues data=new ContentValues();
        data.put(COL_4,"0");
        long result = db.update(TABLE_NAME, data, null, null);
        if(result==-1)
            return false;
        else
            return true;

    }
}

