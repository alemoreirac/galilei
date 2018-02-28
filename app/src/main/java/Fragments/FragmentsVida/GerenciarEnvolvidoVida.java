package Fragments.FragmentsVida;

import android.Manifest;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.GerenciarCorpo;
import com.example.pefoce.peritolocal.ManterPericiaVida;
import com.example.pefoce.peritolocal.R;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapters.AdapterEnvolvidoVida;
import Dialogs.AudioDialog;
import Dialogs.PosicaoCadaverDialog;
import Dialogs.TipoFotoDialog;
import Enums.CategoriaFoto;
import Enums.DocumentoPessoa;
import Enums.Genero;
import Enums.UnidadeTempo;
import Fragments.FragmentsTransito.GerenciarVeiculo;
import Model.Foto;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Util.BuscadorEnum;
import Util.TempoUtil;
import Util.ViewUtil;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GerenciarEnvolvidoVida.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GerenciarEnvolvidoVida#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GerenciarEnvolvidoVida extends android.support.v4.app.Fragment implements Step
{
    int lastPosition;
    RelativeLayout rltvEnvolvido;
    ListView lstvEnvolvidos;
    EditText edtNomeEnvolvido;
    EditText edtNumDocumento;
    Spinner spnTipoDocumento;
    TextView txvDataNascimento;
    EditText edtTempoMorte;
    Spinner spnUnidadeTempo;
    Spinner spnGenero;
    EditText edtVestimentas;
    EditText edtObservacoes;
    CheckBox cxbDesconhecido;
    Button btnLesoes;
    Button btnPosicaoCadaver;
    CheckBox cxbViolencia;
    ImageButton imgbAudio;
    ImageButton imgbCamera;
    FloatingActionButton fabEnvolvidos;


    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;

    AdapterEnvolvidoVida adapterEnvolvidoVida;
    View mView;

    OcorrenciaVida ocorrenciaVida;
    Ocorrencia ocorrencia;
    List<OcorrenciaEnvolvidoVida> ocorrenciaEnvolvidosList;
    ArrayList<EnvolvidoVida> envolvidoVidaModel;
    OcorrenciaEnvolvidoVida ocorrenciaEnvolvidoVida;
    public EnvolvidoVida envolvidoVida;

    private OnFragmentInteractionListener mListener;

    public GerenciarEnvolvidoVida()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GerenciarEnvolvidoVida.
     */
    // TODO: Rename and change types and number of parameters
    public static GerenciarEnvolvidoVida newInstance(String param1, String param2)
    {
        GerenciarEnvolvidoVida fragment = new GerenciarEnvolvidoVida();

        return fragment;
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
        mView = inflater.inflate(R.layout.fragment_gerenciar_envolvido_vida, container, false);

        if (mView != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        }
        return mView;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
            mListener.onFragmentInteraction(uri);
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
        if (rltvEnvolvido.getChildAt(0).isEnabled())
            try
            {
                SalvarEnvolvidoVida();
            } catch (Exception e)
            {
                VerificationError ve = new VerificationError(e.getMessage());
                return ve;
            }
        return null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        String pathImagem = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "imagem_pericia", "Imagens " + ocorrenciaVida.getId().toString(), MagicalCamera.JPEG, true);

        if (pathImagem != null)
        {
            File newFile = new File(pathImagem);

            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() + "/Fotos_Envolvidos/";

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
            OcorrenciaVidaFoto ocorrenciaFoto = new OcorrenciaVidaFoto();
            ocorrenciaFoto.setFoto(foto);
            ocorrenciaFoto.setOcorrenciaVida(ocorrenciaVida);
            ocorrenciaFoto.save();

            if (pathImagem != null)
                Toast.makeText(getActivity(), "Foto salva no caminho: " + pathImagem, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_LONG).show();
        }
    }


    @Override
    public void onSelected()
    {
        lastPosition= -1;

        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Envolvidos");

        AssociarLayout(mView);
        PovoarSpinners(getContext());
        AssociarEventos();

        LimparCampos();

        ocorrenciaVida = ((ManterPericiaVida) getActivity()).ocorrenciaVida;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class,ocorrenciaVida.getOcorrenciaID());

        ocorrenciaEnvolvidosList = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        envolvidoVidaModel = new ArrayList<>();

        for (OcorrenciaEnvolvidoVida eov : ocorrenciaEnvolvidosList)
            envolvidoVidaModel.add(eov.getEnvolvidoVida());

        adapterEnvolvidoVida = new AdapterEnvolvidoVida(envolvidoVidaModel, getActivity());

        lstvEnvolvidos.setAdapter(adapterEnvolvidoVida);

        ViewUtil.modifyAll(rltvEnvolvido, false);

        Bundle bd = getArguments();

        if (bd.getBoolean("DiretoParaEnvolvido"))
        {
            try
            {
                EnvolvidoVida envolvidoIndex = EnvolvidoVida.findById(EnvolvidoVida.class, bd.getLong("EnvolvidoId"));
                lstvEnvolvidos.performItemClick(lstvEnvolvidos, BuscadorEnum.PegarPosicaoEnvolvidoVida(envolvidoVidaModel, envolvidoIndex), lstvEnvolvidos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEnvolvidoVida(envolvidoVidaModel, envolvidoIndex)));
            } catch (Exception e)
            {
            }
        }
    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    public void AssociarLayout(View view)
    {
        spnGenero = (Spinner) view.findViewById(R.id.spn_Genero_Envolvido_Vida);
        spnUnidadeTempo = (Spinner) view.findViewById(R.id.spn_Unidade_Tempo_Morte_Envolvido_Vida);
        spnTipoDocumento = (Spinner) view.findViewById(R.id.spn_Tipo_Documento_Envolvido_Vida);
        edtNomeEnvolvido = (EditText) view.findViewById(R.id.edt_Nome_Envolvido_Vida);
        edtVestimentas = (EditText) view.findViewById(R.id.edt_Vestimentas_Envolvido_Vida);
        edtNumDocumento = (EditText) view.findViewById(R.id.edt_NumDocumento_Envolvido_Vida);
        edtObservacoes = (EditText) view.findViewById(R.id.edt_Observacao_Envolvido_Vida);
        edtTempoMorte = (EditText) view.findViewById(R.id.edt_Tempo_Morte_Envolvido_Vida);
        txvDataNascimento = (TextView) view.findViewById(R.id.txv_Data_Nascimento_Valor_Envolvido_Vida);
        cxbDesconhecido = (CheckBox) view.findViewById(R.id.cxb_Envolvido_Vida_Desconhecido);
        btnLesoes = (Button) view.findViewById(R.id.btn_Lesoes_Envolvido_Vida);
        btnPosicaoCadaver = (Button) view.findViewById(R.id.btn_Posicao_Cadaver_Envolvido_Vida);
        rltvEnvolvido = (RelativeLayout) view.findViewById(R.id.rltv_Detalhe_Envolvido_Vida);
        fabEnvolvidos = (FloatingActionButton) view.findViewById(R.id.fab_Envolvido_Vida);
        lstvEnvolvidos = (ListView) view.findViewById(R.id.lstv_envolvidos_vida);
        cxbViolencia = (CheckBox) view.findViewById(R.id.cxb_Violencia_vida);
        imgbAudio = (ImageButton) view.findViewById(R.id.imgb_Audio_Envolvido_Vida);
        imgbCamera = (ImageButton) view.findViewById(R.id.imgb_Foto_Envolvido_Vida);
    }

    public void InterfacePosicaoCadaver(Bundle bd)
    {
        envolvidoVida.setPosicaoBracoDireito(BuscadorEnum.BuscarPosicaoBraco(bd.getString("BracoDireito")));
        envolvidoVida.setPosicaoBracoEsquerdo(BuscadorEnum.BuscarPosicaoBraco(bd.getString("BracoEsquerdo")));

        envolvidoVida.setPosicaoPernaDireita(BuscadorEnum.BuscarPosicaoPerna(bd.getString("PernaDireita")));
        envolvidoVida.setPosicaoPernaEsquerda(BuscadorEnum.BuscarPosicaoPerna(bd.getString("PernaEsquerda")));

        envolvidoVida.setPosicaoCabeca(BuscadorEnum.BuscarPosicaoCabeca(bd.getString("Cabeca")));

        envolvidoVida.setPosicaoCorpo(BuscadorEnum.BuscarPosicaoTorax(bd.getString("Torax")));
    }

    public void PovoarSpinners(Context ctx)
    {
        List<String> tiposDocumento = new ArrayList<>();

        for (DocumentoPessoa dcp : DocumentoPessoa.values())
            tiposDocumento.add(dcp.getValor());

        spnTipoDocumento.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposDocumento));

        List<String> unidadesTempo = new ArrayList<>();

        for (UnidadeTempo ut : UnidadeTempo.values())
            unidadesTempo.add(ut.getValor());

        spnUnidadeTempo.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, unidadesTempo));

        List<String> generos = new ArrayList<>();

        for (Genero g : Genero.values())
            generos.add(g.getValor());

        spnGenero.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, generos));
    }

    public void AssociarEventos()
    {
        fabEnvolvidos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                envolvidoVida = new EnvolvidoVida();

                ocorrenciaEnvolvidoVida = new OcorrenciaEnvolvidoVida();

                envolvidoVida.save();

                ocorrenciaEnvolvidoVida.setEnvolvidoVida(envolvidoVida);

                ocorrenciaEnvolvidoVida.setOcorrenciaVida(ocorrenciaVida);

                ocorrenciaEnvolvidoVida.save();

                ocorrenciaEnvolvidosList.add(ocorrenciaEnvolvidoVida);

                adapterEnvolvidoVida.add(envolvidoVida);
                adapterEnvolvidoVida.notifyDataSetChanged();

                //lstvEnvolvidos.performItemClick(lstvEnvolvidos, BuscadorEnum.PegarPosicaoEnvolvidoTransito(envolvidotransitoModel, envolvido), lstvEnvolvidos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEnvolvidoTransito(envolvidotransitoModel, envolvido)));
                lstvEnvolvidos.performItemClick(lstvEnvolvidos, BuscadorEnum.PegarPosicaoEnvolvidoVida(envolvidoVidaModel, envolvidoVida), lstvEnvolvidos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEnvolvidoVida(envolvidoVidaModel, envolvidoVida)));

                ViewUtil.modifyAll(rltvEnvolvido, true);

                LimparCampos();
            }
        });


        cxbDesconhecido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbDesconhecido.isChecked())
                {
                    edtNomeEnvolvido.setText("Desconhecido(a)");
                    edtNumDocumento.setText("");
                    txvDataNascimento.setText("00/00/0000");
                    spnTipoDocumento.setSelection(0);
                    spnTipoDocumento.setEnabled(false);
                    edtNumDocumento.setEnabled(false);
                    edtNomeEnvolvido.setEnabled(false);
                    txvDataNascimento.setEnabled(false);
                } else
                {
                    edtNomeEnvolvido.setText("");
                    spnTipoDocumento.setEnabled(true);
                    edtNumDocumento.setEnabled(true);
                    edtNomeEnvolvido.setEnabled(true);
                    txvDataNascimento.setEnabled(true);
                }
            }
        });

        spnTipoDocumento.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if(spnTipoDocumento.getSelectedItem().toString().equals(DocumentoPessoa.NP.getValor()))
                {
                    edtNumDocumento.setText("");
                    edtNumDocumento.setEnabled(false);
                }
                else
                    edtNumDocumento.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        imgbCamera.setOnClickListener(new View.OnClickListener()
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
                magicalPermissions = new MagicalPermissions(GerenciarEnvolvidoVida.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                TipoFotoDialog tfd = new TipoFotoDialog(GerenciarEnvolvidoVida.this, getActivity(),magicalCamera);
            }
        });

        imgbAudio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = getActivity().getFragmentManager();
                AudioDialog dialogFragment = new AudioDialog();

                Bundle bd = new Bundle();
                bd.putString("SecaoVida", "Envolvido");
                bd.putLong("EnvolvidoId",envolvidoVida.getId());
                bd.putLong("OcorrenciaId",ocorrenciaVida.getOcorrenciaID());

                dialogFragment.setArguments(bd);
                dialogFragment.show(fm, "Seleção");
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

                builder.setTitle("Deletar Envolvido")
                        .setMessage("Você deseja deletar este envolvido?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                //OcorrenciaTransitoColisao ocorrenciaColisao = colisoesList.get(position);
                                OcorrenciaEnvolvidoVida ocorrenciaEnvolvidoVida = ocorrenciaEnvolvidosList.get(position);

                                adapterEnvolvidoVida.remove(ocorrenciaEnvolvidoVida.getEnvolvidoVida());

                                if(ocorrenciaEnvolvidoVida.getEnvolvidoVida()!=null)
                                ocorrenciaEnvolvidoVida.getEnvolvidoVida().delete();

                                ocorrenciaEnvolvidoVida.delete();

                                ocorrenciaEnvolvidosList.remove(position);

                                LimparCampos();

                                ViewUtil.modifyAll(rltvEnvolvido, false);

                                Toast.makeText(getActivity(), "Envolvido Deletado com sucesso!", Toast.LENGTH_LONG).show();
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

        lstvEnvolvidos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if(lastPosition!= -1 && lastPosition != position)
                {
                    try
                    {
                        ocorrenciaEnvolvidoVida = ocorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "envolvido_vida = ?", envolvidoVida.getId().toString()).get(0);
                    }catch (Exception e)
                    {
                        ocorrenciaEnvolvidoVida = new OcorrenciaEnvolvidoVida();
                    }
                    SalvarEnvolvidoVida();
                }
                else
                    lastPosition = position;

                envolvidoVida = envolvidoVidaModel.get(position);

                if(envolvidoVida==null)
                    envolvidoVida = new EnvolvidoVida();

                LimparCampos();

                CarregarEnvolvido();

                try
                {
                    ocorrenciaEnvolvidoVida = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ? and envolvido_vida = ?", ocorrenciaVida.getId().toString(), envolvidoVida.getId().toString()).get(0);

                } catch (Exception e)
                {
                    ocorrenciaEnvolvidoVida = new OcorrenciaEnvolvidoVida();
                }

                ViewUtil.modifyAll(rltvEnvolvido, true);

                if(envolvidoVida.getNome()!=null)
                {
                if (envolvidoVida.getNome().equals("Desconhecido(a)"))
                {
                    cxbDesconhecido.setEnabled(true);
                    cxbDesconhecido.setChecked(false);
                    cxbDesconhecido.performClick();
                }
                }
            }
        });

        btnPosicaoCadaver.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                PosicaoCadaverDialog pcd = new PosicaoCadaverDialog(GerenciarEnvolvidoVida.this, getActivity());
            }
        });

        btnLesoes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SalvarEnvolvidoVida();
                Intent it = new Intent(getContext(), GerenciarCorpo.class);
                it.putExtra("EnvolvidoId", envolvidoVida.getId());
                it.putExtra("OcorrenciaId", ocorrenciaVida.getId());
                it.putExtra("GeneroEnvolvido", spnGenero.getSelectedItem().toString());
                startActivity(it);
            }
        });

        txvDataNascimento.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TempoUtil.setDate(txvDataNascimento, getActivity());
            }
        });
    }

    public void CarregarEnvolvido()
    {
        if(envolvidoVida== null)
            return;
        cxbViolencia.setChecked(envolvidoVida.isMorteViolenta());

        if (envolvidoVida.getNome() == "Desconhecido(a)")
        {
            cxbDesconhecido.setChecked(false);
            cxbDesconhecido.performClick();
        } else
        {
            if (envolvidoVida.getNome() != null)
                edtNomeEnvolvido.setText(envolvidoVida.getNome());
            if (envolvidoVida.getNascimento() != null)
                txvDataNascimento.setText(envolvidoVida.getDataNascimentoString());
            if (envolvidoVida.getDocumentoTipo() != null)
                spnTipoDocumento.setSelection(BuscadorEnum.getIndex(spnTipoDocumento, envolvidoVida.getDocumentoTipo().getValor()));
            if (envolvidoVida.getDocumentoValor() != null)
                edtNumDocumento.setText(envolvidoVida.getDocumentoValor());
        }

        if (envolvidoVida.getGenero() != null)
            spnGenero.setSelection(BuscadorEnum.getIndex(spnGenero, envolvidoVida.getGenero().getValor()));

        if (envolvidoVida.getUnidadeTempo() != null)
            spnUnidadeTempo.setSelection(BuscadorEnum.getIndex(spnUnidadeTempo, envolvidoVida.getUnidadeTempo().getValor()));

        if (envolvidoVida.getPeriodoMorte() != 0)
            edtTempoMorte.setText(String.valueOf(envolvidoVida.getPeriodoMorte()));

        if (envolvidoVida.getVestes() != null)
            edtVestimentas.setText(envolvidoVida.getVestes());

        if (envolvidoVida.getObservacoes() != null)
            edtObservacoes.setText(envolvidoVida.getObservacoes());
    }

    public void SalvarEnvolvidoVida()
    {
        envolvidoVida.setNome(edtNomeEnvolvido.getText().toString());
        envolvidoVida.setDocumentoTipo(BuscadorEnum.BuscarDocumentoPessoa(spnTipoDocumento.getSelectedItem().toString()));
        envolvidoVida.setDocumentoValor(edtNumDocumento.getText().toString());
        envolvidoVida.setObservacoes(edtObservacoes.getText().toString());
        envolvidoVida.setGenero(BuscadorEnum.BuscarGenero(spnGenero.getSelectedItem().toString()));
        envolvidoVida.setUnidadeTempo(BuscadorEnum.BuscarUnidadeTempo(spnUnidadeTempo.getSelectedItem().toString()));
        envolvidoVida.setDataNascimentoString(txvDataNascimento.getText().toString());
        envolvidoVida.setVestes(edtVestimentas.getText().toString());
        envolvidoVida.setMorteViolenta(cxbViolencia.isChecked());
        try
        {
            envolvidoVida.setPeriodoMorte(Integer.valueOf(edtTempoMorte.getText().toString()));
        } catch (Exception e)
        {
            envolvidoVida.setPeriodoMorte(0);
        }

        envolvidoVida.save();

        ocorrenciaEnvolvidoVida.setEnvolvidoVida(envolvidoVida);
        ocorrenciaEnvolvidoVida.setOcorrenciaVida(ocorrenciaVida);

        ocorrenciaEnvolvidoVida.save();

        adapterEnvolvidoVida.notifyDataSetChanged();
    }

    public void LimparCampos()
    {
        edtTempoMorte.setText("");
        edtObservacoes.setText("");
        edtVestimentas.setText("");
        edtNomeEnvolvido.setText("");
        edtNumDocumento.setText("");

        cxbDesconhecido.setChecked(false);
        cxbViolencia.setChecked(true);

        spnGenero.setSelection(0);
        spnUnidadeTempo.setSelection(0);
        spnTipoDocumento.setSelection(0);

        txvDataNascimento.setText("00/00/0000");
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mView = null;
        rltvEnvolvido = null;
        lstvEnvolvidos = null;
        edtNomeEnvolvido = null;
        edtNumDocumento = null;
        spnTipoDocumento = null;
        txvDataNascimento = null;
        edtTempoMorte = null;
        spnUnidadeTempo = null;
        spnGenero = null;
        edtVestimentas = null;
        edtObservacoes = null;
        cxbDesconhecido = null;
        btnLesoes = null;
        btnPosicaoCadaver = null;
        fabEnvolvidos = null;
        cxbViolencia = null;
        adapterEnvolvidoVida = null;
    }
}
