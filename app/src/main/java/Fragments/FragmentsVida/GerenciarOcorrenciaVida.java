package Fragments.FragmentsVida;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericiaVida;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import Enums.AreaIntegradaSeguranca;
import Enums.DocumentoSolicitacao;
import Enums.Orgao;
import Enums.PreservacaoLocal;
import Enums.Vida.TipoOcorrenciaVida;
import Model.DadosTerritoriais;
import Model.Ocorrencia;
import Model.Vida.OcorrenciaVida;
import Util.AutoCompleteUtil;
import Util.BuscadorEnum;
import Util.TempoUtil;


public class GerenciarOcorrenciaVida extends android.support.v4.app.Fragment implements Step
{
    Ocorrencia ocorrencia;
    OcorrenciaVida ocorrenciaVida;

    EditText edtNumIncidencia;
    EditText edtComandante;
    EditText edtViatura;
    EditText edtNumDocVida;
    EditText edtDelegado;

    AutoCompleteTextView aucOrgaoOrigem;
    AutoCompleteTextView aucOrgaoDestino;
    AutoCompleteTextView aucBairro;

    TextView txvHoraChamado;
    TextView txvDataChamado;
    TextView txvHoraAtendimento;
    TextView txvDataAtendimento;


    Spinner spnTipoOcorrenciaVida;
    Spinner spnPreservacaoLocal;
    Spinner spnAutoridadePresente;
    Spinner spnTipoDocumentoVida;
    Spinner spnAIS;

    View mView;


    public GerenciarOcorrenciaVida()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GerenciarOcorrenciaVida.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragments.FragmentsVida.GerenciarOcorrenciaVida newInstance(String param1, String param2)
    {
        Fragments.FragmentsVida.GerenciarOcorrenciaVida fragment = new Fragments.FragmentsVida.GerenciarOcorrenciaVida();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {

        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        mView = view;

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ocorrencia_vida, container, false);
    }

    @Override
    public VerificationError verifyStep()
    {
        try
        {
            txvHoraChamado.setTextColor(getResources().getColor(R.color.DefaultTextColor));
            txvHoraAtendimento.setTextColor(getResources().getColor(R.color.DefaultTextColor));

            if (TempoUtil.HoraAntesDe(txvHoraAtendimento.getText().toString(), txvHoraChamado.getText().toString()))
            {
                txvHoraAtendimento.setTextColor(Color.RED);
                VerificationError ve = new VerificationError("Hora do atendimento antes do Chamado!");
                return ve;
            }

            SalvarOcorrencia();

        } catch (Exception e)
        {
            VerificationError ve = new VerificationError(e.getMessage());
            return ve;
        }
        return null;
    }

    private void SalvarOcorrencia()
    {
        ocorrenciaVida.setNumIncidencia(edtNumIncidencia.getText().toString().trim());
        ocorrenciaVida.setComandante(edtComandante.getText().toString().trim());
        ocorrenciaVida.setPreservacaoLocal(BuscadorEnum.BuscarPreservacaoLocal(spnPreservacaoLocal.getSelectedItem().toString()));


        ocorrenciaVida.setDelegado(edtDelegado.getText().toString());
        ocorrenciaVida.setOrgaoDestino(aucOrgaoDestino.getText().toString());
        ocorrenciaVida.setOrgaoOrigem(aucOrgaoOrigem.getText().toString());

        ocorrenciaVida.setHoraChamado(txvHoraChamado.getText().toString());
        ocorrenciaVida.setDataChamadoString(txvDataChamado.getText().toString());

        ocorrenciaVida.setHoraAtendimento(txvHoraAtendimento.getText().toString());
        ocorrenciaVida.setDataAtendimento(txvDataAtendimento.getText().toString());

        ocorrenciaVida.setAis(BuscadorEnum.BuscarAIS(spnAIS.getSelectedItem().toString()));
        //ocorrenciaVida.setAis(AreaIntegradaSeguranca.valueOf(AreaIntegradaSeguranca.class,spnAIS.getSelectedItem().toString()));

        ocorrenciaVida.setViatura(edtViatura.getText().toString());

        ocorrenciaVida.setAutoridadePresente(BuscadorEnum.BuscarOrgao(spnAutoridadePresente.getSelectedItem().toString()));
        //ocorrenciaVida.setAutoridadePresente(Orgao.valueOf(Orgao.class,spnAutoridadePresente.getSelectedItem().toString()));

        ocorrenciaVida.getDocumento().setTipodocumento(BuscadorEnum.BuscarDocSolicitacao(spnTipoDocumentoVida.getSelectedItem().toString()));
        //ocorrenciaVida.getDocumento().setTipodocumento(DocumentoSolicitacao.valueOf(DocumentoSolicitacao.class,spnTipoDocumentoVida.getSelectedItem().toString()));

        ocorrenciaVida.getDocumento().setValor(edtNumDocVida.getText().toString());

        ocorrenciaVida.getDocumento().save();

        ocorrenciaVida.save();
    }

    @Override
    public void onSelected()
    {
        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Ocorrência Vida");

        Bundle bd = getArguments();

        AssociarLayout(mView);
        PovoarSpinners(mView.getContext());
        AssociarEventos();

        if (bd != null)
        {
            if (bd.getLong("OcorrenciaId", 0) != 0)
            {
                ocorrenciaVida = OcorrenciaVida.findById(OcorrenciaVida.class, bd.getLong("OcorrenciaId", 0));

                ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

                CarregarValores();
            }
        }
    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }


    public void AssociarEventos()
    {
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

        txvDataChamado.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TempoUtil.setDate(txvDataChamado, mView.getContext());
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

    public void PovoarSpinners(Context ctx)
    {

        ArrayList<String> tipoOCorrenciaVida = new ArrayList<>();

        for (TipoOcorrenciaVida tov : TipoOcorrenciaVida.values())
            tipoOCorrenciaVida.add(tov.getValor());

        spnTipoOcorrenciaVida.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, tipoOCorrenciaVida));

        ArrayList<String> preservacaoLocal = new ArrayList<>();

        for (PreservacaoLocal pl : PreservacaoLocal.values())
            preservacaoLocal.add(pl.getValor());

        spnPreservacaoLocal.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, preservacaoLocal));

        ArrayList<String> tiposDocumentoVida = new ArrayList<>();

        for (DocumentoSolicitacao doc : DocumentoSolicitacao.values())
            tiposDocumentoVida.add(doc.getValor());

        spnTipoDocumentoVida.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, tiposDocumentoVida));

        ArrayList<String> autoridadePresente = new ArrayList<>();

        for (Orgao orgao : Orgao.values())
            autoridadePresente.add(orgao.getValor());

        spnAutoridadePresente.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, autoridadePresente));

        ArrayList<String> listaAIS = new ArrayList<>();

        for (AreaIntegradaSeguranca ais : AreaIntegradaSeguranca.values())
            listaAIS.add(ais.getValor());

        spnAIS.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, listaAIS));

    }

    public void AssociarLayout(final View view)
    {
        edtComandante = (EditText) view.findViewById(R.id.edt_Autoridade_Comandante_Vida);
        edtNumDocVida = (EditText) view.findViewById(R.id.edt_NumDocVida);
        edtNumIncidencia = (EditText) view.findViewById(R.id.edt_Num_Incidencia_Vida);
        edtViatura = (EditText) view.findViewById(R.id.edt_Placa_Viatura_Vida);
        edtDelegado = (EditText) view.findViewById(R.id.edt_Delegado_Vida);

        spnAutoridadePresente = (Spinner) view.findViewById(R.id.spn_Autoridade_Presente_Vida);
        spnPreservacaoLocal = (Spinner) view.findViewById(R.id.spn_Preservacao_Local_Vida);
        spnTipoDocumentoVida = (Spinner) view.findViewById(R.id.spn_TipoDocumentoOcorrenciaVida);
        spnTipoOcorrenciaVida = (Spinner) view.findViewById(R.id.spn_Tipo_Ocorrencia_Vida);
        spnAIS = (Spinner) view.findViewById(R.id.spn_AIS_Vida);

        aucBairro = (AutoCompleteTextView) view.findViewById(R.id.auc_Bairro_Delegacia_Vida);
        aucOrgaoDestino = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Destino_Vida);
        aucOrgaoOrigem = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Origem_Vida);

        txvDataAtendimento = (TextView) view.findViewById(R.id.txv_Data_Atendimento_Valor_Vida);
        txvHoraAtendimento = (TextView) view.findViewById(R.id.txv_Hora_Atendimento_Valor_Vida);
        txvDataChamado = (TextView) view.findViewById(R.id.txv_Data_Chamado_Valor_Vida);
        txvHoraChamado = (TextView) view.findViewById(R.id.txv_Hora_Chamado_Valor_Vida);

        aucBairro.setAdapter(AutoCompleteUtil.getBairros(view.getContext()));

        aucOrgaoOrigem.setAdapter(AutoCompleteUtil.getDelegacias(view.getContext()));

        aucOrgaoDestino.setAdapter(AutoCompleteUtil.getDelegacias(view.getContext()));

        aucBairro.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3)
            {
                Toast.makeText(view.getContext(), aucBairro.getText().toString(), Toast.LENGTH_LONG).show();

                try
                {
                    DadosTerritoriais dt = DadosTerritoriais.find(DadosTerritoriais.class, "bairro = ?", aucBairro.getText().toString()).get(0);
                    aucOrgaoDestino.setText(dt.getDelegacia());
                    spnAIS.setSelection(BuscadorEnum.getIndex(spnAIS, dt.getAis().getValor()));
                } catch (Exception e)
                {
                    Toast.makeText(view.getContext(), "Bairro não encontrado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void CarregarValores()
    {
        txvDataAtendimento.setText(ocorrenciaVida.getDataAtendimentoString());

        txvHoraAtendimento.setText(ocorrenciaVida.getHoraAtendimentoString());

        txvDataChamado.setText(ocorrenciaVida.getDataChamadoString());

        txvHoraChamado.setText(ocorrenciaVida.getHoraChamadoString());

        if(ocorrenciaVida.getDelegado()!=null)
        edtDelegado.setText(ocorrenciaVida.getDelegado());

        edtComandante.setText(ocorrenciaVida.getComandante());

        edtNumDocVida.setText(ocorrenciaVida.getDocumento().getValor());

        edtNumIncidencia.setText(ocorrenciaVida.getNumIncidencia());

        edtViatura.setText(ocorrenciaVida.getViatura());

        if (ocorrenciaVida.getAutoridadePresente() != null)
            spnAutoridadePresente.setSelection(BuscadorEnum.getIndex(spnAutoridadePresente, ocorrenciaVida.getAutoridadePresente().getValor()));

        if (ocorrenciaVida.getAis() != null)
            spnAIS.setSelection(BuscadorEnum.getIndex(spnAIS, ocorrenciaVida.getAis().getValor()));

        if (ocorrenciaVida.getDocumento().getTipodocumento() != null)
            spnTipoDocumentoVida.setSelection(BuscadorEnum.getIndex(spnTipoDocumentoVida, ocorrenciaVida.getDocumento().getTipodocumento().getValor()));

        if (ocorrenciaVida.getPreservacaoLocal() != null)
            spnPreservacaoLocal.setSelection(BuscadorEnum.getIndex(spnPreservacaoLocal, ocorrenciaVida.getPreservacaoLocal().getValor()));

        if (ocorrenciaVida.getTipoOcorrenciaVida() != null)
            spnTipoOcorrenciaVida.setSelection(BuscadorEnum.getIndex(spnTipoOcorrenciaVida, ocorrenciaVida.getTipoOcorrenciaVida().getValor()));

        aucOrgaoDestino.setText(ocorrenciaVida.getOrgaoDestino());

        aucOrgaoOrigem.setText(ocorrenciaVida.getOrgaoOrigem());

        aucOrgaoOrigem.setText((ocorrenciaVida.getOrgaoOrigem() == null) ? "CIOPS - Coordenadoria Integrada de Operações de Segurança" : ocorrenciaVida.getOrgaoOrigem());
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mView = null;
        ocorrencia = null;
        ocorrenciaVida = null;
        edtNumIncidencia = null;
        edtComandante = null;
        edtViatura = null;
        edtNumDocVida = null;
        aucOrgaoOrigem = null;
        aucOrgaoDestino = null;
        aucBairro = null;
        txvHoraChamado = null;
        txvHoraAtendimento = null;
        txvDataAtendimento = null;
        spnTipoOcorrenciaVida = null;
        spnPreservacaoLocal = null;
        spnAutoridadePresente = null;
        spnTipoDocumentoVida = null;
        spnAIS = null;
    }
}
