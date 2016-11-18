package com.example.yazirsolis.mytakepicture_2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    @BindView(R.id.ivFoto)
    ImageView ivFoto;
    @BindView(R.id.btnTomarFoto)
    Button btnImagen1;
    @BindView (R.id.btnMostrarFoto)
    Button btnMostrarFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTomarFoto)
    public void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //obtiene el directorio de la tarjeta SD
        File ruta_foto = Environment.getExternalStorageDirectory();
        //crea el objeto tipo archivo y se le asigna el path y nombre del archivo
        File photoFile = new File(ruta_foto.getAbsolutePath(), "foto.jpg");

        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                photoFile.createNewFile();
                FileOutputStream fosPicture = new FileOutputStream(photoFile);
                fosPicture.flush();

            } catch (IOException ex) {
                // Error occurred while creating the File
            }

            Uri photoURI = Uri.fromFile(photoFile);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @OnClick(R.id.btnMostrarFoto)
    public void mostrarFoto(){
        File ruta_foto = Environment.getExternalStorageDirectory();
        File photoFile = new File(ruta_foto.getAbsolutePath(), "foto.jpg");
        Bitmap bitmapFoto = BitmapFactory.decodeFile(photoFile.getAbsolutePath());
        ivFoto.setImageBitmap(bitmapFoto);
    }
}
