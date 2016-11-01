package com.example.isaquearaujo.apporganizaenem;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class TelaLivros extends Fragment implements View.OnClickListener {
    ImageView imagelivro1, imagelivro2, imagelivro3, imagelivro4, imagelivro5, imagelivro6, imagelivro7, imagelivro8, imagelivro9, imagelivro10, imagelivro11, imagelivro12, imagelivro13, imagelivro14, imagelivro15, imagelivro16, imagelivro17, imagelivro18, imagelivro19, imagelivro20;
    public static int numberlivros;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_livros, container, false);
        imagelivro1 = (ImageView) rootView.findViewById(R.id.livro1);
        imagelivro1.setOnClickListener(this);
        imagelivro2 = (ImageView) rootView.findViewById(R.id.livro2);
        imagelivro2.setOnClickListener(this);
        imagelivro3 = (ImageView) rootView.findViewById(R.id.livro3);
        imagelivro3.setOnClickListener(this);
        imagelivro4 = (ImageView) rootView.findViewById(R.id.livro4);
        imagelivro4.setOnClickListener(this);
        imagelivro5 = (ImageView) rootView.findViewById(R.id.livro5);
        imagelivro5.setOnClickListener(this);
        imagelivro6 = (ImageView) rootView.findViewById(R.id.livro6);
        imagelivro6.setOnClickListener(this);
        imagelivro7 = (ImageView) rootView.findViewById(R.id.livro7);
        imagelivro7.setOnClickListener(this);
        imagelivro8 = (ImageView) rootView.findViewById(R.id.livro8);
        imagelivro8.setOnClickListener(this);
        imagelivro9 = (ImageView) rootView.findViewById(R.id.livro9);
        imagelivro9.setOnClickListener(this);
        imagelivro10 = (ImageView) rootView.findViewById(R.id.livro10);
        imagelivro10.setOnClickListener(this);
        imagelivro11 = (ImageView) rootView.findViewById(R.id.livro11);
        imagelivro11.setOnClickListener(this);
        imagelivro12 = (ImageView) rootView.findViewById(R.id.livro12);
        imagelivro12.setOnClickListener(this);
        imagelivro13 = (ImageView) rootView.findViewById(R.id.livro13);
        imagelivro13.setOnClickListener(this);
        imagelivro14 = (ImageView) rootView.findViewById(R.id.livro14);
        imagelivro14.setOnClickListener(this);
        imagelivro15 = (ImageView) rootView.findViewById(R.id.livro15);
        imagelivro15.setOnClickListener(this);
        imagelivro16 = (ImageView) rootView.findViewById(R.id.livro16);
        imagelivro16.setOnClickListener(this);
        imagelivro17 = (ImageView) rootView.findViewById(R.id.livro17);
        imagelivro17.setOnClickListener(this);
        imagelivro18 = (ImageView) rootView.findViewById(R.id.livro18);
        imagelivro18.setOnClickListener(this);
        imagelivro19 = (ImageView) rootView.findViewById(R.id.livro19);
        imagelivro19.setOnClickListener(this);
        imagelivro20 = (ImageView) rootView.findViewById(R.id.livro20);
        imagelivro20.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.livro1)
        {
            numberlivros = 1;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.olga));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("Olga");
        }
        if (v.getId() == R.id.livro2)
        {
            numberlivros = 2;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.oqueeisso));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("O que é isso companheiro?");
        }
        if (v.getId() == R.id.livro3)
        {
            numberlivros = 3;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.schin));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("A lista de Schindler");
        }
        if (v.getId() == R.id.livro4)
        {
            numberlivros = 4;
            TelaDicas.transicaodicas.setCurrentItem(5, false);
            TelaMostrarFilmesELivros.imagemfilmeoulivro.setImageDrawable(getResources().getDrawable(R.drawable.segundafeiraaosol));
            TelaMostrarFilmesELivros.titlelivrooufilme.setText("Segunda-feira ao sol");
        }
    }
}
