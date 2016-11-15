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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaMateriaPendente extends Fragment {
    TextView titulomateriapendente1;
    public  static ListView listamateriapendente1;
    public static ArrayList < String > Arraylistapendente1 = new ArrayList < String > ();
    ArrayAdapters adapterpendente1;
    ArrayList < String > selectedItemsPendente1 = new ArrayList< String >();
    int clickpendente1 = 1;
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    private Firebase users;
    public static Firebase lp1;
    FirebaseListAdapter<String> adapterlp1;
    private SharedPreferences settings;
    public  static String emailsplitado;
    Typeface typeface;
    android.os.Handler handler;
    ArrayAdapter< String > adapternulo;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_materia_pendente, container, false);
        listamateriapendente1 = (ListView) rootView.findViewById(R.id.listamateria1pd);
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
        lp1 = principal.child("users").child(emailsplitado).child("ListaMateriaPendentePortugues");
        final Firebase fb = principal.child("users").child(emailsplitado).child("ListaMateriaFeitaPortugues");
        adapterlp1 = new ArrayFireBase(getActivity(), String.class, android.R.layout.simple_list_item_1, lp1);
        titulomateriapendente1 = (TextView) rootView.findViewById(R.id.materia1pd);
        titulomateriapendente1.setText("PORTUGUÊS/LITERATURA");
        titulomateriapendente1.setTypeface(typeface);
        adapterpendente1 = new ArrayAdapters(getActivity(),Arraylistapendente1, TelaMateriaFeita.Arraylistafeita1,selectedItemsPendente1,fb, adapterlp1,listamateriapendente1, TelaMateriaFeita.listamateriafeita1);
        TelaMateriaDoDia.Firebase(lp1,Arraylistapendente1,adapterpendente1);
        listamateriapendente1.setAdapter(adapterpendente1);
        setListViewHeightBasedOnChildren(listamateriapendente1);
        String[] eoqc = {};
        adapternulo = new ArrayAdapter<String>(getActivity(), R.layout.program_list, R.id.textView1, eoqc)
        {
            @Override
            public View getView(final int position, View convertView, ViewGroup parent)
            {
                View v = convertView;
                if (v == null)
                {
                    LayoutInflater vi = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    v = vi.inflate(R.layout.program_list, null);
                    CheckBox r = (CheckBox) v.findViewById(R.id.imageView1);
                }
                TextView tv = (TextView) v.findViewById(R.id.textView1);
                tv.setText(null);
                //                typeface = Typeface.createFromAsset(convertView.getContext().getAssets(), "fonts/OpenSans-Semibold.ttf");
                tv.setTypeface(typeface);
                final CheckBox r = (CheckBox) v.findViewById(R.id.imageView1);
                r.setChecked(false);
                r.setTag(position);
                return v;
            }
        };
        titulomateriapendente1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                selectedItemsPendente1.clear();
                clickpendente1 += 1;
                if (clickpendente1 == 1)
                {
                    listamateriapendente1.setAdapter(adapterpendente1);
                    setListViewHeightBasedOnChildren(listamateriapendente1);
                }
                else if (clickpendente1 == 2)
                {
                    listamateriapendente1.setAdapter(adapternulo);
                    setListViewHeightBasedOnChildren(listamateriapendente1);
                    clickpendente1 = 0;
                }
            }
        });
        return rootView;

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
