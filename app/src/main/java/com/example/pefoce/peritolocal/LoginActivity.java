package com.example.pefoce.peritolocal;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Control.Transito.ColisaoBusiness;
import Control.usuarioBusiness;
import Model.Ocorrencia;
import Model.Usuario;
import Util.AutoCompleteUtil;
import Util.Initializer;

import com.crashlytics.android.Crashlytics;

import Util.UpdateUtil;
import io.fabric.sdk.android.Fabric;



public class LoginActivity extends AppCompatActivity
{
    SharedPreferences sharedpreferences;
    AutoCompleteTextView aucLogin;
    EditText edtSenha;
    Button btnLogin;
    TextView txvVersao;

    public static final String Preferencias = "Minhas_Preferencias";
    public static final String PrimeiroUso = "Primeiro_Uso";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        EnviarDados();

//        ColisaoBusiness.findAllPedestresByColisaoString(1l);

       // UpdateUtil.construirPacoteTransito(Ocorrencia.listAll(Ocorrencia.class).get(0));

//          UpdateUtil.construirPacoteVida(Ocorrencia.listAll(Ocorrencia.class).get(1));

        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);

        sharedpreferences = getSharedPreferences(Preferencias, Context.MODE_PRIVATE);

        if (sharedpreferences.getBoolean(PrimeiroUso, true))
        {
            Initializer.Popular();
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(PrimeiroUso, false);
            editor.commit();
        }

        logUser();
        AssociarLayout();

        //Verifica se é a primeira utilização do usuário, para inicializar o banco de dados de usuários pre-cadastrados
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Usuario usuarioLogin = usuarioBusiness.loginUsuario(aucLogin.getText().toString(),edtSenha.getText().toString());

                if(usuarioLogin!=null)
                {
                    Intent it = new Intent(getBaseContext(), MainActivity.class);
                    it.putExtra("PeritoId", usuarioLogin.getId());
                    startActivity(it);
                }
                else
                    Toast.makeText(getBaseContext(), "Verifique os dados inseridos!", Toast.LENGTH_LONG);
//                if (aucLogin.getText().toString().length() > 0 && edtSenha.getText().toString().length() > 0)
//                {
//                    try
//                    {
//                        usuarioLogin = Usuario.find(Usuario.class, "email = ? AND password = ?", aucLogin.getText().toString().trim(), edtSenha.getText().toString().trim()).get(0);
//                    } catch (Exception e)
//                    {
//                        Toast.makeText(LoginActivity.this, "Usuário Não encontrado!", Toast.LENGTH_LONG);
//                    }
//                }
//                else
//                    Toast.makeText(getBaseContext(), "Verifique os dados inseridos!", Toast.LENGTH_LONG);
//
//                if (usuarioLogin != null)
//                {
//
//                } else
//
            }
        });

//        aucLogin.setText("123");
//        edtSenha.setText("123");
//        btnLogin.performClick();
    }

    private void AssociarLayout()
    {
        edtSenha = (EditText) findViewById(R.id.edt_senha);

        aucLogin = (AutoCompleteTextView) findViewById(R.id.auc_login);

        btnLogin = (Button) findViewById(R.id.btn_login);

        txvVersao = (TextView) findViewById(R.id.txv_Versao);

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

        txvVersao.setText(BuildConfig.VERSION_NAME);
    }

    private void logUser()
    {
        Crashlytics.setUserIdentifier("Teste");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }

    public void onBackPressed()
    {}
    public void EnviarDados()
    {

        DownloadManager mManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request mRqRequest = new DownloadManager.Request(
                Uri.parse("https://docs.google.com/uc?export=download&id=1Wrt-ny-aiE-r5gFiBxP_PRtvjF_FI2ro"));
        mRqRequest.setDescription("Atualização");

        long idDownLoad=mManager.enqueue(mRqRequest);


}}