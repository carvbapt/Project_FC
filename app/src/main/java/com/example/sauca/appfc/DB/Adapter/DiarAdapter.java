package com.example.sauca.appfc.DB.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.R;

import java.util.ArrayList;


/**
 * Created by Sauca on 10-05-2016.
 */
public class DiarAdapter extends ArrayAdapter {

    private ArrayList list = new ArrayList();
    View row;

    public DiarAdapter(Context context, int resource) {
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
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_diario_row, parent, false);
            handler = new DataHandler();
            handler.com_ot = (TextView) row.findViewById(R.id.TV_Ot);
            handler.com_hora = (TextView) row.findViewById(R.id.TV_Hora);
            handler.com_estado = (TextView) row.findViewById(R.id.TV_Estado);
            row.setTag(handler);
        }else {
            handler = (DataHandler) row.getTag();
        }

        Diaria diariaprovider;
        diariaprovider=(Diaria)this.getItem(position);
        handler.com_ot.setText(diariaprovider.getD_ot());
        handler.com_hora.setText(diariaprovider.getD_hora());
        handler.com_estado.setText(diariaprovider.getD_estado());

        return  row;
    }

    static class DataHandler{
        TextView com_ot;
        TextView com_hora;
        TextView com_estado;
    }
}
