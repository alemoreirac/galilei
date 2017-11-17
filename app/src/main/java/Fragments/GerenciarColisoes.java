package Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import Dialogs.AudioDialog;
import Dialogs.InconclusivoDialog;
import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.R;
import Dialogs.VestigioDialog;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterColisao;
import Enums.Transito.AtoresColisao;
import Enums.ConclusaoTransito;
import Enums.Transito.LocalObjeto;
import Enums.Transito.LocalPedestre;
import Enums.Transito.NomeclaturaFaixas_1;
import Enums.Transito.NomeclaturaFaixas_2;
import Enums.Transito.NomeclaturaFaixas_3;
import Enums.Transito.NomeclaturaFaixas_4;
import Enums.Transito.NomeclaturaFaixas_5;
import Enums.OrientacaoGeograficaComposta;
import Enums.TipoInteracao;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnderecoTransito;
import Model.Transito.EnvolvidoTransito;
import Model.Transito.OcorrenciaTransitoColisao;
import Model.Transito.OcorrenciaTransitoEndereco;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;
import Util.ViewUtil;
import info.hoang8f.android.segmented.SegmentedGroup;

/**
 * Gerenciador de Colisões e Interações entre os atores da dinâmica, preenche as informações de como o fato se sucedeu,
 * E aponta quem foi o responsável por causar aquela interação.
 */

public class GerenciarColisoes extends android.support.v4.app.Fragment implements  Step
{

    int lastCheckedId = 0;
    GerenciarColisoes fragment = null;
    Spinner spnVeiculo1 = null;
    Spinner spnVeiculo2 = null;
    Spinner spnEndereco1 = null;
    Spinner spnEndereco2 = null;
    Spinner spnFaixa1 = null;
    Spinner spnFaixa2 = null;
    Spinner spnSentido1 = null;
    Spinner spnSentido2 = null;
    Spinner spnEnvolvido = null;
    Spinner spnEnvolvido_Local = null;
    Spinner spnVeiculo1Causa = null;
    Spinner spnVeiculo2Causa = null;
    Spinner spnObjeto_Local = null;
    Spinner spnAnimal_Local = null;
    Spinner spnTipoInteracao = null;
    Spinner spnJustificativaInconclusao = null;
    EditText edtEnvolvido_Observacao = null;
    EditText edtObjetoDescricao = null;
    EditText edtObjetoObservacao = null;
    EditText edtDistancia = null;
    EditText edtAnimalDescricao = null;
    EditText edtAnimalObservacao = null;
    EditText edtObservacoes = null;
    CheckBox cxbCulpadoVeiculo1 = null;
    CheckBox cxbCulpadoVeiculo2 = null;
    CheckBox cxbContraMaoVeiculo1 = null;
    CheckBox cxbContraMaoVeiculo2 = null;
    CheckBox cxbCausaVeiculo1 = null;
    CheckBox cxbCausaVeiculo2 = null;
    CheckBox cxbCausaPedestre = null;
    public LinearLayout ll_Vestigio = null;
    public LinearLayout ll_Inconclusao = null;
    ImageButton imgbGravar_Colisao = null;
    SegmentedGroup sgOpcoes = null;
    RadioButton rbtnOpcaoVeiculo = null;
    RadioButton rbtnOpcaoPedestre = null;
    RadioButton rbtnOpcaoObjeto = null;
    RadioButton rbtnOpcaoAnimal = null;
    RadioButton rbtnNenhum = null;
    Button btnSave = null;
    FloatingActionButton fabColisoes = null;
    RelativeLayout rltvVeiculo2 = null;
    RelativeLayout rltvVeiculo1 = null;
    RelativeLayout rltvObjeto = null;
    RelativeLayout rltvPedestre = null;
    RelativeLayout rltvNenhum = null;
    RelativeLayout rltvAnimal = null;
    RelativeLayout rltvBlock_Veiculo1;
    RelativeLayout rltvBlock_Veiculo2;
    RelativeLayout rltvBlock_Envolvido;
    RelativeLayout rltvBlock_Animal;
    RelativeLayout rltvBlock_Objeto;
    ListView listColisoes = null;
    View mView = null;
    OcorrenciaTransito ocorrenciaTransitoColisao = null;
    List<OcorrenciaTransitoEndereco> enderecosList = null;
    ArrayList<EnderecoTransito> enderecoModel = null;
    ArrayList<Veiculo> veiculosModel = null;
    List<OcorrenciaTransitoVeiculo> veiculosList = null;
    List<OcorrenciaTransitoEnvolvido> envolvidosList = null;
    List<OcorrenciaTransitoColisao> colisoesList = null;
    ArrayList<EnvolvidoTransito> envolvidosModel = null;
    ArrayList<ColisaoTransito> colisaoTransitoModel = null;
    RelativeLayout rltvBase = null;
    ColisaoTransito colisaoTransito = null;
    OcorrenciaTransitoColisao ocorrenciaColisao = null;
    AdapterColisao adp = null;
    AudioDialog dialogFragment = null;
    ArrayList<String> tipoColisaoAdapter = null;
    InconclusivoDialog inconclusivoDialog = null;
    private OnFragmentInteractionListener mListener;

    public GerenciarColisoes()
    {
    }

    public static GerenciarColisoes newInstance()
    {
        GerenciarColisoes fragment = new GerenciarColisoes();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_colisoes, container, false);
    }

    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);
        mView = view;

    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    @Override
    public VerificationError verifyStep()
    {
        return null;
    }



    @Override
    public void onSelected()
    {
        fragment = this;
        ocorrenciaTransitoColisao = ((ManterPericia) getActivity()).ocorrenciaTransito;

        envolvidosList = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        envolvidosModel = new ArrayList<>();

        enderecosList = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        enderecoModel = new ArrayList<>();

        veiculosList = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        veiculosModel = new ArrayList<>();

        colisoesList = OcorrenciaTransitoColisao.find(OcorrenciaTransitoColisao.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        colisaoTransitoModel = new ArrayList<>();

        for (OcorrenciaTransitoColisao oc : colisoesList)
            colisaoTransitoModel.add(oc.getColisaoTransito());

        AssociarLayout(mView);
        AssociarEventos(mView);
        PovoarSpinners(getContext());

        //((ManterPericia) getActivity()).toolbar.setTitle("Colisões");
        ((ManterPericia) getActivity()).txvToolbarTitulo.setText("Colisões");

        HabilitarInterface(false);

        adp = new AdapterColisao(colisaoTransitoModel, getActivity());
        listColisoes.setAdapter(adp);


    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    private void HabilitarInterface(boolean value)
    {
        ViewUtil.modifyAll(rltvBase, value);
        ViewUtil.modifyAll(rltvAnimal, value);
        ViewUtil.modifyAll(rltvNenhum, value);
        ViewUtil.modifyAll(rltvObjeto, value);
        ViewUtil.modifyAll(rltvVeiculo2, value);
        ViewUtil.modifyAll(rltvVeiculo1, value);
        ViewUtil.modifyAll(rltvPedestre, value);

        rbtnNenhum.setEnabled(value);
        rbtnOpcaoVeiculo.setEnabled(value);
        rbtnOpcaoAnimal.setEnabled(value);
        rbtnOpcaoObjeto.setEnabled(value);
        rbtnOpcaoPedestre.setEnabled(value);

        if (veiculosModel.size() < 2 && value)
        {
            rbtnOpcaoVeiculo.setEnabled(false);
        }
        if (envolvidosModel.size() < 1 && value)
        {
            rbtnOpcaoPedestre.setEnabled(false);
        }

        spnVeiculo1Causa.setEnabled(false);
        spnVeiculo2Causa.setEnabled(false);
    }


    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }

    private void AssociarLayout(View v)
    {
        spnEndereco1 = (Spinner) v.findViewById(R.id.spn_Endereco_Veiculo1);
        spnEndereco2 = (Spinner) v.findViewById(R.id.spn_Endereco_Veiculo2);

        spnFaixa1 = (Spinner) v.findViewById(R.id.spn_Faixa_Veiculo1);
        spnFaixa2 = (Spinner) v.findViewById(R.id.spn_Faixa_Veiculo2);

        spnSentido1 = (Spinner) v.findViewById(R.id.spn_Sentido_Veiculo1);
        spnSentido2 = (Spinner) v.findViewById(R.id.spn_Sentido_Veiculo2);

        spnVeiculo1 = (Spinner) v.findViewById(R.id.spn_Veiculo1);
        spnVeiculo2 = (Spinner) v.findViewById(R.id.spn_Veiculo2);
        spnJustificativaInconclusao = (Spinner) v.findViewById(R.id.spn_dialog_Justificativa);
        spnEnvolvido = (Spinner) v.findViewById(R.id.spn_Envolvido_Dinamica);
        spnEnvolvido_Local = (Spinner) v.findViewById(R.id.spn_Envolvido_Posicao);
        edtEnvolvido_Observacao = (EditText) v.findViewById(R.id.edt_Envolvido_Distancia);

        spnObjeto_Local = (Spinner) v.findViewById(R.id.spn_Objeto_Posicao);
        spnAnimal_Local = (Spinner) v.findViewById(R.id.spn_Animal_Posicao);

        spnTipoInteracao = (Spinner) v.findViewById(R.id.spn_Tipo_Interacao);

        cxbContraMaoVeiculo1 = (CheckBox) v.findViewById(R.id.cxb_Veiculo1_ContraMao);
        cxbCulpadoVeiculo1 = (CheckBox) v.findViewById(R.id.cxb_Veiculo1_Culpado);

        cxbContraMaoVeiculo2 = (CheckBox) v.findViewById(R.id.cxb_Veiculo2_ContraMao);
        cxbCulpadoVeiculo2 = (CheckBox) v.findViewById(R.id.cxb_Veiculo2_Culpado);

        imgbGravar_Colisao = (ImageButton) v.findViewById(R.id.imgb_Audio_Colisao);

        edtObservacoes = (EditText) v.findViewById(R.id.edt_Observacao);
        edtDistancia = (EditText) v.findViewById(R.id.edt_Envolvido_Distancia);

        rbtnNenhum = (RadioButton) v.findViewById(R.id.rbtn_Opcao_Nenhum);
        rbtnOpcaoObjeto = (RadioButton) v.findViewById(R.id.rbtn_Opcao_Objeto);
        rbtnOpcaoVeiculo = (RadioButton) v.findViewById(R.id.rbtn_Opcao_Veiculo);
        rbtnOpcaoPedestre = (RadioButton) v.findViewById(R.id.rbtn_Opcao_Pedestre);
        rbtnOpcaoAnimal = (RadioButton) v.findViewById(R.id.rbtn_Opcao_Animal);

        sgOpcoes = (SegmentedGroup) v.findViewById(R.id.sgm_Dinamica);
        sgOpcoes.setTintColor(getResources().getColor(R.color.colorPrimary));

        rltvBlock_Veiculo1 = (RelativeLayout) v.findViewById(R.id.rltv_block_Veiculo1);
        rltvBlock_Veiculo2 = (RelativeLayout) v.findViewById(R.id.rltv_block_Veiculo2);
        rltvBlock_Animal = (RelativeLayout) v.findViewById(R.id.rltv_block_Animal);
        rltvBlock_Objeto = (RelativeLayout) v.findViewById(R.id.rltv_block_Objeto);
        rltvBlock_Envolvido = (RelativeLayout) v.findViewById(R.id.rltv_block_Envolvido);

        rltvObjeto = (RelativeLayout) v.findViewById(R.id.rltv_Objeto);
        rltvPedestre = (RelativeLayout) v.findViewById(R.id.rltv_Envolvido);
        rltvVeiculo2 = (RelativeLayout) v.findViewById(R.id.rltv_Veiculo2);
        rltvVeiculo1 = (RelativeLayout) v.findViewById(R.id.rltv_Veiculo1);
        rltvNenhum = (RelativeLayout) v.findViewById(R.id.rltv_Nenhum);
        rltvAnimal = (RelativeLayout) v.findViewById(R.id.rltv_Animal);

        edtAnimalDescricao = (EditText) v.findViewById(R.id.edt_Animal_Descricao);
        edtAnimalObservacao = (EditText) v.findViewById(R.id.edt_Animal_Observacao);
        edtObjetoDescricao = (EditText) v.findViewById(R.id.edt_Objeto_Descricao);
        edtObjetoObservacao = (EditText) v.findViewById(R.id.edt_Objeto_Observacao);

        rltvBase = (RelativeLayout) v.findViewById(R.id.rltv_Base);

        fabColisoes = (FloatingActionButton) v.findViewById(R.id.fab_Colisao);
        listColisoes = (ListView) v.findViewById(R.id.lstv_Colisoes);

        btnSave = (Button) v.findViewById(R.id.btn_Salvar_Colisao);

        spnVeiculo1Causa = (Spinner) v.findViewById(R.id.spn_Causa_Veiculo1);

        spnVeiculo2Causa = (Spinner) v.findViewById(R.id.spn_Causa_Veiculo2);

        cxbCausaVeiculo1 = (CheckBox) v.findViewById(R.id.cxb_Veiculo1_Culpado);
        cxbCausaVeiculo2 = (CheckBox) v.findViewById(R.id.cxb_Veiculo2_Culpado);
        cxbCausaPedestre = (CheckBox) v.findViewById(R.id.cxb_Envolvido_Culpado);
        //cxbInconclusivo = (CheckBox) v.findViewById(R.id.cxb_in);
        ll_Inconclusao = (LinearLayout) v.findViewById(R.id.ll_Inconclusao);
        ll_Vestigio = (LinearLayout) v.findViewById(R.id.ll_Vestigio);

        spnVeiculo1Causa.setEnabled(false);
        spnVeiculo2Causa.setEnabled(false);
    }


    private void AssociarEventos(final View view)
    {
        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                SalvarColisao();
            }
        });
        ll_Vestigio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {

                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                VestigioDialog vestigioDialog = new VestigioDialog();


                Bundle bd = new Bundle();

                bd.putLong("ColisaoID", colisaoTransito.getId());

                vestigioDialog.setArguments(bd);

                vestigioDialog.show(fm, "Seleção");
            }
        });

        ll_Inconclusao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {

                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();

                inconclusivoDialog = new InconclusivoDialog();

                Bundle bd = new Bundle();

                if(!rbtnOpcaoPedestre.isChecked())
                    bd.putBoolean("SemEvasores", true);
                else
                    bd.putBoolean("SemEvasores", false);

                bd.putLong("ColisaoID",colisaoTransito.getId());

                inconclusivoDialog.setTargetFragment(fragment,1);

                inconclusivoDialog.setArguments(bd);


                inconclusivoDialog.show(fm, "Seleção");
            }
        });

        listColisoes.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                listColisoes.setItemChecked(position, true);

                colisaoTransito = colisaoTransitoModel.get(position);

                HabilitarInterface(true);

                LimparCampos();
                CarregarColisao();

                spnVeiculo1Causa.setSelection(0);
                spnVeiculo1Causa.setEnabled(false);

                spnVeiculo2Causa.setSelection(0);
                spnVeiculo2Causa.setEnabled(false);

                try
                {
                    ocorrenciaColisao = OcorrenciaTransitoColisao.find(OcorrenciaTransitoColisao.class,
                            "ocorrencia_transito = ? and colisao_transito = ?",
                            ocorrenciaTransitoColisao
                                    .getId().toString(), colisaoTransito.getId().toString()).get(0);
                } catch (Exception e)
                {
                    ocorrenciaColisao = new OcorrenciaTransitoColisao();
                }
            }
        });

        listColisoes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v,
                                           final int position, long id)
            {
                AlertDialog.Builder builder;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
                    builder = new AlertDialog.Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);

                else
                    builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Deletar Colisão")
                        .setMessage("Você deseja deletar esta Colisão?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                OcorrenciaTransitoColisao ocorrenciaColisao = colisoesList.get(position);

                                adp.remove(ocorrenciaColisao.getColisaoTransito());

                                ocorrenciaColisao.getColisaoTransito().delete();

                                ocorrenciaColisao.delete();

                                colisoesList.remove(position);

                                LimparCampos();

                                HabilitarInterface(false);
                                Toast.makeText(getActivity(), "Colisão Deletado com sucesso!", Toast.LENGTH_LONG).show();
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
                return true;
            }
        });

        spnVeiculo2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                spnVeiculo2.setBackgroundColor(Color.TRANSPARENT);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

        spnEndereco1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                EnderecoTransito enderecoTransito = (EnderecoTransito) spnEndereco1.getSelectedItem();

                ArrayList<String> faixas = new ArrayList<String>();

                switch (enderecoTransito.getNumFaixas())
                {
                    case 1:
                        faixas.add(NomeclaturaFaixas_1.FAIXA_UNICA.getValor());
                        break;
                    case 2:
                        for (NomeclaturaFaixas_2 faixa : NomeclaturaFaixas_2.values())
                            faixas.add(faixa.getValor());
                        break;
                    case 3:
                        for (NomeclaturaFaixas_3 faixa : NomeclaturaFaixas_3.values())
                            faixas.add(faixa.getValor());
                        break;
                    case 4:
                        for (NomeclaturaFaixas_4 faixa : NomeclaturaFaixas_4.values())
                            faixas.add(faixa.getValor());
                        break;
                    case 5:
                        for (NomeclaturaFaixas_5 faixa : NomeclaturaFaixas_5.values())
                            faixas.add(faixa.getValor());
                        break;
                }
                spnFaixa1.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, faixas));

                PovoarSentido(enderecoTransito.getSentidoVia(),spnSentido1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });

        spnEndereco2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                EnderecoTransito enderecoTransito = (EnderecoTransito) spnEndereco2.getSelectedItem();

                ArrayList<String> faixas = new ArrayList<String>();

                switch (enderecoTransito.getNumFaixas())
                {
                    case 1:
                        faixas.add(NomeclaturaFaixas_1.FAIXA_UNICA.getValor());
                        break;
                    case 2:
                        for (NomeclaturaFaixas_2 faixa : NomeclaturaFaixas_2.values())
                            faixas.add(faixa.getValor());
                        break;
                    case 3:
                        for (NomeclaturaFaixas_3 faixa : NomeclaturaFaixas_3.values())
                            faixas.add(faixa.getValor());
                        break;
                    case 4:
                        for (NomeclaturaFaixas_4 faixa : NomeclaturaFaixas_4.values())
                            faixas.add(faixa.getValor());
                        break;
                    case 5:
                        for (NomeclaturaFaixas_5 faixa : NomeclaturaFaixas_5.values())
                            faixas.add(faixa.getValor());
                        break;
                }

                spnFaixa2.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, faixas));

                PovoarSentido(enderecoTransito.getSentidoVia(),spnSentido2);

//                ArrayList<String> sentidos = new ArrayList<String>();
//
//                if(enderecoTransito.getSentidoVia()!=null){
//                switch (enderecoTransito.getSentidoVia())
//                {
//                    case SUDOESTE_NORDESTE:
//                    case NORDESTE_SUDOESTE:
//                        sentidos.add(OrientacaoGeograficaComposta.NORDESTE_SUDOESTE.getValor());
//                        sentidos.add(OrientacaoGeograficaComposta.SUDOESTE_NORDESTE.getValor());
//                        break;
//                    case NORTE_SUL:
//                    case SUL_NORTE:
//                        sentidos.add(OrientacaoGeograficaComposta.NORTE_SUL.getValor());
//                        sentidos.add(OrientacaoGeograficaComposta.SUL_NORTE.getValor());
//                        break;
//                    case SUDESTE_NOROESTE:
//                    case NOROESTE_SUDESTE:
//                        sentidos.add(OrientacaoGeograficaComposta.SUDESTE_NOROESTE.getValor());
//                        sentidos.add(OrientacaoGeograficaComposta.NOROESTE_SUDESTE.getValor());
//                        break;
//                    case OESTE_LESTE:
//                    case LESTE_OESTE:
//                        sentidos.add(OrientacaoGeograficaComposta.OESTE_LESTE.getValor());
//                        sentidos.add(OrientacaoGeograficaComposta.LESTE_OESTE.getValor());
//                        break;
//                }
//
//
//                spnSentido2.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_dropdown_item, sentidos));
           // }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }

        });


        //sgOpcoes
        rbtnOpcaoObjeto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                if (lastCheckedId != view.getId())
                {
                    Toast.makeText(getContext(), "Objeto", Toast.LENGTH_SHORT).show();
                    SetVisibility(View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                    lastCheckedId = view.getId();

                    tipoColisaoAdapter = new ArrayList<>();

                    for (TipoInteracao tc : TipoInteracao.values())
                    {
                        tipoColisaoAdapter.add(tc.getValor());
                    }
                    spnTipoInteracao.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoColisaoAdapter));


                    //Seleciona o Choque e força que fique só ele
                    spnTipoInteracao.setSelection(0);
                    spnTipoInteracao.setEnabled(false);

                }
            }
        });
        rbtnOpcaoPedestre.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                if (lastCheckedId != view.getId())
                {
                    Toast.makeText(getContext(), "Pedestre", Toast.LENGTH_SHORT).show();
                    SetVisibility(View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE, View.INVISIBLE);
                    lastCheckedId = view.getId();
                }

                tipoColisaoAdapter = new ArrayList<>();

                for (TipoInteracao tc : TipoInteracao.values())
                {
                    tipoColisaoAdapter.add(tc.getValor());
                }
                spnTipoInteracao.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoColisaoAdapter));

                //Seleciona o Atropelamento e força que fique só ele

                edtDistancia.setEnabled(false);
                edtDistancia.setText("");

                spnTipoInteracao.setSelection(1);
                spnTipoInteracao.setEnabled(false);
            }
        });

        rbtnOpcaoVeiculo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                if (lastCheckedId != view.getId())
                {
                    Toast.makeText(getContext(), "Veiculo", Toast.LENGTH_SHORT).show();
                    SetVisibility(View.VISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE);
                    lastCheckedId = view.getId();
                }
                tipoColisaoAdapter = new ArrayList<>();

                for (TipoInteracao tc : TipoInteracao.values())
                {
                    if (tc != TipoInteracao.ADERNAMENTO && tc != TipoInteracao.ATROPELAMENTO && tc != TipoInteracao.CHOQUE)
                        tipoColisaoAdapter.add(tc.getValor());
                }

                spnTipoInteracao.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoColisaoAdapter));

                spnTipoInteracao.setEnabled(true);
            }
        });

        rbtnOpcaoAnimal.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                if (lastCheckedId != view.getId())
                {
                    Toast.makeText(getContext(), "Animal", Toast.LENGTH_SHORT).show();
                    SetVisibility(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE, View.INVISIBLE);
                    lastCheckedId = view.getId();
                }

                tipoColisaoAdapter = new ArrayList<>();

                for (TipoInteracao tc : TipoInteracao.values())
                {
                    tipoColisaoAdapter.add(tc.getValor());
                }

                spnTipoInteracao.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoColisaoAdapter));


                //Seleciona o Atropelamento e força que fique só ele
                spnTipoInteracao.setSelection(1);
                spnTipoInteracao.setEnabled(false);
            }
        });


        rbtnNenhum.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                if (lastCheckedId != view.getId())
                {
                    Toast.makeText(getContext(), "Nenhum", Toast.LENGTH_SHORT).show();
                    SetVisibility(View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.INVISIBLE, View.VISIBLE);
                    lastCheckedId = view.getId();
                }

                tipoColisaoAdapter = new ArrayList<>();

                for (TipoInteracao tc : TipoInteracao.values())
                {
                    tipoColisaoAdapter.add(tc.getValor());
                }

                spnTipoInteracao.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoColisaoAdapter));

                //Seleciona o Adernamento e força que fique só ele
                spnTipoInteracao.setSelection(2);
                spnTipoInteracao.setEnabled(false);
            }
        });


        imgbGravar_Colisao.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {

                FragmentManager fm = getActivity().getFragmentManager();
                dialogFragment = new AudioDialog();

                Bundle bd = new Bundle();
                bd.putString("Local", "conclusão");
                bd.putBoolean("Colisao", true);
                bd.putLong("ColisaoId", colisaoTransito.getId());
                //if(colisaoTransito.getGravacaoObservacoes() != null)
                //bd.putByteArray("Arquivo",colisaoTransito.getGravacaoObservacoes().getArquivo());
                dialogFragment.setArguments(bd);
                dialogFragment.show(fm, "Seleção");
            }
        });
        fabColisoes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                if (veiculosModel.size() == 0 || enderecoModel.size() == 0)
                    Toast.makeText(getActivity(), "Insira ao menos um Endereço e Veículo!", Toast.LENGTH_LONG).show();
                else
                {
                    TipoColisaoDialog tdc = new TipoColisaoDialog();


                    colisaoTransito = new ColisaoTransito();
                    colisaoTransito.setVeiculo1(veiculosModel.get(0));
                    colisaoTransito.setEndereco_veiculo1(enderecoModel.get(0));

                    InterfaceInconclusiva(false);
                }
            }
        });

        spnEnvolvido_Local.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if (spnEnvolvido_Local.getSelectedItem() == LocalPedestre.PROXIMO_PASSARELA.getValor()
                        || spnEnvolvido_Local.getSelectedItem() == LocalPedestre.PROXIMO_FAIXA.getValor())

                    edtDistancia.setEnabled(true);
                else
                {
                    edtDistancia.setEnabled(false);
                    edtDistancia.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {

            }
        });


        cxbCausaVeiculo1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbCausaVeiculo1.isChecked())
                {
                    spnVeiculo1Causa.setEnabled(true);
                } else
                {
                    spnVeiculo1Causa.setSelection(0);
                    spnVeiculo1Causa.setEnabled(false);
                }
            }
        });
        cxbCausaVeiculo2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbCausaVeiculo2.isChecked())
                {
                    spnVeiculo2Causa.setEnabled(true);
                } else
                {
                    spnVeiculo2Causa.setSelection(0);
                    spnVeiculo2Causa.setEnabled(false);
                }
            }
        });
    }

    private void CarregarColisao()
    {
        spnVeiculo1.setSelection(BuscadorEnum.getVeiculoIndexById(spnVeiculo1, colisaoTransito.getVeiculo1().getId()));

        PovoarSentido(colisaoTransito.getSentido_veiculo1(),spnSentido1);

        if (colisaoTransito.getSentido_veiculo1() == null)
            spnSentido1.setSelection(0);
        else
            spnSentido1.setSelection(BuscadorEnum.getIndex(spnSentido1, colisaoTransito.getSentido_veiculo1().getValor()));

        spnEndereco1.setSelection(BuscadorEnum.getEnderecoIndexById(spnEndereco1, colisaoTransito.getEndereco_veiculo1().getId()));
        cxbCulpadoVeiculo1.setChecked(colisaoTransito.isVeiculo1Causador());

        if (colisaoTransito.isVeiculo1Causador())
            spnVeiculo1Causa.setEnabled(true);

        if (colisaoTransito.isVeiculo1Causador() &&
                colisaoTransito.getConclusaoVeiculo1() != null)
        {
            spnVeiculo1Causa.setEnabled(true);
            spnVeiculo1Causa.setSelection(BuscadorEnum.getIndex(spnVeiculo1Causa,
                    colisaoTransito.getConclusaoVeiculo1().getValor()));
        }

        cxbContraMaoVeiculo1.setChecked(colisaoTransito.isVeiculo1ContraMao());

        edtObservacoes.setText(colisaoTransito.getObservacoesColisao());

        switch (colisaoTransito.getAtoresColisao())
        {
            case ANIMAL:
                edtAnimalDescricao.setText(colisaoTransito.getAnimalDescricao());
                edtAnimalObservacao.setText(colisaoTransito.getAnimalObservacao());
                if (colisaoTransito.getAnimalPosicao() == null)
                    spnAnimal_Local.setSelection(0);
                else
                    spnAnimal_Local.setSelection(BuscadorEnum.getIndex(spnAnimal_Local,
                            colisaoTransito.getAnimalPosicao().getValor()));
                rbtnOpcaoAnimal.performClick();
                break;
            case OBJETO:
                edtObjetoDescricao.setText(colisaoTransito.getObjetoDescricao());
                edtObjetoObservacao.setText(colisaoTransito.getObjetoObservacao());
                if (colisaoTransito.getObjetoPosicao() == null)
                    spnObjeto_Local.setSelection(0);
                else
                    spnObjeto_Local.setSelection(BuscadorEnum.getIndex(spnObjeto_Local,
                            colisaoTransito.getObjetoPosicao().getValor()));
                rbtnOpcaoObjeto.performClick();
                break;
            case VEICULO:

                PovoarSentido(colisaoTransito.getSentido_veiculo2(),spnSentido2);

                if (colisaoTransito.getSentido_veiculo2() == null)
                    spnSentido2.setSelection(0);
                else
                    spnSentido2.setSelection(BuscadorEnum.getIndex(spnSentido2, colisaoTransito.getSentido_veiculo2().getValor()));

                if (colisaoTransito.getEndereco_veiculo2() == null)
                    spnEndereco2.setSelection(0);
                else
                    spnEndereco2.setSelection(BuscadorEnum.getEnderecoIndexById(spnEndereco2, colisaoTransito.getEndereco_veiculo2().getId()));

                if (colisaoTransito.getVeiculo2() == null)
                    spnVeiculo2.setSelection(1);
                else
                    spnVeiculo2.setSelection(BuscadorEnum.getVeiculoIndexById(spnVeiculo2, colisaoTransito.getVeiculo2().getId()));

                if (colisaoTransito.isVeiculo2Causador() &&
                        colisaoTransito.getConclusaoVeiculo2() != null)
                {
                    spnVeiculo2Causa.setEnabled(true);
                    spnVeiculo2Causa.setSelection(BuscadorEnum.getIndex(spnVeiculo2Causa,
                            colisaoTransito.getConclusaoVeiculo2().getValor()));
                }


                cxbCulpadoVeiculo2.setChecked(colisaoTransito.isVeiculo2Causador());
                cxbContraMaoVeiculo2.setChecked(colisaoTransito.isVeiculo2ContraMao());
                rbtnOpcaoVeiculo.performClick();
                break;
            case PEDESTRE:
                if (colisaoTransito.getPedestre() == null)
                    spnEnvolvido.setSelection(0);
                else
                    spnEnvolvido.setSelection(BuscadorEnum.getEnvolvidoIndexById(spnEnvolvido, colisaoTransito.getPedestre().getId()));
                if (colisaoTransito.getDistancia() != 0)
                {
                    edtDistancia.setEnabled(true);
                    edtDistancia.setText(Integer.toString(colisaoTransito.getDistancia()));
                }
                else
                    edtDistancia.setText("");
                if (colisaoTransito.getPosicaoPedestre() == null)
                    spnEnvolvido_Local.setSelection(0);
                else
                {
                    spnEnvolvido_Local.setSelection(BuscadorEnum.getIndex(spnEnvolvido_Local,
                            colisaoTransito.getPosicaoPedestre().getValor()));
                    if (colisaoTransito.getPosicaoPedestre() == LocalPedestre.PROXIMO_FAIXA
                            || colisaoTransito.getPosicaoPedestre() == LocalPedestre.PROXIMO_PASSARELA)
                        edtDistancia.setEnabled(true);
                    else
                    {
                        edtDistancia.setEnabled(false);
                        edtDistancia.setText("");
                    }
                }

                cxbCausaPedestre.setChecked(colisaoTransito.getCulpaPedestre());
                rbtnOpcaoPedestre.performClick();

                break;
            case NENHUM:
                rbtnNenhum.performClick();
                break;
        }

        if(colisaoTransito.getInconclusivo())
            InterfaceInconclusiva(true);
        else
            InterfaceInconclusiva(false);
    }


    private void SalvarColisaoInconclusiva()
    {
        colisaoTransito.AnularCampos();


            colisaoTransito.setEndereco_veiculo1((EnderecoTransito) spnEndereco1.getSelectedItem());
            colisaoTransito.setVeiculo1((Veiculo) spnVeiculo1.getSelectedItem());

            if(rbtnOpcaoPedestre.isChecked())
            {
                colisaoTransito.setAtoresColisao(AtoresColisao.PEDESTRE);
                colisaoTransito.setPedestre((EnvolvidoTransito) spnEnvolvido.getSelectedItem());
            }

            if(rbtnOpcaoVeiculo.isChecked())
                colisaoTransito.setAtoresColisao(AtoresColisao.VEICULO);

            if(rbtnOpcaoAnimal.isChecked())
                colisaoTransito.setAtoresColisao(AtoresColisao.ANIMAL);

            if(rbtnOpcaoObjeto.isChecked())
                colisaoTransito.setAtoresColisao(AtoresColisao.OBJETO);


            colisaoTransito.setInconclusivo(true);
            colisaoTransito.setJustificativaInconclusao(BuscadorEnum.BuscarJustificativa(inconclusivoDialog.spnJustificativa.getSelectedItem().toString()));

            switch (inconclusivoDialog.spnJustificativa.getSelectedItem().toString())
            {
                case "Envolvido se evadiu":
                    colisaoTransito.setEnvolvidoEvadido((EnvolvidoTransito) inconclusivoDialog.spnEvadido.getSelectedItem());
                    colisaoTransito.setVeiculoEvadido(null);
                    break;
                case "Condutor se evadiu":
                    colisaoTransito.setEnvolvidoEvadido(null);
                    colisaoTransito.setVeiculoEvadido((Veiculo)inconclusivoDialog.spnEvadido.getSelectedItem());
                    break;
                default:
                    break;

        }

        colisaoTransito.setJustificativaInconclusao(null);
        colisaoTransito.setVeiculoEvadido(null);
        colisaoTransito.setEnvolvidoEvadido(null);

        colisaoTransito.setObservacoesColisao(edtObservacoes.getText().toString());
        colisaoTransito.setTipoInteracao(BuscadorEnum.BuscarTipoInteracao(spnTipoInteracao.getSelectedItem().toString()));


        colisaoTransito.save();

        ocorrenciaColisao.setOcorrenciaTransito(ocorrenciaTransitoColisao);
        ocorrenciaColisao.setColisaoTransito(colisaoTransito);
        ocorrenciaColisao.save();

        adp.notifyDataSetChanged();

        Toast.makeText(getContext(), "Colisão salva com sucesso!", Toast.LENGTH_LONG).show();
    }

    private void SalvarColisao()
    {
        colisaoTransito.AnularCampos();

        if(inconclusivoDialog!= null)
        {
            if(inconclusivoDialog.cxbInconclusivo.isChecked())

                SalvarColisaoInconclusiva();

        }

        colisaoTransito.setEndereco_veiculo1((EnderecoTransito) spnEndereco1.getSelectedItem());
        colisaoTransito.setVeiculo1((Veiculo) spnVeiculo1.getSelectedItem());
        colisaoTransito.setSentido_veiculo1(BuscadorEnum.BuscarOrientacaoComposta(spnSentido1.getSelectedItem().toString()));
        colisaoTransito.setVeiculo1Causador(cxbCulpadoVeiculo1.isChecked());
        colisaoTransito.setVeiculo1_Faixa(spnFaixa1.getSelectedItemPosition() + 1);
        colisaoTransito.setVeiculo1ContraMao(cxbContraMaoVeiculo1.isChecked());


            colisaoTransito.setConclusaoVeiculo1(BuscadorEnum.BuscarConclusao(spnVeiculo1Causa
                    .getSelectedItem().toString()));

        //colisaoTransito
        if (rbtnNenhum.isChecked())
        {
            colisaoTransito.setAtoresColisao(AtoresColisao.NENHUM);
        }
        if (rbtnOpcaoVeiculo.isChecked())
        {
            if (((Veiculo) spnVeiculo1.getSelectedItem()).getId() == ((Veiculo) spnVeiculo2.getSelectedItem()).getId())
            {
                Toast.makeText(getContext(), "Os veículos envolvidos tem que ser diferentes!", Toast.LENGTH_SHORT).show();
                spnVeiculo2.setBackgroundColor(Color.RED);

                return;
            }
            colisaoTransito.setAtoresColisao(AtoresColisao.VEICULO);
            colisaoTransito.setEndereco_veiculo2((EnderecoTransito) spnEndereco2.getSelectedItem());
            colisaoTransito.setVeiculo2((Veiculo) spnVeiculo2.getSelectedItem());
            colisaoTransito.setSentido_veiculo2(BuscadorEnum.BuscarOrientacaoComposta(spnSentido2.getSelectedItem().toString()));
            colisaoTransito.setVeiculo2Causador(cxbCulpadoVeiculo2.isChecked());
            colisaoTransito.setVeiculo2_Faixa(spnFaixa2.getSelectedItemPosition() + 1);
            colisaoTransito.setVeiculo2ContraMao(cxbContraMaoVeiculo2.isChecked());

            if (cxbCulpadoVeiculo2.isChecked())
                colisaoTransito.setConclusaoVeiculo2(BuscadorEnum.BuscarConclusao
                        (spnVeiculo2Causa.getSelectedItem().toString()));
        }
        if (rbtnOpcaoPedestre.isChecked())
        {
            colisaoTransito.setAtoresColisao(AtoresColisao.PEDESTRE);
            colisaoTransito.setPedestre((EnvolvidoTransito) spnEnvolvido.getSelectedItem());
            colisaoTransito.setPosicaoPedestre(BuscadorEnum.BuscarLocalPedestre(spnEnvolvido_Local.getSelectedItem().toString()));
            colisaoTransito.setCulpaPedestre(cxbCausaPedestre.isChecked());
            try
            {
                colisaoTransito.setDistancia(Integer.parseInt(edtDistancia.getText().toString()));
            } catch (Exception e)
            {
                colisaoTransito.setDistancia(0);
            }
        }
        if (rbtnOpcaoObjeto.isChecked())
        {
            colisaoTransito.setAtoresColisao(AtoresColisao.OBJETO);
            colisaoTransito.setObjetoObservacao(edtObjetoObservacao.getText().toString());
            colisaoTransito.setObjetoDescricao(edtObjetoDescricao.getText().toString());
            colisaoTransito.setObjetoPosicao(BuscadorEnum.BuscarLocalObjeto(spnObjeto_Local.getSelectedItem().toString()));
        }
        if (rbtnOpcaoAnimal.isChecked())
        {
            colisaoTransito.setAtoresColisao(AtoresColisao.ANIMAL);
            colisaoTransito.setAnimalObservacao(edtAnimalObservacao.getText().toString());
            colisaoTransito.setAnimalDescricao(edtAnimalDescricao.getText().toString());
            colisaoTransito.setAnimalPosicao(BuscadorEnum.BuscarLocalObjeto(spnAnimal_Local.getSelectedItem().toString()));
        }

        colisaoTransito.setObservacoesColisao(edtObservacoes.getText().toString());
        colisaoTransito.setTipoInteracao(BuscadorEnum.BuscarTipoInteracao(spnTipoInteracao.getSelectedItem().toString()));

        //  if(dialogFragment != null)
        //  colisaoTransito.setAudioObservacoes(dialogFragment.Arquivo);

//        if(inconclusivoDialog != null)
//        {
//            colisaoTransito.setInconclusivo(inconclusivoDialog.cxbInconclusivo.isChecked());
//            colisaoTransito.setJustificativaInconclusao(BuscadorEnum.BuscarJustificativa(inconclusivoDialog.spnJustificativa.getSelectedItem().toString()));
//            switch (inconclusivoDialog.spnJustificativa.getSelectedItem().toString())
//            {
//                case "Envolvido se evadiu":
//                    colisaoTransito.setEnvolvidoEvadido((EnvolvidoTransito) inconclusivoDialog.spnEvadido.getSelectedItem());
//                    colisaoTransito.setVeiculoEvadido(null);
//                    break;
//                case "Condutor se evadiu":
//                    colisaoTransito.setEnvolvidoEvadido(null);
//                    colisaoTransito.setVeiculoEvadido((Veiculo)inconclusivoDialog.spnEvadido.getSelectedItem());
//                    break;
//                default:
//                    break;
//            }
//        }
//        else
//        {
            colisaoTransito.setJustificativaInconclusao(null);
            colisaoTransito.setVeiculoEvadido(null);
            colisaoTransito.setEnvolvidoEvadido(null);
//        }

        colisaoTransito.save();

        ocorrenciaColisao.setOcorrenciaTransito(ocorrenciaTransitoColisao);
        ocorrenciaColisao.setColisaoTransito(colisaoTransito);
        ocorrenciaColisao.save();

        adp.notifyDataSetChanged();

        Toast.makeText(getContext(), "Colisão salva com sucesso!", Toast.LENGTH_LONG).show();
    }


    private void LimparCampos()
    {
        spnEnvolvido.setSelection(0);
        spnEndereco1.setSelection(0);
        spnEndereco2.setSelection(0);

        spnVeiculo1.setSelection(0);
        spnVeiculo2.setSelection(0);

        spnObjeto_Local.setSelection(0);
        spnEnvolvido_Local.setSelection(0);

        spnSentido2.setSelection(0);
        spnSentido1.setSelection(0);

        spnFaixa1.setSelection(0);
        spnFaixa2.setSelection(0);

        spnTipoInteracao.setSelection(0);

        edtObservacoes.setText("");
        edtEnvolvido_Observacao.setText("");
        edtObjetoDescricao.setText("");
        edtObjetoObservacao.setText("");

        edtAnimalDescricao.setText("");
        edtAnimalObservacao.setText("");

        cxbCulpadoVeiculo1.setChecked(false);
        cxbContraMaoVeiculo1.setChecked(false);
        cxbContraMaoVeiculo2.setChecked(false);
        cxbContraMaoVeiculo2.setChecked(false);
        cxbCulpadoVeiculo1.setChecked(false);
        cxbCulpadoVeiculo2.setChecked(false);
        cxbCausaPedestre.setChecked(false);

    }

    private void PovoarSentido(OrientacaoGeograficaComposta sentido, Spinner spinner)
    {
        ArrayList<String> sentidos = new ArrayList<String>();

        if (sentido!= null)
        {
            switch (sentido)
            {
                case NORDESTE_SUDOESTE:
                case SUDOESTE_NORDESTE:
                    sentidos.add("Nordeste-Sudoeste");
                    sentidos.add("Sudoeste-Nordeste");
                    break;
                case NORTE_SUL:
                case SUL_NORTE:
                    sentidos.add("Norte-Sul");
                    sentidos.add("Sul-Norte");
                    break;
                case SUDESTE_NOROESTE:
                case NOROESTE_SUDESTE:
                    sentidos.add("Sudeste-Noroeste");
                    sentidos.add("Noroeste-Sudeste");
                    break;
                case OESTE_LESTE:
                case LESTE_OESTE:
                    sentidos.add("Leste-Oeste");
                    sentidos.add("Oeste-Leste");
                    break;
            }
            spinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, sentidos));
        }
    }

    private void PovoarSpinners(Context ctx)
    {
        for (OcorrenciaTransitoEndereco ot : enderecosList)
        {
            enderecoModel.add(ot.getEnderecoTransito());
        }
        spnEndereco1.setAdapter(new ArrayAdapter<EnderecoTransito>(ctx, android.R.layout.simple_spinner_dropdown_item, enderecoModel));
        spnEndereco2.setAdapter(new ArrayAdapter<EnderecoTransito>(ctx, android.R.layout.simple_spinner_dropdown_item, enderecoModel));

        for (OcorrenciaTransitoVeiculo ov : veiculosList)
        {
            veiculosModel.add(ov.getVeiculo());
        }

        spnVeiculo1.setAdapter(new ArrayAdapter<Veiculo>(ctx, android.R.layout.simple_spinner_dropdown_item, veiculosModel));
        spnVeiculo2.setAdapter(new ArrayAdapter<Veiculo>(ctx, android.R.layout.simple_spinner_dropdown_item, veiculosModel));


        ArrayList<String> localPedestre = new ArrayList<>();
        for (LocalPedestre lp : LocalPedestre.values())
        {
            localPedestre.add(lp.getValor());
        }
        spnEnvolvido_Local.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, localPedestre));


        ArrayList<String> localObjeto = new ArrayList<>();
        for (LocalObjeto lo : LocalObjeto.values())
        {
            localObjeto.add(lo.getValor());
        }
        spnObjeto_Local.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, localObjeto));
        spnAnimal_Local.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, localObjeto));

        for (OcorrenciaTransitoEnvolvido oe : envolvidosList)
        {
            envolvidosModel.add(oe.getEnvolvidoTransito());
        }
        spnEnvolvido.setAdapter(new ArrayAdapter<EnvolvidoTransito>(ctx, android.R.layout.simple_spinner_dropdown_item, envolvidosModel));

        ArrayList<String> conclusoes = new ArrayList<>();
        for (ConclusaoTransito c : ConclusaoTransito.values())
        {
            conclusoes.add(c.getValor());
        }
        spnVeiculo1Causa.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, conclusoes));

        spnVeiculo2Causa.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, conclusoes));

        //  ArrayList<String> justificativas = new ArrayList<>();


        // for(TipoJustificativa_Inconclusao tji : TipoJustificativa_Inconclusao.values())
        // {
        //     justificativas.add(tji.get());
        // }
        // spnSemaforo.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,semaforoAdapter));


    }

    private void SetVisibility(int vVeiculo, int vObjeto, int vPedestre, int vAnimal, int vNenhum)
    {
        rltvVeiculo2.setVisibility(vVeiculo);
        rltvObjeto.setVisibility(vObjeto);
        rltvPedestre.setVisibility(vPedestre);
        rltvAnimal.setVisibility(vAnimal);
        rltvNenhum.setVisibility(vNenhum);
    }



    public void onActivityResult(int requestCode,int resultcode, Intent data)
    {

        if(requestCode == 1)
        {
            InterfaceInconclusiva(data.getBooleanExtra("Inconclusivo",false));
        }
    }

    public void InterfaceInconclusiva(boolean bloquearInterface)
    {
        if (bloquearInterface)
        {
            rltvBlock_Veiculo1.setVisibility(View.VISIBLE);

            if(rbtnOpcaoPedestre.isChecked())
                rltvBlock_Envolvido.setVisibility(View.VISIBLE);

            if(rbtnOpcaoObjeto.isChecked())
                rltvBlock_Objeto.setVisibility(View.VISIBLE);

            if(rbtnOpcaoVeiculo.isChecked())
                rltvBlock_Veiculo2.setVisibility(View.VISIBLE);
            if(rbtnOpcaoAnimal.isChecked())
                rltvBlock_Animal.setVisibility(View.VISIBLE);
        }
        else
        {
            rltvBlock_Veiculo1.setVisibility(View.INVISIBLE);
            if(rbtnOpcaoPedestre.isChecked())
                rltvBlock_Envolvido.setVisibility(View.INVISIBLE);

            if(rbtnOpcaoObjeto.isChecked())
                rltvBlock_Objeto.setVisibility(View.INVISIBLE);

            if(rbtnOpcaoVeiculo.isChecked())
                rltvBlock_Veiculo2.setVisibility(View.INVISIBLE);
            if(rbtnOpcaoAnimal.isChecked())
                rltvBlock_Animal.setVisibility(View.INVISIBLE);
        }

        //ll_Inconclusao.setBackgroundColor(Color.RED);

        rbtnOpcaoAnimal.setEnabled(!bloquearInterface);

        if(veiculosModel.size()<2)
            rbtnOpcaoVeiculo.setEnabled(false);
        else
        rbtnOpcaoVeiculo.setEnabled(!bloquearInterface);

        rbtnNenhum.setEnabled(!bloquearInterface);
        rbtnOpcaoObjeto.setEnabled(!bloquearInterface);
        rbtnOpcaoPedestre.setEnabled(!bloquearInterface);

        //VIEWS V1 e V2
        spnSentido1.setEnabled(!bloquearInterface);
        spnSentido2.setEnabled(!bloquearInterface);

        cxbCausaVeiculo1.setEnabled(!bloquearInterface);
        cxbCausaVeiculo2.setEnabled(!bloquearInterface);

        spnFaixa1.setEnabled(!bloquearInterface);
        spnFaixa2.setEnabled(!bloquearInterface);

        cxbContraMaoVeiculo1.setEnabled(!bloquearInterface);
        cxbContraMaoVeiculo2.setEnabled(!bloquearInterface);

        spnVeiculo1Causa.setEnabled(!bloquearInterface);
        spnVeiculo2Causa.setEnabled(!bloquearInterface);

        //VIEWS PEDESTRE
        spnEnvolvido_Local.setEnabled(!bloquearInterface);


        if(bloquearInterface == false && colisaoTransito.getPosicaoPedestre() != null)
        {
            if (colisaoTransito.getPosicaoPedestre() == LocalPedestre.PROXIMO_FAIXA
                    || colisaoTransito.getPosicaoPedestre() == LocalPedestre.PROXIMO_PASSARELA)
            {edtDistancia.setEnabled(true);

            edtDistancia.setText(Integer.toString(colisaoTransito.getDistancia()));
            }
            else
            {
                edtDistancia.setEnabled(false);
                edtDistancia.setText("");
            }

        }
        else
        {
            edtDistancia.setEnabled(false);
            edtDistancia.setText("");
        }


        cxbCausaPedestre.setEnabled(!bloquearInterface);

        //VIEWS OBJETO
        spnObjeto_Local.setEnabled(!bloquearInterface);
        edtObjetoObservacao.setEnabled(!bloquearInterface);
        edtObjetoDescricao.setEnabled(!bloquearInterface);

        //VIEWS ANIMAL
        spnAnimal_Local.setEnabled(!bloquearInterface);
        edtAnimalDescricao.setEnabled(!bloquearInterface);
        edtAnimalObservacao.setEnabled(!bloquearInterface);
    }


    public class TipoColisaoDialog
    {
        Activity activity = null;
        Context context;
        ImageView imgvVeiculo, imgvObjeto, imgvAnimal, imgvPedestre, imgvNenhum;
        OcorrenciaTransito ot;
        Dialog dialog;


        public TipoColisaoDialog()
        {
            context = getContext();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_colisao);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle("Tipo de Ocorrência");
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener()
            {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
                {
                    // Prevent dialog close on back press button
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });
            dialog.show();

            activity = getActivity();
            ot = ((ManterPericia) activity).ocorrenciaTransito;

            AssociarLayout();
            AssociarEventos();

            List<OcorrenciaTransitoVeiculo> ocorrenciaVeiculos = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ot.getId().toString());

            List<OcorrenciaTransitoEnvolvido> ocorrenciaEnvolvidos = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ot.getId().toString());

            if (ocorrenciaVeiculos.size() == 1)
            {
                imgvVeiculo.setEnabled(false);
            }
            if (ocorrenciaEnvolvidos.size() == 0)
            {
                imgvPedestre.setEnabled(false);
            }

            HabilitarInterface(true);
            LimparCampos();
        }


        private void AssociarLayout()
        {
            imgvAnimal = (ImageView) dialog.findViewById(R.id.imgv_dialog_Animal);
            imgvObjeto = (ImageView) dialog.findViewById(R.id.imgv_dialog_Objeto);
            imgvNenhum = (ImageView) dialog.findViewById(R.id.imgv_dialog_Nenhum);
            imgvVeiculo = (ImageView) dialog.findViewById(R.id.imgv_dialog_Veiculo);
            imgvPedestre = (ImageView) dialog.findViewById(R.id.imgv_dialog_Pedestre);
        }

        public void AssociarEventos()
        {
            imgvNenhum.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    colisaoTransito.setAtoresColisao(AtoresColisao.NENHUM);
                    salvarNovaColisao();
                    dialog.dismiss();
                    rbtnNenhum.performClick();
                }
            });

            imgvVeiculo.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    colisaoTransito.setAtoresColisao(AtoresColisao.VEICULO);
                    salvarNovaColisao();
                    dialog.dismiss();
                    rbtnOpcaoVeiculo.performClick();
                }
            });

            imgvPedestre.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {

                    colisaoTransito.setAtoresColisao(AtoresColisao.PEDESTRE);
                    salvarNovaColisao();
                    dialog.dismiss();
                    rbtnOpcaoPedestre.performClick();
                }
            });

            imgvAnimal.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    colisaoTransito.setAtoresColisao(AtoresColisao.ANIMAL);
                    salvarNovaColisao();
                    dialog.dismiss();
                    rbtnOpcaoAnimal.performClick();
                }
            });
            imgvObjeto.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    colisaoTransito.setAtoresColisao(AtoresColisao.OBJETO);
                    salvarNovaColisao();
                    dialog.dismiss();
                    rbtnOpcaoObjeto.performClick();
                }
            });


        }

        public void salvarNovaColisao()
        {
            colisaoTransito.save();
            ocorrenciaColisao = new OcorrenciaTransitoColisao();
            ocorrenciaColisao.setOcorrenciaTransito(ocorrenciaTransitoColisao);
            ocorrenciaColisao.setColisaoTransito(colisaoTransito);
            ocorrenciaColisao.save();
            colisoesList.add(ocorrenciaColisao);

            adp.add(colisaoTransito);
            adp.notifyDataSetChanged();
            listColisoes.performItemClick(listColisoes, BuscadorEnum.PegarPosicaoColisao(colisaoTransitoModel, colisaoTransito), listColisoes.getItemIdAtPosition(BuscadorEnum.PegarPosicaoColisao(colisaoTransitoModel, colisaoTransito)));
        }
    }



}
