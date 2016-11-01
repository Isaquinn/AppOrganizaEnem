package com.example.isaquearaujo.apporganizaenem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaConfiguracoes extends Fragment  implements View.OnClickListener{
    TextView AlterarAvatar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_configuracoes, container, false);
        AlterarAvatar = (TextView)rootView.findViewById(R.id.AlterarAvatar);
        AlterarAvatar.setOnClickListener(this);
        return  rootView;

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.AlterarAvatar)
        {
            TelaPrincipal.viewpagerprincipal.setCurrentItem(2,false);
        }
    }
}
