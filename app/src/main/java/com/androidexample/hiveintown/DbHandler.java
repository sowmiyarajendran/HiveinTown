package com.androidexample.hiveintown;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sowmiya on 3/16/2015.
 */
public class DbHandler extends SQLiteOpenHelper {

    private final String TAG = "MainActivity";
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="HiveInTown";
    //table names
    private static final  String TABLE_Helper="DomesticHelper";


    // column names
  //  private static final String Key_Id="Id";
    private static final String KEY_Name="Name";
    private static final String KEY_Age="Age";
    private static final String KEY_Sex="Sex";
    private static final String Key_PhoneNo="PhoneNo";
    private static final String Key_Address="Address";
    private static final String Key_Visiting="VisitingAppartments";
    private static final String Key_Photo="Photo";
    private static final String Key_Doc1="Document1";
    private static final String Key_AddProof="AddProof";
    private static final String Key_Doc2="Document2";
    private static final String Key_IDProof="IdProof";


    //table create statements
    private static final String CREATE_DOMESTICHELPER_TABLE="CREATE TABLE "+TABLE_Helper+"("+

            KEY_Name +" TEXT," +
            KEY_Age +" TEXT," +
            KEY_Sex +" TEXT" +
            Key_PhoneNo +" TEXT PRIMARY KEY,"+
            Key_Address +" TEXT," +
            Key_Visiting +" TEXT," +
            Key_Photo +" BLOB," +
            Key_Doc1 +" TEXT," +
            Key_AddProof +" BLOB," +
            Key_Doc2 +" TEXT," +
           Key_IDProof +" BLOB" +" )";


    public DbHandler(Context context) {

        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //  Log.d("Insert","oncreating table");

        db.execSQL(CREATE_DOMESTICHELPER_TABLE);
        Log.d(TAG, "constant table created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+ TABLE_Helper);
        onCreate(db);
    }
    public// Adding new contact
    void addContact(HelperDatabase detail) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Name, detail.Helper_Name);
        values.put(KEY_Age, detail.Helper_Age );
      values.put(KEY_Sex,detail.Helper_Sex);
        values.put(Key_PhoneNo,detail.Helper_PhoneNo);
        values.put(Key_Address,detail.Helper_Address);
        values.put(Key_Visiting,detail.Visiting_Appartments);
        values.put(Key_Photo,detail.Helper_Photo);
        values.put(Key_Doc1,detail.DocType1);
        values.put(Key_AddProof,detail.Helper_AddressProof);
        values.put(Key_Doc2,detail.DocType2);
        values.put(Key_IDProof,detail.Identity_Proof);





        // Inserting Row
        db.insert(TABLE_Helper, null, values);
        db.close(); // Closing database connection
    }
    // Getting single contact
     boolean getContact(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_Helper, new String[] {KEY_Name,KEY_Age,KEY_Sex,Key_PhoneNo,Key_Address,Key_Visiting,Key_Photo,Key_Doc1,Key_AddProof,Key_Doc2,Key_IDProof  }, Key_PhoneNo + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        //HelperDatabase contact = new HelperDatabase(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getBlob(6),cursor.getString(7),cursor.getBlob(8),cursor.getString(9),cursor.getBlob(10) );
        // return contact
        return true;
    }
    // Getting All Contacts
    public List<HelperDatabase> getAllContacts() {

        List<HelperDatabase> contactList = new ArrayList<HelperDatabase>();
        // Select All Query
        String selectQuery = "SELECT  * FROM "+TABLE_Helper;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HelperDatabase contact = new HelperDatabase();
                contact.setHelper_Name(cursor.getString(0));
                contact.setHelper_Age(cursor.getString(1));
                contact.setHelper_Sex(cursor.getString(2));
                contact.setHelper_PhoneNo(cursor.getString(3));
                contact.setHelper_Address(cursor.getString(4));
                contact.setVisiting_Appartments(cursor.getString(5));
                contact.setHelper_Photo(cursor.getBlob(6));
                contact.setDocType1(cursor.getString(7));
                contact.setHelper_AddressProof(cursor.getBlob(8));
                contact.setDocType2(cursor.getString(9));
                contact.setIdentity_Proof(cursor.getBlob(10));

             //   contact.setId(Integer.parseInt(cursor.getString(0)));
               // contact.setSname(cursor.getString(1));
                //contact.setImages(cursor.getBlob(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // close inserting data from database
        db.close();
        // return contact list
        return contactList;

    }























}
