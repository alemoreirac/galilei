package Fragments.FragmentsTransito;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

import com.example.pefoce.peritolocal.ManterPericiaTransito;
import com.example.pefoce.peritolocal.R;

import Dialogs.VestigioDialog;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import com.thomashaertel.widget.MultiSpinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Adapters.AdapterColisao;
import Enums.Transito.AtoresColisao;
import Enums.Transito.ConclusaoTransito;
import Enums.Transito.LocalObjeto;
import Enums.Transito.LocalPedestre;
import Enums.Transito.NomeclaturaFaixas_1;
import Enums.Transito.NomeclaturaFaixas_2;
import Enums.Transito.NomeclaturaFaixas_3;
import Enums.Transito.NomeclaturaFaixas_4;
import Enums.Transito.NomeclaturaFaixas_5;
import Enums.OrientacaoGeograficaComposta;
import Enums.TipoInteracao;
import Enums.Transito.TipoJustificativa_Inconclusao;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnderecoTransito;
import Model.Transito.EnvolvidoColisao;
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

public class GerenciarColisoesTransito extends android.support.v4.app.Fragment implements Step
{
    int lastPosition;
    int lastCheckedId = 0;
    //    int lastSpinnerPosition = 0;
    boolean flagVisualLstv = false;
    int countColisoes = 0;
    GerenciarColisoesTransito fragment = null;
    Spinner spnVeiculo1 = null;
    Spinner spnVeiculo2 = null;
    Spinner spnEndereco1 = null;
    Spinner spnEndereco2 = null;
    Spinner spnFaixa1 = null;
    Spinner spnFaixa2 = null;
    Spinner spnSentido1 = null;
    Spinner spnSentido2 = null;
    MultiSpinner spnEnvolvidosAtropelados = null;
    Spinner spnEnvolvido_Local = null;
    Spinner spnVeiculo1Causa = null;
    Spinner spnVeiculo2Causa = null;
    Spinner spnObjeto_Local = null;
    Spinner spnAnimal_Local = null;
    Spinner spnTipoInteracao = null;
    Spinner spnOrdem = null;
    Spinner spnJustificativaInconclusao = null;
    EditText edtEnvolvido_Observacao = null;
    EditText edtObjetoDescricao = null;
    EditText edtObjetoObservacao = null;
    EditText edtDistancia = null;
    EditText edtAnimalDescricao = null;
    EditText edtAnimalObservacao = null;
    EditText edtObservacoes = null;
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
    List<EnvolvidoColisao> envolvidoColisaoList = null;
    RelativeLayout rltvBase = null;
    public ColisaoTransito colisaoTransito = null;
    OcorrenciaTransitoColisao ocorrenciaColisao = null;
    AdapterColisao adp = null;
    ArrayList<String> tipoColisaoAdapter = null;
    InconclusivoDialog inconclusivoDialog = null;
    private OnFragmentInteractionListener mListener;
    boolean[] envolvidosSelecionados = null;
    ArrayList<Veiculo> veiculosModel2 = null;
    ArrayAdapter<Veiculo> adapterVeiculo2 = null;
    ArrayList<String> conclusoesVeiculo1 = null;
    ArrayList<String> conclusoesVeiculo2 = null;
    ArrayAdapter<String> adapterConclusoesVeiculo1 = null;
    ArrayAdapter<String> adapterConclusoesVeiculo2 = null;
    Veiculo veiculoEvadido;
    EnvolvidoTransito envolvidoEvadido;
    TipoJustificativa_Inconclusao tipoJustificativa_inconclusao;
    boolean inconclusivo;
    boolean ignoreNovaColisao = false;

    public GerenciarColisoesTransito()
    {
    }

    public static GerenciarColisoesTransito newInstance()
    {
        GerenciarColisoesTransito fragment = new GerenciarColisoesTransito();
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
    public void onDestroyView()
    {
        super.onDestroyView();

        spnVeiculo1 = null;
        spnVeiculo2 = null;
        spnEndereco1 = null;
        spnEndereco2 = null;
        spnFaixa1 = null;
        spnFaixa2 = null;
        spnSentido1 = null;
        spnSentido2 = null;
        spnEnvolvidosAtropelados = null;
        spnEnvolvido_Local = null;
        spnVeiculo1Causa = null;
        spnVeiculo2Causa = null;
        spnObjeto_Local = null;
        spnAnimal_Local = null;
        spnTipoInteracao = null;
        spnJustificativaInconclusao = null;
        edtEnvolvido_Observacao = null;
        edtObjetoDescricao = null;
        edtObjetoObservacao = null;
        edtDistancia = null;
        edtAnimalDescricao = null;
        edtAnimalObservacao = null;
        edtObservacoes = null;
        cxbContraMaoVeiculo1 = null;
        cxbContraMaoVeiculo2 = null;
        cxbCausaVeiculo1 = null;
        cxbCausaVeiculo2 = null;
        cxbCausaPedestre = null;
        ll_Vestigio = null;
        ll_Inconclusao = null;
        imgbGravar_Colisao = null;
        sgOpcoes = null;
        rbtnOpcaoVeiculo = null;
        rbtnOpcaoPedestre = null;
        rbtnOpcaoObjeto = null;
        rbtnOpcaoAnimal = null;
        rbtnNenhum = null;
        fabColisoes = null;
        rltvVeiculo2 = null;
        rltvVeiculo1 = null;
        rltvObjeto = null;
        rltvPedestre = null;
        rltvNenhum = null;
        rltvAnimal = null;
        rltvBlock_Veiculo1 = null;
        rltvBlock_Veiculo2 = null;
        rltvBlock_Envolvido = null;
        rltvBlock_Animal = null;
        rltvBlock_Objeto = null;
        listColisoes = null;
        mView = null;
        ocorrenciaTransitoColisao = null;
        enderecosList = null;
        enderecoModel = null;
        veiculosModel = null;
        veiculosList = null;
        envolvidosList = null;
        colisoesList = null;
        envolvidosModel = null;
        colisaoTransitoModel = null;
        rltvBase = null;
        colisaoTransito = null;
        ocorrenciaColisao = null;
        adp = null;
        tipoColisaoAdapter = null;
        inconclusivoDialog = null;
        veiculosModel2 = null;
        adapterVeiculo2 = null;
        conclusoesVeiculo1 = null;
        conclusoesVeiculo2 = null;
        adapterConclusoesVeiculo1 = null;
        adapterConclusoesVeiculo2 = null;
        veiculoEvadido = null;
        envolvidoEvadido = null;
        tipoJustificativa_inconclusao = null;
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
        if (colisaoTransito != null)
            try
            {
                SalvarColisao();
                colisaoTransito = null;
                ocorrenciaColisao = null;
            } catch (Exception e)
            {
                return new VerificationError(e.getMessage());
            }

        return null;
    }

    @Override
    public void onSelected()
    {
        lastPosition = -1;
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        View view = getActivity().getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        fragment = this;
        ocorrenciaTransitoColisao = ((ManterPericiaTransito) getActivity()).ocorrenciaTransito;

        envolvidosList = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        envolvidosModel = new ArrayList<>();

        enderecosList = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        enderecoModel = new ArrayList<>();

        veiculosList = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        veiculosModel = new ArrayList<>();

        colisoesList = OcorrenciaTransitoColisao.find(OcorrenciaTransitoColisao.class, "ocorrencia_transito = ?", ocorrenciaTransitoColisao.getId().toString());

        Collections.sort(colisoesList, new Comparator<OcorrenciaTransitoColisao>()
        {
            @Override
            public int compare(OcorrenciaTransitoColisao c1, OcorrenciaTransitoColisao c2)
            {
                return c1.getColisaoTransito().getOrdemAcontecimento() - c2.getColisaoTransito().getOrdemAcontecimento();
            }
        });

        colisaoTransitoModel = new ArrayList<>();

        for (OcorrenciaTransitoColisao oc : colisoesList)
            colisaoTransitoModel.add(oc.getColisaoTransito());

        AssociarLayout(mView);
        AssociarEventos(mView);
        PovoarSpinners(getContext());

        //((ManterPericiaTransito) getActivity()).toolbar.setTitle("Colisões");
        ((ManterPericiaTransito) getActivity()).txvToolbarTitulo.setText("Colisões");

        HabilitarInterface(false);

        adp = new AdapterColisao(colisaoTransitoModel, getActivity());
        listColisoes.setAdapter(adp);
adp.notifyDataSetChanged();

    }

    @Override
    public void onError(@NonNull VerificationError error)
    {
    }

    private void HabilitarInterface(boolean value)
    {
        ViewUtil.modifyAll(rltvBase, value);

        rbtnNenhum.setEnabled(value);
        rbtnOpcaoAnimal.setEnabled(value);
        rbtnOpcaoObjeto.setEnabled(value);
        rbtnOpcaoVeiculo.setEnabled(value);
        rbtnOpcaoPedestre.setEnabled(value);

        if (veiculosModel.size() < 2 && value)
            rbtnOpcaoVeiculo.setEnabled(false);

        if (envolvidosModel.size() < 1 && value)
            rbtnOpcaoPedestre.setEnabled(false);

        if (colisaoTransito != null && !colisaoTransito.isVeiculo1Causador())
            spnVeiculo1Causa.setEnabled(false);

        if (colisaoTransito != null && !colisaoTransito.isVeiculo2Causador())
            spnVeiculo2Causa.setEnabled(false);

    }


    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }

    private void AssociarLayout(View view)
    {

        if(view==null)
            return;
        spnEndereco1 = (Spinner) view.findViewById(R.id.spn_Endereco_Veiculo1);
        spnEndereco2 = (Spinner) view.findViewById(R.id.spn_Endereco_Veiculo2);

        spnFaixa1 = (Spinner) view.findViewById(R.id.spn_Faixa_Veiculo1);
        spnFaixa2 = (Spinner) view.findViewById(R.id.spn_Faixa_Veiculo2);

        spnSentido1 = (Spinner) view.findViewById(R.id.spn_Sentido_Veiculo1);
        spnSentido2 = (Spinner) view.findViewById(R.id.spn_Sentido_Veiculo2);

        spnVeiculo1 = (Spinner) view.findViewById(R.id.spn_Veiculo1);
        spnVeiculo2 = (Spinner) view.findViewById(R.id.spn_Veiculo2);
        spnJustificativaInconclusao = (Spinner) view.findViewById(R.id.spn_dialog_Justificativa);
        spnEnvolvidosAtropelados = (MultiSpinner) view.findViewById(R.id.mspn_Envolvido_Dinamica);
        spnEnvolvido_Local = (Spinner) view.findViewById(R.id.spn_Envolvido_Posicao);
        edtEnvolvido_Observacao = (EditText) view.findViewById(R.id.edt_Envolvido_Distancia);

        spnObjeto_Local = (Spinner) view.findViewById(R.id.spn_Objeto_Posicao);
        spnAnimal_Local = (Spinner) view.findViewById(R.id.spn_Animal_Posicao);

        spnTipoInteracao = (Spinner) view.findViewById(R.id.spn_Tipo_Interacao);

        cxbContraMaoVeiculo1 = (CheckBox) view.findViewById(R.id.cxb_Veiculo1_ContraMao);
//        cxbCulpadoVeiculo1 = (CheckBox) v.findViewById(R.id.cxb_Veiculo1_Culpado);

        cxbContraMaoVeiculo2 = (CheckBox) view.findViewById(R.id.cxb_Veiculo2_ContraMao);
//        cxbCulpadoVeiculo2 = (CheckBox) v.findViewById(R.id.cxb_Veiculo2_Culpado);

        imgbGravar_Colisao = (ImageButton) view.findViewById(R.id.imgb_Audio_Veiculo_Vida);

        edtObservacoes = (EditText) view.findViewById(R.id.edt_Observacao_Colisao);
        edtDistancia = (EditText) view.findViewById(R.id.edt_Envolvido_Distancia);

        rbtnNenhum = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Nenhum);
        rbtnOpcaoObjeto = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Objeto);
        rbtnOpcaoVeiculo = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Veiculo);
        rbtnOpcaoPedestre = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Pedestre);
        rbtnOpcaoAnimal = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Animal);

        sgOpcoes = (SegmentedGroup) view.findViewById(R.id.sgm_Dinamica);
        sgOpcoes.setTintColor(Color.GRAY);

        rltvBlock_Veiculo1 = (RelativeLayout) view.findViewById(R.id.rltv_block_Veiculo1);
        rltvBlock_Veiculo2 = (RelativeLayout) view.findViewById(R.id.rltv_block_Veiculo2);
        rltvBlock_Animal = (RelativeLayout) view.findViewById(R.id.rltv_block_Animal);
        rltvBlock_Objeto = (RelativeLayout) view.findViewById(R.id.rltv_block_Objeto);
        rltvBlock_Envolvido = (RelativeLayout) view.findViewById(R.id.rltv_block_Envolvido);

        rltvObjeto = (RelativeLayout) view.findViewById(R.id.rltv_Objeto);
        rltvPedestre = (RelativeLayout) view.findViewById(R.id.rltv_Envolvido);
        rltvVeiculo2 = (RelativeLayout) view.findViewById(R.id.rltv_Veiculo2);
        rltvVeiculo1 = (RelativeLayout) view.findViewById(R.id.rltv_Veiculo1);
        rltvNenhum = (RelativeLayout) view.findViewById(R.id.rltv_Nenhum);
        rltvAnimal = (RelativeLayout) view.findViewById(R.id.rltv_Animal);

        edtAnimalDescricao = (EditText) view.findViewById(R.id.edt_Animal_Descricao);
        edtAnimalObservacao = (EditText) view.findViewById(R.id.edt_Animal_Observacao);
        edtObjetoDescricao = (EditText) view.findViewById(R.id.edt_Objeto_Descricao);
        edtObjetoObservacao = (EditText) view.findViewById(R.id.edt_Objeto_Observacao);

        rltvBase = (RelativeLayout) view.findViewById(R.id.rltv_Base);

        fabColisoes = (FloatingActionButton) view.findViewById(R.id.fab_Colisao);
        listColisoes = (ListView) view.findViewById(R.id.lstv_Colisoes);

        spnOrdem = (Spinner) view.findViewById(R.id.spn_Ordem_Interacao);
        spnVeiculo1Causa = (Spinner) view.findViewById(R.id.spn_Causa_Veiculo1);

        spnVeiculo2Causa = (Spinner) view.findViewById(R.id.spn_Causa_Veiculo2);

        cxbCausaVeiculo1 = (CheckBox) view.findViewById(R.id.cxb_Veiculo1_Culpado);
        cxbCausaVeiculo2 = (CheckBox) view.findViewById(R.id.cxb_Veiculo2_Culpado);

        cxbCausaPedestre = (CheckBox) view.findViewById(R.id.cxb_Envolvido_Culpado);

        ll_Inconclusao = (LinearLayout) view.findViewById(R.id.ll_Inconclusao);
        ll_Vestigio = (LinearLayout) view.findViewById(R.id.ll_Vestigio);

        spnVeiculo1Causa.setEnabled(false);
        spnVeiculo2Causa.setEnabled(false);
    }


    private void AssociarEventos(final View view)
    {
        ll_Vestigio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                VestigioDialog.show(GerenciarColisoesTransito.this, getActivity());
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

                if (!rbtnOpcaoPedestre.isChecked())
                    bd.putBoolean("SemEvasores", true);
                else
                    bd.putBoolean("SemEvasores", false);

                bd.putLong("ColisaoID", colisaoTransito.getId());

                bd.putBoolean("Inconclusivo", inconclusivo);

                if (envolvidoEvadido != null)
                    bd.putLong("EnvolvidoEvasorId", envolvidoEvadido.getId());

                if (veiculoEvadido != null)
                    bd.putLong("VeiculoEvasorId", veiculoEvadido.getId());

                if (tipoJustificativa_inconclusao != null)
                    bd.putString("Justificativa", tipoJustificativa_inconclusao.getValor());

                inconclusivoDialog.setTargetFragment(fragment, 1);

                inconclusivoDialog.setArguments(bd);

                inconclusivoDialog.show(fm, "Seleção");
            }
        });

        listColisoes.setOnItemClickListener((parent, v, position, id) -> {
            if (flagVisualLstv)
            {
                flagVisualLstv = false;
                return;
            }

            if (lastPosition != -1 && lastPosition != position && colisaoTransito!=null)
                SalvarColisao();

            lastPosition = position;

            colisaoTransito = colisaoTransitoModel.get(position);

            HabilitarInterface(true);

            LimparCampos();

            CarregarColisao();

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

                                atualizarOrdem();

                                HabilitarInterface(false);
                                Toast.makeText(getActivity(), "Colisão Deletada com sucesso!", Toast.LENGTH_LONG).show();
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

        spnVeiculo1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                veiculosModel2.clear();
                veiculosModel2.addAll(veiculosModel);

                veiculosModel2.remove(veiculosModel.get(position));

                adapterVeiculo2.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
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

                PovoarSentido(enderecoTransito.getSentidoVia(), spnSentido1);
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

                PovoarSentido(enderecoTransito.getSentidoVia(), spnSentido2);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
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
//                    Toast.makeText(getContext(), "Objeto", Toast.LENGTH_SHORT).show();
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
                spnTipoInteracao.setSelection(BuscadorEnum.getIndex(spnTipoInteracao, TipoInteracao.ATROPELAMENTO.getValor()));
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


                Bundle bd = new Bundle();
                bd.putString("Local", "conclusão");
                bd.putBoolean("Colisao", true);
                bd.putLong("ColisaoId", colisaoTransito.getId());
                bd.putLong("OcorrenciaId", ocorrenciaTransitoColisao.getOcorrencia());

                AudioDialog.show(getActivity(), bd);

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
                    if (colisaoTransito != null)
                        SalvarColisao();

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

                    conclusoesVeiculo1.remove(ConclusaoTransito.CONDUTOR_ISENTO.getValor());
                    adapterConclusoesVeiculo1.notifyDataSetChanged();
//                    spnVeiculo1Causa.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, conclusoes));

                } else
                {
                    spnVeiculo1Causa.setSelection(0);
                    spnVeiculo1Causa.setEnabled(false);

                    conclusoesVeiculo1.add(0, ConclusaoTransito.CONDUTOR_ISENTO.getValor());
                    adapterConclusoesVeiculo1.notifyDataSetChanged();
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
                    conclusoesVeiculo2.remove(ConclusaoTransito.CONDUTOR_ISENTO.getValor());
                    adapterConclusoesVeiculo2.notifyDataSetChanged();
                } else
                {
                    spnVeiculo2Causa.setSelection(0);
                    spnVeiculo2Causa.setEnabled(false);
                    conclusoesVeiculo2.add(0, ConclusaoTransito.CONDUTOR_ISENTO.getValor());
                    adapterConclusoesVeiculo2.notifyDataSetChanged();
                }
            }
        });

        recontarSpinner();

        spnOrdem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (ocorrenciaColisao == null || colisaoTransito==null)
                    return;

                if(ignoreNovaColisao) {
                    ignoreNovaColisao = false;
                    return;
                }
//                adp.remove(ocorrenciaColisao.getColisaoTransito());
//                adp.insert(colisaoTransito,position);
//
                colisaoTransitoModel.remove(colisaoTransito);
                colisaoTransitoModel.add(position, colisaoTransito);

                adp.notifyDataSetChanged();
//                colisoesList.remove(ocorrenciaColisao);
//                colisoesList.add(position, ocorrenciaColisao);

                atualizarOrdem();

                //Permite que apenas o layout seja atualizado, sem cometer operações de banco
                flagVisualLstv = true;

                listColisoes.performItemClick(listColisoes, BuscadorEnum.PegarPosicaoColisao(colisaoTransitoModel, colisaoTransito), listColisoes.getItemIdAtPosition(BuscadorEnum.PegarPosicaoColisao(colisaoTransitoModel, colisaoTransito)));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
    }

    private void atualizarOrdem()
    {
        for (ColisaoTransito ct : colisaoTransitoModel)
        {
            ct.setOrdemAcontecimento(colisaoTransitoModel.indexOf(ct) + 1);
            ct.save();
        }
    }

    private void recontarSpinner()
    {
        countColisoes = colisoesList.size();

        ArrayList<String> total = new ArrayList<String>();

        for (int i = 1; i < countColisoes + 1; i++)
        {
            total.add(Integer.toString(i));
        }

        spnOrdem.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, total));
    }

    private void CarregarColisao()
    {
        recontarSpinner();

        inconclusivo = colisaoTransito.getInconclusivo();

        InterfaceInconclusiva(inconclusivo);

        if (inconclusivo)
        {
            envolvidoEvadido = colisaoTransito.getEnvolvidoEvadido();
            veiculoEvadido = colisaoTransito.getVeiculoEvadido();
            tipoJustificativa_inconclusao = colisaoTransito.getJustificativaInconclusao();
        } else
        {
            inconclusivo = false;
            envolvidoEvadido = null;
            veiculoEvadido = null;
            tipoJustificativa_inconclusao = null;
            inconclusivoDialog = null;
        }

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

                PovoarSentido(colisaoTransito.getSentido_veiculo2(), spnSentido2);

                if (colisaoTransito.getSentido_veiculo2() == null)
                    spnSentido2.setSelection(0);
                else
                    spnSentido2.setSelection(BuscadorEnum.getIndex(spnSentido2, colisaoTransito.getSentido_veiculo2().getValor()));

                if (colisaoTransito.getEndereco_veiculo2() == null)
                    spnEndereco2.setSelection(0);
                else
                    spnEndereco2.setSelection(BuscadorEnum.getEnderecoIndexById(spnEndereco2, colisaoTransito.getEndereco_veiculo2().getId()));

                if (colisaoTransito.getVeiculo2() == null)
                    spnVeiculo2.setSelection(0);
                else
                    spnVeiculo2.setSelection(BuscadorEnum.getVeiculoIndexById(spnVeiculo2, colisaoTransito.getVeiculo2().getId()));

                cxbCausaVeiculo2.setChecked(colisaoTransito.isVeiculo2Causador());


                if (colisaoTransito.isVeiculo2Causador() &&
                        colisaoTransito.getConclusaoVeiculo2() != null)
                {
                    spnVeiculo2Causa.setEnabled(true);
                    spnVeiculo2Causa.setSelection(BuscadorEnum.getIndex(spnVeiculo2Causa,
                            colisaoTransito.getConclusaoVeiculo2().getValor()));
                } else
                {
                    spnVeiculo2Causa.setEnabled(false);
                    spnVeiculo2Causa.setSelection(BuscadorEnum.getIndex(spnVeiculo2Causa,
                            ConclusaoTransito.CONDUTOR_ISENTO.getValor()));
                }


                cxbContraMaoVeiculo2.setChecked(colisaoTransito.isVeiculo2ContraMao());
                rbtnOpcaoVeiculo.performClick();
                break;

            case PEDESTRE:

                envolvidoColisaoList = EnvolvidoColisao.find(EnvolvidoColisao.class,"colisao_transito = ?",colisaoTransito.getId().toString());

                envolvidosSelecionados = new boolean[envolvidosList.size()];

                for(EnvolvidoColisao ec : envolvidoColisaoList)
                {
                    for(EnvolvidoTransito envolvidoTransito : envolvidosModel)
                    {
                        if (ec.getEnvolvidoTransito().getId().equals(envolvidoTransito.getId()))
                            envolvidosSelecionados[envolvidosModel.indexOf(envolvidoTransito)] = true;
                    }
                }

                spnEnvolvidosAtropelados.setSelected(envolvidosSelecionados);

                if (colisaoTransito.getDistancia() != 0)
                {
                    edtDistancia.setEnabled(true);
                    edtDistancia.setText(Integer.toString(colisaoTransito.getDistancia()));
                } else
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

        if (colisaoTransito.getOrdemAcontecimento() - 1 >= 0)
            spnOrdem.setSelection(colisaoTransito.getOrdemAcontecimento() - 1);
        else
            spnOrdem.setSelection(0);


        spnVeiculo1.setSelection(BuscadorEnum.getVeiculoIndexById(spnVeiculo1, colisaoTransito.getVeiculo1().getId()));

        PovoarSentido(colisaoTransito.getSentido_veiculo1(), spnSentido1);

        if (colisaoTransito.getSentido_veiculo1() == null)
            spnSentido1.setSelection(0);
        else

            spnSentido1.setSelection(BuscadorEnum.getIndex(spnSentido1, colisaoTransito.getSentido_veiculo1().getValor()));

        spnEndereco1.setSelection(BuscadorEnum.getEnderecoIndexById(spnEndereco1, colisaoTransito.getEndereco_veiculo1().getId()));

        cxbCausaVeiculo1.setChecked(colisaoTransito.isVeiculo1Causador());

        if (colisaoTransito.isVeiculo1Causador() &&
                colisaoTransito.getConclusaoVeiculo1() != null)
        {
            spnVeiculo1Causa.setEnabled(true);
            spnVeiculo1Causa.setSelection(BuscadorEnum.getIndex(spnVeiculo1Causa,
                    colisaoTransito.getConclusaoVeiculo1().getValor()));
        } else
        {
            spnVeiculo1Causa.setEnabled(false);
            spnVeiculo1Causa.setSelection(BuscadorEnum.getIndex(spnVeiculo1Causa,
                    ConclusaoTransito.CONDUTOR_ISENTO.getValor()));
        }

        cxbContraMaoVeiculo1.setChecked(colisaoTransito.isVeiculo1ContraMao());

        edtObservacoes.setText(colisaoTransito.getObservacoesColisao());
    }


    private void SalvarColisao()
    {
        colisaoTransito.AnularCampos();

        colisaoTransito.setInconclusivo(inconclusivo);

        if (inconclusivo)
        {
            colisaoTransito.setJustificativaInconclusao(tipoJustificativa_inconclusao);

            switch (tipoJustificativa_inconclusao)
            {
                case ENVOLVIDO_EVADIU:
                    colisaoTransito.setEnvolvidoEvadido(envolvidoEvadido);
                    colisaoTransito.setVeiculoEvadido(null);
                    break;
                case CONDUTOR_EVADIU:
                    colisaoTransito.setEnvolvidoEvadido(null);
                    colisaoTransito.setVeiculoEvadido(veiculoEvadido);
                    break;
                default:
                    break;
            }
        } else
        {
            colisaoTransito.setJustificativaInconclusao(null);
            colisaoTransito.setEnvolvidoEvadido(null);
            colisaoTransito.setVeiculoEvadido(null);
        }
        inconclusivo = false;
        tipoJustificativa_inconclusao = null;
        envolvidoEvadido = null;
        veiculoEvadido = null;
        inconclusivoDialog = null;

        colisaoTransito.setOrdemAcontecimento(spnOrdem.getSelectedItemPosition() + 1);

        colisaoTransito.setEndereco_veiculo1((EnderecoTransito) spnEndereco1.getSelectedItem());
        colisaoTransito.setVeiculo1((Veiculo) spnVeiculo1.getSelectedItem());

        if (spnSentido1.getSelectedItem() != null)
            colisaoTransito.setSentido_veiculo1(BuscadorEnum.BuscarOrientacaoComposta(spnSentido1.getSelectedItem().toString()));

        colisaoTransito.setVeiculo1_Faixa(spnFaixa1.getSelectedItemPosition() + 1);
        colisaoTransito.setVeiculo1ContraMao(cxbContraMaoVeiculo1.isChecked());


        colisaoTransito.setVeiculo1Causador(cxbCausaVeiculo1.isChecked());
        colisaoTransito.setConclusaoVeiculo1(BuscadorEnum.BuscarConclusao(spnVeiculo1Causa
                .getSelectedItem().toString()));

        //colisaoTransito
        if (rbtnNenhum.isChecked())
            colisaoTransito.setAtoresColisao(AtoresColisao.NENHUM);



        if (rbtnOpcaoVeiculo.isChecked())
        {
            if (((Veiculo) spnVeiculo2.getSelectedItem()).getId()
                    .equals(((Veiculo) spnVeiculo1.getSelectedItem()).getId()))
            {
                spnVeiculo2.setBackgroundColor(Color.RED);

                Toast.makeText(getContext(), "Os veículos envolvidos tem que ser diferentes!", Toast.LENGTH_SHORT).show();

                return;
            }
            colisaoTransito.setAtoresColisao(AtoresColisao.VEICULO);
            colisaoTransito.setEndereco_veiculo2((EnderecoTransito) spnEndereco2.getSelectedItem());
            if(spnVeiculo2!=null && spnVeiculo2.getSelectedItem()!=null)
            colisaoTransito.setVeiculo2((Veiculo) spnVeiculo2.getSelectedItem());
            if(spnSentido2!=null && spnSentido2.getSelectedItem()!=null)
            colisaoTransito.setSentido_veiculo2(BuscadorEnum.BuscarOrientacaoComposta(spnSentido2.getSelectedItem().toString()));
            colisaoTransito.setVeiculo2Causador(cxbCausaVeiculo2.isChecked());
            colisaoTransito.setVeiculo2_Faixa(spnFaixa2.getSelectedItemPosition() + 1);
            colisaoTransito.setVeiculo2ContraMao(cxbContraMaoVeiculo2.isChecked());


            if (cxbCausaVeiculo2.isChecked())
                colisaoTransito.setConclusaoVeiculo2(BuscadorEnum.BuscarConclusao
                        (spnVeiculo2Causa.getSelectedItem().toString()));
        }
        if (rbtnOpcaoPedestre.isChecked())
        {
            colisaoTransito.setAtoresColisao(AtoresColisao.PEDESTRE);
//            colisaoTransito.setPedestre((EnvolvidoTransito) spnEnvolvidosAtropelados.getSelectedItem());

            for(int i = 0;i < envolvidosModel.size();i++)
            {
                if(envolvidosSelecionados[i] &&
                   EnvolvidoColisao.find(EnvolvidoColisao.class,"envolvido_transito = ? and colisao_transito = ?",envolvidosModel.get(i).getId().toString(),colisaoTransito.getId().toString()).size()==0)
                {
                    EnvolvidoColisao ec = new EnvolvidoColisao(envolvidosList.get(i).getEnvolvidoTransito(),colisaoTransito);
                    ec.save();
                }

                if(!envolvidosSelecionados[i] &&
                        EnvolvidoColisao.find(EnvolvidoColisao.class,"envolvido_transito = ? and colisao_transito = ?"
                                ,envolvidosModel.get(i).getId().toString(),colisaoTransito.getId().toString()).size()>0)
                {
                     EnvolvidoColisao.deleteAll(EnvolvidoColisao.class,"envolvido_transito = ? and colisao_transito = ?",envolvidosModel.get(i).getId().toString(),colisaoTransito.getId().toString());
                }
            }

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
        try
        {
            colisaoTransito.setTipoInteracao(BuscadorEnum.BuscarTipoInteracao(spnTipoInteracao.getSelectedItem().toString()));

        } catch (Exception e)
        {
            colisaoTransito.setTipoInteracao(null);
        }

//        ColisaoTransito.update(colisaoTransito);
        colisaoTransito.save();

        ocorrenciaColisao.setOcorrenciaTransito(ocorrenciaTransitoColisao);
        ocorrenciaColisao.setColisaoTransito(colisaoTransito);
        ocorrenciaColisao.save();

//        OcorrenciaTransitoColisao.update(ocorrenciaColisao);

        adp.notifyDataSetChanged();

        Toast.makeText(getContext(), "Colisão salva com sucesso!", Toast.LENGTH_LONG).show();
    }


    private void LimparCampos()
    {
        if(envolvidosSelecionados!=null)
        {
            for (int i = 0; i < envolvidosSelecionados.length; i++)
                envolvidosSelecionados[i] = false;
        }
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

        cxbContraMaoVeiculo1.setChecked(false);
        cxbContraMaoVeiculo2.setChecked(false);
        cxbCausaVeiculo2.setChecked(false);
        cxbCausaVeiculo1.setChecked(false);
        cxbCausaPedestre.setChecked(false);

    }

    private void PovoarSentido(OrientacaoGeograficaComposta sentido, Spinner spinner)
    {
        ArrayList<String> sentidos = new ArrayList<String>();

        if (sentido != null)
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

        veiculosModel2 = new ArrayList<>();

        veiculosModel2.addAll(veiculosModel);

        adapterVeiculo2 = new ArrayAdapter<Veiculo>(ctx, android.R.layout.simple_spinner_dropdown_item, veiculosModel2);

        spnVeiculo1.setAdapter(new ArrayAdapter<Veiculo>(ctx, android.R.layout.simple_spinner_dropdown_item, veiculosModel));
        spnVeiculo2.setAdapter(adapterVeiculo2);


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

        ArrayAdapter<EnvolvidoTransito> adapterEnvolvidos = new ArrayAdapter<EnvolvidoTransito>(ctx, android.R.layout.simple_spinner_item);

        for (OcorrenciaTransitoEnvolvido oe : envolvidosList)
        {
            envolvidosModel.add(oe.getEnvolvidoTransito());
            adapterEnvolvidos.add(oe.getEnvolvidoTransito());
        }

        MultiSpinner.MultiSpinnerListener onSelectedListener = new MultiSpinner.MultiSpinnerListener()
        {
            public void onItemsSelected(boolean[] selected)
            {
                envolvidosSelecionados = selected;
            }
        };

//        spnEnvolvidosAtropelados.setAdapter(new ArrayAdapter<EnvolvidoTransito>(ctx, android.R.layout.simple_spinner_dropdown_item, envolvidosModel));

        spnEnvolvidosAtropelados.setAdapter(adapterEnvolvidos, false, onSelectedListener);

        conclusoesVeiculo1 = new ArrayList<>();

        conclusoesVeiculo2 = new ArrayList<>();

        for (ConclusaoTransito c : ConclusaoTransito.values())
        {
            conclusoesVeiculo1.add(c.getValor());
            conclusoesVeiculo2.add(c.getValor());
        }

        adapterConclusoesVeiculo1 = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, conclusoesVeiculo1);

        adapterConclusoesVeiculo2 = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, conclusoesVeiculo2);

        spnVeiculo1Causa.setAdapter(adapterConclusoesVeiculo1);

        spnVeiculo2Causa.setAdapter(adapterConclusoesVeiculo2);

    }

    private void SetVisibility(int vVeiculo, int vObjeto, int vPedestre, int vAnimal, int vNenhum)
    {
        rltvVeiculo2.setVisibility(vVeiculo);
        rltvObjeto.setVisibility(vObjeto);
        rltvPedestre.setVisibility(vPedestre);
        rltvAnimal.setVisibility(vAnimal);
        rltvNenhum.setVisibility(vNenhum);
    }

    public void DialogResult(Bundle bd)
    {
        inconclusivo = bd.getBoolean("inconclusivo", false);

        InterfaceInconclusiva(inconclusivo);
        colisaoTransito.setInconclusivo(inconclusivo);
        if (inconclusivo)
        {
            tipoJustificativa_inconclusao = BuscadorEnum.BuscarJustificativa(bd.getString("justificativa"));

            if (tipoJustificativa_inconclusao.equals(TipoJustificativa_Inconclusao.ENVOLVIDO_EVADIU))
            {
                envolvidoEvadido = EnvolvidoTransito.findById(EnvolvidoTransito.class, bd.getLong("envolvido"));
                veiculoEvadido = null;
            }
            if (tipoJustificativa_inconclusao.equals(TipoJustificativa_Inconclusao.CONDUTOR_EVADIU))
            {
                veiculoEvadido = Veiculo.findById(Veiculo.class, bd.getLong("veiculo"));
                envolvidoEvadido = null;
            }

        } else
        {
            tipoJustificativa_inconclusao = null;
            envolvidoEvadido = null;
            veiculoEvadido = null;
        }
    }

    public void InterfaceInconclusiva(boolean bloquearInterface)
    {
        rbtnNenhum.setEnabled(!bloquearInterface);
        rbtnOpcaoAnimal.setEnabled(!bloquearInterface);
        rbtnOpcaoObjeto.setEnabled(!bloquearInterface);
        rbtnOpcaoVeiculo.setEnabled(!bloquearInterface);
        rbtnOpcaoPedestre.setEnabled(!bloquearInterface);

        if (bloquearInterface)
        {
            rltvBlock_Veiculo1.setVisibility(View.VISIBLE);

            if (rbtnOpcaoPedestre.isChecked())
                rltvBlock_Envolvido.setVisibility(View.VISIBLE);

            if (rbtnOpcaoObjeto.isChecked())
                rltvBlock_Objeto.setVisibility(View.VISIBLE);

            if (rbtnOpcaoVeiculo.isChecked())
                rltvBlock_Veiculo2.setVisibility(View.VISIBLE);

            if (rbtnOpcaoAnimal.isChecked())
                rltvBlock_Animal.setVisibility(View.VISIBLE);

            sgOpcoes.setTintColor(Color.GRAY);
        } else
        {
            rltvBlock_Veiculo1.setVisibility(View.INVISIBLE);

            if (rbtnOpcaoPedestre.isChecked())
                rltvBlock_Envolvido.setVisibility(View.INVISIBLE);

            if (rbtnOpcaoObjeto.isChecked())
                rltvBlock_Objeto.setVisibility(View.INVISIBLE);

            if (rbtnOpcaoVeiculo.isChecked())
                rltvBlock_Veiculo2.setVisibility(View.INVISIBLE);

            if (rbtnOpcaoAnimal.isChecked())
                rltvBlock_Animal.setVisibility(View.INVISIBLE);

            sgOpcoes.setTintColor(Color.parseColor("#004A6D"));
        }

        ViewUtil.modifyAll(rltvBase, !bloquearInterface);


        spnTipoInteracao.setEnabled(true);
        ll_Inconclusao.setEnabled(true);
        ll_Vestigio.setEnabled(true);
        imgbGravar_Colisao.setEnabled(true);

        if (veiculosModel.size() < 2)
            rbtnOpcaoVeiculo.setEnabled(false);
        else
            rbtnOpcaoVeiculo.setEnabled(!bloquearInterface);

        if (!bloquearInterface && colisaoTransito.getPosicaoPedestre() != null)
        {
            if (colisaoTransito.getPosicaoPedestre() == LocalPedestre.PROXIMO_FAIXA
                    || colisaoTransito.getPosicaoPedestre() == LocalPedestre.PROXIMO_PASSARELA)
            {
                edtDistancia.setEnabled(true);

                edtDistancia.setText(Integer.toString(colisaoTransito.getDistancia()));
            } else
            {
                edtDistancia.setEnabled(false);
                edtDistancia.setText("");
            }
        } else
        {
            edtDistancia.setEnabled(false);
            edtDistancia.setText("");
        }
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
            ot = ((ManterPericiaTransito) activity).ocorrenciaTransito;

            AssociarLayout();
            AssociarEventos();

            List<OcorrenciaTransitoVeiculo> ocorrenciaVeiculos = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ot.getId().toString());

            List<OcorrenciaTransitoEnvolvido> ocorrenciaEnvolvidos = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ot.getId().toString());

            if (ocorrenciaVeiculos.size() == 1)
                imgvVeiculo.setEnabled(false);

            if (ocorrenciaEnvolvidos.size() == 0)
                imgvPedestre.setEnabled(false);


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

            colisaoTransito.setOrdemAcontecimento(countColisoes + 1);

            colisaoTransito.save();

            ocorrenciaColisao = new OcorrenciaTransitoColisao();
            ocorrenciaColisao.setOcorrenciaTransito(ocorrenciaTransitoColisao);
            ocorrenciaColisao.setColisaoTransito(colisaoTransito);
            ocorrenciaColisao.save();
            colisoesList.add(ocorrenciaColisao);

            recontarSpinner();

            adp.add(colisaoTransito);
            adp.notifyDataSetChanged();
            ignoreNovaColisao = true;
            listColisoes.performItemClick(listColisoes, BuscadorEnum.PegarPosicaoColisao(colisaoTransitoModel, colisaoTransito), listColisoes.getItemIdAtPosition(BuscadorEnum.PegarPosicaoColisao(colisaoTransitoModel, colisaoTransito)));

        }
    }
}
