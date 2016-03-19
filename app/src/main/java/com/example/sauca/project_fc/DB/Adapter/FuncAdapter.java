package com.example.sauca.project_fc.DB.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sauca.project_fc.DB.Model.Funcionario;
import com.example.sauca.project_fc.R;

import java.util.ArrayList;

/**
 * Created by Sauca on 19-03-2016.
 */
public class FuncAdapter extends ArrayAdapter {
    private ArrayList list = new ArrayList();

    public FuncAdapter(Context context, int resource) {
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
        View row;
        row = convertView;
        DataHandler handler;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.activity_login_list_row, parent, false);
            handler = new DataHandler();
            handler.func_logo = (ImageView) row.findViewById(R.id.rlogo);
            handler.func_id = (TextView) row.findViewById(R.id.rid);
            handler.func_nome = (TextView) row.findViewById(R.id.rnome);
            handler.func_apelido = (TextView) row.findViewById(R.id.rapelido);
            row.setTag(handler);
        } else {
            handler = (DataHandler) row.getTag();
        }

        Funcionario dataProvider;
        dataProvider=(Funcionario) this.getItem(position);

        handler.func_logo.setImageResource(dataProvider.getEmp_logo());
        handler.func_id.setText(Integer.toString(dataProvider.getF_id()));
        handler.func_nome.setText(dataProvider.getF_nome());
        handler.func_apelido.setText(dataProvider.getF_apelido());

//        System.out.println("getview:" + position + " " + convertView);
        return row;
    }

    static class DataHandler {
        ImageView func_logo;
        TextView func_id;
        TextView func_nome;
        TextView func_apelido;
    }
}
