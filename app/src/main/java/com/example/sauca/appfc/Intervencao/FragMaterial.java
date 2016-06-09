package com.example.sauca.appfc.Intervencao;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.appfc.DB.Adapter.MateAdapter;
import com.example.sauca.appfc.DB.Dados;
import com.example.sauca.appfc.DB.Model.Diaria;
import com.example.sauca.appfc.DB.Model.Materia;
import com.example.sauca.appfc.DB.RepoQuery.DiarioRepo;
import com.example.sauca.appfc.DB.RepoQuery.MateriaRepo;
import com.example.sauca.appfc.R;
import com.example.sauca.appfc.Registo.Material;
import com.example.sauca.appfc.zxing.android.IntentIntegrator;

import java.util.ArrayList;

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
    ArrayList mId = new ArrayList();

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

        it=new Intent(getContext(), Material.class);
        it.putExtra("otM",mOt);

        lvMaterial.setOnItemClickListener(this);
        btAdic.setOnClickListener(this);

        return vi;
    }

    private void loadMatData() {

        int num=0;
        MateriaRepo myDBM;
        Cursor pnt;

        myDBM= new MateriaRepo(getContext());
        pnt= myDBM.getAllData();

        if(pnt.getCount()==0){
            // show message
            if(!myDBM.DatabaseExist(getContext(),"DBFastcall.db")) {
                showMessage("Base de Dados", "Não existe");
            }
            return;
        }

        adptMaterial=new MateAdapter(getContext(),R.layout.fragment_material_row);
        lvMaterial.setAdapter(adptMaterial);

        num=0;

        while(pnt.moveToNext()){
            if(pnt.getString(1).contentEquals(mOt)) {
                Materia dtprov = new Materia(pnt.getInt(0),pnt.getString(2),pnt.getString(4),pnt.getString(10));
                adptMaterial.add(dtprov);
                mId.add(pnt.getInt(0));
            }
        }
    }

    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.BT_Adic))
            it.putExtra("IndM","add");
            startActivity(it);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // selected item
        it.putExtra("IndM",mId.get(position).toString());
        startActivity(it);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*     MENSAGENS  ALERT DIALOG   */
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void showMessage(String title, String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
