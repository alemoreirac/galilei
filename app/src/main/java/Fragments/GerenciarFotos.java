package Fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.R;
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
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
import Enums.CategoriaFoto;
import Model.Foto;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Transito.OcorrenciaTransito;
import Util.BuscadorEnum;
import Util.ViewUtil;


public class GerenciarFotos  extends  android.support.v4.app.Fragment  implements Step
{
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    File folder = null;
    private ListView lstvImagens;
    private ImageView imgvDetalhe;
    private EditText edtDetalhe;
    private FloatingActionButton fabFoto;
    private RelativeLayout rltvFotos;
    private AdapterGaleria adapterGaleria;
    private Button btnSave;
    private Button btnCancelar;
    private Spinner spnCategoriaFoto;


    OcorrenciaTransitoFoto ocorrenciaFoto;

    Foto foto;
    OcorrenciaTransito ocorrenciaTransitoFoto;
    Ocorrencia ocorrencia;


    private MagicalCamera magicalCamera;
    private MagicalPermissions magicalPermissions;
    private static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 50;

    private OnFragmentInteractionListener mListener;

    List<OcorrenciaTransitoFoto> ocorrenciaFotos;
    ArrayList<Foto> fotosModel;

    private String pathImagem;


    public GerenciarFotos() {
    }

    public static GerenciarFotos newInstance(String param1, String param2) {
        GerenciarFotos fragment = new GerenciarFotos();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gerenciar_fotos, container, false);
    }

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
        if(rltvFotos.getChildAt(0).isEnabled())
        {
        try{
            SalvarFoto();
        }
        catch (Exception e)
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
        AssociarLayout(getView());
        AssociarEventos();
        ocorrenciaTransitoFoto = ((ManterPericia) getActivity()).ocorrenciaTransito;

        ((ManterPericia) getActivity()).txvToolbarTitulo.setText("Fotos");

        ocorrencia = Ocorrencia.findById(Ocorrencia.class,ocorrenciaTransitoFoto.getOcorrenciaID());

        ocorrenciaFotos =  OcorrenciaTransitoFoto.find(OcorrenciaTransitoFoto.class,"ocorrencia_transito = ?",ocorrenciaTransitoFoto.getId().toString());
//        ocorrenciaFotos = Select.from(OcorrenciaTransitoFoto.class)
//                .orderBy("categoriaFoto")
//                .where(Condition.prop("ocorrencia_transito").eq(ocorrenciaTransitoFoto.getId().toString())).lstvEnvolvidos();

        fotosModel = new ArrayList<Foto>();

        for(OcorrenciaTransitoFoto of : ocorrenciaFotos)
        {
            if(of.getFoto()!= null)
            fotosModel.add(of.getFoto());
        }

        Collections.sort(fotosModel, new Comparator<Foto>() {
            @Override

            public int compare(Foto f1, Foto f2) {
            //    return f1.getCategoriaFoto().getValor().compareTo(f2.getCategoriaFoto().getValor());
                return 1;
            }
        });

        adapterGaleria = new AdapterGaleria(getContext(),R.layout.row_galeria,fotosModel);

        lstvImagens.setAdapter(adapterGaleria);

        ViewUtil.modifyAll(rltvFotos, false);
    }

    @Override
    public void onError(@NonNull VerificationError error) {

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
        pathImagem = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(),"imagem_pericia","Imagens " + ocorrenciaTransitoFoto.getId().toString(), MagicalCamera.JPEG, true);


        if(pathImagem != null)
        {
            LimparCampos();

            imgvDetalhe.setImageBitmap(magicalCamera.getPhoto());


            File newFile = new File(pathImagem);

            Picasso.with(getContext()).load(new File(pathImagem)).error(R.drawable.placeholder_error).into(imgvDetalhe);

            String newPath = "";

            switch(spnCategoriaFoto.getSelectedItem().toString())
            {
                case "Endereços":
                    newPath = Environment.getExternalStorageDirectory() +
                        "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoFoto.getDataPath()+"/"+ocorrenciaTransitoFoto.getNumIncidencia() + "/Fotos_Enderecos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_endereco" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;

                case "Veículos":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoFoto.getDataPath()+"/"+ocorrenciaTransitoFoto.getNumIncidencia() + "/Fotos_Veiculos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_veiculo" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
                case "Envolvidos":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoFoto.getDataPath()+"/"+ocorrenciaTransitoFoto.getNumIncidencia() + "/Fotos_Envolvidos/";

                    folder = new File(newPath);
                    if (!folder.exists())
                        folder.mkdirs();

                    newPath += "foto_envolvido" + DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".jpeg";
                    break;
                case "Outros":
                    newPath = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoFoto.getDataPath()+"/"+ocorrenciaTransitoFoto.getNumIncidencia() + "/Fotos_Outros/";

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
                ocorrenciaFoto = new OcorrenciaTransitoFoto();
                ocorrenciaFoto.setFoto(foto);
                ocorrenciaFoto.setOcorrenciaTransito(ocorrenciaTransitoFoto);
                ocorrenciaFoto.save();
                ocorrenciaFotos.add(ocorrenciaFoto);
                adapterGaleria.add(foto);
                adapterGaleria.notifyDataSetChanged();


                if(pathImagem!= null)
                    Toast.makeText(getActivity(), "Foto salva no caminho: " + pathImagem, Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Ocorreu um erro na gravação, entre em contato com o suporte!", Toast.LENGTH_SHORT).show();
                lstvImagens.performItemClick(lstvImagens, BuscadorEnum.PegarPosicaoFoto(fotosModel,foto),lstvImagens.getItemIdAtPosition(BuscadorEnum.PegarPosicaoFoto(fotosModel,foto)));


        }

        ViewUtil.modifyAll(rltvFotos,true);
    }

    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }

    private void AssociarLayout(View v)
    {
        rltvFotos = (RelativeLayout) v.findViewById(R.id.rltv_Detalhe_Foto);
        imgvDetalhe = (ImageView) v.findViewById(R.id.imgv_Foto_Detalhe);
        edtDetalhe = (EditText) v.findViewById(R.id.edt_Foto_Titulo);
        fabFoto = (FloatingActionButton)v.findViewById(R.id.fab_Foto);
        lstvImagens = (ListView) v.findViewById(R.id.lstv_Fotos);
        btnSave = (Button) v.findViewById(R.id.btn_Salvar_Foto);
        btnCancelar = (Button) v.findViewById(R.id.btn_Cancelar_Foto);
        spnCategoriaFoto = (Spinner) v.findViewById(R.id.spn_Categoria_Foto);

        ArrayList<String> categorias = new ArrayList<>();
        for(CategoriaFoto cf : CategoriaFoto.values())
        {
            categorias.add(cf.getValor());
        }

        spnCategoriaFoto.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,categorias));

    }
    private void AssociarEventos()
    {
        fabFoto.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String[] permissions = new String[] {
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                };
                magicalPermissions = new MagicalPermissions(GerenciarFotos.this, permissions);
                magicalCamera = new MagicalCamera(getActivity(),RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);

                foto = new Foto();

                TipoFotoDialog tfd = new TipoFotoDialog();

            };
        });

        lstvImagens.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                foto = fotosModel.get(position);
                view.setSelected(true);
                LimparCampos();
                ViewUtil.modifyAll(rltvFotos,true);
                carregarDadosFoto(foto);
                pathImagem = foto.getArquivo();
                try
                {
                    ocorrenciaFoto = OcorrenciaTransitoFoto.find(OcorrenciaTransitoFoto.class,"ocorrencia_transito = ? and foto = ?",ocorrenciaTransitoFoto.getId().toString(),foto.getId().toString()).get(0);
                }
                catch(Exception e){
                    ocorrenciaFoto = new OcorrenciaTransitoFoto();
            }

            }
        });

        lstvImagens.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
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
                               OcorrenciaTransitoFoto ocorrenciaFotoDelete = ocorrenciaFotos.get(position);

                                adapterGaleria.remove(ocorrenciaFotoDelete.getFoto());

                                ocorrenciaFotos.remove(ocorrenciaFotoDelete.getFoto());
                                ocorrenciaFotoDelete.getFoto().delete();
                                ocorrenciaFotoDelete.delete();
                                LimparCampos();
                                Toast.makeText(getActivity(), "Foto Deletada com sucesso!", Toast.LENGTH_LONG).show();
                                adapterGaleria.notifyDataSetChanged();
                                ViewUtil.modifyAll(rltvFotos,false);
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


        btnCancelar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Picasso.with(getContext()).load(new File(foto.getArquivo())).error(R.drawable.placeholder_error).placeholder(R.drawable.placeholder).into(imgvDetalhe);
                edtDetalhe.setText(foto.getDescricao());
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener()
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

        if(ocorrenciaFoto != null)
        {
            ocorrenciaFoto.setFoto(foto);
            ocorrenciaFoto.save();
        }
        else
        {
            ocorrenciaFoto = new OcorrenciaTransitoFoto();
            ocorrenciaFoto.setOcorrenciaTransito(ocorrenciaTransitoFoto);
            ocorrenciaFoto.setFoto(foto);
            ocorrenciaFoto.save();
        }

        adapterGaleria.notifyDataSetChanged();

        Toast.makeText(getContext(), "Foto salva com sucesso!", Toast.LENGTH_LONG).show();
    }

    public void carregarDadosFoto(Foto f)
    {
        Picasso.with(getContext()).load(new File(f.getArquivo())).error(R.drawable.placeholder_error).placeholder(R.drawable.placeholder).into(imgvDetalhe);
        //imgvDetalhe.setImageBitmap(ImageUtil.byteToBitmap(f.getArquivo()));
        edtDetalhe.setText(f.getDescricao());
        if(f.getCategoriaFoto()!= null)
        spnCategoriaFoto.setSelection(BuscadorEnum.getIndex(spnCategoriaFoto,f.getCategoriaFoto().getValor()));
        else
            spnCategoriaFoto.setSelection(0);
    }

    private void LimparCampos()
    {
        edtDetalhe.setText("");
        Picasso.with(getContext()).load(R.drawable.placeholder_no_image).into(imgvDetalhe);
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

            activity =getActivity();
            ot =  ((ManterPericia)activity).ocorrenciaTransito;

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
                    magicalCamera.takeFragmentPhoto(GerenciarFotos.this);
                    dialog.dismiss();
                }
            });

            imgvGaleria.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) throws IllegalArgumentException,
                        SecurityException, IllegalStateException
                {
                    magicalCamera.selectFragmentPicture(GerenciarFotos.this,"Selecione Uma Imagem");
                    dialog.dismiss();
                }
            });


        }


    }


}
