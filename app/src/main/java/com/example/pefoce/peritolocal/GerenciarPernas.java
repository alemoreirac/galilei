package com.example.pefoce.peritolocal;

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
import Enums.Vida.LesoesEnabled;
import Enums.Vida.ParteCorpo;
import Enums.Vida.Secao;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Util.BuscadorEnum;
import Util.ViewUtil;

public class GerenciarPernas extends AppCompatActivity
{
    TextView txvNomeEnvolvido;

    TextView txvSecaoSuperiorCoxaEsquerdaMasculina;
    TextView txvSecaoInferiorCoxaEsquerdaMasculina;
    TextView txvJoelhoEsquerdoMasculino;
    TextView txvSecaoSuperiorPernaEsquerdaMasculina;
    TextView txvSecaoInferiorPernaEsquerdaMasculina;
    TextView txvPeEsquerdoMasculino;

    TextView txvSecaoSuperiorCoxaDireitaMasculina;
    TextView txvSecaoInferiorCoxaDireitaMasculina;
    TextView txvJoelhoDireitoMasculino;
    TextView txvSecaoSuperiorPernaDireitaMasculina;
    TextView txvSecaoInferiorPernaDireitaMasculina;
    TextView txvPeDireitoMasculino;

    TextView txvSecaoSuperiorCoxaEsquerdaFeminina;
    TextView txvSecaoInferiorCoxaEsquerdaFeminina;
    TextView txvJoelhoEsquerdoFeminino;
    TextView txvSecaoSuperiorPernaEsquerdaFeminina;
    TextView txvSecaoInferiorPernaEsquerdaFeminina;
    TextView txvPeEsquerdoFeminino;

    TextView txvSecaoSuperiorCoxaDireitaFeminina;
    TextView txvSecaoInferiorCoxaDireitaFeminina;
    TextView txvJoelhoDireitoFeminino;
    TextView txvSecaoSuperiorPernaDireitaFeminina;
    TextView txvSecaoInferiorPernaDireitaFeminina;
    TextView txvPeDireitoFeminino;

    EnvolvidoVida envolvidoVida;

    Button btnVoltar;

    ArrayList<Lesao> lesoesList;
    List<LesaoEnvolvido> lesoesEnvolvidos;

    String generoParam;

    RelativeLayout rltvMasculino;
    RelativeLayout rltvFeminino;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_pernas);
        Intent it = getIntent();

        //generoParam = it.getStringExtra("GeneroEnvolvido");

        AssociarLayout();
        AssociarEventos();
        try
        {
            envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class,it.getLongExtra("EnvolvidoId",0));

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
                        if(le.getLesao().getParteCorpo()== ParteCorpo.PERNAS )
                            lesoesList.add(le.getLesao());
                    }
                }
            }

        }catch (Exception e)
        {
            Intent itException = new Intent(this,GerenciarCorpo.class);
            startActivity(itException);
            Toast.makeText(this, "Erro, envolvido inválido", Toast.LENGTH_LONG).show();
        }
        VisibilidadeCorpos();
        AtualizarLesoes();
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
                    if (le.getLesao().getParteCorpo() == ParteCorpo.PERNAS)
                        lesoesList.add(le.getLesao());
                }
            }
        }

        if (generoParam.equals(Genero.FEMININO.getValor()))
            ViewUtil.ColorirCamposLesoes(rltvFeminino, lesoesList, getResources().getColor(R.color.colorPrimary));
        else
            ViewUtil.ColorirCamposLesoes(rltvMasculino, lesoesList, getResources().getColor(R.color.colorPrimary));
    }

    public void AssociarEventos()
    {
        btnVoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent it = new Intent(GerenciarPernas.this,GerenciarCorpo.class);
                it.putExtra("EnvolvidoId",envolvidoVida.getId());
             //   it.putExtra("GeneroEnvolvido",generoParam);
                startActivity(it);
                finish();
            }
        });
    }
    public void AddLesaoPerna(View v)
    {
        TextView txvView = (TextView) v;
//        LesaoDialog lesaoDialog = new LesaoDialog(GerenciarPernas.this,envolvidoVida, BuscadorEnum.BuscarSecao(txvView.getText().toString()));
        LesaoDialog.show(GerenciarPernas.this, envolvidoVida, BuscadorEnum.BuscarSecao(txvView.getText().toString()), LesoesEnabled.TODOS);
    }



    public void AssociarLayout()
    {
        btnVoltar = (Button) findViewById(R.id.btn_Voltar_pernas);

        txvNomeEnvolvido = (TextView) findViewById(R.id.txv_Nome_Envolvido_Lesoes);


        rltvMasculino = (RelativeLayout) findViewById(R.id.rltv_Pernas_Masculinas);
        rltvFeminino = (RelativeLayout) findViewById(R.id.rltv_Pernas_Femininas);

        txvSecaoSuperiorCoxaEsquerdaMasculina = (TextView) findViewById(R.id.txv_Secao_Superior_Coxa_Esquerda_Masculina);
        txvSecaoInferiorCoxaEsquerdaMasculina = (TextView) findViewById(R.id.txv_Secao_Inferior_Coxa_Esquerda_Masculina);
        txvJoelhoEsquerdoMasculino = (TextView) findViewById(R.id.txv_Joelho_Esquerdo_Masculino);
        txvSecaoSuperiorPernaEsquerdaMasculina = (TextView) findViewById(R.id.txv_Secao_Superior_Perna_Esquerda_Masculina);
        txvSecaoInferiorPernaEsquerdaMasculina = (TextView) findViewById(R.id.txv_Secao_Inferior_Perna_Esquerda_Masculina);
        txvPeEsquerdoMasculino = (TextView) findViewById(R.id.txv_Pe_Esquerdo_Masculino);

        txvSecaoSuperiorCoxaDireitaMasculina = (TextView) findViewById(R.id.txv_Secao_Superior_Coxa_Direita_Masculina);
        txvSecaoInferiorCoxaDireitaMasculina = (TextView) findViewById(R.id.txv_Secao_Inferior_Coxa_Direita_Masculina);
        txvJoelhoDireitoMasculino = (TextView) findViewById(R.id.txv_Joelho_Direito_Masculino);
        txvSecaoSuperiorPernaDireitaMasculina = (TextView) findViewById(R.id.txv_Secao_Superior_Perna_Direita_Masculina);
        txvSecaoInferiorPernaDireitaMasculina = (TextView) findViewById(R.id.txv_Secao_Inferior_Perna_Direita_Masculina);
        txvPeDireitoMasculino = (TextView) findViewById(R.id.txv_Pe_Direito_Masculino);

        txvSecaoSuperiorCoxaEsquerdaFeminina = (TextView) findViewById(R.id.txv_Secao_Superior_Coxa_Esquerda_Feminina);
        txvSecaoInferiorCoxaEsquerdaFeminina = (TextView) findViewById(R.id.txv_Secao_Inferior_Coxa_Esquerda_Feminina);
        txvJoelhoEsquerdoFeminino = (TextView) findViewById(R.id.txv_Joelho_Esquerdo_Feminino);
        txvSecaoSuperiorPernaEsquerdaFeminina = (TextView) findViewById(R.id.txv_Secao_Superior_Perna_Esquerda_Feminina);
        txvSecaoInferiorPernaEsquerdaFeminina = (TextView) findViewById(R.id.txv_Secao_Inferior_Perna_Esquerda_Feminina);
        txvPeEsquerdoFeminino = (TextView) findViewById(R.id.txv_Pe_Esquerdo_Feminino);

        txvSecaoSuperiorCoxaDireitaFeminina = (TextView) findViewById(R.id.txv_Secao_Superior_Coxa_Direita_Feminina);
        txvSecaoInferiorCoxaDireitaFeminina = (TextView) findViewById(R.id.txv_Secao_Inferior_Coxa_Direita_Feminina);
        txvJoelhoDireitoFeminino = (TextView) findViewById(R.id.txv_Joelho_Direito_Feminino);
        txvSecaoSuperiorPernaDireitaFeminina = (TextView) findViewById(R.id.txv_Secao_Superior_Perna_Direita_Feminina);
        txvSecaoInferiorPernaDireitaFeminina = (TextView) findViewById(R.id.txv_Secao_Inferior_Perna_Direita_Feminina);
        txvPeDireitoFeminino = (TextView) findViewById(R.id.txv_Pe_Direito_Feminino);

        // -- associar valores

        txvSecaoSuperiorCoxaEsquerdaMasculina.setText(Secao.SETOR_SUPERIOR_COXA_ESQUERDA.getValor());
        txvSecaoInferiorCoxaEsquerdaMasculina.setText(Secao.SETOR_INFERIOR_COXA_ESQUERDA.getValor());
        txvJoelhoEsquerdoMasculino.setText(Secao.JOELHO_ESQUERDO.getValor());
        txvSecaoSuperiorPernaEsquerdaMasculina.setText(Secao.SETOR_SUPERIOR_PERNA_ESQUERDA.getValor());
        txvSecaoInferiorPernaEsquerdaMasculina.setText(Secao.SETOR_INFERIOR_PERNA_ESQUERDA.getValor());
        txvPeEsquerdoMasculino.setText(Secao.PE_ESQUERDO.getValor());

        txvSecaoSuperiorCoxaDireitaMasculina.setText(Secao.SETOR_SUPERIOR_COXA_DIREITA.getValor());
        txvSecaoInferiorCoxaDireitaMasculina.setText(Secao.SETOR_INFERIOR_COXA_DIREITA.getValor());
        txvJoelhoDireitoMasculino.setText(Secao.JOELHO_DIREITO.getValor());
        txvSecaoSuperiorPernaDireitaMasculina.setText(Secao.SETOR_SUPERIOR_PERNA_DIREITA.getValor());
        txvSecaoInferiorPernaDireitaMasculina.setText(Secao.SETOR_INFERIOR_PERNA_DIREITA.getValor());
        txvPeDireitoMasculino.setText(Secao.PE_DIREITO.getValor());

        txvSecaoSuperiorCoxaEsquerdaFeminina.setText(Secao.SETOR_SUPERIOR_COXA_ESQUERDA.getValor());
        txvSecaoInferiorCoxaEsquerdaFeminina.setText(Secao.SETOR_INFERIOR_COXA_ESQUERDA.getValor());
        txvJoelhoEsquerdoFeminino.setText(Secao.JOELHO_ESQUERDO.getValor());
        txvSecaoSuperiorPernaEsquerdaFeminina.setText(Secao.SETOR_SUPERIOR_PERNA_ESQUERDA.getValor());
        txvSecaoInferiorPernaEsquerdaFeminina.setText(Secao.SETOR_INFERIOR_PERNA_ESQUERDA.getValor());
        txvPeEsquerdoFeminino.setText(Secao.PE_ESQUERDO.getValor());

        txvSecaoSuperiorCoxaDireitaFeminina.setText(Secao.SETOR_SUPERIOR_COXA_DIREITA.getValor());
        txvSecaoInferiorCoxaDireitaFeminina.setText(Secao.SETOR_INFERIOR_COXA_DIREITA.getValor());
        txvJoelhoDireitoFeminino.setText(Secao.JOELHO_DIREITO.getValor());
        txvSecaoSuperiorPernaDireitaFeminina.setText(Secao.SETOR_SUPERIOR_PERNA_DIREITA.getValor());
        txvSecaoInferiorPernaDireitaFeminina.setText(Secao.SETOR_INFERIOR_PERNA_DIREITA.getValor());
        txvPeDireitoFeminino.setText(Secao.PE_DIREITO.getValor());

    }

    public void VisibilidadeCorpos()
    {
        if (envolvidoVida.getGenero() == Genero.FEMININO)
        {
            rltvFeminino.setVisibility(View.VISIBLE);
            rltvMasculino.setVisibility(View.INVISIBLE);
        } else
        {
            rltvMasculino.setVisibility(View.VISIBLE);
            rltvFeminino.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onBackPressed()
    {

    }

}
