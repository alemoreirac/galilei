package Fragments.FragmentsVida;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings.Secure;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.GerenciarCabeca;
import com.example.pefoce.peritolocal.GerenciarCorpo;
import com.example.pefoce.peritolocal.ManterPericiaVida;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import Adapters.AdapterEnvolvidoVida;
import Enums.SecaoImagem;
import Model.Ocorrencia;
import Model.Vida.EnderecoVida;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Util.BuilderConclusaoVida;
import Util.CryptUtil;
import Util.ImageUtil;
import Util.ViewUtil;
import info.hoang8f.android.segmented.SegmentedGroup;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GerenciarConclusaoVida extends android.support.v4.app.Fragment implements Step
{
    ScrollView scrlvTexto;
    OcorrenciaVida ocorrenciaVida;
    Ocorrencia ocorrencia;
    TextView txvConclusao = null;
    LinearLayout ll_GerarODT = null;
    TextView txvPath = null;
    String conclusao;
    ImageView imgvAnterior;
    ImageView imgvPosterior;
    ImageView imgvCabecaEsquerda;
    ImageView imgvCabecaDireita;
    ProgressBar progressBar;
    RelativeLayout rltvGaleria;
    ListView lstvEnvolvidos;
    SegmentedGroup sgmtLaudoImagens;
    TextView txvProgress;
    RadioButton rbtnLaudo;
    RadioButton rbtnImagem;
    ArrayList<EnvolvidoVida> envolvidoVidaList;
    List<OcorrenciaEnvolvidoVida> ocorrenciaEnvolvidoVidaList;
    View mView;
    Long envolvidoVidaSelecionadoId;

    private OnFragmentInteractionListener mListener;

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        mView = null;
        ocorrenciaVida = null;
        ocorrencia = null;
        txvConclusao = null;
        ll_GerarODT = null;
        txvPath = null;
        conclusao = null;
        imgvPosterior = null;
        imgvAnterior = null;
        imgvCabecaDireita = null;
        imgvCabecaEsquerda = null;
        sgmtLaudoImagens = null;
        rbtnLaudo = null;
        rbtnImagem = null;
    }

    public GerenciarConclusaoVida()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_gerenciar_conclusao_vida, container, false);

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
        return null;
    }

    @Override
    public void onSelected()
    {

        ((ManterPericiaVida) getActivity()).txvToolbarTitulo.setText("Conclusão");

        ocorrenciaVida = ((ManterPericiaVida) getActivity()).ocorrenciaVida;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

        conclusao = BuilderConclusaoVida.ConstruirConclusao(ocorrenciaVida);

        ocorrenciaEnvolvidoVidaList = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        envolvidoVidaList = new ArrayList<>();

        for (OcorrenciaEnvolvidoVida oev : ocorrenciaEnvolvidoVidaList)
            envolvidoVidaList.add(oev.getEnvolvidoVida());

        AdapterEnvolvidoVida adapterEnvolvidoVida = new AdapterEnvolvidoVida(envolvidoVidaList, getActivity());

        AssociarLayout(getView());

        AssociarEventos();

        imgvCabecaDireita.setVisibility(View.INVISIBLE);
        imgvCabecaEsquerda.setVisibility(View.INVISIBLE);
        imgvPosterior.setVisibility(View.INVISIBLE);
        imgvAnterior.setVisibility(View.INVISIBLE);

        lstvEnvolvidos.setAdapter(adapterEnvolvidoVida);

        txvConclusao.setText(conclusao);

        txvConclusao.getVerticalScrollbarPosition();

        rbtnImagem.performClick();

        ViewUtil.modifyAllRecursive(((ManterPericiaVida)getActivity()).rltvContent, false);

        progressBar.setVisibility(View.VISIBLE);
        txvProgress.setVisibility(View.VISIBLE);

        GerarImagens();
    }

    @Override
    public void onError(@NonNull VerificationError error)
    {
    }

    private void GerarImagens()
    {
        if(envolvidoVidaList.size() == 0)
        {
            progressBar.setVisibility(View.INVISIBLE);
            txvProgress.setText("Não há envolvidos cadastrados!");
            return;
        }

        for(final EnvolvidoVida envolvidoVida : envolvidoVidaList)
        {
            if(envolvidoVida != null)
            {
                List<SecaoImagem> secoes = envolvidoVida.getSecoesImagem();

                if(secoes.size() == 0)
                {
                    lstvEnvolvidos.performItemClick(
                            lstvEnvolvidos.getAdapter().getView(0, null, null),
                            0,
                            lstvEnvolvidos.getAdapter().getItemId(0));

                }

            for(final SecaoImagem secaoSalva : secoes)
            {
                txvProgress.setText("Carregando: "+envolvidoVida.getNome() +" "+ secaoSalva.getValor());

                @SuppressLint("StaticFieldLeak") final AsyncTask<Void, Integer, Bitmap> asyncTaskImagens = new AsyncTask<Void, Integer, Bitmap>()
                {
                    @Override
                    protected Bitmap doInBackground(Void... params)
                    {
                        return ImageUtil.gerarImagens(envolvidoVida, secaoSalva, getActivity());
                    }
                    @Override
                    protected void onPostExecute(Bitmap params)
                    {
                        if(envolvidoVidaList.indexOf(envolvidoVida) == envolvidoVidaList.size()-1)
                        {
                            progressBar.setVisibility(View.INVISIBLE);
                            txvProgress.setVisibility(View.INVISIBLE);

                            ViewUtil.modifyAllRecursive(((ManterPericiaVida)getActivity()).rltvContent, true);

                            lstvEnvolvidos.performItemClick(
                                    lstvEnvolvidos.getAdapter().getView(0, null, null),
                                    0,
                                    lstvEnvolvidos.getAdapter().getItemId(0));
                        }

                        SalvarImagem(params,secaoSalva.getSecaoValor(),envolvidoVida);
                        txvProgress.setText("Carregando: "+envolvidoVida.getNome() +" "+ secaoSalva.getValor());
                    }
                };
                    if(asyncTaskImagens.getStatus().equals(AsyncTask.Status.RUNNING))
                            asyncTaskImagens.cancel(true);

                    asyncTaskImagens.execute();
            }
            }
        }
    }

    private void AssociarEventos()
    {
        ll_GerarODT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                generateNoteOnSD(getContext(), "Laudo_" + ocorrenciaVida.getNumIncidencia(), conclusao);
                EnviarDados();
            }
        });

        rbtnImagem.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txvConclusao.setVisibility(View.INVISIBLE);
                scrlvTexto.setVisibility(View.INVISIBLE);
                rltvGaleria.setVisibility(View.VISIBLE);
            }
        });

        rbtnLaudo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                txvConclusao.setVisibility(View.VISIBLE);
                scrlvTexto.setVisibility(View.VISIBLE);
                rltvGaleria.setVisibility(View.INVISIBLE);
            }
        });

        lstvEnvolvidos.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                try
                {
                    final EnvolvidoVida envolvidoVidaItem = envolvidoVidaList.get(position);

                    imgvCabecaDireita.setImageBitmap(null);
                    imgvPosterior.setImageBitmap(null);
                    imgvAnterior.setImageBitmap(null);
                    imgvCabecaEsquerda.setImageBitmap(null);

                    imgvCabecaDireita.setVisibility(View.INVISIBLE);
                    imgvPosterior.setVisibility(View.INVISIBLE);
                    imgvAnterior.setVisibility(View.INVISIBLE);
                    imgvCabecaEsquerda.setVisibility(View.INVISIBLE);

                    envolvidoVidaSelecionadoId = envolvidoVidaItem.getId();

                    List<SecaoImagem> secoes = envolvidoVidaItem.getSecoesImagem();

                    if(secoes!=null)
                    {
                        if(secoes.size() == 0)
                        {
                            txvProgress.setVisibility(View.VISIBLE);
                            txvProgress.setText("Não há lesões cadastradas para este envolvido");
                            progressBar.setVisibility(View.INVISIBLE);
                            return;
                        }
                    }
                    else
                         return;


                    for (final SecaoImagem secaoImagem : secoes)
                    {
                        Bitmap bitmap = BuscarBitmap(envolvidoVidaItem,secaoImagem);

                        if (bitmap != null)
                        {
                            //bitmap = Bitmap.createScaledBitmap(bitmap, parent.getWidth(), parent.getHeight(), true);

                            switch (secaoImagem.getSecaoValor())
                            {
                                case "_Posterior":
                                    imgvPosterior.setVisibility(View.VISIBLE);
                                    imgvPosterior.setImageBitmap(bitmap);
                                    break;
                                case "_Anterior":
                                    imgvAnterior.setVisibility(View.VISIBLE);
                                    imgvAnterior.setImageBitmap(bitmap);
                                    break;
                                case "_Cabeca_Esquerda":
                                    imgvCabecaEsquerda.setVisibility(View.VISIBLE);
                                    imgvCabecaEsquerda.setImageBitmap(bitmap);
                                    break;
                                case "_Cabeca_Direita":
                                    imgvCabecaDireita.setVisibility(View.VISIBLE);
                                    imgvCabecaDireita.setImageBitmap(bitmap);
                                    break;
                            }
                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    txvProgress.setVisibility(View.INVISIBLE);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        imgvCabecaDireita.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent itCabecaDireita = new Intent(getActivity(), GerenciarCabeca.class);
                itCabecaDireita.putExtra("EnvolvidoId", envolvidoVidaSelecionadoId);
                itCabecaDireita.putExtra("LadoCabeca", SecaoImagem.CABECA_MASCULINO_DIREITA.getValor());
                itCabecaDireita.putExtra("Conclusao", true);
                itCabecaDireita.putExtra("OcorrenciaId",ocorrenciaVida.getId());
                startActivity(itCabecaDireita);
            }
        });

        imgvCabecaEsquerda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent itCabecaEsquerda = new Intent(getActivity(), GerenciarCabeca.class);
                itCabecaEsquerda.putExtra("EnvolvidoId", envolvidoVidaSelecionadoId);
                itCabecaEsquerda.putExtra("LadoCabeca", SecaoImagem.CABECA_FEMININO_ESQUERDA.getValor());
                itCabecaEsquerda.putExtra("Conclusao", true);
                itCabecaEsquerda.putExtra("OcorrenciaId",ocorrenciaVida.getId());
                startActivity(itCabecaEsquerda);
            }
        });

        imgvAnterior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent itCorpoFrente = new Intent(getActivity(), GerenciarCorpo.class);
                itCorpoFrente.putExtra("EnvolvidoId", envolvidoVidaSelecionadoId);
                itCorpoFrente.putExtra("Conclusao", true);
                itCorpoFrente.putExtra("OcorrenciaId",ocorrenciaVida.getId());
                startActivity(itCorpoFrente);
            }
        });

        imgvPosterior.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent itCorpoCostas = new Intent(getActivity(), GerenciarCorpo.class);
                itCorpoCostas.putExtra("EnvolvidoId", envolvidoVidaSelecionadoId);
                itCorpoCostas.putExtra("Conclusao", true);
                itCorpoCostas.putExtra("OcorrenciaId",ocorrenciaVida.getId());
                startActivity(itCorpoCostas);
            }
        });
    }

    private Bitmap BuscarBitmap(EnvolvidoVida envolvidoVida,SecaoImagem secaoImagem)
    {
        File sd = Environment.getExternalStorageDirectory();
        File image = new File(sd + "/Galilei/" + ocorrencia.getPerito().getId().toString() + "_" + ocorrencia.getPerito().getNome() + "/" + "Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() + "/Imagens_Geradas/"
                + envolvidoVida.getId().toString() + "_" + envolvidoVida.getNome() + secaoImagem.getSecaoValor() + ".jpeg");

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);

        return bitmap;
    }

    private void SalvarImagem(Bitmap bmp, String secao,EnvolvidoVida envolvidoVidaParam)
    {
        File f = null;

        if (bmp != null)
        {
            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() + "/Imagens_Geradas/";

            f = new File(newPath);
            if (!f.exists())
                f.mkdirs();

            newPath += envolvidoVidaParam.getId().toString() +"_"+ envolvidoVidaParam.getNome()+ secao+".jpeg";

            f = new File(newPath);
            if (f.exists())
                f.delete();

            FileOutputStream out = null;
            try
            {
                out = new FileOutputStream(newPath,false);
                bmp.compress(Bitmap.CompressFormat.JPEG, 100, out);
                Log.i("File created: ",out.toString());
                System.out.print("File created: "+out.toString());
                //Log.println(Log.ASSERT,"File created",out.toString());
            } catch (Exception e)
            {
                e.printStackTrace();
            } finally
            {
                try
                {
                    if (out != null)
                        out.close();

                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }


    private void AssociarLayout(View v)
    {
        txvConclusao = (TextView) v.findViewById(R.id.txv_Conclusao_Vida_Text);

        rbtnImagem = (RadioButton) v.findViewById(R.id.rbtn_Opcao_Imagens_Vida);
        rbtnLaudo = (RadioButton) v.findViewById(R.id.rbtn_Opcao_Laudo_Vida);

        imgvCabecaDireita = (ImageView) v.findViewById(R.id.imgv_Cabeca_Direita_Conclusao);
        imgvCabecaEsquerda = (ImageView) v.findViewById(R.id.imgv_Cabeca_Esquerda_Conclusao);
        imgvAnterior = (ImageView) v.findViewById(R.id.imgv_Frente_Conclusao);
        imgvPosterior = (ImageView) v.findViewById(R.id.imgv_Costas_Conclusao);
        rltvGaleria = (RelativeLayout) v.findViewById(R.id.rltv_Galeria_Conclusao);
        lstvEnvolvidos = (ListView) v.findViewById(R.id.lstv_Envolvidos_Conclusao);

        txvProgress = (TextView) v.findViewById(R.id.txv_Progress_Imagens);

        progressBar = (ProgressBar) v.findViewById(R.id.pgb_Carregando);
        txvPath = (TextView) v.findViewById(R.id.txv_Arquivos_Path_Vida);
        sgmtLaudoImagens = (SegmentedGroup) v.findViewById(R.id.sgm_Conclusao_Vida);
        ll_GerarODT = (LinearLayout) v.findViewById(R.id.ll_Gerar_ODT_Vida);

        scrlvTexto = (ScrollView) v.findViewById(R.id.scrlv_Conclusao_Vida);
    }


    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody)
    {
        try
        {
            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia();

            File root = new File(newPath + "/Anotacoes/");
            if (!root.exists())
                root.mkdirs();

            File gpxfile = new File(root, sFileName);
            //FileWriter writer = new FileWriter(gpxfile);
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(gpxfile), StandardCharsets.ISO_8859_1);
            writer.append(sBody);

            File file2 = new File(gpxfile.getPath() + ".odt"); // destination dir of your file
            boolean success = gpxfile.renameTo(file2);
            if (success)
            {
                //      txvPath.setText("Caminho salvo em: " + file2.getPath());
            }

            writer.flush();
            writer.close();
            Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_LONG).show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void EnviarDados()
    {

        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, String> asyncTask2 = new AsyncTask<Void, Void, String>()
        {


            @Override
            protected String doInBackground(Void... params)
            {
                try
                {
                    EnderecoVida enderecoVida = EnderecoVida.find(EnderecoVida.class, "ocorrencia_id = ?", ocorrenciaVida.getId().toString()).get(0);

                    String android_id = Secure.getString(getContext().getContentResolver(),
                            Secure.ANDROID_ID);

                    String qtdeEnvolvidos = Integer.toString(OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString()).size());

                    OkHttpClient client = new OkHttpClient();
                    RequestBody requestBody = new FormBody.Builder()

                            .add("ocorrenciaId", ocorrencia.getId().toString())
                            .add("incidencia", ocorrenciaVida.getNumIncidencia())
                            .add("tipoOcorrencia", ocorrencia.getTipoOcorrencia().getValor())
                            .add("dataOcorrencia", ocorrenciaVida.getDataHoraFormatadaAtendimento())
                            .add("usuario", ocorrencia.getPerito().getNome())
                            .add("endereco", enderecoVida.toString())
                            .add("ais", ocorrenciaVida.getAis().getValor())
                            .add("delegacia", ocorrenciaVida.getOrgaoDestino())
                            .add("latitude", enderecoVida.getLatitude())
                            .add("longitude", enderecoVida.getLongitude())
                            .add("qtdeEnvolvidos", qtdeEnvolvidos)
                            .add("deviceId", android_id)
                            .build();

//                    RequestBody requestBody = new MultipartBody.Builder()
//                            .setType(MultipartBody.FORM)
//                            .("teste", "Square Logo")
//                            .build();
//String qualquer= "";
                    Request request = new Request.Builder()
                            .addHeader("senha", CryptUtil.MD5_Hash("123456"))
                            .url("http://189.90.160.48:7070/galileiWebService/cliente/formOcorrencia")
                            .post(requestBody)
                            .build();

                    Response response = client.newCall(request).execute();

                    return response.body().string();

                } catch (Exception e)
                {
                    Log.d("V", e.toString());
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s)
            {
                super.onPostExecute(s);
                if (s != null)
                {
                    //      txvPath.setText(s);
                    //       txvConclusao.setText(s);
                }
            }
        };

        asyncTask2.execute();


    }


}