package com.example.sauca.project_fc.DBLogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Sauca on 14-03-2016.
 */
public class DataBaseLogin extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="DBFastcall.db";
    public static final String TABLE_NAME="Login_table";
    public static final String COL_1="ID";
    public static final String COL_2="NOME";
    public static final String COL_3="APELIDO";
    public static final String COL_4="PASSWORD";
    public static final String COL_5="EMAIL";

    public DataBaseLogin(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, APELIDO TEXT, PASSWORD TEXT, EMAIL TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public  boolean insertData(String nome, String apelido, String password, String email){

        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_2,nome);
        contentValues.put(COL_3,apelido);
        contentValues.put(COL_4, password);
        contentValues.put(COL_5, email);
        long result=db.insert(TABLE_NAME,null,contentValues);

        if(result==1)
            return  true;
        else
            return false;
    }

    public Cursor getAllData(){

        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return  res;
    }
}
