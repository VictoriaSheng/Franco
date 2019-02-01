package com.example.franco;

import java.util.ArrayList;

public class Mymodel {
    //creare Singleton
    private static Mymodel instance=null;
    //creo un arraylist che mi contiene gli users che mi interessano
    //campo della classe
    private ArrayList<String> users=new ArrayList<String>();
    //costruttore vuoto
    private Mymodel(){}
    //metodo che mi restituisce l'array di nomi, visto che il mio array è privato

    public ArrayList<String> getUsers() {
        return users;
    }
    //serve il singleton
// Metodo della classe impiegato per accedere al singleton
    public static synchronized Mymodel getMyModel() {
        if (instance == null) {
            instance = new Mymodel();
        }
        return instance;
    }

    public void addUser(String s){users.add(s);
    }



}
