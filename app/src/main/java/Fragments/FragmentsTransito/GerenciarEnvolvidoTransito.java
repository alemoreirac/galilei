package Fragments.FragmentsTransito;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericiaTransito;
import com.example.pefoce.peritolocal.R;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapters.AdapterEnvolvidoTransito;
import Dialogs.TipoFotoDialog;
import Enums.CategoriaFoto;
import Enums.DocumentoPessoa;
import Enums.Genero;
import Enums.Transito.Lesao;
import Enums.Transito.TipoEnvolvidoTransito;
import Enums.Vida.PresencaEnvolvido;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnvolvidoTransito;
import Model.Foto;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoColisao;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;
import Util.TempoUtil;
import Util.ViewUtil;


public class GerenciarEnvolvidoTransito extends android.support.v4.app.Fragment implements Step
{
    AdapterEnvolvidoTransito adapterEnvolvido = null;
    boolean lastClick;
    EditText edtNome = null;
    int lastPosition;
    CheckBox cxbDesconhecido = null;
    //    TextView txvNascimento = null;
    Spinner spnTipoDocumento = null;
    Spinner spnGenero = null;
    Spinner spnLesao = null;
    Spinner spnVeiculo = null;
    Spinner spnTipoEnvolvido = null;
    Spinner spnPresencaEnvolvido = null;
    EditText edtNumDocumento = null;
    List<Veiculo> veiculos = null;
    RelativeLayout rltv_Envolvido = null;
    View mView;
    EditText edtDiaNascimento;
    EditText edtMesNascimento;
    EditText edtAnoNascimento;
    ListView lstvEnvolvidos = null;
    FloatingActionButton fabEnvolvido = null;
    FloatingActionButton fabFotoEnvolvido = null;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;
    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    //    Button btnCancel = null;
//    Button btnSave = null;
    LinearLayout llDataNascimento;

    TextView txvVeiculo = null;
    EnvolvidoTransito envolvido = null;
    OcorrenciaTransitoEnvolvido ocorrenciaEnvolvido = null;
    Ocorrencia ocorrencia;
    List<OcorrenciaTransitoColisao> colisoesOcorrencia;
    List<ColisaoTransito> colisoesList;
    List<OcorrenciaTransitoEnvolvido> ocorrenciaEnvolvidos = null;

    OcorrenciaTransito ocorrenciaTransitoEnvolvido;


    ArrayList<EnvolvidoTransito> envolvidotransitoModel = null;

    public GerenciarEnvolvidoTransito()
    {

    }


    @Override
    public void onSelected()
    {
        lastPosition = -1;


        // ((ManterPericiaTransito) getActivity()).toolbar.setTitle("Envolvidos");
        ((ManterPericiaTransito) getActivity()).txvToolbarTitulo.setText("Envolvidos");

        ocorrenciaTransitoEnvolvido = ((ManterPericiaTransito) getActivity()).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoEnvolvido.getOcorrenciaID());

        ocorrenciaEnvolvidos = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ocorrenciaTransitoEnvolvido.getId().toString());

        envolvidotransitoModel = new ArrayList<>();

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        for (OcorrenciaTransitoEnvolvido oe : ocorrenciaEnvolvidos)
        {
            if (oe.getEnvolvidoTransito() != null)
                envolvidotransitoModel.add(oe.getEnvolvidoTransito());
        }

        adapterEnvolvido = new AdapterEnvolvidoTransito(envolvidotransitoModel, getActivity());

        AssociarLayout(mView);
        PovoarSpinner(getContext());
        AssociarEventos();

        ViewUtil.modifyAll(rltv_Envolvido, false);


        lstvEnvolvidos.setAdapter(adapterEnvolvido);
        lstvEnvolvidos.setSelector(R.drawable.selector);

        if (envolvidotransitoModel.size() == 0)

            ViewUtil.modifyAll(rltv_Envolvido, false);


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        String pathImagem = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "imagem_pericia", "Imagens " + ocorrenciaTransitoEnvolvido.getId().toString(), MagicalCamera.JPEG, true);

        if (pathImagem != null)
        {
            File newFile = new File(pathImagem);

            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoEnvolvido.getDataPath() + "/" + ocorrenciaTransitoEnvolvido.getNumIncidencia() + "/Fotos_Envolvidos/";

            File folder = new File(newPath);

            if (!folder.exists())
                folder.mkdirs();

            newPath += "foto_envolvido" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";

            Foto foto;
            if (newFile.renameTo(new File(newPath)))
                foto = new Foto("Foto do Envolvido".toString(), newPath, CategoriaFoto.ENVOLVIDOS);

            else
                foto = new Foto("Foto do Envolvido".toString(), pathImagem, CategoriaFoto.ENVOLVIDOS);

            foto.save();
            OcorrenciaTransitoFoto ocorrenciaFoto = new OcorrenciaTransitoFoto();
            ocorrenciaFoto.setFoto(foto);
            ocorrenciaFoto.setOcorrenciaTransito(ocorrenciaTransitoEnvolvido);
            ocorrenciaFoto.save();

            if (pathImagem != null)
                Toast.makeText(getActivity(), "Foto salva no caminho: " + pathImagem, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View root = inflater.inflate(R.layout.fragment_gerenciar_envolvido_transito, container, false);

        mView = root;

        return root;
    }

    private void LimparCampos()
    {
        edtNome.setText("");

        cxbDesconhecido.setChecked(false);

        edtNumDocumento.setText("");

//        txvNascimento.setText("00/00/0000");

        edtAnoNascimento.setText("");
        edtMesNascimento.setText("");
        edtDiaNascimento.setText("");

        spnTipoDocumento.setSelection(0);

        spnLesao.setSelection(0);

        spnPresencaEnvolvido.setSelection(0);

        spnVeiculo.setSelection(0);

        spnTipoEnvolvido.setSelection(0);

        //     cxbFatal.setChecked(false);
    }

    @Override
    public void onViewCreated(View v, Bundle savedInstanceState)
    {
        super.onViewCreated(v, savedInstanceState);
    }


    private void AssociarLayout(View v)
    {
        //   cxbCondutor = (CheckBox) v.findViewById(R.id.cxb_Envolvido_Condutor);
        edtNome = (EditText) v.findViewById(R.id.edt_NomeEnvolvido);
        edtNumDocumento = (EditText) v.findViewById(R.id.edt_NumDocEnvolvido);
        spnTipoDocumento = (Spinner) v.findViewById(R.id.spn_TipoDocumentoEnvolvido);
        spnGenero = (Spinner) v.findViewById(R.id.spn_EnvolvidoGenero);
        spnVeiculo = (Spinner) v.findViewById(R.id.spn_Veiculo_Envolvido);
        spnTipoEnvolvido = (Spinner) v.findViewById(R.id.spn_Tipo_Envolvido);
        spnPresencaEnvolvido = (Spinner) v.findViewById(R.id.spn_Presenca);

        llDataNascimento = (LinearLayout) v.findViewById(R.id.ll_Data_Nascimento_Envolvido_Transito);
        //cxbFatal = (CheckBox) v.findViewById(R.id.cxb_EnvolvidoFatal);
        spnLesao = (Spinner) v.findViewById(R.id.spn_Lesao);
//        spnLesao.toString();
        txvVeiculo = (TextView) v.findViewById(R.id.txv_Envolvido_Veiculo);

        edtDiaNascimento = (EditText) v.findViewById(R.id.edt_Dia_Nascimento_Envolvido_Transito);
        edtMesNascimento = (EditText) v.findViewById(R.id.edt_Mes_Nascimento_Envolvido_Transito);
        edtAnoNascimento = (EditText) v.findViewById(R.id.edt_Ano_Nascimento_Envolvido_Transito);
        rltv_Envolvido = (RelativeLayout) v.findViewById(R.id.rltv_Detalhe_Envolvido);
        lstvEnvolvidos = (ListView) v.findViewById(R.id.lstv_Envolvidos);
        fabEnvolvido = (FloatingActionButton) v.findViewById(R.id.fab_Envolvido);
        fabFotoEnvolvido = (FloatingActionButton) v.findViewById(R.id.fab_Foto_Envolvido);
//        btnCancel = (Button) v.findViewById(R.id.btn_Cancelar_Envolvido);
//        btnSave = (Button) v.findViewById(R.id.btn_Salvar_Envolvido);
        cxbDesconhecido = (CheckBox) v.findViewById(R.id.cxb_Envolvido_Desconhecido);
    }

    private void PovoarSpinner(Context ctx)
    {
        ArrayList<String> tipoDocumento = new ArrayList<>();

        for (DocumentoPessoa documentoPessoa : DocumentoPessoa.values())
            tipoDocumento.add(documentoPessoa.getValor());


        spnTipoDocumento.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, tipoDocumento));

        ArrayList<String> generos = new ArrayList<>();

        for (Genero g : Genero.values())
            generos.add(g.getValor());


        spnGenero.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, generos));


        ArrayList<String> presencas = new ArrayList<>();

        for (PresencaEnvolvido pe : PresencaEnvolvido.values())
            presencas.add(pe.getValor());


        spnPresencaEnvolvido.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, presencas));


        ArrayList<String> lesoes = new ArrayList<>();

        for (Lesao l : Lesao.values())
            lesoes.add(l.getValor());


        spnLesao.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, lesoes));

        veiculos = new ArrayList<>();

        List<OcorrenciaTransitoVeiculo> ocorrenciaVeiculos = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ocorrenciaTransitoEnvolvido.getId().toString());

        for (OcorrenciaTransitoVeiculo ov : ocorrenciaVeiculos)
            veiculos.add(ov.getVeiculo());

        spnVeiculo.setAdapter(new ArrayAdapter<Veiculo>(ctx, android.R.layout.simple_spinner_dropdown_item, veiculos));

        ArrayList<String> tipos = new ArrayList<>();

        for (TipoEnvolvidoTransito tet : TipoEnvolvidoTransito.values())
            tipos.add(tet.getValor());

        spnTipoEnvolvido.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, tipos));


        colisoesOcorrencia = OcorrenciaTransitoColisao.find(OcorrenciaTransitoColisao.class, "ocorrencia_transito = ?", ocorrenciaTransitoEnvolvido.getId().toString());

        colisoesList = new ArrayList<>();

        for (OcorrenciaTransitoColisao co : colisoesOcorrencia)
        {
            if (co.getColisaoTransito().getEnvolvidoEvadido() != null)
                colisoesList.add(co.getColisaoTransito());
        }


    }

    private void CarregarEnvolvido()
    {
        if (envolvido.getNome() != null)
        {
            if (envolvido.getNome().equals("Desconhecido(a)"))
            {
                cxbDesconhecido.setChecked(false);
                cxbDesconhecido.performClick();
            } else
            {
                edtNome.setText(envolvido.getNome());

                if (envolvido.getNascimento() != null)
                {
                    String[] datas = envolvido.getNascimentoString().split("/");
                    if (datas != null && datas.length == 3)
                    {
                        edtDiaNascimento.setText(datas[0]);
                        edtMesNascimento.setText(datas[1]);
                        edtAnoNascimento.setText(datas[2]);
                    }
                } else
                {
                    edtDiaNascimento.setText("");
                    edtMesNascimento.setText("");
                    edtAnoNascimento.setText("");
                }

                if (envolvido.getDocumentoTipo() != null)
                    spnTipoDocumento.setSelection(BuscadorEnum.getIndex(spnTipoDocumento, envolvido.getDocumentoTipo().getValor()));
            }
        }

        ArrayList<String> presenca = new ArrayList<>();

        for (PresencaEnvolvido pe : PresencaEnvolvido.values())
        {
            presenca.add(pe.getValor());
        }

        for (ColisaoTransito c : colisoesList)
        {
            if (c.getEnvolvidoEvadido() != null && c.getEnvolvidoEvadido().getId() == envolvido.getId())
            {
                presenca.remove(PresencaEnvolvido.PRESENTE.getValor());
                break;
            }
        }

        spnPresencaEnvolvido.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, presenca));

        if (envolvido.getPresencaEnvolvido() != null)
            spnPresencaEnvolvido.setSelection(BuscadorEnum.getIndex(spnPresencaEnvolvido, envolvido.getPresencaEnvolvido().getValor()));

        if (envolvido.getDocumentoValor() != null)
            edtNumDocumento.setText(envolvido.getDocumentoValor());

//        if (envolvido.getNascimento() != null)
//            txvNascimento.setText(envolvido.getNascimentoString());

//        if (envolvido.getDocumentoTipo() != null)
//            spnTipoDocumento.setSelection(BuscadorEnum.getIndex(spnTipoDocumento, envolvido.getDocumentoTipo().getValor()));

        if (envolvido.getLesao() != null)
            spnLesao.setSelection(BuscadorEnum.getIndex(spnLesao, envolvido.getLesao().getValor()));

        if (!(envolvido.getTipoEnvolvido().equals(TipoEnvolvidoTransito.NAO_IDENTIFICADO) || envolvido.getTipoEnvolvido().equals(TipoEnvolvidoTransito.PEDESTRE)))
        {
            if (envolvido.getVeiculoEnvolvido() != null)
                spnVeiculo.setSelection(BuscadorEnum.getVeiculoIndexById(spnVeiculo, envolvido.getVeiculoEnvolvido().getId()));

        }
        //cxbCondutor.setChecked(envolvido.getCondutor());
        if (envolvido.getTipoEnvolvido() != null)
            spnTipoEnvolvido.setSelection(BuscadorEnum.getIndex(spnTipoEnvolvido, envolvido.getTipoEnvolvido().getValor()));
    }


    private void SalvarEnvolvido()
    {
        envolvido.setNome(edtNome.getText().toString());
        envolvido.setDocumentoValor(edtNumDocumento.getText().toString());
        //envolvido.setCondutor(cxbCondutor.isChecked());
//        envolvido.setDataNascimentoString(txvNascimento.getText().toString());

        envolvido.setVeiculoEnvolvido((Veiculo) spnVeiculo.getSelectedItem());
        envolvido.setDocumentoTipo(BuscadorEnum.BuscarTipoDocumento(spnTipoDocumento.getSelectedItem().toString()));
        envolvido.setGenero(BuscadorEnum.BuscarGenero(spnGenero.getSelectedItem().toString()));
        envolvido.setLesao(BuscadorEnum.BuscarLesao(spnLesao.getSelectedItem().toString()));
        envolvido.setTipoEnvolvido(BuscadorEnum.BuscarTipoEnvolvido(spnTipoEnvolvido.getSelectedItem().toString()));
        envolvido.setPresencaEnvolvido(BuscadorEnum.BuscarPresencaEnvolvido(spnPresencaEnvolvido.getSelectedItem().toString()));


        if (!edtAnoNascimento.getText().toString().equals("") &&
                !edtMesNascimento.getText().toString().equals("") &&
                !edtDiaNascimento.getText().toString().equals(""))

            envolvido.setDataNascimentoString(edtDiaNascimento.getText() + "/"
                    + edtMesNascimento.getText() + "/" + edtAnoNascimento.getText().toString());


        //
//        envolvido.setDocumentoTipo(DocumentoPessoa.valueOf(DocumentoPessoa.class,spnTipoDocumento.getSelectedItem().toString()));
//        envolvido.setGenero(Genero.valueOf(Genero.class,spnGenero.getSelectedItem().toString()));
//        envolvido.setLesao(Lesao.valueOf(Lesao.class,spnLesao.getSelectedItem().toString()));
//        envolvido.setTipoEnvolvido(TipoEnvolvidoTransito.valueOf(TipoEnvolvidoTransito.class,spnTipoEnvolvido.getSelectedItem().toString()));
        envolvido.save();

        ocorrenciaEnvolvido.setOcorrenciaTransito(ocorrenciaTransitoEnvolvido);
        ocorrenciaEnvolvido.setEnvolvidoTransito(envolvido);

        ocorrenciaEnvolvido.save();

        adapterEnvolvido.notifyDataSetChanged();
    }

    private void AssociarEventos()
    {
        lastClick = false;
        edtDiaNascimento.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {
                int current = 0;

                try
                {
                    current = Integer.valueOf(charSequence.toString());
                } catch (Exception e)
                {

                }
                if (current > 31 || (current < 1 && charSequence.length() > 1))
                {
                    edtDiaNascimento.setText(charSequence.toString().substring(0, charSequence.length() - 1));
                    edtDiaNascimento.setSelection(edtDiaNascimento.getText().length());
                }

                if (edtDiaNascimento.getText().toString().length() == 2)
                    edtMesNascimento.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                if (edtDiaNascimento.getCurrentTextColor() == Color.RED)
                    edtDiaNascimento.setTextColor(Color.BLACK);
            }
        });

        edtMesNascimento.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {
                int current = 0;

                try
                {
                    current = Integer.valueOf(charSequence.toString());
                } catch (Exception e)
                {

                }
                if (current > 12 || (current < 1 && charSequence.length() > 1))
                {
                    edtMesNascimento.setText(charSequence.toString().substring(0, charSequence.length() - 1));
                    edtMesNascimento.setSelection(edtMesNascimento.getText().length());
                }

                if (edtMesNascimento.getText().toString().length() == 2)
                    edtAnoNascimento.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });

        edtAnoNascimento.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count)
            {
                int current = 0;

                try
                {
                    current = Integer.valueOf(charSequence.toString());
                } catch (Exception e)
                {

                }

                if (current > (Calendar.getInstance().get(Calendar.YEAR)) || (current < 1 && charSequence.length() > 1))
                {
                    edtAnoNascimento.setText(charSequence.toString().substring(0, charSequence.length() - 1));
                    edtAnoNascimento.setSelection(edtAnoNascimento.getText().length());
                }
            }

            @Override
            public void afterTextChanged(Editable editable)
            {

            }
        });


        edtAnoNascimento.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL)
                {

                    if (edtAnoNascimento.getText().length() != 0)
                        edtAnoNascimento.setText(edtAnoNascimento.getText().toString().substring(0, edtAnoNascimento.getText().toString().length() - 1));
                    edtAnoNascimento.setSelection(edtAnoNascimento.getText().length());

                    if (edtAnoNascimento.getText().toString().length() == 0)
                    {
                        if (!lastClick)
                            lastClick = true;
                        else
                        {
                            edtMesNascimento.requestFocus();
                            edtMesNascimento.setSelection(edtMesNascimento.getText().length());
                            lastClick = false;
                        }
                    }
                    return true;
                }
                return false;
            }
        });


        edtMesNascimento.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL)
                {
                    if (edtMesNascimento.getText().length() != 0)
                        edtMesNascimento.setText(edtMesNascimento.getText().toString().substring(0, edtMesNascimento.getText().toString().length() - 1));

                    edtMesNascimento.setSelection(edtMesNascimento.getText().length());

                    if (edtMesNascimento.getText().toString().length() == 0)
                    {
                        if (!lastClick)
                            lastClick = true;
                        else
                        {
                            edtDiaNascimento.requestFocus();
                            edtDiaNascimento.setSelection(edtDiaNascimento.getText().length());
                            lastClick = false;
                        }
                    }
                    return true;
                }
                return false;
            }
        });


        spnTipoEnvolvido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if (spnTipoEnvolvido.getSelectedItem().equals("Pedestre") || spnTipoEnvolvido.getSelectedItem().equals("Não Identificado"))
                {
                    spnVeiculo.setVisibility(View.INVISIBLE);
                    txvVeiculo.setVisibility(View.INVISIBLE);
                } else
                {
                    spnVeiculo.setVisibility(View.VISIBLE);
                    txvVeiculo.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
            }

        });

        lstvEnvolvidos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if (lastPosition != -1 && lastPosition != position)
                    SalvarEnvolvido();


                lastPosition = position;

                envolvido = envolvidotransitoModel.get(position);

                ViewUtil.modifyAll(rltv_Envolvido, true);

                CarregarEnvolvido();

                try
                {
                    ocorrenciaEnvolvido = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ? and envolvido_transito = ?", ocorrenciaTransitoEnvolvido.getId().toString(), envolvido.getId().toString()).get(0);
                } catch (Exception e)
                {
                    ocorrenciaEnvolvido = new OcorrenciaTransitoEnvolvido();
                }

                adapterEnvolvido.notifyDataSetChanged();
            }
        });


        lstvEnvolvidos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
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

                builder.setTitle("Deletar Ocorrência")
                        .setMessage("Você deseja deletar esta Ocorrência?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                OcorrenciaTransitoEnvolvido ocorrenciaEnvolvido = ocorrenciaEnvolvidos.get(position);

                                if (ColisaoTransito.find(ColisaoTransito.class, "pedestre = ?", ocorrenciaEnvolvido.getEnvolvidoTransito().getId().toString()).size() > 0)
                                {
                                    Toast.makeText(getContext(), "Não foi possível excluir! O envolvido está atrelado à uma Colisão!", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    return;
                                }

                                adapterEnvolvido.remove(ocorrenciaEnvolvido.getEnvolvidoTransito());

                                ocorrenciaEnvolvido.getEnvolvidoTransito().delete();

                                ocorrenciaEnvolvido.delete();

                                ocorrenciaEnvolvidos.remove(position);

                                LimparCampos();

                                if (lstvEnvolvidos.getAdapter().getCount() == 0)
                                {

                                    ViewUtil.modifyAll(rltv_Envolvido, false);

                                    Toast.makeText(getActivity(), "Envolvido Deletado com sucesso!", Toast.LENGTH_LONG).show();
                                }
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

        fabEnvolvido.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (envolvido != null)
                    SalvarEnvolvido();

                envolvido = new EnvolvidoTransito();

                ocorrenciaEnvolvido = new OcorrenciaTransitoEnvolvido();

                envolvido.save();

                ocorrenciaEnvolvido.setOcorrenciaTransito(ocorrenciaTransitoEnvolvido);
                ocorrenciaEnvolvido.setEnvolvidoTransito(envolvido);

                ocorrenciaEnvolvido.save();
                ocorrenciaEnvolvidos.add(ocorrenciaEnvolvido);

                adapterEnvolvido.add(envolvido);
                adapterEnvolvido.notifyDataSetChanged();

                LimparCampos();

                CarregarEnvolvido();

                ViewUtil.modifyAll(rltv_Envolvido, true);

                lstvEnvolvidos.performItemClick(lstvEnvolvidos, BuscadorEnum.PegarPosicaoEnvolvidoTransito(envolvidotransitoModel, envolvido), lstvEnvolvidos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEnvolvidoTransito(envolvidotransitoModel, envolvido)));

            }
        });


        fabFotoEnvolvido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String[] permissions = new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                magicalPermissions = new MagicalPermissions(GerenciarEnvolvidoTransito.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                TipoFotoDialog.show(GerenciarEnvolvidoTransito.this, getActivity(), magicalCamera);
            }
        });

        spnTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (spnTipoDocumento.getSelectedItem().toString().equals(DocumentoPessoa.NP.getValor()))
                {
                    edtNumDocumento.setText("");
                    edtNumDocumento.setEnabled(false);
                } else
                    edtNumDocumento.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        cxbDesconhecido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbDesconhecido.isChecked())
                {
                    spnTipoDocumento.setEnabled(false);
                    edtNumDocumento.setEnabled(false);
//                    txvNascimento.setEnabled(false);

                    edtNome.setEnabled(false);
                    edtNome.setText("Desconhecido(a)");
                    edtNumDocumento.setText("0");
                    edtAnoNascimento.setEnabled(false);
                    edtMesNascimento.setEnabled(false);
                    edtDiaNascimento.setEnabled(false);
                    edtAnoNascimento.setText("");
                    edtMesNascimento.setText("");
                    edtDiaNascimento.setText("");
                    spnTipoDocumento.setSelection(0);
                } else
                {
                    spnTipoDocumento.setEnabled(true);
                    edtNumDocumento.setEnabled(true);
                    edtAnoNascimento.setEnabled(true);
                    edtMesNascimento.setEnabled(true);
                    edtDiaNascimento.setEnabled(true);
                    edtNome.setEnabled(true);
                    edtNome.setText("");
                    edtNumDocumento.setText("");
                    spnTipoDocumento.setSelection(0);
                }
            }
        });
    }

    @Override
    public VerificationError verifyStep()
    {
        if (rltv_Envolvido.getChildAt(0).isEnabled())
            try
            {
                SalvarEnvolvido();
            } catch (Exception e)
            {
                VerificationError ve = new VerificationError(e.getMessage());
                return ve;
            }
        return null;
    }


    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

}