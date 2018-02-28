package Fragments.FragmentsVida;

import android.Manifest;
import android.app.AlertDialog;
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
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Vida.OcorrenciaVida;
import Model.Vida.OcorrenciaVidaFoto;
import Util.BuscadorEnum;
import Util.ViewUtil;

public class GerenciarFotosVida extends android.support.v4.app.Fragment implements Step
{
    ListView lstvFOtos;

    FloatingActionButton fabFotos;

    ImageView imgvFotoDetalhe;
    EditText edtDetalhe;
    Spinner spnCategoriaFoto;
    MagicalPermissions magicalPermissions;
    MagicalCamera magicalCamera;
    Button btnCancelarFoto;
    Button btnSalvarFoto;
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

    public GerenciarFotosVida()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GerenciarFotosVida.
     */
    // TODO: Rename and change types and number of parameters
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //CALL THIS METHOD EVER
        magicalCamera.resultPhoto(requestCode, resultCode, data);



        //this is for rotate picture in this method
        //magicalCamera.resultPhoto(requestCode, resultCode, data, MagicalCamera.ORIENTATION_ROTATE_180);
//if you need save your bitmap in device use this method and return the path if you need this
        //You need to send, the bitmap picture, the photo name, the directory name, the picture type, and autoincrement photo name if           //you need this send true, else you have the posibility or realize your standard name for your pictures.
        pathImagem = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),"imagem_pericia","Imagens " + ocorrenciaVida.getId().toString(), MagicalCamera.JPEG, true);


        if(pathImagem != null)
        {
            LimparCampos();

            imgvFotoDetalhe.setImageBitmap(magicalCamera.getPhoto());


            File newFile = new File(pathImagem);

            Picasso.with(getContext()).load(new File(pathImagem)).error(R.drawable.placeholder_error).into(imgvFotoDetalhe);

            String newPath = "";

            switch(spnCategoriaFoto.getSelectedItem().toString())
            {
                case "Endereços":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath()+"/"+ocorrenciaVida.getNumIncidencia() + "/Fotos_Enderecos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_endereco" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;

                case "Veículos":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath()+"/"+ocorrenciaVida.getNumIncidencia() + "/Fotos_Veiculos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_veiculo" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
                case "Local Ocorrência":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath()+"/"+ocorrenciaVida.getNumIncidencia() + "/Fotos_Local_Ocorrencia/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_local_ocorrencia" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
                case "Vestígios":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath()+"/"+ocorrenciaVida.getNumIncidencia() + "/Fotos_Vestigios/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_vestigios" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
                case "Envolvidos":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaVida.getDataPath()+"/"+ocorrenciaVida.getNumIncidencia() + "/Fotos_Envolvidos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_envolvido" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
                case "Outros":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaVida.getDataPath()+"/"+ocorrenciaVida.getNumIncidencia() + "/Fotos_Outros/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_outros" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
                default:
                    newPath += "foto" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
            }



            if(newFile.renameTo(new File(newPath)))
                foto = new Foto(edtDetalhe.getText().toString(),newPath);

            else
                foto = new Foto(edtDetalhe.getText().toString(),pathImagem);

            foto.save();
            ocorrenciaVidaFoto = new OcorrenciaVidaFoto();
            ocorrenciaVidaFoto .setFoto(foto);
            ocorrenciaVidaFoto .setOcorrenciaVida(ocorrenciaVida);
            ocorrenciaVidaFoto .save();
            fotosOcorrenciaVida.add(ocorrenciaVidaFoto);
            adapterGaleria.add(foto);
            adapterGaleria.notifyDataSetChanged();

            if(pathImagem!= null)
                Toast.makeText(getActivity(), "Foto salva no caminho: " + pathImagem, Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_SHORT).show();
            lstvFOtos.performItemClick(lstvFOtos, BuscadorEnum.PegarPosicaoFoto(fotosModel,foto),lstvFOtos.getItemIdAtPosition(BuscadorEnum.PegarPosicaoFoto(fotosModel,foto)));

        }

        ViewUtil.modifyAll(rltvFotos,true);
    }



    public void AssociarEventos()
    {
        fabFotos.setOnClickListener(new View.OnClickListener()
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
                magicalPermissions = new MagicalPermissions(GerenciarFotosVida.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                foto = new Foto();

                TipoFotoDialog tfd = new TipoFotoDialog(GerenciarFotosVida.this, getActivity(), magicalCamera);

            }

            ;
        });

        lstvFOtos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                foto = fotosModel.get(position);
                view.setSelected(true);
                LimparCampos();
                ViewUtil.modifyAll(rltvFotos, true);
                CarregarFoto(foto);
                pathImagem = foto.getArquivo();
                try
                {
                    ocorrenciaVidaFoto = OcorrenciaVidaFoto.find(OcorrenciaVidaFoto.class, "ocorrencia_vida = ? and foto = ?", ocorrenciaVidaFoto.getId().toString(), foto.getId().toString()).get(0);
                } catch (Exception e)
                {
                    ocorrenciaVidaFoto = new OcorrenciaVidaFoto();
                }
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
                                fotosOcorrenciaVida.remove(ocorrenciaFotoDelete.getFoto());
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

        btnCancelarFoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Picasso.with(getContext()).load(new File(foto.getArquivo())).error(R.drawable.placeholder_error).placeholder(R.drawable.placeholder).into(imgvFotoDetalhe);
                edtDetalhe.setText(foto.getDescricao());
            }
        });

        btnSalvarFoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                SalvarFoto();
            }
        });

    }

    private void SalvarFoto()
    {
        foto.setDescricao(edtDetalhe.getText().toString());
        foto.setArquivo(pathImagem);
        foto.setCategoriaFoto(BuscadorEnum.BuscarCategoriaFoto(spnCategoriaFoto.getSelectedItem().toString()));
        foto.save();

        if (ocorrenciaVidaFoto != null)
        {
            ocorrenciaVidaFoto.setFoto(foto);
            ocorrenciaVidaFoto.save();
        }
        else
        {
            ocorrenciaVidaFoto = new OcorrenciaVidaFoto();
            ocorrenciaVidaFoto.setFoto(foto);
            ocorrenciaVidaFoto.setOcorrenciaVida(ocorrenciaVida);
            ocorrenciaVidaFoto.save();
        }

        adapterGaleria.notifyDataSetChanged();

        Toast.makeText(getContext(), "Foto salva com sucesso!", Toast.LENGTH_LONG).show();
    }

    private void CarregarFoto(Foto f)
    {
        Picasso.with(getContext()).load(new File(f.getArquivo())).error(R.drawable.placeholder_error).placeholder(R.drawable.placeholder).into(imgvFotoDetalhe);
        edtDetalhe.setText(f.getDescricao());
        if(f.getCategoriaFoto()!=null)
            spnCategoriaFoto.setSelection(BuscadorEnum.getIndex(spnCategoriaFoto,f.getCategoriaFoto().getValor()));
        else
            spnCategoriaFoto.setSelection(0);
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
        AssociarLayout(getView());

        AssociarEventos();

        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Galeria");

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
        btnCancelarFoto = (Button) view.findViewById(R.id.btn_Cancelar_Foto_Vida);
        edtDetalhe = (EditText) view.findViewById(R.id.edt_Foto_Titulo_Vida);
        btnSalvarFoto = (Button) view.findViewById(R.id.btn_Salvar_Foto_Vida);
        lstvFOtos = (ListView) view.findViewById(R.id.lstv_Fotos_Vida);
        fabFotos = (FloatingActionButton) view.findViewById(R.id.fab_Foto_Vida);
        imgvFotoDetalhe = (ImageView) view.findViewById(R.id.imgv_Foto_Detalhe_Vida);
        rltvFotos = (RelativeLayout) view.findViewById(R.id.rltv_Detalhe_Foto_Vida);
        spnCategoriaFoto = (Spinner) view.findViewById(R.id.spn_Categoria_Foto_Vida);


        ArrayList<String> categorias = new ArrayList<>();
        for(CategoriaFoto cf : CategoriaFoto.values())
        {
            categorias.add(cf.getValor());
        }

        spnCategoriaFoto.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,categorias));

    }

}
