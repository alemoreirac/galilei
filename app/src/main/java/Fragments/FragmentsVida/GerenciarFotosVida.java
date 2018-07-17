package Fragments.FragmentsVida;

import android.Manifest;
import android.app.AlertDialog;
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
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericiaVida;
import com.example.pefoce.peritolocal.R;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.squareup.picasso.Picasso;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import Adapters.AdapterGaleria;
import Dialogs.TipoFotoDialog;
import Enums.CategoriaFoto;
import Model.Foto;
import Model.Ocorrencia;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Util.BuscadorEnum;
import Util.ViewUtil;

public class GerenciarFotosVida extends android.support.v4.app.Fragment implements Step
{
    ListView lstvFOtos;
    FloatingActionButton fabFotos;
    int lastPosition;
    ImageView imgvFotoDetalhe;
    EditText edtDetalhe;
    Spinner spnCategoriaFoto;
    MagicalPermissions magicalPermissions;
    MagicalCamera magicalCamera;
    RelativeLayout rltvFotos;
    ArrayList<Foto> fotosModel;
    List<OcorrenciaVidaFoto> fotosOcorrenciaVida;
    Foto foto;
    File folder = null;
    OcorrenciaVidaFoto ocorrenciaVidaFoto;
    OcorrenciaVida ocorrenciaVida;
    Ocorrencia ocorrencia;
    String pathImagem;
    final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;
    AdapterGaleria adapterGaleria;

    ArrayList<String> categorias;


    public GerenciarFotosVida()
    {
    }

    public static GerenciarFotosVida newInstance(String param1, String param2)
    {
        GerenciarFotosVida fragment = new GerenciarFotosVida();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View mView = inflater.inflate(R.layout.fragment_gerenciar_fotos_vida, container, false);

        if (mView != null)
        {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mView.getWindowToken(), 0);
        }
        return mView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);

        //this is for rotate picture in this method
        //magicalCamera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_180);
//if you need save your bitmap in device use this method and return the path if you need this
        //You need to send, the bitmap picture, the photo name, the directory name, the picture type, and autoincrement photo name if           //you need this send true, else you have the posibility or realize your standard name for your pictures.
        pathImagem = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), "imagem_pericia", "Imagens " + ocorrenciaVida.getId().toString(), MagicalCamera.JPEG, true);

        if (pathImagem != null)
        {
            imgvFotoDetalhe.setImageBitmap(magicalCamera.getPhoto());

            File newFile = new File(pathImagem);

            Picasso.with(getContext()).load(new File(pathImagem)).error(R.drawable.placeholder_error).into(imgvFotoDetalhe);

            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia();

            switch (spnCategoriaFoto.getSelectedItem().toString())
            {
                case "Endereços":
                    newPath += "/Fotos_Enderecos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_endereco";
                    break;

                case "Veículos":
                    newPath += "/Fotos_Veiculos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_veiculo";
                    break;

                case "Local Ocorrência":
                    newPath += "/Fotos_Local_Ocorrencia/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_local_ocorrencia";
                    break;
                case "Vestígios":
                    newPath += "/Fotos_Vestigios/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_vestigio";

                    break;
                case "Envolvidos":
                    newPath += "/Fotos_Envolvidos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_envolvido";
                    break;
                case "Outros":
                    newPath += "/Fotos_Outros/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_outros";
                    break;
                default:
                    newPath += "foto";
                    break;
            }

            newPath += DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";

            if (newFile.renameTo(new File(newPath)))
            {
                foto = new Foto(edtDetalhe.getText().toString(), newPath);
                pathImagem = newPath;
            } else
                foto = new Foto(edtDetalhe.getText().toString(), pathImagem);

            foto.save();
            ocorrenciaVidaFoto = new OcorrenciaVidaFoto();
            ocorrenciaVidaFoto.setFoto(foto);
            ocorrenciaVidaFoto.setOcorrenciaVida(ocorrenciaVida);
            ocorrenciaVidaFoto.save();
            fotosOcorrenciaVida.add(ocorrenciaVidaFoto);
            adapterGaleria.add(foto);
            adapterGaleria.notifyDataSetChanged();

            if (pathImagem != null)
                Toast.makeText(getActivity(), "Foto salva no caminho: " + pathImagem, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_LONG).show();

            lstvFOtos.performItemClick(lstvFOtos, BuscadorEnum.PegarPosicaoFoto(fotosModel, foto), lstvFOtos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoFoto(fotosModel, foto)));

        }

        ViewUtil.modifyAll(rltvFotos, true);
    }


    public void AssociarEventos()
    {
        fabFotos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //TODO: refazer chekc permission utilizando o auto permission do android arsenal ou apenas implementar a negativa da permissão//
                String[] permissions = new String[]{
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                magicalPermissions = new MagicalPermissions(GerenciarFotosVida.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                if (foto != null)
                    SalvarFoto();

                foto = new Foto();

                LimparCampos();

                TipoFotoDialog.show(GerenciarFotosVida.this, getActivity(), magicalCamera);

            }
        });

        lstvFOtos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                if (lastPosition != -1 && lastPosition != position)
                {
                    try
                    {
                        ocorrenciaVidaFoto = OcorrenciaVidaFoto.find(OcorrenciaVidaFoto.class, "foto = ?", foto.getId().toString()).get(0);
                    } catch (Exception e)
                    {
                        ocorrenciaVidaFoto = new OcorrenciaVidaFoto();
                    }
                    SalvarFoto();
                }

                lastPosition = position;

                foto = fotosModel.get(position);

                LimparCampos();

                ViewUtil.modifyAll(rltvFotos, true);


                pathImagem = foto.getArquivo();
                try
                {
                    ocorrenciaVidaFoto = OcorrenciaVidaFoto.find(OcorrenciaVidaFoto.class, "foto = ?", foto.getId().toString()).get(0);
                } catch (Exception e)
                {
                    ocorrenciaVidaFoto = new OcorrenciaVidaFoto();
                }

                CarregarFoto();
            }

        });

        lstvFOtos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
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

                builder.setTitle("Deletar Foto")
                        .setMessage("Você deseja deletar esta foto?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface dialog, int which)
                            {
                                OcorrenciaVidaFoto ocorrenciaFotoDelete = fotosOcorrenciaVida.get(position);

                                adapterGaleria.remove(ocorrenciaFotoDelete.getFoto());

                                fotosOcorrenciaVida.remove(ocorrenciaFotoDelete);

                                ocorrenciaFotoDelete.getFoto().delete();
                                ocorrenciaFotoDelete.delete();
                                LimparCampos();
                                Toast.makeText(getActivity(), "Foto Deletada com sucesso!", Toast.LENGTH_LONG).show();
                                adapterGaleria.notifyDataSetChanged();
                                ViewUtil.modifyAll(rltvFotos, false);
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

//        btnCancelarFoto.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                Picasso.with(getContext()).load(new File(foto.getArquivo())).error(R.drawable.placeholder_error).placeholder(R.drawable.placeholder).into(imgvFotoDetalhe);
//                edtDetalhe.setText(foto.getDescricao());
//            }
//        });
//
//        btnSalvarFoto.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                SalvarFoto();
//            }
//        });

    }

    private void SalvarFoto()
    {
        if (!foto.getCategoriaFoto().getValor().equals(CategoriaFoto.DESENHO.getValor()))
            foto.setCategoriaFoto(BuscadorEnum.BuscarCategoriaFoto(spnCategoriaFoto.getSelectedItem().toString()));

        if (foto.getCategoriaFoto() != null
                && !foto.getCategoriaFoto().getValor().equals(CategoriaFoto.DESENHO.getValor()))

        {
            foto.setDescricao(edtDetalhe.getText().toString());
            foto.setArquivo(pathImagem);
        }

        foto.save();

        if (ocorrenciaVidaFoto != null)
        {
            ocorrenciaVidaFoto.setFoto(foto);
            ocorrenciaVidaFoto.save();
        } else
        {
            ocorrenciaVidaFoto = new OcorrenciaVidaFoto();
            ocorrenciaVidaFoto.setFoto(foto);
            ocorrenciaVidaFoto.setOcorrenciaVida(ocorrenciaVida);
            ocorrenciaVidaFoto.save();
        }

        adapterGaleria.notifyDataSetChanged();

        Toast.makeText(getContext(), "Foto salva com sucesso!", Toast.LENGTH_LONG).show();
    }

    private void CarregarFoto()
    {
        pathImagem = foto.getArquivo();
        Picasso.with(getContext()).load(new File(foto.getArquivo())).error(R.drawable.placeholder_error).placeholder(R.drawable.placeholder).into(imgvFotoDetalhe);


        if (foto.getCategoriaFoto() != null)
        {
            if (!foto.getCategoriaFoto().getValor().equals(CategoriaFoto.DESENHO.getValor()))
            {
                if (!spnCategoriaFoto.isEnabled())
                {
                    if(categorias.contains(CategoriaFoto.DESENHO.getValor()))
                    {
                        categorias.remove(CategoriaFoto.DESENHO.getValor());
                        spnCategoriaFoto.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, categorias));
                    }
                    spnCategoriaFoto.setEnabled(true);
                }
                spnCategoriaFoto.setSelection(BuscadorEnum.getIndex(spnCategoriaFoto, foto.getCategoriaFoto().getValor()));
                edtDetalhe.setText(foto.getDescricao());

            } else
            {
                categorias = new ArrayList<>();
                for (CategoriaFoto cf : CategoriaFoto.values())
                    categorias.add(cf.getValor());

                spnCategoriaFoto.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, categorias));

                spnCategoriaFoto.setSelection(BuscadorEnum.getIndex(spnCategoriaFoto, CategoriaFoto.DESENHO.getValor()));

                spnCategoriaFoto.setEnabled(false);
            }
        }

    }

    private void LimparCampos()
    {
        edtDetalhe.setText("");
        Picasso.with(getContext()).load(R.drawable.placeholder_no_image).into(imgvFotoDetalhe);
    }

    @Override
    public VerificationError verifyStep()
    {
        return null;
    }

    @Override
    public void onSelected()
    {
        lastPosition = -1;
        AssociarLayout(getView());

        AssociarEventos();

        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Galeria");

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        ocorrenciaVida = ((ManterPericiaVida) getActivity()).ocorrenciaVida;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

        fotosOcorrenciaVida = OcorrenciaVidaFoto.find(OcorrenciaVidaFoto.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        fotosModel = new ArrayList<>();

        for (OcorrenciaVidaFoto ovf : fotosOcorrenciaVida)
        {
            if (ovf.getFoto() != null)
                fotosModel.add(ovf.getFoto());
        }
        Collections.sort(fotosModel, new Comparator<Foto>()
        {
            @Override

            public int compare(Foto f1, Foto f2)
            {
                //    return f1.getCategoriaFoto().getValor().compareTo(f2.getCategoriaFoto().getValor());
                return 1;
            }
        });

        adapterGaleria = new AdapterGaleria(getContext(), R.layout.row_galeria, fotosModel);

        lstvFOtos.setAdapter(adapterGaleria);

        ViewUtil.modifyAll(rltvFotos, false);
    }

    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    public void AssociarLayout(View view)
    {
//        btnCancelarFoto = (Button) view.findViewById(R.id.btn_Cancelar_Foto_Vida);
//        btnSalvarFoto = (Button) view.findViewById(R.id.btn_Salvar_Foto_Vida);
        edtDetalhe = (EditText) view.findViewById(R.id.edt_Foto_Titulo_Vida);
        lstvFOtos = (ListView) view.findViewById(R.id.lstv_Fotos_Vida);
        fabFotos = (FloatingActionButton) view.findViewById(R.id.fab_Foto_Vida);
        imgvFotoDetalhe = (ImageView) view.findViewById(R.id.imgv_Foto_Detalhe_Vida);
        rltvFotos = (RelativeLayout) view.findViewById(R.id.rltv_Detalhe_Foto_Vida);
        spnCategoriaFoto = (Spinner) view.findViewById(R.id.spn_Categoria_Foto_Vida);

        categorias = new ArrayList<>();
        for (CategoriaFoto cf : CategoriaFoto.values())
        {
            categorias.add(cf.getValor());
        }
        categorias.remove(CategoriaFoto.DESENHO.getValor());

        spnCategoriaFoto.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, categorias));

    }

}
