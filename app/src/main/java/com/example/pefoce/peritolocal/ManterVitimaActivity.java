package com.example.pefoce.peritolocal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Enums.DocumentoPessoa;
import Enums.Genero;
import Model.Transito.EnvolvidoTransito;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Util.BuscadorEnum;

public class ManterVitimaActivity extends AppCompatActivity {

    EditText edtNome= null;
    CheckBox cxbFatal = null;
    CheckBox cxbDesconhecido = null;
    TextView txvNascimento = null;
    Spinner spnTipoDocumento = null;
    Spinner spnGenero = null;
    EditText edtNumDocumento = null;

    EnvolvidoTransito  vitima = null;
    OcorrenciaTransitoEnvolvido ocorrenciaEnvolvido = null;
    OcorrenciaTransito ocorrenciaTransito = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_vitima);

        AssociarLayout();
        PovoarSpinner(this);

        Intent it = getIntent();

        if(it.getLongExtra("OcorrenciaId",0) != 0)
        {
            ocorrenciaTransito = OcorrenciaTransito.findById(OcorrenciaTransito.class, it.getLongExtra("OcorrenciaId", 0));
        }

        if(it.getLongExtra("EnvolvidoId",0)!= 0)
        {
            vitima = EnvolvidoTransito.findById(EnvolvidoTransito.class,it.getLongExtra("EnvolvidoId",0));
            CarregarVitima();
        }
        else
        vitima = new EnvolvidoTransito();
        try{
            ocorrenciaEnvolvido = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class,"ocorrencia_transito = ? and envolvido_transito = ?",ocorrenciaTransito.getId().toString(),vitima.getId().toString()).get(0);
        }
        catch (Exception e)
        {
            ocorrenciaEnvolvido = new OcorrenciaTransitoEnvolvido();
        }


        Button btnSave = (Button) findViewById(R.id.btn_Salvar_Vitima);

        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SalvarEnvolvido();

                Toast.makeText(v.getContext(), "Vítima salva com Sucesso!", Toast.LENGTH_LONG).show();

                Intent it = new Intent(v.getContext(),ManterPericiaTransito.class);
                it.putExtra("OcorrenciaId",ocorrenciaTransito.getId());
                it.putExtra("FragmentPicker","Vítima");
                startActivity(it);
            }
        });


        Button btnCancel = (Button) findViewById(R.id.btn_Cancelar_Vitima);

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(v.getContext(),ManterPericiaTransito.class);
                it.putExtra("OcorrenciaId",ocorrenciaTransito.getId());
                it.putExtra("FragmentPicker","Vítima");
                startActivity(it);
            }
        });

        cxbDesconhecido = (CheckBox) findViewById(R.id.cxb_Vitima_Desconhecida);
        cxbDesconhecido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               if(cxbDesconhecido.isChecked())
               {
                   edtNome.setEnabled(false);
                   edtNome.setText("Desconhecido(a)");
               }
                else
               {
                   edtNome.setEnabled(true);
                   edtNome.setText("");
               }
            }
        });




    }

    private void CarregarVitima()
    {
        if(vitima.getNome() != null)
        edtNome.setText(vitima.getNome());

        if(vitima.getDocumentoValor()!= null)
        edtNumDocumento.setText(vitima.getDocumentoValor());

        if(vitima.getNascimento()!= null)
        txvNascimento.setText(vitima.getNascimentoString());

        if(vitima.getDocumentoTipo()!= null)
        spnTipoDocumento.setSelection(BuscadorEnum.getIndex(spnTipoDocumento,vitima.getDocumentoTipo().getValor()));



    }

    private void AssociarLayout()
    {
        edtNome = (EditText) findViewById(R.id.edt_NomeVitima);
        edtNumDocumento = (EditText) findViewById(R.id.edt_NumDocVitima);

        spnTipoDocumento = (Spinner) findViewById(R.id.spn_TipoDocumentoVitima);
        spnGenero = (Spinner) findViewById(R.id.spn_VitimaGenero);

        txvNascimento = (TextView) findViewById(R.id.txv_DataNascimentoVitima_Valor);
        cxbFatal = (CheckBox) findViewById(R.id.cxb_VitimaFatal);
    }

    private void PovoarSpinner(Context ctx)
    {
        ArrayList<String> tipoDocumento= new ArrayList<>();

        for(DocumentoPessoa documentoPessoa : DocumentoPessoa.values())
        {
            tipoDocumento.add(documentoPessoa.getValor());
        }

        spnTipoDocumento.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,tipoDocumento));

        ArrayList<String> generos = new ArrayList<>();

        for(Genero g : Genero.values())
        {
            generos.add(g.getValor());
        }

        spnGenero.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,generos));
    }

    private void SalvarEnvolvido()
    {
        vitima.setNome(edtNome.getText().toString());
        vitima.setDocumentoValor(edtNumDocumento.getText().toString());
        //vitima.setFatal(cxbFatal.isChecked());
        vitima.setDataNascimentoString(txvNascimento.getText().toString());
        //vitima.setDocumentoTipo(BuscadorEnum.BuscarTipoDocumento(spnTipoDocumento.getSelectedItem().toString()));
        vitima.setDocumentoTipo(DocumentoPessoa.valueOf(DocumentoPessoa.class,spnTipoDocumento.getSelectedItem().toString()));

        //vitima.setGenero(BuscadorEnum.BuscarGenero(spnGenero.getSelectedItem().toString()));
        vitima.setGenero(Genero.valueOf(Genero.class,spnGenero.getSelectedItem().toString()));

        vitima.save();

        ocorrenciaEnvolvido.setOcorrenciaTransito(ocorrenciaTransito);
        ocorrenciaEnvolvido.setEnvolvidoTransito(vitima);

        ocorrenciaEnvolvido.save();

    }

    public void onBackPressed()
    {
//        Intent it = new Intent(this, MainActivity.class);
//        startActivity(it);
    }


}
