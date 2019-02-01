package com.example.franco;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity implements View.OnClickListener {
    private  TextView nomeUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Log.d("myTap", "sono nella terza");

        Button mybutton=findViewById(R.id.imagesearch);
        mybutton.setOnClickListener(this);
        //controllare se su Fotogram c'è sui fragments


    }

    public void addToArray(View v){
        Mymodel m = Mymodel.getMyModel();
        //Button button= findViewById(R.id.addToArray);
        Log.d("myTap", "il bottone funziona");

        String name = ((EditText)findViewById(R.id.nome)).getText().toString();

        m.addUser(name);//aggiungo il nome all'array
        Log.d("myTap", "nome:"+name+" aggiunto");
        ArrayList<String> utenti=m.getUsers();
        Log.d("myTap", "nome:"+utenti.get(0)+" presente");
        //fino a qui tutto ok
    }


    @Override
    public void onClick(View v) {
            selectImage("profilo");
    }

    public static final int PICK_IMAGE = 1;
    String imgProfiloB64 = "";
    String imgPostB64 = "";
    String tipoImageSelect = "";

    public void selectImage(String tipo){
        tipoImageSelect = tipo;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                return;
            }
            try {
                InputStream is = this.getContentResolver().openInputStream(data.getData());
                //byte[] decodedString = Base64.decode(immagine, Base64.DEFAULT);
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                int nRead;
                byte[] databyte = new byte[16384];
                while ((nRead = is.read(databyte, 0, databyte.length)) != -1) {
                    buffer.write(databyte, 0, nRead);
                }
                byte[] decodedString = buffer.toByteArray();

                    imgProfiloB64 = Base64.encodeToString(decodedString, Base64.DEFAULT);
                    final Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    final AlertDialog.Builder alertadd = new AlertDialog.Builder(this);
                    LayoutInflater factory = LayoutInflater.from(this);
                    final View view = factory.inflate(R.layout.select_image, null);
                    ImageView image = (ImageView) view.findViewById(R.id.select_image);
                    image.setImageBitmap(decodedByte);
                    alertadd.setView(view);
                    alertadd.setPositiveButton("SALVA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ImageView image = findViewById(R.id.imageView1);
                            image.setImageBitmap(decodedByte);
                        }
                    });
                    alertadd.setNegativeButton("ANNULLA", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertadd.show();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
