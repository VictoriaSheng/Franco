package com.example.franco;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactsAdapter extends ArrayAdapter<String> {
        //devo definire questi due costruttori
        public ContactsAdapter(Context context, int textViewResourceId) {
            super(context, textViewResourceId);
        }
        public ContactsAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
        }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_element, null);
            Log.d("myTap", "l'adapter funziona");
        }
        String p = getItem(position);
        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.name);
            Log.d("myTap", "valore di p: "+p);
            tt1.setText(p);
        }
        return v;
    }// End of method (getView)
}
