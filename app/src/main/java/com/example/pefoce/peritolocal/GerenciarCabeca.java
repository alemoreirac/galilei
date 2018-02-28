package com.example.pefoce.peritolocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Dialogs.LesaoDialog;
import Enums.Genero;
import Enums.SecaoImagem;
import Enums.Vida.ParteCorpo;
import Enums.Vida.Secao;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Model.Vida.OcorrenciaVida;
import Util.BuscadorEnum;
import Util.StringUtil;
import Util.ViewUtil;
import info.hoang8f.android.segmented.SegmentedGroup;

public class GerenciarCabeca extends AppCompatActivity
{
    Long ocorrenciaVidaId;

    RelativeLayout rltvMasculinoEsquerdo;
    RelativeLayout rltvMasculinoDireito;

    RelativeLayout rltvFemininoEsquerdo;
    RelativeLayout rltvFemininoDireito;

    Button btnVoltar;

    TextView txvNomeEnvolvido;

    TextView txvParietalMasculinaDireita;
    TextView txvFrontalMasculinaDireita;
    TextView txvOcularMasculinaDireita;
    TextView txvNasalMasculinaDireita;
    TextView txvBucalMasculinaDireita;
    TextView txvMentonianaMasculinaDireita;
    TextView txvMalarMasculinaDireita;
    TextView txvOccipitalMasculinaDireita;
    TextView txvAuricularMasculinaDireita;
    TextView txvCervicalMasculinaDireita;
    TextView txvCarotidianaMasculinaDireita;

    TextView txvParietalMasculinaEsquerda;
    TextView txvFrontalMasculinaEsquerda;
    TextView txvOcularMasculinaEsquerda;
    TextView txvNasalMasculinaEsquerda;
    TextView txvBucalMasculinaEsquerda;
    TextView txvMentonianaMasculinaEsquerda;
    TextView txvMalarMasculinaEsquerda;
    TextView txvOccipitalMasculinaEsquerda;
    TextView txvAuricularMasculinaEsquerda;
    TextView txvCervicalMasculinaEsquerda;
    TextView txvCarotidianaMasculinaEsquerda;

    TextView txvParietalFemininaDireita;
    TextView txvFrontalFemininaDireita;
    TextView txvOcularFemininaDireita;
    TextView txvNasalFemininaDireita;
    TextView txvBucalFemininaDireita;
    TextView txvMentonianaFemininaDireita;
    TextView txvMalarFemininaDireita;
    TextView txvOccipitalFemininaDireita;
    TextView txvAuricularFemininaDireita;
    TextView txvCervicalFemininaDireita;
    TextView txvCarotidianaFemininaDireita;

    TextView txvParietalFemininaEsquerda;
    TextView txvFrontalFemininaEsquerda;
    TextView txvOcularFemininaEsquerda;
    TextView txvNasalFemininaEsquerda;
    TextView txvBucalFemininaEsquerda;
    TextView txvMentonianaFemininaEsquerda;
    TextView txvMalarFemininaEsquerda;
    TextView txvOccipitalFemininaEsquerda;
    TextView txvAuricularFemininaEsquerda;
    TextView txvCervicalFemininaEsquerda;
    TextView txvCarotidianaFemininaEsquerda;

    ArrayList<Lesao> lesoesList;
    List<LesaoEnvolvido> lesoesEnvolvidos;

    EnvolvidoVida envolvidoVida;

    String generoParam;


    SegmentedGroup sgmtEsquerdaDireita;
    RadioButton rbtnEsquerda;
    RadioButton rbtnDireita;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_cabeca);
        Intent it = getIntent();

        AssociarLayout();
        AssociarEventos();


        //generoParam = it.getStringExtra("GeneroEnvolvido");
        try
        {
            envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class, it.getLongExtra("EnvolvidoId", 0));

            if(envolvidoVida!=null)
                txvNomeEnvolvido.setText(envolvidoVida.getNome());

            generoParam = envolvidoVida.getGenero().getValor();

            lesoesEnvolvidos = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", envolvidoVida.getId().toString());

            lesoesList = new ArrayList<>();

            ocorrenciaVidaId = it.getLongExtra("OcorrenciaId",0l);

            for (LesaoEnvolvido le : lesoesEnvolvidos)
            {
                if (le.getLesao() != null)
                {
                    if (le.getLesao().getParteCorpo() != null)
                    {
                        if (le.getLesao().getParteCorpo() == ParteCorpo.CABECA)
                            lesoesList.add(le.getLesao());
                    }
                }
            }

            String ladoParam = (it.getStringExtra("LadoCabeca"));

            if (ladoParam != null)
            {
                if (ladoParam.equals(SecaoImagem.CABECA_MASCULINO_ESQUERDA.getValor()) || ladoParam.equals(SecaoImagem.CABECA_FEMININO_ESQUERDA.getValor()))
                    rbtnEsquerda.performClick();
                if (ladoParam.equals(SecaoImagem.CABECA_MASCULINO_DIREITA.getValor()) || ladoParam.equals(SecaoImagem.CABECA_FEMININO_DIREITA.getValor()))
                    rbtnDireita.performClick();
            } else
                rbtnEsquerda.performClick();


        } catch (Exception e)
        {
            Intent itException = new Intent(this, GerenciarCorpo.class);
            itException.putExtra("EnvolvidoId", envolvidoVida.getId());
            startActivity(itException);
            Toast.makeText(this, "Erro, envolvido inválido", Toast.LENGTH_LONG).show();
        }
    }

    public void AssociarEventos()
    {
        rbtnDireita.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (generoParam.equals(Genero.FEMININO.getValor()))
                {
                    VisibilidadeCorpos(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvFemininoDireito, lesoesList, getResources().getColor(R.color.colorPrimary));
                } else
                {
                    VisibilidadeCorpos(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvMasculinoDireito, lesoesList, getResources().getColor(R.color.colorPrimary));
                }
            }
        });

        rbtnEsquerda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (generoParam.equals(Genero.FEMININO.getValor()))
                {
                    VisibilidadeCorpos(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvFemininoEsquerdo, lesoesList, getResources().getColor(R.color.colorPrimary));
                } else
                {
                    VisibilidadeCorpos(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                    ViewUtil.ColorirCamposLesoes(rltvMasculinoEsquerdo, lesoesList, getResources().getColor(R.color.colorPrimary));
                }
            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (getIntent().getBooleanExtra("Conclusao", false))
                {
                    Intent it = new Intent(GerenciarCabeca.this, ManterPericiaVida.class);
                    it.putExtra("DiretoParaConclusao", true);
                    it.putExtra("EnvolvidoId", envolvidoVida.getId());
                    it.putExtra("OcorrenciaId", ocorrenciaVidaId);
                    startActivity(it);
                    finish();
                } else
                {
                    Intent it = new Intent(GerenciarCabeca.this, GerenciarCorpo.class);
                    it.putExtra("EnvolvidoId", envolvidoVida.getId());
                    startActivity(it);
                    finish();
                }
            }
        });
    }


    public void AtualizarLesoes()
    {
        lesoesEnvolvidos = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", envolvidoVida.getId().toString());

        lesoesList = new ArrayList<>();

        for (LesaoEnvolvido le : lesoesEnvolvidos)
        {
            if (le.getLesao() != null)
            {
                if (le.getLesao().getParteCorpo() != null)
                {
                    if (le.getLesao().getParteCorpo() == ParteCorpo.CABECA)
                        lesoesList.add(le.getLesao());
                }
            }
        }

        if (rbtnEsquerda.isChecked())
            rbtnEsquerda.performClick();

        if (rbtnDireita.isChecked())
            rbtnDireita.performClick();
    }


    @Override
    public void onBackPressed()
    {

    }


    public void addLesaoCabeca(View view)
    {
        TextView txvView = (TextView) view;

        LesaoDialog lesaoDialog = new LesaoDialog(GerenciarCabeca.this, envolvidoVida, BuscadorEnum.BuscarSecao(txvView.getText().toString()));
    }


    public void AssociarLayout()
    {
        btnVoltar = (Button) findViewById(R.id.btn_Voltar_Cabeca);

        txvNomeEnvolvido = (TextView) findViewById(R.id.txv_Nome_Envolvido_Lesoes);

        sgmtEsquerdaDireita = (SegmentedGroup) findViewById(R.id.sgm_Direita_Esquerda_Cabeca);
        rbtnDireita = (RadioButton) findViewById(R.id.rbtn_Opcao_Direita_Cabeca);
        rbtnEsquerda = (RadioButton) findViewById(R.id.rbtn_Opcao_Esquerda_Cabeca);

        rltvMasculinoEsquerdo = (RelativeLayout) findViewById(R.id.rltv_Cabeca_Masculina_Esquerda);
        rltvMasculinoDireito = (RelativeLayout) findViewById(R.id.rltv_Cabeca_Masculina_Direita);
        rltvFemininoEsquerdo = (RelativeLayout) findViewById(R.id.rltv_Cabeca_Feminina_Esquerda);
        rltvFemininoDireito = (RelativeLayout) findViewById(R.id.rltv_Cabeca_Feminina_Direita);

        txvParietalMasculinaDireita = (TextView) findViewById(R.id.txv_Parietal_Masculina_Direita);
        txvFrontalMasculinaDireita = (TextView) findViewById(R.id.txv_Frontal_Masculina_Direita);
        txvOcularMasculinaDireita = (TextView) findViewById(R.id.txv_Ocular_Masculina_Direita);
        txvNasalMasculinaDireita = (TextView) findViewById(R.id.txv_Nasal_Masculina_Direita);
        txvBucalMasculinaDireita = (TextView) findViewById(R.id.txv_Bucal_Masculina_Direita);
        txvMentonianaMasculinaDireita = (TextView) findViewById(R.id.txv_Mentoniana_Masculina_Direita);
        txvMalarMasculinaDireita = (TextView) findViewById(R.id.txv_Malar_Masculina_Direita);
        txvOccipitalMasculinaDireita = (TextView) findViewById(R.id.txv_Occipital_Masculina_Direita);
        txvAuricularMasculinaDireita = (TextView) findViewById(R.id.txv_Auricular_Masculina_Direita);
        txvCervicalMasculinaDireita = (TextView) findViewById(R.id.txv_Cervical_Masculina_Direita);
        txvCarotidianaMasculinaDireita = (TextView) findViewById(R.id.txv_Carotidiana_Masculina_Direita);

        txvParietalMasculinaEsquerda = (TextView) findViewById(R.id.txv_Parietal_Masculina_Esquerda);
        txvFrontalMasculinaEsquerda = (TextView) findViewById(R.id.txv_Frontal_Masculina_Esquerda);
        txvOcularMasculinaEsquerda = (TextView) findViewById(R.id.txv_Ocular_Masculina_Esquerda);
        txvNasalMasculinaEsquerda = (TextView) findViewById(R.id.txv_Nasal_Masculina_Esquerda);
        txvBucalMasculinaEsquerda = (TextView) findViewById(R.id.txv_Bucal_Masculina_Esquerda);
        txvMentonianaMasculinaEsquerda = (TextView) findViewById(R.id.txv_Mentoniana_Masculina_Esquerda);
        txvMalarMasculinaEsquerda = (TextView) findViewById(R.id.txv_Malar_Masculina_Esquerda);
        txvOccipitalMasculinaEsquerda = (TextView) findViewById(R.id.txv_Occipital_Masculina_Esquerda);
        txvAuricularMasculinaEsquerda = (TextView) findViewById(R.id.txv_Auricular_Masculina_Esquerda);
        txvCervicalMasculinaEsquerda = (TextView) findViewById(R.id.txv_Cervical_Masculina_Esquerda);
        txvCarotidianaMasculinaEsquerda = (TextView) findViewById(R.id.txv_Carotidiana_Masculina_Esquerda);

        txvParietalFemininaDireita = (TextView) findViewById(R.id.txv_Parietal_Feminina_Direita);
        txvFrontalFemininaDireita = (TextView) findViewById(R.id.txv_Frontal_Feminina_Direita);
        txvOcularFemininaDireita = (TextView) findViewById(R.id.txv_Ocular_Feminina_Direita);
        txvNasalFemininaDireita = (TextView) findViewById(R.id.txv_Nasal_Feminina_Direita);
        txvBucalFemininaDireita = (TextView) findViewById(R.id.txv_Bucal_Feminina_Direita);
        txvMentonianaFemininaDireita = (TextView) findViewById(R.id.txv_Mentoniana_Feminina_Direita);
        txvMalarFemininaDireita = (TextView) findViewById(R.id.txv_Malar_Feminina_Direita);
        txvOccipitalFemininaDireita = (TextView) findViewById(R.id.txv_Occipital_Feminina_Direita);
        txvAuricularFemininaDireita = (TextView) findViewById(R.id.txv_Auricular_Feminina_Direita);
        txvCervicalFemininaDireita = (TextView) findViewById(R.id.txv_Cervical_Feminina_Direita);
        txvCarotidianaFemininaDireita = (TextView) findViewById(R.id.txv_Carotidiana_Feminina_Direita);

        txvParietalFemininaEsquerda = (TextView) findViewById(R.id.txv_Parietal_Feminina_Esquerda);
        txvFrontalFemininaEsquerda = (TextView) findViewById(R.id.txv_Frontal_Feminina_Esquerda);
        txvOcularFemininaEsquerda = (TextView) findViewById(R.id.txv_Ocular_Feminina_Esquerda);
        txvNasalFemininaEsquerda = (TextView) findViewById(R.id.txv_Nasal_Feminina_Esquerda);
        txvBucalFemininaEsquerda = (TextView) findViewById(R.id.txv_Bucal_Feminina_Esquerda);
        txvMentonianaFemininaEsquerda = (TextView) findViewById(R.id.txv_Mentoniana_Feminina_Esquerda);
        txvMalarFemininaEsquerda = (TextView) findViewById(R.id.txv_Malar_Feminina_Esquerda);
        txvOccipitalFemininaEsquerda = (TextView) findViewById(R.id.txv_Occipital_Feminina_Esquerda);
        txvAuricularFemininaEsquerda = (TextView) findViewById(R.id.txv_Auricular_Feminina_Esquerda);
        txvCervicalFemininaEsquerda = (TextView) findViewById(R.id.txv_Cervical_Feminina_Esquerda);
        txvCarotidianaFemininaEsquerda = (TextView) findViewById(R.id.txv_Carotidiana_Feminina_Esquerda);


        txvParietalMasculinaDireita.setText(Secao.PARIETAL_DIREITA.getValor());
        txvFrontalMasculinaDireita.setText(Secao.FRONTAL_DIREITA.getValor());
        txvOcularMasculinaDireita.setText(Secao.OCULAR_DIREITA.getValor());
        txvNasalMasculinaDireita.setText(Secao.NASAL_DIREITA.getValor());
        txvBucalMasculinaDireita.setText(Secao.BUCAL_DIREITA.getValor());
        txvMentonianaMasculinaDireita.setText(Secao.MENTONIANA_DIREITA.getValor());
        txvMalarMasculinaDireita.setText(Secao.MALAR_DIREITA.getValor());
        txvOccipitalMasculinaDireita.setText(Secao.OCCIPITAL_DIREITA.getValor());
        txvAuricularMasculinaDireita.setText(Secao.AURICULAR_DIREITA.getValor());
        txvCervicalMasculinaDireita.setText(Secao.CERVICAL_DIREITA.getValor());
        txvCarotidianaMasculinaDireita.setText(Secao.CAROTIDIANA_DIREITA.getValor());

        txvParietalMasculinaEsquerda.setText(Secao.PARIETAL_ESQUERDA.getValor());
        txvFrontalMasculinaEsquerda.setText(Secao.FRONTAL_ESQUERDA.getValor());
        txvOcularMasculinaEsquerda.setText(Secao.OCULAR_ESQUERDA.getValor());
        txvNasalMasculinaEsquerda.setText(Secao.NASAL_ESQUERDA.getValor());
        txvBucalMasculinaEsquerda.setText(Secao.BUCAL_ESQUERDA.getValor());
        txvMentonianaMasculinaEsquerda.setText(Secao.MENTONIANA_ESQUERDA.getValor());
        txvMalarMasculinaEsquerda.setText(Secao.MALAR_ESQUERDA.getValor());
        txvOccipitalMasculinaEsquerda.setText(Secao.OCCIPITAL_ESQUERDA.getValor());
        txvAuricularMasculinaEsquerda.setText(Secao.AURICULAR_ESQUERDA.getValor());
        txvCervicalMasculinaEsquerda.setText(Secao.CERVICAL_ESQUERDA.getValor());
        txvCarotidianaMasculinaEsquerda.setText(Secao.CAROTIDIANA_ESQUERDA.getValor());

        txvParietalFemininaDireita.setText(Secao.PARIETAL_DIREITA.getValor());
        txvFrontalFemininaDireita.setText(Secao.FRONTAL_DIREITA.getValor());
        txvOcularFemininaDireita.setText(Secao.OCULAR_DIREITA.getValor());
        txvNasalFemininaDireita.setText(Secao.NASAL_DIREITA.getValor());
        txvBucalFemininaDireita.setText(Secao.BUCAL_DIREITA.getValor());
        txvMentonianaFemininaDireita.setText(Secao.MENTONIANA_DIREITA.getValor());
        txvMalarFemininaDireita.setText(Secao.MALAR_DIREITA.getValor());
        txvOccipitalFemininaDireita.setText(Secao.OCCIPITAL_DIREITA.getValor());
        txvAuricularFemininaDireita.setText(Secao.AURICULAR_DIREITA.getValor());
        txvCervicalFemininaDireita.setText(Secao.CERVICAL_DIREITA.getValor());
        txvCarotidianaFemininaDireita.setText(Secao.CAROTIDIANA_DIREITA.getValor());

        txvParietalFemininaEsquerda.setText(Secao.PARIETAL_ESQUERDA.getValor());
        txvFrontalFemininaEsquerda.setText(Secao.FRONTAL_ESQUERDA.getValor());
        txvOcularFemininaEsquerda.setText(Secao.OCULAR_ESQUERDA.getValor());
        txvNasalFemininaEsquerda.setText(Secao.NASAL_ESQUERDA.getValor());
        txvBucalFemininaEsquerda.setText(Secao.BUCAL_ESQUERDA.getValor());
        txvMentonianaFemininaEsquerda.setText(Secao.MENTONIANA_ESQUERDA.getValor());
        txvMalarFemininaEsquerda.setText(Secao.MALAR_ESQUERDA.getValor());
        txvOccipitalFemininaEsquerda.setText(Secao.OCCIPITAL_ESQUERDA.getValor());
        txvAuricularFemininaEsquerda.setText(Secao.AURICULAR_ESQUERDA.getValor());
        txvCervicalFemininaEsquerda.setText(Secao.CERVICAL_ESQUERDA.getValor());
        txvCarotidianaFemininaEsquerda.setText(Secao.CAROTIDIANA_ESQUERDA.getValor());
    }

    public void VisibilidadeCorpos(int vMasculinoDireito, int vMasculinoEsquerdo, int vFemininoDireito, int vFemininoEsquerdo)
    {
        rltvMasculinoDireito.setVisibility(vMasculinoDireito);
        rltvMasculinoEsquerdo.setVisibility(vMasculinoEsquerdo);

        rltvFemininoDireito.setVisibility(vFemininoDireito);
        rltvFemininoEsquerdo.setVisibility(vFemininoEsquerdo);
    }
}
