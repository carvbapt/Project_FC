package com.example.sauca.project_fc.Intervencao;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.project_fc.R;
import com.google.zxing.ResultPoint;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


import junit.framework.TestCase;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragMaterial extends Fragment implements View.OnClickListener {

    View vi;
    Intent it;
    private Button scanBtn;
    private TextView formatTxt, contentTxt;
    Intervencao activ = (Intervencao) getActivity();

    public FragMaterial() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_material, container, false);

        scanBtn = (Button)vi.findViewById(R.id.scan_butt);
        formatTxt = (TextView)vi.findViewById(R.id.scan_form);
        contentTxt = (TextView)vi.findViewById(R.id.scan_cont);

        scanBtn.setOnClickListener(this);

        return vi;
    }


    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.scan_butt)){
//            Toast.makeText(getContext(),"Material",Toast.LENGTH_LONG).show();
            IntentIntegrator scanIntegrator = new IntentIntegrator(FragMaterial.this);
            scanIntegrator.initiateScan();
        }
    }


//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//        if (scanningResult != null) {
//            String scanContent = scanningResult.getContents();
//            String scanFormat = scanningResult.getFormatName();
//            Toast.makeText(getContext()," Codigo - "+scanFormat+" - "+scanContent,Toast.LENGTH_LONG).show();
//        }
//        else{
//            Toast toast = Toast.makeText(getContext(),"No scan data received!", Toast.LENGTH_SHORT);
//            toast.show();
//        }
//    }

    public void doSomething(String form, String cont) {
        // do something in fragment
        formatTxt.setText("FORMAT: " +form);
        contentTxt.setText("CONTENT: " + cont);
    }


}
