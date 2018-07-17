package com.example.pefoce.peritolocal;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Environment;
import android.os.Bundle;
import android.support.v4.view.MarginLayoutParamsCompat;
import android.text.format.DateFormat;
import android.view.Display;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.raed.drawingview.DrawingView;
import com.raed.drawingview.brushes.BrushSettings;
import com.raed.drawingview.brushes.Brushes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import Enums.CategoriaFoto;
import Enums.Genero;
import Model.Foto;
import Model.Ocorrencia;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Util.ImageUtil;
import Util.StringUtil;

public class DesenhoEnvolvidoActivity extends Activity
{
    boolean isApagando = true;
    Button btnSalvar = null;
    Button btnDesfazer = null;
    Button btnRefazer = null;
    Button btnCaneta = null;
    Button btnLimpar = null;
    Button btnEsconder = null;
    Button btnVoltar = null;
    LinearLayout llFerramentas = null;

    AnimatorSet mAnimationSet;

    DrawingView drawingView = null;
    BrushSettings brushSettings = null;

    Foto foto;
    OcorrenciaVidaFoto ocorrenciaVidaFoto;

    String generoParam;
    EnvolvidoVida envolvidoVida;
    OcorrenciaVida ocorrenciaVida;
    Ocorrencia ocorrencia;

    private void AssociarLayout()
    {
        drawingView = (DrawingView) findViewById(R.id.dv_Corpo);
        btnCaneta = (Button) findViewById(R.id.btn_draw_Caneta_Borracha);
        btnSalvar = (Button) findViewById(R.id.btn_draw_Salvar);
        btnDesfazer = (Button) findViewById(R.id.btn_draw_Desfazer);
        btnRefazer = (Button) findViewById(R.id.btn_draw_Refazer);
        btnLimpar = (Button) findViewById(R.id.btn_draw_Limpar);
        btnVoltar = (Button) findViewById(R.id.btn_draw_Voltar);
        btnEsconder = (Button) findViewById(R.id.btn_Esconder_Opcoes);
        llFerramentas = (LinearLayout) findViewById(R.id.ll_Opcoes_Draw);

        Bitmap bgAuxiliar;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        if(width < 1300)
        {
            if (generoParam.equals( Genero.FEMININO.getValor()))
                bgAuxiliar = BitmapFactory.decodeResource(getResources(),
                        R.drawable.corpo_feminino_draw_1024).copy(Bitmap.Config.ARGB_8888, true);

            else
                bgAuxiliar = BitmapFactory.decodeResource(getResources(),
                        R.drawable.corpo_masculino_draw_1024).copy(Bitmap.Config.ARGB_8888, true);
        }
        else
        {
            if (generoParam.equals(Genero.FEMININO.getValor()))
                bgAuxiliar = BitmapFactory.decodeResource(getResources(),
                        R.drawable.corpo_feminino_draw_1920).copy(Bitmap.Config.ARGB_8888, true);

            else
                bgAuxiliar = BitmapFactory.decodeResource(getResources(),
                        R.drawable.corpo_masculino_draw_1920).copy(Bitmap.Config.ARGB_8888, true);
        }


        if (envolvidoVida != null)
            drawingView.setBackgroundImage(ImageUtil.escreverNomeDesenho(bgAuxiliar, StringUtil.checkValue(envolvidoVida.getNome(), 40, "(Sem nome)")));

        drawingView.setUndoAndRedoEnable(true);

        brushSettings = drawingView.getBrushSettings();

    }

    private void AssociarEventos()
    {
        btnEsconder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(mAnimationSet != null && mAnimationSet.isRunning())
                    return;

                ObjectAnimator fadeOut = ObjectAnimator.ofFloat(llFerramentas, "alpha",  1f, 0f);
                fadeOut.setDuration(500);

                ObjectAnimator fadeIn = ObjectAnimator.ofFloat(llFerramentas, "alpha", 0f, 1f);
                fadeIn.setDuration(500);

                mAnimationSet = new AnimatorSet();

                if(btnEsconder.getText().equals("Esconder"))
                {
                    mAnimationSet.play(fadeOut);

                    mAnimationSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            llFerramentas.setVisibility(View.INVISIBLE);
                        }
                    });
                    mAnimationSet.start();

                    btnEsconder.setText("Mostrar");
                }
                else
                {
                    llFerramentas.setVisibility(View.VISIBLE);

                    mAnimationSet.play(fadeIn);

                    mAnimationSet.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            llFerramentas.setVisibility(View.VISIBLE);
                        }
                    });
                    mAnimationSet.start();
                    btnEsconder.setText("Esconder");
                }
            }
        });

        btnLimpar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                drawingView.clear();
            }
        });

        btnRefazer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                drawingView.redo();
            }
        });

        btnDesfazer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                drawingView.undo();
            }
        });

        btnSalvar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO: salvar esta imagem como anexo de fotografia e fazer um dialog de confirmação de save
                String path = Environment.getExternalStorageDirectory() +
                        "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome()
                        + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() +
                        "/Fotos_Envolvidos/";

                File folder = new File(path);
                if (!folder.exists())
                    folder.mkdirs();

//                String extraPath = "mapa_lesoes"+ DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";

                String extraPath =  envolvidoVida.getId().toString()+"_"+StringUtil.normalize(envolvidoVida.getNome()) + ".jpeg";

                Bitmap bmp = drawingView.exportDrawing();

                FileOutputStream out = null;
                try
                {
//                    File f = new File(path, extraPath);
                    out = new FileOutputStream(path + extraPath, false);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);

                } catch (Exception e)
                {
                    e.printStackTrace();
                } finally
                {
                    try
                    {
                        if (out != null)
                        {
                            out.close();
                            foto = new Foto("Mapa das lesões", path + extraPath, CategoriaFoto.DESENHO);
                            foto.save();

                            ocorrenciaVidaFoto = new OcorrenciaVidaFoto(ocorrenciaVida, foto);
                            ocorrenciaVidaFoto.save();
                        }
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
                btnVoltar.performClick();
            }
        });


        btnCaneta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (isApagando)
                {
                    brushSettings.setSelectedBrush(Brushes.PEN);

                    brushSettings.setSelectedBrushSize(0.05f);

                    brushSettings.setColor(Color.RED);

                    btnCaneta.setText("Caneta");

                    btnCaneta.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_icon_caneta, 0, 0, 0);

                    isApagando = false;

                } else
                {
                    brushSettings.setSelectedBrush(Brushes.ERASER);

                    brushSettings.setSelectedBrushSize(0.35f);

                    btnCaneta.setText("Borracha");

                    btnCaneta.setCompoundDrawablesWithIntrinsicBounds(R.drawable.resized_icon_borracha, 0, 0, 0);

                    isApagando = true;
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
                    Intent it = new Intent(DesenhoEnvolvidoActivity.this, ManterPericiaVida.class);
                    it.putExtra("DiretoParaConclusao", true);
                    it.putExtra("EnvolvidoId", envolvidoVida.getId());
                    it.putExtra("OcorrenciaId", ocorrenciaVida.getId());
                    startActivity(it);
                    finish();
                } else
                {
                    Intent it = new Intent(DesenhoEnvolvidoActivity.this, ManterPericiaVida.class);
                    it.putExtra("DiretoParaEnvolvido", true);
                    it.putExtra("EnvolvidoId", envolvidoVida.getId());
                    it.putExtra("OcorrenciaId", ocorrenciaVida.getId());
                    startActivity(it);
                    finish();
                }
//                drawingView.setdrawing
            }
        });
    }

    @Override
    protected void onPause()
    {
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        Intent it = getIntent();

        envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class, it.getLongExtra("EnvolvidoId", 0l));

        ocorrenciaVida = OcorrenciaVida.findById(OcorrenciaVida.class, it.getLongExtra("OcorrenciaId", 0l));

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

        generoParam = it.getStringExtra("GeneroEnvolvido");

        AssociarLayout();

        AssociarEventos();

        btnCaneta.performClick();
    }


}

