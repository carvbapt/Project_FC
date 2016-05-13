package com.example.sauca.project_fc.Intervencao;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.PaintDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.project_fc.DB.Dados;
import com.example.sauca.project_fc.MainMenu;
import com.example.sauca.project_fc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragCliente extends Fragment implements View.OnClickListener {

    View vi;
    Intent it;
    Button btInicio,btFim;

    TextView tvTlf,tvMorada,tvEmpresa,tvLocal;
    ImageButton ibMap,ibPho;
    TabLayout tabLayout;

    LinearLayout fMat,fTec;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_cliente, container, false);

        fMat=(LinearLayout)vi.findViewById(R.id.LL_Material);
        fTec=(LinearLayout)vi.findViewById(R.id.LL_Tecnico);

        btInicio=(Button)vi.findViewById(R.id.BT_Inicio);
        btFim=(Button)vi.findViewById(R.id.BT_Fim);

        tvEmpresa=(TextView)vi.findViewById(R.id.TV_Empresa);
        tvTlf=(TextView)vi.findViewById(R.id.TV_Phone);
        tvMorada=(TextView)vi.findViewById(R.id.TV_Morada);
        tvLocal=(TextView)vi.findViewById(R.id.TV_Local);

        ibPho=(ImageButton)vi.findViewById(R.id.IB_Phone);
        ibMap=(ImageButton)vi.findViewById(R.id.IB_Mapa);

        btInicio.setOnClickListener(this);
        btFim.setOnClickListener(this);
        ibPho.setOnClickListener(this);
        ibMap.setOnClickListener(this);

        loadData(getActivity().getIntent().getStringExtra("ot"));

        return vi;
    }

    private void loadData(String str) {

        TextView tv;

        for(int r=0;r< Dados.Ordens.length;r++){
            if(Dados.Ordens[r].toString().contentEquals(str)){
                Toast.makeText(getActivity(),"Indice - "+ r,Toast.LENGTH_LONG).show();
                tvEmpresa.setText(Dados.Empresas[r].toString());
                tvTlf.setText(Dados.det_empresa[r][3].toString());
                tvMorada.setText(Dados.det_empresa[r][1].toString());
                tvLocal.setText(Dados.det_empresa[r][2].toString());
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.BT_Inicio)) {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");
            btInicio.setText(df.format(c.getTime()));
            btInicio.setBackgroundColor(Color.GREEN);
            btInicio.setTextColor(Color.WHITE);
            Toast.makeText(getContext(), "Inicio", Toast.LENGTH_LONG).show();
        }else if(v== vi.findViewById(R.id.BT_Fim)){
//            Toast.makeText(getActivity(),btInicio.getBackground().toString(),Toast.LENGTH_LONG).show();
            Log.i("CORI",""+btInicio.getSolidColor());
            if(btInicio.getCurrentTextColor()==Color.WHITE) {
                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("HH:mm");
                btFim.setText(df.format(c.getTime()));
                btFim.setBackgroundColor(Color.RED);
                btFim.setTextColor(Color.WHITE);
                Toast.makeText(getContext(), "FIM", Toast.LENGTH_LONG).show();
                Intervencao.ntab.putString("TAB","1");
            }
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
