package com.example.isaquearaujo.apporganizaenem;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class SelecaoDicas extends Fragment implements View.OnClickListener{
    ImageView filmes, livros, relaxamento, mente;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_selecao_dicas, container, false);
        filmes = (ImageView) rootView.findViewById(R.id.buttonfilmes);
        filmes.setOnClickListener(this);
        livros = (ImageView) rootView.findViewById(R.id.buttonlivros);
        livros.setOnClickListener(this);
        relaxamento = (ImageView) rootView.findViewById(R.id.buttonrelaxamento);
        relaxamento.setOnClickListener(this);
        mente = (ImageView) rootView.findViewById(R.id.buttonmente);
        mente.setOnClickListener(this);
        return rootView;

    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttonfilmes)
        {
            TelaPrincipal.viewpagerdicas = 1;
            TelaDicas.transicaodicas.setCurrentItem(1,false);
            TelaLivros.numberlivros = 0;
            TelaMostrarFilmesELivros.sinopse.setText("");
            TelaMostrarFilmesELivros.mostrarmateriaRelacionada.setText("");
            TelaMostrarFilmesELivros.mostrarproducao.setText("");
            TelaMostrarFilmesELivros.Descricaoclick = 0;
            TelaMostrarFilmesELivros.Materiarelacionadaclick = 0;
            TelaMostrarFilmesELivros.Producaoclick = 0;
        }
        if(v.getId() == R.id.buttonlivros)
        {
            TelaPrincipal.viewpagerdicas = 2;
            TelaDicas.transicaodicas.setCurrentItem(2,false);
            TelaFilmes.numberfilmes = 0;
            TelaMostrarFilmesELivros.sinopse.setText("");
            TelaMostrarFilmesELivros.mostrarmateriaRelacionada.setText("");
            TelaMostrarFilmesELivros.mostrarproducao.setText("");
            TelaMostrarFilmesELivros.Descricaoclick = 0;
            TelaMostrarFilmesELivros.Materiarelacionadaclick = 0;
            TelaMostrarFilmesELivros.Producaoclick = 0;
        }
        if(v.getId() == R.id.buttonrelaxamento)
        {
            TelaDicas.transicaodicas.setCurrentItem(3,false);
        }
        if(v.getId() == R.id.buttonmente)
        {
            TelaDicas.transicaodicas.setCurrentItem(4,false);
        }
    }
}
