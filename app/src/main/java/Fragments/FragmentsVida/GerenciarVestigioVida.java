package Fragments.FragmentsVida;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericiaVida;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterVestigio;
import Enums.Calibre;
import Enums.DocumentoPessoa;
import Enums.TipoArma;
import Enums.TipoRecolhimentoAmostra_Papiloscopia;
import Enums.TipoMunicao;
import Enums.TipoVestigioBiologico;
import Enums.TipoRecolhimentoAmostra_Biologica;
import Enums.Vida.TipoVestigioVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.VestigioVida;
import Model.Vida.VestigioVidaOcorrencia;
import Util.BuscadorEnum;
import Util.ViewUtil;

public class GerenciarVestigioVida extends android.support.v4.app.Fragment implements Step
{

    int lastPosition;

    ListView lstvVestigios;
    FloatingActionButton fabVestigio;
    RelativeLayout rltvVestigioDetalhe;
    Spinner spnTipoVestigio;

    RelativeLayout rltvVestigioPapiloscopico;
    RelativeLayout rltvVestigioBiologico;
    RelativeLayout rltvVestigioArma;
    RelativeLayout rltvVestigioMunicao;
    RelativeLayout rltvVestigioOutro;
    RelativeLayout rltvVestigioDocumento;

    AdapterVestigio adapterVestigio;
    OcorrenciaVida ocorrenciaVida;
    VestigioVida vestigioVida;
    VestigioVidaOcorrencia vestigioVidaOcorrencia;
    List<VestigioVidaOcorrencia> vestigioVidaOcorrenciaList;
    ArrayList<VestigioVida> vestigioVidaModel;

    Spinner spnTipoDocumentoVestigio;
    EditText edtNumDocumentoVestigio;
    EditText edtObservacoesDocumentoVestigio;

    Spinner spnTipoVestigioBiologico;
    Spinner spnRecolhimentoVestigioBiologico;
    EditText edtObservacoesVestigioBiologico;

    Spinner spnCalibreMunicao;
    Spinner spnTipoMunicao;
    EditText edtQuantidadeMunicao;
    CheckBox cxbMunicaoBoaCondicao;
    EditText edtOBservacoesMunicao;

    Spinner spnTipoArma;
    Spinner spnCalibreArma;
    EditText edtNumeracaoArma;
    EditText edtOBservacoesArma;

    Spinner spnColetaVestigioPapiloscopico;
    EditText edtObjetoPapiloscopia;
    EditText edtObservacoesPapiloscopia;

    EditText edtObjetoRecolhido;
    EditText edtObservacoesOutro;

    View mView;

    public GerenciarVestigioVida()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GerenciarVestigioVida.
     */
    // TODO: Rename and change types and number of parameters
    public static GerenciarVestigioVida newInstance(String param1, String param2)
    {
        GerenciarVestigioVida fragment = new GerenciarVestigioVida();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_gerenciar_vestigio_vida, container, false);
        return mView;
    }

    @Override
    public VerificationError verifyStep()
    {
        if (rltvVestigioDetalhe.getChildAt(0).isEnabled())
            try
            {
                SalvarVestigio();
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
        lastPosition = -1;

        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Vestígios");

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ocorrenciaVida = ((ManterPericiaVida) getActivity()).ocorrenciaVida;

        vestigioVidaOcorrenciaList = VestigioVidaOcorrencia.find(VestigioVidaOcorrencia.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        vestigioVidaModel = new ArrayList<>();

        AssociarLayout(mView);
        AssociarEventos();
        Povoarspinners(getActivity());

        for (VestigioVidaOcorrencia vo : vestigioVidaOcorrenciaList)
        {
            if (vo.getVestigioVida() != null)
                vestigioVidaModel.add(vo.getVestigioVida());
        }

        adapterVestigio = new AdapterVestigio(vestigioVidaModel, getActivity());

        lstvVestigios.setAdapter(adapterVestigio);

        // ModificarTodosEnabled(false);
        //ViewUtil.modifyAll(rltvVestigioDetalhe,false);
        ViewUtil.modifyAll(rltvVestigioDetalhe, false);
    }

//    private void ModificarTodosEnabled(boolean enabled)
//    {
//        rltvVestigioArma.setEnabled(enabled);
//        rltvVestigioBiologico.setEnabled(enabled);
//        rltvVestigioPapiloscopico.setEnabled(enabled);
//        rltvVestigioDocumento.setEnabled(enabled);
//        rltvVestigioMunicao.setEnabled(enabled);
//        rltvVestigioOutro.setEnabled(enabled);
//    }

    private void ModificarTodosVisible(TipoVestigioVida opcao)
    {
        rltvVestigioArma.setVisibility(View.INVISIBLE);
        rltvVestigioBiologico.setVisibility(View.INVISIBLE);
        rltvVestigioPapiloscopico.setVisibility(View.INVISIBLE);
        rltvVestigioDocumento.setVisibility(View.INVISIBLE);
        rltvVestigioMunicao.setVisibility(View.INVISIBLE);
        rltvVestigioOutro.setVisibility(View.INVISIBLE);

        switch (opcao)
        {
            case MUNICAO:
                rltvVestigioMunicao.setVisibility(View.VISIBLE);
                break;
            case ARMA_DE_FOGO:
                rltvVestigioArma.setVisibility(View.VISIBLE);
                break;
            case BIOLOGICO:
                rltvVestigioBiologico.setVisibility(View.VISIBLE);
                break;
            case PAPILOSCOPICO:
                rltvVestigioPapiloscopico.setVisibility(View.VISIBLE);
                break;
            case DOCUMENTO:
                rltvVestigioDocumento.setVisibility(View.VISIBLE);
                break;
            case OUTRO:
                rltvVestigioOutro.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    public void AssociarLayout(View view)
    {
        if(view==null)
            return;

        rltvVestigioDetalhe = (RelativeLayout) view.findViewById(R.id.rltv_Detalhe_Vestigio_Vida);
        edtNumDocumentoVestigio = (EditText) view.findViewById(R.id.edt_NumDoc_Vestigio_Vida);
        edtObservacoesDocumentoVestigio = (EditText) view.findViewById(R.id.edt_Observacoes_Vestigio_Documento);
        spnTipoDocumentoVestigio = (Spinner) view.findViewById(R.id.spn_Tipo_Documento_Vestigio_Vida);

        spnRecolhimentoVestigioBiologico = (Spinner) view.findViewById(R.id.spn_Coleta_Vestigio_Biologico);
        spnTipoVestigioBiologico = (Spinner) view.findViewById(R.id.spn_Tipo_Vestigio_Biologico);
        edtObservacoesVestigioBiologico = (EditText) view.findViewById(R.id.edt_Observacoes_Biologico);

        spnTipoMunicao = (Spinner) view.findViewById(R.id.spn_Tipo_Vestigio_Municao);
        cxbMunicaoBoaCondicao = (CheckBox) view.findViewById(R.id.cxb_BoaCondicao_Municao);
        spnCalibreMunicao = (Spinner) view.findViewById(R.id.spn_Calibre_Municao);
        edtQuantidadeMunicao = (EditText) view.findViewById(R.id.edt_Quantidade_Municao);
        edtOBservacoesMunicao = (EditText) view.findViewById(R.id.edt_Observacoes_Municao);

        spnColetaVestigioPapiloscopico = (Spinner) view.findViewById(R.id.spn_TipoRecolhimento_Amostra_Papiloscopica);
        edtObjetoPapiloscopia = (EditText) view.findViewById(R.id.edt_Objeto_Papiloscopia);
        edtObservacoesPapiloscopia = (EditText) view.findViewById(R.id.edt_Observacoes_Papiloscopia);

        edtNumeracaoArma = (EditText) view.findViewById(R.id.edt_Numeracao_Arma);
        spnCalibreArma = (Spinner) view.findViewById(R.id.spn_Calibre_Arma);
        spnTipoArma = (Spinner) view.findViewById(R.id.spn_Tipo_Arma_Fogo);
        edtOBservacoesArma = (EditText) view.findViewById(R.id.edt_Observacao_Arma);

        spnTipoVestigio = (Spinner) view.findViewById(R.id.spn_Tipo_Vestigio_Vida);
        fabVestigio = (FloatingActionButton) view.findViewById(R.id.fab_Vestigio_Vida);
        lstvVestigios = (ListView) view.findViewById(R.id.lstv_Vestigios);

        edtObjetoRecolhido = (EditText) view.findViewById(R.id.edt_Descricao_Vestigio_Outro);
        edtObservacoesOutro = (EditText) view.findViewById(R.id.edt_Observacoes_Outro);

        rltvVestigioArma = (RelativeLayout) view.findViewById(R.id.rltv_Vestigio_Arma_Fogo);
        rltvVestigioBiologico = (RelativeLayout) view.findViewById(R.id.rltv_Vestigio_Biologico);
        rltvVestigioPapiloscopico = (RelativeLayout) view.findViewById(R.id.rltv_Vestigio_Papiloscopico);
        rltvVestigioDocumento = (RelativeLayout) view.findViewById(R.id.rltv_Vestigio_Documento);
        rltvVestigioMunicao = (RelativeLayout) view.findViewById(R.id.rltv_Vestigio_Municao);
        rltvVestigioOutro = (RelativeLayout) view.findViewById(R.id.rltv_Vestigio_Outro);
    }

    public void Povoarspinners(Context ctx)
    {

        //Geral

        List<String> tiposVestigio = new ArrayList<>();

        for (TipoVestigioVida tvv : TipoVestigioVida.values())
            tiposVestigio.add(tvv.getValor());

        spnTipoVestigio.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposVestigio));

        //Documentação

        List<String> tiposDocumento = new ArrayList<>();

        for (DocumentoPessoa dcp : DocumentoPessoa.values())
        {
            if (!dcp.equals(DocumentoPessoa.NP))
            tiposDocumento.add(dcp.getValor());
        }

        spnTipoDocumentoVestigio.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposDocumento));

        //Biologia

        List<String> tiposVestigiosBiologicos = new ArrayList<>();

        for (TipoVestigioBiologico tvb : TipoVestigioBiologico.values())
            tiposVestigiosBiologicos.add(tvb.getValor());

        spnTipoVestigioBiologico.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposVestigiosBiologicos));


        List<String> tiposRecolhimentoAmostraBiologica = new ArrayList<>();

        for (TipoRecolhimentoAmostra_Biologica trab : TipoRecolhimentoAmostra_Biologica.values())
            tiposRecolhimentoAmostraBiologica.add(trab.getValor());

        spnRecolhimentoVestigioBiologico.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposRecolhimentoAmostraBiologica));

        //Papiloscopia

        List<String> tiposRecolhimentoPapiloscopia = new ArrayList<>();

        for (TipoRecolhimentoAmostra_Papiloscopia trap : TipoRecolhimentoAmostra_Papiloscopia.values())
            tiposRecolhimentoPapiloscopia.add(trap.getValor());

        spnColetaVestigioPapiloscopico.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposRecolhimentoPapiloscopia));


        //Armas e munições

        List<String> tiposMunicao = new ArrayList<>();

        for (TipoMunicao tm : TipoMunicao.values())
            tiposMunicao.add(tm.getValor());

        spnTipoMunicao.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposMunicao));

        List<String> calibresMunicao = new ArrayList<>();

        for (Calibre c : Calibre.values())
            calibresMunicao.add(c.getValor());

        spnCalibreArma.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, calibresMunicao));
        spnCalibreMunicao.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, calibresMunicao));


        List<String> tiposArma = new ArrayList<>();

        for (TipoArma ta : TipoArma.values())
            tiposArma.add(ta.getValor());

        spnTipoArma.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposArma));

    }

    public void AssociarEventos()
    {
        spnTipoVestigio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                try
                {
                    ModificarTodosVisible(BuscadorEnum.BuscarVestigioVida(spnTipoVestigio.getSelectedItem().toString()));
                } catch (Exception e)
                {
                    ModificarTodosVisible(TipoVestigioVida.OUTRO);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        lstvVestigios.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(lastPosition!= -1 && lastPosition != position)
                {
//                    try
//                    {
//                        vestigioVidaOcorrencia = VestigioVidaOcorrencia.find(VestigioVidaOcorrencia.class, "vestigio_vida = ?", vestigioVida.getId().toString()).get(0);
//                    }catch (Exception e)
//                    {
//                        vestigioVidaOcorrencia = new VestigioVidaOcorrencia();
//                    }

                    SalvarVestigio();
                }
                else
                lastPosition = position;

                vestigioVida = vestigioVidaModel.get(position);

                try
                {
                    vestigioVidaOcorrencia = VestigioVidaOcorrencia.find(VestigioVidaOcorrencia.class, "vestigio_vida = ?", vestigioVida.getId().toString()).get(0);
                }catch (Exception e)
                {
                    vestigioVidaOcorrencia = new VestigioVidaOcorrencia();
                }

                LimparCampos();

                CarregarVestigio();

                //ModificarTodosEnabled(true);
                ViewUtil.modifyAll(rltvVestigioDetalhe, true);

            }
        });

        lstvVestigios.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View v,final int position, long id)

            {
                AlertDialog.Builder builder;

                    builder = new AlertDialog.Builder(getActivity());

                builder.setTitle("Deletar Envolvido")
                        .setMessage("Você deseja deletar este vestígio?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                //OcorrenciaTransitoColisao ocorrenciaColisao = colisoesList.get(position);
                                VestigioVidaOcorrencia vestigioVidaOcorrenciaDelete = vestigioVidaOcorrenciaList.get(position);

                                adapterVestigio.remove(vestigioVidaOcorrenciaDelete.getVestigioVida());

                                vestigioVidaOcorrenciaDelete.getVestigioVida().delete();

                                vestigioVidaOcorrenciaDelete.delete();

                                vestigioVidaOcorrenciaList.remove(position);

                                LimparCampos();

                                //ModificarTodosEnabled(false);
                                ViewUtil.modifyAll(rltvVestigioDetalhe, false);

                                Toast.makeText(getActivity(), "Vestígio Deletado com sucesso!", Toast.LENGTH_LONG).show();
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

        fabVestigio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(vestigioVida != null)
                    SalvarVestigio();

                vestigioVida = new VestigioVida();

                vestigioVida.save();

                vestigioVidaOcorrencia = new VestigioVidaOcorrencia();

                vestigioVidaOcorrencia.setOcorrenciaVida(ocorrenciaVida);

                vestigioVidaOcorrencia.setVestigioVida(vestigioVida);

                vestigioVidaOcorrencia.save();

                vestigioVidaOcorrenciaList.add(vestigioVidaOcorrencia);

                adapterVestigio.add(vestigioVida);
                adapterVestigio.notifyDataSetChanged();

                lstvVestigios.performItemClick(lstvVestigios, BuscadorEnum.PegarPosicaoVestigioVida(vestigioVidaModel, vestigioVida),
                        lstvVestigios.getItemIdAtPosition(BuscadorEnum.PegarPosicaoVestigioVida(vestigioVidaModel, vestigioVida)));

                ViewUtil.modifyAll(rltvVestigioDetalhe, true);
//                ModificarTodosEnabled(true);
                //ModificarTodosVisible(TipoVestigioVida.OUTRO);

                spnTipoVestigio.setSelection(0);

                LimparCampos();
            }
        });

    }


    public void CarregarVestigio()
    {
        if (vestigioVida.getTipoVestigio() != null)
        {
            spnTipoVestigio.setSelection(BuscadorEnum.getIndex(spnTipoVestigio, vestigioVida.getTipoVestigio().getValor()));

            switch (vestigioVida.getTipoVestigio())
            {
                case ARMA_DE_FOGO:

                    if (vestigioVida.getNumeracaoArma() != null)
                        edtNumeracaoArma.setText(vestigioVida.getNumeracaoArma());

                    if (vestigioVida.getTipoArma() != null)
                        spnTipoArma.setSelection(BuscadorEnum.getIndex(spnTipoArma, vestigioVida.getTipoArma().getValor()));

                    if (vestigioVida.getCalibreArma() != null)
                        spnTipoArma.setSelection(BuscadorEnum.getIndex(spnTipoArma, vestigioVida.getCalibreArma().getValor()));

                    if (vestigioVida.getObservacao() != null)
                        edtOBservacoesArma.setText(vestigioVida.getObservacao());
                    break;

                case BIOLOGICO:
                    if (vestigioVida.getTipoVestigioBiologico() != null)
                        spnTipoVestigioBiologico.setSelection(BuscadorEnum.getIndex(spnTipoVestigioBiologico, vestigioVida.getTipoVestigioBiologico().getValor()));

                    if (vestigioVida.getTipoRecolhimentoAmostraBiologica() != null)
                        spnRecolhimentoVestigioBiologico.setSelection(BuscadorEnum.getIndex(spnRecolhimentoVestigioBiologico, vestigioVida.getTipoRecolhimentoAmostraBiologica().getValor()));

                    if (vestigioVida.getObservacao() != null)
                        edtObservacoesVestigioBiologico.setText(vestigioVida.getObservacao());

                    break;

                case MUNICAO:
                    if (vestigioVida.getCalibreMunicao() != null)
                        spnCalibreMunicao.setSelection(BuscadorEnum.getIndex(spnCalibreMunicao, vestigioVida.getCalibreMunicao().getValor()));

                    if(vestigioVida.getTipoMunicao()!=null)
                        spnTipoMunicao.setSelection(BuscadorEnum.getIndex(spnTipoMunicao,vestigioVida.getTipoMunicao().getValor()));

                    if (vestigioVida.getObservacao() != null)
                        edtOBservacoesMunicao.setText(vestigioVida.getObservacao());

                    cxbMunicaoBoaCondicao.setChecked(vestigioVida.getCondicaoMunicao());

                    edtQuantidadeMunicao.setText(String.valueOf(vestigioVida.getQuantidadeMunicao()));
                    break;

                case DOCUMENTO:
                    if (vestigioVida.getTipoDocumento() != null)
                        spnTipoDocumentoVestigio.setSelection(BuscadorEnum.getIndex(spnTipoDocumentoVestigio, vestigioVida.getTipoDocumento().getValor()));
                    if (vestigioVida.getNumDocumento() != null)
                        edtNumDocumentoVestigio.setText(vestigioVida.getNumDocumento());
                    if (vestigioVida.getObservacao() != null)
                        edtObservacoesDocumentoVestigio.setText(vestigioVida.getObservacao());
                    break;

                case PAPILOSCOPICO:
                    if (vestigioVida.getObjetoRecolhidoPapiloscopia() != null)
                        edtObjetoPapiloscopia.setText(vestigioVida.getObjetoRecolhidoPapiloscopia());

                    if (vestigioVida.getTipoRecolhimentoAmostraPapiloscopia() != null)
                        spnColetaVestigioPapiloscopico.setSelection(BuscadorEnum.getIndex(spnColetaVestigioPapiloscopico, vestigioVida.getTipoRecolhimentoAmostraPapiloscopia().getValor()));
                    break;

                case OUTRO:
                    if (vestigioVida.getObservacao() != null)
                        edtObservacoesOutro.setText(vestigioVida.getObservacao());

                    if (vestigioVida.getObjetoRecolhido() != null)
                        edtObjetoRecolhido.setText(vestigioVida.getObjetoRecolhido());
            }
        }
    }

    public void SalvarVestigio()
    {
        vestigioVida.LimparCampos();
        vestigioVida.setTipoVestigio(BuscadorEnum.BuscarVestigioVida(spnTipoVestigio.getSelectedItem().toString()));

        switch (vestigioVida.getTipoVestigio())
        {
            case ARMA_DE_FOGO:
                vestigioVida.setNumeracaoArma(edtNumeracaoArma.getText().toString());
                vestigioVida.setCalibreArma(BuscadorEnum.BuscarCalibre(spnCalibreArma.getSelectedItem().toString()));
                vestigioVida.setTipoArma(BuscadorEnum.BuscarTipoArma(spnTipoArma.getSelectedItem().toString()));
                vestigioVida.setObservacao(edtOBservacoesArma.getText().toString());
                break;

            case MUNICAO:
                vestigioVida.setCalibreMunicao(BuscadorEnum.BuscarCalibre(spnCalibreMunicao.getSelectedItem().toString()));
                vestigioVida.setTipoMunicao(BuscadorEnum.BuscarTipoMunicao(spnTipoMunicao.getSelectedItem().toString()));
                vestigioVida.setObservacao(edtOBservacoesMunicao.getText().toString());
                vestigioVida.setCondicaoMunicao(cxbMunicaoBoaCondicao.isChecked());
                try
                {
                    vestigioVida.setQuantidadeMunicao(Integer.valueOf(edtQuantidadeMunicao.getText().toString()));
                } catch (Exception e)
                {
                    vestigioVida.setQuantidadeMunicao(1);
                }
                break;

            case BIOLOGICO:
                vestigioVida.setTipoVestigioBiologico(BuscadorEnum.BuscarVestigioBiologico(spnTipoVestigioBiologico.getSelectedItem().toString()));
                vestigioVida.setTipoRecolhimentoAmostraBiologica(BuscadorEnum.BuscarRecolhimentoAmostraBiologica(spnRecolhimentoVestigioBiologico.getSelectedItem().toString()));
                vestigioVida.setObservacao(edtObservacoesVestigioBiologico.getText().toString());
                break;

            case PAPILOSCOPICO:
                vestigioVida.setObjetoRecolhidoPapiloscopia(edtObjetoPapiloscopia.getText().toString());
                vestigioVida.setTipoRecolhimentoAmostraPapiloscopia(BuscadorEnum.BuscarRecolhimentoAmostraPapiloscopia(spnColetaVestigioPapiloscopico.getSelectedItem().toString()));
                vestigioVida.setObservacao(edtObservacoesPapiloscopia.getText().toString());
                break;

            case DOCUMENTO:
                vestigioVida.setNumDocumento(edtNumDocumentoVestigio.getText().toString());
                vestigioVida.setObservacao(edtObservacoesDocumentoVestigio.getText().toString());
                vestigioVida.setTipoDocumento(BuscadorEnum.BuscarDocumentoPessoa(spnTipoDocumentoVestigio.getSelectedItem().toString()));
                break;

            case OUTRO:
                vestigioVida.setObjetoRecolhido(edtObjetoRecolhido.getText().toString());
                vestigioVida.setObservacao(edtObservacoesOutro.getText().toString());
                break;
        }
        vestigioVida.save();
        vestigioVidaOcorrencia.setVestigioVida(vestigioVida);
        vestigioVidaOcorrencia.setOcorrenciaVida(ocorrenciaVida);

        vestigioVidaOcorrencia.save();

        adapterVestigio.notifyDataSetChanged();
    }


    public void LimparCampos()
    {
        edtNumDocumentoVestigio.setText("");
        edtObservacoesDocumentoVestigio.setText("");
        edtQuantidadeMunicao.setText("");
        edtObservacoesPapiloscopia.setText("");
        edtObjetoPapiloscopia.setText("");
        edtNumeracaoArma.setText("");
        edtObservacoesVestigioBiologico.setText("");

        cxbMunicaoBoaCondicao.setChecked(false);

        spnTipoDocumentoVestigio.setSelection(0);
        spnRecolhimentoVestigioBiologico.setSelection(0);
        spnTipoVestigioBiologico.setSelection(0);
        spnTipoMunicao.setSelection(0);
        spnCalibreMunicao.setSelection(0);
        spnColetaVestigioPapiloscopico.setSelection(0);
        edtObservacoesPapiloscopia.setSelection(0);
        spnCalibreArma.setSelection(0);
        spnTipoArma.setSelection(0);
        spnTipoVestigio.setSelection(0);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mView = null;
        ocorrenciaVida = null;

        lstvVestigios = null;
        fabVestigio = null;
        rltvVestigioDetalhe = null;
        spnTipoVestigio = null;
        rltvVestigioPapiloscopico = null;
        rltvVestigioBiologico = null;
        rltvVestigioArma = null;
        rltvVestigioMunicao = null;
        rltvVestigioOutro = null;
        rltvVestigioDocumento = null;
        adapterVestigio = null;
        ocorrenciaVida = null;
        vestigioVida = null;
        vestigioVidaOcorrencia = null;
        spnTipoDocumentoVestigio = null;
        edtNumDocumentoVestigio = null;
        edtObservacoesDocumentoVestigio = null;
        spnTipoVestigioBiologico = null;
        spnRecolhimentoVestigioBiologico = null;
        edtObservacoesVestigioBiologico = null;
        spnCalibreMunicao = null;
        spnTipoMunicao = null;
        edtQuantidadeMunicao = null;
        cxbMunicaoBoaCondicao = null;
        edtOBservacoesMunicao = null;
        spnTipoArma = null;
        spnCalibreArma = null;
        edtNumeracaoArma = null;
        edtOBservacoesArma = null;
        spnColetaVestigioPapiloscopico = null;
        edtObjetoPapiloscopia = null;
        edtObservacoesPapiloscopia = null;
        edtObjetoRecolhido = null;
        edtObservacoesOutro = null;
    }
}
