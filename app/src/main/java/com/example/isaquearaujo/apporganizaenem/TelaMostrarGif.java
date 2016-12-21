package com.example.isaquearaujo.apporganizaenem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaMostrarGif extends Fragment {
    public static ImageView gif;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_mostrar_gif, container, false);
        gif = (ImageView)rootView.findViewById(R.id.gif);
        Glide.with(getActivity().getApplication()) .load(R.drawable.respquad) .asGif() .into(gif);
        return rootView;
    }

}
