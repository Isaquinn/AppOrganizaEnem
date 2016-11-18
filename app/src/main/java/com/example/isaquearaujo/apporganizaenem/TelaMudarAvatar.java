package com.example.isaquearaujo.apporganizaenem;


import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class TelaMudarAvatar extends Fragment implements View.OnClickListener {
    public static ImageView imageavatarconfig;
    Button cabeloanterior, olhoanterior, bocaanterior, cabeloposterior, olhoposterior, bocaposterior, cor1, cor2, cor3, okvatar, cancelvatar;
    public static Bitmap bitmap;
    public static Bitmap rosto;
    public static Bitmap rostosembranco;
    public static Bitmap cabelo;
    public static Bitmap olho;
    public static Bitmap boca;
    public static Bitmap allimagessembranco;
    public static int numberrosto, numbercabelo, numberolho, numberboca;
    public static int avatarnumber;
    public static int sexo = 0;
    public static String nomeusuario;
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    public static  Firebase users;
    private Firebase usersavatar;
    public  static String emailsplitado;
    private SharedPreferences settings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tela_mudar_avatar, container, false);
        Firebase.setAndroidContext(getActivity());
        settings = getActivity().getSharedPreferences(com.example.isaquearaujo.apporganizaenem.Principal.PREFS_NAME, 0);
        firebaseAuth = FirebaseAuth.getInstance();
        principal = new Firebase("https://organiza-enem-app.firebaseio.com/");
        imageavatarconfig = (ImageView) rootView.findViewById(R.id.imageavatarconfig);
        cabeloanterior = (Button) rootView.findViewById(R.id.cabeloanteriorconfig);
        cabeloanterior.setOnClickListener(this);
        olhoanterior = (Button) rootView.findViewById(R.id.olhoanteriorconfig);
        olhoanterior.setOnClickListener(this);
        bocaanterior = (Button) rootView.findViewById(R.id.bocanteriorconfig);
        bocaanterior.setOnClickListener(this);
        cabeloposterior = (Button) rootView.findViewById(R.id.cabeloposteriorconfig);
        cabeloposterior.setOnClickListener(this);
        olhoposterior = (Button) rootView.findViewById(R.id.olhoposteriorconfig);
        olhoposterior.setOnClickListener(this);
        bocaposterior = (Button) rootView.findViewById(R.id.bocaposteriorconfig);
        bocaposterior.setOnClickListener(this);
        cor1 = (Button) rootView.findViewById(R.id.cor1config);
        cor1.setOnClickListener(this);
        cor2 = (Button) rootView.findViewById(R.id.cor2config);
        cor2.setOnClickListener(this);
        cor3 = (Button) rootView.findViewById(R.id.cor3config);
        cor3.setOnClickListener(this);
        okvatar = (Button) rootView.findViewById(R.id.okavatarconfig);
        okvatar.setOnClickListener(this);;
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
                numberrosto = Integer.parseInt(rostoitem);
                numberboca = Integer.parseInt(bocaitem);
                numbercabelo = Integer.parseInt(cabeloitem);
                numberolho = Integer.parseInt(olhoitem);
                avatarnumber = Integer.parseInt(avataritem);
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
                allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                imageavatarconfig.setImageBitmap(allimagessembranco);
                TelaPrincipal.progress.dismiss();

                //numerodaimagemavatar = Integer.parseInt(part2);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        return  rootView;

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
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cabeloanteriorconfig)
        {
            Log.d("Sexo", String.valueOf(sexo));
            if (avatarnumber == 1)
            {
                if (numbercabelo > 0)
                {
                    numbercabelo -= 1;
                    if (numbercabelo == 0)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 1)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo2);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 2)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo3);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                }
            }
            if (avatarnumber == 2)
            {
                if (numbercabelo > 0)
                {
                    numbercabelo -= 1;
                    if (numbercabelo == 0)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 1)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 2)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                }
            }
        }
        if (v.getId() == R.id.cabeloposteriorconfig)
        {
            if (avatarnumber == 1)
            {
                if (numbercabelo < 2)
                {
                    numbercabelo += 1;
                    if (numbercabelo == 0)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 1)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo2);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 2)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo3);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                }
            }
            if (avatarnumber == 2)
            {
                if (numbercabelo < 2)
                {
                    numbercabelo += 1;
                    if (numbercabelo == 0)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 1)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                    else if (numbercabelo == 2)
                    {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                        Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        imageavatarconfig.setImageBitmap(allimagessembranco);
                    }
                }
            }
        }
        if (v.getId() == R.id.olhoanteriorconfig)
        {
            if (numberolho > 0)
            {
                numberolho -= 1;
                if (numberolho == 0)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 1)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos2);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 2)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos3);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 3)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos4);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 4)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos5);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 5)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos6);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
            }
        }
        if (v.getId() == R.id.olhoposteriorconfig)
        {
            if (numberolho < 5)
            {
                numberolho += 1;
                if (numberolho == 0)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);;
                }
                else if (numberolho == 1)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos2);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 2)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos3);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 3)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos4);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 4)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos5);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberolho == 5)
                {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos6);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
            }
        }
        if (v.getId() == R.id.bocanteriorconfig)
        {
            if (numberboca > 0)
            {
                numberboca -= 1;
                if (numberboca == 0)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 1)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca2);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 2)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca3);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 3)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca4);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 4)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca5);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
            }
        }
        if (v.getId() == R.id.bocaposteriorconfig)
        {
            if (numberboca < 4)
            {
                numberboca += 1;
                if (numberboca == 0)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 1)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca2);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 2)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca3);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 3)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca4);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
                else if (numberboca == 4)
                {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca5);
                    allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
                    Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    imageavatarconfig.setImageBitmap(allimagessembranco);
                }
            }
        }
        if (v.getId() == R.id.cor1config)
        {
            numberrosto = 0;
            rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rostobranco);
            rostosembranco = BitmapFactory.decodeResource(getResources(), R.drawable.rosto);
            allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
            Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            imageavatarconfig.setImageBitmap(allimagessembranco);
        }
        if (v.getId() == R.id.cor2config)
        {
            numberrosto = 1;
            rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto2branco);
            rostosembranco = BitmapFactory.decodeResource(getResources(), R.drawable.rosto2);
            allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
            Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            imageavatarconfig.setImageBitmap(allimagessembranco);
        }
        if (v.getId() == R.id.cor3config)
        {
            numberrosto = 2;
            rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto3branco);
            rostosembranco = BitmapFactory.decodeResource(getResources(), R.drawable.rosto3);
            allimagessembranco = createSingleImageFromMultipleImages(rostosembranco, cabelo, olho, boca);
            Padrao.allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            imageavatarconfig.setImageBitmap(allimagessembranco);
        }
        if (v.getId() == R.id.okavatarconfig)
        {
            avatarnumber = 1;
            //atualizaravatar(nomeusuario, avatarnumber, sexo, numberrosto, numbercabelo, numberolho, numberboca);
            Padrao.roundImage = new RoundImage(Padrao.allimages);
            Padrao.imageview.setImageDrawable(Padrao.roundImage);
            usersavatar = principal.child("users").child(emailsplitado).child("Avatar");
            usersavatar.setValue(String.valueOf(avatarnumber).toString().trim());
            usersavatar = principal.child("users").child(emailsplitado).child("Face");
            usersavatar.setValue(String.valueOf(numberrosto).toString().trim());
            usersavatar = principal.child("users").child(emailsplitado).child("Cabelo");
            usersavatar.setValue(String.valueOf(numbercabelo).toString().trim());
            usersavatar = principal.child("users").child(emailsplitado).child("Olho");
            usersavatar.setValue(String.valueOf(numberolho).toString().trim());
            usersavatar = principal.child("users").child(emailsplitado).child("Boca");
            usersavatar.setValue(String.valueOf(numberboca).toString().trim());
            TelaPrincipal.viewpagerprincipal.setCurrentItem(1, false);
        }

    }
}
