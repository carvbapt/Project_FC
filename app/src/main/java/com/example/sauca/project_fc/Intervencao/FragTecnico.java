package com.example.sauca.project_fc.Intervencao;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sauca.project_fc.R;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragTecnico extends Fragment implements View.OnClickListener {

    View vi;
    ImageView iv;
    Button btVPdf;
    ImageButton ibCam,ibGal,ibPdf,ibEmail;
    Bitmap bp;
    Intent it;

    File myPathFile,myPathPDF;
    String myFile=null,myFileGal=null;

    File rootpath;


    public static final int CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE = 100;
    public static final int IMAGE_FULLSIZE_FROM_GALLERY = 200;

    public FragTecnico() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        vi=inflater.inflate(R.layout.fragment_tecnico, container, false);
        ibCam=(ImageButton)vi.findViewById(R.id.ibCamera);
        ibGal=(ImageButton)vi.findViewById(R.id.ibGaleria);
        ibPdf=(ImageButton)vi.findViewById(R.id.ibPdf);
        btVPdf=(Button)vi.findViewById(R.id.btVPdf);
        ibEmail=(ImageButton)vi.findViewById(R.id.ibEmail);
        iv=(ImageView)vi.findViewById(R.id.IVCam);

        ibCam.setOnClickListener(this);
        ibGal.setOnClickListener(this);
        ibPdf.setOnClickListener(this);
        ibEmail.setOnClickListener(this);
        btVPdf.setOnClickListener(this);
        return vi;
    }

    @Override
    public void onClick(View v) {

        // Criar directorio
        rootpath = new File(Environment.getExternalStorageDirectory(),"Fastcall");
        if (!rootpath.exists()){
            rootpath.mkdir();
        }
        if(v== vi.findViewById(R.id.ibCamera)){
            showInputDialog();
        }else if(v== vi.findViewById(R.id.ibGaleria)) {
            Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, IMAGE_FULLSIZE_FROM_GALLERY);
        }else if(v== vi.findViewById(R.id.ibPdf)) {
            if (bp==null)
                Toast.makeText(getContext(), "Não Existe Imagem", Toast.LENGTH_SHORT).show();
            else {
                try {
                    if(myFileGal==null)
                        createPDF(String.valueOf(myPathFile));
                    else {
                        createPDF(String.valueOf(myFileGal));
                        myFileGal=null;
                    }
                    promptForMessage();
                    Toast.makeText(getContext(), "Imagem convertida para PDF com sucesso", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
        }else if(v==vi.findViewById(R.id.ibEmail)){
            Toast.makeText(getContext(), "Enviar EMAIL", Toast.LENGTH_SHORT).show();
            emailNote();
        }else if(v==vi.findViewById(R.id.btVPdf)) {
            Toast.makeText(getContext(), "Visualizar PDF", Toast.LENGTH_SHORT).show();
            promptForNextAction();
        }
    }

// **********************************************************************************************************************************
//          Imagem
// **********************************************************************************************************************************

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE)
        {
            bp = decodeSampledBitmapFromFile(myPathFile.getAbsolutePath(), 1000, 700);
            iv.setImageBitmap(bp);
        }else if (requestCode == IMAGE_FULLSIZE_FROM_GALLERY ) {
            Uri selectedImage = data.getData();
            String[] filePath = { MediaStore.Images.Media.DATA };
            Cursor c = getActivity().getContentResolver().query(selectedImage,filePath, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString(columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            Log.w("ImageGallery", picturePath+"");
            myFileGal=picturePath;
            iv.setImageBitmap(thumbnail);
        }
    }

    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight)
    { // BEST QUALITY MATCH

        //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight)
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth)
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }

        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }


// **********************************************************************************************************************************
//          PDF
// **********************************************************************************************************************************

    // Criar PDF
    private void createPDF(String jpgFilePath) throws FileNotFoundException, DocumentException {

        // Criar directorio
        File rootpath = new File(Environment.getExternalStorageDirectory(),"Fastcall");
        if (!rootpath.exists()){
            rootpath.mkdir();
        }
        // Criar nome ficheiro
        myPathPDF=new File(rootpath,myFile+".pdf");
        Toast.makeText(getContext(), "PATH - "+myPathPDF, Toast.LENGTH_SHORT).show();

        OutputStream output= new FileOutputStream(myPathPDF);

        try {
            Document  document = new Document();

            PdfWriter.getInstance(document, output);
            Image image = Image.getInstance(jpgFilePath);
            image.scaleAbsolute(480,800);
            document.open();
            document.add(image);
            document.close();
            promptForMessage();
            Toast.makeText(getContext(), "Imagem convertida para PDF com sucesso", Toast.LENGTH_SHORT).show();
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//            bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] byteArray = stream.toByteArray();
//            addImage(document,byteArray);
////            document.add(new Paragraph("Sandro Augusto Carvalho"));
////            document.add(new Paragraph("Av. Republica nº 100"));
//            document.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addImage(Document document,byte[] byteArray)
    {
        Image image = null;
        try
        {
            image = Image.getInstance(byteArray);
        }
        catch (BadElementException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // image.scaleAbsolute(150f, 150f);
        try
        {
            document.add(image);
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // Visualizar PDF
    private void viewPDF(){
        it= new Intent(Intent.ACTION_VIEW);
        it.setDataAndType(Uri.fromFile(myPathPDF), "application/pdf");
        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try {
            getContext().startActivity(it);
        }
        catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "No application available to view PDF", Toast.LENGTH_LONG).show();
        }
    }

// **********************************************************************************************************************************
//          Email
// **********************************************************************************************************************************

    private void emailNote()
    {
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_SUBJECT,"Titulo");
        email.putExtra(Intent.EXTRA_TEXT, "Texto a escrever");
        Uri uri =Uri.fromFile(myPathPDF);
        email.putExtra(Intent.EXTRA_STREAM, uri);
        email.setType("message/rfc822");
        startActivity(email);
    }

// **********************************************************************************************************************************
//          Dialog
// **********************************************************************************************************************************

    private void promptForMessage()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(" Directorio");
        builder.setMessage("PATH - "+myPathFile);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void promptForNextAction()
    {
        final String[] options = { getString(R.string.label_email), getString(R.string.label_preview),
                getString(R.string.label_cancel) };

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Note Saved, What Next?");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (options[which].equals(getString(R.string.label_email))) {
                    emailNote();
                } else if (options[which].equals(getString(R.string.label_preview))) {
                    viewPDF();
                } else if (options[which].equals(getString(R.string.label_cancel))) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    protected void showInputDialog(){

        // get prompts.xml view
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View promptView = layoutInflater.inflate(R.layout.dialog_input, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setView(promptView);

        final EditText editText = (EditText) promptView.findViewById(R.id.ET_ficheiro);
        // setup a dialog window
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        myFile= String.valueOf(editText.getText());//+ ".pdf";
                        it = new Intent("android.media.action.IMAGE_CAPTURE");
                        myPathFile = new File(rootpath, myFile +".jpg");
                        it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(myPathFile));
                        startActivityForResult(it, CAPTURE_IMAGE_FULLSIZE_ACTIVITY_REQUEST_CODE);
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        // create an alert dialog
        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }

}
