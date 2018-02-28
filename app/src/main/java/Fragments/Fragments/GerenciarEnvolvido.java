package Fragments.Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
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
import Enums.CategoriaFoto;
import Enums.DocumentoPessoa;
import Enums.Genero;
import Enums.Transito.Lesao;
import Enums.Transito.TipoEnvolvidoTransito;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnvolvidoTransito;
import Model.Foto;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;
import Util.TempoUtil;
import Util.ViewUtil;


public class GerenciarEnvolvido extends android.support.v4.app.Fragment implements Step
{
    AdapterEnvolvidoTransito adp = null;

    EditText edtNome = null;
    //  CheckBox cxbFatal = null;
    CheckBox cxbDesconhecido = null;
    TextView txvNascimento = null;
    Spinner spnTipoDocumento = null;
    Spinner spnGenero = null;
    Spinner spnLesao = null;
    Spinner spnVeiculo = null;
    Spinner spnTipoEnvolvido = null;
    EditText edtNumDocumento = null;
    List<Veiculo> veiculos = null;
    RelativeLayout rltv_Envolvido = null;
    View mView;
    ListView lstvEnvolvidos = null;
    FloatingActionButton fabEnvolvido = null;
    FloatingActionButton fabFotoEnvolvido = null;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;
    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    Button btnCancel = null;
    Button btnSave = null;
    TextView txvVeiculo = null;
    EnvolvidoTransito envolvido = null;
    OcorrenciaTransitoEnvolvido ocorrenciaEnvolvido = null;
    Ocorrencia ocorrencia;

    List<OcorrenciaTransitoEnvolvido> ocorrenciaEnvolvidos = null;

    OcorrenciaTransito ocorrenciaTransitoEnvolvido;


    ArrayList<EnvolvidoTransito> envolvidotransitoModel = null;

    public GerenciarEnvolvido()
    {

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
                foto = new Foto("Foto do Endereço".toString(), pathImagem, CategoriaFoto.ENVOLVIDOS);

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
        final View root = inflater.inflate(R.layout.fragment_gerenciar_envolvido, container, false);

        mView = root;

        return root;
    }

    private void LimparCampos()
    {
        edtNome.setText("");

        cxbDesconhecido.setChecked(false);

        edtNumDocumento.setText("");

        txvNascimento.setText("00/00/0000");

        spnTipoDocumento.setSelection(0);

        spnLesao.setSelection(0);

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
        txvNascimento = (TextView) v.findViewById(R.id.txv_DataNascimento_Envolvido_Valor);
        //cxbFatal = (CheckBox) v.findViewById(R.id.cxb_EnvolvidoFatal);
        spnLesao = (Spinner) v.findViewById(R.id.spn_Lesao);
//        spnLesao.toString();
        txvVeiculo = (TextView) v.findViewById(R.id.txv_Envolvido_Veiculo);
        rltv_Envolvido = (RelativeLayout) v.findViewById(R.id.rltv_Detalhe_Envolvido);
        lstvEnvolvidos = (ListView) v.findViewById(R.id.lstv_Envolvidos);
        fabEnvolvido = (FloatingActionButton) v.findViewById(R.id.fab_Envolvido);
        fabFotoEnvolvido = (FloatingActionButton) v.findViewById(R.id.fab_Foto_Envolvido);
        btnCancel = (Button) v.findViewById(R.id.btn_Cancelar_Envolvido);
        btnSave = (Button) v.findViewById(R.id.btn_Salvar_Envolvido);
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


    }

    private void CarregarEnvolvido()
    {
        if (envolvido.getNome() != null)
            edtNome.setText(envolvido.getNome());

        if (envolvido.getNome().equals("Desconhecido(a)"))
        {
            edtNome.setEnabled(false);
            cxbDesconhecido.performClick();
        }

        if (envolvido.getDocumentoValor() != null)
            edtNumDocumento.setText(envolvido.getDocumentoValor());

        if (envolvido.getNascimento() != null)
            txvNascimento.setText(envolvido.getNascimentoString());

        if (envolvido.getDocumentoTipo() != null)
            spnTipoDocumento.setSelection(BuscadorEnum.getIndex(spnTipoDocumento, envolvido.getDocumentoTipo().getValor()));

        if (envolvido.getLesao() != null)
            spnLesao.setSelection(BuscadorEnum.getIndex(spnLesao, envolvido.getLesao().getValor()));

        if(!(envolvido.getTipoEnvolvido().equals(TipoEnvolvidoTransito.NAO_IDENTIFICADO) || envolvido.getTipoEnvolvido().equals(TipoEnvolvidoTransito.PEDESTRE)))
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
        envolvido.setDataNascimentoString(txvNascimento.getText().toString());
        envolvido.setVeiculoEnvolvido((Veiculo) spnVeiculo.getSelectedItem());
        envolvido.setDocumentoTipo(BuscadorEnum.BuscarTipoDocumento(spnTipoDocumento.getSelectedItem().toString()));
        envolvido.setGenero(BuscadorEnum.BuscarGenero(spnGenero.getSelectedItem().toString()));
        envolvido.setLesao(BuscadorEnum.BuscarLesao(spnLesao.getSelectedItem().toString()));
        envolvido.setTipoEnvolvido(BuscadorEnum.BuscarTipoEnvolvido(spnTipoEnvolvido.getSelectedItem().toString()));
        envolvido.save();

        ocorrenciaEnvolvido.setOcorrenciaTransito(ocorrenciaTransitoEnvolvido);
        ocorrenciaEnvolvido.setEnvolvidoTransito(envolvido);

        ocorrenciaEnvolvido.save();

        adp.notifyDataSetChanged();
    }

    private void AssociarEventos()
    {
        spnTipoEnvolvido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if (spnTipoEnvolvido.getSelectedItem().equals("Pedestre") || spnTipoEnvolvido.getSelectedItem().equals("Não Identificado") )
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
                envolvido = envolvidotransitoModel.get(position);
                CarregarEnvolvido();
                try
                {
                    ocorrenciaEnvolvido = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ? and envolvido_transito = ?", ocorrenciaTransitoEnvolvido.getId().toString(), envolvido.getId().toString()).get(0);
                } catch (Exception e)
                {
                    ocorrenciaEnvolvido = new OcorrenciaTransitoEnvolvido();
                }


                ViewUtil.modifyAll(rltv_Envolvido, true);
            }
        });

        txvNascimento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TempoUtil.setDate(txvNascimento, getActivity());
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

                                if(ColisaoTransito.find(ColisaoTransito.class,"pedestre = ?",ocorrenciaEnvolvido.getEnvolvidoTransito().getId().toString()).size() > 0)
                                {
                                    Toast.makeText(getContext(), "Não foi possível excluir! O pedestre está atrelado à uma Colisão!", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    return;
                                }

                                adp.remove(ocorrenciaEnvolvido.getEnvolvidoTransito());

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

                envolvido = new EnvolvidoTransito();

                ocorrenciaEnvolvido = new OcorrenciaTransitoEnvolvido();

                envolvido.save();

                ocorrenciaEnvolvido.setOcorrenciaTransito(ocorrenciaTransitoEnvolvido);
                ocorrenciaEnvolvido.setEnvolvidoTransito(envolvido);

                ocorrenciaEnvolvido.save();
                ocorrenciaEnvolvidos.add(ocorrenciaEnvolvido);

                adp.add(envolvido);
                adp.notifyDataSetChanged();

                //quando eu tento isso aqui, ele dá tipo um clear na seleção (caso eu tenha clicado em outro item), e quando dou scroll ele exibe a seleção que estava.
                //lstvEnvolvidos.setSelection(adp.getCount()-1);
                lstvEnvolvidos.performItemClick(lstvEnvolvidos, BuscadorEnum.PegarPosicaoEnvolvido(envolvidotransitoModel, envolvido), lstvEnvolvidos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEnvolvido(envolvidotransitoModel, envolvido)));

                ViewUtil.modifyAll(rltv_Envolvido, true);
                LimparCampos();


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
                magicalPermissions = new MagicalPermissions(GerenciarEnvolvido.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                TipoFotoDialog tfd = new TipoFotoDialog();


            }

            ;
        });


        btnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                SalvarEnvolvido();

                Toast.makeText(v.getContext(), "Envolvido salvo com Sucesso!", Toast.LENGTH_LONG).show();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                CarregarEnvolvido();
                Toast.makeText(v.getContext(), "Operação desfeita!", Toast.LENGTH_LONG).show();
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
                    txvNascimento.setEnabled(false);
                    edtNome.setEnabled(false);
                    edtNome.setText("Desconhecido(a)");
                    edtNumDocumento.setText("0");
                    txvNascimento.setText("00/00/0000");
                    spnTipoDocumento.setSelection(0);
                } else
                {
                    spnTipoDocumento.setEnabled(true);
                    edtNumDocumento.setEnabled(true);
                    txvNascimento.setEnabled(true);
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
    public void onSelected()
    {

        View view = getActivity().getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

       // ((ManterPericiaTransito) getActivity()).toolbar.setTitle("Envolvidos");
        ((ManterPericiaTransito) getActivity()).txvToolbarTitulo.setText("Envolvidos");

        ocorrenciaTransitoEnvolvido = ((ManterPericiaTransito) getActivity()).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoEnvolvido.getOcorrenciaID());

        ocorrenciaEnvolvidos = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class, "ocorrencia_transito = ?", ocorrenciaTransitoEnvolvido.getId().toString());

        envolvidotransitoModel = new ArrayList<>();

        for (OcorrenciaTransitoEnvolvido oe : ocorrenciaEnvolvidos)
        {
            if (oe.getEnvolvidoTransito() != null)
            {
                envolvidotransitoModel.add(oe.getEnvolvidoTransito());
            }
        }

        adp = new AdapterEnvolvidoTransito(envolvidotransitoModel, getActivity());


        AssociarLayout(mView);
        PovoarSpinner(getContext());
        AssociarEventos();


        ViewUtil.modifyAll(rltv_Envolvido, false);


        lstvEnvolvidos.setAdapter(adp);
        lstvEnvolvidos.setSelector(R.drawable.selector);

        if (envolvidotransitoModel.size() == 0)

            ViewUtil.modifyAll(rltv_Envolvido, false);


    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }
//
//    public View getViewByPosition(int pos, ListView listView)
//    {
//        final int firstListItemPosition = listView.getFirstVisiblePosition();
//        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
//
//        if (pos < firstListItemPosition || pos > lastListItemPosition)
//        {
//            return listView.getAdapter().getView(pos, null, listView);
//        } else
//        {
//            final int childIndex = pos - firstListItemPosition;
//            return listView.getChildAt(childIndex);
//
//        }
//    }


    public class TipoFotoDialog
    {
        Activity activity = null;
        Context context;
        ImageView imgvCamera, imgvGaleria;
        OcorrenciaTransito ot;
        Dialog dialog;

        public TipoFotoDialog()
        {
            context = getContext();

            dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_tipo_foto);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle("Escolha a fonte da Fotografia");
            dialog.show();

            activity = getActivity();
            ot = ((ManterPericiaTransito) activity).ocorrenciaTransito;

            AssociarLayout();
            AssociarEventos();

        }


        private void AssociarLayout()
        {
            imgvCamera = (ImageView) dialog.findViewById(R.id.imgv_Opcao_camera);
            imgvGaleria = (ImageView) dialog.findViewById(R.id.imgv_Opcao_galeria);
        }

        public void AssociarEventos()
        {
            imgvCamera.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    magicalCamera.takeFragmentPhoto(GerenciarEnvolvido.this);
                    dialog.dismiss();
                }
            });

            imgvGaleria.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    magicalCamera.selectFragmentPicture(GerenciarEnvolvido.this, "Selecione Uma Imagem");
                    dialog.dismiss();
                }
            });


        }


    }
}