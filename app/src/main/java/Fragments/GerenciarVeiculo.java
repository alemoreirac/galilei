package Fragments;

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
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.GerenciarDano;
import com.example.pefoce.peritolocal.ManterPericia;
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
import Enums.Transito.TercoDano;
import Enums.Transito.TipoCNH;
import Enums.Transito.TipoDano;
import Enums.Transito.TipoVeiculo;
import Model.Transito.ColisaoTransito;
import Model.Dano;
import Model.Transito.DanoVeiculo;
import Model.Transito.EnderecoVeiculo;
import Model.Foto;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;
import Util.BuscadorEnum;
import Util.ViewUtil;

public class GerenciarVeiculo extends android.support.v4.app.Fragment implements Step
{

    OcorrenciaTransito ocorrenciaTransitoVeiculo;
    Ocorrencia ocorrencia;
    ListView lstvVeiculos = null;


    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;

    CheckBox cxbVeiculoDesconhecido = null;

    List<Dano> danosTotais = null;
    ListView lstvDanos = null;
    ArrayList<Veiculo> veiculosModel = null;
    Spinner spnTipoVeiculo = null;

    AdapterVeiculo adp = null;

    Spinner spnCor = null;
    Spinner spnAnoVeiculo = null;
    Spinner spnAnoFabricacao = null;

    EditText edtModeloVeiculo = null;
    EditText edtMarcaVeiculo = null;
    EditText edtPlaca_Letras = null;
    EditText edtPlaca_Numeros = null;


    TextView txvProprietarioNome = null;
    TextView txvProprietarioNumero = null;

    TextView txvCondutorNome = null;
    TextView txvCondutorNumero = null;


    Button btnSalvarVeiculo = null;
    Button btnCancelarVeiculo = null;
    Button btnSelecionarOk = null;

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

    public GerenciarVeiculo()
    {

    }

    public static GerenciarVeiculo newInstance()
    {
        GerenciarVeiculo fragment = new GerenciarVeiculo();

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
        //((ManterPericia) getActivity()).toolbar.setTitle("Veículos");
        ((ManterPericia) getActivity()).txvToolbarTitulo.setText("Veículos");

        View view = getActivity().getCurrentFocus();
        if (view != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        AssociarLayout(mView);
        PovoarSpinner(getContext());
        AssociarEventos();

        ViewUtil.modifyAll(rltv_Veiculo, false);
        ViewUtil.modifyAll(rltvProprietario, false);

        ocorrenciaTransitoVeiculo = ((ManterPericia) getActivity()).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoVeiculo.getOcorrenciaID());

        ocorrenciaVeiculos = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ?", ocorrenciaTransitoVeiculo.getId().toString());

        veiculosModel = new ArrayList<>();

        for (OcorrenciaTransitoVeiculo ov : ocorrenciaVeiculos)
        {
            if (ov.getVeiculo() != null)
                veiculosModel.add(ov.getVeiculo());
        }

        adp = new AdapterVeiculo(veiculosModel, getActivity());

//        if(veiculosModel.size()==0)
//        {
        ViewUtil.modifyAll(rltv_Veiculo, false);
        ViewUtil.modifyAll(rltvProprietario, false);
//        }


        Bundle bd = getArguments();


        lstvVeiculos.setAdapter(adp);


//        ocorrenciaEnderecos = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class,"ocorrencia_transito = ?",ocorrenciaTransitoVeiculo.getId().toString());
//
//        if(ocorrenciaEnderecos == null )
//        {
//
//        }
//        else
//        {
//            if(ocorrenciaEnderecos.size() == 0)
//            {
//
//            }
//        }

        if (bd.getBoolean("DiretoParaVeiculo"))
        {

            Veiculo veiculoIndex = Veiculo.findById(Veiculo.class, bd.getLong("VeiculoId"));


            lstvVeiculos.performItemClick(lstvVeiculos, BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculoIndex), lstvVeiculos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculoIndex)));

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


        try
        {
            veiculo.setAnoModelo(Integer.parseInt(spnAnoVeiculo.getSelectedItem().toString()));
            veiculo.setAnoFabricacao(Integer.parseInt(spnAnoFabricacao.getSelectedItem().toString()));
        } catch (Exception e)
        {
            veiculo.setAnoModelo(0);
            veiculo.setAnoFabricacao(0);
        }

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

        if (veiculo.getNomeCondutor() != null)
            txvCondutorNome.setText(veiculo.getNomeCondutor());

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
        //      spnEnderecoVeiculo.setSelection(BuscadorEnum.getIndex(spnEnderecoVeiculo,enderecoVeiculo.getEnderecoTransito().getEnderecoTransito().getDescricao()));


        lstvDanos.setAdapter(adapterDano);
    }

    public void LimparCampos()
    {
        spnAnoVeiculo.setSelection(0);
        spnAnoFabricacao.setSelection(0);
        spnCor.setSelection(0);
        edtModeloVeiculo.setText("");
        edtMarcaVeiculo.setText("");
        spnTipoVeiculo.setSelection(0);
        edtPlaca_Letras.setText("");
        edtPlaca_Numeros.setText("");
        //  spnEnderecoVeiculo.setSelection(0);
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

        btnSalvarVeiculo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (!cxbVeiculoDesconhecido.isChecked())
                {
                    if (edtPlaca_Letras.getText().toString().length() < 3)
                    {
                        edtPlaca_Letras.setTextColor(Color.RED);
                        Toast.makeText(getContext(), "A placa deve ter 3 letras!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    if (edtPlaca_Numeros.getText().toString().length() < 4)
                    {
                        edtPlaca_Numeros.setTextColor(Color.RED);
                        Toast.makeText(getContext(), "A placa deve ter 4 dígitos!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                SalvarVeiculo();

                Intent it = new Intent(view.getContext(), ManterPericia.class);
                it.putExtra("OcorrenciaId", ocorrenciaTransitoVeiculo.getId());
                it.putExtra("FragmentPicker", "Veículo");
                startActivity(it);
            }
        });

        btnCancelarVeiculo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //  Intent it = new Intent(v.getContext(),MainActivity.class);
                Intent it = new Intent(view.getContext(), ManterPericia.class);
                //it.putExtra("PeritoId",ocorrenciaTransitoColisao.getPerito().getId());
                it.putExtra("OcorrenciaId", ocorrenciaTransitoVeiculo.getId());
                it.putExtra("FragmentPicker", "Veículo");
                startActivity(it);
            }
        });


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

            ;
        });

        cxbVeiculoDesconhecido.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (cxbVeiculoDesconhecido.isChecked())
                {
                    edtPlaca_Numeros.setEnabled(false);
                    edtPlaca_Letras.setEnabled(false);
                    edtPlaca_Letras.setText("");
                    edtPlaca_Numeros.setText("");
                } else
                {
                    edtPlaca_Numeros.setEnabled(true);
                    edtPlaca_Letras.setEnabled(true);
                }
            }
        });

        rltvProprietario.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_proprietario);
                dialog.setCanceledOnTouchOutside(false);
                dialog.setTitle("Definir Proprietário e Condutor");
                dialog.show();


                AssociarViews_Proprietario(dialog);
            }
        });

        lstvVeiculos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                veiculo = veiculosModel.get(position);
                view.setSelected(true);

                LimparCampos();

                ViewUtil.modifyAll(rltv_Veiculo, true);
                ViewUtil.modifyAll(rltvProprietario, true);

                try
                {
                    ocorrenciaVeiculo = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class, "ocorrencia_transito = ? , veiculo = ?", ocorrenciaTransitoVeiculo.getId().toString(), veiculo.getId().toString()).get(0);
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
                                } catch (Exception e)
                                {

                                }


                                adp.remove(ocorrenciaVeiculo.getVeiculo());

                                ocorrenciaVeiculo.getVeiculo().delete();

                                ocorrenciaVeiculo.delete();

                                ocorrenciaVeiculos.remove(position);

                                ViewUtil.modifyAll(rltv_Veiculo, false);
                                ViewUtil.modifyAll(rltvProprietario, false);


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

                //lstvVeiculos.setItemChecked(0,true);
                //lstvVeiculos.performItemClick(lstvVeiculos, 0, lstvVeiculos.getItemIdAtPosition(0));
                //     return;
                //5

                veiculo = new Veiculo();
                ocorrenciaVeiculo = new OcorrenciaTransitoVeiculo();

                veiculo.save();

                ocorrenciaVeiculo.setVeiculo(veiculo);
                ocorrenciaVeiculo.setOcorrenciaTransito(ocorrenciaTransitoVeiculo);

                ocorrenciaVeiculo.save();

                ocorrenciaVeiculos.add(ocorrenciaVeiculo);

                adp.add(veiculo);
                adp.notifyDataSetChanged();

                LimparCampos();
                ViewUtil.modifyAll(rltv_Veiculo, true);
                ViewUtil.modifyAll(rltvProprietario, true);

                lstvVeiculos.performItemClick(lstvVeiculos, BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculo), lstvVeiculos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoVeiculo(veiculosModel, veiculo)));

            }
        });

        fabFotoVeiculo.setOnClickListener(new View.OnClickListener()
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
                magicalPermissions = new MagicalPermissions(GerenciarVeiculo.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                TipoFotoDialog tfd = new TipoFotoDialog();


            }
        });


    }

    private void AssociarViews_Proprietario(final Dialog d)
    {


        final EditText edtNomeProprietario = (EditText) d.findViewById(R.id.edt_NomeProprietario);
        final EditText edtDocumentoProprietario = (EditText) d.findViewById(R.id.edt_ProprietarioCNH);
        final Spinner  spnTipoCNHProprietario = (Spinner) d.findViewById(R.id.spn_CNH_Proprietario_720);
        final CheckBox cxbProprietarioDesabilitado = (CheckBox) d.findViewById(R.id.cxb_Proprietario_Nao_Habilitado);
        final CheckBox cxbProprietarioDesconhecido = (CheckBox) d.findViewById(R.id.cxb_Proprietario_Nao_Identificado);
        final CheckBox cxbProprietario_Igual_Condutor = (CheckBox) d.findViewById(R.id.cxb_Proprietario_igual_Condutor);

        final EditText edtNomeCondutor = (EditText) d.findViewById(R.id.edt_Nome_Condutor);
        final EditText edtDocumentoCondutor = (EditText) d.findViewById(R.id.edt_CondutorCNH);
        final Spinner  spnTipoCNHCondutor = (Spinner) d.findViewById(R.id.spn_CNH_Condutor_720);
        final CheckBox cxbCondutorDesabilitado = (CheckBox) d.findViewById(R.id.cxb_Condutor_Nao_Habilitado);
        final CheckBox cxbCondutorDesconhecido = (CheckBox) d.findViewById(R.id.cxb_Condutor_Nao_Identificado);
        final TextView txvTituloCondutorNome = (TextView) d.findViewById(R.id.txv_Nome_Condutor);
        final TextView txvTituloCondutorCNH = (TextView) d.findViewById(R.id.txv_CNH_Condutor);
        final TextView txvTituloCondutorCategoria = (TextView) d.findViewById(R.id.txv_Categoria_Condutor);
        final TextView txvCondutorProprietario= (TextView) d.findViewById(R.id.txv_Condutor_Proprietario);

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
                        && !cxbProprietarioDesconhecido.isChecked())
                {
                    edtNomeProprietario.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "Nome do(a) Proprietário(a) muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edtDocumentoProprietario.getText().toString().length() < 3
                        && (!cxbProprietarioDesabilitado.isChecked() && !cxbProprietarioDesconhecido.isChecked())
                    //&& !edtDocumentoProprietario.getText().toString().equals("-")
                        )
                {
                    edtDocumentoProprietario.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "O Nº Doc. do(a) Proprietário(a) é muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }

                if (edtNomeCondutor.getText().toString().length() < 3
                        && !cxbCondutorDesconhecido.isChecked()
                        && !cxbProprietario_Igual_Condutor.isChecked())
                {
                    edtNomeCondutor.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "Nome do(a) Condutor(a) muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }
                if (edtDocumentoCondutor.getText().toString().length() < 3
                        && (!cxbCondutorDesabilitado.isChecked() && !cxbCondutorDesconhecido.isChecked())
                        && !cxbProprietario_Igual_Condutor.isChecked())
                {
                    edtDocumentoCondutor.setTextColor(Color.RED);
                    Toast.makeText(v.getContext(), "O Nº Doc. do(a) Condutor(a) é muito curto!", Toast.LENGTH_LONG).show();
                    return;
                }

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
                }

                if(cxbProprietario_Igual_Condutor.isChecked())
                {
                    veiculo.setNomeCondutor(veiculo.getNomeProprietario());
                    veiculo.setCategoriaCondutor(veiculo.getCategoriaProprietario());
                    veiculo.setNumeroDocumentoCondutor(veiculo.getNumeroDocumentoProprietario());

                }
                else
                {
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
                    }
                }


                txvProprietarioNome.setText(veiculo.getNomeProprietario());
                txvProprietarioNumero.setText(veiculo.getNumeroDocumentoProprietario() + " Cat. " + veiculo.getCategoriaProprietario().toString());

                txvCondutorNome.setText(veiculo.getNomeCondutor());
                txvCondutorNumero.setText(veiculo.getNumeroDocumentoCondutor() + " Cat. " + veiculo.getCategoriaCondutor().toString());


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

        edtDocumentoProprietario.addTextChangedListener(new TextWatcher()
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
                if (cxbProprietario_Igual_Condutor.isChecked())
                {
                    edtDocumentoCondutor.setEnabled(true);
                    edtDocumentoCondutor.setText(s.toString());
                    edtDocumentoCondutor.setEnabled(false);
                }

            }
        });


        edtNomeProprietario.addTextChangedListener(new TextWatcher()
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
                if (cxbProprietario_Igual_Condutor.isChecked())
                {
                    edtNomeCondutor.setEnabled(true);
                    edtNomeCondutor.setText(s.toString());
                    edtNomeCondutor.setEnabled(false);
                }
            }
        });

//        if(veiculo.getNomeProprietario()!= null
//                && veiculo.getCategoriaProprietario() != null
//                && veiculo.getNumeroDocumentoProprietario() != null
//                && veiculo.getCategoriaCondutor() != null  )

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


        cxbProprietario_Igual_Condutor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                if (cxbProprietario_Igual_Condutor.isChecked())
//                {
//                    edtNomeCondutor.setText(edtNomeProprietario.getText().toString());
//                    edtDocumentoCondutor.setText(edtDocumentoProprietario.getText().toString());
//                    spnTipoCNHCondutor.setSelection(spnTipoCNHProprietario.getSelectedItemPosition());
//                    edtNomeCondutor.setEnabled(false);
//                    edtDocumentoCondutor.setEnabled(false);
//                    spnTipoCNHCondutor.setEnabled(false);
//                    cxbCondutorDesabilitado.setEnabled(false);
//                    cxbCondutorDesconhecido.setEnabled(false);
//                } else
//                {
//                    if(cxbCondutorDesconhecido.isChecked())
//                    {
//                        edtDocumentoCondutor.setEnabled(false);
//                        edtNomeCondutor.setEnabled(false);
//                        spnTipoCNHCondutor.setEnabled(false);
//                    }
//                    else
//                    {
//                        if(cxbCondutorDesabilitado.isChecked())
//                        {
//                            edtDocumentoCondutor.setEnabled(false);
//                            spnTipoCNHCondutor.setEnabled(false);
//                        }
//                        else
//                        {
//                            edtDocumentoCondutor.setEnabled(true);
//                            edtNomeCondutor.setEnabled(true);
//                            spnTipoCNHCondutor.setEnabled(true);
//                        }
//                    }
//                }
//
//                cxbCondutorDesabilitado.setEnabled(true);
//                cxbCondutorDesconhecido.setEnabled(true);

                if (cxbProprietario_Igual_Condutor.isChecked())
                {
                    cxbCondutorDesabilitado.setVisibility(View.INVISIBLE);
                    cxbCondutorDesconhecido.setVisibility(View.INVISIBLE);
                    edtDocumentoCondutor.setVisibility(View.INVISIBLE);
                    edtNomeCondutor.setVisibility(View.INVISIBLE);
                    spnTipoCNHCondutor.setVisibility(View.INVISIBLE);
                    txvTituloCondutorNome.setVisibility(View.INVISIBLE);
                    txvTituloCondutorCNH.setVisibility(View.INVISIBLE);
                    txvTituloCondutorCategoria.setVisibility(View.INVISIBLE);

                    txvCondutorProprietario.setVisibility(View.VISIBLE);
                } else
                {
                    cxbCondutorDesabilitado.setChecked(false);
                    cxbCondutorDesconhecido.setChecked(false);
                    edtDocumentoCondutor.setText("");
                    edtNomeCondutor.setText("");
                    spnTipoCNHCondutor.setSelection(0);

                    cxbCondutorDesabilitado.setVisibility(View.VISIBLE);
                    cxbCondutorDesconhecido.setVisibility(View.VISIBLE);
                    edtDocumentoCondutor.setVisibility(View.VISIBLE);
                    edtNomeCondutor.setVisibility(View.VISIBLE);
                    spnTipoCNHCondutor.setVisibility(View.VISIBLE);
                    txvTituloCondutorNome.setVisibility(View.VISIBLE);
                    txvTituloCondutorCNH.setVisibility(View.VISIBLE);
                    txvTituloCondutorCategoria.setVisibility(View.VISIBLE);

                    txvCondutorProprietario.setVisibility(View.INVISIBLE);
                }
            }
        });


        //   cxbProprietario_Igual_Condutor.setOnClickListener(new View.OnClickListener() {
//
        //       @Override
        //       public void onClick(View v)
        //       {
        //           if (((CheckBox) v).isChecked())
        //           {
        //
        //           }
        //           else
        //           {
        //
//
        //           }
        //       }
        //  });
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


        //   cxbCondutorDesabilitado.setOnClickListener(new View.OnClickListener() {
//
        //       @Override
        //       public void onClick(View v)
        //       {
        //           if (((CheckBox) v).isChecked()) {
        //
        //           }
        //           else
        //           {
        //
        //           }
        //       }
        //   });
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
                    && veiculo.getCategoriaProprietario().equals(veiculo.getCategoriaCondutor()))
            {

                cxbProprietario_Igual_Condutor.setChecked(false);
                cxbProprietario_Igual_Condutor.performClick();
            }
        }

    }

    private void PovoarSpinner(Context ctx)
    {
        if (anos.size() >0)
            anos.clear();

//        {
            int esteAno = Calendar.getInstance().get(Calendar.YEAR);
            for (int i = esteAno; i >= 1920; i--)
            {
                anos.add(Integer.toString(i));
            }
            anos.add(0, "Não Identificado");
            adapterAno = new ArrayAdapter<>(ctx, android.R.layout.simple_spinner_dropdown_item, anos);

            spnAnoVeiculo.setAdapter(adapterAno);

            spnAnoFabricacao.setAdapter(adapterAno);
     //   }
        //     if(ocorrenciaEnderecos != null)
        //     {
        //         for(int i = 0; i<ocorrenciaEnderecos.size();i++)
        //             enderecosTransito.add(ocorrenciaEnderecos.get(i).getEnderecoTransito());
        //
        //         spnEnderecoVeiculo.setAdapter(new ArrayAdapter<EnderecoTransito>(ctx,android.R.layout.simple_spinner_dropdown_item,enderecosTransito));
        //     }
        //
        //     if(enderecoVeiculo != null)
        //          spnEnderecoVeiculo.setSelection(enderecosTransito.indexOf(enderecoVeiculo.getEnderecoTransito()));


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
        //   spnEnderecoVeiculo.setSelection(0);

    }

    private void AssociarLayout(View v)
    {
        edtModeloVeiculo = (EditText) v.findViewById(R.id.edt_Modelo_Veiculo);
        edtMarcaVeiculo = (EditText) v.findViewById(R.id.edt_Marca_Veiculo);
        edtPlaca_Numeros = (EditText) v.findViewById(R.id.edt_PlacaNumeros_Envolvido);
        edtPlaca_Letras = (EditText) v.findViewById(R.id.edt_PlacaLetras_Envolvido);
        lstvVeiculos = (ListView) v.findViewById(R.id.lstv_Veiculos);

        fabVeiculo = (FloatingActionButton) v.findViewById(R.id.fab_Veiculo);
        fabFotoVeiculo = (FloatingActionButton) v.findViewById(R.id.fab_Foto_Veiculo);
        txvProprietarioNumero = (TextView) v.findViewById(R.id.txv_Proprietario_Documento);
        txvProprietarioNome = (TextView) v.findViewById(R.id.txv_Proprietario_Nome);

        txvCondutorNome = (TextView) v.findViewById(R.id.txv_Condutor_Nome);
        txvCondutorNumero = (TextView) v.findViewById(R.id.txv_Condutor_Documento);

        spnAnoFabricacao = (Spinner) v.findViewById(R.id.spn_Ano_Veiculo_Fabricacao);
        spnAnoVeiculo = (Spinner) v.findViewById(R.id.spn_Ano_Veiculo);
        //   spnEnderecoVeiculo = (Spinner) v.findViewById(R.id.spn_EnderecoVeiculo);
        spnTipoVeiculo = (Spinner) v.findViewById(R.id.spn_TipoVeiculo);
        spnCor = (Spinner) v.findViewById(R.id.spn_CorVeiculo);

        btnSalvarVeiculo = (Button) v.findViewById(R.id.btn_Salvar_Veiculo);

        btnCancelarVeiculo = (Button) v.findViewById(R.id.btn_Cancelar_Veiculo);

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
                    {
                        return "";
                    }
                }
                return null;
            }
        };

        edtPlaca_Letras.setFilters(new InputFilter[]{new InputFilter.AllCaps(), new InputFilter.LengthFilter(3), filter});

        danosTotais = new ArrayList<>();

        lstvDanos = (ListView) v.findViewById(R.id.lstv_Danos_Veiculo);

        adapterDano = new ArrayAdapter<Dano>(v.getContext(), android.R.layout.simple_spinner_dropdown_item, danosTotais);

        lstvDanos.setAdapter(adapterDano);

        adapterDano.notifyDataSetChanged();

        rltvProprietario = (RelativeLayout) v.findViewById(R.id.rltv_Proprietario_Condutor);
    }


    public class DanoDialog
    {

        CheckBox cxbSelecionarVarios = null;
        ArrayAdapter<Dano> adapterDano_Dialog = null;
        Context context;
        CheckBox cxbCompatibilidade = null;
        Spinner spnTipoDano = null;
        Spinner spnTercoDano = null;
        ListView lstvDanosDialog = null;
        Button btnAddDano = null;
        Button btnSalvarDano = null;
        Button btnLimparDanos = null;

        ArrayList<String> setores = new ArrayList<>();

        public DanoDialog()
        {
            context = getContext();

            final Dialog dialog = new Dialog(context);
            //  dialog.setContentView(R.layout.dano_veiculo);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle("Danos do Veículo");
            dialog.show();

            if (danosTotais.size() > 0)
            {
                for (Dano d : danosTotais)
                {
                    if (!setores.contains(d.setor.toString()))

                        setores.add(d.setor.toString());
                }

                for (String s : setores)
                {
                    switch (s)
                    {
                        case "AAD":
                            ((TextView) dialog.findViewById(R.id.txv_AAD)).setTextColor(Color.GREEN);
                            break;
                        case "LAD":
                            ((TextView) dialog.findViewById(R.id.txv_LAD)).setTextColor(Color.GREEN);
                            break;
                        case "LMD":
                            ((TextView) dialog.findViewById(R.id.txv_LMD)).setTextColor(Color.GREEN);
                            break;
                        case "LPD":
                            ((TextView) dialog.findViewById(R.id.txv_LPD)).setTextColor(Color.GREEN);
                            break;
                        case "APD":
                            ((TextView) dialog.findViewById(R.id.txv_APD)).setTextColor(Color.GREEN);
                            break;
                        case "PPD":
                            ((TextView) dialog.findViewById(R.id.txv_PPD)).setTextColor(Color.GREEN);
                            break;
                        case "PPM":
                            ((TextView) dialog.findViewById(R.id.txv_PPM)).setTextColor(Color.GREEN);
                            break;
                        case "PPE":
                            ((TextView) dialog.findViewById(R.id.txv_PPE)).setTextColor(Color.GREEN);
                            break;
                        case "APE":
                            ((TextView) dialog.findViewById(R.id.txv_APE)).setTextColor(Color.GREEN);
                            break;
                        case "LPE":
                            ((TextView) dialog.findViewById(R.id.txv_LPE)).setTextColor(Color.GREEN);
                            break;
                        case "LME":
                            ((TextView) dialog.findViewById(R.id.txv_LME)).setTextColor(Color.GREEN);
                            break;
                        case "LAE":
                            ((TextView) dialog.findViewById(R.id.txv_LAE)).setTextColor(Color.GREEN);
                            break;
                        case "AAE":
                            ((TextView) dialog.findViewById(R.id.txv_AAE)).setTextColor(Color.GREEN);
                            break;
                        case "PAE":
                            ((TextView) dialog.findViewById(R.id.txv_PAE)).setTextColor(Color.GREEN);
                            break;
                        case "PAM":
                            ((TextView) dialog.findViewById(R.id.txv_PAM)).setTextColor(Color.GREEN);
                            break;
                        case "PAD":
                            ((TextView) dialog.findViewById(R.id.txv_PAD)).setTextColor(Color.GREEN);
                            break;
                    }
                }
            }

            btnSelecionarOk.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    cxbSelecionarVarios.setChecked(false);
                    cxbSelecionarVarios.setEnabled(true);
                    btnSelecionarOk.setVisibility(View.INVISIBLE);

                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_dano_detalhe);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.setOnKeyListener(new DialogInterface.OnKeyListener()
                    {
                        @Override
                        public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
                        {
                            // Prevent dialog close on back press button
                            return keyCode == KeyEvent.KEYCODE_BACK;
                        }
                    });

                    dialog.setTitle("Danos Múltiplos");
                    dialog.show();
                }

                ;

            });
        }

        public void Selecionar_Angulo(final View viewAngulo)
        {
            ArrayList<String> setoresSelecionados = new ArrayList<>();
            TextView txv = (TextView) viewAngulo;


            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog_dano_detalhe);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener()
            {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
                {
                    // Prevent dialog close on back press button
                    return keyCode == KeyEvent.KEYCODE_BACK;
                }
            });

            if (cxbSelecionarVarios.isChecked())
            {
                if (txv.getCurrentTextColor() != Color.GREEN)
                {
                    setoresSelecionados.add(txv.getText().toString());
                    txv.setTextColor(Color.GREEN);
                } else
                {
                    Toast.makeText(getContext(), "Selecione um ângulo que não teve danos!", Toast.LENGTH_LONG).show();
                }

            } else
            {
                if (txv.getTextColors().equals(Color.GREEN))
                {
                    txv.setTextColor(context.getResources().getColor(R.color.DefaultTextColor));
                } else
                {
                    txv.setTextColor(Color.GREEN);
                }

                dialog.setTitle("Dano " + (((TextView) viewAngulo).getText().toString()));
                dialog.show();
            }


            Carregar_DanoDetalhe(dialog, viewAngulo);


            btnSalvarDano.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    for (int i = 0; i < adapterDano_Dialog.getCount(); i++)
                    {
                        if (!danosTotais.contains(adapterDano_Dialog.getItem(i)))
                            danosTotais.add(adapterDano_Dialog.getItem(i));
                    }
                    if (adapterDano_Dialog.getCount() == 0)
                        ((TextView) viewAngulo).setTextColor(context.getResources().getColor(R.color.DefaultTextColor));

                    adapterDano_Dialog.clear();

                    dialog.dismiss();
                }

                ;


            });

            btnAddDano.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    if (cxbSelecionarVarios.isChecked())
                    {
                        for (String set : setores)
                        {
                            Dano d = new Dano();
                            d.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                            d.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                            d.setCompatibilidade(cxbCompatibilidade.isChecked());
                            d.setSetor(BuscadorEnum.BuscarSetorDano(set));
                            adapterDano_Dialog.add(d);
                        }
                        return;
                    }
                    Dano d = new Dano();

                    d.setTerco(BuscadorEnum.BuscarTercoDano(spnTercoDano.getSelectedItem().toString()));
                    d.setTipo(BuscadorEnum.BuscarTipoDano(spnTipoDano.getSelectedItem().toString()));
                    d.setCompatibilidade(cxbCompatibilidade.isChecked());
                    d.setSetor(BuscadorEnum.BuscarSetorDano(((TextView) viewAngulo).getText().toString()));

                    //    if(adapterDano_Dialog.getPosition(d) == -1)
                    adapterDano_Dialog.add(d);

                    //    else
                    //        Toast.makeText(dialog.getContext(),"Não pode adicionar dano duplicado!",Toast.LENGTH_LONG).show();

                }

                ;
            });

            btnLimparDanos.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    for (int i = 0; i < adapterDano_Dialog.getCount(); i++)

                        danosTotais.remove(adapterDano_Dialog.getItem(i));

                    adapterDano_Dialog.clear();
                }

                ;

            });

            lstvDanosDialog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
            {
                @Override
                public boolean onItemLongClick(AdapterView<?> arg0, View v, final int position, long id)
                {
                    new AlertDialog.Builder(context)
                            .setTitle("Deletar Dano")
                            .setMessage("Você deseja deletar este Dano?")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                            {
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    danosTotais.remove(adapterDano_Dialog.getItem(position));
                                    adapterDano_Dialog.remove(adapterDano_Dialog.getItem(position));
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

            adapterDano_Dialog.notifyDataSetChanged();
            lstvDanosDialog.setAdapter(adapterDano_Dialog);

        }


        private void Carregar_DanoDetalhe(Dialog dialog, View view)
        {
            lstvDanosDialog = (ListView) dialog.findViewById(R.id.lstv_dialog_ListDanos);
            btnAddDano = (Button) dialog.findViewById(R.id.btn_dialog_AddDano);
            btnSalvarDano = (Button) dialog.findViewById(R.id.btn_dialog_SalvarDano_Detalhe);
            btnLimparDanos = (Button) dialog.findViewById(R.id.btn_dialog_LimparDano);
            spnTipoDano = (Spinner) dialog.findViewById(R.id.spn_dialog_TipoDano);
            spnTercoDano = (Spinner) dialog.findViewById(R.id.spn_dialog_TercoDano);
            cxbCompatibilidade = (CheckBox) dialog.findViewById(R.id.cxb_dialog_CompatibilidadeDano);


            ArrayList<String> tipoDano = new ArrayList<String>();

            for (TipoDano td : TipoDano.values())
            {
                tipoDano.add(td.getValor());
            }

            spnTipoDano.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, tipoDano));

            ArrayList<String> tercoDano = new ArrayList<String>();

            for (TercoDano td : TercoDano.values())
            {
                tercoDano.add(td.getValor());
            }

            spnTercoDano.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, tercoDano));

            ArrayList<Dano> danos = new ArrayList<Dano>();

            for (Dano d : danosTotais)
            {
                if (d.getSetor() == (BuscadorEnum.BuscarSetorDano(((TextView) view).getText().toString())))
                {
                    danos.add(d);
                }
            }

            adapterDano_Dialog = new ArrayAdapter<Dano>(context, android.R.layout.simple_spinner_dropdown_item, danos);

        }


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
                    magicalCamera.takeFragmentPhoto(GerenciarVeiculo.this);
                    dialog.dismiss();
                }
            });

            imgvGaleria.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    magicalCamera.selectFragmentPicture(GerenciarVeiculo.this, "Selecione Uma Imagem");
                    dialog.dismiss();
                }
            });


        }


    }
}