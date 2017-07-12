package com.example.pefoce.peritolocal;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

import Adapters.AdapterOcorrencia;
import Enums.DocumentoSolicitacao;
import Model.DocumentoOcorrencia;
import Model.OcorrenciaTransito;
import Model.Pessoa;
import Util.BuscadorEnum;

public class MainActivity extends Activity
{

    AdapterOcorrencia adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TextView txvNomePerito = (TextView)findViewById(R.id.txv_nome);
        ListView list = (ListView)findViewById(R.id.lstvOcorrencias);
        //takeScreenshot();

        Intent intent = getIntent();

        //Recebe o ID do perito logado, caso não seja encontrado, volta para a tela de login
        Long peritoId = intent.getLongExtra("PeritoId",0);

        if(peritoId == 0)
        {
            Intent it = new Intent(this,LoginActivity.class);
            startActivity(it);
        }

        //Busca o perito pelo id passado e depois busca as ocorrências relacionadas a ele
        final Pessoa p = Pessoa.findById(Pessoa.class,1l);

        final ArrayList<OcorrenciaTransito> ocorrencias = (ArrayList<OcorrenciaTransito>) OcorrenciaTransito.find(OcorrenciaTransito.class, "perito = ?", p.getId().toString());

        //Carrega as ocorrencias na lista
        adapter = new AdapterOcorrencia(ocorrencias,getApplicationContext());
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                OcorrenciaTransito ocorrenciaTransito = ocorrencias.get(position);
                Intent it = new Intent(getApplicationContext(),ManterPericia.class);
                it.putExtra("OcorrenciaId",ocorrenciaTransito.getId());
                startActivity(it);
            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           final int position, long id) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(MainActivity.this);
                }
                builder.setTitle("Deletar Ocorrência")
                        .setMessage("Você deseja deletar esta Ocorrência?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                OcorrenciaTransito ocorrenciaTransito = ocorrencias.get(position);
                                adapter.remove(ocorrenciaTransito);
                                ocorrenciaTransito.delete();

                                Toast.makeText(MainActivity.this, "Ocorrência Deletada com sucesso!", Toast.LENGTH_LONG).show();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });

        FloatingActionButton myFab = (FloatingActionButton)  findViewById(R.id.fab_Pericia);
                myFab.setOnClickListener(
                new View.OnClickListener() {
            public void onClick(View v) {

                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_pericia_transito);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                final EditText edtNumDoc = (EditText) dialog.findViewById(R.id.edt_dialog_NumDoc);

                final Spinner spnTipoDoc = (Spinner) dialog.findViewById(R.id.spn_dialog_TipoDoc);

                //Carrega os valores do Enum Documento Solicitacao no Spinner
                ArrayList<String> docs = new ArrayList<String>();

                for(DocumentoSolicitacao d : DocumentoSolicitacao.values())
                {
                    docs.add(d.getValor());
                }

                spnTipoDoc.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,docs));

                Button btnComecaPericia = (Button)dialog.findViewById(R.id.btn_dialog_IniciarPericia);

                btnComecaPericia.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if(edtNumDoc.getText().toString().length()>0)
                        {
                            OcorrenciaTransito ocorrenciaTransito = new OcorrenciaTransito();

                            DocumentoOcorrencia documentoOcorrencia = new DocumentoOcorrencia(BuscadorEnum.BuscarDocSolicitacao(spnTipoDoc.getSelectedItem().toString())
                                    ,edtNumDoc.getText().toString());

                            documentoOcorrencia.save();

                            ocorrenciaTransito.setDocumentoOcorrencia(documentoOcorrencia);

                            ocorrenciaTransito.setPerito(p);

                            ocorrenciaTransito.save();

                            Intent it = new Intent(MainActivity.this,ManterPericia.class);
                            it.putExtra("OcorrenciaId",ocorrenciaTransito.getId());

                            startActivity(it);
                        }

                        else
                            Toast.makeText(getApplicationContext(), "Preencha corretamente os dados!", Toast.LENGTH_LONG).show();
                    }
                });

                //Botão de cancelar a criação de perícia
                Button btnCancelaPericia = (Button)dialog.findViewById(R.id.btn_dialog_Cancelar);

                btnCancelaPericia.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });

            }
        });
    }


    private void takeScreenshot()
    {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);

        try
        {
            // image naming and path  to include sd card  appending name you choose for file
            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";

            // create bitmap screen capture
            View v1 = getWindow().getDecorView().getRootView();
            v1.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
            v1.setDrawingCacheEnabled(false);

            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

        }
        catch (Throwable e)
        {
            // Several error may come out with file handling or OOM
            e.printStackTrace();
        }
    }

}
