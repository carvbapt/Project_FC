package com.example.sauca.project_fc.Intervencao;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sauca.project_fc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragMaterial extends Fragment implements View.OnClickListener {

    View vi;
    Intent it;
    Button btMat;

    public FragMaterial() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_material, container, false);
        btMat=(Button)vi.findViewById(R.id.btMat);
        btMat.setOnClickListener(this);
        return vi;
    }

    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.btMat)){
            Toast.makeText(getContext(),"Material",Toast.LENGTH_LONG).show();
        }
    }
}
