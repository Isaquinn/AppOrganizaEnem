package com.example.isaquearaujo.apporganizaenem;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaMostrarFilmesELivros extends Fragment implements View.OnClickListener {
    public static ImageView imagemfilmeoulivro;
    public static TextView titlelivrooufilme;
    public static TextView sinopse, mostrarmateriaRelacionada, mostrarproducao, Descricao, MateriaRelacionada, Producao;
    public static int Descricaoclick = 0; public  static int Materiarelacionadaclick = 0; public static int Producaoclick = 0;
    Typeface typeface;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_mostrar_filmes_elivros, container, false);
        typeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/OpenSans-Semibold.ttf");
        imagemfilmeoulivro = (ImageView)rootView.findViewById(R.id.imagemfilmeoulivro);
        titlelivrooufilme = (TextView)rootView.findViewById(R.id.titlelivrooufilme);
        sinopse = (TextView)rootView.findViewById(R.id.textodescricao);
        mostrarmateriaRelacionada = (TextView)rootView.findViewById(R.id.textomateriarelacionada);
        mostrarproducao = (TextView)rootView.findViewById(R.id.textoproducao);
        Descricao = (TextView)rootView.findViewById(R.id.buttondescricao);
        Descricao.setTypeface(typeface);
        Descricao.setOnClickListener(this);
        MateriaRelacionada = (TextView)rootView.findViewById(R.id.buttonmateriarelacionada);
        MateriaRelacionada.setTypeface(typeface);
        MateriaRelacionada.setOnClickListener(this);
        Producao = (TextView)rootView.findViewById(R.id.buttonproducao);
        Producao.setTypeface(typeface);
        Producao.setOnClickListener(this);
        return rootView;
    }
    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.buttondescricao)
        {
            Descricaoclick+=1;
            if(Descricaoclick == 1)
            {
                if(TelaFilmes.numberfilmes == 1)
                {
                    sinopse.setText("Olga(2004), a cinebiografia da judia alemã Olga Benário Prestes foi dirigida por Jayme Monjardim. Militante comunista e companheira de Luís Carlos Prestes, Olga foi deportada para a Alemanha nazista durante o governo Getúlio Vargas. Presa num campo de extermínio, ela foi morta em 1942.");

                }
                else  if(TelaFilmes.numberfilmes == 2)
                {
                    sinopse.setText("O que é isso companheiro?(1997), momento marcante na luta armada contra a ditadura militar, o sequestro de Charles Elbrick, o embaixador norte-americano no Brasil durante parte dos anos de chumbo, é narrado nesse filme. O filme foi inspirado no livro de mesmo nome, de Fernando Gabeira.");

                }
                else if(TelaFilmes.numberfilmes == 3)
                {
                    sinopse.setText("A lista de Schindler(1993), simplesmente um dos maiores filmes da história do cinema. O longa de Steven Spielberg fala sobre nazismo e holocausto. Conta a história de Oskar Schindler, um empresário alemão que ajudou a salvar as vidas de mais de mil judeus ao dar emprego para eles numa fábrica, durante a Segunda Guerra Mundial.");

                }
                else if(TelaFilmes.numberfilmes == 4)
                {
                    sinopse.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                if(TelaLivros.numberlivros == 1)
                {
                    sinopse.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                else if(TelaLivros.numberlivros == 2)
                {
                    sinopse.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                else if(TelaLivros.numberlivros == 3)
                {
                    sinopse.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                else if(TelaLivros.numberlivros == 4)
                {
                    sinopse.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }

            }
            else if(Descricaoclick == 2)
            {
                sinopse.setText("");
                Descricaoclick = 0;
            }
        }
        if(v.getId() == R.id.buttonmateriarelacionada)
        {
            Materiarelacionadaclick+=1;
            if(Materiarelacionadaclick == 1)
            {
                if(TelaFilmes.numberfilmes == 1)
                {
                    mostrarmateriaRelacionada.setText("Olga(2004), a cinebiografia da judia alemã Olga Benário Prestes foi dirigida por Jayme Monjardim. Militante comunista e companheira de Luís Carlos Prestes, Olga foi deportada para a Alemanha nazista durante o governo Getúlio Vargas. Presa num campo de extermínio, ela foi morta em 1942.");

                }
                else  if(TelaFilmes.numberfilmes == 2)
                {
                    mostrarmateriaRelacionada.setText("O que é isso companheiro?(1997), momento marcante na luta armada contra a ditadura militar, o sequestro de Charles Elbrick, o embaixador norte-americano no Brasil durante parte dos anos de chumbo, é narrado nesse filme. O filme foi inspirado no livro de mesmo nome, de Fernando Gabeira.");

                }
                else if(TelaFilmes.numberfilmes == 3)
                {
                    mostrarmateriaRelacionada.setText("A lista de Schindler(1993), simplesmente um dos maiores filmes da história do cinema. O longa de Steven Spielberg fala sobre nazismo e holocausto. Conta a história de Oskar Schindler, um empresário alemão que ajudou a salvar as vidas de mais de mil judeus ao dar emprego para eles numa fábrica, durante a Segunda Guerra Mundial.");

                }
                else if(TelaFilmes.numberfilmes == 4)
                {
                    mostrarmateriaRelacionada.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                if(TelaLivros.numberlivros == 1)
                {
                    mostrarmateriaRelacionada.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                else if(TelaLivros.numberlivros == 2)
                {
                    mostrarmateriaRelacionada.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                else if(TelaLivros.numberlivros == 3)
                {
                    mostrarmateriaRelacionada.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                else if(TelaLivros.numberlivros == 4)
                {
                    mostrarmateriaRelacionada.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
            }
            else if(Materiarelacionadaclick == 2)
            {
                mostrarmateriaRelacionada.setText("");
                Materiarelacionadaclick = 0;
            }
        }
        if(v.getId() == R.id.buttonproducao)
        {
            Producaoclick+=1;
            if(Producaoclick == 1)
            {
                if(TelaFilmes.numberfilmes == 1)
                {
                    mostrarproducao.setText("Olga(2004), a cinebiografia da judia alemã Olga Benário Prestes foi dirigida por Jayme Monjardim. Militante comunista e companheira de Luís Carlos Prestes, Olga foi deportada para a Alemanha nazista durante o governo Getúlio Vargas. Presa num campo de extermínio, ela foi morta em 1942.");

                }
                else  if(TelaFilmes.numberfilmes == 2)
                {
                    mostrarproducao.setText("O que é isso companheiro?(1997), momento marcante na luta armada contra a ditadura militar, o sequestro de Charles Elbrick, o embaixador norte-americano no Brasil durante parte dos anos de chumbo, é narrado nesse filme. O filme foi inspirado no livro de mesmo nome, de Fernando Gabeira.");

                }
                else if(TelaFilmes.numberfilmes == 3)
                {
                    mostrarproducao.setText("A lista de Schindler(1993), simplesmente um dos maiores filmes da história do cinema. O longa de Steven Spielberg fala sobre nazismo e holocausto. Conta a história de Oskar Schindler, um empresário alemão que ajudou a salvar as vidas de mais de mil judeus ao dar emprego para eles numa fábrica, durante a Segunda Guerra Mundial.");

                }
                else if(TelaFilmes.numberfilmes == 4)
                {
                    mostrarproducao.setText("Segunda-feira ao sol(2001), o desemprego é a explicitação perversa da situação-limite do trabalho na sociedade do capital. Aranoa consegue traduzir em dramas pessoais a condição perversa do agudo estranhamento da classe operária, expresso por meio do desemprego de longa duração (há mais de dois anos, os desempregados homens não conseguem entrar no mercado de trabalho).");

                }
                if(TelaLivros.numberlivros == 1)
                {
                    mostrarproducao.setText("Olga(2004), a cinebiografia da judia alemã Olga Benário Prestes foi dirigida por Jayme Monjardim. Militante comunista e companheira de Luís Carlos Prestes, Olga foi deportada para a Alemanha nazista durante o governo Getúlio Vargas. Presa num campo de extermínio, ela foi morta em 1942.");

                }
                else if(TelaLivros.numberlivros == 2)
                {
                    mostrarproducao.setText("Olga(2004), a cinebiografia da judia alemã Olga Benário Prestes foi dirigida por Jayme Monjardim. Militante comunista e companheira de Luís Carlos Prestes, Olga foi deportada para a Alemanha nazista durante o governo Getúlio Vargas. Presa num campo de extermínio, ela foi morta em 1942.");

                }
                else if(TelaLivros.numberlivros == 3)
                {
                    mostrarproducao.setText("Olga(2004), a cinebiografia da judia alemã Olga Benário Prestes foi dirigida por Jayme Monjardim. Militante comunista e companheira de Luís Carlos Prestes, Olga foi deportada para a Alemanha nazista durante o governo Getúlio Vargas. Presa num campo de extermínio, ela foi morta em 1942.");

                }
                else if(TelaLivros.numberlivros == 4)
                {
                    mostrarproducao.setText("Olga(2004), a cinebiografia da judia alemã Olga Benário Prestes foi dirigida por Jayme Monjardim. Militante comunista e companheira de Luís Carlos Prestes, Olga foi deportada para a Alemanha nazista durante o governo Getúlio Vargas. Presa num campo de extermínio, ela foi morta em 1942.");

                }
            }
            else if(Producaoclick == 2)
            {
                mostrarproducao.setText("");
                Producaoclick = 0;
            }
        }
    }
}
