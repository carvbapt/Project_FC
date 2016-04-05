package com.example.sauca.project_fc.Intervencao;


import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sauca.project_fc.R;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragTecnico extends Fragment implements View.OnClickListener {


    View vi;
    ImageView iv;
    Button btCam,btPdf;
    Bitmap bp;
    Intent it;

    public FragTecnico() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_tecnico, container, false);
        btCam=(Button)vi.findViewById(R.id.btCam);
        btPdf=(Button)vi.findViewById(R.id.btPdf);
        iv=(ImageView)vi.findViewById(R.id.IVCam);

        btCam.setOnClickListener(this);
        btPdf.setOnClickListener(this);
        return vi;
    }

    @Override
    public void onClick(View v) {

        if(v== vi.findViewById(R.id.btCam)){
            it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(it, 0);
        }
        if(v== vi.findViewById(R.id.btPdf)) {
            if (bp==null)
                Toast.makeText(getContext(), "An error occured while converting the JPG to PDF.", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "The JPG was successfully converted to PDF.", Toast.LENGTH_SHORT).show();
            bp=null;
            iv.setImageBitmap(bp);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }


}
