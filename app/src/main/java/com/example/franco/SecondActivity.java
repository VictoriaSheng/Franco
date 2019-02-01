package com.example.franco;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        mTextView= findViewById(R.id.textView2);

        Button myButton=findViewById(R.id.serversearch);
        myButton.setOnClickListener(this);

        Button cerca=findViewById(R.id.cerca);
        cerca.setOnClickListener(this);

        Log.d("myTap", "sono nella seconda");

    }
    public void onClick(View view){

        switch (view.getId()){
            case R.id.cerca:
                createList();
            break;
            case R.id.serversearch:
                mTextView.setText("Pippo");
                //importo gli users dal finto server
                addPersons();
            break;
        }


    }

    private void addPersons() {
        Mymodel m=Mymodel.getMyModel();
        List<String> users= getStudentFromServer("www.ciao.com");
        int i;
        for(i=0;i< users.size(); i++){
            m.addUser(users.get(i));
        }
    }

    private void createList() {
        //lista
        ListView users=(ListView) findViewById(R.id.contatti);
        //al click del bottone cerca, mi esce la lista dei contatti
        Mymodel m=Mymodel.getMyModel();
        //arraylist di contatti che prendo dal model
        final ArrayList<String> contatti=m.getUsers();

        //adapter
        final ContactsAdapter myAdapter = new ContactsAdapter(this, android.R.layout.list_content, contatti);
        users.setAdapter(myAdapter);
        users.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String c = contatti.get(i);
                        Log.d("ItemClickistener", c);
                        //fino a qui funziona
                        // startActivity(new Intent(DisplayActivity.this,Utente.class))
                        Intent intent=new Intent(SecondActivity.this, ContactActivity.class);
                        intent.putExtra("nome",c);
                        startActivity(intent);
                    }
                }
        );
    }

    public void myAdd(View view){
        //Button button= findViewById(R.id.cerca);
        Intent intent=new Intent(SecondActivity.this, ThirdActivity.class);
        startActivity(intent);
    }


    //Simulates to get data from a server, including a 5s delay

    //A string should be given in input, simulating the server address

    private List<String> getStudentFromServer(String url) {

        Log.d("Fake request", "Getting data from: " + url);

        ArrayList<String> result = new ArrayList<String>();

        result.add(new String("Andrea"));

        result.add(new String("Bruno"));

        result.add(new String("Carlo"));

        result.add(new String("Diego"));

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {

            e.printStackTrace();
        }
        return result;
    }

}
