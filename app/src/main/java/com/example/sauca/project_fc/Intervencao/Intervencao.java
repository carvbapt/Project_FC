package com.example.sauca.project_fc.Intervencao;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sauca.project_fc.DB.Dados;
import com.example.sauca.project_fc.MainMenu;
import com.example.sauca.project_fc.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Intervencao extends AppCompatActivity implements  View.OnClickListener {

    //S@C
    private ImageButton ibtBack;
    TextView tvOt;
    FragMaterial fragMat;

    public static Bundle ntab;

    Intent it;
    int r;

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervencao);

        ntab=new Bundle();
        ntab.putString("TAB",null);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        ibtBack=(ImageButton)findViewById(R.id.BTI_Back);
        ibtBack.setOnClickListener(this);

//        it= getIntent();
//        r=it.getIntExtra("pos",0);
//        it.getExtras().remove("pos");
//        Toast.makeText(getBaseContext(),"Linha - "+ r,Toast.LENGTH_LONG).show();

        tvOt=(TextView)findViewById(R.id.TV_Ot);
        tvOt.setText(getIntent().getStringExtra("ot"));


//        fragMat = (FragMaterial)getSupportFragmentManager().findFragmentById(R.id.FragMaterial);
//        Toast.makeText(this, "Frag - "+,Toast.LENGTH_LONG).show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_intervencao, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        if (v == findViewById(R.id.BTI_Back)) {
            finish();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return new FragCliente();
                case 1:
                    return new FragTecnico();
                default:
                    return new FragMaterial();
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
                return 3;

        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Cliente";
                case 1:
                    return "Técnico";
                case 2:
                    return "Material";
            }
            return null;
        }
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
//
//        if (resultCode == 0x0000c0de02) {
//            if (scanningResult != null) {
//                String scanContent = scanningResult.getContents();
//                String scanFormat = scanningResult.getFormatName();
//                Toast.makeText(this, " Codigo - " + scanFormat + " - " + scanContent, Toast.LENGTH_LONG).show();
//                if (fragMat != null) {
//                    fragMat.doSomething(scanFormat, scanContent);
//                }
////
//            } else {
//                Toast toast = Toast.makeText(this, "No scan data received!", Toast.LENGTH_SHORT);
//                toast.show();
//            }
//        }
//    }
}
