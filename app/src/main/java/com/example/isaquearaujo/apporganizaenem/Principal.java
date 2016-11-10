package com.example.isaquearaujo.apporganizaenem;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Debug;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Principal extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    private Firebase users;
    public  static String emailsplitado;
    public static final String PREFS_NAME = "organizaenem";
    private SharedPreferences settings;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        settings = getSharedPreferences(PREFS_NAME, 0);
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        principal = new Firebase("https://organiza-enem-app.firebaseio.com/");
        String emailsplit = settings.getString("email", "").toString().trim();
        emailsplit.replace(".", ",");
        emailsplitado = emailsplit.replace("." , ",");
        users = principal.child("users").child(emailsplitado).child("Avatar");
        Log.d("sdasdasdasd", emailsplit + "oqodadas");
        handler = new Handler();
        if(emailsplit.equals(""))
        {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(Principal.this, TelaLogin.class);
                    startActivityForResult(intent, 0);
                }
            }, 2 * 1000);


        }
        else if(!emailsplit.equals(""))
        {

            if(verificaConexao() == true)
            {
                Log.d("nao","eoq");
                LoginUser();
            }
            else
            {
                Toast.makeText(this, "Não foi possivel conectar", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Principal.this, TelaLogin.class);
                startActivityForResult(intent, 0);
            }

        }
    }
    private  void LoginUser()
    {
        String textoemail = settings.getString("email",null).toString().trim();
        String textosenha = settings.getString("senha",null).toString().trim();
        if(TextUtils.isEmpty(textoemail))
        {
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(textosenha))
        {
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(textoemail,textosenha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Log.d("Conectrou?", "sim");
                    users.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String message = dataSnapshot.getValue(String.class);
                            if(message.contains("0"))
                            {
                                Intent intent = new Intent(Principal.this, TelaLogin.class);
                                startActivityForResult(intent, 0);
                            }
                            else
                            {
                                Intent intent = new Intent(Principal.this, TelaPrincipal.class);
                                startActivityForResult(intent, 0);
                            }
                        }
                        @Override
                        public void onCancelled(FirebaseError firebaseError) {

                        }
                    });
                    /*Toast.makeText(NewLogin.this,"Login Succesefuly", Toast.LENGTH_SHORT).show();
                    Intent iinent= new Intent(NewLogin.this,CustomAvatar.class);
                    startActivity(iinent);*/
                    //Toast.makeText(principal.this,"Login Succesefuly", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Intent intent = new Intent(Principal.this, TelaLogin.class);
                    startActivityForResult(intent, 0);

                }
            }
        });

    }
    public  boolean verificaConexao() {
        boolean conectado;
        ConnectivityManager conectivtyManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (conectivtyManager.getActiveNetworkInfo() != null
                && conectivtyManager.getActiveNetworkInfo().isAvailable()
                && conectivtyManager.getActiveNetworkInfo().isConnected()) {
            conectado = true;
        } else {
            conectado = false;
        }
        return conectado;
    }
}
