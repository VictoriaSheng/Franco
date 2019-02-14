package com.example.franco;


import android.arch.persistence.room.Room;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class fourthActivity extends AppCompatActivity implements View.OnClickListener{

    ArrayList<String> utenti;
    ArrayList<String> UtentiDalDB=new ArrayList<String>();
    AppDatabase userDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        Button button=findViewById(R.id.downloadDes);
        button.setOnClickListener(this);

        /*Mymodel m=Mymodel.getMyModel();
        utenti=m.getUsers();
        Log.d("myTap", utenti.get(1));*/

        //devo prendere i miei utenti e caricarli nel DB, deve essere qui e non nell'asink task, altrimenti succede che l'arraylist
        //non viene riempito in tempo
        new Operationdb().execute("");

    }

    @Override
    public void onClick(View v) {
       // Toast.makeText(getApplicationContext(),"Utenti scaricati nel DB", Toast.LENGTH_LONG).show();
        Log.d("myTap", "sono nell'onclick");

        //Al click ritornami quelli nel DB
        //for(int i=1; i< utenti.size(); i++){}

        ListView users=(ListView) findViewById(R.id.listaDbDes);
        final ContactsAdapter myAdapter = new ContactsAdapter(this, android.R.layout.list_content, UtentiDalDB);
        users.setAdapter(myAdapter);
    }

    //<String, Void, String> sono i valori nei parametri dei tre metodi : doInBackgroud, onProgressUpdate e onPostExecute
    private class Operationdb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String...params) {


            //ci vuole anche se semplicemente richiamo i valori nel db
            final String DATABASE_NAME="users_db";
            userDataBase = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, DATABASE_NAME).build();

            Log.d("myTag", "database costruito ma senza utenti");
            //Log.d("myTap", utenti.get(2));

            //userDataBase.daoAccess().deleteAll(); Log.d("myTag", "cancellati tutti");

            //una volta inserito un utente, è memorizzato in maniera persistente, per questo ho commentato il codice, al momento ci sono tutti


            for(int i=0; i< 4;i++ ){
                /*Users utente= new Users();
                utente.setUid(i);
                utente.setNameUser(utenti.get(i));
                userDataBase.daoAccess().insertSingleUser(utente);*/

                Users u=userDataBase.daoAccess().fetchOneUserFromId(i);
                Log.d("myTap", "Utente nel DB: "+u.getNameUser());
                UtentiDalDB.add(u.getNameUser());

            }


            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),"Utenti in utenti", Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPreExecute() {}

        @Override
        protected void onProgressUpdate(Void... values) {}
    }



}

