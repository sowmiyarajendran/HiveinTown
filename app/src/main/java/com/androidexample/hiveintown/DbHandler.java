package com.androidexample.hiveintown;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
    private static final String Key_Id="Id";
    private static final String KEY_Name="Name";
    private static final String KEY_Age="Age";
    private static final String KEY_Sex="Sex";
    private static final String Key_PhoneNo="PhoneNo";
    private static final String Key_Address="Address";
    private static final String Key_Visiting="VisitingAppartments";
    private static final String Key_Photo="Photo";
    private static final String Key_AddProof="AddProof";


    //table create statements
    private static final String CREATE_DOMESTICHELPER_TABLE="CREATE TABLE "+TABLE_Helper+"("+

            KEY_Name +" TEXT," +
            KEY_Age +" TEXT," +
            KEY_Sex +" TEXT" +
            Key_PhoneNo +" TEXT PRIMARY KEY,"+
            Key_Address +" TEXT," +
            Key_Visiting +" TEXT," +
            Key_Photo +" TEXT," +
           Key_AddProof +" TEXT" +" )";


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
}
