package com.example.kaleb.serialrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kaleb on 31/08/2016.
 */
public class MyDBHandler  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 2; //current version of the database
    private static final String DATABASE_NAME = "items.db"; //the name of the database
    public static final String TABLE_ITEMS = "Items"; //name of the table
    public static final String COLUMN_ID = "_id"; //column for the item ID
    public static final String COLUMN_NAME = "itemName"; //column for the item Name
    public static final String COLUMN_DESCRIPTION = "itemDescription"; //column for the item Description
    public static final String COLUMN_SERIALNUMBER = "serialNumber"; //column for the item Serial Number
    public static final String COLUMN_DATEPURCHASED = "datePurchased"; //column for the Date that item was purchased

    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create the tables and columns
        String query = "CREATE TABLE " + TABLE_ITEMS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_SERIALNUMBER + " TEXT, " +
                COLUMN_DATEPURCHASED + " TEXT " + ");";
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
        values.put(COLUMN_DATEPURCHASED, items.get_datePurchased());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_ITEMS, null, values);
        sqLiteDatabase.close();

    }

    //method that prints the database out
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_ITEMS + " WHERE 1;";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        while(!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("datePurchased")) != null) {
                dbString += c.getString(c.getColumnIndex("datePurchased"));
                dbString += "\t";
                if (c.getString(c.getColumnIndex("itemName")) != null) {
                    dbString += c.getString(c.getColumnIndex("itemName"));
                    dbString += "\t";
                    if (c.getString(c.getColumnIndex("serialNumber")) != null) {
                        dbString += c.getString(c.getColumnIndex("serialNumber"));
                        dbString += "\t";
                    }
                }

            }
            dbString += "\n";
            c.moveToNext();
        }

        sqLiteDatabase.close();
        return dbString;
    }
}
