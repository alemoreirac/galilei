package com.example.pefoce.peritolocal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Enums.CondicaoPista;
import Enums.Iluminacao;
import Enums.Pavimentacao;
import Enums.Semaforo;
import Enums.SinalizacaoPare;
import Enums.TipoVia;
import Enums.Topografia;
import Model.Bairro;
import Model.Cidade;
import Model.Endereco;
import Model.EnderecoTransito;
import Model.OcorrenciaEndereco;
import Model.OcorrenciaTransito;
import Util.BuscadorEnum;

public class ManterEnderecoActivity extends Activity {

    Spinner spnTipoVia = null;
    Spinner spnSemaforo = null;
    Spinner spnTopografia = null;
    Spinner spnCondicaoVia = null;
    Spinner spnPavimentacao = null;
    Spinner spnIluminacaoVia = null;
    Spinner spnSinalizacaoPare = null;
    NumberPicker nmbFaixas = null;

    EditText edtEndereco = null;
    AutoCompleteTextView aucCidade = null;
    AutoCompleteTextView aucBairro = null;


    CheckBox cxbPreferencial = null;
    CheckBox cxbComposta = null;
    CheckBox cxbCurva = null;
    CheckBox cxbHumidade = null;


    OcorrenciaTransito ocorrenciaTransito = null;
    EnderecoTransito enderecoFragment = null;
    OcorrenciaEndereco ocorrenciaEndereco = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_endereco);


        AssociarLayout();
        PovoarSpinner(this);


        Intent it = getIntent();


         if(it.getLongExtra("OcorrenciaId",0) != 0)
         {
             ocorrenciaTransito = OcorrenciaTransito.findById(OcorrenciaTransito.class, it.getLongExtra("OcorrenciaId", 0));
         }

        if(it.getLongExtra("EnderecoId",0) != 0)
        {
            enderecoFragment = EnderecoTransito.findById(EnderecoTransito.class, it.getLongExtra("EnderecoId", 0));
            CarregarEndereco();
        }
        else
        {
            enderecoFragment = new EnderecoTransito();
        }

        try{
            ocorrenciaEndereco = OcorrenciaEndereco.find(OcorrenciaEndereco.class,"ocorrencia_transito = ? and endereco_transito = ?",ocorrenciaTransito.getId().toString(),enderecoFragment.getId().toString()).get(0);
        }catch (Exception e)
        {
            ocorrenciaEndereco = new OcorrenciaEndereco();
        }

        cxbComposta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                nmbFaixas.setEnabled(cxbComposta.isChecked());
            }
        });

        Button btnSave = (Button) findViewById(R.id.btn_Salvar_Endereco);

        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SalvarEndereco();

                Toast.makeText(v.getContext(), "Endereço salvo com Sucesso!", Toast.LENGTH_LONG).show();

                Intent it = new Intent(v.getContext(),ManterPericia.class);
                it.putExtra("OcorrenciaId",ocorrenciaTransito.getId());
                it.putExtra("FragmentPicker","Endereço");
                startActivity(it);
            }
        });



        Button btnCancel = (Button) findViewById(R.id.btn_Cancelar_Endereco);

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
              //  Intent it = new Intent(v.getContext(),MainActivity.class);
                Intent it = new Intent(v.getContext(),ManterPericia.class);
                //it.putExtra("PeritoId",ocorrenciaTransito.getPerito().getId());
                it.putExtra("OcorrenciaId",ocorrenciaTransito.getId());
                it.putExtra("FragmentPicker","Endereço");
                startActivity(it);
            }
        });

    }

    private void SalvarEndereco()
    {
        enderecoFragment.setCondicao(BuscadorEnum.BuscarCondicaoPista(spnCondicaoVia.getSelectedItem().toString()));
        enderecoFragment.setPavimentacao(BuscadorEnum.BuscarPavimentacao(spnPavimentacao.getSelectedItem().toString()));
        enderecoFragment.setTipoVia(BuscadorEnum.BuscarTipoVia(spnTipoVia.getSelectedItem().toString()));
        enderecoFragment.setSemaforo(BuscadorEnum.BuscarSemaforizacao(spnSemaforo.getSelectedItem().toString()));
        enderecoFragment.setTopografia(BuscadorEnum.BuscarTopografia(spnTopografia.getSelectedItem().toString()));
        enderecoFragment.setSinalizacaoPare(BuscadorEnum.BuscarSinalizacaoPare(spnSinalizacaoPare.getSelectedItem().toString()));
        enderecoFragment.setIluminacao(BuscadorEnum.BuscarIluminacao(spnIluminacaoVia.getSelectedItem().toString()));

        enderecoFragment.setCurva(cxbCurva.isChecked());

        enderecoFragment.setComposta(cxbComposta.isChecked());

        if(cxbComposta.isChecked())
            //enderecoFragment.setNumFaixas(Integer.getInteger(spnFaixas.getSelectedItem().toString()));
        enderecoFragment.setNumFaixas(nmbFaixas.getValue());
        else
        enderecoFragment.setNumFaixas(0);

        enderecoFragment.setMolhada(cxbHumidade.isChecked());

        enderecoFragment.setPreferencial(cxbPreferencial.isChecked());



        Endereco endereco = new Endereco();
        endereco.setEndereco(edtEndereco.getText().toString());
        endereco.setCidade(aucCidade.getText().toString());
        endereco.setBairro(aucBairro.getText().toString());

        endereco.save();

        enderecoFragment.setEndereco(endereco);

        enderecoFragment.save();

        ocorrenciaEndereco.setOcorrenciaTransito(ocorrenciaTransito);
        ocorrenciaEndereco.setEnderecoTransito(enderecoFragment);

        ocorrenciaEndereco.save();

    }

    private void CarregarEndereco()
    {
        EnderecoTransito enderecoTransito = enderecoFragment;

        if(enderecoTransito.getEndereco() != null)
        {
            aucCidade.setText(enderecoTransito.getEndereco().getCidade());
            aucBairro.setText(enderecoTransito.getEndereco().getBairro());
            edtEndereco.setText(enderecoTransito.getEndereco().getDescricao());
        }

        //if(!enderecoTransito.isPreferencial()
        cxbPreferencial.setChecked(enderecoTransito.isPreferencial());

      //  if(!enderecoTransito.isCurva().equals(null))
        cxbCurva.setChecked(enderecoTransito.isCurva());

      //  if(!enderecoTransito.isMolhada().equals(null))
        cxbHumidade.setChecked(enderecoTransito.isMolhada());

     //   if(!enderecoTransito.isComposta().equals(null))
        cxbComposta.setChecked(enderecoTransito.isComposta());

       if(enderecoTransito.isComposta())
           nmbFaixas.setEnabled(true);
        else
           nmbFaixas.setEnabled(false);

        if(enderecoTransito.getTipoVia()!= null)
        spnTipoVia.setSelection(BuscadorEnum.getIndex(spnTipoVia,enderecoTransito.getTipoVia().getValor()));

        if(enderecoTransito.getIluminacao() != null)
        spnIluminacaoVia.setSelection(BuscadorEnum.getIndex(spnIluminacaoVia,enderecoTransito.getIluminacao().getValor()));

        if(enderecoTransito.getPavimentacao() != null)
        spnPavimentacao.setSelection(BuscadorEnum.getIndex(spnPavimentacao,enderecoTransito.getPavimentacao().getValor()));

        if(enderecoTransito.getCondicao() != null)
        spnCondicaoVia.setSelection(BuscadorEnum.getIndex(spnCondicaoVia,enderecoTransito.getCondicao().getValor()));

        if(enderecoTransito.getSemaforo() != null)
        spnSemaforo.setSelection(BuscadorEnum.getIndex(spnSemaforo,enderecoTransito.getSemaforo().getValor()));

        if(enderecoTransito.getTopografia()!=null)
        spnTopografia.setSelection(BuscadorEnum.getIndex(spnTopografia,enderecoTransito.getTopografia().getValor()));

        if(enderecoTransito.getSinalizacaoPare() !=null)
        spnSinalizacaoPare.setSelection(BuscadorEnum.getIndex(spnSinalizacaoPare,enderecoTransito.getSinalizacaoPare().getValor()));

        nmbFaixas.setValue(enderecoTransito.getNumFaixas());



    }

    private void AssociarLayout()
    {
        nmbFaixas = (NumberPicker) findViewById(R.id.nmb_Faixas);
        spnTipoVia = (Spinner) findViewById(R.id.spn_TipoVia);
        spnSemaforo = (Spinner) findViewById(R.id.spn_Semaforo);
        spnTopografia = (Spinner) findViewById(R.id.spn_Topografia);
        spnCondicaoVia = (Spinner) findViewById(R.id.spn_CondicaoVia);
        spnIluminacaoVia = (Spinner) findViewById(R.id.spn_Iluminacao);
        spnSinalizacaoPare = (Spinner) findViewById(R.id.spn_SinalPare);
        spnPavimentacao = (Spinner) findViewById(R.id.spn_Pavimentacao);


        cxbCurva = (CheckBox)findViewById(R.id.cxb_Tracado);
        cxbHumidade = (CheckBox)findViewById(R.id.cxb_Humidade);
        cxbComposta = (CheckBox)findViewById(R.id.cxb_ViaComposta);
        cxbPreferencial = (CheckBox)findViewById(R.id.cxb_Preferencial);

        edtEndereco = (EditText) findViewById(R.id.edt_Endereco);

        aucCidade = (AutoCompleteTextView) findViewById(R.id.auc_Cidade);
        aucBairro = (AutoCompleteTextView)findViewById(R.id.auc_Bairro);

        nmbFaixas = (NumberPicker)findViewById(R.id.nmb_Faixas);

        nmbFaixas.setMinValue(1);
        nmbFaixas.setMaxValue(4);
        nmbFaixas.setEnabled(false);
    }

    private void PovoarSpinner(Context ctx)
    {
        aucCidade.setAdapter(getCidades(ctx));
        aucBairro.setAdapter(getBairros(ctx));

        ArrayList<String> tipoViaAdapter = new ArrayList<String>();

        for(TipoVia tv : TipoVia.values())
        {
            tipoViaAdapter.add(tv.getValor());
        }
        spnTipoVia.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,tipoViaAdapter));

        ArrayList<String> semaforoAdapter = new ArrayList<String>();

        for(Semaforo sem : Semaforo.values())
        {
            semaforoAdapter.add(sem.getValor());
        }
        spnSemaforo.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,semaforoAdapter));

        ArrayList<String> topografiaAdapter = new ArrayList<String>();

        for(Topografia tp : Topografia.values())
        {
            topografiaAdapter.add(tp.getValor());
        }
        spnTopografia.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,topografiaAdapter));

        ArrayList<String>  condicaoViaAdapter = new ArrayList<String>();

        for(CondicaoPista cpis : CondicaoPista.values())
        {
            condicaoViaAdapter.add(cpis.getValor());
        }
        spnCondicaoVia.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,condicaoViaAdapter));

        ArrayList<String>  pavimentacaoAdapter = new ArrayList<String>();

        for(Pavimentacao pav : Pavimentacao.values())
        {
            pavimentacaoAdapter.add(pav.getValor());
        }
        spnPavimentacao.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,pavimentacaoAdapter));

        ArrayList<String>  iluminacaoAdapter = new ArrayList<String>();

        for(Iluminacao ilu : Iluminacao.values())
        {
            iluminacaoAdapter.add(ilu.getValor());
        }
        spnIluminacaoVia.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,iluminacaoAdapter));

        ArrayList<String>  sinalPareAdapter = new ArrayList<String>();

        for(SinalizacaoPare spa : SinalizacaoPare.values())
        {
            sinalPareAdapter.add(spa.getValor());
        }
        spnSinalizacaoPare.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,sinalPareAdapter));



    }


    private ArrayAdapter<String> getCidades(Context context) {
        ArrayList<Cidade> cidades = (ArrayList<Cidade>) Cidade.listAll(Cidade.class);

        String[] cidadesString = new String[cidades.size()];

        for (int i = 0; i<cidades.size();i++)
        {
            cidadesString[i] = cidades.get(i).getDescricao();
        }

        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, cidadesString);
    }



    private ArrayAdapter<String> getBairros(Context context) {
        ArrayList<Bairro> bairros = (ArrayList<Bairro>) Bairro.listAll(Bairro.class);

        String[] bairroString = new String[bairros.size()];

        for (int i = 0; i<bairros.size();i++)
        {
            bairroString[i] = bairros.get(i).getDescricao();
        }

        return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, bairroString);
    }
}
