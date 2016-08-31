package com.example.kaleb.serialrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kaleb on 31/08/2016.
 */
public class MyDBHandler  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1; //current version of the database
    private static final String DATABASE_NAME = "items.db"; //the name of the database
    public static final String TABLE_ITEMS = "Items"; //name of the table
    public static final String COLUMN_ID = "_id"; //column for the item ID
    public static final String COLUMN_NAME = "_itemName"; //column for the item Name
    public static final String COLUMN_DESCRIPTION = "_itemDescription"; //column for the item Description
    public static final String COLUMN_SERIALNUMBER = "_serialNumber"; //column for the item Serial Number

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create the tables and columns
        String query = "CREATE TABLE " + TABLE_ITEMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT " +
                COLUMN_NAME + " TEXT " +
                COLUMN_DESCRIPTION + " TEXT " +
                COLUMN_SERIALNUMBER + " TEXT " + ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(sqLiteDatabase);
    }

    //method that adds Items to the database
    public void addItem(Items items){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, items.get_itemName());
        values.put(COLUMN_DESCRIPTION, items.get_itemDescription());
        values.put(COLUMN_SERIALNUMBER, items.get_serialNumber());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_ITEMS, null, values);
        sqLiteDatabase.close();

    }
}
