package Fragments.FragmentsTransito;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericiaTransito;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import Enums.AreaIntegradaSeguranca;
import Enums.DocumentoSolicitacao;
import Enums.Orgao;
import Enums.PreservacaoLocal;
import Model.DadosTerritoriais;
import Model.DocumentoOcorrencia;
import Model.Ocorrencia;
import Model.Transito.EnderecoTransito;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoEndereco;
import Util.AutoCompleteUtil;
import Util.BuscadorEnum;
import Util.TempoUtil;

public class OcorrenciaTransitoFragment extends android.support.v4.app.Fragment implements Step
{


    OcorrenciaTransito ocorrenciaTransito = null;
    Ocorrencia ocorrencia = null;

    Spinner spnLocal = null;
    Spinner spnDocumento = null;
    Spinner spnOrgaoPresente = null;
    Spinner spnAIS = null;

    EditText edtValorDocumento = null;
    EditText edtComandante = null;
    EditText edtViatura = null;


    EditText edtIncidencia = null;

    //    TextView txvIncidenciaMask = null;

    TextView txvDataAtendimento = null;
    TextView txvHoraAtendimento = null;

    TextView txvHoraChamado = null;
    TextView txvDataChamado = null;

    AutoCompleteTextView autocOrgaoDestino = null;
    AutoCompleteTextView autocOrgaoOrigem = null;
    AutoCompleteTextView autocBairro = null;

    CheckBox cxbUltimaForma = null;

    View v = null;

    public OcorrenciaTransitoFragment()
    {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        v = view;

        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_ocorrencia_transito, container, false);
    }

    private void PopularSpinners(View view)
    {

        ArrayList<String> preservacaoLocal = new ArrayList<String>();

        for (PreservacaoLocal pl : PreservacaoLocal.values())
        {
            preservacaoLocal.add(pl.getValor());
        }

        spnLocal.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, preservacaoLocal));

        autocOrgaoDestino.setAdapter(AutoCompleteUtil.getDelegacias(view.getContext()));

        autocOrgaoOrigem.setAdapter(AutoCompleteUtil.getDelegacias(view.getContext()));

        ArrayList<String> docs = new ArrayList<String>();

        for (DocumentoSolicitacao d : DocumentoSolicitacao.values())
        {
            docs.add(d.getValor());
        }

        spnDocumento.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, docs));

        ArrayList<String> orgao = new ArrayList<String>();

        for (Orgao o : Orgao.values())
            orgao.add(o.getValor());

        spnOrgaoPresente.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, orgao));

        ArrayList<String> arrayAIS = new ArrayList<>();

        for (AreaIntegradaSeguranca ais : AreaIntegradaSeguranca.values())
            arrayAIS.add(ais.getValor());

        spnAIS.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, arrayAIS));
    }

    @Override
    public VerificationError verifyStep()
    {
        try
        {
        txvHoraChamado.setTextColor(getResources().getColor(R.color.DefaultTextColor));
        txvHoraAtendimento.setTextColor(getResources().getColor(R.color.DefaultTextColor));
        txvDataChamado.setTextColor(getResources().getColor(R.color.DefaultTextColor));
        txvDataAtendimento.setTextColor(getResources().getColor(R.color.DefaultTextColor));

        if(txvDataChamado.getText().toString().equals(txvDataAtendimento.getText().toString()))
        {
            if (TempoUtil.HoraAntesDe(txvHoraAtendimento.getText().toString(), txvHoraChamado.getText().toString()))
            {
                txvHoraAtendimento.setTextColor(Color.RED);
                VerificationError ve = new VerificationError("Hora do atendimento antes do chamado!");
                return ve;
            }
        }
        else
        {
            if(TempoUtil.DataAntesDe(txvDataAtendimento.getText().toString(),txvDataChamado.getText().toString()))
            {
                txvDataAtendimento.setTextColor(Color.RED);
                VerificationError ve = new VerificationError("Data do atendimento antes do chamado!");
                return ve;
            }
        }


        SalvarOcorrencia();

    } catch (Exception e)
    {
        VerificationError ve = new VerificationError(e.getMessage());
        return ve;
    }
        return null;
}


    @Override
    public void onSelected()
    {
        ((ManterPericiaTransito) getActivity()).txvToolbarTitulo.setText("Ocorrência de Trânsito");

        Bundle bd = getArguments();

        AssociarLayout(v);
        AssociarEventos(v);
        PopularSpinners(v);



        if (bd != null)
        {
            if (bd.getLong("OcorrenciaId", 0) != 0)
            {
                ocorrenciaTransito = OcorrenciaTransito.findById(OcorrenciaTransito.class, bd.getLong("OcorrenciaId", 0));

                ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransito.getOcorrenciaID());

                CarregarValores(ocorrenciaTransito);

                try
                {
                    OcorrenciaTransitoEndereco ote = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrenciaTransito.getId().toString()).get(0);
                    CarregarBairro(ote.getEnderecoTransito().getBairro());
                    autocBairro.setText(ote.getEnderecoTransito().getBairro());
                }catch (Exception e)
                {
                    autocBairro.setText("");
                }
            }
        }

    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    private void CarregarValores(OcorrenciaTransito ot)
    {
        AssociarLayout(v);

        txvDataChamado.setText(ot.getDataChamadoString());

        txvHoraChamado.setText(ot.getHoraChamadoString());

        txvHoraAtendimento.setText(ot.getHoraAtendimentoString());

        txvDataAtendimento.setText(ot.getDataAtendimentoString());

        cxbUltimaForma.setChecked(ot.getUltimaForma());

        if (ot.getNumIncidencia() != null)
            edtIncidencia.setText(ot.getNumIncidencia());

        if (ot.getDocumentoOcorrencia().getValor() != null)
            edtValorDocumento.setText(ot.getDocumentoOcorrencia().getValor().toString());

        if (ot.getPreservacaoLocal() != null)
            spnLocal.setSelection(BuscadorEnum.getIndex(spnLocal, ot.getPreservacaoLocal().getValor()));

        if (ot.getDocumentoOcorrencia().getTipodocumento() != null)
            spnDocumento.setSelection(BuscadorEnum.getIndex(spnDocumento, ot.getDocumentoOcorrencia().getTipodocumento().getValor()));

        if (ot.getOrgaoPresente() != null)
            spnOrgaoPresente.setSelection(BuscadorEnum.getIndex(spnOrgaoPresente, ot.getOrgaoPresente().getValor()));

        if (ot.getAis() != null)
            spnAIS.setSelection(BuscadorEnum.getIndex(spnAIS, ot.getAis().getValor()));

        autocOrgaoOrigem.setText((ot.getOrgaoOrigem() == null) ? "CIOPS - Coordenadoria Integrada de Operações de Segurança" : ot.getOrgaoOrigem());

        autocOrgaoDestino.setText((ot.getOrgaoDestino() == null) ? "" : ot.getOrgaoDestino());

        if (ot.getComandante() != null)
            edtComandante.setText(ot.getComandante());

        if (ot.getViatura().length() >= 8)
        {
            edtViatura.setText(ot.getViatura());
//            edtPlacaViatura_Letras.setText(ot.getViatura().substring(0, 3));
//            edtPlacaViatura_Numeros.setText(ot.getViatura().substring(4, 8));
        }
    }

    private void SalvarOcorrencia()
    {
        ocorrenciaTransito.setPreservacaoLocal(BuscadorEnum.BuscarPreservacaoLocal(spnLocal.getSelectedItem().toString()));
        //ocorrenciaTransito.setPreservacaoLocal(PreservacaoLocal.valueOf(PreservacaoLocal.class,spnLocal.getSelectedItem().toString()));

        ocorrenciaTransito.setOrgaoPresente(BuscadorEnum.BuscarOrgao(spnOrgaoPresente.getSelectedItem().toString()));
        //ocorrenciaTransito.setOrgaoPresente(Orgao.valueOf(Orgao.class,spnOrgaoPresente.getSelectedItem().toString()));

        ocorrenciaTransito.setAis(BuscadorEnum.BuscarAIS(spnAIS.getSelectedItem().toString()));
        //ocorrenciaTransito.setAis(AreaIntegradaSeguranca.valueOf(AreaIntegradaSeguranca.class,spnAIS.getSelectedItem().toString()));

        ocorrenciaTransito.setNumIncidencia(edtIncidencia.getText().toString().trim());
        ocorrenciaTransito.getDocumentoOcorrencia().setValor(edtValorDocumento.getText().toString());
        ocorrenciaTransito.setUltimaForma(cxbUltimaForma.isChecked());

        DocumentoOcorrencia docOcorrencia = DocumentoOcorrencia.findById(DocumentoOcorrencia.class, ocorrenciaTransito.getDocumentoOcorrencia().getId());

        docOcorrencia.setValor(edtValorDocumento.getText().toString());
        docOcorrencia.setTipodocumento(BuscadorEnum.BuscarDocSolicitacao(spnDocumento.getSelectedItem().toString()));
        //docOcorrencia.setTipodocumento(DocumentoSolicitacao.valueOf(DocumentoSolicitacao.class,spnDocumento.getSelectedItem().toString()));

        docOcorrencia.save();

        ocorrenciaTransito.setDocumentoOcorrencia(docOcorrencia);

        if (txvHoraAtendimento.getText().toString() != null)
            ocorrenciaTransito.setHoraAtendimento(txvHoraAtendimento.getText().toString());

        if(txvDataAtendimento.getText().toString()!=null)
            ocorrenciaTransito.setDataAtendimento(txvDataAtendimento.getText().toString());

        if(txvDataChamado.getText().toString()!=null)
            ocorrenciaTransito.setDataChamado(txvDataChamado.getText().toString());

        if(txvHoraChamado.getText().toString()!=null)
            ocorrenciaTransito.setHoraChamado(txvHoraChamado.getText().toString());

        if (autocOrgaoDestino != null)
            ocorrenciaTransito.setOrgaoDestino(autocOrgaoDestino.getText().toString());

        if (autocOrgaoOrigem != null)
            ocorrenciaTransito.setOrgaoOrigem(autocOrgaoOrigem.getText().toString());


        if (edtComandante!= null)
            ocorrenciaTransito.setComandante(edtComandante.getText().toString());

        if(edtViatura!=null)
        ocorrenciaTransito.setViatura(edtViatura.getText().toString());

//        if (edtPlacaViatura_Letras.getText().toString() != null && edtPlacaViatura_Numeros.getText().toString() != null)
//            ocorrenciaTransito.setViatura(edtPlacaViatura_Letras.getText().toString() + "-" + edtPlacaViatura_Numeros.getText().toString());


        ocorrenciaTransito.save();

        ocorrencia.setDataChamado(ocorrenciaTransito.getDataChamado());

        ocorrencia.save();

        ((ManterPericiaTransito) getActivity()).ocorrenciaTransito = ocorrenciaTransito;

    }

    private void AssociarEventos(View mView)
    {

        txvDataChamado.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TempoUtil.setDate(txvDataChamado, mView.getContext());
            }
        });

        txvHoraChamado.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TempoUtil.setTime(txvHoraChamado, mView.getContext());
            }
        });

        txvDataAtendimento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TempoUtil.setDate(txvDataAtendimento, mView.getContext());
            }
        });


        txvHoraAtendimento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TempoUtil.setTime(txvHoraAtendimento, mView.getContext());
            }
        });
    }

    private void AssociarLayout(View view)
    {
        txvHoraChamado = (TextView) view.findViewById(R.id.txv_Hora_Chamado_Valor_Transito);
        txvHoraAtendimento = (TextView) view.findViewById(R.id.txv_Hora_Atendimento_Valor);
        txvDataAtendimento = (TextView) view.findViewById(R.id.txv_Data_Atendimento_Valor_Transito);
        txvDataChamado = (TextView) view.findViewById(R.id.txv_Data_Chamado_Transito_Valor);

        edtValorDocumento = (EditText) view.findViewById(R.id.edt_ValorDocumento);
        edtIncidencia = (EditText) view.findViewById(R.id.edt_Num_Incidencia);

        spnLocal = (Spinner) view.findViewById(R.id.spn_PreservacaoLocal);
        spnDocumento = (Spinner) view.findViewById(R.id.spn_TipoDocumentoOcorrencia);
        spnOrgaoPresente = (Spinner) view.findViewById(R.id.spn_Autoridade_Presente);
        spnAIS = (Spinner) view.findViewById(R.id.spn_AIS);

        edtComandante = (EditText) view.findViewById(R.id.edt_Autoridade_Comandante);

        edtViatura = (EditText) view.findViewById(R.id.edt_Placa_Viatura_Transito);

//        edtPlacaViatura_Numeros = (EditText) view.findViewById(R.id.edt_PlacaNumeros_Viatura);
//        edtPlacaViatura_Letras = (EditText) view.findViewById(R.id.edt_PlacaLetras_Viatura);

        autocBairro = (AutoCompleteTextView) view.findViewById(R.id.auc_Bairro_Delegacia);

        autocOrgaoOrigem = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Origem);

        autocOrgaoDestino = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Destino);

        cxbUltimaForma = (CheckBox) view.findViewById(R.id.cxb_Ultima_Forma);

        edtIncidencia.setNextFocusRightId(edtValorDocumento.getId());
        edtValorDocumento.setNextFocusRightId(edtComandante.getId());
        edtComandante.setNextFocusRightId(edtViatura.getId());
        edtViatura.setNextFocusRightId(autocOrgaoOrigem.getId());
        autocOrgaoOrigem.setNextFocusRightId(autocOrgaoDestino.getId());
        autocBairro.setNextFocusRightId(autocOrgaoDestino.getId());

        autocBairro.setAdapter(AutoCompleteUtil.getBairros(view.getContext()));

        autocBairro.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3)
            {
                CarregarBairro(autocBairro.getText().toString());
            }
        });

    }

    public void CarregarBairro(String bairro)
    {
        try
        {
            DadosTerritoriais dt = DadosTerritoriais.find(DadosTerritoriais.class, "bairro = ?", bairro).get(0);
            autocOrgaoDestino.setText(dt.getDelegacia());
            spnAIS.setSelection(BuscadorEnum.getIndex(spnAIS, dt.getAis().getValor()));
        } catch (Exception e)
        {
            Toast.makeText(getActivity(), "Bairro não encontrado", Toast.LENGTH_LONG).show();
        }
    }

}
