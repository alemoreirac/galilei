package com.example.pefoce.peritolocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Dialogs.LesaoDialog;
import Enums.Genero;
import Enums.Vida.ParteCorpo;
import Enums.Vida.Secao;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Util.BuscadorEnum;
import Util.ViewUtil;
import info.hoang8f.android.segmented.SegmentedGroup;

public class GerenciarTorax extends AppCompatActivity
{
    EnvolvidoVida envolvidoVida;

    TextView txvClavicularEsquerdoMasculinoCostas;
    TextView txvEscapularEsquerdoMasculino;
    TextView txvLombarEsquerdoMasculino;
    TextView txvIliacoEsquerdoMasculinoCostas;
    TextView txvNadegaEsquerdaMasculina;

    TextView txvClavicularDireitoMasculinoCostas;
    TextView txvEscapularDireitoMasculino;
    TextView txvLombarDireitoMasculino;
    TextView txvIliacoDireitoMasculinoCostas;
    TextView txvNadegaDireitaMasculina;

    TextView txvAnusMasculino;

    TextView txvClavicularEsquerdoFemininoCostas;
    TextView txvEscapularEsquerdoFeminino;
    TextView txvLombarEsquerdoFeminino;
    TextView txvIliacoEsquerdoFemininoCostas;
    TextView txvNadegaEsquerdaFeminina;

    TextView txvClavicularDireitoFemininoCostas;
    TextView txvEscapularDireitoFeminino;
    TextView txvLombarDireitoFeminino;
    TextView txvIliacoDireitoFemininoCostas;
    TextView txvNadegaDireitaFeminina;

    TextView txvAnusFeminino;

    TextView txvClavicularEsquerdoMasculinoFrente;
    TextView txvPeitoralEsquerdoMasculino;
    TextView txvHipocondrioEsquerdoMasculino;
    TextView txvFlancoEsquerdoMasculino;
    TextView txvIliacoEsquerdoMasculinoFrente;

    TextView txvClavicularDireitoMasculinoFrente;
    TextView txvPeitoralDireitoMasculino;
    TextView txvHipocondrioDireitoMasculino;
    TextView txvFlancoDireitoMasculino;
    TextView txvIliacoDireitoMasculinoFrente;

    TextView txvGenitalMasculino;


    TextView txvClavicularEsquerdoFemininoFrente;
    TextView txvPeitoralEsquerdoFeminino;
    TextView txvHipocondrioEsquerdoFeminino;
    TextView txvFlancoEsquerdoFeminino;
    TextView txvIliacoEsquerdoFemininoFrente;

    TextView txvClavicularDireitoFemininoFrente;
    TextView txvPeitoralDireitoFeminino;
    TextView txvHipocondrioDireitoFeminino;
    TextView txvFlancoDireitoFeminino;
    TextView txvIliacoDireitoFemininoFrente;

    TextView txvGenitalFeminino;

    RelativeLayout rltvMasculinoFrente;
    RelativeLayout rltvMasculinoCostas;

    RelativeLayout rltvFemininoFrente;
    RelativeLayout rltvFemininoCostas;

    SegmentedGroup sgmtFrenteCostas;
    RadioButton rbtnFrente;
    RadioButton rbtnCostas;

    Button btnVoltar;

    TextView txvNomeEnvolvido;

    ArrayList<Lesao> lesoesList;
    List<LesaoEnvolvido> lesoesEnvolvidos;


    String generoParam;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_torax);

        Intent it = getIntent();

        AssociarLayout();
        AssociarEventos();

    //    generoParam = it.getStringExtra("GeneroEnvolvido");
        try
        {
            envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class, it.getLongExtra("EnvolvidoId", 0));

            if(envolvidoVida!=null)
                txvNomeEnvolvido.setText(envolvidoVida.getNome());

            generoParam = envolvidoVida.getGenero().getValor();

            lesoesEnvolvidos= LesaoEnvolvido.find(LesaoEnvolvido.class,"envolvido_vida = ?",envolvidoVida.getId().toString());

            lesoesList = new ArrayList<>();

            for(LesaoEnvolvido le : lesoesEnvolvidos)
            {
                if(le.getLesao()!=null)
                {
                    if(le.getLesao().getParteCorpo()!=null)
                    {
                        if(le.getLesao().getParteCorpo()== ParteCorpo.TORAX)
                            lesoesList.add(le.getLesao());
                    }
                }
            }

                if (it.getBooleanExtra("Costas",false))
                {
                    rbtnCostas.performClick();
                }
                else
                {
                    rbtnFrente.performClick();
                }


        } catch (Exception e)
        {
            Intent itException = new Intent(this, GerenciarCorpo.class);
            startActivity(itException);
            Toast.makeText(this, "Erro, envolvido inválido", Toast.LENGTH_LONG).show();
        }

    }

    private void AssociarLayout()
    {
        txvNomeEnvolvido = (TextView) findViewById(R.id.txv_Nome_Envolvido_Lesoes);

        rltvFemininoCostas = (RelativeLayout) findViewById(R.id.rltv_Costas_Femininas);
        rltvMasculinoCostas= (RelativeLayout) findViewById(R.id.rltv_Costas_Masculinas);

        rltvFemininoFrente = (RelativeLayout) findViewById(R.id.rltv_Frente_Feminina);
        rltvMasculinoFrente = (RelativeLayout) findViewById(R.id.rltv_Frente_Masculina);

        btnVoltar = (Button) findViewById(R.id.btn_Torax_Voltar);

        sgmtFrenteCostas = (SegmentedGroup) findViewById(R.id.sgm_Frente_Costas_Torax);

        rbtnCostas = (RadioButton) findViewById(R.id.rbtn_Opcao_Costas_Torax);
        rbtnFrente = (RadioButton) findViewById(R.id.rbtn_Opcao_Frente_Torax);

        txvClavicularEsquerdoMasculinoCostas = (TextView) findViewById(R.id.txv_Clavicular_Esquerdo_Masculino_Costas);
        txvEscapularEsquerdoMasculino = (TextView) findViewById(R.id.txv_Escapular_Esquerdo_Masculino);
        txvLombarEsquerdoMasculino = (TextView) findViewById(R.id.txv_Lombar_Esquerdo_Masculino);
        txvIliacoEsquerdoMasculinoCostas = (TextView) findViewById(R.id.txv_Iliaco_Esquerdo_Costas_Masculino);
        txvNadegaEsquerdaMasculina = (TextView) findViewById(R.id.txv_Nadega_Esquerda_Masculina);

        txvClavicularDireitoMasculinoCostas = (TextView) findViewById(R.id.txv_Clavicular_Direito_Masculino_Costas);
        txvEscapularDireitoMasculino = (TextView) findViewById(R.id.txv_Escapular_Direito_Masculino);
        txvLombarDireitoMasculino = (TextView) findViewById(R.id.txv_Lombar_Direito_Masculino);
        txvIliacoDireitoMasculinoCostas = (TextView) findViewById(R.id.txv_Iliaco_Direito_Costas_Masculino);
        txvNadegaDireitaMasculina = (TextView) findViewById(R.id.txv_Nadega_Direita_Masculina);

        txvAnusMasculino = (TextView) findViewById(R.id.txv_Anus_Masculina);

        txvClavicularEsquerdoFemininoCostas = (TextView) findViewById(R.id.txv_Clavicular_Esquerdo_Feminino_Costas);
        txvEscapularEsquerdoFeminino = (TextView) findViewById(R.id.txv_Escapular_Esquerdo_Feminino);
        txvLombarEsquerdoFeminino = (TextView) findViewById(R.id.txv_Lombar_Esquerdo_Feminino);
        txvIliacoEsquerdoFemininoCostas = (TextView) findViewById(R.id.txv_Iliaco_Esquerdo_Costas_Feminino);
        txvNadegaEsquerdaFeminina = (TextView) findViewById(R.id.txv_Nadega_Esquerda_Feminina);

        txvClavicularDireitoFemininoCostas = (TextView) findViewById(R.id.txv_Clavicular_Direito_Feminino_Costas);
        txvEscapularDireitoFeminino = (TextView) findViewById(R.id.txv_Escapular_Direito_Feminino);
        txvLombarDireitoFeminino = (TextView) findViewById(R.id.txv_Lombar_Direito_Feminino);
        txvIliacoDireitoFemininoCostas = (TextView) findViewById(R.id.txv_Iliaco_Direito_Costas_Feminino);
        txvNadegaDireitaFeminina = (TextView) findViewById(R.id.txv_Nadega_Direita_Feminina);

        txvAnusFeminino = (TextView) findViewById(R.id.txv_Anus_Feminina);

        txvClavicularEsquerdoMasculinoFrente = (TextView) findViewById(R.id.txv_Clavicular_Esquerdo_Masculino_Frente);
        txvPeitoralEsquerdoMasculino = (TextView) findViewById(R.id.txv_Peitoral_Esquerdo_Masculino);
        txvHipocondrioEsquerdoMasculino = (TextView) findViewById(R.id.txv_Hipocondrio_Esquerdo_Masculino);
        txvFlancoEsquerdoMasculino = (TextView) findViewById(R.id.txv_Flanco_Esquerdo_Masculino);
        txvIliacoEsquerdoMasculinoFrente = (TextView) findViewById(R.id.txv_Iliaco_Esquerdo_Frente_Masculino);

        txvClavicularDireitoMasculinoFrente = (TextView) findViewById(R.id.txv_Clavicular_Direito_Masculino_Frente);
        txvPeitoralDireitoMasculino = (TextView) findViewById(R.id.txv_Peitoral_Direito_Masculino);
        txvHipocondrioDireitoMasculino = (TextView) findViewById(R.id.txv_Hipocondrio_Direito_Masculino);
        txvFlancoDireitoMasculino = (TextView) findViewById(R.id.txv_Flanco_Direito_Masculino);
        txvIliacoDireitoMasculinoFrente = (TextView) findViewById(R.id.txv_Iliaco_Direito_Frente_Masculino);

        txvGenitalMasculino = (TextView) findViewById(R.id.txv_Genital_Masculino);

        txvClavicularEsquerdoFemininoFrente = (TextView) findViewById(R.id.txv_Clavicular_Esquerdo_Feminino_Frente);
        txvPeitoralEsquerdoFeminino = (TextView) findViewById(R.id.txv_Peitoral_Esquerdo_Feminino);
        txvHipocondrioEsquerdoFeminino = (TextView) findViewById(R.id.txv_Hipocondrio_Esquerdo_Feminino);
        txvFlancoEsquerdoFeminino = (TextView) findViewById(R.id.txv_Flanco_Esquerdo_Feminino);
        txvIliacoEsquerdoFemininoFrente = (TextView) findViewById(R.id.txv_Iliaco_Esquerdo_Frente_Feminino);

        txvClavicularDireitoFemininoFrente = (TextView) findViewById(R.id.txv_Clavicular_Direito_Feminino_Frente);
        txvPeitoralDireitoFeminino = (TextView) findViewById(R.id.txv_Peitoral_Direito_Feminino);
        txvHipocondrioDireitoFeminino = (TextView) findViewById(R.id.txv_Hipocondrio_Direito_Feminino);
        txvFlancoDireitoFeminino = (TextView) findViewById(R.id.txv_Flanco_Direito_Feminino);
        txvIliacoDireitoFemininoFrente = (TextView) findViewById(R.id.txv_Iliaco_Direito_Frente_Feminino);
        txvGenitalFeminino = (TextView) findViewById(R.id.txv_Genital_Feminino);


        txvClavicularEsquerdoMasculinoCostas.setText(Secao.CLAVICULAR_POSTERIOR_ESQUERDO.getValor());
        txvEscapularEsquerdoMasculino.setText(Secao.ESCAPULAR_ESQUERDO.getValor());
        txvLombarEsquerdoMasculino.setText(Secao.LOMBAR_ESQUERDO.getValor());
        txvIliacoEsquerdoMasculinoCostas.setText(Secao.ILIACO_POSTERIOR_ESQUERDO.getValor());
        txvNadegaEsquerdaMasculina.setText(Secao.GLUTEO_ESQUERDO.getValor());

        txvClavicularDireitoMasculinoCostas.setText(Secao.CLAVICULAR_POSTERIOR_DIREITO.getValor());
        txvEscapularDireitoMasculino.setText(Secao.ESCAPULAR_DIREITO.getValor());
        txvLombarDireitoMasculino.setText(Secao.LOMBAR_DIREITO.getValor());
        txvIliacoDireitoMasculinoCostas.setText(Secao.ILIACO_POSTERIOR_DIREITO.getValor());
        txvNadegaDireitaMasculina.setText(Secao.GLUTEO_DIREITO.getValor());

        txvAnusMasculino.setText(Secao.ANUS.getValor());


        txvClavicularEsquerdoFemininoCostas.setText(Secao.CLAVICULAR_POSTERIOR_ESQUERDO.getValor());
        txvEscapularEsquerdoFeminino.setText(Secao.ESCAPULAR_ESQUERDO.getValor());
        txvLombarEsquerdoFeminino.setText(Secao.LOMBAR_ESQUERDO.getValor());
        txvIliacoEsquerdoFemininoCostas.setText(Secao.ILIACO_POSTERIOR_ESQUERDO.getValor());
        txvNadegaEsquerdaFeminina.setText(Secao.GLUTEO_ESQUERDO.getValor());

        txvClavicularDireitoFemininoCostas.setText(Secao.CLAVICULAR_POSTERIOR_DIREITO.getValor());
        txvEscapularDireitoFeminino.setText(Secao.ESCAPULAR_DIREITO.getValor());
        txvLombarDireitoFeminino.setText(Secao.LOMBAR_DIREITO.getValor());
        txvIliacoDireitoFemininoCostas.setText(Secao.ILIACO_POSTERIOR_DIREITO.getValor());
        txvNadegaDireitaFeminina.setText(Secao.GLUTEO_DIREITO.getValor());

        txvAnusFeminino.setText(Secao.ANUS.getValor());

        txvClavicularEsquerdoMasculinoFrente.setText(Secao.CLAVICULAR_ANTERIOR_ESQUERDO.getValor());
        txvPeitoralEsquerdoMasculino.setText(Secao.PEITORAL_ESQUERDO.getValor());
        txvHipocondrioEsquerdoMasculino.setText(Secao.HIPOCONDRIO_ESQUERDO.getValor());
        txvFlancoEsquerdoMasculino.setText(Secao.FLANCO_ESQUERDO.getValor());
        txvIliacoEsquerdoMasculinoFrente.setText(Secao.ILIACO_ANTERIOR_ESQUERDO.getValor());

        txvClavicularDireitoMasculinoFrente.setText(Secao.CLAVICULAR_ANTERIOR_DIREITO.getValor());
        txvPeitoralDireitoMasculino.setText(Secao.PEITORAL_DIREITO.getValor());
        txvHipocondrioDireitoMasculino.setText(Secao.HIPOCONDRIO_DIREITO.getValor());
        txvFlancoDireitoMasculino.setText(Secao.FLANCO_DIREITO.getValor());
        txvIliacoDireitoMasculinoFrente.setText(Secao.ILIACO_ANTERIOR_DIREITO.getValor());

        txvGenitalMasculino.setText(Secao.GENITAL.getValor());

        txvClavicularEsquerdoFemininoFrente.setText(Secao.CLAVICULAR_ANTERIOR_ESQUERDO.getValor());
        txvPeitoralEsquerdoFeminino.setText(Secao.PEITORAL_ESQUERDO.getValor());
        txvHipocondrioEsquerdoFeminino.setText(Secao.HIPOCONDRIO_ESQUERDO.getValor());
        txvFlancoEsquerdoFeminino.setText(Secao.FLANCO_ESQUERDO.getValor());
        txvIliacoEsquerdoFemininoFrente.setText(Secao.ILIACO_ANTERIOR_ESQUERDO.getValor());

        txvClavicularDireitoFemininoFrente.setText(Secao.CLAVICULAR_ANTERIOR_DIREITO.getValor());
        txvPeitoralDireitoFeminino.setText(Secao.PEITORAL_DIREITO.getValor());
        txvHipocondrioDireitoFeminino.setText(Secao.HIPOCONDRIO_DIREITO.getValor());
        txvFlancoDireitoFeminino.setText(Secao.FLANCO_DIREITO.getValor());
        txvIliacoDireitoFemininoFrente.setText(Secao.ILIACO_ANTERIOR_DIREITO.getValor());

        txvGenitalFeminino.setText(Secao.GENITAL.getValor());
    }

    private void AssociarEventos()
    {
        rbtnFrente.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(generoParam.equals(Genero.FEMININO.getValor()))
                {
                    VisibilidadeCorpos(View.INVISIBLE,View.INVISIBLE,View.VISIBLE,View.INVISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvFemininoFrente, lesoesList,getResources().getColor(R.color.colorPrimary));
                }
                else
                {
                    VisibilidadeCorpos(View.INVISIBLE,View.VISIBLE,View.INVISIBLE,View.INVISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvMasculinoFrente,lesoesList,getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        rbtnCostas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(generoParam.equals(Genero.FEMININO.getValor()))
                {
                    VisibilidadeCorpos(View.INVISIBLE,View.INVISIBLE,View.INVISIBLE,View.VISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvFemininoCostas, lesoesList,getResources().getColor(R.color.colorPrimary));
                }
                else
                {
                    VisibilidadeCorpos(View.VISIBLE,View.INVISIBLE,View.INVISIBLE,View.INVISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvMasculinoCostas,lesoesList,getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(GerenciarTorax.this,GerenciarCorpo.class);
                it.putExtra("EnvolvidoId",envolvidoVida.getId());
                it.putExtra("GeneroEnvolvido",generoParam);
                startActivity(it);
            }
        });
//        txvClavicularEsquerdoMasculinoCostas.setOnClickListener(listener);
//        txvEscapularEsquerdoMasculino.setOnClickListener(listener);
//        txvLombarEsquerdoMasculino.setOnClickListener(listener);
//        txvIliacoEsquerdoMasculinoCostas.setOnClickListener(listener);
//        txvNadegaEsquerdaMasculina.setOnClickListener(listener);
//
//        txvClavicularDireitoMasculinoCostas.setOnClickListener(listener);
//        txvEscapularDireitoMasculino.setOnClickListener(listener);
//        txvLombarDireitoMasculino.setOnClickListener(listener);
//        txvIliacoDireitoMasculinoCostas.setOnClickListener(listener);
//        txvNadegaDireitaMasculina.setOnClickListener(listener);
//
//        txvAnusMasculino.setOnClickListener(listener);
//
//
//        txvClavicularEsquerdoFemininoCostas.setOnClickListener(listener);
//        txvEscapularEsquerdoFeminino.setOnClickListener(listener);
//        txvLombarEsquerdoFeminino.setOnClickListener(listener);
//        txvIliacoEsquerdoFemininoCostas.setOnClickListener(listener);
//        txvNadegaEsquerdaFeminina.setOnClickListener(listener);
//
//        txvClavicularDireitoFemininoCostas.setOnClickListener(listener);
//        txvEscapularDireitoFeminino.setOnClickListener(listener);
//        txvLombarDireitoFeminino.setOnClickListener(listener);
//        txvIliacoDireitoFemininoCostas.setOnClickListener(listener);
//        txvNadegaDireitaFeminina.setOnClickListener(listener);
//
//        txvAnusFeminino.setOnClickListener(listener);
//
//
//        txvClavicularEsquerdoMasculinoFrente.setOnClickListener(listener);
//        txvPeitoralEsquerdoMasculino.setOnClickListener(listener);
//        txvHipocondrioEsquerdoMasculino.setOnClickListener(listener);
//        txvFlancoEsquerdoMasculino.setOnClickListener(listener);
//        txvIliacoEsquerdoMasculinoFrente.setOnClickListener(listener);
//
//        txvClavicularDireitoMasculinoFrente.setOnClickListener(listener);
//        txvPeitoralDireitoMasculino.setOnClickListener(listener);
//        txvHipocondrioDireitoMasculino.setOnClickListener(listener);
//        txvFlancoDireitoMasculino.setOnClickListener(listener);
//        txvIliacoDireitoMasculinoFrente.setOnClickListener(listener);
//
//        txvGenitalMasculino.setOnClickListener(listener);
//
//        txvClavicularEsquerdoFemininoFrente.setOnClickListener(listener);
//        txvPeitoralEsquerdoFeminino.setOnClickListener(listener);
//        txvHipocondrioEsquerdoFeminino.setOnClickListener(listener);
//        txvFlancoEsquerdoFeminino.setOnClickListener(listener);
//        txvIliacoEsquerdoFemininoFrente.setOnClickListener(listener);
//
//        txvClavicularDireitoFemininoFrente.setOnClickListener(listener);
//        txvPeitoralDireitoFeminino.setOnClickListener(listener);
//        txvHipocondrioDireitoFeminino.setOnClickListener(listener);
//        txvFlancoDireitoFeminino.setOnClickListener(listener);
//        txvIliacoDireitoFemininoFrente.setOnClickListener(listener);
//
//        txvGenitalFeminino.setOnClickListener(listener);
//
    }

    public void addLesaoTorax(View view)
    {
        TextView txvView = (TextView) view;
        LesaoDialog lesaoDialog = new LesaoDialog(GerenciarTorax.this, envolvidoVida, BuscadorEnum.BuscarSecao(txvView.getText().toString()));
    }

    public void AtualizarLesoes()
    {
        lesoesEnvolvidos= LesaoEnvolvido.find(LesaoEnvolvido.class,"envolvido_vida = ?",envolvidoVida.getId().toString());

        lesoesList = new ArrayList<>();

        for(LesaoEnvolvido le : lesoesEnvolvidos)
        {
            if(le.getLesao()!=null)
            {
                if(le.getLesao().getParteCorpo()!=null)
                {
                    if(le.getLesao().getParteCorpo()== ParteCorpo.TORAX)
                        lesoesList.add(le.getLesao());
                }
            }
        }

        if(rbtnCostas.isChecked())
            rbtnCostas.performClick();

        if(rbtnFrente.isChecked())
            rbtnFrente.performClick();
    }

    public void VisibilidadeCorpos(int vMasculinoCostas,int vMasculinoFrente,int vFemininoFrente,int vFemininoCostas)
    {
        rltvMasculinoCostas.setVisibility(vMasculinoCostas);
        rltvMasculinoFrente.setVisibility(vMasculinoFrente);

        rltvFemininoCostas.setVisibility(vFemininoCostas);
        rltvFemininoFrente.setVisibility(vFemininoFrente);
    }



    @Override
    public void onBackPressed()
    {

    }

}
