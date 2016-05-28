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
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.Model.Materia;
import com.example.sauca.appfc.DB.RepoQuery.DiarioRepo;
import com.example.sauca.appfc.R;
import com.example.sauca.appfc.Registo.Material;
import com.example.sauca.appfc.zxing.android.IntentIntegrator;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragMaterial extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    View vi;
    Intent it;
    private Button btAdic;

    private DiarioRepo myDB;
    private Diaria diar;

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

        btAdic = (Button)vi.findViewById(R.id.BT_Adic);

        mOt=getActivity().getIntent().getStringExtra("ot");
        myDB= new DiarioRepo(getActivity());
        diar= myDB.searchDataSingle("OT",mOt);

        if(diar.d_estado.contentEquals("Fechado"))
            btAdic.setVisibility(View.GONE);

        lvMaterial=(ListView)vi.findViewById(R.id.LV_Material);
        loadMatData();

        lvMaterial.setOnItemClickListener(this);
        btAdic.setOnClickListener(this);

        return vi;
    }

    private void loadMatData() {

        int num=0;

        adptMaterial=new MateAdapter(getContext(),R.layout.fragment_material_row);
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

        if(v== vi.findViewById(R.id.BT_Adic)){
            it=new Intent(getContext(), Material.class);
            it.putExtra("otM",mOt);
            startActivity(it);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // selected item
        String selected = ((TextView) view.findViewById(R.id.TV_RMat)).getText().toString();
//        Toast.makeText(getContext(), selected, Toast.LENGTH_SHORT).show();

        it=new Intent(getContext(),Material.class);
        it.putExtra("otM",mOt);
        it.putExtra("Material",selected);
        startActivity(it);
    }
}
