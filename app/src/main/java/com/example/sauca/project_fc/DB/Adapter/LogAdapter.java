package com.example.sauca.project_fc.DB.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sauca.project_fc.R;

import java.util.ArrayList;


/**
 * Created by Sauca on 15-03-2016.
 */
public class LogAdapter extends ArrayAdapter{

   private ArrayList list = new ArrayList();

   public LogAdapter(Context context, int resource) {
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
            handler.emp_logo = (ImageView) row.findViewById(R.id.rlogo);
            handler.emp_id = (TextView) row.findViewById(R.id.rid);
            handler.emp_nome = (TextView) row.findViewById(R.id.rnome);
            handler.emp_apelido = (TextView) row.findViewById(R.id.rapelido);
            row.setTag(handler);
        } else {
            handler = (DataHandler) row.getTag();
        }

        LogDataProvider dataProvider;
        dataProvider = (LogDataProvider) this.getItem(position);

        handler.emp_logo.setImageResource(dataProvider.getEmp_logo());
        handler.emp_id.setText(dataProvider.getEmp_id());
        handler.emp_nome.setText(dataProvider.getEmp_nome());
        handler.emp_apelido.setText(dataProvider.getEmp_apelido());

//        System.out.println("getview:" + position + " " + convertView);
        return row;
    }

    static class DataHandler {
        ImageView emp_logo;
        TextView emp_id;
        TextView emp_nome;
        TextView emp_apelido;
    }

}
