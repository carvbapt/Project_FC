package com.example.sauca.project_fc;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import android.view.Menu;

public class Intervencao extends AppCompatActivity implements View.OnClickListener,TabHost.OnTabChangeListener {

    private ImageButton ibtBack;
    private TabHost tbInt;
    private TabHost.TabSpec tbSpe;
    private TextView tbtv1,tbtv2,tbtv3;


    Button b1,b2;
    Bitmap bp;
    ImageView iv;
    Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervencao);


        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        tbtv1=(TextView)findViewById(R.id.tv1);
        tbtv2=(TextView)findViewById(R.id.tv2);
//        tbtv3=(TextView)findViewById(R.id.tv3);
        tbInt = (TabHost)findViewById(R.id.TB_Intervencao);
        tbInt.setup();

        b1=(Button)findViewById(R.id.button);
        iv=(ImageView)findViewById(R.id.imageView);


        // Add tabs
        tbInt.addTab(tbInt.newTabSpec(getResources().getString(R.string.cliente)).setIndicator(getResources().getString(R.string.cliente)).setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return tbtv1;
            }
        }));
        tbInt.addTab(tbInt.newTabSpec(getResources().getString(R.string.tecnico)).setIndicator(getResources().getString(R.string.tecnico)).setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return tbtv2;
            }
        }));
        tbInt.addTab(tbInt.newTabSpec(getResources().getString(R.string.material)).setIndicator(getResources().getString(R.string.material)).setContent(new TabHost.TabContentFactory() {
            @Override
            public View createTabContent(String tag) {
                return tbtv3;
            }
        }));

        tbtv1.setText(R.string.cliente);

        ibtBack.setOnClickListener(this);
        tbInt.setOnTabChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == findViewById(R.id.BTI_Back))
            startActivity(new Intent(this,Menu.class));
    }

    @Override
    public void onTabChanged(String tabId) {
        if(tabId == getResources().getString(R.string.cliente)){
           tbtv1.setText(R.string.cliente);
        }else if(tabId== getResources().getString(R.string.tecnico)){
            tbtv2.setText(R.string.tecnico);
        }else if(tabId==getResources().getString(R.string.material)){
//            tbtv3.setText(R.string.material);
            it = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(it, 0);
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        iv.setImageBitmap(bp);
    }

}
