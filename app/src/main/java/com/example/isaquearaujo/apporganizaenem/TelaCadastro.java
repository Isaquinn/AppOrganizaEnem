package com.example.isaquearaujo.apporganizaenem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TelaCadastro extends AppCompatActivity {
    private EditText nomeusario, emialusuario, senhausuario;
    private Button registrarnovousuario;
    private static int selecaodeavatar;
    Typeface fonttexto;
    private ProgressDialog progress;
    private FirebaseAuth firebaseAuth;
    private Firebase principal;
    private Firebase users;
    public  static  String emailsplit;
    private TextView entrar;
    //endregion
    String materias = "PORTUGUÊS/LITERATURA!MATEMÁTICA!BIOLOGIA!HISTÓRIA!GEOGRAFIA!FILOSOFIA!SOCIOLOGIA!FÍSICA!QUÍMICA!";
    String materiadodia = "PronomesYAcentosYAlgebraYGeometriaYCelulasYSeleçãoNatural";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);
        Firebase.setAndroidContext(this);
        firebaseAuth = FirebaseAuth.getInstance();
        principal = new Firebase("https://organiza-enem-app.firebaseio.com/");
        users = principal.child("users");
        //region Identificação de assets
        nomeusario = (EditText)findViewById(R.id.username);
        emialusuario = (EditText)findViewById(R.id.email);
        senhausuario = (EditText)findViewById(R.id.passwordcadastro);
        registrarnovousuario = (Button)findViewById(R.id.Registrarnovousuario);
        entrar = (TextView)findViewById(R.id.entrar);
        //jatemconta = (TextView)findViewById(R.id.jaexisteconta);
        fonttexto =  Typeface.createFromAsset(getAssets(),"fonts/OpenSans-ExtraBold.ttf");
        registrarnovousuario.setTypeface(fonttexto);
        //endregion
        //region Evento click para registrar novo usuario
        registrarnovousuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecaodeavatar = 0;
                if(nomeusario != null && validar(emialusuario.getText().toString()) && senhausuario!=null && selecaodeavatar == 0)
                {
                    if(isOnline() == true) {
                        registerUser();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Não é possível se cadastrar, você está sem conexão com a internet", Toast.LENGTH_LONG).show();
                    }
                }
                else if(nomeusario != null && validar(emialusuario.getText().toString()) == false && senhausuario!=null && selecaodeavatar== 0)
                {
                    Toast.makeText(TelaCadastro.this, "Por favor insira um email valido!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(nomeusario == null || validar(emialusuario.getText().toString()) || senhausuario==null || selecaodeavatar== 0)
                {
                    Toast.makeText(TelaCadastro.this, "Por favor preencha todos os campos!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaCadastro.this, TelaLogin.class);
                startActivityForResult(intent, 0);
                overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_left);
            }
        });
    }
    public static boolean validar(String email)
    {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
    //endregion
    //region Verificador de conexão com a internet
    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
    private  void registerUser()
    {
        final String textoemail = emialusuario.getText().toString().trim();
        final String textosenha = senhausuario.getText().toString().trim();
        final String textonome = nomeusario.getText().toString().trim();
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
        progress = new ProgressDialog(TelaCadastro.this,R.style.full_screen_dialog) {
            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.custom_progressdialog);
                getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.FILL_PARENT);
            }
        };
        progress.show();
        firebaseAuth.createUserWithEmailAndPassword(textoemail,textosenha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Map<String, String> userData = new HashMap<String, String>();
                    userData.put("User", textonome);
                    userData.put("Data","");
                    userData.put("Avatar","0");
                    userData.put("Sexo","");
                    userData.put("Face","");
                    userData.put("Cabelo","");
                    userData.put("Olho","");
                    userData.put("Boca","");
                    userData.put("Data","");
                    userData.put("Xp","0");
                    userData.put("Porcentagem","0");
                    userData.put("Materias","");
                    userData.put("ListaDoDia","");
                    userData.put("ListaMateriaPendentePortugues","");
                    userData.put("ListaMateriaPendenteMatematica","");
                    userData.put("ListaMateriaPendenteBiologia","");
                    userData.put("ListaMateriaPendenteHistoria","");
                    userData.put("ListaMateriaPendenteGeografia","");
                    userData.put("ListaMateriaPendenteFilosofia","");
                    userData.put("ListaMateriaPendenteSociologia","");
                    userData.put("ListaMateriaPendenteFisica","");
                    userData.put("ListaMateriaPendenteQuimica","");
                    userData.put("ListaMateriaFeitaPortugues","");
                    userData.put("ListaMateriaFeitaMatematica","");
                    userData.put("ListaMateriaFeitaBiologia","");
                    userData.put("ListaMateriaFeitaHistoria","");
                    userData.put("ListaMateriaFeitaGeografia","");
                    userData.put("ListaMateriaFeitaFilosofia","");
                    userData.put("ListaMateriaFeitaSociologia","");
                    userData.put("ListaMateriaFeitaFisica","");
                    userData.put("ListaMateriaFeitaQuimica","");
                    String nomesalvar = emialusuario.getText().toString().trim();
                    nomesalvar.replace('.', ',');
                    emailsplit = nomesalvar.replace('.', ',');
                    users = principal.child("users").child(emailsplit);
                    users.setValue(userData);
                    String[] Materias = {"PORTUGUÊS/LITERATURA", "MATEMÁTICA", "BIOLOGIA", "HISTÓRIA", "GEOGRAFIA", "FILOSIFIA", "SOCIOLOGIA", "FÍSICA", "QUÍMICA"};
                    principal.child("users").child(emailsplit).child("Materias").setValue(Materias);
                    String[] ListaDoDia = {"Pontuação", "Figuras de Linguagem", "Crase", "Advérbios"};
                    principal.child("users").child(emailsplit).child("ListaDoDia").setValue(ListaDoDia);
                    Toast.makeText(TelaCadastro.this,"Registered Succesefuly", Toast.LENGTH_SHORT).show();
                    progress.dismiss();
                }
                else
                {
                    progress.dismiss();
                    Toast.makeText(TelaCadastro.this,"Could not register user", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void onBackPressed()  {
        Intent intent = new Intent(TelaCadastro.this, TelaLogin.class );
        startActivityForResult(intent, 0);
        overridePendingTransition(R.animator.slide_in_left, R.animator.slide_out_left);
    }
}
