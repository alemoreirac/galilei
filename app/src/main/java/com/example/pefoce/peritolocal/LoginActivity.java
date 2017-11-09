package com.example.pefoce.peritolocal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import Model.EnderecoTransito;
import Model.Ocorrencia;
import Model.OcorrenciaEndereco;
import Model.Pessoa;
import Util.Initializer;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;
import me.panavtec.drawableview.DrawableView;
import me.panavtec.drawableview.DrawableViewConfig;

public class LoginActivity extends AppCompatActivity
{

    SharedPreferences sharedpreferences;

    public static final String Preferencias = "Minhas_Preferencias";
    public static final String PrimeiroUso = "Primeiro_Uso";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_login);
        logUser();

//        Pessoa p2 = null;
//        p2.getNome();
//
//        Pessoa p = new Pessoa();
//        //p.setNome("Nei");
//
//        try
//        {
//            p.setNome("Banana");
//            int x = Integer.parseInt(p.getNome());
//
//            if (p.getNome().indexOf(0) == 'A')
//            {
//                Toast.makeText(this, "O nome começa com A", Toast.LENGTH_LONG).show();
//            } else
//            {
//                Toast.makeText(this, "O nome não começa com A", Toast.LENGTH_LONG).show();
//            }
//        }catch(Exception e)
//        {
//            switch (e.toString())
//            {
//                case "NullPointer":
//                    Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
//                    break;
//
//            }
//        }




//        final DrawableView drawableView = (DrawableView) findViewById(R.id.dv_Teste);
//        DrawableViewConfig config = new DrawableViewConfig();
//        final ImageView imgv = (ImageView) findViewById(R.id.imgv_result);
//        config.setStrokeColor(getResources().getColor(android.R.color.holo_red_light));
//        config.setShowCanvasBounds(true); // If the view is bigger than canvas, with this the user will see the bounds (Recommended)
//        config.setStrokeWidth(5.0f);
//        config.setMinZoom(0.0f);
//        config.setMaxZoom(0.0f);
//
//        config.setCanvasHeight(300);
//
//        config.setCanvasWidth(300);
//        drawableView.setConfig(config);



//        Pessoa p= null;
//        p.setNascimento(null);
//        p.save();

   // Pessoa p = new Pessoa("Login","Nome","senha");
   //     p.setNome("ale");
   //     p.setTeste(1);
   //     p.setLogin("");
   //     //p.setNascimento();
   //     p.save();

     //  try {
     //      OOUtils.open(new File(Environment.getExternalStorageDirectory() + "/teste.odt"));
     //      ODPackage odPackage = ODPackage.createFromFile(new File(Environment.getExternalStorageDirectory() + "/teste.odt"));
     //      ODDocument odDocument = odPackage.getODDocument();
     //      TextDocument txdoc = odPackage.getTextDocument();

     //      ODSingleXMLDocument p1 = ODSingleXMLDocument.createFromPackage(new File(Environment.getExternalStorageDirectory() + "/teste.odt"));






     //  } catch (IOException e1) {
     //      e1.printStackTrace();
     //  } catch (JDOMException e) {
     //      e.printStackTrace();
     //  }

        // Load the template.
        // Java 5 users will have to use RhinoFileTemplate instead

        // Fill with sample values.


        // Open the document with OpenOffice.org !
        //OOUtils.open(outFile);


//    Foto f = Foto.listAll(Foto.class).get(0);
//            Gson gson = new Gson();
//            String s = gson.toJson(f);


        //    byte[] b = new byte[12];
        //    b[0] = 1;
        //    b[1] = 12;
        //    b[2] = 14;
        //    b[3] = 15;
        //    b[4] = 16;
        //    b[5] = 17;
        //    b[7] = 18;
        //    b[8] = 19;
        //    b[6] = 12;
        //    b[9] = 11;
        //    Foto f = new Foto("ale",b);
        //    f.save();
//
        //    SQLiteDatabase mydatabase = openOrCreateDatabase("pefoce_db.db",MODE_PRIVATE,null);
//
        //    Cursor c = mydatabase.rawQuery("SELECT * from FOTO",null);
//
        //    c.moveToFirst();


        //Initializer.InicializarPessoas();

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

//                imgv.setImageBitmap(drawableView.obtainBitmap());
//
//
//                busca os dados cadastrais no banco e passa a pessoa como parâmetro para a MainActivity caso funcione.

//                Pessoa p =Pessoa.find(Pessoa.class,"login = ? AND senha = ?", edtLogin.getText().toString(),edtSenha.getText().toString()).get(0);
//
//                Pessoa p =Pessoa.find(Pessoa.class,"login = ? AND senha = ?","05704656307","ale123").get(0);

                Pessoa pessoaLogin = null;
                if(edtLogin.getText().toString().length()>0 && edtSenha.getText().toString().length()>0)
                {
                    try
                    {
                        pessoaLogin = Pessoa.find(Pessoa.class, "login = ? AND senha = ?", edtLogin.getText().toString().trim(), edtSenha.getText().toString().trim()).get(0);
                    } catch (Exception e)
                    {
                     //   Toast.makeText(LoginActivity.this, "Usuário Não encontrado!", Toast.LENGTH_LONG);
                    }
                }
                if(pessoaLogin!= null)
                {
                   Intent it = new Intent(getBaseContext(),MainActivity.class);
                    //Intent it = new Intent(getBaseContext(),MainActivity.class);
                   it.putExtra("PeritoId", pessoaLogin.getId());
                   startActivity(it);
                }
               else
                   Toast.makeText(getBaseContext(),"Deu errado!",Toast.LENGTH_LONG);

            }
        });
        //button.performClick();
    }

    private void logUser() {
        Crashlytics.setUserIdentifier("Teste");
        Crashlytics.setUserEmail("user@fabric.io");
        Crashlytics.setUserName("Test User");
    }



}
