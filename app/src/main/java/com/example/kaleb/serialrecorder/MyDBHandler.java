package com.example.kaleb.serialrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kaleb on 31/08/2016.
 */
public class MyDBHandler  extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 5; //current version of the database
    private static final String DATABASE_NAME = "items.db"; //the name of the database
    public static final String TABLE_ITEMS = "Items"; //name of the table
    public static final String COLUMN_ID = "_id"; //column for the item ID
    public static final String COLUMN_NAME = "itemName"; //column for the item Name
    public static final String COLUMN_DESCRIPTION = "itemDescription"; //column for the item Description
    public static final String COLUMN_SERIALNUMBER = "serialNumber"; //column for the item Serial Number
    public static final String COLUMN_DATEPURCHASED = "datePurchased"; //column for the Date that item was purchased
    public static final String COLUMN_IMAGEPATH = "imagePath"; //column for the image path

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
                COLUMN_DATEPURCHASED + " TEXT, " +
                COLUMN_IMAGEPATH + " TEXT " + ");";
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
        values.put(COLUMN_IMAGEPATH, items.get_imagePath());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_ITEMS, null, values);
        sqLiteDatabase.close();

    }

    //method that edits Items in the database
    public void editItem(Items items, int id){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues updatedValues = new ContentValues();
        updatedValues.put(COLUMN_NAME, items.get_itemName());
        updatedValues.put(COLUMN_DESCRIPTION, items.get_itemDescription());
        updatedValues.put(COLUMN_SERIALNUMBER, items.get_serialNumber());
        updatedValues.put(COLUMN_DATEPURCHASED, items.get_datePurchased());
        updatedValues.put(COLUMN_IMAGEPATH, items.get_imagePath());

        sqLiteDatabase.update(TABLE_ITEMS, updatedValues, "_id=" + id, null);
        sqLiteDatabase.close();
    }

    public void wipeDatabase(){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "DELETE FROM " + TABLE_ITEMS;
        sqLiteDatabase.execSQL(query);
        sqLiteDatabase.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_ITEMS + "'");
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

    //List of items in the database to populate the ListView with.
    public List<Items> getAllItems(){
        List<Items> itemsList = new ArrayList<Items>();

        String selectQuery = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        itemsList.clear(); //clear list so data isn't added multiple times
        if(cursor.moveToFirst()){
            do{
                Items item = new Items();
                item.set_id(Integer.parseInt(cursor.getString(0)));
                item.set_itemName(cursor.getString(1));
                item.set_itemDescription(cursor.getString(2));
                item.set_serialNumber(cursor.getString(3));
                item.set_datePurchased(cursor.getString(4));
                item.set_imagePath(cursor.getString(5));

                String itemRow = "Name: " + cursor.getString(1) + "\n" + "Serial Number: " +
                        cursor.getString(3) + "\n" + "Date Purchased: " + cursor.getString(4);
                ViewItemsActivity.itemArray.add(itemRow);
                itemsList.add(item);
            } while(cursor.moveToNext());
        }

        return itemsList;
    }

    //gets the Item Name (displays in TextView)
    public String getItemName(int id){
        String itemName = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT itemName FROM " + TABLE_ITEMS + " WHERE _id = " + id + ";";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        if(c.getString(c.getColumnIndex("itemName")) != null){
            itemName = c.getString(c.getColumnIndex("itemName"));
        }

        sqLiteDatabase.close();
        return itemName;
    }

    //gets the Item Description (displays in TextView)
    public String getItemDescription(int id){
        String itemDescription = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT itemDescription FROM " + TABLE_ITEMS + " WHERE _id = " + id + ";";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        if(c.getString(c.getColumnIndex("itemDescription")) != null){
            itemDescription = c.getString(c.getColumnIndex("itemDescription"));
        }

        sqLiteDatabase.close();
        return itemDescription;
    }

    //gets the Item Serial Number (displays in TextView)
    public String getSerialNumber(int id){
        String itemSerialNumber = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT serialNumber FROM " + TABLE_ITEMS + " WHERE _id = " + id + ";";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        if(c.getString(c.getColumnIndex("serialNumber")) != null){
            itemSerialNumber = c.getString(c.getColumnIndex("serialNumber"));
        }

        sqLiteDatabase.close();
        return itemSerialNumber;
    }

    //gets the Item DatePurchased (displays in TextView)
    public String getDatePurchased(int id){
        String datePurchased = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT datePurchased FROM " + TABLE_ITEMS + " WHERE _id = " + id + ";";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        if(c.getString(c.getColumnIndex("datePurchased")) != null){
            datePurchased = c.getString(c.getColumnIndex("datePurchased"));
        }

        sqLiteDatabase.close();
        return datePurchased;
    }

    //gets the Image Uri (displays in ImageView)
    public String getImageUri(int id){
        String imageUri = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT imagePath FROM " + TABLE_ITEMS + " WHERE _id = " + id + ";";

        Cursor c = sqLiteDatabase.rawQuery(query, null);
        c.moveToFirst();

        if(c.getString(c.getColumnIndex("imagePath")) != null){
            imageUri = c.getString(c.getColumnIndex("imagePath"));
        }

        sqLiteDatabase.close();
        return imageUri;
    }
}
