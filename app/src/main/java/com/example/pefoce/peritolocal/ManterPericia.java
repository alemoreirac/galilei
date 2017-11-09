package com.example.pefoce.peritolocal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

import Enums.AtoresColisao;
import Fragments.GerenciarColisoes;
import Fragments.GerenciarConclusao;
import Fragments.GerenciarEndereco;
import Fragments.GerenciarEnvolvido;
import Fragments.GerenciarFotos;
import Fragments.GerenciarVeiculo;
import Fragments.OcorrenciaFragment;
import Model.Ocorrencia;
import Model.OcorrenciaFoto;
import Model.OcorrenciaTransito;
import Model.Pessoa;
import Util.TempoUtil;
import Util.ViewUtil;

public class ManterPericia extends AppCompatActivity implements StepperLayout.StepperListener
{
    public ImageButton imgbSair;
    public StepperLayout stepperLayout;
    public OcorrenciaTransito ocorrenciaTransito;
    public TextView txvToolbarTitulo;
    public Toolbar toolbar = null;
    Intent it = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_manter_pericia);


        AssociarLayout();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        it = getIntent();


        stepperLayout.setAdapter(new ManterPericia.MyStepperAdapter(getSupportFragmentManager(), this));
        //stepperLayout.
        stepperLayout.setListener(this);

        CarregarOcorencia();
    }

    public void AssociarLayout()
    {
        toolbar = (Toolbar) findViewById(R.id.toolbar_pericia);
        stepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        txvToolbarTitulo = (TextView) toolbar.findViewById(R.id.txv_toolbar_Titulo);
        imgbSair = (ImageButton) toolbar.findViewById(R.id.imgb_Sair);
        final Activity activity = this;

        imgbSair.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DialogSair("Você deseja encerrar a perícia?");
            }
        });
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
                        Ocorrencia ocorrencia = Ocorrencia.findById(Ocorrencia.class,ocorrenciaTransito.getOcorrenciaID());
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


    @Override
    public void onCompleted(View completeButton)
    {
        DialogSair("Você deseja finalizar a perícia?");
    }


    @Override
    public void onError(VerificationError verificationError)
    {
        Toast.makeText(this, "Ocorreu algum erro", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStepSelected(int newStepPosition)
    {
        //Toast.makeText(this, "selected!", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onReturn()
    {
        Toast.makeText(this, "return", Toast.LENGTH_SHORT).show();
    }


    public class MyStepperAdapter extends AbstractFragmentStepAdapter
    {


        private MyStepperAdapter(android.support.v4.app.FragmentManager fm, Context context)
        {
            super(fm, context);

        }

        @Override
        public Step createStep(int position)
        {

            Bundle bd = new Bundle();
            bd.putInt("teste", position);
            bd.putLong("OcorrenciaId", ocorrenciaTransito.getId());

            if (it.getBooleanExtra("DiretoParaVeiculo", false))
            {
                bd.putLong("VeiculoId", it.getLongExtra("VeiculoId", 0));
                bd.putBoolean("DiretoParaVeiculo", true);
            }

            switch (position)
            {
                case 0:
                    OcorrenciaFragment stepOcorrencia = new OcorrenciaFragment();
                    stepOcorrencia.setArguments(bd);
                    return stepOcorrencia;
                case 1:
                    GerenciarEndereco stepEndereco = new GerenciarEndereco();
                    stepEndereco.setArguments(bd);
                    return stepEndereco;
                case 2:
                    final GerenciarVeiculo stepVeiculo = new GerenciarVeiculo();
                    stepVeiculo.setArguments(bd);
                    return stepVeiculo;
                case 3:
                    GerenciarEnvolvido stepEnvolvido = new GerenciarEnvolvido();
                    stepEnvolvido.setArguments(bd);
                    return stepEnvolvido;
                case 4:
                    final GerenciarColisoes stepColisao = new GerenciarColisoes();
                    stepColisao.setArguments(bd);
                    return stepColisao;
                case 5:
                    final GerenciarFotos stepFoto = new GerenciarFotos();
                    stepFoto.setArguments(bd);
                    return stepFoto;
                case 6:
                    final GerenciarConclusao stepConclusao = new GerenciarConclusao();
                    stepConclusao.setArguments(bd);
                    return stepConclusao;
                default:
                    return null;

            }

        }

        @Override
        public int getCount()
        {
            return 7;
        }
    }

    private void CarregarOcorencia()
    {

        Bundle args = new Bundle();

        if (it.getLongExtra("OcorrenciaId", 0) != 0)
        {
            args.putLong("OcorrenciaId", it.getLongExtra("OcorrenciaId", 0));
            ocorrenciaTransito = OcorrenciaTransito.findById(OcorrenciaTransito.class, it.getLongExtra("OcorrenciaId", 0));
//            Gson gson = new Gson();
//            gson.toJson(ocorrenciaTransito);
        } else
        {
            ocorrenciaTransito = new OcorrenciaTransito();
            ocorrenciaTransito.save();
        }

        if (it.getBooleanExtra("DiretoParaVeiculo", false))
        {
            stepperLayout.setCurrentStepPosition(2);
            return;
        }

        if (it.getStringExtra("FragmentPicker") != null)
        {
            switch (it.getStringExtra("FragmentPicker"))
            {
                case "Endereço":
                    stepperLayout.setCurrentStepPosition(1);
                    break;
                case "Veículo":
                    stepperLayout.setCurrentStepPosition(2);
                    break;
                case "Vítima":
                    stepperLayout.setCurrentStepPosition(3);
                    break;
                case "Foto":
                    stepperLayout.setCurrentStepPosition(5);
                    break;
                case "Conclusão":
                    stepperLayout.setCurrentStepPosition(6);
                    break;
                default:
                    stepperLayout.setCurrentStepPosition(0);
                    break;
            }
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void TrocaHora(View v)
    {
        final TextView txv;

        switch (v.getId())
        {
            case R.id.txv_Hora_Atendimento_Valor:
                txv = (TextView) v.findViewById(R.id.txv_Hora_Atendimento_Valor);
                TempoUtil.setTime(txv, this);

                break;

            case R.id.txv_HoraChamado:
                txv = (TextView) v.findViewById(R.id.txv_HoraChamado);
                TempoUtil.setTime(txv, this);

                break;

            case R.id.txv_HoraOcorrencia:
                txv = (TextView) v.findViewById(R.id.txv_HoraOcorrencia);
                TempoUtil.setTime(txv, this);

                break;
            default:
                break;
        }
    }

    public void TrocaData(View v)
    {
        final TextView txv;
        switch (v.getId())
        {
            case R.id.txv_Data_Atendimento_Valor:
                txv = (TextView) v.findViewById(R.id.txv_Data_Atendimento_Valor);
                TempoUtil.setDate(txv, this);
                break;

            case R.id.txv_DataNascimento_Envolvido_Valor:
                txv = (TextView) v.findViewById(R.id.txv_DataNascimento_Envolvido_Valor);
                TempoUtil.setDate(txv, this);
                break;
        }
    }


    @Override
    public void onBackPressed()
    {
//        Intent it = new Intent(this, MainActivity.class);
//        startActivity(it);
    }


}
