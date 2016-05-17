package com.example.sauca.appfc.DB.RepoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sauca.appfc.DB.DataBaseFC;
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.Intervencao.Diario;

import java.io.File;

/**
 * Created by Sauca on 16-05-2016.
 */
public class DiarioRepo {

    private DataBaseFC databasefc;

    public DiarioRepo(Context context){
        databasefc= new DataBaseFC(context);
    }

    public static boolean DatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public int insert(Diaria diar) {
        SQLiteDatabase db =  databasefc.getWritableDatabase();
        ContentValues val=upDate(diar);

        // Inserting Row
        long result=db.insert(Diaria.TABLE, null, val);
        db.close();
        return (int) result;
    }

    public int updateData(Diaria diar){
        SQLiteDatabase db=databasefc.getWritableDatabase();
        ContentValues val=upDate(diar);

        // Update Row
        long result=db.update(Diaria.TABLE, val, Diaria.DIA_EMP1 + "=?", new String[]{String.valueOf(diar.d_id)});
        db.close();
        return (int) result;
    }

    public int delete(int id ) {
        SQLiteDatabase db = databasefc.getWritableDatabase();

        // Delete Row
        long result=db.delete(Diaria.TABLE, Diaria.DIA_EMP1 + "=?", new String[]{String.valueOf(id)});
        db.close();
        return (int) result;
    }



    /*********************************************************************************************************************************************************************
     FUNÇÕES PROCURA
     /********************************************************************************************************************************************************************/

    public Cursor getAllData(){
        SQLiteDatabase db= databasefc.getWritableDatabase();

        // List All
        return db.rawQuery("select * from " + Diaria.TABLE, null);
    }

    public  Diaria searchDataSingle(String field, String value){
        SQLiteDatabase db= databasefc.getReadableDatabase();
        Diaria diar=new Diaria();
        String str=null;

        if(field.equals("ID"))
            str="select ID,OT,DATA,HORA,ESTADO,INICIO,FIM,EMPRESA FROM "+Diaria.TABLE+" WHERE "+Diaria.DIA_EMP1+"=?";
        else if(field.equals("OT"))
            str="select ID,OT,DATA,HORA,ESTADO,INICIO,FIM,EMPRESA FROM "+Diaria.TABLE+" WHERE "+Diaria.DIA_EMP2+"=?";
        else if(field.equals("ESTADO"))
            str="select ID,OT,DATA,HORA,ESTADO,INICIO,FIM,EMPRESA FROM "+Diaria.TABLE+" WHERE "+Diaria.DIA_EMP5+"=?";

        // Get Single by Gender
        Cursor res = db.rawQuery(str,new String[]{value});

        if(res.moveToFirst()){
            do{
                diar.d_id=res.getInt(res.getColumnIndex(Diaria.DIA_EMP1));
                diar.d_ot=res.getString(res.getColumnIndex(Diaria.DIA_EMP2));
                diar.d_data=res.getString(res.getColumnIndex(Diaria.DIA_EMP3));
                diar.d_hora=res.getString(res.getColumnIndex(Diaria.DIA_EMP4));
                diar.d_estado=res.getString(res.getColumnIndex(Diaria.DIA_EMP5));
                diar.d_inicio=res.getString(res.getColumnIndex(Diaria.DIA_EMP6));
                diar.d_fim=res.getString(res.getColumnIndex(Diaria.DIA_EMP7));
                diar.d_empresa=res.getString(res.getColumnIndex(Diaria.DIA_EMP8));
            }while (res.moveToNext());
        }
        res.close();
        return  diar;
    }

    public Cursor searchData(String field,String txt){
        SQLiteDatabase db= databasefc.getReadableDatabase();
        Cursor res=db.rawQuery("select * from " + Diaria.TABLE, null);

        // Get All by Gender
        if(field.toUpperCase().equals("OT"))
            res=db.rawQuery("select * from "+Diaria.TABLE+" WHERE "+Diaria.DIA_EMP2+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("DATA"))
            res=db.rawQuery("select * from "+Diaria.TABLE+" WHERE "+Diaria.DIA_EMP3+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("ESTADO"))
            res=db.rawQuery("select * from "+Diaria.TABLE+" WHERE "+Diaria.DIA_EMP5+" LIKE "+"'%"+txt+"%'",null);

        return res;
    }

    /*********************************************************************************************************************************************************************
     FUNÇÕES AUXILIARES
     /********************************************************************************************************************************************************************/

    public ContentValues upDate(Diaria diar) {

        ContentValues values = new ContentValues();
        values.put(Diaria.DIA_EMP2, diar.getD_ot());
        values.put(Diaria.DIA_EMP3, diar.getD_data());
        values.put(Diaria.DIA_EMP4, diar.getD_hora());
        values.put(Diaria.DIA_EMP5, diar.getD_estado());
        values.put(Diaria.DIA_EMP6, diar.getD_inicio());
        values.put(Diaria.DIA_EMP7, diar.getD_fim());
        values.put(Diaria.DIA_EMP8, diar.getD_empresa());

        return values;
    }
}
