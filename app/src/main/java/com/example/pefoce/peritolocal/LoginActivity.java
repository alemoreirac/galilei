package com.example.pefoce.peritolocal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Dialogs.InconclusivoDialog;
import Enums.Genero;
import Enums.Vida.Secao;
import Model.Pessoa;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Util.AutoCompleteUtil;
import Util.BusinessOcorrencia;
import Util.Initializer;

import com.crashlytics.android.Crashlytics;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.List;

import Util.SecaoDrawableUtil;
import io.fabric.sdk.android.Fabric;

import static Util.ImageUtil.Overlay;

public class LoginActivity extends AppCompatActivity
{

    SharedPreferences sharedpreferences;
    AutoCompleteTextView aucLogin;
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


        sharedpreferences = getSharedPreferences(Preferencias, Context.MODE_PRIVATE);

        if (sharedpreferences.getBoolean(PrimeiroUso, true))
        {
            Initializer.InicializarPessoas();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(PrimeiroUso, false);
            editor.commit();
            Toast.makeText(LoginActivity.this, "Thanks", Toast.LENGTH_LONG).show();
        }

        logUser();
        AssociarLayout();


// Verifica se é a primeira utilização do usuário, para inicializar o banco de dados de usuários pre-cadastrados


        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Pessoa pessoaLogin = null;
                if (aucLogin.getText().toString().length() > 0 && edtSenha.getText().toString().length() > 0)
                {
                    try
                    {
                        pessoaLogin = Pessoa.find(Pessoa.class, "login = ? AND senha = ?", aucLogin.getText().toString().trim(), edtSenha.getText().toString().trim()).get(0);
                    } catch (Exception e)
                    {
                        Toast.makeText(LoginActivity.this, "Usuário Não encontrado!", Toast.LENGTH_LONG);
                    }
                }
                else
                    Toast.makeText(getBaseContext(), "Verifique os dados inseridos!", Toast.LENGTH_LONG);

                if (pessoaLogin != null)
                {
                    Intent it = new Intent(getBaseContext(), MainActivity.class);
                    it.putExtra("PeritoId", pessoaLogin.getId());
                    startActivity(it);
                } else
                    Toast.makeText(getBaseContext(), "Verifique os dados inseridos!", Toast.LENGTH_LONG);
            }
        });

        aucLogin.setText("123");
        edtSenha.setText("123");
        btnLogin.performClick();
    }

    private void AssociarLayout()
    {
        edtSenha = (EditText) findViewById(R.id.edt_senha);

        aucLogin = (AutoCompleteTextView) findViewById(R.id.auc_login);

        btnLogin = (Button) findViewById(R.id.btn_login);

        aucLogin.setAdapter(AutoCompleteUtil.getEmails(this));

        aucLogin.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3)
            {
                edtSenha.requestFocus();
            }
        });

        edtSenha.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    btnLogin.performClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void logUser()
    {
        Crashlytics.setUserIdentifier("Teste");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }

    public void onBackPressed()
    {

    }


}
