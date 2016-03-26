package com.example.sauca.project_fc.DB.RepoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sauca.project_fc.DB.DataBaseFC;
import com.example.sauca.project_fc.DB.Model.Funcionario;

import java.io.File;

/**
 * Created by Sauca on 16-03-2016.
 */
public class FuncionarioRepo {

    private DataBaseFC databasefc;

    public FuncionarioRepo(Context context){
        databasefc= new DataBaseFC(context);
    }

    public static boolean DatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public int insert(Funcionario func) {
        SQLiteDatabase db =  databasefc.getWritableDatabase();
        ContentValues val=upDate(func);

        // Inserting Row
        long result=db.insert(Funcionario.TABLE, null, val);
        db.close();
        return (int) result;
    }

    public int updateData(Funcionario func){
        SQLiteDatabase db=databasefc.getWritableDatabase();
        ContentValues val=upDate(func);

        // Update Row
        long result=db.update(Funcionario.TABLE, val, Funcionario.COL_EMP1 + "=?", new String[]{String.valueOf(func.f_id)});
        db.close();
        return (int) result;
    }

    public int delete(int id ) {
        SQLiteDatabase db = databasefc.getWritableDatabase();

        // Delete Row
        long result=db.delete(Funcionario.TABLE, Funcionario.COL_EMP1 + "=?", new String[]{String.valueOf(id)});
        db.close();
        return (int) result;
    }

/*********************************************************************************************************************************************************************
     FUNÇÕES PROCURA
/********************************************************************************************************************************************************************/

    public Cursor getAllData(){
        SQLiteDatabase db= databasefc.getWritableDatabase();

        // List All
        return db.rawQuery("select * from " + Funcionario.TABLE, null);
    }

    public  Funcionario searchDataSingle(String field, String value){
        SQLiteDatabase db= databasefc.getReadableDatabase();
        Funcionario func=new Funcionario();
        String str=null;

        if(field.equals("ID"))
            str="select ID,NOME,APELIDO,PASSWORD,EMAIL,EMPRESA FROM "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP1+"=?";
        else if(field.equals("EMAIL"))
            str="select ID,NOME,APELIDO,PASSWORD,EMAIL,EMPRESA FROM "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP5+"=?";

        // Get Single by Gender
        Cursor res = db.rawQuery(str,new String[]{value});

        if(res.moveToFirst()){
            do{
                func.f_id=res.getInt(res.getColumnIndex(Funcionario.COL_EMP1));
                func.f_nome=res.getString(res.getColumnIndex(Funcionario.COL_EMP2));
                func.f_apelido=res.getString(res.getColumnIndex(Funcionario.COL_EMP3));
                func.f_password=res.getString(res.getColumnIndex(Funcionario.COL_EMP4));
                func.f_email=res.getString(res.getColumnIndex(Funcionario.COL_EMP5));
                func.f_empresa=res.getString(res.getColumnIndex(Funcionario.COL_EMP6));
            }while (res.moveToNext());
        }
        res.close();
        return  func;
    }

    public Cursor searchData(String field,String txt){
        SQLiteDatabase db= databasefc.getReadableDatabase();
        Cursor res=db.rawQuery("select * from " + Funcionario.TABLE, null);

        // Get All by Gender
        if(field.toUpperCase().equals("NOMEAPELIDO"))
            res=db.rawQuery("select * from "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP2+" LIKE "+"'%"+txt+"%' or "+Funcionario.COL_EMP3+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("EMAIL"))
           res=db.rawQuery("select * from "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP5+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("EMPRESA"))
            res=db.rawQuery("select * from "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP6+" LIKE "+"'%"+txt+"%'",null);

        return res;
    }

/*********************************************************************************************************************************************************************
         FUNÇÕES AUXILIARES
/********************************************************************************************************************************************************************/

    public ContentValues upDate(Funcionario funcionario) {

        ContentValues values = new ContentValues();
        values.put(Funcionario.COL_EMP2, funcionario.getF_nome());
        values.put(Funcionario.COL_EMP3, funcionario.getF_apelido());
        values.put(Funcionario.COL_EMP4, funcionario.getF_password());
        values.put(Funcionario.COL_EMP5, funcionario.getF_email());
        values.put(Funcionario.COL_EMP6, funcionario.getF_empresa());

        return values;
    }
}
