package com.example.sauca.appfc.Intervencao;


import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
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

import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.RepoQuery.DiarioRepo;
import com.example.sauca.appfc.MainMenu;
import com.example.sauca.appfc.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragCliente extends Fragment implements View.OnClickListener {

    View vi;
    Intent it;
    Button btInicio,btFim;

    TextView tvTlf,tvTlfnome,tvMorada,tvEmpresa,tvLocal,tvTarefa;
    ImageButton ibMap,ibPho;

    LinearLayout fMat,fTec;

    Calendar c;
    SimpleDateFormat df,hf;

    DiarioRepo myDB;
    Diaria diar;

    Dados dado;
    int indice;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_cliente, container, false);

        c = Calendar.getInstance();
        df = new SimpleDateFormat("yyyy.MM.dd");
        hf = new SimpleDateFormat("HH:mm");

        fMat=(LinearLayout)vi.findViewById(R.id.LL_Material);
        fTec=(LinearLayout)vi.findViewById(R.id.LL_Tecnico);

        btInicio=(Button)vi.findViewById(R.id.BT_Inicio);
        btFim=(Button)vi.findViewById(R.id.BT_Fim);

        tvEmpresa=(TextView)vi.findViewById(R.id.TV_Empresa);
        tvTlf=(TextView)vi.findViewById(R.id.TV_Phone);
        tvTlfnome=(TextView)vi.findViewById(R.id.TV_PhoneName);
        tvMorada=(TextView)vi.findViewById(R.id.TV_Morada);
        tvLocal=(TextView)vi.findViewById(R.id.TV_Local);
        tvTarefa=(TextView)vi.findViewById(R.id.TV_Tarefa);

        ibPho=(ImageButton)vi.findViewById(R.id.IB_Phone);
        ibMap=(ImageButton)vi.findViewById(R.id.IB_Mapa);

        myDB= new DiarioRepo(getActivity());
        diar= myDB.searchDataSingle("OT",getActivity().getIntent().getStringExtra("ot"));

        loadData(diar);

        btInicio.setOnClickListener(this);
        btFim.setOnClickListener(this);
        ibPho.setOnClickListener(this);
        ibMap.setOnClickListener(this);

//        loadData(getActivity().getIntent().getStringExtra("ot"));

        return vi;
    }

    private void loadData(Diaria dia) {

        TextView tv;
        int ind=Integer.parseInt(dia.d_empresa);// dia.d_id;

//                Toast.makeText(getActivity(),"Indice - "+ r,Toast.LENGTH_LONG).show();
                tvEmpresa.setText(Dados.Empresas[ind]);
                tvMorada.setText(Dados.det_empresa[ind][1]);
                tvLocal.setText(Dados.det_empresa[ind][2]);
                tvTlfnome.setText(Dados.det_empresa[ind][4]);
                tvTlf.setText(Dados.det_empresa[ind][3]);
                tvTarefa.setText(dia.d_tarefa);

                Log.i("DATA- ",""+dia.d_data.compareTo(df.format(c.getTime())));

                if(dia.d_data.compareTo(df.format(c.getTime()))>0) {
                    btInicio.setTextColor(Color.GRAY);
                    btInicio.setEnabled(false);
                    btFim.setTextColor(Color.GRAY);
                    btFim.setEnabled(false);
                }else {
                    if(dia.getD_estado().contains("Fechado")){
                        btInicio.setText(dia.getD_inicio());
                        btInicio.setBackgroundColor(Color.parseColor("#038f03"));
                        btInicio.setTextColor(Color.WHITE);
                        btInicio.setEnabled(false);
                        btFim.setText(dia.getD_fim());
                        btFim.setBackgroundColor(Color.RED);
                        btFim.setTextColor(Color.WHITE);
                        btFim.setEnabled(false);
                    }
                    if(!dia.getD_inicio().contentEquals("") ){
                        btInicio.setText(dia.getD_inicio());
                        btInicio.setBackgroundColor(Color.parseColor("#038f03"));
                        btInicio.setTextColor(Color.WHITE);
                        btInicio.setEnabled(false);
                    }
                }
    }

    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.BT_Inicio)) {
            btInicio.setText(hf.format(c.getTime()));
            diar.d_inicio=btInicio.getText().toString();
            myDB.updateData(diar);
            btInicio.setBackgroundColor(Color.parseColor("#038f03"));
            btInicio.setTextColor(Color.WHITE);
            btInicio.setEnabled(false);
            loadActivity();
//            Toast.makeText(getContext(), "Inicio", Toast.LENGTH_LONG).show();
        }else if(v== vi.findViewById(R.id.BT_Fim)){
//            Toast.makeText(getActivity(),btInicio.getBackground().toString(),Toast.LENGTH_LONG).show();
            Log.i("CORI",""+btInicio.getSolidColor());
            if(!btInicio.getText().toString().contains("Inicio")) {
                btFim.setText(hf.format(c.getTime()));
                diar.d_fim=btFim.getText().toString();
                diar.d_estado="Fechado";
                myDB.updateData(diar);
                btFim.setBackgroundColor(Color.RED);
                btFim.setTextColor(Color.WHITE);
                btInicio.setEnabled(false);
//                Toast.makeText(getContext(), "FIM", Toast.LENGTH_LONG).show();
            }else
                Toast.makeText(getContext(), "Inicie Serviço", Toast.LENGTH_LONG).show();
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


    // Refresh Activity
    private void loadActivity() {
        Intent intent = getActivity().getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_NO_ANIMATION);
        getActivity().overridePendingTransition(0, 0);
        getActivity().finish();
        getActivity().overridePendingTransition(0, 0);
        startActivity(intent);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*     MENSAGENS  ALERT DIALOG   */
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
