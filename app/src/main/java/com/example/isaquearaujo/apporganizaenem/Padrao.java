package com.example.isaquearaujo.apporganizaenem;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class Padrao extends Fragment {
    public static ViewPager transicaopadrao;
    public static ImageView imageview;
    public static String nomeusuario;
    public static int numerodaimagemavatar;
    RoundImage roundImage;
    Bitmap bitmap;
    Bitmap rosto;
    Bitmap rostosembranco;
    Bitmap cabelo;
    Bitmap olho;
    Bitmap boca;
    Bitmap allimages;
    Bitmap allimagessembranco;
    int numberrosto, numbercabelo, numberolho, numberboca;
    int avatarnumber;
    int sexo = 0;
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    private Firebase users;
    public  static String emailsplitado;
    private SharedPreferences settings;
    public static int progress = 0;
    public static int progressvalor = 0;
    public static int porcentagem = 0;
    public static int porcentagemvalor = 0;
    public static ProgressBar xpusuario;
    public static TextView Porcentagem;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_padrao, container, false);
        transicaopadrao =(ViewPager) rootView.findViewById(R.id.transicaopadrao);
        transicaopadrao.setAdapter(new NavigatePadrao(getActivity().getSupportFragmentManager()));
        transicaopadrao.setOffscreenPageLimit(3);
        transicaopadrao.setCurrentItem(0);
        Firebase.setAndroidContext(getActivity());
        settings = getActivity().getSharedPreferences(com.example.isaquearaujo.apporganizaenem.Principal.PREFS_NAME, 0);
        firebaseAuth = FirebaseAuth.getInstance();
        principal = new Firebase("https://organiza-enem-app.firebaseio.com/");
        imageview = (ImageView) rootView.findViewById(R.id.imageView);
        Porcentagem = (TextView) rootView.findViewById(R.id.porcentagem);
        xpusuario = (ProgressBar) rootView.findViewById(R.id.xp);
        xpusuario.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#19a7b5")));
        xpusuario.setMax(2000);
        //String que pega o nome do usuário na tela de Login
        nomeusuario = settings.getString("email", null).toString().trim();
        String emailsplit = settings.getString("email",null).toString().trim();
        emailsplit.replace(".", ",");
        emailsplitado = emailsplit.replace("." , ",");
        users = principal.child("users").child(emailsplitado);
        Log.d("Email",emailsplitado);
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> mapa = dataSnapshot.getValue(Map.class);
                String porcentagemitem = mapa.get("Porcentagem");
                String xpitem = mapa.get("Xp");
                String avataritem = mapa.get("Sexo");
                String cabeloitem = mapa.get("Cabelo");
                String rostoitem = mapa.get("Face");
                String olhoitem = mapa.get("Olho");
                String bocaitem = mapa.get("Boca");
                Porcentagem.setText(porcentagemitem+"%");
                porcentagem = Integer.parseInt(porcentagemitem);
                porcentagemvalor = Integer.parseInt(porcentagemitem);
                progressvalor = Integer.parseInt(xpitem);
                progress = Integer.parseInt(xpitem);
                Log.d("Valor", xpitem.toString().trim());
                xpusuario.setProgress(progressvalor);
                switch (Integer.parseInt(avataritem))
                {
                    case 1:
                        switch (Integer.parseInt(cabeloitem))
                        {
                            case 0:
                                cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo);
                                break;
                            case 1:
                                cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo2);
                                break;
                            case 2:
                                cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo3);
                                break;
                        }
                        break;
                    case 2:
                        switch (Integer.parseInt(cabeloitem))
                        {
                            case 0:
                                cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                                break;
                            case 1:
                                cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                                break;
                            case 2:
                                cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                                break;
                        }
                        break;
                }
                switch (Integer.parseInt(rostoitem))
                {
                    case 0:
                        rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rostobranco);
                        rostosembranco = BitmapFactory.decodeResource(getResources(), R.drawable.rosto);
                        break;
                    case 1:
                        rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto2branco);
                        rostosembranco = BitmapFactory.decodeResource(getResources(), R.drawable.rosto2);
                        break;
                    case 2:
                        rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto3branco);
                        rostosembranco = BitmapFactory.decodeResource(getResources(), R.drawable.rosto3);
                        break;
                }
                switch (Integer.parseInt(olhoitem))
                {
                    case 0:
                        olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos);
                        break;
                    case 1:
                        olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos2);
                        break;
                    case 2:
                        olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos3);
                        break;
                    case 3:
                        olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos4);
                        break;
                    case 4:
                        olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos5);
                        break;
                    case 5:
                        olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos6);
                        break;
                }
                switch (Integer.parseInt(bocaitem))
                {
                    case 0:
                        boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca);
                        break;
                    case 1:
                        boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca2);
                        break;
                    case 2:
                        boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca3);
                        break;
                    case 3:
                        boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca4);
                        break;
                    case 4:
                        boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca5);
                        break;
                }
                allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);

                Bitmap teste = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
                roundImage = new RoundImage(allimages);
                imageview.setImageDrawable(roundImage);
                TelaPrincipal.progress.dismiss();

                //numerodaimagemavatar = Integer.parseInt(part2);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return rootView;
    }
    private Bitmap createSingleImageFromMultipleImages(Bitmap rosto, Bitmap cabelo, Bitmap olho, Bitmap boca)
    {
        Bitmap result = Bitmap.createBitmap(rosto.getWidth(), rosto.getHeight(), rosto.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(rosto, 0f, 0f, null);
        canvas.drawBitmap(cabelo, 0, 0, null);
        canvas.drawBitmap(olho, 0, 0, null);
        canvas.drawBitmap(boca, 0, 0, null);
        return result;
    }

}
