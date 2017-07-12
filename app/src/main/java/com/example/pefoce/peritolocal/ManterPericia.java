package com.example.pefoce.peritolocal;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;

import java.util.List;

import Fragments.ColisoesFragment;
import Fragments.EnderecosFragment;
import Fragments.OcorrenciaFragment;
import Fragments.VeiculoFragment;
import Fragments.VitimasFragment;
import Model.OcorrenciaTransito;
import Util.TempoUtil;

public class ManterPericia extends AppCompatActivity implements StepperLayout.StepperListener{

    public StepperLayout stepperLayout;
    public OcorrenciaTransito ocorrenciaTransito;
    public Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manter_pericia);

        toolbar = (Toolbar) findViewById(R.id.toolbar_pericia);
        setSupportActionBar(toolbar);

        stepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        stepperLayout.setAdapter(new ManterPericia.MyStepperAdapter(getSupportFragmentManager(), this));
        stepperLayout.setListener(this);


        CarregarOcorencia();

    }


    @Override
    public void onCompleted(View completeButton) {
        Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(VerificationError verificationError) {
        Toast.makeText(this, verificationError.getErrorMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {


        Toast.makeText(this, "selected!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturn() {
        Toast.makeText(this, "return", Toast.LENGTH_SHORT).show();
    }


    public   class MyStepperAdapter extends AbstractFragmentStepAdapter
    {


        private   MyStepperAdapter(android.support.v4.app.FragmentManager fm, Context context)
        {
            super(fm, context);
        }

        @Override
        public Step createStep(int position)
        {

            Bundle bd = new Bundle();
            bd.putInt("teste", position);
            bd.putLong("OcorrenciaId",ocorrenciaTransito.getId());

            switch(position)
            {
                case 0:
                    OcorrenciaFragment stepOcorrencia = new OcorrenciaFragment();
                    stepOcorrencia.setArguments(bd);
                    return stepOcorrencia;

                case 1:
                      EnderecosFragment stepEndereco = new EnderecosFragment();
                     stepEndereco.setArguments(bd);
                    return stepEndereco;

                case 2:
                      VitimasFragment stepVitima = new VitimasFragment();
                     stepVitima.setArguments(bd);
                    return stepVitima;
                case 3:
                    final VeiculoFragment stepVeiculo = new VeiculoFragment();
                    stepVeiculo.setArguments(bd);
                    return stepVeiculo;
                case 4:
                    final ColisoesFragment stepColisao = new ColisoesFragment();
                    stepColisao.setArguments(bd);
                    return stepColisao;
                default:
                    return null;

            }

        }




        @Override
        public int getCount()
        {
            return 5;
        }

    }


    private void CarregarOcorencia()
    {
        Intent it = getIntent();

        Bundle args = new Bundle();

        if(it.getLongExtra("OcorrenciaId",0) != 0)
        {
            args.putLong("OcorrenciaId", it.getLongExtra("OcorrenciaId", 0));
            ocorrenciaTransito = OcorrenciaTransito.findById(OcorrenciaTransito.class, it.getLongExtra("OcorrenciaId", 0));
        }

        if(it.getStringExtra("FragmentPicker")!=null)
        {
            switch (it.getStringExtra("FragmentPicker"))
            {
                case "Endereço" :
                    stepperLayout.setCurrentStepPosition(1);
                    break;
                case "Vítima" :
                    stepperLayout.setCurrentStepPosition(2);
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
        final TextView txv ;

        switch (v.getId())
        {
            case R.id.txv_Hora_Atendimento_Valor:
                txv = (TextView)v.findViewById(R.id.txv_Hora_Atendimento_Valor);
                TempoUtil.setTime(txv,this);

                break;

            case R.id.txv_HoraChamado:
                txv = (TextView)v.findViewById(R.id.txv_HoraChamado);
                TempoUtil.setTime(txv,this);

                break;

            case R.id.txv_HoraOcorrencia:
                txv = (TextView)v.findViewById(R.id.txv_HoraOcorrencia);
                TempoUtil.setTime(txv,this);

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
                txv = (TextView)v.findViewById(R.id.txv_Data_Atendimento_Valor);
                TempoUtil.setDate(txv,this);
                break;

            case R.id.txv_DataNascimentoVitima:
                txv = (TextView)v.findViewById(R.id.txv_DataNascimentoVitima);
                TempoUtil.setDate(txv,this);
                break;
        }
    }

}
