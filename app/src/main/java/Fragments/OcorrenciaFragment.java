package Fragments;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.MainActivity;
import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.PericiaTransito;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;

import Enums.DocumentoSolicitacao;
import Enums.EstadoSitioColisao;
import Enums.Orgao;
import Enums.PreservacaoLocal;
import Model.Delegacia;
import Model.DocumentoOcorrencia;
import Model.OcorrenciaTransito;
import Util.BuscadorEnum;
import Util.TempoUtil;

public class OcorrenciaFragment extends android.support.v4.app.Fragment implements Step {


    OcorrenciaTransito ocorrenciaFragment = null;
    Spinner spnLocal = null;
    Spinner spnColisao = null;
    Spinner spnDocumento = null;
    Spinner spnOrgaoPresente = null;


    EditText edtValorDocumento = null;
    EditText edtComandante = null;
    EditText edtPlacaViatura_Numeros = null;
    EditText edtPlacaViatura_Letras = null;


    TextView txvDataAtendimento = null;
    TextView txvChamado = null;
    TextView txvOcorrencia = null;
    TextView txvAtendimento = null;

    AutoCompleteTextView autocOrgaoDestino = null;
    AutoCompleteTextView autocOrgaoOrigem = null;

    View v= null;

    public OcorrenciaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        v=view;
     //   ImageView imgv = (ImageView) toolbar.findViewById(R.id.action_anterior);
//
     //   imgv.setOnClickListener(new View.OnClickListener()
     //   {
     //       @Override
     //       public void onClick(View v)
     //       {
//
     //      //     SalvarOcorrencia();
//
     //           Toast.makeText(v.getContext(), "Ocorrência salva com Sucesso!", Toast.LENGTH_LONG).show();
//
     //      //  Intent it = new Intent(v.getContext(),MainActivity.class);
     //      //  it.putExtra("PeritoId",ocorrenciaFragment.getPerito().getId());
     //      //  startActivity(it);
//
     //       }
     //   });


     //  Button btnSalvar = (Button) getActivity().findViewById(R.id.btn_Salvar_Pericia);

     //  btnSalvar.setOnClickListener(new View.OnClickListener()
     //  {
     //      @Override
     //      public void onClick(View v)
     //      {

     //          SalvarOcorrencia();

     //          Toast.makeText(v.getContext(), "Ocorrência salva com Sucesso!", Toast.LENGTH_LONG).show();

     //          Intent it = new Intent(v.getContext(),MainActivity.class);
     //          it.putExtra("PeritoId",ocorrenciaFragment.getPerito().getId());
     //          startActivity(it);

     //      }
     //  });

     //  Button btnCancelar = (Button) getActivity().findViewById(R.id.btn_Cancelar_Pericia);

     //  btnCancelar.setOnClickListener(new View.OnClickListener()
     //  {
     //      @Override
     //      public void onClick(final View v)
     //      {
     //          AlertDialog.Builder builder;
     //          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
     //              builder = new AlertDialog.Builder(v.getContext(), android.R.style.Theme_Material_Dialog_Alert);
     //          } else {
     //              builder = new AlertDialog.Builder(v.getContext());
     //          }
     //          builder.setTitle("Cancelar Ocorrência")
     //                  .setMessage("Você deseja cancelar as alterações desta Ocorrência?")
     //                  .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
     //                      public void onClick(DialogInterface dialog, int which) {
     //                          Intent it = new Intent(v.getContext(),MainActivity.class);
     //                          it.putExtra("PeritoId",ocorrenciaFragment.getPerito().getId());
//   //                            it.putExtra("OcorrenciaId",0);
     //                          startActivity(it);
     //                      }
     //                  })
     //                  .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
     //                      public void onClick(DialogInterface dialog, int which) {
     //                          dialog.dismiss();
     //                      }
     //                  })
     //                  .show();
     //      }
     //  });
//
//        Button btnProxima = (Button) getActivity().findViewById(R.id.btn_Proxima_Pericia);
//
//        btnProxima.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(final View v)
//            {
//                Toast.makeText(v.getContext(), "Ocorrência salva com Sucesso!", Toast.LENGTH_LONG).show();
//
//                SalvarOcorrencia();
//
//                // Pega o FragmentManager
//                FragmentManager fm = getActivity().getFragmentManager();
//
//                // Abre uma transação e adiciona
//                FragmentTransaction ft = fm.beginTransaction();
//
//                EnderecosFragment fragment = new EnderecosFragment();
//                Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
//                ft.replace(R.id.fragment_content,fragment);
//                ft.commit();
//                toolbar.setTitle("Endereços");
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
       final View view = inflater.inflate(R.layout.fragment_ocorrencia,
                container, false);

        return inflater.inflate(R.layout.fragment_ocorrencia, container, false);
    }

    private void PopularSpinners(View view)
    {

        ArrayList<String> sitioColisao = new ArrayList<String>();

        for(EstadoSitioColisao esc : EstadoSitioColisao.values())
        {
            sitioColisao.add(esc.getValor());
        }

        ArrayList<String> preservacaoLocal = new ArrayList<String>();

        for(PreservacaoLocal pl : PreservacaoLocal.values())
        {
            preservacaoLocal.add(pl.getValor());
        }

        spnLocal.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, preservacaoLocal));

        //SPINNER CONDIÇÕES DA COLISÃO

        spnColisao.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, sitioColisao));

        autocOrgaoDestino.setAdapter(getDelegacias(view.getContext()));

        autocOrgaoOrigem.setAdapter(getDelegacias(view.getContext()));

        ArrayList<String> docs = new ArrayList<String>();

        for(DocumentoSolicitacao d : DocumentoSolicitacao.values())
        {
            docs.add(d.getValor());
        }

        spnDocumento.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,docs));


        ArrayList<String> orgao = new ArrayList<String>();

        for(Orgao o : Orgao.values())
        {
            orgao.add(o.getValor());
        }

        spnOrgaoPresente.setAdapter(new ArrayAdapter<String>(view.getContext(),android.R.layout.simple_spinner_dropdown_item,orgao));
    }

    @Override
    public VerificationError verifyStep() {

        try
        {
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
        ((ManterPericia) getActivity()).toolbar.setTitle("Ocorrência");

        Bundle bd = getArguments();

        AssociarLayout(v);
        PopularSpinners(v);

        if(bd != null)
        {
            if(bd.getLong("OcorrenciaId",0)!= 0)
            {
                ocorrenciaFragment = OcorrenciaTransito.findById(OcorrenciaTransito.class, bd.getLong("OcorrenciaId", 0));
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

        txvChamado.setText(ot.getHoraChamadoString());

        txvOcorrencia.setText(ot.getHoraOcorrenciaString());

        txvAtendimento.setText(ot.getHoraAtendimentoString());

        txvDataAtendimento.setText(ot.getDataAtendimentoString());

        if(ot.getDocumentoOcorrencia().getValor() != null)
            edtValorDocumento.setText(ot.getDocumentoOcorrencia().getValor().toString());

        if(ot.getEstadoSitioColisao() != null)
            spnColisao.setSelection(BuscadorEnum.getIndex(spnColisao,ot.getEstadoSitioColisao().getValor()));
        if(ot.getPreservacaoLocal() != null)
            spnLocal.setSelection(BuscadorEnum.getIndex(spnLocal,ot.getPreservacaoLocal().getValor()));

        if(ot.getDocumentoOcorrencia().getTipodocumento()!=null)
            spnDocumento.setSelection(BuscadorEnum.getIndex(spnDocumento,ot.getDocumentoOcorrencia().getTipodocumento().getValor()));

        if(ot.getOrgaoPresente() != null)
            spnOrgaoPresente.setSelection(BuscadorEnum.getIndex(spnOrgaoPresente,ot.getOrgaoPresente().getValor()));

        autocOrgaoOrigem.setText((ot.getOrgaoOrigem() == null) ? "" : ot.getOrgaoOrigem());

        autocOrgaoDestino.setText((ot.getOrgaoDestino() == null) ? "" : ot.getOrgaoDestino());

        if(ot.getComandante()!=null)
        edtComandante.setText(ot.getComandante());

        if(ot.getViatura()!=null)
        {
            edtPlacaViatura_Letras.setText(ot.getViatura().substring(0,3));
            edtPlacaViatura_Numeros.setText(ot.getViatura().substring(4,8));
        }



    }

    private void SalvarOcorrencia()
    {
        ocorrenciaFragment.setEstadoSitioColisao(BuscadorEnum.BuscarEstadoColisao(spnColisao.getSelectedItem().toString()));
        ocorrenciaFragment.setPreservacaoLocal(BuscadorEnum.BuscarPreservacaoLocal(spnLocal.getSelectedItem().toString()));
        ocorrenciaFragment.setOrgaoPresente(BuscadorEnum.BuscarOrgao(spnOrgaoPresente.getSelectedItem().toString()));

        ocorrenciaFragment.getDocumentoOcorrencia().setValor(edtValorDocumento.getText().toString());

        DocumentoOcorrencia docOcorrencia = DocumentoOcorrencia.findById(DocumentoOcorrencia.class,ocorrenciaFragment.getDocumentoOcorrencia().getId());

        docOcorrencia.setValor(edtValorDocumento.getText().toString());
        docOcorrencia.setTipodocumento(BuscadorEnum.BuscarDocSolicitacao(spnDocumento.getSelectedItem().toString()));

        docOcorrencia.save();

        ocorrenciaFragment.setDocumentoOcorrencia(docOcorrencia);

        if(txvChamado.getText().toString() != null)
            ocorrenciaFragment.setDataChamado( TempoUtil.stringToTime(txvChamado.getText().toString()));

        if(txvOcorrencia.getText().toString() != null)
            ocorrenciaFragment.setDataOcorrencia( TempoUtil.stringToTime(txvOcorrencia.getText().toString()));

        if(txvAtendimento.getText().toString() != null)
            ocorrenciaFragment.setHoraAtendimento(txvAtendimento.getText().toString());

        if(txvDataAtendimento.getText().toString() != null)
            ocorrenciaFragment.setDataAtendimento((txvDataAtendimento.getText().toString()));

        if(autocOrgaoDestino.getText().toString() != null)
        ocorrenciaFragment.setOrgaoOrigem(autocOrgaoDestino.getText().toString());

        if(autocOrgaoOrigem.getText().toString() != null)
        ocorrenciaFragment.setOrgaoDestino(autocOrgaoOrigem.getText().toString());


        if(edtComandante.getText().toString() != null)
        ocorrenciaFragment.setComandante(edtComandante.getText().toString());


        if(edtPlacaViatura_Letras.getText().toString() != null && edtPlacaViatura_Numeros.getText().toString() != null )
        ocorrenciaFragment.setViatura(edtPlacaViatura_Letras.getText().toString() +"-"+ edtPlacaViatura_Numeros.getText().toString());

        ocorrenciaFragment.save();

        ((ManterPericia) getActivity()).ocorrenciaTransito = ocorrenciaFragment;

    }

    private void AssociarLayout(View view)
    {

        txvChamado = (TextView) view.findViewById(R.id.txv_HoraChamado);
        txvOcorrencia= (TextView) view.findViewById(R.id.txv_HoraOcorrencia);
        txvAtendimento = (TextView) view.findViewById(R.id.txv_Hora_Atendimento_Valor);
        txvDataAtendimento = (TextView)view.findViewById(R.id.txv_Data_Atendimento_Valor);

        edtValorDocumento = (EditText) view.findViewById(R.id.edt_ValorDocumento);

        spnLocal = (Spinner)view.findViewById(R.id.spn_PreservacaoLocal);
        spnColisao = (Spinner)view.findViewById(R.id.spn_SitioColisao);
        spnDocumento = (Spinner) view.findViewById(R.id.spn_TipoDocumentoOcorrencia);
        spnOrgaoPresente = (Spinner) view.findViewById(R.id.spn_Autoridade_Presente);

        edtComandante = (EditText)view.findViewById(R.id.edt_Autoridade_Comandante);

        edtPlacaViatura_Numeros = (EditText)view.findViewById(R.id.edt_PlacaNumeros);
        edtPlacaViatura_Letras = (EditText)view.findViewById(R.id.edt_PlacaLetras);
        edtPlacaViatura_Letras.setFilters(new InputFilter[] {new InputFilter.AllCaps(),new InputFilter.LengthFilter(3)});


        autocOrgaoOrigem = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Origem);
        autocOrgaoDestino = (AutoCompleteTextView) view.findViewById(R.id.auc_Orgao_Destino);
    }

    private ArrayAdapter<String> getDelegacias(Context context) {
     ArrayList<Delegacia> delegas = (ArrayList<Delegacia>) Delegacia.listAll(Delegacia.class);

     String[] delegs = new String[delegas.size()];

       for (int i = 0; i<delegas.size();i++)
       {
           delegs[i] = delegas.get(i).getDescricao();
       }

     return new ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, delegs);
   }


}
