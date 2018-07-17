package Fragments.FragmentsTransito;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.GerenciarDano;
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

import Adapters.AdapterVeiculo;
import Enums.CategoriaFoto;
import Enums.Cor;
import Enums.Transito.TipoCNH;
import Enums.Transito.TipoVeiculo;
import Enums.UF;
import Model.Transito.ColisaoTransito;
import Model.Transito.Dano;
import Model.Transito.DanoVeiculo;
import Model.Transito.EnderecoVeiculo;
import Model.Foto;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;
import Util.StringUtil;
import Util.ViewUtil;

public class GerenciarVeiculoTransito extends android.support.v4.app.Fragment implements Step
{

    OcorrenciaTransito ocorrenciaTransitoVeiculo;
    Ocorrencia ocorrencia;
    ListView lstvVeiculos = null;

    int lastPosition;

    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;

    CheckBox cxbVeiculoDesconhecido = null;

    List<Dano> danosTotais = null;
    ListView lstvDanos = null;
    ArrayList<Veiculo> veiculosModel = null;

    AdapterVeiculo adapterV = null;

    Spinner spnTipoVeiculo = null;
    Spinner spnCor = null;
    Spinner spnAnoVeiculo = null;
    Spinner spnAnoFabricacao = null;
    Spinner spnUF = null;

    EditText edtModeloVeiculo = null;
    EditText edtMarcaVeiculo = null;
    EditText edtPlaca_Letras = null;
    EditText edtPlaca_Numeros = null;
    EditText edtMunicipio = null;

    TextView txvProprietarioNome = null;
    TextView txvProprietarioNumero = null;

    TextView txvCondutorNome = null;
    TextView txvCondutorNumero = null;

    Veiculo veiculo = null;
    OcorrenciaTransitoVeiculo ocorrenciaVeiculo = null;
    EnderecoVeiculo enderecoVeiculo = null;

    List<DanoVeiculo> danosVeiculo = null;

    List<OcorrenciaTransitoVeiculo> ocorrenciaVeiculos = null;

    FloatingActionButton fabFotoVeiculo = null;
    ArrayList<String> anos = new ArrayList<String>();
    ArrayAdapter<String> adapterAno = null;
    ArrayAdapter<Dano> adapterDano = null;

    FloatingActionButton fabVeiculo = null;
    LinearLayout addDano = null;

    RelativeLayout rltv_Veiculo = null;

    RelativeLayout rltvProprietario = null;
    View mView;

    //Veículo não Motorizado
    boolean VNM = false;

    public GerenciarVeiculoTransito()
    {

    }

    public static GerenciarVeiculoTransito newInstance()
    {
        GerenciarVeiculoTransito fragment = new GerenciarVeiculoTransito();

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
        final View root = inflater.inflate(R.layout.fragment_gerenciar_veiculo, container, false);
        mView = root;

        return root;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }


    @Override
    public VerificationError verifyStep()
    {
        if (rltv_Veiculo.getChildAt(0).isEnabled())
        {
            try
            {
                SalvarVeiculo();
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
        //((ManterPericiaTransito) getActivity()).toolbar.setTitle("Veículos");
        ((ManterPericiaTransito) getActivity()).txvToolbarTitulo.setText("Veículos");

        lastPosition = -1;

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        AssociarLayout(mView);
        PovoarSpinner(getContext());
        AssociarEventos();

        ViewUtil.modifyAll(rltv_Veiculo, false);
//        ViewUtil.modifyAll(rltvProprietario, false);

        ocorrenciaTransitoVeiculo = ((ManterPericiaTransito) getActivity()).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoVeiculo.getOcorrenciaID());

        ocorrenciaVeiculos = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ocorrenciaTransitoVeiculo.getId().toString());

        veiculosModel = new ArrayList<>();

        for (OcorrenciaTransitoVeiculo ov : ocorrenciaVeiculos)
        {
            if (ov.getVeiculo() != null)
                veiculosModel.add(ov.getVeiculo());
        }

        adapterV = new AdapterVeiculo(veiculosModel, getActivity());

//        if(veiculosModel.size()==0)
//        {
        ViewUtil.modifyAll(rltv_Veiculo, false);
//        ViewUtil.modifyAll(rltvProprietario, false);
//        }


        Bundle bd = getArguments();


        lstvVeiculos.setAdapter(adapterV);

        if (bd.getBoolean("DiretoParaVeiculo"))
        {
            try
            {
                Veiculo veiculoIndex = Veiculo.findById(Veiculo.class, bd.getLong("VeiculoId"));
                lstvVeiculos.performItemClick(lstvVeiculos, BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculoIndex), lstvVeiculos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculoIndex)));
            } catch (Exception e)
            {
            }
        }

        if (bd.getLong("VeiculoId") != 0 && !bd.getBoolean("DiretoParaVeiculo"))
        {
            veiculo = Veiculo.findById(Veiculo.class, bd.getLong("VeiculoId"));

            try
            {
                enderecoVeiculo = EnderecoVeiculo.find(EnderecoVeiculo.class, "veiculo = ?", veiculo.getId().toString()).get(0);
            } catch (Exception e)
            {
                enderecoVeiculo = new EnderecoVeiculo();
            }

            carregarDadosVeiculo();
        }
    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    private void SalvarVeiculo()
    {
        veiculo.setCor(BuscadorEnum.BuscarCor(spnCor.getSelectedItem().toString()));
        //veiculo.setCor(Cor.valueOf(Cor.class,spnCor.getSelectedItem().toString()));

        try
        {
            veiculo.setAnoModelo(Integer.parseInt(spnAnoVeiculo.getSelectedItem().toString()));
            veiculo.setAnoFabricacao(Integer.parseInt(spnAnoFabricacao.getSelectedItem().toString()));
        } catch (Exception e)
        {
            veiculo.setAnoModelo(0);
            veiculo.setAnoFabricacao(0);
        }

        veiculo.setMunicipioPlaca(edtMunicipio.getText().toString());

        veiculo.setUfPlaca(BuscadorEnum.BuscarUF(spnUF.getSelectedItem().toString()));

        veiculo.setTipoVeiculo(BuscadorEnum.BuscarTipoVeiculo(spnTipoVeiculo.getSelectedItem().toString()));
        veiculo.setModelo(edtModeloVeiculo.getText().toString());
        veiculo.setMarca(edtMarcaVeiculo.getText().toString());
        veiculo.setPlaca(edtPlaca_Letras.getText() + "-" + edtPlaca_Numeros.getText());

        veiculo.save();

        if (veiculo.getId() != null)
        {
            List<DanoVeiculo> danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", veiculo.getId().toString());

            for (DanoVeiculo danov : danosVeiculo)
            {
                danov.delete();
            }

            for (Dano dano : danosTotais)
            {
                dano.save();
                DanoVeiculo dv = new DanoVeiculo(dano, veiculo);
                dv.save();
            }
        }

        //  enderecoVeiculo.setVeiculo(veiculo);

        //  enderecoVeiculo.setEnderecoTransito(enderecosTransito.get(spnEnderecoVeiculo.getSelectedItemPosition()));

        //   enderecoVeiculo.save();


        try
        {
            String[] args = {veiculo.getId().toString(), ocorrenciaTransitoVeiculo.getId().toString()};
            ocorrenciaVeiculo = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "veiculo = ? and ocorrencia_transito = ?", args).get(0);
        } catch (Exception e)
        {
            ocorrenciaVeiculo = new OcorrenciaTransitoVeiculo(ocorrenciaTransitoVeiculo, veiculo);
        }

        ocorrenciaVeiculo.save();
    }

    private void carregarDadosVeiculo()
    {
        LimparCampos();

        if (veiculo.getAnoModelo() != 0)
            //            spnTipoVeiculo.setSelection(veiculo.getAnoModelo());
            spnAnoVeiculo.setSelection(adapterAno.getPosition(String.valueOf(veiculo.getAnoModelo())));
        if (veiculo.getAnoFabricacao() != 0)
            spnAnoFabricacao.setSelection(adapterAno.getPosition(String.valueOf(veiculo.getAnoFabricacao())));

        if (veiculo.getCor() != null)
            spnCor.setSelection(BuscadorEnum.getIndex(spnCor, veiculo.getCor().getValor()));

        if (veiculo.getModelo() != null)
            edtModeloVeiculo.setText(veiculo.getModelo());

        if (veiculo.getMarca() != null)
            edtMarcaVeiculo.setText(veiculo.getMarca());

        if (veiculo.getTipoVeiculo() != null)
            spnTipoVeiculo.setSelection(BuscadorEnum.getIndex(spnTipoVeiculo, veiculo.getTipoVeiculo().getValor()));

        if (veiculo.getPlaca() != null)
        {
            if (veiculo.getPlaca().length() == 8)
            {
                edtPlaca_Letras.setText(veiculo.getPlaca().substring(0, 3));
                edtPlaca_Numeros.setText(veiculo.getPlaca().substring(4, 8));
            }

            if (veiculo.getPlaca().equals("-"))
                cxbVeiculoDesconhecido.performClick();
        }

        if (veiculo.getMunicipioPlaca() != null)
            edtMunicipio.setText(veiculo.getMunicipioPlaca());

        if (veiculo.getUfPlaca() != null)
            spnUF.setSelection(BuscadorEnum.getIndex(spnUF, veiculo.getUfPlaca().getValor()));

        if (veiculo.getNomeCondutor() != null)
            txvCondutorNome.setText(StringUtil.checkValue(veiculo.getNomeCondutor(), 10, ""));

        if (veiculo.getNumeroDocumentoCondutor() != null)
            txvCondutorNumero.setText(veiculo.getNumeroDocumentoCondutor());

        if (veiculo.getNomeProprietario() != null)
            txvProprietarioNome.setText(veiculo.getNomeProprietario());

        if (veiculo.getNumeroDocumentoProprietario() != null)
            txvProprietarioNumero.setText(veiculo.getNumeroDocumentoProprietario());

        if (veiculo.getCategoriaProprietario() != null)
            txvProprietarioNumero.setText(txvProprietarioNumero.getText().toString() + " Cat. " + veiculo
                    .getCategoriaProprietario().getValor());

        if (veiculo.getCategoriaCondutor() != null)
            txvCondutorNumero.setText(txvCondutorNumero.getText().toString() + " Cat. " + veiculo
                    .getCategoriaCondutor().getValor());


        //  if(enderecoVeiculo.getEnderecoTransito()!=null)
        //      spnEnderecoVeiculo.setSelection(BuscadorEnum.getIndex(spnEnderecoVeiculo,enderecoVeiculo.getEnderecoTransito().getEnderecoTransito().getDescricaoEndereco()));


        adapterDano = new ArrayAdapter<Dano>(getContext(), android.R.layout.simple_spinner_dropdown_item, danosTotais);

        lstvDanos.setAdapter(adapterDano);

        adapterDano.notifyDataSetChanged();
    }

    public void LimparCampos()
    {
        spnAnoVeiculo.setSelection(0);
        spnAnoFabricacao.setSelection(0);
        spnCor.setSelection(0);
        edtModeloVeiculo.setText("");
        edtMarcaVeiculo.setText("");
        spnTipoVeiculo.setSelection(0);

        spnUF.setSelection(BuscadorEnum.getIndex(spnUF, UF.CE.getValor()));

        edtMunicipio.setText("");
        edtPlaca_Letras.setText("");
        edtPlaca_Numeros.setText("");
        //  spnEnderecoVeiculo.setSelection(0);
        if (adapterDano != null)
            adapterDano.clear();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        String pathImagem = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "imagem_pericia", "Imagens " + ocorrenciaTransitoVeiculo.getId().toString(), MagicalCamera.JPEG, true);

        if (pathImagem != null)
        {
            File newFile = new File(pathImagem);

            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoVeiculo.getDataPath() + "/" + ocorrenciaTransitoVeiculo.getNumIncidencia() + "/Fotos_Veiculos/";

            File folder = new File(newPath);

            if (!folder.exists())
                folder.mkdirs();

            newPath += "foto_veiculo" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";

            Foto foto;
            if (newFile.renameTo(new File(newPath)))
                foto = new Foto("Foto do Veiculo".toString(), newPath, CategoriaFoto.VEICULOS);

            else
                foto = new Foto("Foto do Veiculo".toString(), pathImagem, CategoriaFoto.VEICULOS);

            foto.save();
            OcorrenciaTransitoFoto ocorrenciaFoto = new OcorrenciaTransitoFoto();
            ocorrenciaFoto.setFoto(foto);
            ocorrenciaFoto.setOcorrenciaTransito(ocorrenciaTransitoVeiculo);
            ocorrenciaFoto.save();
            //ocorrenciaFotos.add(ocorrenciaFoto);
            //adapterGaleria.add(foto);
            //adapterGaleria.notifyDataSetChanged();

            if (pathImagem != null)
                Toast.makeText(getActivity(), "Foto salva no caminho: " + newPath, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_LONG).show();

        }
    }


    private void AssociarEventos()
    {

        addDano.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View arg0)
            {
                SalvarVeiculo();
                Intent it = new Intent(getContext(), GerenciarDano.class);
                it.putExtra("VeiculoId", veiculo.getId());
                it.putExtra("OcorrenciaId", ocorrenciaTransitoVeiculo.getId());
                startActivity(it);
            }
        });

        cxbVeiculoDesconhecido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbVeiculoDesconhecido.isChecked())
                {
                    edtMunicipio.setEnabled(false);
                    spnUF.setEnabled(false);
                    edtPlaca_Numeros.setEnabled(false);
                    edtPlaca_Letras.setEnabled(false);
                    edtPlaca_Letras.setText("");
                    edtPlaca_Numeros.setText("");
                    edtMunicipio.setText("");
                    spnUF.setSelection(0);
                    edtPlaca_Numeros.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.stroke_placa_transito_disabled));
                    edtPlaca_Letras.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.stroke_placa_transito_disabled));

                } else
                {
                    edtMunicipio.setEnabled(true);
                    spnUF.setEnabled(true);
                    edtPlaca_Numeros.setEnabled(true);
                    spnUF.setSelection(BuscadorEnum.getIndex(spnUF, UF.CE.getValor()));
                    edtPlaca_Letras.setEnabled(true);
                    edtPlaca_Numeros.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.stroke_placa_transito));
                    edtPlaca_Letras.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.stroke_placa_transito));
                }
            }
        });

        rltvProprietario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_proprietario);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();


                AssociarViews_Proprietario(dialog);
            }
        });

        lstvVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (lastPosition != -1 && lastPosition != position)
                {
                    try
                    {
                        ocorrenciaVeiculo = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "veiculo = ?", veiculo.getId().toString()).get(0);
                    } catch (Exception e)
                    {
                        ocorrenciaVeiculo = new OcorrenciaTransitoVeiculo();
                    }

                    SalvarVeiculo();

                }

                lastPosition = position;

                veiculo = veiculosModel.get(position);

                LimparCampos();

                ViewUtil.modifyAll(rltv_Veiculo, true);
//                ViewUtil.modifyAll(rltvProprietario, true);

                try
                {
                    ocorrenciaVeiculo = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ? and veiculo = ?", ocorrenciaTransitoVeiculo.getId().toString(), veiculo.getId().toString()).get(0);
                } catch (Exception e)
                {
                    ocorrenciaVeiculo = new OcorrenciaTransitoVeiculo();
                }
                try
                {
                    enderecoVeiculo = EnderecoVeiculo.find(EnderecoVeiculo.class, "veiculo = ?", veiculo.getId().toString()).get(0);
                } catch (Exception e)
                {
                    enderecoVeiculo = new EnderecoVeiculo();
                }

                danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", veiculo.getId().toString());

                for (DanoVeiculo dv : danosVeiculo)
                {
                    danosTotais.add(dv.getDano());
                }


                carregarDadosVeiculo();

                adapterV.notifyDataSetChanged();
            }
        });

        lstvVeiculos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
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

                builder.setTitle("Deletar Veículo")
                        .setMessage("Você deseja deletar este Veículo?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                OcorrenciaTransitoVeiculo ocorrenciaVeiculo = ocorrenciaVeiculos.get(position);

                                if (ColisaoTransito.find(ColisaoTransito.class, "veiculo2 = ?", ocorrenciaVeiculo.getVeiculo().getId().toString()).size() > 0
                                        || ColisaoTransito.find(ColisaoTransito.class, "veiculo1 = ?", ocorrenciaVeiculo.getVeiculo().getId().toString()).size() > 0)
                                {
                                    Toast.makeText(getContext(), "Não foi possível excluir! O veículo está atrelado à uma Colisão!", Toast.LENGTH_LONG).show();
                                    dialog.dismiss();
                                    return;
                                }

                                try
                                {
                                    List<DanoVeiculo> danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", ocorrenciaVeiculo.getVeiculo().getId().toString());

                                    for (DanoVeiculo dv : danosVeiculo)
                                    {
                                        dv.getDano().delete();
                                        dv.delete();
                                    }

                                    enderecoVeiculo = EnderecoVeiculo.find(EnderecoVeiculo.class, "veiculo = ?", ocorrenciaVeiculo.getVeiculo().getId().toString()).get(0);
                                    enderecoVeiculo.delete();
                                } catch (Exception ignored)
                                {

                                }

                                adapterV.remove(ocorrenciaVeiculo.getVeiculo());

                                ocorrenciaVeiculo.getVeiculo().delete();

                                ocorrenciaVeiculo.delete();

                                ocorrenciaVeiculos.remove(position);

                                ViewUtil.modifyAll(rltv_Veiculo, false);
//                                ViewUtil.modifyAll(rltvProprietario, false);


                                Toast.makeText(getActivity(), "Veículo Deletado com sucesso!", Toast.LENGTH_LONG).show();
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

        fabVeiculo.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                if (veiculo != null)
                    SalvarVeiculo();

                veiculo = new Veiculo();
                ocorrenciaVeiculo = new OcorrenciaTransitoVeiculo();

                veiculo.save();

                ocorrenciaVeiculo.setVeiculo(veiculo);
                ocorrenciaVeiculo.setOcorrenciaTransito(ocorrenciaTransitoVeiculo);

                ocorrenciaVeiculo.save();

                ocorrenciaVeiculos.add(ocorrenciaVeiculo);

                adapterV.add(veiculo);
                adapterV.notifyDataSetChanged();

                LimparCampos();
                ViewUtil.modifyAll(rltv_Veiculo, true);

                lstvVeiculos.performItemClick(lstvVeiculos, BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculo), lstvVeiculos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculo)));
            }
        });

        fabFotoVeiculo.setOnClickListener(new View.OnClickListener()
        {
            //TODO: refazer chekc permission utilizando o auto permission do android arsenal ou apenas implementar a negativa da permissão//
            public void onClick(View v)
            {
                String[] permissions = new String[]
                        {
                                Manifest.permission.CAMERA,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION
                        };
                magicalPermissions = new MagicalPermissions(GerenciarVeiculoTransito.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                TipoFotoDialog tfd = new TipoFotoDialog();
            }
        });

        edtPlaca_Letras.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after)
            {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s)
            {
                if (s.length() == 3)
                    edtPlaca_Numeros.requestFocus();
            }
        });
    }

    private void AssociarViews_Proprietario(final Dialog d)
    {
        final EditText edtNomeProprietario = (EditText) d.findViewById(R.id.edt_NomeProprietario);
        final EditText edtDocumentoProprietario = (EditText) d.findViewById(R.id.edt_ProprietarioCNH);
        final Spinner spnTipoCNHProprietario = (Spinner) d.findViewById(R.id.spn_CNH_Proprietario);
        final CheckBox cxbProprietarioDesabilitado = (CheckBox) d.findViewById(R.id.cxb_Proprietario_Nao_Habilitado);
        final CheckBox cxbProprietarioDesconhecido = (CheckBox) d.findViewById(R.id.cxb_Proprietario_Nao_Identificado);

        final CheckBox cxbCondutor_Igual_Proprietario = (CheckBox) d.findViewById(R.id.cxb_Condutor_igual_Proprietario);

        final EditText edtNomeCondutor = (EditText) d.findViewById(R.id.edt_Nome_Condutor);
        final EditText edtDocumentoCondutor = (EditText) d.findViewById(R.id.edt_CondutorCNH);
        final Spinner spnTipoCNHCondutor = (Spinner) d.findViewById(R.id.spn_CNH_Condutor);
        final CheckBox cxbCondutorDesabilitado = (CheckBox) d.findViewById(R.id.cxb_Condutor_Nao_Habilitado);
        final CheckBox cxbCondutorDesconhecido = (CheckBox) d.findViewById(R.id.cxb_Condutor_Nao_Identificado);
        final TextView txvTituloProprietarioNome = (TextView) d.findViewById(R.id.txv_Nome_Proprietario);
        final TextView txvTituloProprietarioCNH = (TextView) d.findViewById(R.id.txv_CNH_Proprietario);
        final TextView txvTituloProprietarioCategoria = (TextView) d.findViewById(R.id.txv_Categoria_Proprietario);
        final TextView txvCondutorProprietario = (TextView) d.findViewById(R.id.txv_Condutor_Proprietario);

        ArrayList<String> tipoCNH = new ArrayList<String>();

        for (TipoCNH cnh : TipoCNH.values())
        {
            tipoCNH.add(cnh.getValor());
        }
        spnTipoCNHProprietario.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoCNH));
        spnTipoCNHCondutor.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipoCNH));

        Button salvarProprietario = (Button) d.findViewById(R.id.btn_Salvar_Proprietario);
        salvarProprietario.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                if (edtNomeProprietario.getText().toString().length() < 3
                        && !cxbProprietarioDesconhecido.isChecked()
                        && !cxbCondutor_Igual_Proprietario.isChecked())
                {
                    edtNomeProprietario.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "Nome do(a) Proprietário(a) muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edtDocumentoProprietario.getText().toString().length() < 3
                        && (!cxbProprietarioDesabilitado.isChecked() && !cxbProprietarioDesconhecido.isChecked()
                        && !cxbCondutor_Igual_Proprietario.isChecked())
                    //&& !edtDocumentoProprietario.getText().toString().equals("-")
                        )
                {
                    edtDocumentoProprietario.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "O Nº Doc. do(a) Proprietário(a) é muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (edtNomeCondutor.getText().toString().length() < 3
                        && !cxbCondutorDesconhecido.isChecked()
//                        && !cxbCondutor_Igual_Proprietario.isChecked()
                        )
                {
                    edtNomeCondutor.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "Nome do(a) Condutor(a) muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edtDocumentoCondutor.getText().toString().length() < 3
                        && (!cxbCondutorDesabilitado.isChecked() && !cxbCondutorDesconhecido.isChecked())
//                        && !cxbCondutor_Igual_Proprietario.isChecked()
                        )
                {
                    edtDocumentoCondutor.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "O Nº Doc. do(a) Condutor(a) é muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (cxbCondutorDesabilitado.isChecked())
                {
                    veiculo.setNumeroDocumentoCondutor("0");
                    veiculo.setCategoriaCondutor(TipoCNH.NP);
                    if (cxbCondutorDesconhecido.isChecked())
                        veiculo.setNomeCondutor("Desconhecido(a)");
                    else
                        veiculo.setNomeCondutor(edtNomeCondutor.getText().toString());
                } else
                {
                    veiculo.setNomeCondutor(edtNomeCondutor.getText().toString());
                    veiculo.setNumeroDocumentoCondutor(edtDocumentoCondutor.getText().toString());
                    veiculo.setCategoriaCondutor(BuscadorEnum.BuscarTipoCNH(spnTipoCNHCondutor.getSelectedItem().toString()));
                    //veiculo.setCategoriaCondutor(TipoCNH.valueOf(TipoCNH.class,spnTipoCNHCondutor.getSelectedItem().toString()));
                }

                if (cxbCondutor_Igual_Proprietario.isChecked())

                {
                    veiculo.setNomeProprietario(veiculo.getNomeCondutor());
                    veiculo.setCategoriaProprietario(veiculo.getCategoriaCondutor());
                    veiculo.setNumeroDocumentoProprietario(veiculo.getNumeroDocumentoCondutor());


                } else
                {
                    if (cxbProprietarioDesabilitado.isChecked())
                    {
                        veiculo.setNumeroDocumentoProprietario("0");
                        veiculo.setCategoriaProprietario(TipoCNH.NP);
                        if (cxbProprietarioDesconhecido.isChecked())
                            veiculo.setNomeProprietario("Desconhecido(a)");
                        else
                            veiculo.setNomeProprietario(edtNomeProprietario.getText().toString());
                    } else
                    {
                        veiculo.setNomeProprietario(edtNomeProprietario.getText().toString());
                        veiculo.setNumeroDocumentoProprietario(edtDocumentoProprietario.getText().toString());
                        veiculo.setCategoriaProprietario(BuscadorEnum.BuscarTipoCNH(spnTipoCNHProprietario.getSelectedItem().toString()));
                        //veiculo.setCategoriaCondutor(TipoCNH.valueOf(TipoCNH.class,spnTipoCNHCondutor.getSelectedItem().toString()));
                    }
                }

                txvProprietarioNome.setText(veiculo.getNomeProprietario());

                if (veiculo.getNumeroDocumentoProprietario() != "0")
                    txvProprietarioNumero.setText(veiculo.getNumeroDocumentoProprietario() + " Cat. " + veiculo.getCategoriaProprietario().toString());
                else
                    txvProprietarioNumero.setText("(Não habilitado)");

                txvCondutorNome.setText(veiculo.getNomeCondutor());
                if (veiculo.getNumeroDocumentoCondutor() != "0")
                    txvCondutorNumero.setText(veiculo.getNumeroDocumentoCondutor() + " Cat. " + veiculo.getCategoriaCondutor().toString());
                else
                    txvCondutorNumero.setText("(Não habilitado)");

                d.dismiss();

            }
        });


        Button cancelarProprietario = (Button) d.findViewById(R.id.btn_Cancelar_Proprietario);
        cancelarProprietario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                d.dismiss();
            }
        });


        cxbProprietarioDesabilitado.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbProprietarioDesabilitado.isChecked())
                {
                    edtDocumentoProprietario.setText("0");
                    edtDocumentoProprietario.setEnabled(false);
                    spnTipoCNHProprietario.setSelection(BuscadorEnum.getIndex(spnTipoCNHProprietario, TipoCNH.NP.getValor()));
                    spnTipoCNHProprietario.setEnabled(false);
                } else
                {
                    edtDocumentoProprietario.setEnabled(true);
                    edtDocumentoProprietario.setText("");
                    spnTipoCNHProprietario.setEnabled(true);
                }
            }
        });


        cxbCondutor_Igual_Proprietario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbCondutor_Igual_Proprietario.isChecked())
                {
                    cxbProprietarioDesabilitado.setVisibility(View.INVISIBLE);
                    cxbProprietarioDesconhecido.setVisibility(View.INVISIBLE);
                    edtDocumentoProprietario.setVisibility(View.INVISIBLE);
                    edtNomeProprietario.setVisibility(View.INVISIBLE);
                    spnTipoCNHProprietario.setVisibility(View.INVISIBLE);
                    txvTituloProprietarioNome.setVisibility(View.INVISIBLE);
                    txvTituloProprietarioCNH.setVisibility(View.INVISIBLE);
                    txvTituloProprietarioCategoria.setVisibility(View.INVISIBLE);

                    txvCondutorProprietario.setVisibility(View.VISIBLE);


                } else
                {
                    cxbProprietarioDesabilitado.setChecked(false);
                    cxbProprietarioDesconhecido.setChecked(false);

                    cxbProprietarioDesabilitado.setEnabled(true);
                    cxbProprietarioDesconhecido.setEnabled(true);

                    edtDocumentoProprietario.setText("");
                    edtNomeProprietario.setText("");
                    spnTipoCNHProprietario.setSelection(0);
                    edtNomeProprietario.setEnabled(true);
                    edtDocumentoProprietario.setEnabled(true);
                    spnTipoCNHProprietario.setEnabled(true);


                    cxbProprietarioDesabilitado.setVisibility(View.VISIBLE);
                    cxbProprietarioDesconhecido.setVisibility(View.VISIBLE);
                    edtDocumentoProprietario.setVisibility(View.VISIBLE);
                    edtNomeProprietario.setVisibility(View.VISIBLE);
                    spnTipoCNHProprietario.setVisibility(View.VISIBLE);
                    txvTituloProprietarioNome.setVisibility(View.VISIBLE);
                    txvTituloProprietarioCNH.setVisibility(View.VISIBLE);
                    txvTituloProprietarioCategoria.setVisibility(View.VISIBLE);

                    txvCondutorProprietario.setVisibility(View.INVISIBLE);
                }
            }
        });


        cxbProprietarioDesconhecido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbProprietarioDesconhecido.isChecked())
                {
//                    edtDocumentoProprietario.setText("0");
//                    edtDocumentoProprietario.setEnabled(false);
                    edtNomeProprietario.setText("Desconhecido(a)");
                    edtNomeProprietario.setEnabled(false);
                    spnTipoCNHProprietario.setSelection(BuscadorEnum.getIndex(spnTipoCNHProprietario, TipoCNH.NP.getValor()));
                    spnTipoCNHProprietario.setEnabled(false);
                    cxbProprietarioDesabilitado.setChecked(false);
                    cxbProprietarioDesabilitado.performClick();
                    cxbProprietarioDesabilitado.setEnabled(false);
                } else
                {
                    if (cxbProprietarioDesabilitado.isChecked())
                    {
                        edtNomeProprietario.setText("");
                        edtNomeProprietario.setEnabled(true);
                        cxbProprietarioDesabilitado.setEnabled(true);
                        return;
                    }
                    edtDocumentoProprietario.setText("");
                    edtDocumentoProprietario.setEnabled(true);
                    spnTipoCNHProprietario.setEnabled(true);

                }
            }
        });

        cxbCondutorDesabilitado.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbCondutorDesabilitado.isChecked())
                {
                    edtDocumentoCondutor.setText("0");
                    edtDocumentoCondutor.setEnabled(false);
                    spnTipoCNHCondutor.setSelection(BuscadorEnum.getIndex(spnTipoCNHCondutor, TipoCNH.NP.getValor()));
                    spnTipoCNHCondutor.setEnabled(false);
                } else
                {
                    edtDocumentoCondutor.setText("");
                    edtDocumentoCondutor.setEnabled(true);
                    spnTipoCNHCondutor.setEnabled(true);
                }
            }
        });


        cxbCondutorDesconhecido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbCondutorDesconhecido.isChecked())
                {
                    edtDocumentoCondutor.setText("0");
                    edtDocumentoCondutor.setEnabled(false);
                    edtNomeCondutor.setText("Desconhecido(a)");
                    edtNomeCondutor.setEnabled(false);
                    spnTipoCNHCondutor.setSelection(BuscadorEnum.getIndex(spnTipoCNHCondutor, TipoCNH.NP.getValor()));
                    spnTipoCNHCondutor.setEnabled(false);
                    cxbCondutorDesabilitado.setChecked(false);
                    cxbCondutorDesabilitado.performClick();
                    cxbCondutorDesabilitado.setEnabled(false);
                } else
                {
                    if (cxbCondutorDesabilitado.isChecked())
                    {
                        edtNomeCondutor.setText("");
                        edtNomeCondutor.setEnabled(true);
                        cxbCondutorDesabilitado.setEnabled(true);
                        return;
                    }
                    edtDocumentoCondutor.setText("");
                    edtDocumentoCondutor.setEnabled(true);
                    spnTipoCNHCondutor.setEnabled(true);
                }
            }
        });

        if (veiculo.getNomeProprietario() != null)
            edtNomeProprietario.setText(veiculo.getNomeProprietario());
        if (veiculo.getNumeroDocumentoProprietario() != null)
            edtDocumentoProprietario.setText(veiculo.getNumeroDocumentoProprietario());
        if (veiculo.getCategoriaProprietario() != null)
            spnTipoCNHProprietario.setSelection(BuscadorEnum.getIndex(spnTipoCNHProprietario, veiculo.getCategoriaProprietario().getValor()));

        if (veiculo.getNomeCondutor() != null)
            edtNomeCondutor.setText(veiculo.getNomeCondutor());
        if (veiculo.getNumeroDocumentoCondutor() != null)
            edtDocumentoCondutor.setText(veiculo.getNumeroDocumentoCondutor());
        if (veiculo.getCategoriaCondutor() != null)
            spnTipoCNHCondutor.setSelection(BuscadorEnum.getIndex(spnTipoCNHCondutor, veiculo.getCategoriaCondutor().getValor()));

        if (veiculo.getNomeProprietario().equals("Desconhecido(a)") && veiculo.getNumeroDocumentoProprietario().equals("0"))

        {
            cxbProprietarioDesconhecido.setChecked(false);
            cxbProprietarioDesconhecido.performClick();
        }
        if (veiculo.getNomeCondutor().equals("Desconhecido(a)") && veiculo.getNumeroDocumentoCondutor().equals("0"))

        {
            cxbCondutorDesconhecido.setChecked(false);
            cxbCondutorDesconhecido.performClick();
        }

        if (!veiculo.getNomeProprietario().equals("Desconhecido(a)") && veiculo.getNumeroDocumentoProprietario().equals("0"))
        {
            cxbProprietarioDesabilitado.setChecked(false);
            cxbProprietarioDesabilitado.performClick();
        }
        if (!veiculo.getNomeCondutor().equals("Desconhecido(a)") && veiculo.getNumeroDocumentoCondutor().equals("0"))

        {
            cxbCondutorDesabilitado.setChecked(false);
            cxbCondutorDesabilitado.performClick();
        }


        if (veiculo.getNomeProprietario() != null
                && veiculo.getCategoriaProprietario() != null
                && veiculo.getNumeroDocumentoProprietario() != null)
        {
            if (veiculo.getNomeProprietario().equals(veiculo.getNomeCondutor())
                    && veiculo.getNumeroDocumentoCondutor().equals(veiculo.getNumeroDocumentoProprietario())
                    && veiculo.getCategoriaProprietario().equals(veiculo.getCategoriaCondutor())
                    && !veiculo.getNomeCondutor().equals("Desconhecido(a)"))
            {

                cxbCondutor_Igual_Proprietario.setChecked(false);
                cxbCondutor_Igual_Proprietario.performClick();
            }
        }

    }

    private void PovoarSpinner(Context ctx)
    {
        ArrayList<String> listaUF = new ArrayList<>();

        for (UF uf : UF.values())
            listaUF.add(uf.getValor());

        spnUF.setAdapter(new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, listaUF));

        if (anos.size() > 0)
            anos.clear();


        int esteAno = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = esteAno; i >= 1920; i--)
        {
            anos.add(Integer.toString(i));
        }
        anos.add(0, "Não Identificado");
        adapterAno = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, anos);

        spnAnoVeiculo.setAdapter(adapterAno);

        spnAnoFabricacao.setAdapter(adapterAno);


        ArrayList<String> tipoVeiculo = new ArrayList<>();

        for (TipoVeiculo tv : TipoVeiculo.values())
            tipoVeiculo.add(tv.getValor());


        spnTipoVeiculo.setAdapter(new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, tipoVeiculo));

        ArrayList<String> corVeiculo = new ArrayList<String>();

        for (Cor cor : Cor.values())
            corVeiculo.add(cor.getValor());


        spnCor.setAdapter(new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, corVeiculo));

        spnAnoVeiculo.setSelection(0);
        spnAnoFabricacao.setSelection(0);



    }

    private void AssociarLayout(View v)
    {
        edtModeloVeiculo = (EditText) v.findViewById(R.id.edt_Modelo_Veiculo);
        edtMarcaVeiculo = (EditText) v.findViewById(R.id.edt_Marca_Veiculo);
        edtPlaca_Numeros = (EditText) v.findViewById(R.id.edt_PlacaNumeros_Envolvido);
        edtPlaca_Letras = (EditText) v.findViewById(R.id.edt_PlacaLetras_Envolvido);
        lstvVeiculos = (ListView) v.findViewById(R.id.lstv_Veiculos);

        edtMunicipio = (EditText) v.findViewById(R.id.edt_Municipio_Veiculo);
        spnUF = (Spinner) v.findViewById(R.id.spn_UF_Placa_Veiculo);

        fabVeiculo = (FloatingActionButton) v.findViewById(R.id.fab_Veiculo);
        fabFotoVeiculo = (FloatingActionButton) v.findViewById(R.id.fab_Foto_Veiculo);
        txvProprietarioNumero = (TextView) v.findViewById(R.id.txv_Proprietario_Documento);
        txvProprietarioNome = (TextView) v.findViewById(R.id.txv_Proprietario_Nome);

        txvCondutorNome = (TextView) v.findViewById(R.id.txv_Condutor_Nome);
        txvCondutorNumero = (TextView) v.findViewById(R.id.txv_Condutor_Documento);

        spnAnoFabricacao = (Spinner) v.findViewById(R.id.spn_Ano_Veiculo_Fabricacao);
        spnAnoVeiculo = (Spinner) v.findViewById(R.id.spn_Ano_Veiculo_Modelo);
        //   spnEnderecoVeiculo = (Spinner) v.findViewById(R.id.spn_EnderecoVeiculo);
        spnTipoVeiculo = (Spinner) v.findViewById(R.id.spn_TipoVeiculo);
        spnCor = (Spinner) v.findViewById(R.id.spn_Cor_Veiculo);

        cxbVeiculoDesconhecido = (CheckBox) v.findViewById(R.id.cxb_Veiculo_Desconhecido);
        rltv_Veiculo = (RelativeLayout) v.findViewById(R.id.rltv_Detalhe_Veiculo);

        addDano = (LinearLayout) v.findViewById(R.id.btn_Add_Dano);


        InputFilter filter = new InputFilter()
        {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend)
            {
                for (int i = start; i < end; i++)
                {
                    if (!Character.isLetter(source.charAt(i)))
                        return "";

                }
                return null;
            }
        };

        edtPlaca_Letras.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3), filter});

        danosTotais = new ArrayList<>();

        lstvDanos = (ListView) v.findViewById(R.id.lstv_Danos_Veiculo);

        rltvProprietario = (RelativeLayout) v.findViewById(R.id.rltv_Proprietario_Condutor);

        edtMarcaVeiculo.setNextFocusForwardId(edtModeloVeiculo.getId());
        edtModeloVeiculo.setNextFocusForwardId(edtPlaca_Letras.getId());

        lstvDanos.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
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
                    magicalCamera.takeFragmentPhoto(GerenciarVeiculoTransito.this);
                    dialog.dismiss();
                }
            });

            imgvGaleria.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    magicalCamera.selectFragmentPicture(GerenciarVeiculoTransito.this, "Selecione Uma Imagem");
                    dialog.dismiss();
                }
            });

        }
    }

    //Desabilita itens da interface para veículos que não possuem placa, ano, etc.
    public void InterfaceVNM(boolean valor)
    {
        VNM = valor;

        edtPlaca_Letras.setText("");
        edtPlaca_Numeros.setText("");
        edtPlaca_Letras.setEnabled(valor);
        edtPlaca_Numeros.setEnabled(valor);

    }
}