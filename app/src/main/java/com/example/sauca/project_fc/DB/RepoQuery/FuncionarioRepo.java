package com.example.sauca.project_fc.DB.RepoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sauca.project_fc.DB.DataBaseFC;
import com.example.sauca.project_fc.DB.Model.Funcionario;

/**
 * Created by Sauca on 16-03-2016.
 */
public class FuncionarioRepo {

    private DataBaseFC databasefc;

    public FuncionarioRepo(Context context){
        databasefc= new DataBaseFC(context);
    }

    public int insert(Funcionario funcionario) {

        SQLiteDatabase db =  databasefc.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Funcionario.COL_EMP2,funcionario.getF_nome());
        values.put(Funcionario.COL_EMP3,funcionario.getF_apelido());
        values.put(Funcionario.COL_EMP4,funcionario.getF_password());
        values.put(Funcionario.COL_EMP5,funcionario.getF_email());
        values.put(Funcionario.COL_EMP6, funcionario.getF_empresa());

        // Inserting Row
        long result=db.insert(Funcionario.TABLE, null, values);
        db.close();
        return (int) result;
    }

    public Cursor getAllData(){

        SQLiteDatabase db= databasefc.getWritableDatabase();
//        Cursor res=db.rawQuery("select * from "+ Funcionario.TABLE,null);
        return db.rawQuery("select * from "+ Funcionario.TABLE,null);
    }

    public void delete(int funcio ) {
        SQLiteDatabase db = databasefc.getWritableDatabase();
        db.delete(Funcionario.TABLE, null, null);
        db.close();
    }
}
