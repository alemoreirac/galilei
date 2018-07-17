package Fragments.FragmentsVida;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericiaVida;
import com.example.pefoce.peritolocal.R;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Dialogs.AudioDialog;
import Dialogs.MapDialog;
import Dialogs.TipoFotoDialog;
import Enums.CategoriaFoto;
import Enums.Comodo;
import Enums.IluminacaoArtificial;
import Enums.LocalPraia;
import Enums.LocalVeiculo;
import Enums.Meteorologia;
import Enums.TipoLocal;
import Enums.TipoLocalCrime;
import Enums.TipoVegetacao;
import Enums.Transito.LocalObjeto;
import Enums.Transito.Pavimentacao;
import Enums.Transito.TipoCNH;
import Enums.Transito.TipoVia;
import Enums.Vida.TipoAberturaLocal;
import Model.Foto;
import Model.Ocorrencia;
import Model.Vida.EnderecoVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Util.AutoCompleteUtil;
import Util.BuscadorEnum;
import Util.SingleShotLocationProvider;
import Util.StringUtil;
import Util.ViewUtil;
import br.com.sapereaude.maskedEditText.MaskedEditText;
import info.hoang8f.android.segmented.SegmentedGroup;
import io.fabric.sdk.android.services.common.SafeToast;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GerenciarEnderecoVida.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GerenciarEnderecoVida#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GerenciarEnderecoVida extends android.support.v4.app.Fragment implements Step
{
    AudioDialog dialogFragment = null;
    LocationManager locationManager;
    Spinner spnTipoVia;

    Bundle bd;

    AutoCompleteTextView aucCidade;
    AutoCompleteTextView aucBairro;

    EditText edtEndereco;
    EditText edtComplemento;
    MaskedEditText edtLatitude;
    MaskedEditText edtLongitude;

    ImageButton imgbCoordenadas;
    ImageButton imgbCamera;
    ImageButton imgbAudio;

    SegmentedGroup sgmtLocalVida;
    RadioButton rbtnPraia;
    RadioButton rbtnRural;
    RadioButton rbtnViaPublica;
    RadioButton rbtnResidencial;
    RadioButton rbtnOutro;

    RelativeLayout rltvRural;
    Spinner spnVegetacaoRural;
    Spinner spnAbertoRural;
    EditText edtObservacoesRural;

    RelativeLayout rltvPraia;
    Spinner spnLocalPraia;
    Spinner spnVegetacaoPraia;
    EditText edtObservacoesPraia;

    RelativeLayout rltvResidencia;
    Spinner spnEspacoResidencia;
    Spinner spnLocalidadeResidencia;
    Spinner spnComodo;
    EditText edtObservacoesResidencia;

    RelativeLayout rltvViaPublica;
    Spinner spnPosicaoViaPublica;
    Spinner spnPavimentacaoViaPublica;
    EditText edtObservacoesViaPublica;

    RelativeLayout rltvOutro;
    EditText edtDescricaoOutro;

    RelativeLayout rltvClima;
    Spinner spnClima;
    Spinner spnIluminacao;

    Ocorrencia ocorrencia;
    OcorrenciaVida ocorrenciaVida;
    EnderecoVida enderecoVida;

    LinearLayout llVeiculo;
    Fragment fragment;

    FusedLocationProviderClient mFusedLocationClient;

    ProgressBar pbCoordenadas;

    View mView;

    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;
    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;

    private OnFragmentInteractionListener mListener;

    public GerenciarEnderecoVida()
    {
        // Required empty public constructor
    }


    public void SalvarEndereco()
    {
        enderecoVida.AnularCampos();

        enderecoVida.setDescricaoEndereco(edtEndereco.getText().toString());
        enderecoVida.setCidade(aucCidade.getText().toString());
        enderecoVida.setBairro(aucBairro.getText().toString());
        enderecoVida.setComplemento(edtComplemento.getText().toString());

        enderecoVida.setTipoVia(BuscadorEnum.BuscarTipoVia(spnTipoVia.getSelectedItem().toString()));

        enderecoVida.setOcorrenciaId(ocorrenciaVida.getId());

        enderecoVida.setLatitude(edtLatitude.getText().toString());
        enderecoVida.setLongitude(edtLongitude.getText().toString());

        //enderecoVida.setTipoLocalCrime(BuscadorEnum.BuscarTipoLocalCrime(spnTipoLocal.getSelectedItem().toString()));
        if (rbtnViaPublica.isChecked())
            enderecoVida.setTipoLocalCrime(TipoLocalCrime.VIA_PUBLICA);

        if (rbtnPraia.isChecked())
            enderecoVida.setTipoLocalCrime(TipoLocalCrime.PRAIA);

        if (rbtnOutro.isChecked())
            enderecoVida.setTipoLocalCrime(TipoLocalCrime.OUTRO);

        if (rbtnRural.isChecked())
            enderecoVida.setTipoLocalCrime(TipoLocalCrime.RURAL);

        if (rbtnResidencial.isChecked())
            enderecoVida.setTipoLocalCrime(TipoLocalCrime.RESIDENCIAL);

        if (rltvClima.getVisibility() == View.VISIBLE)
        {
            enderecoVida.setCondicoesClimaticas(BuscadorEnum.BuscarMeteorologia(spnClima.getSelectedItem().toString()));
            enderecoVida.setTipoIluminacao(BuscadorEnum.BuscarIluminacaoArtificial(spnIluminacao.getSelectedItem().toString()));
        }

        switch (enderecoVida.getTipoLocalCrime())
        {
            case PRAIA:
                enderecoVida.setLocalPraia(BuscadorEnum.BuscarLocaisPraia(spnLocalPraia.getSelectedItem().toString()));
                enderecoVida.setObservacao(edtObservacoesPraia.getText().toString());
                break;
            case RURAL:
                enderecoVida.setTipoVegetacao(BuscadorEnum.BuscarVegetacao(spnVegetacaoPraia.getSelectedItem().toString()));
                enderecoVida.setLocalAberto(BuscadorEnum.BuscarLocalAberto(spnAbertoRural.getSelectedItem().toString()));
                break;
            case RESIDENCIAL:
                enderecoVida.setLocalAberto(BuscadorEnum.BuscarLocalAberto(spnEspacoResidencia.getSelectedItem().toString()));
                enderecoVida.setLocalResidencia(BuscadorEnum.BuscarTipoLocal(spnLocalidadeResidencia.getSelectedItem().toString()));
                enderecoVida.setObservacao(edtObservacoesResidencia.getText().toString());
                enderecoVida.setComodo(BuscadorEnum.BuscarComodo(spnComodo.getSelectedItem().toString()));
                break;
            case VIA_PUBLICA:
                enderecoVida.setPosicaoVia(BuscadorEnum.BuscarLocalObjeto(spnPosicaoViaPublica.getSelectedItem().toString()));
                enderecoVida.setPavimentacao(BuscadorEnum.BuscarPavimentacao(spnPavimentacaoViaPublica.getSelectedItem().toString()));
                enderecoVida.setObservacao(edtObservacoesViaPublica.getText().toString());
                break;
            case OUTRO:
                enderecoVida.setObservacao(edtDescricaoOutro.getText().toString());
                break;
            default:
                break;
        }

        enderecoVida.save();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        bd = savedInstanceState;
        mView = view;

        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static GerenciarEnderecoVida newInstance(String param1, String param2)
    {
        GerenciarEnderecoVida fragment = new GerenciarEnderecoVida();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gerenciar_endereco_vida, container, false);
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
        SalvarEndereco();
        return null;
    }

    @Override
    public void onSelected()
    {
        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Local do Crime");

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getActivity());

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Bundle bd = getArguments();

        fragment = this;

        AssociarLayout(mView);
        PovoarSpinners(mView.getContext());
        AssociarEventos();

        if (bd != null)
        {
            if (bd.getLong("OcorrenciaId", 0) != 0)
            {
                ocorrenciaVida = OcorrenciaVida.findById(OcorrenciaVida.class, bd.getLong("OcorrenciaId", 0));
                try
                {
                    ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());
                    enderecoVida = EnderecoVida.find(EnderecoVida.class, "ocorrencia_id = ?", ocorrenciaVida.getId().toString()).get(0);
                } catch (Exception e)
                {
                    enderecoVida = new EnderecoVida();
                }
                LimparCampos();
                CarregarEndereco(enderecoVida);
            }
        }
    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    private void AssociarEventos()
    {


        spnEspacoResidencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (spnAbertoRural.getSelectedItem().toString() == "Aberto")
                {
                    spnClima.setSelection(0);
                    spnIluminacao.setSelection(0);
                    rltvClima.setVisibility(View.VISIBLE);
                } else
                {
                    rltvClima.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        spnAbertoRural.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (spnAbertoRural.getSelectedItem().toString() == "Aberto")
                {
                    spnClima.setSelection(0);
                    spnIluminacao.setSelection(0);
                    rltvClima.setVisibility(View.VISIBLE);
                } else
                {
                    rltvClima.setVisibility(View.INVISIBLE);
                }
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
                magicalPermissions = new MagicalPermissions(GerenciarEnderecoVida.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                TipoFotoDialog.show(GerenciarEnderecoVida.this, getActivity(), magicalCamera);
            }
        });

        imgbAudio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bd = new Bundle();
                bd.putString("Local", "conclusão");
                bd.putLong("OcorrenciaId", ocorrencia.getId());
                bd.putString("SecaoVida", "Endereco");
                AudioDialog.show(getActivity(), bd);
            }
        });

        llVeiculo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(getActivity());

                dialog.setContentView(R.layout.dialog_veiculo_vida);
                dialog.setTitle("Veículo");
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();

                final RelativeLayout rltvCampos = (RelativeLayout) dialog.findViewById(R.id.rltv_Dados_Veiculo_Vida);
                final CheckBox cxbProprietarioDesconhecido = (CheckBox) dialog.findViewById(R.id.cxb_Proprietario_Vida_Desconhecido);
                final CheckBox cxbExisteVeiculo = (CheckBox) dialog.findViewById(R.id.cxb_Veiculo_Envolvido);
                final EditText edtNomeProprietario = (EditText) dialog.findViewById(R.id.edt_Nome_Proprietario_Vida);
                final EditText edtNumCNHPRorpeitario = (EditText) dialog.findViewById(R.id.edt_Proprietario_Num_CNH_Vida);
                final Spinner spnTipoCNHProprietario = (Spinner) dialog.findViewById(R.id.spn_Tipo_CNH_Proprietario_Vida);
                final EditText edtMarcaVeiculo = (EditText) dialog.findViewById(R.id.edt_Marca_Veiculo_Vida);
                final EditText edtModeloVeiculo = (EditText) dialog.findViewById(R.id.edt_Modelo_Veiculo_Vida);
                final Spinner spnLocalVeiculo = (Spinner) dialog.findViewById(R.id.spn_Local_Veiculo_Vida);

                final EditText edtPlacaLetras = (EditText) dialog.findViewById(R.id.edt_Placa_Letras_Veiculo_Vida);
                final EditText edtPlacaNumeros = (EditText) dialog.findViewById(R.id.edt_Placa_Numeros_Veiculo_Vida);

                final Button btnSalvar = (Button) dialog.findViewById(R.id.btn_Salvar_Veiculo_Vida);
                final Button btnCancelar = (Button) dialog.findViewById(R.id.btn_Cancelar_Veiculo_Vida);

                List<String> tiposCNH = new ArrayList<>();

                for (TipoCNH t : TipoCNH.values())
                    tiposCNH.add(t.getValor());

                spnTipoCNHProprietario.setAdapter(new ArrayAdapter<String>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, tiposCNH));

                List<String> locaisVeiculo = new ArrayList<>();

                for (LocalVeiculo lv : LocalVeiculo.values())
                    locaisVeiculo.add(lv.getValor());

                spnLocalVeiculo.setAdapter(new ArrayAdapter<String>(v.getContext(), R.layout.support_simple_spinner_dropdown_item, locaisVeiculo));


                cxbExisteVeiculo.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if (cxbExisteVeiculo.isChecked())
                        {

                            ViewUtil.modifyAll(rltvCampos, true);
                        } else
                        {
                            ViewUtil.modifyAll(rltvCampos, false);

                            cxbProprietarioDesconhecido.setChecked(false);
                            edtNomeProprietario.setText("");
                            edtNumCNHPRorpeitario.setText("");
                            spnTipoCNHProprietario.setSelection(BuscadorEnum.getIndex(spnTipoCNHProprietario, TipoCNH.NP.getValor()));
                            edtMarcaVeiculo.setText("");
                            edtModeloVeiculo.setText("");
                            edtPlacaLetras.setText("");
                            edtPlacaNumeros.setText("");
                            spnLocalVeiculo.setSelection(0);
                        }
                    }
                });

                btnCancelar.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.dismiss();
                    }
                });

                btnSalvar.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {

                        if (cxbExisteVeiculo.isChecked())
                        {
                            enderecoVida.setModeloVeiculo(edtModeloVeiculo.getText().toString());
                            enderecoVida.setMarcaVeiculo(edtMarcaVeiculo.getText().toString());
                            enderecoVida.setPlacaVeiculo(edtPlacaLetras.getText().toString() + "-" + edtPlacaNumeros.getText().toString());
                            enderecoVida.setNomeProprietario(edtNomeProprietario.getText().toString());
                            enderecoVida.setNumeroDocumentoProprietario(edtNumCNHPRorpeitario.getText().toString());
                            enderecoVida.setCategoriaProprietario(BuscadorEnum.BuscarTipoCNH(spnTipoCNHProprietario.getSelectedItem().toString()));
                            enderecoVida.setLocalVeiculo(BuscadorEnum.BuscarLocalVeiculo(spnLocalVeiculo.getSelectedItem().toString()));
                            enderecoVida.setVeiculoEnvolvido(true);
                        } else
                        {
                            enderecoVida.setModeloVeiculo("");
                            enderecoVida.setMarcaVeiculo("");
                            enderecoVida.setPlacaVeiculo("");
                            enderecoVida.setNomeProprietario("");
                            enderecoVida.setNumeroDocumentoProprietario("");
                            enderecoVida.setCategoriaProprietario(null);
                            enderecoVida.setLocalVeiculo(null);
                            enderecoVida.setVeiculoEnvolvido(false);
                        }
                        dialog.dismiss();
                    }
                });

//                imgbAudio.setOnClickListener(new View.OnClickListener()
//                {
//                    @Override
//                    public void onClick(View view) throws IllegalArgumentException,
//                            SecurityException, IllegalStateException
//                    {
//
////                        FragmentManager fm = getActivity().getFragmentManager();
////                        dialogFragment = new AudioDialog();
//                        Bundle bd = new Bundle();
//                        bd.putString("Local", "conclusão");
//                        bd.putLong("OcorrenciaId",ocorrenciaVida.getId());
//                        bd.putString("SecaoVida", "Endereco");
//
//                        AudioDialog.show(getActivity(),bd);
////                        dialogFragment.setArguments(bd);
////                        dialogFragment.show(fm, "Seleção");
//                    }
//                });
//


                cxbProprietarioDesconhecido.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        if (cxbProprietarioDesconhecido.isChecked())
                        {
                            edtNomeProprietario.setEnabled(false);
                            edtNomeProprietario.setText("Desconhecido(a)");

                            edtNumCNHPRorpeitario.setText("");
                            edtNumCNHPRorpeitario.setEnabled(false);

                            spnTipoCNHProprietario.setEnabled(false);
                            spnTipoCNHProprietario.setSelection(BuscadorEnum.getIndex(spnTipoCNHProprietario, TipoCNH.NP.getValor()));
                        } else
                        {
                            edtNomeProprietario.setEnabled(true);
                            edtNumCNHPRorpeitario.setEnabled(true);
                            spnTipoCNHProprietario.setEnabled(true);
                            edtNomeProprietario.setText("");
                        }
                    }
                });


                if (enderecoVida.getVeiculoEnvolvido())
                {
                    ViewUtil.modifyAll(rltvCampos, true);
                    cxbExisteVeiculo.setChecked(true);
                } else
                {
                    ViewUtil.modifyAll(rltvCampos, false);
                    cxbExisteVeiculo.setChecked(false);
                    return;
                }

                if (enderecoVida.getPlacaVeiculo() != null)
                {
                    if (enderecoVida.getPlacaVeiculo().length() == 8)
                    {
                        edtPlacaLetras.setText(enderecoVida.getPlacaVeiculo().substring(0, 3));
                        edtPlacaNumeros.setText(enderecoVida.getPlacaVeiculo().substring(4, 8));
                    }
                }

                if (enderecoVida.getMarcaVeiculo() != null)
                    edtMarcaVeiculo.setText(enderecoVida.getMarcaVeiculo());

                if (enderecoVida.getModeloVeiculo() != null)
                    edtModeloVeiculo.setText(enderecoVida.getModeloVeiculo());

                if (enderecoVida.getNumeroDocumentoProprietario() != null)
                    edtNumCNHPRorpeitario.setText(enderecoVida.getNumeroDocumentoProprietario());

                if (enderecoVida.getCategoriaProprietario() != null)
                    spnTipoCNHProprietario.setSelection(BuscadorEnum.getIndex(spnTipoCNHProprietario, enderecoVida.getCategoriaProprietario().getValor()));

                if (enderecoVida.getLocalVeiculo() != null)
                    spnLocalVeiculo.setSelection(BuscadorEnum.getIndex(spnLocalVeiculo, enderecoVida.getLocalVeiculo().getValor()));

                if (enderecoVida.getNomeProprietario() != null)
                {
                    if (enderecoVida.getNomeProprietario().equals("Desconhecido(a)"))
                    {
                        cxbProprietarioDesconhecido.performClick();
                        return;
                    }
                    edtNomeProprietario.setText(enderecoVida.getNomeProprietario());
                }
            }

        });

        rbtnRural.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TrocarLocalCrime(TipoLocalCrime.RURAL);
            }
        });

        rbtnOutro.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TrocarLocalCrime(TipoLocalCrime.OUTRO);
            }
        });

        rbtnPraia.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TrocarLocalCrime(TipoLocalCrime.PRAIA);
            }
        });

        rbtnResidencial.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TrocarLocalCrime(TipoLocalCrime.RESIDENCIAL);
            }
        });


        rbtnViaPublica.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TrocarLocalCrime(TipoLocalCrime.VIA_PUBLICA);
            }
        });


        imgbCoordenadas.setOnClickListener(new View.OnClickListener()
            {
                boolean checking = true;

                @Override
                public void onClick(View v)
                {
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
                             try
                             {
                                 SingleShotLocationProvider.cancelUpdate();
                                 Toast.makeText(getContext(), "Tempo limite excedido!", Toast.LENGTH_LONG).show();

                                 imgbCoordenadas.setVisibility(View.VISIBLE);
                                 pbCoordenadas.setVisibility(View.INVISIBLE);
                                 edtLatitude.setEnabled(true);
                                 edtLongitude.setEnabled(true);
                             }catch (Exception e)
                             {
                                 Toast.makeText(getActivity(),"Erro! Tente novamente",Toast.LENGTH_LONG);
                                 imgbCoordenadas.setVisibility(View.VISIBLE);
                                 pbCoordenadas.setVisibility(View.INVISIBLE);
                                 edtLatitude.setEnabled(true);
                                 edtLongitude.setEnabled(true);

                             }
                            }
                        }
                    }, 1000 * 120);
                }
            }
        );

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // If this is our permission request result.
        if (requestCode == 1)
        {
            if (grantResults.length > 0)
            {
                // Construct result message.
                StringBuffer msgBuf = new StringBuffer();
                int grantResult = grantResults[0];
                if (grantResult == PackageManager.PERMISSION_GRANTED)
                {
                    msgBuf.append("You granted below permissions, you can do the action again to use the permission : ");
                } else
                {
                    msgBuf.append("You denied below permissions : ");
                }

                // Add granted permissions to the message.
                if (permissions != null)
                {
                    int length = permissions.length;
                    for (int i = 0; i < length; i++)
                    {
                        String permission = permissions[i];
                        msgBuf.append(permission);

                        if (i < length - 1)
                        {
                            msgBuf.append(",");
                        }
                    }
                }

                // Show result message.
                Toast.makeText(getActivity(), msgBuf.toString(), Toast.LENGTH_LONG).show();
            }
        }
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
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() + "/Fotos_Endereco/";

            File folder = new File(newPath);

            if (!folder.exists())
                folder.mkdirs();

            newPath += ocorrenciaVida.getNumIncidencia() + "_foto_endereco" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";

            Foto foto;

            if (newFile.renameTo(new File(newPath)))
                foto = new Foto("Foto do Endereço".toString(), newPath, CategoriaFoto.ENDERECOS);

            else
                foto = new Foto("Foto do Endereço".toString(), pathImagem, CategoriaFoto.ENDERECOS);

            foto.save();

            OcorrenciaVidaFoto ocorrenciaFoto = new OcorrenciaVidaFoto(ocorrenciaVida, foto);

            ocorrenciaFoto.save();

            if (pathImagem != null)
                Toast.makeText(getActivity(), "Foto salva no caminho: " + pathImagem, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_LONG).show();

        }
    }


    public void PovoarSpinners(Context ctx)
    {
        aucCidade.setAdapter(AutoCompleteUtil.getCidades(ctx));
        aucBairro.setAdapter(AutoCompleteUtil.getBairros(ctx));

        List<String> tiposVia = new ArrayList<>();

        for (TipoVia tl : TipoVia.values())
            tiposVia.add(tl.getValor());

        spnTipoVia.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tiposVia));

        List<String> posicoesVia = new ArrayList<>();

        for (LocalObjeto lo : LocalObjeto.values())
            posicoesVia.add(lo.getValor());

        spnPosicaoViaPublica.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, posicoesVia));

        List<String> pavimentacoes = new ArrayList<>();

        for (Pavimentacao p : Pavimentacao.values())
            pavimentacoes.add(p.getValor());

        spnPavimentacaoViaPublica.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, pavimentacoes));

        List<String> locaisPraia = new ArrayList<>();

        for (LocalPraia lp : LocalPraia.values())
            locaisPraia.add(lp.getValor());

        spnLocalPraia.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, locaisPraia));

        List<String> vegetacoes = new ArrayList<>();

        for (TipoVegetacao tv : TipoVegetacao.values())
            vegetacoes.add(tv.getValor());

        spnVegetacaoPraia.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, vegetacoes));

        spnVegetacaoRural.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, vegetacoes));

        List<String> tipoAbertura = new ArrayList<>();

        for (TipoAberturaLocal tal : TipoAberturaLocal.values())
            tipoAbertura.add(tal.getValor());

        spnEspacoResidencia.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tipoAbertura));

        spnAbertoRural.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, tipoAbertura));

        List<String> localResidencia = new ArrayList<>();

        for (TipoLocal tal : TipoLocal.values())
            localResidencia.add(tal.getValor());

        spnLocalidadeResidencia.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, localResidencia));

        List<String> comodos = new ArrayList<>();

        for (Comodo c : Comodo.values())
            comodos.add(c.getValor());

        spnComodo.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, comodos));


        List<String> condicoesClimaticas = new ArrayList<>();

        for (Meteorologia m : Meteorologia.values())
            condicoesClimaticas.add(m.getValor());

        spnClima.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, condicoesClimaticas));

        List<String> iluminacaoArtifical = new ArrayList<>();

        for (IluminacaoArtificial ia : IluminacaoArtificial.values())
            iluminacaoArtifical.add(ia.getValor());

        spnIluminacao.setAdapter(new ArrayAdapter<String>(ctx, R.layout.support_simple_spinner_dropdown_item, iluminacaoArtifical));


    }

    public void AssociarLayout(View view)
    {
        //Dados Gerais
        edtEndereco = (EditText) view.findViewById(R.id.edt_Endereco_Vida);
        edtLatitude = (MaskedEditText) view.findViewById(R.id.edt_latitude_Vida);
        edtLongitude = (MaskedEditText) view.findViewById(R.id.edt_longitude_Vida);
        edtComplemento = (EditText) view.findViewById(R.id.edt_Complemento_End_Vida);

        pbCoordenadas = (ProgressBar) view.findViewById(R.id.pgb_Carregar_Coordenadas_Vida);
        spnTipoVia = (Spinner) view.findViewById(R.id.spn_TipoVia_Vida);

        aucBairro = (AutoCompleteTextView) view.findViewById(R.id.auc_Bairro_Vida);
        aucCidade = (AutoCompleteTextView) view.findViewById(R.id.auc_Cidade_Vida);

        imgbCoordenadas = (ImageButton) view.findViewById(R.id.imgb_Coordenadas_Vida);
        sgmtLocalVida = (SegmentedGroup) view.findViewById(R.id.sgm_Local_Vida);

        //Relative Views de cada tipo de endereço
        rltvResidencia = (RelativeLayout) view.findViewById(R.id.rltv_Endereco_Vida_Residencia);
        spnEspacoResidencia = (Spinner) view.findViewById(R.id.spn_Espaco_Residencia);
        spnLocalidadeResidencia = (Spinner) view.findViewById(R.id.spn_Tipo_Localidade_Residencia);
        spnComodo = (Spinner) view.findViewById(R.id.spn_Comodo_Residencia);
        edtObservacoesResidencia = (EditText) view.findViewById(R.id.edt_Observacoes_Residencia);

        rltvRural = (RelativeLayout) view.findViewById(R.id.rltv_Endereco_Vida_Rural);
        edtObservacoesRural = (EditText) view.findViewById(R.id.edt_Observacoes_Rural);
        spnVegetacaoRural = (Spinner) view.findViewById(R.id.spn_Vegetacao_Rural);
        spnAbertoRural = (Spinner) view.findViewById(R.id.spn_Tipo_Localidade_Rural);

        rltvPraia = (RelativeLayout) view.findViewById(R.id.rltv_Endereco_Vida_Praia);
        spnLocalPraia = (Spinner) view.findViewById(R.id.spn_Local_Praia);
        edtObservacoesPraia = (EditText) view.findViewById(R.id.edt_Observacoes_Praia);
        spnVegetacaoPraia = (Spinner) view.findViewById(R.id.spn_Vegetacao_Praia);

        rltvViaPublica = (RelativeLayout) view.findViewById(R.id.rltv_Endereco_Vida_Via_Publica);
        spnPosicaoViaPublica = (Spinner) view.findViewById(R.id.spn_Posicao_Via_Publica);
        edtObservacoesViaPublica = (EditText) view.findViewById(R.id.edt_Observacoes_Via_Publica);
        spnPavimentacaoViaPublica = (Spinner) view.findViewById(R.id.spn_Pavimentacao_Vida);

        rltvOutro = (RelativeLayout) view.findViewById(R.id.rltv_Outro_Vida);
        edtDescricaoOutro = (EditText) view.findViewById(R.id.edt_Descricao_Outro);

        rbtnOutro = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Outro);
        rbtnPraia = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Praia);
        rbtnResidencial = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Residencia);
        rbtnViaPublica = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Via_Publica);
        rbtnRural = (RadioButton) view.findViewById(R.id.rbtn_Opcao_Rural);

        llVeiculo = (LinearLayout) view.findViewById(R.id.ll_Veiculo_Vida);

        rltvClima = (RelativeLayout) view.findViewById(R.id.rltv_Condicoes_Climaticas);
        spnClima = (Spinner) view.findViewById(R.id.spn_Condicoes_Climaticas_Vida);
        spnIluminacao = (Spinner) view.findViewById(R.id.spn_Iluminacao_Vida);

        imgbCamera = (ImageButton) view.findViewById(R.id.imgb_Foto_Endereco_Vida);
        imgbAudio = (ImageButton) view.findViewById(R.id.imgb_Audio_Endereco_Vida);


        edtEndereco.setNextFocusForwardId(edtComplemento.getId());
        edtComplemento.setNextFocusForwardId(aucCidade.getId());
        aucCidade.setNextFocusForwardId(aucBairro.getId());
//        aucBairro.setNextFocusForwardId(edtLatitude.getId());
        edtLatitude.setNextFocusForwardId(edtLongitude.getId());

    }

    public void TrocarLocalCrime(TipoLocalCrime local)
    {
        rltvViaPublica.setVisibility(View.INVISIBLE);
        rltvRural.setVisibility(View.INVISIBLE);
        rltvResidencia.setVisibility(View.INVISIBLE);
        rltvOutro.setVisibility(View.INVISIBLE);
        rltvPraia.setVisibility(View.INVISIBLE);
        if (local != null)
            switch (local)
            {
                case VIA_PUBLICA:
                    rltvViaPublica.setVisibility(View.VISIBLE);
                    break;
                case RURAL:
                    rltvRural.setVisibility(View.VISIBLE);
                    break;
                case OUTRO:
                    rltvOutro.setVisibility(View.VISIBLE);
                    break;
                case PRAIA:
                    rltvPraia.setVisibility(View.VISIBLE);
                    break;
                case RESIDENCIAL:
                    rltvResidencia.setVisibility(View.VISIBLE);
                    break;
                default:
                    break;
            }
    }

    public void LimparCampos()
    {
        spnPosicaoViaPublica.setSelection(0);
        spnPavimentacaoViaPublica.setSelection(0);
        spnAbertoRural.setSelection(0);
        spnVegetacaoRural.setSelection(0);
        spnVegetacaoPraia.setSelection(0);
        spnTipoVia.setSelection(0);
        spnEspacoResidencia.setSelection(0);
        spnLocalidadeResidencia.setSelection(0);
        spnLocalPraia.setSelection(0);
        spnVegetacaoPraia.setSelection(0);
        spnComodo.setSelection(0);
        spnClima.setSelection(0);
        spnIluminacao.setSelection(0);

        edtEndereco.setText("");
        edtComplemento.setText("");
        edtObservacoesViaPublica.setText("");
        edtObservacoesRural.setText("");
        edtObservacoesResidencia.setText("");
        edtObservacoesPraia.setText("");

        aucBairro.setText("");
        aucCidade.setText("");

        edtLatitude.setText("");
        edtLongitude.setText("");

    }

    public void CarregarEndereco(EnderecoVida enderecoVida)
    {
        edtEndereco.setText(enderecoVida.getDescricaoEndereco());
        aucBairro.setText(enderecoVida.getBairro());
        aucCidade.setText(enderecoVida.getCidade());
        edtComplemento.setText(enderecoVida.getComplemento());

        edtLatitude.setText(enderecoVida.getLatitude());
        edtLongitude.setText(enderecoVida.getLongitude());

        if (enderecoVida.getTipoVia() != null)
            spnTipoVia.setSelection(BuscadorEnum.getIndex(spnTipoVia, enderecoVida.getTipoVia().toString()));

        if (enderecoVida.getTipoLocalCrime() != null)

            switch (enderecoVida.getTipoLocalCrime())
            {
                case OUTRO:
                    edtDescricaoOutro.setText(enderecoVida.getObservacao());
                    rbtnOutro.performClick();
                    break;
                case PRAIA:
                    if (enderecoVida.getLocalPraia() != null)
                        spnLocalPraia.setSelection(BuscadorEnum.getIndex(spnLocalPraia, enderecoVida.getLocalPraia().getValor()));

                    if (enderecoVida.getTipoVegetacao() != null)
                        spnVegetacaoPraia.setSelection(BuscadorEnum.getIndex(spnVegetacaoPraia, enderecoVida.getTipoVegetacao().getValor()));

                    edtObservacoesPraia.setText(enderecoVida.getObservacao());
                    rbtnPraia.performClick();
                    break;
                case RESIDENCIAL:
                    if (enderecoVida.getLocalResidencia() != null)
                        spnLocalidadeResidencia.setSelection(BuscadorEnum.getIndex(spnLocalidadeResidencia, enderecoVida.getLocalResidencia().getValor()));
                    if (enderecoVida.getLocalAberto() != null)
                        spnEspacoResidencia.setSelection(BuscadorEnum.getIndex(spnEspacoResidencia, enderecoVida.getLocalAberto().getValor()));
                    if (enderecoVida.getComodo() != null)
                        spnComodo.setSelection(BuscadorEnum.getIndex(spnComodo, enderecoVida.getComodo().getValor()));
                    edtObservacoesResidencia.setText(enderecoVida.getObservacao());
                    rbtnResidencial.performClick();
                    break;
                case RURAL:
                    if (enderecoVida.getTipoVegetacao() != null)
                        spnVegetacaoRural.setSelection(BuscadorEnum.getIndex(spnVegetacaoRural, enderecoVida.getTipoVegetacao().getValor()));
                    if (enderecoVida.getLocalAberto() != null)
                    {
                        spnAbertoRural.setSelection(BuscadorEnum.getIndex(spnEspacoResidencia, enderecoVida.getLocalAberto().getValor()));
                        if (enderecoVida.getLocalAberto() == TipoAberturaLocal.ABERTO)
                        {
                            rltvClima.setVisibility(View.VISIBLE);
                            if (enderecoVida.getCondicoesClimaticas() != null)
                                spnClima.setSelection(BuscadorEnum.getIndex(spnClima, enderecoVida.getCondicoesClimaticas().getValor()));
                            if (enderecoVida.getTipoIluminacao() != null)
                                spnIluminacao.setSelection(BuscadorEnum.getIndex(spnIluminacao, enderecoVida.getTipoIluminacao().getValor()));
                        }
                    }
                    edtObservacoesRural.setText(enderecoVida.getObservacao());

                    rbtnRural.performClick();
                    break;
                case VIA_PUBLICA:
                    if (enderecoVida.getPavimentacao() != null)
                        spnPavimentacaoViaPublica.setSelection(BuscadorEnum.getIndex(spnPavimentacaoViaPublica, enderecoVida.getPavimentacao().getValor()));
                    if (enderecoVida.getPosicaoVia() != null)
                        spnPosicaoViaPublica.setSelection(BuscadorEnum.getIndex(spnPosicaoViaPublica, enderecoVida.getPosicaoVia().getValor()));
                    edtObservacoesViaPublica.setText(enderecoVida.getObservacao());
                    rbtnViaPublica.performClick();
                    break;
            }
        else
            rbtnViaPublica.performClick();

        if (enderecoVida.getLocalAberto() != null)
        {
            if (enderecoVida.getLocalAberto() == TipoAberturaLocal.ABERTO)
            {
                rltvClima.setVisibility(View.VISIBLE);
                if (enderecoVida.getCondicoesClimaticas() != null)
                    spnClima.setSelection(BuscadorEnum.getIndex(spnClima, enderecoVida.getCondicoesClimaticas().getValor()));
                if (enderecoVida.getTipoIluminacao() != null)
                    spnIluminacao.setSelection(BuscadorEnum.getIndex(spnIluminacao, enderecoVida.getTipoIluminacao().getValor()));
            }
        }
    }


    public void DialogResult(Bundle bd)
    {
        if (bd != null)
        {
            if (!bd.getString("Latitude", "").equals("")) ;
            edtLatitude.setText(bd.getString("Latitude", ""));

            if (!bd.getString("Longitude", "").equals("")) ;
            edtLongitude.setText(bd.getString("Longitude", ""));
        }
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mView = null;

        ocorrenciaVida = null;
        ocorrencia = null;
        dialogFragment = null;
        locationManager = null;
        spnTipoVia = null;
        aucCidade = null;
        aucBairro = null;
        edtEndereco = null;
        edtComplemento = null;
        edtLatitude = null;
        edtLongitude = null;
        imgbCoordenadas = null;
        imgbCamera = null;
        imgbAudio = null;
        sgmtLocalVida = null;
        rbtnPraia = null;
        rbtnRural = null;
        rbtnViaPublica = null;
        rbtnResidencial = null;
        rbtnOutro = null;
        rltvRural = null;
        spnVegetacaoRural = null;
        spnAbertoRural = null;
        edtObservacoesRural = null;
        rltvPraia = null;
        spnLocalPraia = null;
        spnVegetacaoPraia = null;
        edtObservacoesPraia = null;
        rltvResidencia = null;
        spnEspacoResidencia = null;
        spnLocalidadeResidencia = null;
        spnComodo = null;
        edtObservacoesResidencia = null;
        rltvViaPublica = null;
        spnPosicaoViaPublica = null;
        spnPavimentacaoViaPublica = null;
        edtObservacoesViaPublica = null;
        rltvOutro = null;
        edtDescricaoOutro = null;
        rltvClima = null;
        spnClima = null;
        spnIluminacao = null;
        ocorrencia = null;
        ocorrenciaVida = null;
        enderecoVida = null;
        llVeiculo = null;
    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
