package com.example.sauca.appfc.DB.RepoQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sauca.appfc.DB.DataBaseFC;
import com.example.sauca.appfc.DB.Model.Materia;

import java.io.File;

/**
 * Created by Sauca on 27-05-2016.
 */
public class MateriaRepo {

    private DataBaseFC databasefc;

    public MateriaRepo(Context context) {
        databasefc = new DataBaseFC(context);
    }

    public static boolean DatabaseExist(Context context, String dbName) {
        File dbFile = context.getDatabasePath(dbName);
        return dbFile.exists();
    }

    public int insert(Materia mate) {
        SQLiteDatabase db =  databasefc.getWritableDatabase();
        ContentValues val=upDate(mate);

        // Inserting Row
        long result=db.insert(Materia.TABLE, null, val);
        db.close();
        return (int) result;
    }

    public int updateData(Materia mate){
        SQLiteDatabase db=databasefc.getWritableDatabase();
        ContentValues val=upDate(mate);

        // Update Row
        long result=db.update(Materia.TABLE, val, Materia.MAT_CAMP1 + "=?", new String[]{String.valueOf(mate.m_id)});
        db.close();
        return (int) result;
    }

    public int delete(int id ) {
        SQLiteDatabase db = databasefc.getWritableDatabase();

        // Delete Row
        long result=db.delete(Materia.TABLE, Materia.MAT_CAMP1 + "=?", new String[]{String.valueOf(id)});
        db.close();
        return (int) result;
    }


    /*********************************************************************************************************************************************************************
     FUNÇÕES PROCURA
     /********************************************************************************************************************************************************************/

    public Cursor getAllData(){
        SQLiteDatabase db= databasefc.getWritableDatabase();

        // List All
        return db.rawQuery("select * from " + Materia.TABLE, null);
    }

    public  Materia searchDataSingle(String field, String value){
        SQLiteDatabase db= databasefc.getReadableDatabase();
        Materia mate=new Materia();
        String str=null;

        if(field.equals("OT"))
            str="select ID,OT,MATERIAL,MARCA,MODELO,SERIAL,MAC,IMEI,ICCID,CARTAO,ESTADO FROM "+Materia.TABLE+" WHERE "+Materia.MAT_CAMP2+"=?";
        else if(field.equals("MATERIAL"))
            str="select ID,OT,MATERIAL,MARCA,MODELO,SERIAL,MAC,IMEI,ICCID,CARTAO,ESTADO FROM "+Materia.TABLE+" WHERE "+Materia.MAT_CAMP3+"=?";
        else if(field.equals("ESTADO"))
            str="select ID,OT,MATERIAL,MARCA,MODELO,SERIAL,MAC,IMEI,ICCID,CARTAO,ESTADO FROM "+Materia.TABLE+" WHERE "+Materia.MAT_CAMP11+"=?";

        // Get Single by Gender
        Cursor res = db.rawQuery(str,new String[]{value});

        if(res.moveToFirst()){
            do{
                mate.m_id=res.getInt(res.getColumnIndex(Materia.MAT_CAMP1));
                mate.m_ot=res.getString(res.getColumnIndex(Materia.MAT_CAMP2));
                mate.m_material=res.getString(res.getColumnIndex(Materia.MAT_CAMP3));
                mate.m_marca=res.getString(res.getColumnIndex(Materia.MAT_CAMP4));
                mate.m_modelo=res.getString(res.getColumnIndex(Materia.MAT_CAMP5));
                mate.m_serial=res.getString(res.getColumnIndex(Materia.MAT_CAMP6));
                mate.m_mac=res.getString(res.getColumnIndex(Materia.MAT_CAMP7));
                mate.m_imei=res.getString(res.getColumnIndex(Materia.MAT_CAMP8));
                mate.m_iccid=res.getString(res.getColumnIndex(Materia.MAT_CAMP9));
                mate.m_cartao=res.getString(res.getColumnIndex(Materia.MAT_CAMP10));
                mate.m_estado=res.getString(res.getColumnIndex(Materia.MAT_CAMP11));

            }while (res.moveToNext());
        }
        res.close();
        return  mate;
    }

    public Cursor searchData(String field,String txt){
        SQLiteDatabase db= databasefc.getReadableDatabase();
        Cursor res=db.rawQuery("select * from " + Materia.TABLE, null);

        // Get All by Gender
        if(field.toUpperCase().equals("MATERIAL"))
            res=db.rawQuery("select * from "+Materia.TABLE+" WHERE "+Materia.MAT_CAMP3+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("MODELO"))
            res=db.rawQuery("select * from "+Materia.TABLE+" WHERE "+Materia.MAT_CAMP5+" LIKE "+"'%"+txt+"%'",null);
        if(field.toUpperCase().equals("ESTADO"))
            res=db.rawQuery("select * from "+Materia.TABLE+" WHERE "+Materia.MAT_CAMP11+" LIKE "+"'%"+txt+"%'",null);

        return res;
    }

    /*********************************************************************************************************************************************************************
     FUNÇÕES AUXILIARES
     /********************************************************************************************************************************************************************/

    public ContentValues upDate(Materia mate) {

        ContentValues values = new ContentValues();
        values.put(Materia.MAT_CAMP2, mate.getM_ot());
        values.put(Materia.MAT_CAMP3, mate.getM_material());
        values.put(Materia.MAT_CAMP4, mate.getM_marca());
        values.put(Materia.MAT_CAMP5, mate.getM_modelo());
        values.put(Materia.MAT_CAMP6, mate.getM_serial());
        values.put(Materia.MAT_CAMP7, mate.getM_mac());
        values.put(Materia.MAT_CAMP8, mate.getM_imei());
        values.put(Materia.MAT_CAMP9, mate.getM_iccid());
        values.put(Materia.MAT_CAMP10, mate.getM_cartao());
        values.put(Materia.MAT_CAMP11, mate.getM_estado());

        return values;
    }
}
