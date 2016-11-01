package com.example.isaquearaujo.apporganizaenem;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class Padrao extends Fragment {
    public static ViewPager transicaopadrao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_padrao, container, false);
        transicaopadrao =(ViewPager) rootView.findViewById(R.id.transicaopadrao);
        transicaopadrao.setAdapter(new NavigatePadrao(getActivity().getSupportFragmentManager()));
        transicaopadrao.setOffscreenPageLimit(3);
        transicaopadrao.setCurrentItem(0);
        return rootView;
    }

}
