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
    int clickpendente1 = 0;
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
        lp1 = principal.child("users").child(emailsplitado).child("ListaMateriaPendentePortugues");
        adapterlp1 = new ArrayFireBase(getActivity(), String.class, android.R.layout.simple_list_item_1, lp1);
        titulomateriapendente1 = (TextView) rootView.findViewById(R.id.materia1pd);
        titulomateriapendente1.setText("PORTUGUÊS/LITERATURA");
        titulomateriapendente1.setTypeface(typeface);
        adapterpendente1 = new ArrayAdapters(getActivity(),Arraylistapendente1, TelaMateriaFeita.Arraylistafeita1,selectedItemsPendente1,TelaMateriaFeita.lv1, adapterlp1,listamateriapendente1, TelaMateriaFeita.listamateriafeita1);
        Firebase(lp1,Arraylistapendente1,adapterpendente1);
        listamateriapendente1.setAdapter(adapterpendente1);
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
        */
        return rootView;

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
    void Firebase(Firebase firebase, final ArrayList<String> lista, final ArrayAdapters adapter)
    {
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String message = dataSnapshot.getValue(String.class);
                lista.add(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String message = dataSnapshot.getValue(String.class);
                lista.remove(message);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Toast.makeText(getActivity(),"Não foi possivel adicionar", Toast.LENGTH_SHORT);
            }
        });
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
