package com.example.isaquearaujo.apporganizaenem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaFilmes extends Fragment implements View.OnClickListener {
    ImageView imagefilme1, imagefilme2, imagefilme3, imagefilme4, imagefilme5, imagefilme6, imagefilme7, imagefilme8, imagefilme9, imagefilme10, imagefilme11, imagefilme12, imagefilme13, imagefilme14, imagefilme15, imagefilme16, imagefilme17, imagefilme18, imagefilme19, imagefilme20;
    public static int numberfilmes;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_filmes, container, false);
        imagefilme1 = (ImageView) rootView.findViewById(R.id.filme1);
        imagefilme1.setOnClickListener(this);
        imagefilme2 = (ImageView) rootView.findViewById(R.id.filme2);
        imagefilme2.setOnClickListener(this);
        imagefilme3 = (ImageView) rootView.findViewById(R.id.filme3);
        imagefilme3.setOnClickListener(this);
        imagefilme4 = (ImageView) rootView.findViewById(R.id.filme4);
        imagefilme4.setOnClickListener(this);
        imagefilme5 = (ImageView) rootView.findViewById(R.id.filme5);
        imagefilme5.setOnClickListener(this);
        imagefilme6 = (ImageView) rootView.findViewById(R.id.filme6);
        imagefilme6.setOnClickListener(this);
        imagefilme7 = (ImageView) rootView.findViewById(R.id.filme7);
        imagefilme7.setOnClickListener(this);
        imagefilme8 = (ImageView) rootView.findViewById(R.id.filme8);
        imagefilme8.setOnClickListener(this);
        imagefilme9 = (ImageView) rootView.findViewById(R.id.filme9);
        imagefilme9.setOnClickListener(this);
        imagefilme10 = (ImageView) rootView.findViewById(R.id.filme10);
        imagefilme10.setOnClickListener(this);
        imagefilme11 = (ImageView) rootView.findViewById(R.id.filme11);
        imagefilme11.setOnClickListener(this);
        imagefilme12 = (ImageView) rootView.findViewById(R.id.filme12);
        imagefilme12.setOnClickListener(this);
        imagefilme13 = (ImageView) rootView.findViewById(R.id.filme13);
        imagefilme13.setOnClickListener(this);
        imagefilme14 = (ImageView) rootView.findViewById(R.id.filme14);
        imagefilme14.setOnClickListener(this);
        imagefilme15 = (ImageView) rootView.findViewById(R.id.filme15);
        imagefilme15.setOnClickListener(this);
        imagefilme16 = (ImageView) rootView.findViewById(R.id.filme16);
        imagefilme16.setOnClickListener(this);
        imagefilme17 = (ImageView) rootView.findViewById(R.id.filme17);
        imagefilme17.setOnClickListener(this);
        imagefilme18 = (ImageView) rootView.findViewById(R.id.filme18);
        imagefilme18.setOnClickListener(this);
        imagefilme19 = (ImageView) rootView.findViewById(R.id.filme19);
        imagefilme19.setOnClickListener(this);
        imagefilme20 = (ImageView) rootView.findViewById(R.id.filme20);
        imagefilme20.setOnClickListener(this);
        return  rootView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.filme1)
        {
            numberfilmes = 1;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.olga));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("Olga");
        }
        if (v.getId() == R.id.filme2)
        {
            numberfilmes = 2;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.oqueeisso));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("O que é isso companheiro?");
        }
        if (v.getId() == R.id.filme3)
        {
            numberfilmes = 3;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.schin));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("A lista de Schindler");
        }
        if (v.getId() == R.id.filme4)
        {
            numberfilmes = 4;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.segundafeiraaosol));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("Segunda-feira ao sol");
        }
    }
}
