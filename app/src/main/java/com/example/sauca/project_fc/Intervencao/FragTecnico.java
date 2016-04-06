package com.example.sauca.project_fc.Intervencao;

import android.content.ActivityNotFoundException;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sauca.project_fc.R;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragTecnico extends Fragment implements View.OnClickListener {

    View vi;
    ImageView iv;
    Button btCam,btPdf,btVPdf;
    ImageButton ibEmail;
    Bitmap bp;
    Intent it;

    File myFile;
    String mypath;

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
        btVPdf=(Button)vi.findViewById(R.id.btVPdf);
        ibEmail=(ImageButton)vi.findViewById(R.id.ibEmail);
        iv=(ImageView)vi.findViewById(R.id.IVCam);

        btCam.setOnClickListener(this);
        btPdf.setOnClickListener(this);
        btVPdf.setOnClickListener(this);
        ibEmail.setOnClickListener(this);
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
                Toast.makeText(getContext(), "Não Existe Imagem", Toast.LENGTH_SHORT).show();
            else {
                try {
                    createPDF();
                    promptForMessage();
                    Toast.makeText(getContext(), "Imagem convertida para PDF com sucesso", Toast.LENGTH_SHORT).show();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
            }
            bp=null;
        }
        if(v==vi.findViewById(R.id.btVPdf)){
            Toast.makeText(getContext(), "Visualizar PDF", Toast.LENGTH_SHORT).show();
            promptForNextAction();
        }
        if(v==vi.findViewById(R.id.ibEmail)){
            Toast.makeText(getContext(), "Enviar EMAIL", Toast.LENGTH_SHORT).show();
            emailNote();
        }
    }

// **********************************************************************************************************************************
//          Imagem
// **********************************************************************************************************************************

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        bp = (Bitmap) data.getExtras().get("data");
        bp= getResizedBitmap(bp,600,800);
        iv.setImageBitmap(bp);
    }

    // Resize Imagem
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

// **********************************************************************************************************************************
//          PDF
// **********************************************************************************************************************************

    // Criar PDF
    private void createPDF() throws FileNotFoundException, DocumentException {

        // Criar directorio
        //Create a directory for your PDF
        File pdfDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOCUMENTS), "Fastcall");
        if (!pdfDir.exists()){
            pdfDir.mkdir();
        }

//        File path=new  getFilesDir();
//        OutputStreamWriter out;
//        try {
//            File f = new File(path.getCanonicalPath() + "/myfile.txt");
//            out = new OutputStreamWriter(openFileOutput( f.getPath(), MODE_PRIVATE));
//            out.write("test");
//            out.close();
//        }



        // Criar nome ficheiro
        ContextWrapper pdfFolder = new ContextWrapper(getContext());
        Date date = new Date() ;
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);
        mypath=pdfFolder.getFilesDir().getPath();
        myFile = new File(mypath, timeStamp + ".pdf");
        mypath=mypath+myFile;
        Toast.makeText(getContext(), "PATH - "+myFile, Toast.LENGTH_SHORT).show();

        OutputStream output= new FileOutputStream(myFile);

        try {
            Document  document = new Document();

            PdfWriter.getInstance(document, output);
            document.open();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bp.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();
            addImage(document,byteArray);
//            document.add(new Paragraph("Sandro Augusto Carvalho"));
//            document.add(new Paragraph("Av. Republica nº 100"));
            document.close();
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
        it.setDataAndType(Uri.fromFile(myFile), "application/pdf");
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
        Uri uri = Uri.parse(String.valueOf(myFile));
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
        builder.setMessage(mypath);
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
}
