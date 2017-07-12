package com.example.pefoce.peritolocal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.Pessoa;
import Model.Veiculo;
import Util.Initializer;

public class LoginActivity extends AppCompatActivity
{

    SharedPreferences sharedpreferences;

    public static final String Preferencias = "Minhas_Preferencias" ;
    public static final String PrimeiroUso = "Primeiro_Uso";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

     //   Initializer.InicializarPessoas();

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

        final Button button = (Button)findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                EditText edtLogin = (EditText) findViewById(R.id.edt_login);
                EditText edtSenha = (EditText) findViewById(R.id.edt_senha);


                //busca os dados cadastrais no banco e passa a pessoa como parâmetro para a MainActivity caso funcione.

                //Pessoa p =Pessoa.find(Pessoa.class,"login = ? AND senha = ?", edtLogin.getText().toString(),edtSenha.getText().toString()).get(0);

                Pessoa p =Pessoa.find(Pessoa.class,"login = ? AND senha = ?","05704656307","ale123").get(0);

                if(p!= null)
                {
                   Intent it = new Intent(getBaseContext(),MainActivity.class);
                    //Intent it = new Intent(getBaseContext(),MainActivity.class);
                   it.putExtra("PeritoId", p.getId());
                   startActivity(it);
                }
                else
                    Toast.makeText(getBaseContext(),"Deu errado!",Toast.LENGTH_LONG);

            }
        });
    }
}
