package com.example.sauca.project_fc.Intervencao;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.project_fc.MainMenu;
import com.example.sauca.project_fc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragCliente extends Fragment implements View.OnClickListener {

    View vi;
    Intent it;
    Button btCli;

    TextView tvTlf,tvMorada;
    ImageButton ibMap,ibPho;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_cliente, container, false);

        btCli=(Button)vi.findViewById(R.id.btSalvar);

        tvTlf=(TextView)vi.findViewById(R.id.TV_Phone);
        tvTlf.setText("918814212");
        tvMorada=(TextView)vi.findViewById(R.id.TV_Morada);
        tvMorada.setText("Faro");

        ibPho=(ImageButton)vi.findViewById(R.id.IB_Phone);
        ibMap=(ImageButton)vi.findViewById(R.id.IB_Mapa);

        btCli.setOnClickListener(this);
        ibPho.setOnClickListener(this);
        ibMap.setOnClickListener(this);

        return vi;
    }

    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.btSalvar)){
            Toast.makeText(getContext(), "Cliente", Toast.LENGTH_LONG).show();
        }else if(v==vi.findViewById(R.id.IB_Phone)){
            it=new Intent(Intent.ACTION_CALL);
            it.setData(Uri.parse("tel:"+tvTlf.getText()));
            startActivity(it);
            Toast.makeText(getContext(), "tel:"+ tvTlf.getText(), Toast.LENGTH_LONG).show();
        }else if(v==vi.findViewById(R.id.IB_Mapa)){
            Toast.makeText(getContext(), "MAPA", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getContext(), MainMenu.class));
        }
    }

}
