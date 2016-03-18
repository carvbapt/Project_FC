package com.example.sauca.project_fc.DB.RepoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

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

    public boolean updateData(String nome, String apelido,String password,String email,String empresa){
        SQLiteDatabase db=databasefc.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Funcionario.COL_EMP2,nome);
        values.put(Funcionario.COL_EMP3,apelido);
        values.put(Funcionario.COL_EMP4,password);
        values.put(Funcionario.COL_EMP5,email);
        values.put(Funcionario.COL_EMP6,empresa);
//        db.update(Funcionario.TABLE, values, "ID=?",)
        return true;
    }

    public  Funcionario searchDataID(int id){

        SQLiteDatabase db= databasefc.getReadableDatabase();
        Funcionario func=new Funcionario();
        Cursor res = db.rawQuery("select ID,NOME,APELIDO,PASSWORD,EMAIL,EMPRESA FROM "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP1+"=?",new String[]{String.valueOf(id)});

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
        db.close();
        return  func;
    }

    public Cursor searchDataLogin(String field,String txt){
        SQLiteDatabase db= databasefc.getReadableDatabase();
        Cursor res=db.rawQuery("select * from "+ Funcionario.TABLE,null);

        if(field.toUpperCase().equals("NOMEAPELIDO"))
            res=db.rawQuery("select * from "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP2+" LIKE "+"'%"+txt+"%' or "+Funcionario.COL_EMP3+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("EMAIL"))
           res=db.rawQuery("select * from "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP5+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("EMPRESA"))
            res=db.rawQuery("select * from "+Funcionario.TABLE+" WHERE "+Funcionario.COL_EMP6+" LIKE "+"'%"+txt+"%'",null);

        return res;
    }

    public int delete(int id ) {
        SQLiteDatabase db = databasefc.getWritableDatabase();
        int res=db.delete(Funcionario.TABLE,Funcionario.COL_EMP1+"=?",new String[]{String.valueOf(id)});
        db.close();
        return res;
    }
}
