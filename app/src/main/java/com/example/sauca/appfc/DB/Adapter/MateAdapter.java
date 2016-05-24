package com.example.sauca.appfc.DB.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sauca.appfc.DB.Model.Materia;
import com.example.sauca.appfc.R;

import java.util.ArrayList;

/**
 * Created by Sauca on 24-05-2016.
 */
public class MateAdapter extends ArrayAdapter {

    private ArrayList list= new ArrayList();
    View row;

    public MateAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        row=convertView;
        DataHandler handler;
        if(convertView==null) {
            LayoutInflater inflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.fragment_material_row, parent, false);
            handler = new DataHandler();
            handler.mat_material= (TextView) row.findViewById(R.id.TV_RMat);
            handler.mat_modelo = (TextView) row.findViewById(R.id.TV_RMod);
            handler.mat_estado = (TextView) row.findViewById(R.id.TV_REstMat);
            row.setTag(handler);
        }else {
            handler = (DataHandler) row.getTag();
        }

        Materia materia;
        materia=(Materia)this.getItem(position);

        handler.mat_material.setText(materia.getM_material());
        handler.mat_modelo.setText(materia.getM_modelo());
        handler.mat_estado.setText(materia.getM_estado());

        return  row;
    }

    static class DataHandler{
        TextView mat_material;
        TextView mat_modelo;
        TextView mat_estado;
    }

}
