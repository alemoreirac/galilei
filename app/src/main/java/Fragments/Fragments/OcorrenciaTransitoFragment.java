package Fragments.Fragments;

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
import Model.Transito.OcorrenciaTransito;
import Util.AutoCompleteUtil;
import Util.BuscadorEnum;
import Util.TempoUtil;

public class OcorrenciaTransitoFragment extends android.support.v4.app.Fragment implements Step {


    OcorrenciaTransito ocorrenciaFragment = null;
    Ocorrencia ocorrencia = null;

    Spinner spnLocal = null;
    Spinner spnDocumento = null;
    Spinner spnOrgaoPresente = null;
    Spinner spnAIS = null;

    EditText edtValorDocumento = null;
    EditText edtComandante = null;
    EditText edtPlacaViatura_Numeros = null;
    EditText edtPlacaViatura_Letras = null;
    EditText edtIncidencia = null;

//    TextView txvIncidenciaMask = null;
    TextView txvDataAtendimento = null;
    TextView txvChamado = null;
    TextView txvAtendimento = null;

    AutoCompleteTextView autocOrgaoDestino = null;
    AutoCompleteTextView autocOrgaoOrigem = null;
    AutoCompleteTextView autocBairro = null;

    CheckBox cxbUltimaForma = null;

    View v= null;

    public OcorrenciaTransitoFragment() {
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        v=view;

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
       final View view = inflater.inflate(R.layout.fragment_ocorrencia_transito,
                container, false);



        return inflater.inflate(R.layout.fragment_ocorrencia_transito, container, false);
    }

    private void PopularSpinners(View view)
    {

        ArrayList<String> preservacaoLocal = new ArrayList<String>();

        for(PreservacaoLocal pl : PreservacaoLocal.values())
        {
            preservacaoLocal.add(pl.getValor());
        }

        spnLocal.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, preservacaoLocal));



        autocOrgaoDestino.setAdapter(AutoCompleteUtil.getDelegacias(view.getContext()));

        autocOrgaoOrigem.setAdapter(AutoCompleteUtil.getDelegacias(view.getContext()));

        ArrayList<String> docs = new ArrayList<String>();

        for(DocumentoSolicitacao d : DocumentoSolicitacao.values())
        {
            docs.add(d.getValor());
        }

        spnDocumento.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,docs));


        ArrayList<String> orgao = new ArrayList<String>();

        for(Orgao o : Orgao.values())
            orgao.add(o.getValor());

        spnOrgaoPresente.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,orgao));

        ArrayList<String> arrayAIS = new ArrayList<>();

        for(AreaIntegradaSeguranca ais : AreaIntegradaSeguranca.values())
            arrayAIS.add(ais.getValor());

        spnAIS.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,arrayAIS));
    }

    @Override
    public VerificationError verifyStep() {
        try
        {
            txvChamado.setTextColor(getResources().getColor(R.color.DefaultTextColor));
            txvAtendimento.setTextColor(getResources().getColor(R.color.DefaultTextColor));


            if(TempoUtil.HoraAntesDe(txvAtendimento.getText().toString(),txvChamado.getText().toString()))
            {
                txvAtendimento.setTextColor(Color.RED);
                VerificationError ve = new VerificationError("Hora do atendimento antes do Chamado!");
                return ve;
            }

            SalvarOcorrencia();
        }
        catch (Exception e)
        {
            VerificationError ve = new VerificationError(e.getMessage());
            return ve;
        }
        return null;
    }

    @Override
    public void onSelected()
    {
        //((ManterPericiaTransito) getActivity()).toolbar.setTitle("Ocorrência");
        ((ManterPericiaTransito) getActivity()).txvToolbarTitulo.setText("Ocorrência");

        Bundle bd = getArguments();

        AssociarLayout(v);
        PopularSpinners(v);

        if(bd != null)
        {
            if(bd.getLong("OcorrenciaId",0)!= 0)
            {
                ocorrenciaFragment = OcorrenciaTransito.findById(OcorrenciaTransito.class, bd.getLong("OcorrenciaId", 0));

                ocorrencia = Ocorrencia.findById(Ocorrencia.class,ocorrenciaFragment.getOcorrenciaID());

                CarregarValores(ocorrenciaFragment, v);
            }
        }

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    private void CarregarValores(OcorrenciaTransito ot, View v)
    {
        AssociarLayout(v);

//        txvChamado.setText(ocorrenciaTransito.getHoraChamadoString());
        txvChamado.setText(ocorrencia.getHoraChamadoString());
       // txvOcorrencia.setText(ocorrenciaTransito.getHoraOcorrenciaString());

        txvAtendimento.setText(ot.getHoraAtendimentoString());

        txvDataAtendimento.setText(ot.getDataAtendimentoString());

        cxbUltimaForma.setChecked(ot.getUltimaForma());

        if(ot.getNumIncidencia() != null)
            edtIncidencia.setText(ot.getNumIncidencia());

        if(ot.getDocumentoOcorrencia().getValor() != null)
            edtValorDocumento.setText(ot.getDocumentoOcorrencia().getValor().toString());

        if(ot.getPreservacaoLocal() != null)
            spnLocal.setSelection(BuscadorEnum.getIndex(spnLocal,ot.getPreservacaoLocal().getValor()));

        if(ot.getDocumentoOcorrencia().getTipodocumento()!=null)
            spnDocumento.setSelection(BuscadorEnum.getIndex(spnDocumento,ot.getDocumentoOcorrencia().getTipodocumento().getValor()));

        if(ot.getOrgaoPresente() != null)
        spnOrgaoPresente.setSelection(BuscadorEnum.getIndex(spnOrgaoPresente,ot.getOrgaoPresente().getValor()));

        if(ot.getAis() != null)
            spnAIS.setSelection(BuscadorEnum.getIndex(spnAIS,ot.getAis().getValor()));

        autocOrgaoOrigem.setText((ot.getOrgaoOrigem() == null) ? "CIOPS - Coordenadoria Integrada de Operações de Segurança" : ot.getOrgaoOrigem());

        autocOrgaoDestino.setText((ot.getOrgaoDestino() == null) ? "" : ot.getOrgaoDestino());

        if(ot.getComandante()!=null)
        edtComandante.setText(ot.getComandante());


        if(ot.getViatura().length()>=8)
        {
            edtPlacaViatura_Letras.setText(ot.getViatura().substring(0,3));
            edtPlacaViatura_Numeros.setText(ot.getViatura().substring(4,8));
        }
    }

    private void SalvarOcorrencia()
    {
        ocorrenciaFragment.setPreservacaoLocal(BuscadorEnum.BuscarPreservacaoLocal(spnLocal.getSelectedItem().toString()));
        ocorrenciaFragment.setOrgaoPresente(BuscadorEnum.BuscarOrgao(spnOrgaoPresente.getSelectedItem().toString()));
        ocorrenciaFragment.setAis(BuscadorEnum.BuscarAIS(spnAIS.getSelectedItem().toString()));
        ocorrenciaFragment.setNumIncidencia(edtIncidencia.getText().toString());
        ocorrenciaFragment.getDocumentoOcorrencia().setValor(edtValorDocumento.getText().toString());
        ocorrenciaFragment.setUltimaForma(cxbUltimaForma.isChecked());

        DocumentoOcorrencia docOcorrencia = DocumentoOcorrencia.findById(DocumentoOcorrencia.class,ocorrenciaFragment.getDocumentoOcorrencia().getId());

        docOcorrencia.setValor(edtValorDocumento.getText().toString());
        docOcorrencia.setTipodocumento(BuscadorEnum.BuscarDocSolicitacao(spnDocumento.getSelectedItem().toString()));

        docOcorrencia.save();

        ocorrenciaFragment.setDocumentoOcorrencia(docOcorrencia);

        if(txvChamado.getText().toString() != null)
            ocorrencia.setDataChamado( TempoUtil.stringToTime(txvChamado.getText().toString()));

     //   if(txvOcorrencia.getText().toString() != null)
    //        ocorrenciaFragment.setDataChamado( TempoUtil.stringToTime(txvOcorrencia.getText().toString()));

        if(txvAtendimento.getText().toString() != null)
            ocorrenciaFragment.setHoraAtendimento(txvAtendimento.getText().toString());

        if(txvDataAtendimento.getText().toString() != null)
        {
            ocorrenciaFragment.setDataAtendimento((txvDataAtendimento.getText().toString()));
            ocorrencia.setDataChamado((txvDataAtendimento.getText().toString()));
        }
        if(autocOrgaoDestino.getText().toString() != null)
            ocorrenciaFragment.setOrgaoDestino(autocOrgaoDestino.getText().toString());

        if(autocOrgaoOrigem.getText().toString() != null)
            ocorrenciaFragment.setOrgaoOrigem(autocOrgaoOrigem.getText().toString());



        if(edtComandante.getText().toString() != null)
            ocorrenciaFragment.setComandante(edtComandante.getText().toString());


        if(edtPlacaViatura_Letras.getText().toString() != null && edtPlacaViatura_Numeros.getText().toString() != null )
            ocorrenciaFragment.setViatura(edtPlacaViatura_Letras.getText().toString() +"-"+ edtPlacaViatura_Numeros.getText().toString());

        ocorrenciaFragment.save();
        ocorrencia.save();

        ((ManterPericiaTransito) getActivity()).ocorrenciaTransito = ocorrenciaFragment;

    }

    private void AssociarLayout(View view)
    {
        txvChamado = (TextView) view.findViewById(R.id.txv_HoraChamado);
        txvAtendimento = (TextView) view.findViewById(R.id.txv_Hora_Atendimento_Valor);
        txvDataAtendimento = (TextView)view.findViewById(R.id.txv_Data_Atendimento_Valor);
        //txvIncidenciaMask = (TextView) view.findViewById(R.id.txv_Incidencia_Mask);

        edtValorDocumento = (EditText) view.findViewById(R.id.edt_ValorDocumento);
        edtIncidencia = (EditText) view.findViewById(R.id.edt_Num_Incidencia);

        spnLocal = (Spinner)view.findViewById(R.id.spn_PreservacaoLocal);
        spnDocumento = (Spinner) view.findViewById(R.id.spn_TipoDocumentoOcorrencia);
        spnOrgaoPresente = (Spinner) view.findViewById(R.id.spn_Autoridade_Presente);
        spnAIS = (Spinner) view.findViewById(R.id.spn_AIS);

        edtComandante = (EditText)view.findViewById(R.id.edt_Autoridade_Comandante);

        edtPlacaViatura_Numeros = (EditText)view.findViewById(R.id.edt_PlacaNumeros_Viatura);
        edtPlacaViatura_Letras = (EditText)view.findViewById(R.id.edt_PlacaLetras_Viatura);

        autocBairro = (AutoCompleteTextView) view.findViewById(R.id.auc_Bairro_Delegacia);
        autocOrgaoOrigem = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Origem);
        autocOrgaoDestino = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Destino);

        cxbUltimaForma = (CheckBox) view.findViewById(R.id.cxb_Ultima_Forma);

        InputFilter filter = new InputFilter()
        {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend)
            {
                for (int i = start; i < end; i++)
                {
                    if (!Character.isLetter(source.charAt(i)))
                    {
                        return "";
                    }
                }
                return null;
            }
        };


        edtPlacaViatura_Letras.setFilters(new InputFilter[] {new InputFilter.AllCaps(),new InputFilter.LengthFilter(3),filter});

    //   edtIncidencia.addTextChangedListener(new TextWatcher() {

    //       StringBuilder incidencia = new StringBuilder("0000000");

    //       @Override
    //       public void onTextChanged(CharSequence s, int start, int before, int count) {

    //       }
    //       @Override
    //       public void beforeTextChanged(CharSequence s, int start, int count,
    //                                     int after) {

    //       }
    //       @Override
    //       public void afterTextChanged(Editable s)
    //       {
    //           incidencia.setLength(0);
    //           incidencia.insert(0,"0000000");
    //           incidencia.replace(incidencia.length() - s.length(), incidencia.length(), s.toString());

    //           txvIncidenciaMask.setText("I"+ Calendar.getInstance().get(Calendar.YEAR)+" "+
    //                   incidencia.toString());
    //       }
    //   });

        autocBairro.setAdapter(AutoCompleteUtil.getBairros(view.getContext()));

autocBairro.setOnItemClickListener(new AdapterView.OnItemClickListener() {

    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                            long arg3) {


        Toast.makeText(v.getContext(),  autocBairro.getText().toString(), Toast.LENGTH_LONG).show();

        try
        {
            DadosTerritoriais dt = DadosTerritoriais.find(DadosTerritoriais.class, "bairro = ?", autocBairro.getText().toString()).get(0);
            autocOrgaoDestino.setText(dt.getDelegacia());
            spnAIS.setSelection(BuscadorEnum.getIndex(spnAIS,dt.getAis().getValor()));
        }
        catch(Exception e )
        {
            Toast.makeText(v.getContext(), "Bairro não encontrado", Toast.LENGTH_LONG).show();
        }
    }
    });

    }

}
