package com.example.isaquearaujo.apporganizaenem;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaMateriaDoDia extends Fragment {
    ArrayList < String > selectedItems = new ArrayList < String > ();
    TextView titulomateriaododia;
    ListView listadodia;
    ArrayList < String > Arraylistadodia = new ArrayList< String >();
    ArrayAdapters adapterdodia;
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    public static Firebase users;
    private Firebase ld;
    FirebaseListAdapter<String> adapterdia;
    private SharedPreferences settings;
    public  static String emailsplitado;
    Typeface typeface;
    public static android.os.Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_materia_do_dia, container, false);
        Firebase.setAndroidContext(getActivity());
        firebaseAuth = FirebaseAuth.getInstance();
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Semibold.ttf");
        principal = new Firebase("https://organiza-enem-app.firebaseio.com/");
        handler = new android.os.Handler();
        settings = getActivity().getSharedPreferences(com.example.isaquearaujo.apporganizaenem.Principal.PREFS_NAME, 0);
        String emailsplit = settings.getString("email",null).toString().trim();
        emailsplit.replace(".", ",");
        emailsplitado = emailsplit.replace("." , ",");
        users = principal.child("users").child(emailsplitado);
        ld = principal.child("users").child(emailsplitado).child("ListaDoDia");
        final Firebase fb = principal.child("users").child(emailsplitado).child("ListaMateriaFeitaPortugues");
        adapterdia = new ArrayFireBase(getActivity(), String.class, android.R.layout.simple_list_item_1, ld);
        listadodia = (ListView) rootView.findViewById(R.id.listamateriadodia);
        titulomateriaododia = (TextView) rootView.findViewById(R.id.materiadodia);
        titulomateriaododia.setTypeface(typeface);
        adapterdodia = new ArrayAdapters(getActivity(),Arraylistadodia, TelaMateriaFeita.Arraylistafeita1,selectedItems,fb, adapterdia,listadodia, TelaMateriaFeita.listamateriafeita1);
        Firebase(ld,Arraylistadodia,adapterdodia);

        listadodia.setAdapter(adapterdodia);
        setListViewHeightBasedOnChildren(listadodia);
        return rootView;
    }
    public static void Firebase(Firebase firebase, final ArrayList<String> lista, final ArrayAdapters adapter)
    {
        firebase.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                lista.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                lista.remove(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //Toast.makeText(getActivity(),"Não foi possivel adicionar", Toast.LENGTH_SHORT);
            }
        });
    }
    public static void Firebase2(Firebase firebase, final ArrayList<String> lista, final ArrayAdaptersChecked adapter)
    {
        firebase.addChildEventListener(new com.firebase.client.ChildEventListener() {
            @Override
            public void onChildAdded(com.firebase.client.DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                lista.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(com.firebase.client.DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                lista.remove(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(com.firebase.client.DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                //Toast.makeText(getActivity(),"Não foi possivel adicionar", Toast.LENGTH_SHORT);
            }
        });
    }


    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        int i;
        for (i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();

        }

        //add divider height to total height as many items as there are in listview
        totalHeight += listView.getDividerHeight()*i;
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setDivider(null);
        listView.setDividerHeight(10);
        listView.setLayoutParams(params);

    }

}
