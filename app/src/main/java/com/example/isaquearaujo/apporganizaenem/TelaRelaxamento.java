package com.example.isaquearaujo.apporganizaenem;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaRelaxamento extends Fragment implements View.OnClickListener {
     public static TextView animquad;
    Typeface typeface;
    Typeface typefacebold;
    ImageView iamge;
    Fragment fragment;
    TelaMostrarGif telaMostrarGif;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_relaxamento, container, false);
        animquad = (TextView)rootView.findViewById(R.id.buttonrespquad);
        iamge = (ImageView)rootView.findViewById(R.id.teste);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Semibold.ttf");
        typefacebold = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Bold.ttf");
        animquad.setTypeface(typeface);
        animquad.setOnClickListener(this);
telaMostrarGif = new TelaMostrarGif();
        return rootView;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonrespquad)
        {
            animquad.setTypeface(typefacebold);
            TelaDicas.transicaodicas.setCurrentItem(6, false);
        }
    }
}
