package com.example.pefoce.peritolocal;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import Enums.DocumentoPessoa;
import Model.EnvolvidoTransito;
import Model.OcorrenciaTransito;
import Model.OcorrenciaEnvolvido;
import Util.BuscadorEnum;
import Util.TempoUtil;

public class ManterVitimaActivity extends AppCompatActivity {

    EditText edtNome= null;
    CheckBox cxbFatal = null;
    TextView txvNascimento = null;
    Spinner spnTipoDocumento = null;
    EditText edtNumDocumento = null;

    EnvolvidoTransito  vitima = null;
    OcorrenciaEnvolvido ocorrenciaEnvolvido = null;
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
            ocorrenciaEnvolvido = OcorrenciaEnvolvido.find(OcorrenciaEnvolvido.class,"ocorrencia_transito = ? and envolvido_transito = ?",ocorrenciaTransito.getId().toString(),vitima.getId().toString()).get(0);
        }
        catch (Exception e)
        {
            ocorrenciaEnvolvido = new OcorrenciaEnvolvido();
        }


        Button btnSave = (Button) findViewById(R.id.btn_Salvar_Vitima);

        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SalvarEnvolvido();

                Toast.makeText(v.getContext(), "Vítima salva com Sucesso!", Toast.LENGTH_LONG).show();

                Intent it = new Intent(v.getContext(),ManterPericia.class);
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
                Intent it = new Intent(v.getContext(),ManterPericia.class);
                it.putExtra("OcorrenciaId",ocorrenciaTransito.getId());
                it.putExtra("FragmentPicker","Vítima");
                startActivity(it);
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

        cxbFatal.setChecked(vitima.isFatal());

    }

    private void AssociarLayout()
    {
        edtNome = (EditText) findViewById(R.id.edt_NomeVitima);
        edtNumDocumento = (EditText) findViewById(R.id.edt_NumDocVitima);
        spnTipoDocumento = (Spinner) findViewById(R.id.spn_TipoDocumentoVitima);
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
    }

    private void SalvarEnvolvido()
    {
        vitima.setNome(edtNome.getText().toString());
        vitima.setDocumentoValor(edtNumDocumento.getText().toString());
        vitima.setFatal(cxbFatal.isChecked());
        vitima.setDataNascimentoString(txvNascimento.getText().toString());
        vitima.setDocumentoTipo(BuscadorEnum.BuscarTipoDocumento(spnTipoDocumento.getSelectedItem().toString()));

        vitima.save();

        ocorrenciaEnvolvido.setOcorrenciaTransito(ocorrenciaTransito);
        ocorrenciaEnvolvido.setEnvolvidoTransito(vitima);

        ocorrenciaEnvolvido.save();

    }

    public void SetNascimento(View v)
    {
        TempoUtil.setDate(txvNascimento,this);
    }
}
