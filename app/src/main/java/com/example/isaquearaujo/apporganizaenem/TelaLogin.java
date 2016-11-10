package com.example.isaquearaujo.apporganizaenem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class TelaLogin extends AppCompatActivity {
    public static EditText usuario, senha;
    private Button entrar;
    private TextView criarconta;
    public static String nomeusuario, senhausuario;
    private static final String REGISTER_URL = "http://oficina2016.esy.es/oficina.php";
    Typeface fonttexto;
    private ProgressDialog progress;
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    private Firebase users;
    public  static String emailsplitado;
    private SharedPreferences settings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_login);
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        //encontrar assets da tela inicial
        //region Identificação dos assets da Tela Inicial
        usuario = (EditText) findViewById(R.id.login);
        senha = (EditText) findViewById(R.id.password);
        entrar = (Button) findViewById(R.id.LogIn);
        criarconta = (TextView) findViewById(R.id.naoexisteconta);
        fonttexto = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-ExtraBold.ttf");
        criarconta.setTypeface(fonttexto);
        entrar.setTypeface(fonttexto);
        principal = new Firebase("https://organiza-enem-app.firebaseio.com/");
        settings = getSharedPreferences(com.example.isaquearaujo.apporganizaenem.Principal.PREFS_NAME, 0);
        //endregion
        //Evento click do botão de entrar
        //region Eventos clicks para entrar e ir para a tela de cadastro
        entrar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String emailsplit = usuario.getText().toString().trim();
                emailsplit.replace(".", ",");
                emailsplitado = emailsplit.replace("." , ",");
                users = principal.child("users").child(emailsplitado).child("Avatar");
                if (isOnline() == true)
                {
                    nomeusuario = usuario.getText().toString();
                    senhausuario = senha.getText().toString();
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putString("email", nomeusuario);
                    editor.putString("senha",senhausuario);
                    editor.commit();
                    LoginUser();
                }
                else
                {
                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "Não é possível entrar, você está sem conexão com a internet", Toast.LENGTH_LONG)
                            .show();
                }
            }
        });
        //Evento click do botao de registrar novo usuário
        criarconta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(TelaLogin.this, TelaCadastro.class);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.animator.slide_in_right, R.animator.slide_out_right);
            }
        });

    }
    public boolean isOnline()
    {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null && manager.getActiveNetworkInfo()
                .isConnectedOrConnecting();
    }
    private  void LoginUser()
    {
        String textoemail = usuario.getText().toString().trim();
        String textosenha = senha.getText().toString().trim();
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
        progress = new ProgressDialog(TelaLogin.this,R.style.full_screen_dialog) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.custom_progressdialog);
                getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
            }
        };
        progress.show();
        firebaseAuth.signInWithEmailAndPassword(textoemail,textosenha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Log.d("vaivai", "porfavor");
                    users.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String message = dataSnapshot.getValue(String.class);
                            if(message.contains("0"))
                            {
                                Intent intent = new Intent(TelaLogin.this, TelaCustomizacaoAvatar.class);
                                startActivityForResult(intent, 0);
                            }
                            else
                            {
                                Intent intent = new Intent(TelaLogin.this, TelaPrincipal.class);
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
                    Toast.makeText(TelaLogin.this,"Login Succesefuly", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(TelaLogin.this,"Could not login user", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
            }
        });

    }
}
