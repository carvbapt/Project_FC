package com.example.sauca.appfc.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.Model.Funcionario;
import com.example.sauca.appfc.DB.Model.Materia;

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
        db.execSQL(Diaria.createTable());
        db.execSQL(Materia.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+Funcionario.TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Diaria.TABLE);
        db.execSQL("DROP TABLE IF EXISTS "+Materia.TABLE);

        onCreate(db);
    }
}
