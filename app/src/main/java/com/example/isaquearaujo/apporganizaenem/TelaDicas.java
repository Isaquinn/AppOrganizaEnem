package com.example.isaquearaujo.apporganizaenem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaDicas extends Fragment {
    public static ViewPager transicaodicas;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_dicas, container, false);
        transicaodicas = (ViewPager)rootView.findViewById(R.id.transicaodicas);
        transicaodicas.setAdapter(new NavigateTelaDicas(getActivity().getSupportFragmentManager()));
        transicaodicas.setOffscreenPageLimit(6);
        transicaodicas.setCurrentItem(0);
        return rootView;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
}
