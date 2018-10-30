package com.example.pefoce.peritolocal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Enums.Transito.TercoDano;
import Enums.Transito.TipoDano;
import Model.Transito.Dano;
import Model.Transito.DanoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;
import Util.ComparatorUtil;

public class GerenciarDano extends AppCompatActivity
{
    RelativeLayout rltvBase = null;
    Button btnVOltar = null;
    TextView txvDetalhe = null;

    TextView txvAnterior = null;
    TextView txvPosterior = null;
    TextView txvLadoDireito = null;
    TextView txvLadoEsquerdo = null;


    CheckBox cxbCompatibilidade = null;

    CheckBox cxbDanoContuso = null;
    CheckBox cxbDanoCortante = null;
    CheckBox cxbDanoFriccao = null;
    CheckBox cxbDanoPerfurante = null;

    CheckBox cxbTercoSuperior = null;
    CheckBox cxbTercoMedio = null;
    CheckBox cxbTercoInferior = null;

    boolean isTercoSuperior, isTercoInferior, isTercoMedio, isDanoContuso, isDanoCortante, isDanoFriccao, isDanoPerfurante, isCompativel;

    List<DanoVeiculo> danosVeiculo = null;

    ArrayList<Dano> danosIniciais = null;

    public List<Dano> danosTotais = null;

    ArrayList<String> setores = new ArrayList<>();
    Veiculo veiculo = null;
    Long ocorrenciaId = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_dano);
        AssociarLayout();

        cxbCompatibilidade.setSelected(true);

        Intent it = getIntent();

        ocorrenciaId = it.getLongExtra("OcorrenciaId", 0);

        danosIniciais = new ArrayList<>();
        danosTotais = new ArrayList<>();

        if (it.getLongExtra("VeiculoId", 0) != 0)
        {
            veiculo = Veiculo.findById(Veiculo.class, it.getLongExtra("VeiculoId", 0));
            txvDetalhe.setText(veiculo.getModelo());

            cxbCompatibilidade.performClick();

            danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", veiculo.getId().toString());

            for (DanoVeiculo dv : danosVeiculo)
            {
                danosIniciais.add(dv.getDano());
            }

            danosTotais.addAll(danosIniciais);

            for (Dano d : danosTotais)
            {
                if (!setores.contains(d.setor.toString()))
                    setores.add(d.setor.toString());

            }

            ColorirTxv_Angulos();


            txvAnterior.setText("A\nN\nT\nE\nR\nI\nO\nR");
            txvPosterior.setText("P\nO\nS\nT\nE\nR\nI\nO\nR");

            btnVOltar.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    for (Dano d : danosIniciais)
                    {
                        if (!ComparatorUtil.containsId(d, danosTotais))
                        {
                            DanoVeiculo dv = DanoVeiculo.find(DanoVeiculo.class, "dano = ?", d.getId().toString()).get(0);
                            dv.getDano().delete();
                            dv.delete();
                        }
                    }

                    for (Dano d : danosTotais)
                    {
                        if (d.getId() == null)
                        {
                            DanoVeiculo dv = new DanoVeiculo(d, veiculo);
                            dv.getDano().save();
                            dv.save();
                        }
                    }

                    Intent it = new Intent(GerenciarDano.this, ManterPericiaTransito.class);
                    it.putExtra("VeiculoId", veiculo.getId());
                    it.putExtra("OcorrenciaId", ocorrenciaId);
                    it.putExtra("DiretoParaVeiculo", true);
                    startActivity(it);

                }
            });
        }
    }

    public void NovoDano(final View viewLado)
    {
        if (!isDanoPerfurante && !isDanoFriccao && !isDanoCortante && !isDanoContuso)

        {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if(v!=null)
            v.vibrate(1000);


            cxbDanoCortante.setTextColor(Color.RED);
            cxbDanoPerfurante.setTextColor(Color.RED);
            cxbDanoContuso.setTextColor(Color.RED);
            cxbDanoFriccao.setTextColor(Color.RED);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    cxbDanoCortante.setTextColor(Color.BLACK);
                    cxbDanoPerfurante.setTextColor(Color.BLACK);
                    cxbDanoContuso.setTextColor(Color.BLACK);
                    cxbDanoFriccao.setTextColor(Color.BLACK);
                }
            }, 2000);


            Toast.makeText(this, "Selecione um tipo de dano.", Toast.LENGTH_LONG).show();
            return;
        }

        if(!isTercoMedio && !isTercoInferior && !isTercoSuperior)
        {
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            if(v!=null)
                v.vibrate(1000);

            cxbTercoInferior.setTextColor(Color.RED);
            cxbTercoMedio.setTextColor(Color.RED);
            cxbTercoSuperior.setTextColor(Color.RED);

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                       cxbTercoInferior.setTextColor(Color.BLACK);
                       cxbTercoMedio.setTextColor(Color.BLACK);
                       cxbTercoSuperior.setTextColor(Color.BLACK);
                }
            }, 2000);

            Toast.makeText(this, "Selecione um terço para o dano.", Toast.LENGTH_LONG).show();
            return;
        }
        final TextView txv = (TextView) viewLado;
        txv.setTextColor(Color.RED);

        String anguloValor = txv.getText().toString();
        anguloValor = anguloValor.replace("\n", "");

        ArrayList<Dano> danosNovos = new ArrayList<>();

        if (isTercoInferior)
        {
            if (isDanoContuso)
                danosNovos.add(new Dano(TipoDano.CONTUSO, TercoDano.INFERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoCortante)
                danosNovos.add(new Dano(TipoDano.CORTANTE, TercoDano.INFERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoFriccao)
                danosNovos.add(new Dano(TipoDano.FRICCAO, TercoDano.INFERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoPerfurante)
                danosNovos.add(new Dano(TipoDano.PERFURANTE, TercoDano.INFERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));
        }

        if (isTercoMedio)
        {
            if (isDanoContuso)
                danosNovos.add(new Dano(TipoDano.CONTUSO, TercoDano.MEDIO, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoCortante)
                danosNovos.add(new Dano(TipoDano.CORTANTE, TercoDano.MEDIO, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoFriccao)
                danosNovos.add(new Dano(TipoDano.FRICCAO, TercoDano.MEDIO, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoPerfurante)
                danosNovos.add(new Dano(TipoDano.PERFURANTE, TercoDano.MEDIO, BuscadorEnum.BuscarSetorDano(anguloValor), true));
        }

        if (isTercoSuperior)
        {
            if (isDanoContuso)
                danosNovos.add(new Dano(TipoDano.CONTUSO, TercoDano.SUPERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoCortante)
                danosNovos.add(new Dano(TipoDano.CORTANTE, TercoDano.SUPERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoFriccao)
                danosNovos.add(new Dano(TipoDano.FRICCAO, TercoDano.SUPERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));

            if (isDanoPerfurante)
                danosNovos.add(new Dano(TipoDano.PERFURANTE, TercoDano.SUPERIOR, BuscadorEnum.BuscarSetorDano(anguloValor), true));
        }

        for (Dano dNovo : danosNovos)
        {
            if (!ComparatorUtil.containsDano(dNovo, danosTotais))
                danosTotais.add(dNovo);
        }

        if (danosNovos.size() == 0)
            Toast.makeText(this, "Selecione uma opção válida!", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "Danos salvos!", Toast.LENGTH_SHORT).show();
    }


    private void RemoverTodos(final View viewLado)
    {
        final TextView txv = (TextView) viewLado;
        txv.setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));

        String anguloValor = txv.getText().toString();
        anguloValor = anguloValor.replace("\n", "");

        List<Dano> danosRemovidos = new ArrayList<>();

        for (Dano d : danosTotais)
        {
            if (d.setor.toString().equals(anguloValor))
                danosRemovidos.add(d);
        }

        danosTotais.removeAll(danosRemovidos);
    }

    private void AssociarLayout()
    {
        rltvBase = (RelativeLayout) findViewById(R.id.rltv_Gerenciar_Dano);

        btnVOltar = (Button) findViewById(R.id.btn_SalvarDano_Veiculo);
        txvDetalhe = (TextView) findViewById(R.id.txv_Subtitulo_Veiculo);
        txvAnterior = (TextView) findViewById(R.id.txv_Anterior_Veiculo);
        txvPosterior = (TextView) findViewById(R.id.txv_Posterior_Veiculo);
        txvLadoDireito = (TextView) findViewById(R.id.txv_Lado_Direito_Veiculo);
        txvLadoEsquerdo = (TextView) findViewById(R.id.txv_Lado_Esquerdo_Veiculo);

        cxbCompatibilidade = (CheckBox) findViewById(R.id.cxb_Compatibilidade_Dano_Activity);
        cxbDanoContuso = (CheckBox) findViewById(R.id.cxb_Danos_Contusos);
        cxbDanoCortante = (CheckBox) findViewById(R.id.cxb_Danos_Cortantes);
        cxbDanoFriccao = (CheckBox) findViewById(R.id.cxb_Danos_Friccao);
        cxbDanoPerfurante = (CheckBox) findViewById(R.id.cxb_Danos_Perfurante);

        cxbTercoSuperior = (CheckBox) findViewById(R.id.cxb_Terco_Superior);
        cxbTercoMedio = (CheckBox) findViewById(R.id.cxb_Terco_Medio);
        cxbTercoInferior = (CheckBox) findViewById(R.id.cxb_Terco_Inferior);

        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                NovoDano(v);
            }
        };

        View.OnLongClickListener longListener = new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder builder;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    builder = new AlertDialog.Builder(GerenciarDano.this, android.R.style.Theme_Material_Dialog_Alert);

                else
                    builder = new AlertDialog.Builder(GerenciarDano.this);

                builder.setTitle("Deletar Danos")
                        .setMessage("Remover os danos do ângulo?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                RemoverTodos(v);
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
        };


        View.OnClickListener listenerLado = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView txv = (TextView) v;

                if (isCompativel &&
                        (isDanoPerfurante || isDanoFriccao || isDanoCortante || isDanoContuso)
                        &&(isTercoMedio || isTercoInferior || isTercoSuperior))

                    txv.setTextColor(Color.RED);

                switch (txv.getText().toString())
                {
                    case "LADO DIREITO":
                        NovoDano(findViewById(R.id.txv_LAD));
                        NovoDano(findViewById(R.id.txv_LMD));
                        NovoDano(findViewById(R.id.txv_LPD));
                        break;
                    case "LADO ESQUERDO":
                        NovoDano(findViewById(R.id.txv_LAE));
                        NovoDano(findViewById(R.id.txv_LME));
                        NovoDano(findViewById(R.id.txv_LPE));
                        break;
                    case "P\nO\nS\nT\nE\nR\nI\nO\nR":
                        NovoDano(findViewById(R.id.txv_PPD));
                        NovoDano(findViewById(R.id.txv_PPM));
                        NovoDano(findViewById(R.id.txv_PPE));
                        break;
                    case "A\nN\nT\nE\nR\nI\nO\nR":
                        NovoDano(findViewById(R.id.txv_PAD));
                        NovoDano(findViewById(R.id.txv_PAM));
                        NovoDano(findViewById(R.id.txv_PAE));
                        break;
                }
            }
        };

        View.OnLongClickListener longListenerLado = new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View v)
            {
                AlertDialog.Builder builder;

                builder = new AlertDialog.Builder(GerenciarDano.this);

                builder.setTitle("Deletar Danos")
                        .setMessage("Remover os danos do ângulo?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                TextView txv = (TextView) v;

                                txv.setTextColor(GerenciarDano.this.getResources().getColor(R.color.DefaultTextColor));

                                switch (txv.getText().toString())
                                {
                                    case "LADO DIREITO":
                                        RemoverTodos(findViewById(R.id.txv_LAD));
                                        RemoverTodos(findViewById(R.id.txv_LMD));
                                        RemoverTodos(findViewById(R.id.txv_LPD));

                                        break;
                                    case "LADO ESQUERDO":
                                        RemoverTodos(findViewById(R.id.txv_LAE));
                                        RemoverTodos(findViewById(R.id.txv_LME));
                                        RemoverTodos(findViewById(R.id.txv_LPE));

                                        break;
                                    case "P\nO\nS\nT\nE\nR\nI\nO\nR":
                                        RemoverTodos(findViewById(R.id.txv_PPD));
                                        RemoverTodos(findViewById(R.id.txv_PPM));
                                        RemoverTodos(findViewById(R.id.txv_PPE));

                                        break;
                                    case "A\nN\nT\nE\nR\nI\nO\nR":
                                        RemoverTodos(findViewById(R.id.txv_PAD));
                                        RemoverTodos(findViewById(R.id.txv_PAM));
                                        RemoverTodos(findViewById(R.id.txv_PAE));

                                        break;
                                }
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
        };


        txvLadoEsquerdo.setOnClickListener(listenerLado);
        txvLadoDireito.setOnClickListener(listenerLado);
        txvPosterior.setOnClickListener(listenerLado);
        txvAnterior.setOnClickListener(listenerLado);

        txvLadoEsquerdo.setOnLongClickListener(longListenerLado);
        txvLadoDireito.setOnLongClickListener(longListenerLado);
        txvPosterior.setOnLongClickListener(longListenerLado);
        txvAnterior.setOnLongClickListener(longListenerLado);

        for (int i = 0; i < rltvBase.getChildCount(); i++)
        {
            try
            {
                TextView txv = (TextView) rltvBase.getChildAt(i);
                if (BuscadorEnum.BuscarSetorDano(txv.getText().toString()) != null)
                {
                    txv.setOnClickListener(listener);
                    txv.setOnLongClickListener(longListener);
                }

            } catch (Exception e)
            {
            }
        }
        cxbCompatibilidade.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isCompativel = cxbCompatibilidade.isChecked();
            }
        });
        cxbDanoCortante.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isDanoCortante = cxbDanoCortante.isChecked();
            }
        });
        cxbDanoContuso.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isDanoContuso = cxbDanoContuso.isChecked();
            }
        });
        cxbDanoFriccao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isDanoFriccao = cxbDanoFriccao.isChecked();
            }
        });
        cxbDanoPerfurante.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isDanoPerfurante = cxbDanoPerfurante.isChecked();
            }
        });
        cxbTercoInferior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isTercoInferior = cxbTercoInferior.isChecked();
            }
        });
        cxbTercoMedio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isTercoMedio = cxbTercoMedio.isChecked();
            }
        });

        cxbTercoSuperior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isTercoSuperior = cxbTercoSuperior.isChecked();
            }
        });

    }

    private void ColorirTxv_Angulos()
    {

        for (String s : setores)
        {
            switch (s)
            {
                case "AAD":
                    ((TextView) findViewById(R.id.txv_AAD)).setTextColor(Color.RED);
                    break;
                case "LAD":
                    ((TextView) findViewById(R.id.txv_LAD)).setTextColor(Color.RED);
                    break;
                case "LMD":
                    ((TextView) findViewById(R.id.txv_LMD)).setTextColor(Color.RED);
                    break;
                case "LPD":
                    ((TextView) findViewById(R.id.txv_LPD)).setTextColor(Color.RED);
                    break;
                case "APD":
                    ((TextView) findViewById(R.id.txv_APD)).setTextColor(Color.RED);
                    break;
                case "PPD":
                    ((TextView) findViewById(R.id.txv_PPD)).setTextColor(Color.RED);
                    break;
                case "PPM":
                    ((TextView) findViewById(R.id.txv_PPM)).setTextColor(Color.RED);
                    break;
                case "PPE":
                    ((TextView) findViewById(R.id.txv_PPE)).setTextColor(Color.RED);
                    break;
                case "APE":
                    ((TextView) findViewById(R.id.txv_APE)).setTextColor(Color.RED);
                    break;
                case "LPE":
                    ((TextView) findViewById(R.id.txv_LPE)).setTextColor(Color.RED);
                    break;
                case "LME":
                    ((TextView) findViewById(R.id.txv_LME)).setTextColor(Color.RED);
                    break;
                case "LAE":
                    ((TextView) findViewById(R.id.txv_LAE)).setTextColor(Color.RED);
                    break;
                case "AAE":
                    ((TextView) findViewById(R.id.txv_AAE)).setTextColor(Color.RED);
                    break;
                case "PAE":
                    ((TextView) findViewById(R.id.txv_PAE)).setTextColor(Color.RED);
                    break;
                case "PAM":
                    ((TextView) findViewById(R.id.txv_PAM)).setTextColor(Color.RED);
                    break;
                case "PAD":
                    ((TextView) findViewById(R.id.txv_PAD)).setTextColor(Color.RED);
                    break;
            }
        }

        if (((TextView) findViewById(R.id.txv_LAE)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_LME)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_LPE)).getCurrentTextColor() == Color.RED
                )

        {
            txvLadoEsquerdo.setTextColor(Color.RED);
        }


        if (((TextView) findViewById(R.id.txv_LAD)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_LMD)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_LPD)).getCurrentTextColor() == Color.RED
                )

        {
            txvLadoDireito.setTextColor(Color.RED);
        }

        if (((TextView) findViewById(R.id.txv_PAD)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_PAM)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_PAE)).getCurrentTextColor() == Color.RED
                )

        {
            txvAnterior.setTextColor(Color.RED);
        }


        if (((TextView) findViewById(R.id.txv_PPD)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_PPM)).getCurrentTextColor() == Color.RED &&
                ((TextView) findViewById(R.id.txv_PPE)).getCurrentTextColor() == Color.RED
                )

        {
            txvPosterior.setTextColor(Color.RED);
        }
    }


}
