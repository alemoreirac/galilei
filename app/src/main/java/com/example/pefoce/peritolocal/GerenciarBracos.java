package com.example.pefoce.peritolocal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class GerenciarBracos extends AppCompatActivity
{
    TextView txvNomeEnvolvido;

    //Masculino lado Direito
    TextView txv_SecaoSuperior_BracoDireito_Masculino;
    TextView txv_SecaoInferior_BracoDireito_Masculino;

    TextView txv_SecaoSuperior_AnteBracoDireito_Masculino;
    TextView txv_SecaoInferior_AnteBracoDireito_Masculino;

    TextView txv_CotoveloDireito_Masculino;
    TextView txv_MaoDireita_Masculina;

    //Masculino lado Esquerdo
    TextView txv_SecaoSuperior_BracoEsquerdo_Masculino;
    TextView txv_SecaoInferior_BracoEsquerdo_Masculino;

    TextView txv_SecaoSuperior_AnteBracoEsquerdo_Masculino;
    TextView txv_SecaoInferior_AnteBracoEsquerdo_Masculino;

    TextView txv_MaoEsquerda_Masculina;
    TextView txv_CotoveloEsquerdo_Masculino;

    //Feminino lado Direito
    TextView txv_SecaoSuperior_BracoDireito_Feminino;
    TextView txv_SecaoInferior_BracoDireito_Feminino;

    TextView txv_SecaoSuperior_AnteBracoDireito_Feminino;
    TextView txv_SecaoInferior_AnteBracoDireito_Feminino;

    TextView txv_CotoveloDireito_Feminino;
    TextView txv_MaoDireita_Feminina;

    //Feminino lado Esquerdo
    TextView txv_SecaoSuperior_BracoEsquerdo_Feminino;
    TextView txv_SecaoInferior_BracoEsquerdo_Feminino;

    TextView txv_SecaoSuperior_AnteBracoEsquerdo_Feminino;
    TextView txv_SecaoInferior_AnteBracoEsquerdo_Feminino;

    TextView txv_MaoEsquerda_Feminina;
    TextView txv_CotoveloEsquerdo_Feminino;

    EnvolvidoVida envolvidoVida;

    RelativeLayout rltvMasculino;
    RelativeLayout rltvFeminino;

    String generoParam;

    Button btnVoltar;

    ArrayList<Lesao> lesoesList;
    List<LesaoEnvolvido> lesoesEnvolvidos;



    @Override
    public void onBackPressed()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_bracos);

        Intent it = getIntent();

       generoParam = "";

        AssociarLayout();
        AssociarEventos();
        try
        {
            envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class, it.getLongExtra("EnvolvidoId", 0));

            if(envolvidoVida!=null)
                txvNomeEnvolvido.setText(envolvidoVida.getNome());

            generoParam = envolvidoVida.getGenero().getValor();

            lesoesEnvolvidos = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", envolvidoVida.getId().toString());

            lesoesList = new ArrayList<>();
            for (LesaoEnvolvido le : lesoesEnvolvidos)
            {
                if (le.getLesao() != null)
                {
                    if (le.getLesao().getParteCorpo() != null)
                    {
                        if (le.getLesao().getParteCorpo() == ParteCorpo.BRACOS)
                            lesoesList.add(le.getLesao());
                    }
                }
            }

            if (generoParam.equals(Genero.FEMININO.getValor()))
                ViewUtil.ColorirCamposLesoes(rltvFeminino, lesoesList, getResources().getColor(R.color.colorPrimary));
            else
                ViewUtil.ColorirCamposLesoes(rltvMasculino, lesoesList, getResources().getColor(R.color.colorPrimary));

            VisibilidadeCorpos();

        } catch (Exception e)
        {
            Intent itException = new Intent(this, GerenciarCorpo.class);
            startActivity(itException);
            Toast.makeText(this, "Erro, envolvido inválido", Toast.LENGTH_LONG).show();
        }

    }

    public void AssociarEventos()
    {
        btnVoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(GerenciarBracos.this,GerenciarCorpo.class);
                it.putExtra("EnvolvidoId",envolvidoVida.getId());
             //   it.putExtra("GeneroEnvolvido",generoParam);
                startActivity(it);
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
                    if (le.getLesao().getParteCorpo() == ParteCorpo.BRACOS)

                        lesoesList.add(le.getLesao());
                }
            }
        }

        if (generoParam.equals(Genero.FEMININO.getValor()))
            ViewUtil.ColorirCamposLesoes(rltvFeminino, lesoesList, getResources().getColor(R.color.colorPrimary));
        else
            ViewUtil.ColorirCamposLesoes(rltvMasculino, lesoesList, getResources().getColor(R.color.colorPrimary));
    }

    public void addLesaoBraco(View view)
    {
        TextView txvView = (TextView) view;
        LesaoDialog lesaoDialog = new LesaoDialog(GerenciarBracos.this, envolvidoVida, BuscadorEnum.BuscarSecao(txvView.getText().toString()));
    }

    public void AssociarLayout()
    {
        rltvFeminino = (RelativeLayout) findViewById(R.id.rltv_Bracos_Femininos);
        rltvMasculino = (RelativeLayout) findViewById(R.id.rltv_Bracos_Masculinos);

        VisibilidadeCorpos();

        txvNomeEnvolvido = (TextView) findViewById(R.id.txv_Nome_Envolvido_Lesoes);

        btnVoltar = (Button) findViewById(R.id.btn_Voltar_bracos);

        txv_SecaoSuperior_BracoEsquerdo_Feminino = (TextView) findViewById(R.id.txv_Secao_Superior_Braco_Esquerdo_Feminino);
        txv_SecaoInferior_BracoEsquerdo_Feminino = (TextView) findViewById(R.id.txv_Secao_Inferior_Braco_Esquerdo_Feminino);
        txv_CotoveloEsquerdo_Feminino = (TextView) findViewById(R.id.txv_Cotovelo_Braco_Esquerdo_Feminino);
        txv_SecaoSuperior_AnteBracoEsquerdo_Feminino = (TextView) findViewById(R.id.txv_Secao_Superior_AnteBraco_Esquerdo_Feminino);
        txv_SecaoInferior_AnteBracoEsquerdo_Feminino = (TextView) findViewById(R.id.txv_Secao_Inferior_AnteBraco_Esquerdo_Feminino);
        txv_MaoEsquerda_Feminina = (TextView) findViewById(R.id.txv_Mao_Esquerda_Feminino);


        txv_SecaoSuperior_BracoDireito_Feminino = (TextView) findViewById(R.id.txv_Secao_Superior_Braco_Direito_Feminino);
        txv_SecaoInferior_BracoDireito_Feminino = (TextView) findViewById(R.id.txv_Secao_Inferior_Braco_Direito_Feminino);
        txv_CotoveloDireito_Feminino = (TextView) findViewById(R.id.txv_Cotovelo_Braco_Direito_Feminino);
        txv_SecaoSuperior_AnteBracoDireito_Feminino = (TextView) findViewById(R.id.txv_Secao_Superior_AnteBraco_Direito_Feminino);
        txv_SecaoInferior_AnteBracoDireito_Feminino = (TextView) findViewById(R.id.txv_Secao_Inferior_AnteBraco_Direito_Feminino);
        txv_MaoDireita_Feminina = (TextView) findViewById(R.id.txv_Mao_Direita_Feminino);


        txv_SecaoSuperior_BracoEsquerdo_Masculino = (TextView) findViewById(R.id.txv_Secao_Superior_Braco_Esquerdo_Masculino);
        txv_SecaoInferior_BracoEsquerdo_Masculino = (TextView) findViewById(R.id.txv_Secao_Inferior_Braco_Esquerdo_Masculino);
        txv_CotoveloEsquerdo_Masculino = (TextView) findViewById(R.id.txv_Cotovelo_Braco_Esquerdo_Masculino);
        txv_SecaoSuperior_AnteBracoEsquerdo_Masculino = (TextView) findViewById(R.id.txv_Secao_Superior_AnteBraco_Esquerdo_Masculino);
        txv_SecaoInferior_AnteBracoEsquerdo_Masculino = (TextView) findViewById(R.id.txv_Secao_Inferior_AnteBraco_Esquerdo_Masculino);
        txv_MaoEsquerda_Masculina = (TextView) findViewById(R.id.txv_Mao_Esquerda_Masculino);

        txv_SecaoSuperior_BracoDireito_Masculino = (TextView) findViewById(R.id.txv_Secao_Superior_Braco_Direito_Masculino);
        txv_SecaoInferior_BracoDireito_Masculino = (TextView) findViewById(R.id.txv_Secao_Inferior_Braco_Direito_Masculino);
        txv_CotoveloDireito_Masculino = (TextView) findViewById(R.id.txv_Cotovelo_Braco_Direito_Masculino);
        txv_SecaoSuperior_AnteBracoDireito_Masculino = (TextView) findViewById(R.id.txv_Secao_Superior_AnteBraco_Direito_Masculino);
        txv_SecaoInferior_AnteBracoDireito_Masculino = (TextView) findViewById(R.id.txv_Secao_Inferior_AnteBraco_Direito_Masculino);
        txv_MaoDireita_Masculina = (TextView) findViewById(R.id.txv_Mao_Direita_Masculino);

        // ---- Associar valores dos txvs aos enums para passar o parâmetro de forma correta na função de criar dialog de lesões

        txv_SecaoSuperior_BracoEsquerdo_Feminino.setText(Secao.SETOR_SUPERIOR_BRACO_ESQUERDO.getValor());
        txv_SecaoInferior_BracoEsquerdo_Feminino.setText(Secao.SETOR_INFERIOR_BRACO_ESQUERDO.getValor());
        txv_CotoveloEsquerdo_Feminino.setText(Secao.COTOVELO_ESQUERDO.getValor());
        txv_SecaoSuperior_AnteBracoEsquerdo_Feminino.setText(Secao.SETOR_SUPERIOR_ANTEBRACO_ESQUERDO.getValor());
        txv_SecaoInferior_AnteBracoEsquerdo_Feminino.setText(Secao.SETOR_INFERIOR_ANTEBRACO_ESQUERDO.getValor());
        txv_MaoEsquerda_Feminina.setText(Secao.MAO_ESQUERDA.getValor());

        txv_SecaoSuperior_BracoDireito_Feminino.setText(Secao.SETOR_SUPERIOR_BRACO_DIREITO.getValor());
        txv_SecaoInferior_BracoDireito_Feminino.setText(Secao.SETOR_INFERIOR_BRACO_DIREITO.getValor());
        txv_CotoveloDireito_Feminino.setText(Secao.COTOVELO_DIREITO.getValor());
        txv_SecaoSuperior_AnteBracoDireito_Feminino.setText(Secao.SETOR_SUPERIOR_ANTEBRACO_DIREITO.getValor());
        txv_SecaoInferior_AnteBracoDireito_Feminino.setText(Secao.SETOR_INFERIOR_ANTEBRACO_DIREITO.getValor());
        txv_MaoDireita_Feminina.setText(Secao.MAO_DIREITA.getValor());

        //--
        txv_SecaoSuperior_BracoEsquerdo_Masculino.setText(Secao.SETOR_SUPERIOR_BRACO_ESQUERDO.getValor());
        txv_SecaoInferior_BracoEsquerdo_Masculino.setText(Secao.SETOR_INFERIOR_BRACO_ESQUERDO.getValor());
        txv_CotoveloEsquerdo_Masculino.setText(Secao.COTOVELO_ESQUERDO.getValor());
        txv_SecaoSuperior_AnteBracoEsquerdo_Masculino.setText(Secao.SETOR_SUPERIOR_ANTEBRACO_ESQUERDO.getValor());
        txv_SecaoInferior_AnteBracoEsquerdo_Masculino.setText(Secao.SETOR_INFERIOR_ANTEBRACO_ESQUERDO.getValor());
        txv_MaoEsquerda_Masculina.setText(Secao.MAO_ESQUERDA.getValor());

        txv_SecaoSuperior_BracoDireito_Masculino.setText(Secao.SETOR_SUPERIOR_BRACO_DIREITO.getValor());
        txv_SecaoInferior_BracoDireito_Masculino.setText(Secao.SETOR_INFERIOR_BRACO_DIREITO.getValor());
        txv_CotoveloDireito_Masculino.setText(Secao.COTOVELO_DIREITO.getValor());
        txv_SecaoSuperior_AnteBracoDireito_Masculino.setText(Secao.SETOR_SUPERIOR_ANTEBRACO_DIREITO.getValor());
        txv_SecaoInferior_AnteBracoDireito_Masculino.setText(Secao.SETOR_INFERIOR_ANTEBRACO_DIREITO.getValor());
        txv_MaoDireita_Masculina.setText(Secao.MAO_DIREITA.getValor());
    }

    public void VisibilidadeCorpos()
    {

        if (generoParam.equals(Genero.FEMININO.getValor()))
        {
            rltvFeminino.setVisibility(View.VISIBLE);
            rltvMasculino.setVisibility(View.INVISIBLE);
        } else
        {
            rltvMasculino.setVisibility(View.VISIBLE);
            rltvFeminino.setVisibility(View.INVISIBLE);
        }
    }

}