package com.example.isaquearaujo.apporganizaenem;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Map;

public class TelaCustomizacaoAvatar extends AppCompatActivity implements View.OnClickListener{
    ImageView avatar;
    Button cabeloanterior, olhoanterior, bocaanterior, cabeloposterior, olhoposterior, bocaposterior, cor1, cor2, cor3, okvatar;
    Bitmap rosto;
    Bitmap cabelo;
    Bitmap olho;
    Bitmap boca;
    Bitmap allimages;
    int numberrosto, numbercabelo, numberolho, numberboca;
    int avatarnumber;
    private String nomeusuario;
    ViewPager montagemavatar;
    ImageView masculino, feminino;
    int sexo = 0;
    private ProgressDialog progress;
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    private Firebase users;
    private Firebase usersexo;
    private Firebase useravatar;
    private Firebase userrosto;
    private Firebase usercabelo;
    private Firebase userolho;
    private Firebase userboca;
    public  static String emailsplitado;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_customizacao_avatar);
        Firebase.setAndroidContext(this);
        settings = getSharedPreferences(com.example.isaquearaujo.apporganizaenem.Principal.PREFS_NAME, 0);
        firebaseAuth = FirebaseAuth.getInstance();
        principal = new Firebase("https://organiza-enem-app.firebaseio.com/");
        avatarnumber = 1;
        montagemavatar = (ViewPager) findViewById(R.id.escolheravatar);
        montagemavatar.setAdapter(new WizardPagerAdapter());
        montagemavatar.setOffscreenPageLimit(2);
        nomeusuario = TelaLogin.usuario.getText()
                .toString();
        masculino = (ImageView) findViewById(R.id.masculino);
        masculino.setOnClickListener(this);
        feminino = (ImageView) findViewById(R.id.feminino);
        feminino.setOnClickListener(this);
        avatar = (ImageView) findViewById(R.id.imageavatar);
        cabeloanterior = (Button) findViewById(R.id.cabeloanterior);
        cabeloanterior.setOnClickListener(this);
        olhoanterior = (Button) findViewById(R.id.olhoanterior);
        olhoanterior.setOnClickListener(this);
        bocaanterior = (Button) findViewById(R.id.bocanterior);
        bocaanterior.setOnClickListener(this);
        cabeloposterior = (Button) findViewById(R.id.cabeloposterior);
        cabeloposterior.setOnClickListener(this);
        olhoposterior = (Button) findViewById(R.id.olhoposterior);
        olhoposterior.setOnClickListener(this);
        bocaposterior = (Button) findViewById(R.id.bocaposterior);
        bocaposterior.setOnClickListener(this);
        cor1 = (Button) findViewById(R.id.cor1);
        cor1.setOnClickListener(this);
        cor2 = (Button) findViewById(R.id.cor2);
        cor2.setOnClickListener(this);
        cor3 = (Button) findViewById(R.id.cor3);
        cor3.setOnClickListener(this);
        okvatar = (Button) findViewById(R.id.okavatar);
        okvatar.setOnClickListener(this);
        String emailsplit = settings.getString("email", null).toString().trim();
        emailsplit.replace(".", ",");
        emailsplitado = emailsplit.replace("." , ",");
        users = principal.child("users").child(emailsplitado);
        users.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Map<String,String> mapa = dataSnapshot.getValue(Map.class);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        numberolho = 0;
        numberboca = 0;
        numbercabelo = 0;
        numberolho = 0;
        rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto);
        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo);
        olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos);
        boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca);
        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
        avatar.setImageBitmap(allimages);
    }
    private Bitmap createSingleImageFromMultipleImages(Bitmap rosto, Bitmap cabelo, Bitmap olho, Bitmap boca)
    {
        Bitmap result = Bitmap.createBitmap(rosto.getWidth(), rosto.getHeight(), rosto.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(rosto, 0f, 0f, null);
        canvas.drawBitmap(olho, 0, 0, null);
        canvas.drawBitmap(cabelo, 0, 0, null);
        canvas.drawBitmap(boca, 0, 0, null);
        return result;
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.cabeloanterior) {
            if (sexo == 1) {
                if (numbercabelo > 0) {
                    numbercabelo -= 1;
                    if (numbercabelo == 0) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 1) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo2);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 2) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo3);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    }
                }
            }
            if (sexo == 2) {
                if (numbercabelo > 0) {
                    numbercabelo -= 1;
                    if (numbercabelo == 0) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 1) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 2) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    }
                }
            }
        }
        if (v.getId() == R.id.cabeloposterior) {
            if (sexo == 1) {
                if (numbercabelo < 2) {
                    numbercabelo += 1;
                    if (numbercabelo == 0) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 1) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo2);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 2) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo3);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    }
                }
            }
            if (sexo == 2) {
                if (numbercabelo < 2) {
                    numbercabelo += 1;
                    if (numbercabelo == 0) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 1) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    } else if (numbercabelo == 2) {
                        cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
                        allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                        avatar.setImageBitmap(allimages);
                    }
                }
            }
        }
        if (v.getId() == R.id.olhoanterior) {
            if (numberolho > 0) {
                numberolho -= 1;
                if (numberolho == 0) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 1) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos2);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 2) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos3);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 3) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos4);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 4) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos5);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 5) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos6);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                }
            }
        }
        if (v.getId() == R.id.olhoposterior) {
            if (numberolho < 5) {
                numberolho += 1;
                if (numberolho == 0) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 1) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos2);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 2) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos3);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 3) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos4);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 4) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos5);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberolho == 5) {
                    olho = BitmapFactory.decodeResource(getResources(), R.drawable.olhos6);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                }
            }
        }
        if (v.getId() == R.id.bocanterior) {
            if (numberboca > 0) {
                numberboca -= 1;
                if (numberboca == 0) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 1) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca2);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 2) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca3);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 3) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca4);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 4) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca5);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                }
            }
        }
        if (v.getId() == R.id.bocaposterior) {
            if (numberboca < 4) {
                numberboca += 1;
                if (numberboca == 0) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 1) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca2);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 2) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca3);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 3) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca4);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                } else if (numberboca == 4) {
                    boca = BitmapFactory.decodeResource(getResources(), R.drawable.boca5);
                    allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
                    avatar.setImageBitmap(allimages);
                }
            }
        }
        if (v.getId() == R.id.cor1) {
            numberrosto = 0;
            rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto);
            allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            avatar.setImageBitmap(allimages);
        }
        if (v.getId() == R.id.cor2) {
            numberrosto = 1;
            rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto2);
            allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            avatar.setImageBitmap(allimages);
        }
        if (v.getId() == R.id.cor3) {
            numberrosto = 2;
            rosto = BitmapFactory.decodeResource(getResources(), R.drawable.rosto3);
            allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            avatar.setImageBitmap(allimages);
        }
        if (v.getId() == R.id.okavatar) {
            avatarnumber = 1;
            useravatar = principal.child("users").child(emailsplitado).child("Avatar");
            useravatar.setValue(String.valueOf(avatarnumber).toString().trim());
            userrosto = principal.child("users").child(emailsplitado).child("Face");
            userrosto.setValue(String.valueOf(numberrosto).toString().trim());
            usercabelo = principal.child("users").child(emailsplitado).child("Cabelo");
            usercabelo.setValue(String.valueOf(numbercabelo).toString().trim());
            userolho = principal.child("users").child(emailsplitado).child("Olho");
            userolho.setValue(String.valueOf(numberolho).toString().trim());
            userboca = principal.child("users").child(emailsplitado).child("Boca");
            userboca.setValue(String.valueOf(numberboca).toString().trim());
        }
        if (v.getId() == R.id.masculino) {
            sexo = 1;
            cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo);
            allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            avatar.setImageBitmap(allimages);
            montagemavatar.setCurrentItem(1);
            String emailsplit = TelaLogin.usuario.getText().toString().trim();
            emailsplit.replace(".", ",");
            emailsplitado = emailsplit.replace(".", ",");
            usersexo = principal.child("users").child(emailsplitado).child("Sexo");
            usersexo.setValue(String.valueOf(sexo).toString().trim());


        }
        if (v.getId() == R.id.feminino) {
            sexo = 2;
            cabelo = BitmapFactory.decodeResource(getResources(), R.drawable.cabelo4);
            allimages = createSingleImageFromMultipleImages(rosto, cabelo, olho, boca);
            avatar.setImageBitmap(allimages);
            montagemavatar.setCurrentItem(1);
            usersexo = principal.child("users").child(emailsplitado).child("Sexo");
            usersexo.setValue(String.valueOf(sexo).toString().trim());

        }
    }
    class WizardPagerAdapter extends PagerAdapter
    {
        public Object instantiateItem(View collection, int position)
        {
            int resId = 0;
            switch (position)
            {
                case 0:
                    resId = R.id.escolhersexo;
                    break;
                case 1:
                    resId = R.id.montaravatar;
                    break;
            }
            return findViewById(resId);
        }
        @Override
        public int getCount()
        {
            return 2;
        }
        @Override
        public boolean isViewFromObject(View arg0, Object arg1)
        {
            return arg0 == ((View) arg1);
        }
    }
}
