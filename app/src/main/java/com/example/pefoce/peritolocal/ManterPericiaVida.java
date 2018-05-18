package com.example.pefoce.peritolocal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

import Fragments.FragmentsVida.GerenciarConclusaoVida;
import Fragments.FragmentsVida.GerenciarEnderecoVida;
import Fragments.FragmentsVida.GerenciarEnvolvidoVida;
import Fragments.FragmentsVida.GerenciarFotosVida;
import Fragments.FragmentsVida.GerenciarOcorrenciaVida;
import Fragments.FragmentsVida.GerenciarVestigioVida;
import Model.Ocorrencia;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.OcorrenciaVida;

public class ManterPericiaVida extends AppCompatActivity implements StepperLayout.StepperListener
{
    public ImageButton imgbSair;
    public StepperLayout stepperLayout;
    public OcorrenciaVida ocorrenciaVida;
    public TextView txvToolbarTitulo;
    public Toolbar toolbar = null;
    public RelativeLayout rltvContent;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_pericia_vida);

        it = getIntent();


        AssociarLayout();

        toolbar.setTitle("");

        stepperLayout.setAdapter(new StepperAdapterVida(getSupportFragmentManager(),this));

        stepperLayout.setListener(this);

        txvToolbarTitulo.setText("Ocorrência Vida");

        setSupportActionBar(toolbar);

        CarregarOcorrencia();

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void CarregarOcorrencia()
    {
        Bundle args = new Bundle();


        if(it.getLongExtra("OcorrenciaId",0)!=0)
        {
            args.putLong("OcorrenciaId",it.getLongExtra("OcorrenciaId",0));
            ocorrenciaVida = OcorrenciaVida.findById(OcorrenciaVida.class,it.getLongExtra("OcorrenciaId",0));
        }
        if (it.getBooleanExtra("DiretoParaEnvolvido", false))
        {
            try
            {
                EnvolvidoVida envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class, it.getLongExtra("EnvolvidoId", 0));
                OcorrenciaEnvolvidoVida ocorrenciaEnvolvidoVida = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "envolvido_vida = ?", envolvidoVida.getId().toString()).get(0);
                ocorrenciaVida = ocorrenciaEnvolvidoVida.getOcorrenciaVida();
            }catch (Exception e)
            {}
            //ocorrenciaVida = OcorrenciaVida.find(OcorrenciaVida.class,"")
            stepperLayout.setCurrentStepPosition(2);
            return;
        }
        if (it.getBooleanExtra("DiretoParaConclusao", false))
        {
            stepperLayout.setCurrentStepPosition(5);
            return;
        }
            switch (it.getStringExtra("FragmentPicker"))
            {
                case "Ocorrência":
                    stepperLayout.setCurrentStepPosition(0);
                    break;
                case "Endereço":
                    stepperLayout.setCurrentStepPosition(1);
                    break;
                case "Vítima":
                    stepperLayout.setCurrentStepPosition(2);
                    break;
                case "Vestígio":
                    stepperLayout.setCurrentStepPosition(3);
                    break;
                case "Foto":
                    stepperLayout.setCurrentStepPosition(4);
                    break;
                default:
                    stepperLayout.setCurrentStepPosition(0);
                    break;
            }
    }

    public void AssociarLayout()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar_pericia_vida);
        stepperLayout = (StepperLayout) findViewById(R.id.stpl_Vida);
        txvToolbarTitulo = (TextView) toolbar.findViewById(R.id.txv_toolbar_Titulo_Vida);
        imgbSair = (ImageButton) toolbar.findViewById(R.id.imgb_Sair_Vida);
        rltvContent = (RelativeLayout) findViewById(R.id.rltv_Pericia_Vida);

        imgbSair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogSair("Você deseja sair da perícia?");
            }
        });
    }


    @Override
    public void onCompleted(View completeButton)
    {
        DialogSair("Você deseja finalizar a perícia?");
    }

    @Override
    public void onError(VerificationError verificationError)
    {

    }

    @Override
    public void onStepSelected(int newStepPosition)
    {

    }

    @Override
    public void onReturn()
    {

    }


    @Override
    public void onBackPressed()
    {

    }

    private void DialogSair(String mensagem)
    {

        AlertDialog.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);

        else
            builder = new AlertDialog.Builder(this);

        builder.setTitle("Voltar")
                .setMessage(mensagem)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        Intent it = new Intent(getApplicationContext(),MainActivity.class);
                        Ocorrencia ocorrencia = Ocorrencia.findById(Ocorrencia.class,ocorrenciaVida.getOcorrenciaID());

                        if(ocorrencia!=null)
                            it.putExtra("PeritoId",ocorrencia.getPerito().getId());

                        startActivity(it);
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
    }

    public class StepperAdapterVida extends AbstractFragmentStepAdapter
    {
        private StepperAdapterVida(android.support.v4.app.FragmentManager fm, Context context)
        {
            super(fm, context);
        }

        @Override
        public Step createStep(int position)
        {
            Bundle bd = new Bundle();
            bd.putInt("teste", position);
            bd.putLong("OcorrenciaId", ocorrenciaVida.getId());

            if (it.getBooleanExtra("DiretoParaEnvolvido", false))
            {
                bd.putLong("EnvolvidoId", it.getLongExtra("EnvolvidoId", 0));
                bd.putBoolean("DiretoParaEnvolvido", true);
            }


            switch (position)
            {
                case 0:
                    GerenciarOcorrenciaVida stepOcorrencia = new GerenciarOcorrenciaVida();
                    stepOcorrencia.setArguments(bd);
                    return stepOcorrencia;
                case 1:
                    GerenciarEnderecoVida stepEndereco = new GerenciarEnderecoVida();
                    stepEndereco.setArguments(bd);
                    return stepEndereco;
                case 2:
                    final GerenciarEnvolvidoVida stepEnvolvido= new GerenciarEnvolvidoVida();
                    stepEnvolvido.setArguments(bd);
                    return stepEnvolvido;
                case 3:
                    GerenciarVestigioVida stepVestigio = new GerenciarVestigioVida();
                    stepVestigio.setArguments(bd);
                    return stepVestigio;
                case 4:
                    GerenciarFotosVida stepFotos = new GerenciarFotosVida();
                    stepFotos.setArguments(bd);
                    return stepFotos;
                case 5:
                    GerenciarConclusaoVida stepConclusao = new GerenciarConclusaoVida();
                    stepConclusao.setArguments(bd);
                    return stepConclusao;
                default:
                    return null;

            }

        }
        @Override
        public int getCount()
        {
            return 6;
        }
    }





}
