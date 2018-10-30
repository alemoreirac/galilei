package Fragments.FragmentsTransito;

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
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.pefoce.peritolocal.ManterPericiaTransito;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.travijuu.numberpicker.library.Enums.ActionEnum;
import com.travijuu.numberpicker.library.NumberPicker;

import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Adapters.AdapterEnderecoTransito;
import Control.BairroBusiness;
import Control.MunicipioBusiness;
import Enums.CategoriaFoto;
import Enums.Transito.CondicaoPista;
import Enums.IluminacaoVia;
import Enums.OrientacaoGeograficaComposta;
import Enums.Transito.Pavimentacao;
import Enums.Transito.Semaforo;
import Enums.Transito.SinalizacaoPare;
import Enums.Transito.TipoVia;
import Enums.Transito.Topografia;
import Model.Bairro;
import Model.Municipio;
import Model.Transito.ColisaoTransito;
import Model.Transito.EnderecoTransito;
import Model.Foto;
import Model.Gravacao;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoEndereco;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Util.AutoCompleteUtil;
import Util.BuscadorEnum;
import Util.SingleShotLocationProvider;
import Util.StringUtil;
import Util.ViewUtil;
import br.com.sapereaude.maskedEditText.MaskedEditText;
//import okhttp3.internal.Util;

import static Util.StringUtil.isNotNullAndEmpty;


public class GerenciarEnderecoTransito extends android.support.v4.app.Fragment implements Step, LocationListener
{
    int REQUEST_CODE = 1;
    View mView;
    int lastPosition;
    ImageButton imgbCoordenadas = null;
    AdapterEnderecoTransito adapterEnderecoTransito = null;
    EnderecoTransito endereco = null;
    Spinner spnTipoVia = null;
    Spinner spnSemaforo = null;
    Spinner spnTopografia = null;
    Spinner spnCondicaoVia = null;
    Spinner spnPavimentacao = null;
    Spinner spnIluminacaoVia = null;
    Spinner spnSinalizacaoPare = null;
    Spinner spnSentido_Direcao = null;
    ProgressBar pbCoordenadas = null;
    NumberPicker nmbFaixas = null;
    NumberPicker nmbPistas = null;
    EditText edtEndereco = null;
    EditText edtComplemento = null;
    EditText edtAngulo = null;
    AutoCompleteTextView aucCidade = null;
    AutoCompleteTextView aucBairro = null;
    ListView listEnderecos = null;
    FloatingActionButton fabNovoEndereco = null;
    CheckBox cxbPreferencial = null;
    CheckBox cxbComposta = null;
    CheckBox cxbCurva = null;
    CheckBox cxbHumidade = null;
    CheckBox cxbMeioFio = null;
    CheckBox cxbMaoDupla = null;
    List<OcorrenciaTransitoEndereco> ocorrenciaEnderecos = null;
    ArrayList<EnderecoTransito> enderecoTransitoModel = null;
    OcorrenciaTransito ocorrenciaTransitoEndereco = null;
    OcorrenciaTransitoEndereco ocorrenciaEndereco = null;
    MaskedEditText edtLatitude = null;
    MaskedEditText edtLongitude = null;
    RelativeLayout rltv_Endereco = null;
    FloatingActionButton fabFotoEndereco = null;
    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;
    private OcorrenciaTransitoFoto ocorrenciaFoto;
    Ocorrencia ocorrencia;
    private String pathImagem;
    EditText edtLargura;
    Bairro Bairro;
    Municipio Municipio;
    boolean checking;

    public GerenciarEnderecoTransito()
    {

    }

    public static GerenciarEnderecoTransito newInstance(String param1, String param2)
    {
        GerenciarEnderecoTransito fragment = new GerenciarEnderecoTransito();
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

        final View root = inflater.inflate(R.layout.fragment_gerenciar_endereco_transito, container, false);
        mView = root;
        return root;
    }
    @Override
    public void onPause()
    {
        super.onPause();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
    }

    @Override
    public void onStop()
    {
        super.onStop();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
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
        lastPosition = -1;

        View view = getActivity().getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        Municipio = new Municipio();
        Bairro = new Bairro();

        AssociarLayout(mView);
        PovoarSpinner(getContext());
        AssociarEventos();

        rltv_Endereco = (RelativeLayout) mView.findViewById(R.id.rltv_Detalhe_Endereco);

        ocorrenciaTransitoEndereco = ((ManterPericiaTransito) getActivity()).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoEndereco.getOcorrencia());

        ocorrenciaEnderecos = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class, "ocorrencia_transito = ?", ocorrenciaTransitoEndereco.getId().toString());

        enderecoTransitoModel = new ArrayList<>();

        for (OcorrenciaTransitoEndereco oe : ocorrenciaEnderecos)
        {
            if (oe.getEnderecoTransito() != null)
                enderecoTransitoModel.add(oe.getEnderecoTransito());
        }

        adapterEnderecoTransito = new AdapterEnderecoTransito(enderecoTransitoModel, getActivity());

        ViewUtil.modifyAll(rltv_Endereco, false);

        if (enderecoTransitoModel.size() > 0)
        {
            if (enderecoTransitoModel.get(0).getBairro() != null &&
                    isNotNullAndEmpty(enderecoTransitoModel.get(0).getBairro().getDescricao()))
                Bairro = enderecoTransitoModel.get(0).getBairro();

            if (enderecoTransitoModel.get(0).getMunicipio() != null &&
                    isNotNullAndEmpty(enderecoTransitoModel.get(0).getMunicipio().getDescricao()))
                Municipio = enderecoTransitoModel.get(0).getMunicipio();
        }

        nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.INCREMENT, false);

        listEnderecos.setAdapter(adapterEnderecoTransito);
        adapterEnderecoTransito.notifyDataSetChanged();

        ((ManterPericiaTransito) getActivity()).txvToolbarTitulo.setText("Endereços");
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
     * to the context and potentially other fragments contained in that
     * context.
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
                endereco.setAnguloInclinacao(Integer.parseInt(edtAngulo.getText().toString()));
            } catch (Exception e)
            {
                endereco.setAnguloInclinacao(0);
            }
        else
            endereco.setAnguloInclinacao(0);

        endereco.setCurva(cxbCurva.isChecked());
        endereco.setMolhada(cxbHumidade.isChecked());
        endereco.setMaoDupla(cxbMaoDupla.isChecked());
        endereco.setMeioFio(cxbMeioFio.isChecked());
        endereco.setComposta(cxbComposta.isChecked());
        endereco.setPreferencial(cxbPreferencial.isChecked());


        if (edtLargura.getText().toString().length() > 0)
        {
            try
            {
                endereco.setLargura(Float.valueOf(edtLargura.getText().toString()));
            } catch (Exception e)
            {
                endereco.setLargura(1f);
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

        endereco.setDescricaoEndereco(edtEndereco.getText().toString());
        endereco.setComplemento(edtComplemento.getText().toString());

        try
        {
            endereco.setMunicipio(Municipio.find(Municipio.class, "descricao = ?", aucCidade.getText().toString()).get(0));
        } catch (Exception e)
        {
            e.toString();
        }
        try
        {
            endereco.setBairro(Bairro.find(Bairro.class, "descricao = ?", aucBairro.getText().toString()).get(0));
        } catch (Exception e)
        {
            e.toString();
        }

//        endereco.setMunicipio(aucCidade.getText().toString());
//        endereco.setBairro(aucBairro.getText().toString());

        endereco.save();

        ocorrenciaEndereco.setOcorrencia(ocorrenciaTransitoEndereco);
        ocorrenciaEndereco.setEndereco(endereco);

        ocorrenciaEndereco.save();

        adapterEnderecoTransito.notifyDataSetChanged();
    }

    private void LimparCampos()
    {
        edtLargura.setText("");
        aucCidade.setText("");
        aucBairro.setText("");
        edtAngulo.setText("");
        edtEndereco.setText("");
        edtComplemento.setText("");
        edtLatitude.setText("");
        edtLongitude.setText("");

        cxbPreferencial.setChecked(true);

        cxbCurva.setChecked(false);

        cxbHumidade.setChecked(false);

        cxbMeioFio.setChecked(false);

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

        if (endereco.getMunicipio() != null && endereco.getMunicipio().getDescricao() != null)
            aucCidade.setText(endereco.getMunicipio().getDescricao());

        if (endereco.getBairro() != null && endereco.getBairro().getDescricao() != null)
            aucBairro.setText(endereco.getBairro().getDescricao());

        if (endereco.getDescricaoEndereco() != null)
            edtEndereco.setText(endereco.getDescricaoEndereco());

        if (endereco.getLargura() != null)
            edtLargura.setText(endereco.getLargura().toString());

        if (endereco.getAnguloInclinacao() == 0)
            edtAngulo.setEnabled(false);

        else
        {
            edtAngulo.setEnabled(true);
            edtAngulo.setText(String.valueOf(endereco.getAnguloInclinacao()));
        }

        if (endereco.getComplemento() != null)
            edtComplemento.setText(endereco.getComplemento());

        cxbPreferencial.setChecked(endereco.isPreferencial());

        cxbCurva.setChecked(endereco.isCurva());

        cxbHumidade.setChecked(endereco.isMolhada());

        cxbMeioFio.setChecked(endereco.getMeioFio());

        cxbComposta.setChecked(endereco.isComposta());

        cxbMaoDupla.setChecked(endereco.isMaoDupla());

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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        if (requestCode == REQUEST_CODE)
        {
            if (grantResults.length == 0)
            {
                imgbCoordenadas.setVisibility(View.VISIBLE);
                pbCoordenadas.setVisibility(View.INVISIBLE);
                edtLatitude.setEnabled(true);
                edtLongitude.setEnabled(true);
                return;
            }
            for (int i = 0; i < grantResults.length; i++)
            {
                if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                {
                    imgbCoordenadas.setVisibility(View.VISIBLE);
                    pbCoordenadas.setVisibility(View.INVISIBLE);
                    edtLatitude.setEnabled(true);
                    edtLongitude.setEnabled(true);
                    checking = false;
                    return;
                }
            }

            checking = true;

            pbCoordenadas.setVisibility(View.VISIBLE);
            imgbCoordenadas.setVisibility(View.INVISIBLE);
            edtLatitude.setEnabled(false);
            edtLongitude.setEnabled(false);

            SingleShotLocationProvider.requestSingleUpdate(getActivity(),
                    new SingleShotLocationProvider.LocationCallback()
                    {
                        @Override
                        public void onNewLocationAvailable(Double lat, Double lng)
                        {
                            edtLatitude.setText(StringUtil.converterLatitude(lat));
                            edtLongitude.setText(StringUtil.converterLongitude(lng));

                            imgbCoordenadas.setVisibility(View.VISIBLE);
                            pbCoordenadas.setVisibility(View.INVISIBLE);
                            edtLatitude.setEnabled(true);
                            edtLongitude.setEnabled(true);
                            checking = false;
                        }
                    }
            );

            Looper myLooper = Looper.myLooper();

            final Handler myHandler = new Handler(myLooper);
            myHandler.postDelayed(new Runnable()
            {
                public void run()
                {
                    if (checking)
                    {
                        SingleShotLocationProvider.cancelUpdate();
                        Toast.makeText(getContext(), "Tempo limite excedido!", Toast.LENGTH_LONG).show();

                        imgbCoordenadas.setVisibility(View.VISIBLE);
                        pbCoordenadas.setVisibility(View.INVISIBLE);
                        edtLatitude.setEnabled(true);
                        edtLongitude.setEnabled(true);
                    }
                }
            }, 1000 * 120);


        }
    }

    private void AssociarLayout(View view)
    {
        if(view ==null)
            return;

        nmbFaixas = (NumberPicker) view.findViewById(R.id.npck_Faixas);
        nmbPistas = (NumberPicker) view.findViewById(R.id.npck_Pistas);

        spnTipoVia = (Spinner) view.findViewById(R.id.spn_TipoVia);
        spnSemaforo = (Spinner) view.findViewById(R.id.spn_Semaforo);
        spnTopografia = (Spinner) view.findViewById(R.id.spn_Topografia);
        spnCondicaoVia = (Spinner) view.findViewById(R.id.spn_CondicaoVia);
        spnIluminacaoVia = (Spinner) view.findViewById(R.id.spn_Iluminacao);
        spnSinalizacaoPare = (Spinner) view.findViewById(R.id.spn_SinalPare);
        spnPavimentacao = (Spinner) view.findViewById(R.id.spn_Pavimentacao);
        spnSentido_Direcao = (Spinner) view.findViewById(R.id.spn_SentidoVia_Direcao);

        cxbCurva = (CheckBox) view.findViewById(R.id.cxb_Tracado);
        cxbHumidade = (CheckBox) view.findViewById(R.id.cxb_Umidade);
        cxbMaoDupla = (CheckBox) view.findViewById(R.id.cxb_MaoDupla);
        cxbMeioFio = (CheckBox) view.findViewById(R.id.cxb_MeioFio);
        cxbComposta = (CheckBox) view.findViewById(R.id.cxb_ViaComposta);
        cxbPreferencial = (CheckBox) view.findViewById(R.id.cxb_Preferencial);

        pbCoordenadas = (ProgressBar) view.findViewById(R.id.pgb_Carregar_Coordenadas_Transito);

//        btnSave = (Button) v.findViewById(R.id.btn_Salvar_Endereco);
//        btnCancel = (Button) v.findViewById(R.id.btn_Cancelar_Endereco);

        listEnderecos = (ListView) view.findViewById(R.id.lstv_Enderecos);

        edtEndereco = (EditText) view.findViewById(R.id.edt_Endereco);
        edtComplemento = (EditText) view.findViewById(R.id.edt_Complemento_End_Transito);
        edtAngulo = (EditText) view.findViewById(R.id.edt_Topografia_Angulo);

        aucBairro = (AutoCompleteTextView) view.findViewById(R.id.auc_Bairro);
        aucCidade = (AutoCompleteTextView) view.findViewById(R.id.auc_Cidade);

        imgbCoordenadas = (ImageButton) view.findViewById(R.id.imgb_Coordenadas_Transito);

        edtLatitude = (MaskedEditText) view.findViewById(R.id.medt_Latitude_Transito);
        edtLongitude = (MaskedEditText) view.findViewById(R.id.medt_Longitude_Transito);
        edtLargura = (EditText) view.findViewById(R.id.edt_Largura_Via);

        fabNovoEndereco = (FloatingActionButton) view.findViewById(R.id.fab_Endereco);

        fabFotoEndereco = (FloatingActionButton) view.findViewById(R.id.fab_Foto_Endereco);

        nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, false);

        nmbPistas.setActionEnabled(ActionEnum.DECREMENT, false);
        nmbPistas.setActionEnabled(ActionEnum.INCREMENT, false);

        OcorrenciaTransito ocorrenciaTransito = new OcorrenciaTransito();

        ocorrenciaTransito.setGravacaoConclusao(new Gravacao());

        edtEndereco.setNextFocusForwardId(edtComplemento.getId());
        edtComplemento.setNextFocusForwardId(aucCidade.getId());
        aucCidade.setNextFocusForwardId(aucBairro.getId());
        edtLatitude.setNextFocusForwardId(edtLongitude.getId());
//        edtLongitude.setNextFocusForwardId(edtLargura.getId());
    }

    private void AssociarEventos()
    {
        listEnderecos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id)
            {
                if (lastPosition != -1 && lastPosition != position)

                    SalvarEndereco();

                lastPosition = position;

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

                                adapterEnderecoTransito.remove(ocorrenciaEndereco.getEnderecoTransito());

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
                if (endereco != null)
                    SalvarEndereco();

                endereco = new EnderecoTransito();
                ocorrenciaEndereco = new OcorrenciaTransitoEndereco();

                endereco.save();

                ocorrenciaEndereco.setEndereco(endereco);
                ocorrenciaEndereco.setOcorrencia(ocorrenciaTransitoEndereco);

                ocorrenciaEndereco.save();

                enderecoTransitoModel.add(endereco);

                adapterEnderecoTransito.notifyDataSetChanged();

                LimparCampos();

                ViewUtil.modifyAll(rltv_Endereco, true);

                nmbFaixas.setActionEnabled(ActionEnum.DECREMENT, true);
                nmbFaixas.setActionEnabled(ActionEnum.INCREMENT, true);
                nmbPistas.setActionEnabled(ActionEnum.DECREMENT, true);
                nmbPistas.setActionEnabled(ActionEnum.INCREMENT, true);

                listEnderecos.performItemClick(listEnderecos, BuscadorEnum.PegarPosicaoEnderecoTransito(enderecoTransitoModel, endereco), listEnderecos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoEnderecoTransito(enderecoTransitoModel, endereco)));

//                int index = enderecoTransitoModel.indexOf(endereco);
//
//                listEnderecos.performItemClick(listEnderecos, index, listEnderecos.getItemIdAtPosition(index));

                //Ao criar um novo endereço, preenche automáticamente o bairro e cidade de acordo com o primeiro endereço cadastrado

                if (isNotNullAndEmpty(Bairro.getDescricao()))
                {
//                    endereco.setBairro(Bairro);
                    aucBairro.setText(Bairro.getDescricao());
                }
                if (isNotNullAndEmpty(Municipio.getDescricao()))
                {
                    aucCidade.setText(Municipio.getDescricao());
//                    endereco.setMunicipio(Municipio);
                }
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
                magicalPermissions = new MagicalPermissions(GerenciarEnderecoTransito.this, permissions);

                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                TipoFotoDialog tfd = new TipoFotoDialog();
            }
        });

        cxbComposta.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (!cxbComposta.isChecked())
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
                requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);


            }
        });
    }

    private void PovoarSpinner(Context ctx)
    {
        aucCidade.setAdapter(AutoCompleteUtil.getCidades(ctx));
        aucBairro.setAdapter(AutoCompleteUtil.getBairros(ctx));


        aucCidade.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    if (MunicipioBusiness.findByDescricao(aucCidade.getText().toString()) == null)
                    {
                        aucCidade.setText("");
                        aucCidade.setHint("Inválido");
                    }
                }
            }
        });

        aucBairro.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            public void onFocusChange(View v, boolean hasFocus)
            {
                if (!hasFocus)
                {
                    if (BairroBusiness.findByDescricao(aucBairro.getText().toString()) == null)
                    {
                        aucBairro.setText("");
                        aucBairro.setHint("Inválido");
                    }
                }
            }
        });

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

        spnSemaforo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id)
            {
                if (spnSemaforo.getSelectedItem().equals("Ativo"))
                {
                    spnSinalizacaoPare.setSelection(BuscadorEnum.getIndex(spnSinalizacaoPare, "Ausente"));
                    spnSinalizacaoPare.setEnabled(false);
                } else

                {   //Quando o usuário acessa o fragment de endereco transito, o on item selected dispara após o disable da interface, deixando este único spinner habilitado
                    if (!rltv_Endereco.isEnabled())
                    {
                        spnSinalizacaoPare.setSelection(0);
                        spnSinalizacaoPare.setEnabled(true);
                    }
                }

                spnSinalizacaoPare.setEnabled(false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView)
            {
                // your code here
            }

        });


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
                    magicalCamera.takeFragmentPhoto(GerenciarEnderecoTransito.this);
                    dialog.dismiss();
                }
            });

            imgvGaleria.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    magicalCamera.selectFragmentPicture(GerenciarEnderecoTransito.this, "Selecione Uma Imagem");
                    dialog.dismiss();
                }
            });


        }


    }
}
