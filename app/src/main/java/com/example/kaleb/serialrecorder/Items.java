package com.example.kaleb.serialrecorder;

/**
 * Created by kaleb on 31/08/2016.
 */
public class Items {

    private int _id;
    private String _itemName; //This is the attribute for the Item Name
    private String _itemDescription; //This is the attribute for the Item Description
    private String _serialNumber; //This is the attribute for the Item Serial Number
    private String _datePurchased; //This is the attribute for the date entered for the item

    //Empty constructor so we don't always have to an itemName, Description AND serialNumber
    public Items(){

    }

    //Constructor for user inputs
    public Items(String datePurchased, String itemDescription, String itemName, String serialNumber) {
        this._datePurchased = datePurchased;
        this._itemDescription = itemDescription;
        this._itemName = itemName;
        this._serialNumber = serialNumber;
    }

    //Set method for id
    public void set_id(int _id) {
        this._id = _id;
    }

    //Set method for Item Description
    public void set_itemDescription(String _itemDescription) {
        this._itemDescription = _itemDescription;
    }

    //Set method for Item Name
    public void set_itemName(String _itemName) {
        this._itemName = _itemName;
    }

    //Set method for Serial Number
    public void set_serialNumber(String _serialNumber) {
        this._serialNumber = _serialNumber;
    }

    //Set method for Date Purchased
    public void set_datePurchased(String _datePurchased) {
        this._datePurchased = _datePurchased;
    }

    //Get method for id
    public int get_id() {
        return _id;
    }

    //Get method for Item Description
    public String get_itemDescription() {
        return _itemDescription;
    }

    //Get method for Item Name
    public String get_itemName() {
        return _itemName;
    }

    //Get method for Serial Number
    public String get_serialNumber() {
        return _serialNumber;
    }

    //Get method for datePurchased
    public String get_datePurchased() {
        return _datePurchased;
    }
}
