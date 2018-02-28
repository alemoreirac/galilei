package com.example.pefoce.peritolocal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Enums.Genero;
import Enums.Vida.Secao;
import Model.Pessoa;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Util.Initializer;
import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.List;

import Util.SecaoDrawableUtil;
import io.fabric.sdk.android.Fabric;

import static Util.ImageUtil.Overlay;

public class LoginActivity extends AppCompatActivity
{

    SharedPreferences sharedpreferences;
    EditText edtLogin;
    EditText edtSenha;
    Button btnLogin;


    public static final String Preferencias = "Minhas_Preferencias";
    public static final String PrimeiroUso = "Primeiro_Uso";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);



//
//        EnvolvidoVida envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class,2l);
//
//        ArrayList<Lesao> lesaoList = new ArrayList<>();
//
//        List<Bitmap> imagens = new ArrayList<Bitmap>();
//
//        List<LesaoEnvolvido> lesaoEnvolvidoList = LesaoEnvolvido.find(LesaoEnvolvido.class,"envolvido_vida = ?",envolvidoVida.getId().toString());
//        for(LesaoEnvolvido le : lesaoEnvolvidoList)
//        {
//            if(le.getLesao()!=null)
//                lesaoList.add(le.getLesao());
//        }
//
//        Bitmap molde = BitmapFactory.decodeResource(getResources(), R.drawable.molde_masculino_frontal);
//
//
//        for (Lesao l : lesaoList)
//        {
//            if(envolvidoVida.getGenero().equals(Genero.NAO_IDENTIFICADO))
//                imagens.add(BitmapFactory.decodeResource(getResources(), SecaoDrawableUtil.findIdByString(l.getSecaoLesao().getValor(), Genero.MASCULINO.getValor())));
//            else
//                imagens.add(BitmapFactory.decodeResource(getResources(), SecaoDrawableUtil.findIdByString(l.getSecaoLesao().getValor(), envolvidoVida.getGenero().getValor())));
//        }
//
//        for (Bitmap bmp : imagens)
//        {
//            molde = Overlay(molde, null, bmp, 255);
//        }
 //       SecaoDrawableUtil.findIdByString(Secao.CLAVICULAR_TRASEIRO_ESQUERDO.getValor(), Genero.MASCULINO.getValor());

//        for(Secao s:Secao.values())
//        {
//            if(SecaoDrawableUtil.findIdByString(s.getValor(), Genero.MASCULINO.getValor()) == -1)
//            {
//                String x= s.getValor();
//            }
//            if(SecaoDrawableUtil.findIdByString(s.getValor(), Genero.FEMININO.getValor()) == -1)
//
//            {
//                String x= s.getValor();
//            }
//        }

        logUser();
        AssociarLayout();

        edtLogin.setText("123");
        edtSenha.setText("123");


        sharedpreferences = getSharedPreferences(Preferencias, Context.MODE_PRIVATE);

// Verifica se é a primeira utilização do usuário, para inicializar o banco de dados de usuários pre-cadastrados
         if(sharedpreferences.getBoolean(PrimeiroUso, true))
         {
              Initializer.InicializarPessoas();
             SharedPreferences.Editor editor = sharedpreferences.edit();
             editor.putBoolean(PrimeiroUso,false);
             editor.commit();
             Toast.makeText(LoginActivity.this,"Thanks",Toast.LENGTH_LONG).show();
         }

        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Pessoa pessoaLogin = null;
                if(edtLogin.getText().toString().length()>0 && edtSenha.getText().toString().length()>0)
                {
                    try
                    {
                        pessoaLogin = Pessoa.find(Pessoa.class, "login = ? AND senha = ?", edtLogin.getText().toString().trim(), edtSenha.getText().toString().trim()).get(0);
                    } catch (Exception e)
                    {
                        Toast.makeText(LoginActivity.this, "Usuário Não encontrado!", Toast.LENGTH_LONG);
                    }
                }
                if(pessoaLogin!= null)
                {
                   Intent it = new Intent(getBaseContext(),MainActivity.class);
                   it.putExtra("PeritoId", pessoaLogin.getId());
                   startActivity(it);
                }
               else
                   Toast.makeText(getBaseContext(),"Deu errado!",Toast.LENGTH_LONG);
            }
        });
      btnLogin.performClick();
    }

    private void AssociarLayout()
    {
        edtLogin= (EditText) findViewById(R.id.edt_login);
        edtSenha= (EditText) findViewById(R.id.edt_senha);
        btnLogin = (Button)findViewById(R.id.btn_login);
    }

    private void logUser() {
        Crashlytics.setUserIdentifier("Teste");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }

    public void onBackPressed()
    {

    }




}
