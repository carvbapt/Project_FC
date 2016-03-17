package com.example.sauca.project_fc.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sauca.project_fc.DB.Model.Funcionario;

/**
 * Created by Sauca on 14-03-2016.
 */
public class DataBaseFC extends SQLiteOpenHelper {


    private static final String DATABASE_NAME="DBFastcall.db";
    private static final int DATABASE_VERSION= 1;

    public DataBaseFC(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Funcionario.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Funcionario.TABLE);
        onCreate(db);
    }





//
//
//
//    // Empregados
//    public static final String TABLE_EMP="Login_table";
//    public static final String COL_EMP1="ID";
//    public static final String COL_EMP2="NOME";
//    public static final String COL_EMP3="APELIDO";
//    public static final String COL_EMP4="PASSWORD";
//    public static final String COL_EMP5="EMAIL";
//
//    public static final String CREATE_EMP ="Create Table " + TABLE_EMP + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NOME TEXT, APELIDO TEXT, PASSWORD TEXT, EMAIL TEXT )";
//

//    public  boolean insertData(String nome, String apelido, String password, String email){
//
//        SQLiteDatabase db= this.getWritableDatabase();
//        ContentValues contentValues= new ContentValues();
//        contentValues.put(COL_EMP2,nome);
//        contentValues.put(COL_EMP3,apelido);
//        contentValues.put(COL_EMP4, password);
//        contentValues.put(COL_EMP5, email);
//        long result=db.insert(TABLE_EMP,null,contentValues);
//
//        if(result>=1)
//            return  true;
//        else
//            return false;
//    }
//
//    public Cursor getAllData(){
//
//        SQLiteDatabase db= this.getWritableDatabase();
//        Cursor res=db.rawQuery("select * from "+TABLE_EMP,null);
//        return  res;
//    }
}
