package Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.NumberPicker;

import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapters.AdapterEndereco;
import Enums.CategoriaFoto;
import Enums.Transito.CondicaoPista;
import Enums.IluminacaoVia;
import Enums.OrientacaoGeograficaComposta;
import Enums.Transito.Pavimentacao;
import Enums.Transito.Semaforo;
import Enums.Transito.SinalizacaoPare;
import Enums.Transito.TipoVia;
import Enums.Transito.Topografia;
import Model.Transito.ColisaoTransito;
import Model.Endereco;
import Model.Transito.EnderecoTransito;
import Model.Foto;
import Model.Gravacao;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoEndereco;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Util.AutoCompleteUtil;
import Util.BuscadorEnum;
import Util.ViewUtil;


public class GerenciarEndereco extends android.support.v4.app.Fragment implements Step, LocationListener
{


    ImageButton imgbCoordenadas = null;
    AdapterEndereco adp = null;
    EnderecoTransito endereco = null;
    Spinner spnTipoVia = null;
    Spinner spnSemaforo = null;
    Spinner spnTopografia = null;
    Spinner spnCondicaoVia = null;
    Spinner spnPavimentacao = null;
    Spinner spnIluminacaoVia = null;
    Spinner spnSinalizacaoPare = null;
    Spinner spnSentido_Direcao = null;
    NumberPicker nmbFaixas = null;
    NumberPicker nmbPistas = null;
    EditText edtEndereco = null;
    EditText edtAngulo = null;
    AutoCompleteTextView aucCidade = null;
    AutoCompleteTextView aucBairro = null;
    ListView listEnderecos = null;
    Button btnSave = null;
    Button btnCancel = null;
    FloatingActionButton fabNovoEndereco = null;
    CheckBox cxbPreferencial = null;
    CheckBox cxbComposta = null;
    CheckBox cxbCurva = null;
    CheckBox cxbHumidade = null;
    CheckBox cxbMaoDupla = null;
    List<OcorrenciaTransitoEndereco> ocorrenciaEnderecos = null;
    ArrayList<EnderecoTransito> enderecoTransitoModel = null;
    OcorrenciaTransito ocorrenciaTransitoEndereco = null;
    OcorrenciaTransitoEndereco ocorrenciaEndereco = null;
    EditText edtLatitude = null;
    EditText edtLongitude = null;
    RelativeLayout rltv_Endereco = null;
    FloatingActionButton fabFotoEndereco = null;
    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;
    private OcorrenciaTransitoFoto ocorrenciaFoto;
    Ocorrencia ocorrencia;
    private String pathImagem;
    private LocationManager locationManager;
    EditText edtLargura;
    private OnFragmentInteractionListener mListener;

    public GerenciarEndereco()
    {

    }


    public static GerenciarEndereco newInstance(String param1, String param2)
    {
        GerenciarEndereco fragment = new GerenciarEndereco();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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

        final View root = inflater.inflate(R.layout.fragment_gerenciar_endereco, container, false);

        View view = getActivity().getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }


        AssociarLayout(root);
        PovoarSpinner(getContext());
        AssociarEventos();

        rltv_Endereco = (RelativeLayout) root.findViewById(R.id.rltv_Detalhe_Endereco);

        ocorrenciaTransitoEndereco = ((ManterPericia) getActivity()).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoEndereco.getOcorrenciaID());

        ocorrenciaEnderecos = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrenciaTransitoEndereco.getId().toString());

        enderecoTransitoModel = new ArrayList<>();

        for (OcorrenciaTransitoEndereco oe : ocorrenciaEnderecos)
        {
            if (oe.getEnderecoTransito() != null)
            {
                enderecoTransitoModel.add(oe.getEnderecoTransito());
            }
        }
        adp = new AdapterEndereco(enderecoTransitoModel, getActivity());


        //   if (enderecoTransitoModel.size() == 0) {
        ViewUtil.modifyAll(rltv_Endereco, false);

        nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.INCREMENT, false);
        //    }

        listEnderecos.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listEnderecos.setAdapter(adp);


        return root;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

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
        if (rltv_Endereco.getChildAt(0).isEnabled())
        {
            try
            {
                SalvarEndereco();
            } catch (Exception e)
            {
                VerificationError ve = new VerificationError(e.getMessage());
                return ve;
            }
        }
        return null;
    }

    @Override
    public void onSelected()
    {
        //((ManterPericia) getActivity()).toolbar.setTitle("Endereços");
        ((ManterPericia) getActivity()).txvToolbarTitulo.setText("Endereços");
    }


    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    @Override
    public void onLocationChanged(Location location)
    {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras)
    {

    }

    @Override
    public void onProviderEnabled(String provider)
    {

    }

    @Override
    public void onProviderDisabled(String provider)
    {

    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        pathImagem = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "imagem_pericia", "Imagens " + ocorrenciaTransitoEndereco.getId().toString(), MagicalCamera.JPEG, true);

        if (pathImagem != null)
        {
            File newFile = new File(pathImagem);

            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoEndereco.getDataPath() + "/" + ocorrenciaTransitoEndereco.getNumIncidencia() + "/Fotos_Enderecos/";

            File folder = new File(newPath);

            if (!folder.exists())
                folder.mkdirs();


            newPath += "foto_endereco" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";

            Foto foto;
            if (newFile.renameTo(new File(newPath)))
                foto = new Foto("Foto do Endereço".toString(), newPath, CategoriaFoto.ENDERECOS);

            else
                foto = new Foto("Foto do Endereço".toString(), pathImagem, CategoriaFoto.ENDERECOS);

            foto.save();
            ocorrenciaFoto = new OcorrenciaTransitoFoto();
            ocorrenciaFoto.setFoto(foto);
            ocorrenciaFoto.setOcorrenciaTransito(ocorrenciaTransitoEndereco);
            ocorrenciaFoto.save();

            if (pathImagem != null)
                Toast.makeText(getActivity(), "Foto salva no caminho: " + newPath, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_SHORT).show();

        }
    }

    private void SalvarEndereco()
    {
        endereco.setTipoVia(BuscadorEnum.BuscarTipoVia(spnTipoVia.getSelectedItem().toString()));
        endereco.setSemaforo(BuscadorEnum.BuscarSemaforizacao(spnSemaforo.getSelectedItem().toString()));
        endereco.setTopografia(BuscadorEnum.BuscarTopografia(spnTopografia.getSelectedItem().toString()));
        endereco.setCondicao(BuscadorEnum.BuscarCondicaoPista(spnCondicaoVia.getSelectedItem().toString()));
        endereco.setIluminacao(BuscadorEnum.BuscarIluminacao(spnIluminacaoVia.getSelectedItem().toString()));
        endereco.setSentidoVia(BuscadorEnum.BuscarOrientacaoComposta(spnSentido_Direcao.getSelectedItem().toString()));
        endereco.setPavimentacao(BuscadorEnum.BuscarPavimentacao(spnPavimentacao.getSelectedItem().toString()));
        endereco.setSinalizacaoPare(BuscadorEnum.BuscarSinalizacaoPare(spnSinalizacaoPare.getSelectedItem().toString()));


        if (endereco.getTopografia() != Topografia.RETA_PLANA)
            try
            {
                endereco.setAngulo(Integer.parseInt(edtAngulo.getText().toString()));
            }catch (Exception e)
            {
                endereco.setAngulo(0);
            }
        else
            endereco.setAngulo(0);

        endereco.setCurva(cxbCurva.isChecked());
        endereco.setMolhada(cxbHumidade.isChecked());
        endereco.setMaoDupla(cxbMaoDupla.isChecked());
        endereco.setComposta(cxbComposta.isChecked());
        endereco.setPreferencial(cxbPreferencial.isChecked());


        if (edtLargura.getText().toString().length() > 0)
        {
            try
            {
                endereco.setLargura(Long.valueOf(edtLargura.getText().toString()));
            } catch (Exception e)
            {
                endereco.setLargura(1l);
            }
        }
        if (cxbComposta.isChecked())
        {
            endereco.setNumFaixas(nmbFaixas.getValue());
            endereco.setNumPistas(nmbPistas.getValue());
        } else
        {
            endereco.setNumFaixas(1);
            endereco.setNumPistas(1);
        }

        endereco.setLatitude(edtLatitude.getText().toString());
        endereco.setLongitude(edtLongitude.getText().toString());

        Endereco end = new Endereco();
        end.setEndereco(edtEndereco.getText().toString());
        end.setCidade(aucCidade.getText().toString());
        end.setBairro(aucBairro.getText().toString());

        end.save();

        endereco.setEndereco(end);

        endereco.save();

        ocorrenciaEndereco.setOcorrencia(ocorrenciaTransitoEndereco);
        ocorrenciaEndereco.setEndereco(endereco);

        ocorrenciaEndereco.save();

        adp.notifyDataSetChanged();

    }

    private void LimparCampos()
    {

        aucCidade.setText("");
        aucBairro.setText("");
        edtAngulo.setText("");
        edtEndereco.setText("");
        edtLatitude.setText("");
        edtLongitude.setText("");
        edtLargura.setText("");

        cxbPreferencial.setChecked(true);

        cxbCurva.setChecked(false);

        cxbHumidade.setChecked(false);

        cxbMaoDupla.setChecked(false);

        cxbComposta.setChecked(false);

        nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.INCREMENT, false);

        spnTipoVia.setSelection(0);

        spnIluminacaoVia.setSelection(0);

        spnPavimentacao.setSelection(0);

        spnCondicaoVia.setSelection(0);

        spnSemaforo.setSelection(0);

        spnTopografia.setSelection(0);

        spnSinalizacaoPare.setSelection(0);

        nmbPistas.setValue(1);

        nmbFaixas.setValue(1);
    }

    private void CarregarEndereco()
    {
        LimparCampos();

        if (endereco.getEndereco() != null)
        {
            aucCidade.setText(endereco.getEndereco().getCidade());
            aucBairro.setText(endereco.getEndereco().getBairro());
            edtEndereco.setText(endereco.getEndereco().getDescricao());
        }
        if (endereco.getLargura() != null)
            edtLargura.setText(endereco.getLargura().toString());

        if (endereco.getAngulo() == 0)
            edtAngulo.setEnabled(false);
        else
        {
            edtAngulo.setEnabled(true);
            edtAngulo.setText(String.valueOf(endereco.getAngulo()));
        }

        cxbPreferencial.setChecked(endereco.isPreferencial());

        cxbCurva.setChecked(endereco.isCurva());

        cxbHumidade.setChecked(endereco.isMolhada());

        cxbComposta.setChecked(endereco.isComposta());

        if (endereco.isComposta())
        {
            nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, true);
            nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, true);
            nmbPistas.setActionEnabled(ActionEnum.DECREMENT, true);
            nmbPistas.setActionEnabled(ActionEnum.INCREMENT, true);
        } else
        {
            nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, false);
            nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, false);
            nmbPistas.setActionEnabled(ActionEnum.DECREMENT, false);
            nmbPistas.setActionEnabled(ActionEnum.INCREMENT, false);
        }

        nmbFaixas.setValue(endereco.getNumFaixas());

        nmbPistas.setValue(endereco.getNumPistas());

        if (endereco.getLargura() != null)
            edtLargura.setText(endereco.getLargura().toString());

        if (endereco.getTipoVia() != null)
            spnTipoVia.setSelection(BuscadorEnum.getIndex(spnTipoVia, endereco.getTipoVia().getValor()));

        if (endereco.getIluminacao() != null)
            spnIluminacaoVia.setSelection(BuscadorEnum.getIndex(spnIluminacaoVia, endereco.getIluminacao().getValor()));

        if (endereco.getPavimentacao() != null)
            spnPavimentacao.setSelection(BuscadorEnum.getIndex(spnPavimentacao, endereco.getPavimentacao().getValor()));

        if (endereco.getCondicao() != null)
            spnCondicaoVia.setSelection(BuscadorEnum.getIndex(spnCondicaoVia, endereco.getCondicao().getValor()));

        if (endereco.getSemaforo() != null)
            spnSemaforo.setSelection(BuscadorEnum.getIndex(spnSemaforo, endereco.getSemaforo().getValor()));

        if (endereco.getTopografia() != null)
            spnTopografia.setSelection(BuscadorEnum.getIndex(spnTopografia, endereco.getTopografia().getValor()));

        if (endereco.getSinalizacaoPare() != null)
            spnSinalizacaoPare.setSelection(BuscadorEnum.getIndex(spnSinalizacaoPare, endereco.getSinalizacaoPare().getValor()));

        //      if(endereco.getSentidoOrigem() != null)
//            spnSentido_Origem.setSelection(BuscadorEnum.getIndex(spnSentido_Origem,endereco.getSentidoOrigem().getValor()));

        if (endereco.getSentidoVia() != null)
            spnSentido_Direcao.setSelection(BuscadorEnum.getIndex(spnSentido_Direcao, endereco.getSentidoVia().getValor()));

        if (endereco.getLongitude() != null)
            edtLongitude.setText(endereco.getLongitude());

        if (endereco.getLatitude() != null)
            edtLatitude.setText(endereco.getLatitude());
    }

    private void AssociarLayout(View v)
    {
        nmbFaixas = (NumberPicker) v.findViewById(R.id.npck_Faixas);
        nmbPistas = (NumberPicker) v.findViewById(R.id.npck_Pistas);

        spnTipoVia = (Spinner) v.findViewById(R.id.spn_TipoVia);
        spnSemaforo = (Spinner) v.findViewById(R.id.spn_Semaforo);
        spnTopografia = (Spinner) v.findViewById(R.id.spn_Topografia);
        spnCondicaoVia = (Spinner) v.findViewById(R.id.spn_CondicaoVia);
        spnIluminacaoVia = (Spinner) v.findViewById(R.id.spn_Iluminacao);
        spnSinalizacaoPare = (Spinner) v.findViewById(R.id.spn_SinalPare);
        spnPavimentacao = (Spinner) v.findViewById(R.id.spn_Pavimentacao);
        spnSentido_Direcao = (Spinner) v.findViewById(R.id.spn_SentidoVia_Direcao);

        cxbCurva = (CheckBox) v.findViewById(R.id.cxb_Tracado);
        cxbHumidade = (CheckBox) v.findViewById(R.id.cxb_Humidade);
        cxbMaoDupla = (CheckBox) v.findViewById(R.id.cxb_MaoDupla);
        cxbComposta = (CheckBox) v.findViewById(R.id.cxb_ViaComposta);
        cxbPreferencial = (CheckBox) v.findViewById(R.id.cxb_Preferencial);


        btnSave = (Button) v.findViewById(R.id.btn_Salvar_Endereco);
        listEnderecos = (ListView) v.findViewById(R.id.lstv_Enderecos);

        edtEndereco = (EditText) v.findViewById(R.id.edt_Endereco);
        edtAngulo = (EditText) v.findViewById(R.id.edt_Topografia_Angulo);

        aucBairro = (AutoCompleteTextView) v.findViewById(R.id.auc_Bairro);
        aucCidade = (AutoCompleteTextView) v.findViewById(R.id.auc_Cidade);

        imgbCoordenadas = (ImageButton) v.findViewById(R.id.imgb_Coordenadas);

        edtLatitude = (EditText) v.findViewById(R.id.edt_latitude);
        edtLongitude = (EditText) v.findViewById(R.id.edt_longitude);
        edtLargura = (EditText) v.findViewById(R.id.edt_Largura_Via);

        btnCancel = (Button) v.findViewById(R.id.btn_Cancelar_Endereco);

        fabNovoEndereco = (FloatingActionButton) v.findViewById(R.id.fab_Endereco);

        fabFotoEndereco = (FloatingActionButton) v.findViewById(R.id.fab_Foto_Endereco);

        nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, false);

        nmbPistas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.INCREMENT, false);

        OcorrenciaTransito ocorrenciaTransito = new OcorrenciaTransito();

        ocorrenciaTransito.setGravacaoConclusao(new Gravacao());

        //3.43.6.67704 S
        //38.32.8.42712 W


    }

    private void AssociarEventos()
    {
        listEnderecos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                listEnderecos.setItemChecked(position, true);
                endereco = enderecoTransitoModel.get(position);

                ViewUtil.modifyAll(rltv_Endereco, true);

                nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, true);
                nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, true);
                nmbPistas.setActionEnabled(ActionEnum.DECREMENT, true);
                nmbPistas.setActionEnabled(ActionEnum.INCREMENT, true);

                CarregarEndereco();
                try
                {
                    ocorrenciaEndereco = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ? and endereco = ?", ocorrenciaTransitoEndereco.getId().toString(), endereco.getId().toString()).get(0);
                } catch (Exception e)
                {
                    ocorrenciaEndereco = new OcorrenciaTransitoEndereco();
                }
            }
        });

        listEnderecos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
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

                builder.setTitle("Deletar Endereço")
                        .setMessage("Você deseja deletar este Endereço?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {

                                OcorrenciaTransitoEndereco ocorrenciaEndereco = ocorrenciaEnderecos.get(position);

                                if (ColisaoTransito.find(ColisaoTransito.class, "enderecoveiculo1 = ?", ocorrenciaEndereco.getEnderecoTransito().getId().toString()).size() > 0
                                        || ColisaoTransito.find(ColisaoTransito.class, "enderecoveiculo2 = ?", ocorrenciaEndereco.getEnderecoTransito().getId().toString()).size() > 0)
                                {
                                    Toast.makeText(getContext(), "Não foi possível excluir! O endereço está atrelado à uma Colisão!", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    return;
                                }


                                adp.remove(ocorrenciaEndereco.getEnderecoTransito());

                                ocorrenciaEndereco.getEnderecoTransito().delete();

                                ocorrenciaEndereco.delete();

                                ocorrenciaEnderecos.remove(position);

                                LimparCampos();

                                if (listEnderecos.getAdapter().getCount() == 0)
                                {
                                    ViewUtil.modifyAll(rltv_Endereco, false);

                                    nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, false);
                                    nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, false);
                                    nmbPistas.setActionEnabled(ActionEnum.DECREMENT, false);
                                    nmbPistas.setActionEnabled(ActionEnum.INCREMENT, false);

                                    Toast.makeText(getActivity(), "Endereço Deletado com sucesso!", Toast.LENGTH_LONG).show();
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


        fabNovoEndereco.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                endereco = new EnderecoTransito();
                endereco.setEndereco(new Endereco());

                ocorrenciaEndereco = new OcorrenciaTransitoEndereco();

                endereco.save();

                ocorrenciaEndereco.setOcorrencia(ocorrenciaTransitoEndereco);
                ocorrenciaEndereco.setEndereco(endereco);
                ocorrenciaEndereco.save();
                ocorrenciaEnderecos.add(ocorrenciaEndereco);

                adp.add(endereco);
                adp.notifyDataSetChanged();

                //quando eu tento isso aqui, ele dá tipo um clear na seleção (caso eu tenha clicado em outro item), e quando dou scroll ele exibe a seleção que estava.
                //lstvEnvolvidos.setSelection(adp.getCount()-1);

                //View item = getViewByPosition(adp.getCount()-1,lstvEnvolvidos);
                //item.setBackgroundColor(Color.GRAY);

                Toast.makeText(getContext(), "adp: " + adp.getCount() + " lstvVeiculos: " + listEnderecos.getCount(), Toast.LENGTH_LONG).show();

                ViewUtil.modifyAll(rltv_Endereco, true);

                nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, true);
                nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, true);
                nmbPistas.setActionEnabled(ActionEnum.DECREMENT, true);
                nmbPistas.setActionEnabled(ActionEnum.INCREMENT, true);

                listEnderecos.performItemClick(listEnderecos, BuscadorEnum.PegarPosicaoEndereco(enderecoTransitoModel, endereco), listEnderecos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEndereco(enderecoTransitoModel, endereco)));
                LimparCampos();
            }
        });

        fabFotoEndereco.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String[] permissions = new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                magicalPermissions = new MagicalPermissions(GerenciarEndereco.this, permissions);
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

                SalvarEndereco();

                Toast.makeText(v.getContext(), "Endereço salvo com Sucesso!", Toast.LENGTH_LONG).show();
            }
        });


        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                CarregarEndereco();
                Toast.makeText(v.getContext(), "Operação desfeita!", Toast.LENGTH_LONG).show();
            }
        });


        cxbComposta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!cxbComposta.isChecked())
                {
                        nmbFaixas.setValue(1);
                        nmbPistas.setValue(1);
                }

                nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, cxbComposta.isChecked());
                nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, cxbComposta.isChecked());

                nmbPistas.setActionEnabled(ActionEnum.DECREMENT, cxbComposta.isChecked());
                nmbPistas.setActionEnabled(ActionEnum.INCREMENT, cxbComposta.isChecked());
            }
        });


        imgbCoordenadas.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                Location location = getLocation();

                if (location != null)
                    converterCoordenadas(location.getLatitude(), location.getLongitude(), edtLatitude, edtLongitude);
                else
                    Toast.makeText(getContext(), "Deu errado!", Toast.LENGTH_LONG).show();
            }
        });


    }

    private void PovoarSpinner(Context ctx)
    {
        aucCidade.setAdapter(AutoCompleteUtil.getCidades(ctx));
        aucBairro.setAdapter(AutoCompleteUtil.getBairros(ctx));

        ArrayList<String> tipoViaAdapter = new ArrayList<String>();

        for (TipoVia tv : TipoVia.values())
        {
            tipoViaAdapter.add(tv.getValor());
        }
        spnTipoVia.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, tipoViaAdapter));

        ArrayList<String> semaforoAdapter = new ArrayList<String>();

        for (Semaforo sem : Semaforo.values())
        {
            semaforoAdapter.add(sem.getValor());
        }
        spnSemaforo.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, semaforoAdapter));

        ArrayList<String> topografiaAdapter = new ArrayList<String>();

        for (Topografia tp : Topografia.values())
        {
            topografiaAdapter.add(tp.getValor());
        }
        spnTopografia.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, topografiaAdapter));

        ArrayList<String> condicaoViaAdapter = new ArrayList<String>();

        for (CondicaoPista cpis : CondicaoPista.values())
        {
            condicaoViaAdapter.add(cpis.getValor());
        }
        spnCondicaoVia.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, condicaoViaAdapter));


        ArrayList<String> pavimentacaoAdapter = new ArrayList<String>();
        for (Pavimentacao pav : Pavimentacao.values())
        {
            pavimentacaoAdapter.add(pav.getValor());
        }
        spnPavimentacao.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, pavimentacaoAdapter));

        ArrayList<String> iluminacaoAdapter = new ArrayList<String>();

        for (IluminacaoVia ilu : IluminacaoVia.values())
        {
            iluminacaoAdapter.add(ilu.getValor());
        }
        spnIluminacaoVia.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, iluminacaoAdapter));

        ArrayList<String> sinalPareAdapter = new ArrayList<String>();

        for (SinalizacaoPare spa : SinalizacaoPare.values())
        {
            sinalPareAdapter.add(spa.getValor());
        }
        spnSinalizacaoPare.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, sinalPareAdapter));
//
//        ArrayList<String> orientacaoGeografica = new ArrayList<>();
//
//        for(OrientacaoGeografica og : OrientacaoGeografica.values())
//        {
//            orientacaoGeografica.add(og.getValor());
//        }

        ArrayList<String> orientacaoGeograficaComposta = new ArrayList<>();

        for (OrientacaoGeograficaComposta og : OrientacaoGeograficaComposta.values())
        {
            orientacaoGeograficaComposta.add(og.getValor());
        }

        spnSentido_Direcao.setAdapter(new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_dropdown_item, orientacaoGeograficaComposta));


        spnTopografia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if (spnTopografia.getSelectedItem().equals("Aclive") || spnTopografia.getSelectedItem().equals("Declive"))
                    edtAngulo.setEnabled(true);

                else
                {
                    edtAngulo.setText("");
                    edtAngulo.setEnabled(false);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }

        });

//       spnSentido_Origem.setAdapter(new ArrayAdapter<String>(ctx,android.R.layout.simple_spinner_dropdown_item,orientacaoGeografica));


    }

    private Location getLocation()
    {
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // provider = locationManager.getBestProvider(criteria, false);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {


        } else
        {

        }

        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;

        for (String provider : providers)
        {
            Location l = locationManager.getLastKnownLocation(provider);


            if (l == null)
            {
                continue;
            }
            if (bestLocation == null
                    || l.getAccuracy() < bestLocation.getAccuracy())
            {
                bestLocation = l;
            }
        }
        if (bestLocation == null)
        {
            return null;
        }

        return bestLocation;
    }

    private void converterCoordenadas(double latitude, double longitude, EditText edtLatitude, EditText edtLongitude)
    {
        StringBuilder builderLatitude = new StringBuilder();


        String latitudeDegrees = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS);
        String[] latitudeSplit = latitudeDegrees.split(":");
        builderLatitude.append(latitudeSplit[0]);
        builderLatitude.append("°");
        builderLatitude.append(latitudeSplit[1]);
        builderLatitude.append("'");
        builderLatitude.append(latitudeSplit[2]);
        builderLatitude.append("\"");

        if (latitude < 0)
        {
            builderLatitude.append("S ");
        } else
        {
            builderLatitude.append("N ");
        }

        edtLatitude.setText(builderLatitude.toString());


        StringBuilder builderLongitude = new StringBuilder();

        String longitudeDegrees = Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS);
        String[] longitudeSplit = longitudeDegrees.split(":");
        builderLongitude.append(longitudeSplit[0]);
        builderLongitude.append("°");
        builderLongitude.append(longitudeSplit[1]);
        builderLongitude.append("'");
        builderLongitude.append(longitudeSplit[2]);
        builderLongitude.append("\"");

        if (longitude < 0)
        {
            builderLongitude.append("W ");
        } else
        {
            builderLongitude.append("E ");
        }

        edtLongitude.setText(builderLongitude.toString());
    }

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
            ot = ((ManterPericia) activity).ocorrenciaTransito;

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
                    magicalCamera.takeFragmentPhoto(GerenciarEndereco.this);
                    dialog.dismiss();
                }
            });

            imgvGaleria.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    magicalCamera.selectFragmentPicture(GerenciarEndereco.this, "Selecione Uma Imagem");
                    dialog.dismiss();
                }
            });


        }


    }
}
