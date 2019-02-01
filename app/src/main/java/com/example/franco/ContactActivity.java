package com.example.franco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        Intent i=getIntent();
        String nome=(String)i.getSerializableExtra("nome");

        TextView name=(TextView)findViewById(R.id.nome);
        name.setText(nome);
    }
}
