package Fragments.FragmentsVida;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.KeyEvent;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pefoce.peritolocal.DesenhoEnvolvidoActivity;
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
import Enums.Vida.IndiciosTempoMorte;
import Enums.Vida.TipoMorte;
import Model.Foto;
import Model.Ocorrencia;
import Model.Vida.EnderecoVida;
import Model.Vida.OcorrenciaEnderecoVida;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Util.BuscadorEnum;
import Util.StringUtil;
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
    //    TextView txvDataNascimento;
    EditText edtDiaNascimento;
    EditText edtMesNascimento;
    EditText edtAnoNascimento;
    Spinner spnIndiciosTempoMorte;
    //    EditText edtTempoMorte;
    Spinner spnEndereco;
    Spinner spnGenero;
    EditText edtVestimentas;
    EditText edtObservacoes;
    CheckBox cxbDesconhecido;
    Button btnLesoes;
    Button btnDesenho;
    Button btnPosicaoCadaver;
    Spinner spnTipoMorte;
    //    CheckBox cxbViolencia;
    LinearLayout llDatePicker;
    ImageButton imgbAudio;
    ImageButton imgbCamera;
    FloatingActionButton fabEnvolvidos;

    boolean lastClick;
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

    ArrayList<EnderecoVida> enderecos;

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
                if (!TempoUtil.checkValues(llDatePicker))
                {
                    edtDiaNascimento.setTextColor(Color.RED);
                    Toast.makeText(getContext(), "Dia inválido", Toast.LENGTH_LONG).show();
                    VerificationError ve = new VerificationError("");
                    return ve;
                } else
                    SalvarEnvolvidoVida();


            } catch (Exception e)
            {
                VerificationError ve = new VerificationError(e.getMessage());
                Toast.makeText(getContext(), e.toString(), Toast.LENGTH_LONG).show();
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
        lastPosition = -1;

        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Envolvidos");


        ocorrenciaVida = ((ManterPericiaVida) getActivity()).ocorrenciaVida;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

        ocorrenciaEnvolvidosList = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());


        AssociarLayout(mView);
        PovoarSpinners(getContext());
        AssociarEventos();

        LimparCampos();

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
        llDatePicker = (LinearLayout) view.findViewById(R.id.ll_Data_Nascimento_Envolvido_Vida);
        spnGenero = (Spinner) view.findViewById(R.id.spn_Genero_Envolvido_Vida);
        spnIndiciosTempoMorte = (Spinner) view.findViewById(R.id.spn_Indicios_Tempo_Morte_Envolvido_Vida);
        spnEndereco = (Spinner) view.findViewById(R.id.spn_Endereco_Envolvido_Vida);
        spnTipoDocumento = (Spinner) view.findViewById(R.id.spn_Tipo_Documento_Envolvido_Vida);
        edtNomeEnvolvido = (EditText) view.findViewById(R.id.edt_Nome_Envolvido_Vida);
        edtVestimentas = (EditText) view.findViewById(R.id.edt_Vestimentas_Envolvido_Vida);
        edtNumDocumento = (EditText) view.findViewById(R.id.edt_NumDocumento_Envolvido_Vida);
        edtObservacoes = (EditText) view.findViewById(R.id.edt_Observacao_Envolvido_Vida);
        edtDiaNascimento = (EditText) view.findViewById(R.id.edt_Dia_Nascimento_Envolvido_Vida);
        edtMesNascimento = (EditText) view.findViewById(R.id.edt_Mes_Nascimento_Envolvido_Vida);
        edtAnoNascimento = (EditText) view.findViewById(R.id.edt_Ano_Nascimento_Envolvido_Vida);
        cxbDesconhecido = (CheckBox) view.findViewById(R.id.cxb_Envolvido_Vida_Desconhecido);
        btnLesoes = (Button) view.findViewById(R.id.btn_Lesoes_Envolvido_Vida);
        btnDesenho = (Button) view.findViewById(R.id.btn_Desenho_Envolvido_Vida);
        btnPosicaoCadaver = (Button) view.findViewById(R.id.btn_Posicao_Cadaver_Envolvido_Vida);
        rltvEnvolvido = (RelativeLayout) view.findViewById(R.id.rltv_Detalhe_Envolvido_Vida);
        fabEnvolvidos = (FloatingActionButton) view.findViewById(R.id.fab_Envolvido_Vida);
        lstvEnvolvidos = (ListView) view.findViewById(R.id.lstv_envolvidos_vida);
//        cxbViolencia = (CheckBox) view.findViewById(R.id.cxb_Violencia_vida);
        spnTipoMorte = (Spinner) view.findViewById(R.id.spn_Tipo_Morte_Envolvido_Vida);
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
        List<String> tiposMorte = new ArrayList<>();

        for (TipoMorte tm : TipoMorte.values())
            tiposMorte.add(tm.getValor());

        spnTipoMorte.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposMorte));

        List<String> tiposDocumento = new ArrayList<>();

        for (DocumentoPessoa dcp : DocumentoPessoa.values())
            tiposDocumento.add(dcp.getValor());

        spnTipoDocumento.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposDocumento));

        List<String> indiciosMorte = new ArrayList<>();

        for (IndiciosTempoMorte itm : IndiciosTempoMorte.values())
            indiciosMorte.add(itm.getValor());

        spnIndiciosTempoMorte.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, indiciosMorte));

        List<String> generos = new ArrayList<>();

        for (Genero g : Genero.values())
            generos.add(g.getValor());

        spnGenero.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, generos));

//        List<String>


        enderecos = new ArrayList<>();

        List<OcorrenciaEnderecoVida> ocorrenciaEnderecosSpn = OcorrenciaEnderecoVida.find(OcorrenciaEnderecoVida.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        for (OcorrenciaEnderecoVida ov : ocorrenciaEnderecosSpn)
            enderecos.add(ov.getEnderecoVida());

        spnEndereco.setAdapter(new ArrayAdapter<EnderecoVida>(ctx, android.R.layout.simple_spinner_dropdown_item, enderecos));
    }

    public void AssociarEventos()
    {
        fabEnvolvidos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (envolvidoVida != null)
                    SalvarEnvolvidoVida();

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

                ViewUtil.modifyAll(rltvEnvolvido, true);

                LimparCampos();

                lstvEnvolvidos.performItemClick(lstvEnvolvidos, BuscadorEnum.PegarPosicaoEnvolvidoVida(envolvidoVidaModel, envolvidoVida), lstvEnvolvidos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEnvolvidoVida(envolvidoVidaModel, envolvidoVida)));

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
//                    txvDataNascimento.setText("--/--/----");
                    spnTipoDocumento.setSelection(0);
                    spnTipoDocumento.setEnabled(false);
                    edtNumDocumento.setEnabled(false);
                    edtNomeEnvolvido.setEnabled(false);
//                    txvDataNascimento.setEnabled(false);
                    edtAnoNascimento.setEnabled(false);
                    edtMesNascimento.setEnabled(false);
                    edtDiaNascimento.setEnabled(false);
                    edtAnoNascimento.setText("");
                    edtMesNascimento.setText("");
                    edtDiaNascimento.setText("");
                } else
                {

                    edtNomeEnvolvido.setText("");
                    spnTipoDocumento.setEnabled(true);
                    edtNumDocumento.setEnabled(true);
                    edtNomeEnvolvido.setEnabled(true);
//                    txvDataNascimento.setEnabled(true);
                    edtAnoNascimento.setEnabled(true);
                    edtMesNascimento.setEnabled(true);
                    edtDiaNascimento.setEnabled(true);

                }
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

                TipoFotoDialog.show(GerenciarEnvolvidoVida.this, getActivity(), magicalCamera);
            }
        });

        imgbAudio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                FragmentManager fm = getActivity().getFragmentManager();
//                AudioDialog dialogFragment = new AudioDialog();

                Bundle bd = new Bundle();
                bd.putString("SecaoVida", "Envolvido");
                bd.putLong("EnvolvidoId", envolvidoVida.getId());
                bd.putLong("OcorrenciaId", ocorrenciaVida.getOcorrenciaID());

                AudioDialog.show(getActivity(), bd);
//                dialogFragment.setArguments(bd);
//                dialogFragment.show(fm, "Seleção");
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

                                if (ocorrenciaEnvolvidoVida.getEnvolvidoVida() != null)
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
                if (lastPosition != -1 && lastPosition != position)
                {

                    SalvarEnvolvidoVida();
                }

                lastPosition = position;

                envolvidoVida = envolvidoVidaModel.get(position);

                if (envolvidoVida == null)
                    envolvidoVida = new EnvolvidoVida();

//                LimparCampos();

                CarregarEnvolvido();

                try
                {
                    ocorrenciaEnvolvidoVida = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ? and envolvido_vida = ?", ocorrenciaVida.getId().toString(), envolvidoVida.getId().toString()).get(0);

                } catch (Exception e)
                {
                    ocorrenciaEnvolvidoVida = new OcorrenciaEnvolvidoVida();
                }

                ViewUtil.modifyAll(rltvEnvolvido, true);

                if (envolvidoVida.getNome() != null)
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
                PosicaoCadaverDialog.show(GerenciarEnvolvidoVida.this, getActivity());
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

        btnDesenho.setOnClickListener(new View.OnClickListener()
        {
            //TODO: utilize um dialog para confirmar que a foto antiga será apagada, tentar exibir foto antiga?

            @Override
            public void onClick(View v)
            {
                SalvarEnvolvidoVida();
                Intent it = new Intent(getContext(), DesenhoEnvolvidoActivity.class);
                it.putExtra("EnvolvidoId", envolvidoVida.getId());
                it.putExtra("OcorrenciaId", ocorrenciaVida.getId());
                it.putExtra("GeneroEnvolvido", spnGenero.getSelectedItem().toString());
                startActivity(it);
            }
        });

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
            {}
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

//        txvDataNascimento.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                TempoUtil.setDate(txvDataNascimento, getActivity());
//            }
//        });
    }

    public void CarregarEnvolvido()
    {
        if (envolvidoVida == null)
            return;


        if (envolvidoVida.getNome() == "Desconhecido(a)")
        {
            cxbDesconhecido.setChecked(false);
            cxbDesconhecido.performClick();
        } else
        {
            if (envolvidoVida.getNome() != null)
                edtNomeEnvolvido.setText(envolvidoVida.getNome());

            if (envolvidoVida.getNascimento() != null)
            {
                String[] datas = envolvidoVida.getDataNascimentoString().split("/");
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
            if (envolvidoVida.getDocumentoTipo() != null)
            {
                if (envolvidoVida.getDocumentoTipo().equals(DocumentoPessoa.NP))
                {
                    edtNumDocumento.setText("");
                    edtNumDocumento.setEnabled(false);
                } else
                    spnTipoDocumento.setSelection(BuscadorEnum.getIndex(spnTipoDocumento, envolvidoVida.getDocumentoTipo().getValor()));
            }

            if (envolvidoVida.getDocumentoValor() != null)
                edtNumDocumento.setText(envolvidoVida.getDocumentoValor());
        }

        if (envolvidoVida.getTipoMorte() != null)
            spnTipoMorte.setSelection(BuscadorEnum.getIndex(spnTipoMorte, envolvidoVida.getTipoMorte().getValor()));

        if (envolvidoVida.getGenero() != null)
            spnGenero.setSelection(BuscadorEnum.getIndex(spnGenero, envolvidoVida.getGenero().getValor()));

        if (envolvidoVida.getIndiciosTempoMorte() != null)
            spnIndiciosTempoMorte.setSelection(BuscadorEnum.getIndex(spnIndiciosTempoMorte, envolvidoVida.getIndiciosTempoMorte().getValor()));

        if (envolvidoVida.getVestes() != null)
            edtVestimentas.setText(envolvidoVida.getVestes());

        if (envolvidoVida.getObservacoes() != null)
            edtObservacoes.setText(envolvidoVida.getObservacoes());

        if (envolvidoVida.getEnderecoId() != null)
            spnEndereco.setSelection(BuscadorEnum.getEnderecoVidaById(spnEndereco, envolvidoVida.getId()));

    }

    public void SalvarEnvolvidoVida()
    {
        envolvidoVida.setNome(edtNomeEnvolvido.getText().toString());
        envolvidoVida.setDocumentoTipo(BuscadorEnum.BuscarDocumentoPessoa(spnTipoDocumento.getSelectedItem().toString()));
        envolvidoVida.setDocumentoValor(edtNumDocumento.getText().toString());
        envolvidoVida.setObservacoes(edtObservacoes.getText().toString());
        envolvidoVida.setGenero(BuscadorEnum.BuscarGenero(spnGenero.getSelectedItem().toString()));
        envolvidoVida.setIndiciosTempoMorte(BuscadorEnum.BuscarIndicioTempoMorte(spnIndiciosTempoMorte.getSelectedItem().toString()));
        envolvidoVida.setEnderecoId(((EnderecoVida)spnEndereco.getSelectedItem()).getId());

        envolvidoVida.setVestes(edtVestimentas.getText().toString());
        envolvidoVida.setTipoMorte(BuscadorEnum.BuscarTipoMorte(spnTipoMorte.getSelectedItem().toString()));

        if (!edtAnoNascimento.getText().toString().equals("") &&
                !edtMesNascimento.getText().toString().equals("") &&
                !edtDiaNascimento.getText().toString().equals(""))
        {
            try
            {
                envolvidoVida.setDataNascimentoString(edtDiaNascimento.getText() + "/"
                        + edtMesNascimento.getText() + "/" + edtAnoNascimento.getText().toString());
            } catch (Exception e)
            {
                envolvidoVida.setNascimento(null);
            }

        }
        envolvidoVida.save();

        ocorrenciaEnvolvidoVida.setEnvolvidoVida(envolvidoVida);
        ocorrenciaEnvolvidoVida.setOcorrenciaVida(ocorrenciaVida);

        ocorrenciaEnvolvidoVida.save();

        adapterEnvolvidoVida.notifyDataSetChanged();

//        String path = Environment.getExternalStorageDirectory() +
//                "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome().substring(0, 5)
//                + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() +
//                "/Fotos_Envolvidos/" + envolvidoVida.getId().toString()+"_"+ StringUtil.normalize(envolvidoVida.getNome()) + ".jpeg";;
//
//        File f = new File(path);
//
//        if(!f.exists())
    }

    public void LimparCampos()
    {
        edtObservacoes.setText("");
        edtVestimentas.setText("");
        edtNomeEnvolvido.setText("");
        edtNumDocumento.setText("");

        cxbDesconhecido.setChecked(false);
//        cxbViolencia.setChecked(true);
        spnTipoMorte.setSelection(0);

        spnGenero.setSelection(0);
        spnIndiciosTempoMorte.setSelection(0);
        spnTipoDocumento.setSelection(0);

        edtAnoNascimento.setText("");
        edtMesNascimento.setText("");
        edtDiaNascimento.setText("");

//        txvDataNascimento.setText("00/00/0000");
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
//        txvDataNascimento = null;
        edtAnoNascimento = null;
        edtMesNascimento = null;
        edtDiaNascimento = null;
        spnIndiciosTempoMorte = null;
        spnGenero = null;
        edtVestimentas = null;
        edtObservacoes = null;
        cxbDesconhecido = null;
        btnLesoes = null;
        btnPosicaoCadaver = null;
        fabEnvolvidos = null;
//        cxbViolencia = null;
        spnTipoMorte = null;
        adapterEnvolvidoVida = null;
    }
}
