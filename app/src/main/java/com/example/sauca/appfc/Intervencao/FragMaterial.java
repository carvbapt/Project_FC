package com.example.sauca.appfc.Intervencao;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.appfc.DB.Adapter.MateAdapter;
import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.DB.Model.Materia;
import com.example.sauca.appfc.R;
import com.example.sauca.appfc.Registo.Material;
import com.example.sauca.appfc.zxing.android.IntentIntegrator;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragMaterial extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    View vi;
    Intent it;
    private Button scanBtn;
    private TextView formatTxt, contentTxt;

    private ListView lvMaterial;
    private MateAdapter adptMaterial;
    String mOt;
    int num;

    public FragMaterial() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_material, container, false);

        scanBtn = (Button)vi.findViewById(R.id.scan_butt);

        mOt=getActivity().getIntent().getStringExtra("ot");

        lvMaterial=(ListView)vi.findViewById(R.id.LV_Material);
        loadMatData();

        lvMaterial.setOnItemClickListener(this);

        scanBtn.setOnClickListener(this);

        return vi;
    }

    private void loadMatData() {

        int num=0;

        adptMaterial=new MateAdapter(getContext(),R.layout.fragment_material_row);
//        loadMatData();
        num=0;
        for(int r=0;r< Dados.Material.length;r++) {
            if (Dados.Ordens[Integer.parseInt(Dados.Material[r][0])].contentEquals(mOt)) {
                Materia dtprov = new Materia();
                dtprov.setM_material(Dados.Material[r][1]);
                dtprov.setM_modelo(Dados.Material[r][2]);
                dtprov.setM_estado(Dados.Material[r][4]);
                num = num + 1;
//                Toast.makeText(getActivity(), "Linhas - " + num, Toast.LENGTH_LONG).show();
                adptMaterial.add(dtprov);
            }
        }
        lvMaterial.setAdapter(adptMaterial);
    }

    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.scan_butt)){
            it=new Intent(getContext(), Material.class);
            it.putExtra("otM",mOt);
            startActivity(it);
        }
    }


    public void doSomething(String form, String cont) {
        // do something in fragment
        formatTxt.setText("FORMAT: " +form);
        contentTxt.setText("CONTENT: " + cont);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(getContext(),"Posição "+position, Toast.LENGTH_LONG).show();
        showConfirm(position);
    }

    /*********************************************************************************************************************************************************************
     FUNÇÕES AUXILIARES
     /********************************************************************************************************************************************************************/

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNeutralButton("OK", null);
        builder.show();
    }

    public void showConfirm(final int pos){
        AlertDialog.Builder bld = new AlertDialog.Builder(getContext());
        bld.setTitle("Eliminar registo " + pos);
        bld.setMessage("Confirma Eliminação?");
        bld.setNegativeButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getContext(),"Reg - "+pos+" Eliminado com sucesso" , Toast.LENGTH_LONG).show();
            }
        });
        bld.setPositiveButton("Não",null);
        bld.show();
    }
}
