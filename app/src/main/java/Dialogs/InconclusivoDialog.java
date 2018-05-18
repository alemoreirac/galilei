package Dialogs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.pefoce.peritolocal.ManterPericiaTransito;
import com.example.pefoce.peritolocal.R;

import java.util.ArrayList;
import java.util.List;

import Enums.Transito.TipoJustificativa_Inconclusao;
import Fragments.FragmentsTransito.GerenciarColisoesTransito;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnvolvidoTransito;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;

/**
 * Created by Pefoce on 17/10/2017.
 */

public class InconclusivoDialog extends android.support.v4.app.DialogFragment
{
    public ColisaoTransito colisaoTransito;
    public Activity activity = null;
    public Spinner spnJustificativa, spnEvadido;
    public Button btnCancelarInconclusao, btnSalvarInconclusao;
    public OcorrenciaTransito ocorrenciaTransito;
    public CheckBox cxbInconclusivo;
    public TextView txvAtorEvadido;
    public TextView txvJustificativa;
    public ArrayList<EnvolvidoTransito> envolvidos;
    public ArrayList<Veiculo> veiculos;
    public boolean semEvasores;
    public Long envolvidoId = 0l;
    public Long veiculoId = 0l;
    public String tipoJustificativa_inconclusao;
    public boolean inconclusivo;


    public InconclusivoDialog()
    {
    }

//    public static VestigioDialog newInstance(String title, String local)
//    {
//        VestigioDialog frag = new VestigioDialog();
//
//        Bundle args = new Bundle();
//
//        args.putString("Gravar Áudio", title);
//        args.putString("Local", local);
//
//        frag.setArguments(args);
//
//        return frag;
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_inconclusivo, container, false);

        WindowManager.LayoutParams wmlp = getDialog().getWindow().getAttributes();
        wmlp.gravity = Gravity.CENTER;

        Bundle bd = getArguments();

        activity = getActivity();
        ocorrenciaTransito = ((ManterPericiaTransito) activity).ocorrenciaTransito;

        //colisaoTransito = ColisaoTransito.findById(ColisaoTransito.class,bd.getLong("ColisaoID"));
        colisaoTransito = ((GerenciarColisoesTransito) getTargetFragment()).colisaoTransito;

        semEvasores = bd.getBoolean("SemEvasores");

        inconclusivo = bd.getBoolean("Inconclusivo");

        if (inconclusivo)
        {
            tipoJustificativa_inconclusao = bd.getString("Justificativa");
            veiculoId = bd.getLong("VeiculoEvasorId", 0l);
            envolvidoId = bd.getLong("EnvolvidoEvasorId", 0l);
        }

        List<OcorrenciaTransitoEnvolvido> ocorrenciaEnvolvidos = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());
        envolvidos = new ArrayList<EnvolvidoTransito>();

        for (OcorrenciaTransitoEnvolvido oe : ocorrenciaEnvolvidos)
            envolvidos.add(oe.getEnvolvidoTransito());

        List<OcorrenciaTransitoVeiculo> ocorrenciaVeiculos = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString());
        veiculos = new ArrayList<Veiculo>();

        for (OcorrenciaTransitoVeiculo ov : ocorrenciaVeiculos)

            veiculos.add(ov.getVeiculo());

        activity = getActivity();

        AssociarLayout(view);
        AssociarEventos();

//        if (colisaoTransito.getJustificativaInconclusao() != null || colisaoTransito.getEnvolvidoEvadido() != null
//                || colisaoTransito.getVeiculoEvadido() != null || colisaoTransito.getInconclusivo())
        if(inconclusivo || veiculoId!=0l || envolvidoId != 0l || tipoJustificativa_inconclusao!= null)
            CarregarInconclusivo(view);

        return view;
    }

    private void CarregarInconclusivo(View v)
    {
        if (tipoJustificativa_inconclusao!= null)
        {
            cxbInconclusivo.setChecked(false);
            cxbInconclusivo.performClick();
            try
            {
                spnJustificativa.setSelection(BuscadorEnum.getIndex(spnJustificativa, tipoJustificativa_inconclusao));
            } catch (Exception e)
            {
                spnJustificativa.setSelection(0);
            }
        }
        try
        {
            if (veiculoId != null && veiculoId != 0l)
            {
                spnEvadido.setAdapter(new ArrayAdapter<Veiculo>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, veiculos));
                spnEvadido.setSelection(BuscadorEnum.PegarPosicaoVeiculo(veiculos,Veiculo.findById(Veiculo.class,envolvidoId)));
            }
            if (colisaoTransito.getEnvolvidoEvadido() != null)
            {
                spnEvadido.setAdapter(new ArrayAdapter<EnvolvidoTransito>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, envolvidos));
                spnEvadido.setSelection(BuscadorEnum.PegarPosicaoEnvolvidoTransito(envolvidos, EnvolvidoTransito.findById(EnvolvidoTransito.class,envolvidoId)));
            }
        } catch (Exception e)
        {
            spnEvadido.setSelection(0);
        }
    }

    private void AssociarEventos()
    {
        btnCancelarInconclusao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getDialog().dismiss();
            }
        });

        btnSalvarInconclusao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bd = new Bundle();

                bd.putBoolean("inconclusivo", cxbInconclusivo.isChecked());

                if (cxbInconclusivo.isChecked())
                {
//                    colisaoTransito.setInconclusivo(true);
//                    colisaoTransito.setJustificativaInconclusao(BuscadorEnum.BuscarJustificativa(spnJustificativa.getSelectedItem().toString()));

                    bd.putString("justificativa", spnJustificativa.getSelectedItem().toString());

                    if (spnJustificativa.getSelectedItem().toString().equals(TipoJustificativa_Inconclusao.CONDUTOR_EVADIU.getValor()))
//                        colisaoTransito.setVeiculoEvadido((Veiculo) spnEvadido.getSelectedItem());
                        bd.putLong("veiculo", ((Veiculo) spnEvadido.getSelectedItem()).getId());

                    if (spnJustificativa.getSelectedItem().toString().equals(TipoJustificativa_Inconclusao.ENVOLVIDO_EVADIU.getValor()))
//                        colisaoTransito.setEnvolvidoEvadido((EnvolvidoTransito) spnEvadido.getSelectedItem());
                        bd.putLong("envolvido", ((EnvolvidoTransito) spnEvadido.getSelectedItem()).getId());

//                    colisaoTransito.save();
                    //colisaoTransito.set
                }


                ((GerenciarColisoesTransito) getTargetFragment()).DialogResult(bd);

                getDialog().dismiss();
            }
        });

        cxbInconclusivo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    txvJustificativa.setVisibility(View.VISIBLE);
                    spnJustificativa.setVisibility(View.VISIBLE);
                } else
                {
                    spnEvadido.setVisibility(View.INVISIBLE);
                    txvAtorEvadido.setVisibility(View.INVISIBLE);
                    txvJustificativa.setVisibility(View.INVISIBLE);
                    spnJustificativa.setVisibility(View.INVISIBLE);
                }
            }
        });


        spnJustificativa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                switch (spnJustificativa.getSelectedItem().toString())
                {
                    case "Envolvido se evadiu":
                        txvAtorEvadido.setText("Envolvido que evadiu: ");
                        spnEvadido.setAdapter(new ArrayAdapter<EnvolvidoTransito>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, envolvidos));

                        txvAtorEvadido.setVisibility(View.VISIBLE);
                        spnEvadido.setVisibility(View.VISIBLE);
                        break;
                    case "Condutor se evadiu":
                        txvAtorEvadido.setText("Condutor que evadiu: ");
                        spnEvadido.setAdapter(new ArrayAdapter<Veiculo>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, veiculos));

                        txvAtorEvadido.setVisibility(View.VISIBLE);
                        spnEvadido.setVisibility(View.VISIBLE);
                        break;
                    default:
                        txvAtorEvadido.setVisibility(View.INVISIBLE);
                        spnEvadido.setVisibility(View.INVISIBLE);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });


    }


    private void sendResult(boolean resultado)
    {
        Intent intent = new Intent();
        intent.putExtra("Inconclusivo", resultado);
        getTargetFragment().onActivityResult(
                getTargetRequestCode(), 1, intent);
    }

    private void AssociarLayout(View view)
    {
        btnCancelarInconclusao = (Button) view.findViewById(R.id.btn_dialog_Cancelar_Inconclusivo);
        btnSalvarInconclusao = (Button) view.findViewById(R.id.btn_dialog_Salvar_Inconclusivo);
        spnEvadido = (Spinner) view.findViewById(R.id.spn_dialog_Evadido);
        spnJustificativa = (Spinner) view.findViewById(R.id.spn_dialog_Justificativa);
        cxbInconclusivo = (CheckBox) view.findViewById(R.id.cxb_dialog_Inconclusivo);
        txvAtorEvadido = (TextView) view.findViewById(R.id.txv_dialog_Evadido);
        txvJustificativa = (TextView) view.findViewById(R.id.txv_dialog_Justificativa);

        ArrayList<String> justificativas = new ArrayList<>();


        for (TipoJustificativa_Inconclusao tji : TipoJustificativa_Inconclusao.values())
            justificativas.add(tji.getValor());

        //Caso não seja um atropelamento, é removida a opção de um envolvido se evadir.
        if (semEvasores || spnEvadido.getCount() == 0)
            justificativas.remove(justificativas.size() - 1);


        spnJustificativa.setAdapter(new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_dropdown_item, justificativas));

    }

}
