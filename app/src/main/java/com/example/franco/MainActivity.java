package com.example.franco;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //R è una classe, layout è un attributo statico activity_main è una variabile statica e final
        //ovvero una costante che crea il "collegamento" alle risorse
        Log.d("viki", "metodo onCreate");

        Toolbar myToolbar= findViewById(R.id.customToolbar);

    }
    public void myTap(View v) {

        Button button= findViewById(R.id.button);
        Intent intent=new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
        Log.d("myTap", "sono loggata");
    }

    public void memoryTap(View v){
        Button buttonM=findViewById(R.id.button);
        Intent intent=new Intent(MainActivity.this, fourthActivity.class);
        startActivity(intent);
    }

    public void myTapBrowser(View v){
        Uri uriUrl = Uri.parse("https://www.netflix.com/it/");
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }


     protected void onStart(){
        super.onStart();
        Log.d("viki", "metodo onStart");
     }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("viki", "metodo onStop");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d("viki", "metodo onResume");
    }

    protected void onPause(){
        super.onPause();
        Log.d("viki", "metodo onPause");
    }


    protected void onDestroy(){
        super.onDestroy();
        Log.d("viki", "metodo onDestry");
    }

    protected void onRestart(){
        super.onRestart();
        Log.d("viki", "metodo onRestart");
    }


}
