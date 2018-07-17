package com.example.pefoce.peritolocal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import Dialogs.DesenhoThumbnailDialog;
import Enums.Genero;
import Model.Ocorrencia;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Model.Vida.OcorrenciaVida;
import Util.StringUtil;

import static Dialogs.AudioDialog.ocorrenciaVida;

public class GerenciarCorpo extends AppCompatActivity
{

    Button btnThumbnail;

    ArrayList<Lesao> listLesoes;
    List<LesaoEnvolvido> lesaoEnvolvidos;
    EnvolvidoVida envolvidoVida;

    Button btnVoltar;

    TextView txvNomeEnvolvido;

    RelativeLayout rltvCorpoFeminino;
    RelativeLayout rltvCorpoMasculino;


    TextView txvCabecaFeminina;
    TextView txvToraxFeminino;

    TextView txvBracosFemininos;

    TextView txvPernasFemininas;

    TextView txvCabecaMasculina;
    TextView txvToraxMasculino;

    TextView txvBracosMasculinos;

    TextView txvPernasMasculinas;

    String generoParam;

    Long ocorrenciaId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gerenciar_corpo);
        AssociarLayout();
        AssociarEventos();

        Intent it = getIntent();

        ocorrenciaId = it.getLongExtra("OcorrenciaId",0l);
        //generoParam = it.getStringExtra("GeneroEnvolvido");

        try
        {
            envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class, it.getLongExtra("EnvolvidoId", 0l));

            if(envolvidoVida!=null)
                txvNomeEnvolvido.setText(envolvidoVida.getNome());

            generoParam = envolvidoVida.getGenero().getValor();

            //if(envolvidoVida.getGenero()== Genero.FEMININO)
            if (generoParam.equals(Genero.FEMININO.getValor()))
                rltvCorpoFeminino.setVisibility(View.VISIBLE);
            else
                rltvCorpoMasculino.setVisibility(View.VISIBLE);

            lesaoEnvolvidos = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", envolvidoVida.getId().toString());

            listLesoes = new ArrayList<>();

            for (LesaoEnvolvido le : lesaoEnvolvidos)
                listLesoes.add(le.getLesao());

            CarregarLesoes();

//            ocorrenciaVida = OcorrenciaVida.findById(OcorrenciaVida.class,ocorrenciaId);
//
//            Ocorrencia ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());
//
//            String path = Environment.getExternalStorageDirectory() +
//                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome()
//                    + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() +
//                    "/Fotos_Envolvidos/" + envolvidoVida.getId().toString() + "_" + StringUtil.normalize(envolvidoVida.getNome()) + ".jpeg";
//
//
//            File imgFile = new File(path);
//            if (imgFile.exists())
//            {
////                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//
//                Picasso.with(ctx).load(imgFile).resize(150,100).into(imgvThumbnail);
//            }
//            else
//                Picasso.with(ctx).load(R.drawable.placeholder_error).into(imgvThumbnail);


        } catch (Exception e)
        {
            e.toString();
        }
    }

    public void AssociarLayout()
    {

        txvNomeEnvolvido = (TextView) findViewById(R.id.txv_Nome_Envolvido_Lesoes);

        btnVoltar = (Button) findViewById(R.id.btn_Voltar_corpo);
        rltvCorpoFeminino = (RelativeLayout) findViewById(R.id.rltv_Corpo_Feminino);
        rltvCorpoMasculino = (RelativeLayout) findViewById(R.id.rltv_Corpo_Masculino);

        txvBracosFemininos = (TextView) findViewById(R.id.txv_Bracos_Femininos);


        txvPernasFemininas = (TextView) findViewById(R.id.txv_Pernas_Femininas);

        txvCabecaFeminina = (TextView) findViewById(R.id.txv_Cabeca_Feminina);
        txvToraxFeminino = (TextView) findViewById(R.id.txv_Torax_Feminino);


        txvBracosMasculinos = (TextView) findViewById(R.id.txv_Bracos_Masculinos);

        txvPernasMasculinas = (TextView) findViewById(R.id.txv_Pernas_Masculinas);

        txvCabecaMasculina = (TextView) findViewById(R.id.txv_Cabeca_Masculina);
        txvToraxMasculino = (TextView) findViewById(R.id.txv_Torax_Masculino);

        btnThumbnail = (Button) findViewById(R.id.btn_Thumbnail_Desenho_Corpo);
    }

    public void CarregarLesoes()
    {
        if (generoParam.equals(Genero.FEMININO.getValor()))
        {
            for (Lesao l : listLesoes)
            {
                if (l != null)
                {
                    if (l.getParteCorpo() != null)
                    {
                        switch (l.getParteCorpo().getValor())
                        {
                            case "Cabeça":
                                txvCabecaFeminina.setTextColor(Color.RED);
                                break;
                            case "Tórax":
                                txvToraxFeminino.setTextColor(Color.RED);

                                break;
                            case "Pernas":
                                txvPernasFemininas.setTextColor(Color.RED);
                                break;
                            case "Braços":
                                txvBracosFemininos.setTextColor(Color.RED);
                                break;
                        }
                    }
                }
            }
        } else
        {
            for (Lesao l : listLesoes)
            {
                if (l != null)
                {
                    if (l.getParteCorpo() != null)
                    {
                        switch (l.getParteCorpo().getValor())
                        {
                            case "Cabeça":
                                txvCabecaMasculina.setTextColor(Color.RED);
                                break;
                            case "Tórax":
                                txvToraxMasculino.setTextColor(Color.RED);
                                break;
                            case "Pernas":
                                txvPernasMasculinas.setTextColor(Color.RED);
                                break;
                            case "Braços":
                                txvBracosMasculinos.setTextColor(Color.RED);
                                break;
                        }
                    }
                }
            }
        }

    }

    public void AssociarEventos()
    {
        btnVoltar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if(getIntent().getBooleanExtra("Conclusao",false))
                {
                    Intent it = new Intent(GerenciarCorpo.this, ManterPericiaVida.class);
                    it.putExtra("DiretoParaConclusao", true);
                    it.putExtra("EnvolvidoId", envolvidoVida.getId());
                    it.putExtra("OcorrenciaId",ocorrenciaId);
                    startActivity(it);
                    finish();
                }
                else
                    {
                        Intent it = new Intent(GerenciarCorpo.this, ManterPericiaVida.class);
                        it.putExtra("DiretoParaEnvolvido", true);
                        it.putExtra("EnvolvidoId", envolvidoVida.getId());
                        it.putExtra("OcorrenciaId", ocorrenciaId);
                        startActivity(it);
                        finish();
                    }
            }
        });
        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (v.getId() == R.id.txv_Bracos_Masculinos ||
                        v.getId() == R.id.txv_Bracos_Femininos)
                {
                    Intent itBraco = new Intent(GerenciarCorpo.this, GerenciarBracos.class);
                    itBraco.putExtra("EnvolvidoId", envolvidoVida.getId());
                    itBraco.putExtra("OcorrenciaId", ocorrenciaId);
                    startActivity(itBraco);
                    finish();
                }

                if (v.getId() == R.id.txv_Pernas_Masculinas ||
                        v.getId() == R.id.txv_Pernas_Femininas)
                {
                    Intent itPerna = new Intent(GerenciarCorpo.this, GerenciarPernas.class);
                    itPerna.putExtra("EnvolvidoId", envolvidoVida.getId());
                    itPerna.putExtra("OcorrenciaId", ocorrenciaId);
                    startActivity(itPerna);
                    finish();
                }

                if (v.getId() == R.id.txv_Cabeca_Feminina || v.getId() == R.id.txv_Cabeca_Masculina)
                {
                    Intent itCabeca = new Intent(GerenciarCorpo.this, GerenciarCabeca.class);
                    itCabeca.putExtra("EnvolvidoId", envolvidoVida.getId());
                    itCabeca.putExtra("OcorrenciaId", ocorrenciaId);
                    startActivity(itCabeca);
                    finish();
                }

                if (v.getId() == R.id.txv_Torax_Feminino || v.getId() == R.id.txv_Torax_Masculino)
                {
                    Intent itTorax = new Intent(GerenciarCorpo.this, GerenciarTorax.class);
                    itTorax.putExtra("EnvolvidoId", envolvidoVida.getId());
                    //   itTorax.putExtra("GeneroEnvolvido", generoParam);
                    itTorax.putExtra("OcorrenciaId", ocorrenciaId);
                    startActivity(itTorax);
                    finish();
                }
            }
        };

        btnThumbnail.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DesenhoThumbnailDialog.criarDialog(GerenciarCorpo.this,envolvidoVida, ocorrenciaId);
            }
        });

        txvBracosMasculinos.setOnClickListener(listener);

        txvPernasMasculinas.setOnClickListener(listener);

        txvToraxMasculino.setOnClickListener(listener);

        txvCabecaMasculina.setOnClickListener(listener);

        txvBracosFemininos.setOnClickListener(listener);

        txvPernasFemininas.setOnClickListener(listener);

        txvToraxFeminino.setOnClickListener(listener);

        txvCabecaFeminina.setOnClickListener(listener);

    }


    @Override
    public void onBackPressed()
    {

    }


}
