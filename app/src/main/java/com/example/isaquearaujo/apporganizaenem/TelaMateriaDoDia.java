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
    private Firebase users;
    private Firebase ld;
    FirebaseListAdapter<String> adapterdia;
    private SharedPreferences settings;
    public  static String emailsplitado;
    Typeface typeface;
    android.os.Handler handler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_materia_do_dia, container, false);
        /*Firebase.setAndroidContext(getActivity());
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
        adapterdia = new ArrayFireBase(getActivity(), String.class, android.R.layout.simple_list_item_1, ld);
        listadodia = (ListView) rootView.findViewById(R.id.listamateriadodia);
        titulomateriaododia = (TextView) rootView.findViewById(R.id.materiadodia);
        titulomateriaododia.setTypeface(typeface);
        adapterdodia = new ArrayAdapters(getActivity(),Arraylistadodia, TelaMateriaFeita.Arraylistafeita1,selectedItems,TelaMateriaFeita.lv1, adapterdia,listadodia, TelaMateriaFeita.listamateriafeita1);
        Firebase(ld,Arraylistadodia,adapterdodia);
        listadodia.setAdapter(adapterdodia);*/
        return rootView;
    }
    void Firebase(Firebase firebase, final ArrayList<String> lista, final TelaMateriaDoDia.ArrayAdapters adapter)
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
                Toast.makeText(getActivity(),"Não foi possivel adicionar", Toast.LENGTH_SHORT);
            }
        });
    }
    public  class  ArrayFireBase  extends FirebaseListAdapter<String> {
        public ArrayFireBase(Activity activity, Class modelClass, int modelLayout, Firebase ref) {
            super(activity, modelClass, modelLayout, ref);
        }

        @Override
        protected void populateView(View view, String o, int i) {
            Log.d("s", o);
            TextView textView = (TextView)view.findViewById(android.R.id.text1);
            textView.setText(o);
        }
    }
    public  class ArrayAdapters extends BaseAdapter
    {
        private Context mContext;
        private ArrayList<String> listaadapter = new ArrayList<String>();
        private ArrayList<String> listaadapter2 = new ArrayList<String>();
        private FirebaseListAdapter<String>firebaseListAdapter;
        private ArrayList<String> selecteditens = new ArrayList<String>();
        private  ListView materiapendente;
        private  ListView materiafeita;
        Firebase firebaseaqui;
        public ArrayAdapters(Context c, ArrayList<String> AL_name_time,ArrayList<String> AL_name_time2,
                             ArrayList<String> AL_name_time1,Firebase firebasee,FirebaseListAdapter<String> firrebaselist, ListView MF, ListView MP) {
            this.mContext = c;
            this.listaadapter = AL_name_time;
            this.listaadapter2 = AL_name_time2;
            this.selecteditens = AL_name_time1;
            this.firebaseaqui = firebasee;
            this.firebaseListAdapter = firrebaselist;
            this.materiapendente = MP;
            this.materiafeita = MF;

        }
        @Override
        public int getCount() {
            return listaadapter.size();
        }

        @Override
        public Object getItem(int position) {
            return listaadapter.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

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
            tv.setText(listaadapter.get(position));
            final CheckBox r = (CheckBox) v.findViewById(R.id.imageView1);
            r.setChecked(false);
            r.setTag(position);
            r.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Padrao.progress += 20;
                    Padrao.porcentagem += 1;
                    new Thread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            while (Padrao.progressvalor < Padrao.progress)
                            {
                                Padrao.progressvalor += 1;
                                handler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Padrao.xpusuario.setProgress(Padrao.progressvalor);
                                        Firebase progress  = users.child("Xp");
                                        progress.setValue(String.valueOf(Padrao.progressvalor).toString().trim());
                                    }
                                });
                            }
                            while (Padrao.porcentagemvalor < Padrao.porcentagem)
                            {
                                Padrao.porcentagemvalor += 1;
                                handler.post(new Runnable()
                                {
                                    @Override
                                    public void run()
                                    {
                                        Padrao.Porcentagem.setText(String.valueOf(Padrao.porcentagemvalor) + "%");
                                        Firebase porcentagem  = users.child("Porcentagem");
                                        porcentagem.setValue(String.valueOf(Padrao.porcentagemvalor).toString().trim());
                                    }
                                });
                            }
                        }
                    }).start();
                    selecteditens.add(listaadapter.get(position));
                    for (int i = 0; i < selecteditens.size(); i++) {
                        Log.d("dasdas", String.valueOf(i));
                        if (!listaadapter2.contains(selecteditens.get(i)))
                        {
                            firebaseaqui.push().setValue(selecteditens.get(i));

                        }
                        else if (listaadapter2.contains(selecteditens.get(i)))
                        {
                            Log.d("eoq", "Já existe");
                        }

                        Firebase itemRef = firebaseListAdapter.getRef(position);
                        itemRef.removeValue();
                        //selectedItems.remove(i);
                        // items.remove(items.get(adapter.getItemViewType(ids.get(i))));
                        // items.remove(adapter.getItemViewType(ids.get(i)));
                        System.out.println();
                    }
                    selecteditens.clear();
                    //setListViewHeightBasedOnChildren(materiapendente);
                    //setListViewHeightBasedOnChildren(materiafeita);
                    //setListViewHeightBasedOnChildren(lv3);
                    //((CustomAdapter) lv3.getAdapter()).notifyDataSetChanged();
                }
            });
            return v;
        }
    }
    public static void setListViewHeightBasedOnChildren(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            // pre-condition
            return;
        }
        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup)
            {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        listView.setDivider(null);
        listView.setDividerHeight(10);
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

}
