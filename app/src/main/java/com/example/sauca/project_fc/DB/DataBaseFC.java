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
}
