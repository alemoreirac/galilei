package com.example.pefoce.peritolocal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Enums.Transito.SetorDano;
import Enums.Transito.TercoDano;
import Enums.Transito.TipoDano;
import Model.Transito.Dano;
import Model.Transito.DanoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;

public class GerenciarDano extends AppCompatActivity
{


    ArrayAdapter<Dano> adapterDano = null;
    CheckBox cxbCompatibilidade = null;
    Spinner spnTipoDano = null;
    Spinner spnTercoDano = null;
    ListView lstvDanosDialog = null;
    Button btnAddDano = null;
    Button btnSalvarDano = null;
    Button btnLimparDanos = null;
    TextView txvDetalhe = null;

    TextView txvAnterior = null;
    TextView txvPosterior = null;


    public List<Dano> danosTotais = null;

    ArrayList<String> setores = new ArrayList<>();
    ArrayList<Dano> danos = new ArrayList<>();
    Veiculo veiculo = null;
    Long ocorrenciaId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_dano);

        txvDetalhe = (TextView) findViewById(R.id.txv_Subtitulo_Veiculo);

        Intent it = getIntent();

        ocorrenciaId = it.getLongExtra("OcorrenciaId", 0);

        danosTotais = new ArrayList<>();

        if (it.getLongExtra("VeiculoId", 0) != 0)
        {
            veiculo = Veiculo.findById(Veiculo.class, it.getLongExtra("VeiculoId", 0));
            txvDetalhe.setText(veiculo.getModelo());

            List<DanoVeiculo> danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", veiculo.getId().toString());
            for (DanoVeiculo dv : danosVeiculo)
            {
                danosTotais.add(dv.getDano());
            }

            for (Dano d : danosTotais)
            {
                if (!setores.contains(d.setor.toString()))
                    setores.add(d.setor.toString());

            }

            ColorirTxv_Angulos();

            txvAnterior = (TextView) findViewById(R.id.txv_Anterior_Veiculo);
            txvPosterior = (TextView) findViewById(R.id.txv_Posterior_Veiculo);

            txvAnterior.setText("A\nN\nT\nE\nR\nI\nO\nR");
            txvPosterior.setText("P\nO\nS\nT\nE\nR\nI\nO\nR");

            Button btnFechar = (Button) findViewById(R.id.btn_SalvarDano_Veiculo);
            btnFechar.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    List<DanoVeiculo> danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", veiculo.getId().toString());

                    for (DanoVeiculo danov : danosVeiculo)
                    {
                        danov.delete();
                    }

                    for (Dano dano : danosTotais)
                    {
                        dano.save();
                        DanoVeiculo dv = new DanoVeiculo(dano, veiculo);
                        dv.save();
                    }

                    Intent it = new Intent(GerenciarDano.this, ManterPericiaTransito.class);
                    it.putExtra("VeiculoId", veiculo.getId());
                    it.putExtra("OcorrenciaId", ocorrenciaId);
                    it.putExtra("DiretoParaVeiculo", true);
                    startActivity(it);
                }

                ;
            });
        }
    }

    private void ColorirTxv_Angulos()
    {

        for (String s : setores)
        {
            switch (s)
            {
                case "AAD":
                    ((TextView) findViewById(R.id.txv_AAD)).setTextColor(Color.GREEN);
                    break;
                case "LAD":
                    ((TextView) findViewById(R.id.txv_LAD)).setTextColor(Color.GREEN);
                    break;
                case "LMD":
                    ((TextView) findViewById(R.id.txv_LMD)).setTextColor(Color.GREEN);
                    break;
                case "LPD":
                    ((TextView) findViewById(R.id.txv_LPD)).setTextColor(Color.GREEN);
                    break;
                case "APD":
                    ((TextView) findViewById(R.id.txv_APD)).setTextColor(Color.GREEN);
                    break;
                case "PPD":
                    ((TextView) findViewById(R.id.txv_PPD)).setTextColor(Color.GREEN);
                    break;
                case "PPM":
                    ((TextView) findViewById(R.id.txv_PPM)).setTextColor(Color.GREEN);
                    break;
                case "PPE":
                    ((TextView) findViewById(R.id.txv_PPE)).setTextColor(Color.GREEN);
                    break;
                case "APE":
                    ((TextView) findViewById(R.id.txv_APE)).setTextColor(Color.GREEN);
                    break;
                case "LPE":
                    ((TextView) findViewById(R.id.txv_LPE)).setTextColor(Color.GREEN);
                    break;
                case "LME":
                    ((TextView) findViewById(R.id.txv_LME)).setTextColor(Color.GREEN);
                    break;
                case "LAE":
                    ((TextView) findViewById(R.id.txv_LAE)).setTextColor(Color.GREEN);
                    break;
                case "AAE":
                    ((TextView) findViewById(R.id.txv_AAE)).setTextColor(Color.GREEN);
                    break;
                case "PAE":
                    ((TextView) findViewById(R.id.txv_PAE)).setTextColor(Color.GREEN);
                    break;
                case "PAM":
                    ((TextView) findViewById(R.id.txv_PAM)).setTextColor(Color.GREEN);
                    break;
                case "PAD":
                    ((TextView) findViewById(R.id.txv_PAD)).setTextColor(Color.GREEN);
                    break;
            }
        }
    }

    public void Selecionar_Lado(final View viewLado)
    {

        final Dialog dialog = new Dialog(GerenciarDano.this);
        dialog.setTitle("Lateral Completa");
        dialog.setContentView(R.layout.dialog_dano_detalhe);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });

        final TextView txv = (TextView) viewLado;
        txv.setTextColor(Color.GREEN);

        switch (txv.getText().toString())
        {
            case "LADO ESQUERDO":
                ((TextView) findViewById(R.id.txv_LME)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_LAE)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_LPE)).setTextColor(Color.GREEN);
                break;
            case "LADO DIREITO":
                ((TextView) findViewById(R.id.txv_LMD)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_LAD)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_LPD)).setTextColor(Color.GREEN);
                break;
            case "ANTERIOR":
                ((TextView) findViewById(R.id.txv_PAD)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_PAM)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_PAE)).setTextColor(Color.GREEN);
                break;
            case "POSTERIOR":
                ((TextView) findViewById(R.id.txv_PPM)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_PPD)).setTextColor(Color.GREEN);
                ((TextView) findViewById(R.id.txv_PPE)).setTextColor(Color.GREEN);
                break;
        }

        dialog.setTitle("Dano " + (((TextView) viewLado).getText().toString()));
        dialog.show();

        Carregar_DanoDetalhe(dialog);

        for (Dano d : danosTotais)
        {
            switch (txv.getText().toString())
            {
                case "LADO ESQUERDO":
                    if (d.getSetor() == SetorDano.LME || d.getSetor() == SetorDano.LPE || d.getSetor() == SetorDano.LAE)
                        danos.add(d);
                    break;
                case "LADO DIREITO":
                    if (d.getSetor() == SetorDano.LMD || d.getSetor() == SetorDano.LPD || d.getSetor() == SetorDano.LAD)
                        danos.add(d);
                    break;
                case "ANTERIOR":
                    if (d.getSetor() == SetorDano.PAD || d.getSetor() == SetorDano.PAM || d.getSetor() == SetorDano.PAE)
                        danos.add(d);
                    break;
                case "POSTERIOR":
                    if (d.getSetor() == SetorDano.PPM || d.getSetor() == SetorDano.PPD || d.getSetor() == SetorDano.PPE)
                        danos.add(d);
                    break;
            }
        }

        adapterDano = new ArrayAdapter<Dano>(GerenciarDano.this, android.R.layout.simple_spinner_dropdown_item, danos);


        btnAddDano.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String texto = txv.getText().toString();
                texto = texto.replace("\n","");

                switch (texto)
                {

                    case "LADO ESQUERDO":
                        Dano danoEsquerdo = new Dano();
                        danoEsquerdo.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoEsquerdo.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoEsquerdo.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoEsquerdo.setSetor(BuscadorEnum.BuscarSetorDano("LME"));
                        adapterDano.add(danoEsquerdo);

                        danoEsquerdo = new Dano();
                        danoEsquerdo.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoEsquerdo.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoEsquerdo.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoEsquerdo.setSetor(BuscadorEnum.BuscarSetorDano("LAE"));

                        adapterDano.add(danoEsquerdo);

                        danoEsquerdo = new Dano();
                        danoEsquerdo.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoEsquerdo.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoEsquerdo.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoEsquerdo.setSetor(BuscadorEnum.BuscarSetorDano("LPE"));
                        adapterDano.add(danoEsquerdo);
                        break;
                    case "LADO DIREITO":
                        Dano danoDireito = new Dano();
                        danoDireito.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoDireito.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoDireito.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoDireito.setSetor(BuscadorEnum.BuscarSetorDano("LMD"));
                        adapterDano.add(danoDireito);

                        danoDireito = new Dano();
                        danoDireito.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoDireito.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoDireito.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoDireito.setSetor(BuscadorEnum.BuscarSetorDano("LAD"));
                        adapterDano.add(danoDireito);

                        danoDireito = new Dano();
                        danoDireito.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoDireito.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoDireito.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoDireito.setSetor(BuscadorEnum.BuscarSetorDano("LPD"));
                        adapterDano.add(danoDireito);
                        break;

                    case "ANTERIOR":
                        Dano danoAnterior = new Dano();
                        danoAnterior.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoAnterior.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoAnterior.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoAnterior.setSetor(BuscadorEnum.BuscarSetorDano("PAD"));
                        adapterDano.add(danoAnterior);

                        danoAnterior = new Dano();
                        danoAnterior.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoAnterior.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoAnterior.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoAnterior.setSetor(BuscadorEnum.BuscarSetorDano("PAM"));
                        adapterDano.add(danoAnterior);

                        danoAnterior = new Dano();
                        danoAnterior.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoAnterior.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoAnterior.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoAnterior.setSetor(BuscadorEnum.BuscarSetorDano("PAE"));
                        adapterDano.add(danoAnterior);
                        break;

                    case "POSTERIOR":
                        Dano danoPosterior = new Dano();
                        danoPosterior.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoPosterior.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoPosterior.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoPosterior.setSetor(BuscadorEnum.BuscarSetorDano("PPD"));
                        adapterDano.add(danoPosterior);

                        danoPosterior = new Dano();
                        danoPosterior.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoPosterior.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoPosterior.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoPosterior.setSetor(BuscadorEnum.BuscarSetorDano("PPM"));
                        adapterDano.add(danoPosterior);

                        danoPosterior = new Dano();
                        danoPosterior.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                        danoPosterior.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                        danoPosterior.setCompatibilidade(cxbCompatibilidade.isChecked());
                        danoPosterior.setSetor(BuscadorEnum.BuscarSetorDano("PPE"));
                        adapterDano.add(danoPosterior);
                        break;
                }
            }
        });


        btnLimparDanos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                for (int i = 0; i < adapterDano.getCount(); i++)

                    danosTotais.remove(adapterDano.getItem(i));

                adapterDano.clear();
            }
        });

        btnSalvarDano.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for (int i = 0; i < adapterDano.getCount(); i++)
                {
                    if (!danosTotais.contains(adapterDano.getItem(i)))
                        danosTotais.add(adapterDano.getItem(i));
                }

                if (adapterDano.getCount() == 0)
                    ((TextView) viewLado).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));

                //Removendo os \n para ler o TextView de Anterior e Posterior, que estão na vertical.
                String texto = txv.getText().toString();
                texto = texto.replace("\n","");

                switch (texto)
                {
                    case "LADO ESQUERDO":
                        ((TextView) findViewById(R.id.txv_LME)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_LAE)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_LPE)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        for (Dano dano : danosTotais)
                        {
                            if (dano.getSetor().equals(SetorDano.LPE))
                                ((TextView) findViewById(R.id.txv_LPE)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.LAE))
                                ((TextView) findViewById(R.id.txv_LAE)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.LME))
                                ((TextView) findViewById(R.id.txv_LME)).setTextColor(Color.GREEN);
                        }
                        break;
                    case "LADO DIREITO":
                        ((TextView) findViewById(R.id.txv_LMD)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_LAD)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_LPD)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        for (Dano dano : danosTotais)
                        {
                            if (dano.getSetor().equals(SetorDano.LMD))
                                ((TextView) findViewById(R.id.txv_LMD)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.LAD))
                                ((TextView) findViewById(R.id.txv_LAD)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.LPD))
                                ((TextView) findViewById(R.id.txv_LPD)).setTextColor(Color.GREEN);
                        }
                        break;
                    case "ANTERIOR":
                        ((TextView) findViewById(R.id.txv_PAD)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_PAM)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_PAE)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        for (Dano dano : danosTotais)
                        {
                            if (dano.getSetor().equals(SetorDano.PAD))
                                ((TextView) findViewById(R.id.txv_PAD)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.PAM))
                                ((TextView) findViewById(R.id.txv_PAM)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.PAE))
                                ((TextView) findViewById(R.id.txv_PAE)).setTextColor(Color.GREEN);
                        }
                        break;
                    case "POSTERIOR":
                        ((TextView) findViewById(R.id.txv_PPM)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_PPD)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        ((TextView) findViewById(R.id.txv_PPE)).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));
                        for (Dano dano : danosTotais)
                        {
                            if (dano.getSetor().equals(SetorDano.PPM))
                                ((TextView) findViewById(R.id.txv_PPM)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.PPD))
                                ((TextView) findViewById(R.id.txv_PPD)).setTextColor(Color.GREEN);
                            if (dano.getSetor().equals(SetorDano.PPE))
                                ((TextView) findViewById(R.id.txv_PPE)).setTextColor(Color.GREEN);
                        }
                        break;
                }


                adapterDano.clear();

                dialog.dismiss();
            }

            ;


        });


        adapterDano.notifyDataSetChanged();
        lstvDanosDialog.setAdapter(adapterDano);
    }


    public void Selecionar_Angulo(final View viewAngulo)
    {
        TextView txv = (TextView) viewAngulo;


        final Dialog dialog = new Dialog(GerenciarDano.this);
        dialog.setContentView(R.layout.dialog_dano_detalhe);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener()
        {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                // Prevent dialog close on back press button
                return keyCode == KeyEvent.KEYCODE_BACK;
            }
        });


        if (txv.getTextColors().equals(Color.GREEN))
            txv.setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));

        else
            txv.setTextColor(Color.GREEN);


        dialog.setTitle("Dano " + (((TextView) viewAngulo).getText().toString()));
        dialog.show();


        Carregar_DanoDetalhe(dialog);


        for (Dano d : danosTotais)
        {
            if (d.getSetor() == (BuscadorEnum.BuscarSetorDano(((TextView) viewAngulo).getText().toString())))
            {
                danos.add(d);
            }

        }

        adapterDano = new ArrayAdapter<Dano>(GerenciarDano.this, android.R.layout.simple_spinner_dropdown_item, danos);

        btnSalvarDano.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                for (int i = 0; i < adapterDano.getCount(); i++)
                {
                    if (!danosTotais.contains(adapterDano.getItem(i)))
                        danosTotais.add(adapterDano.getItem(i));
                }

                if (adapterDano.getCount() == 0)
                    ((TextView) viewAngulo).setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));

                adapterDano.clear();

                dialog.dismiss();
            }

            ;

        });

        btnAddDano.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Dano d = new Dano();

                d.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                d.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                d.setCompatibilidade(cxbCompatibilidade.isChecked());
                d.setSetor(BuscadorEnum.BuscarSetorDanoSigla(((TextView) viewAngulo).getText().toString()));
                adapterDano.add(d);
            }

            ;
        });

        btnLimparDanos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                for (int i = 0; i < adapterDano.getCount(); i++)

                    danosTotais.remove(adapterDano.getItem(i));

                adapterDano.clear();
            }

            ;

        });

        lstvDanosDialog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v, final int position, long id)
            {
                new AlertDialog.Builder(GerenciarDano.this)
                        .setTitle("Deletar Dano")
                        .setMessage("Você deseja deletar este Dano?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                danosTotais.remove(adapterDano.getItem(position));
                                adapterDano.remove(adapterDano.getItem(position));
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                dialog.dismiss();
                            }
                        })
                        .show();
                return true;
            }
        });

        adapterDano.notifyDataSetChanged();
        lstvDanosDialog.setAdapter(adapterDano);

    }


    private void Carregar_DanoDetalhe(Dialog dialog)
    {
        lstvDanosDialog = (ListView) dialog.findViewById(R.id.lstv_dialog_ListDanos);
        btnAddDano = (Button) dialog.findViewById(R.id.btn_dialog_AddDano);
        btnSalvarDano = (Button) dialog.findViewById(R.id.btn_dialog_SalvarDano_Detalhe);
        btnLimparDanos = (Button) dialog.findViewById(R.id.btn_dialog_LimparDano);
        spnTipoDano = (Spinner) dialog.findViewById(R.id.spn_dialog_TipoDano);
        spnTercoDano = (Spinner) dialog.findViewById(R.id.spn_dialog_TercoDano);
        cxbCompatibilidade = (CheckBox) dialog.findViewById(R.id.cxb_dialog_CompatibilidadeDano);

        ArrayList<String> tipoDano = new ArrayList<String>();

        for (TipoDano td : TipoDano.values())
        {
            tipoDano.add(td.getValor());
        }

        spnTipoDano.setAdapter(new ArrayAdapter<String>(GerenciarDano.this, android.R.layout.simple_spinner_dropdown_item, tipoDano));

        ArrayList<String> tercoDano = new ArrayList<String>();

        for (TercoDano td : TercoDano.values())
        {
            tercoDano.add(td.getValor());
        }

        spnTercoDano.setAdapter(new ArrayAdapter<String>(GerenciarDano.this, android.R.layout.simple_spinner_dropdown_item, tercoDano));
    }

    public void onBackPressed()
    {
//        Intent it = new Intent(this, MainActivity.class);
//        startActivity(it);
    }
}
