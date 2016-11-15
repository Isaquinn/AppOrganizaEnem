package com.example.isaquearaujo.apporganizaenem;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;

import java.util.ArrayList;

/**
 * Created by Isaque Araujo on 14/11/2016.
 */

public class ArrayAdapters extends BaseAdapter
{
    private Activity mContext;
    private ArrayList<String> listaadapter = new ArrayList<String>();
    private ArrayList<String> listaadapter2 = new ArrayList<String>();
    private FirebaseListAdapter<String> firebaseListAdapter;
    private ArrayList<String> selecteditens = new ArrayList<String>();
    private Activity activity;
    private ListView materiapendente;
    private  ListView materiafeita;
    private Firebase firebaseaqui;
    public  ArrayAdapters(Activity c, ArrayList<String> AL_name_time,ArrayList<String> AL_name_time2,
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
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                Padrao.desfer = false;
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Padrao.progress += 20;
                        Padrao.porcentagem += 1;
                            Padrao.progressvalor += 20;
                            TelaMateriaDoDia.handler.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Log.d("valorps",String.valueOf(Padrao.progress));

                                    Firebase progress  = TelaMateriaDoDia.users.child("Xp");
                                    progress.setValue(String.valueOf(Padrao.progress).toString().trim());
                                    Padrao.xpusuario.setProgress(Padrao.progress);
                                }
                            });


                            Padrao.porcentagemvalor += 1;
                            TelaMateriaDoDia.handler.post(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    Firebase porcentagem  = TelaMateriaDoDia.users.child("Porcentagem");
                                    porcentagem.setValue(String.valueOf(Padrao.porcentagem).toString().trim());
                                    Padrao.Porcentagem.setText(String.valueOf(Padrao.porcentagem) + "%");

                                }
                            });
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
