package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.R;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransito;
import Util.BuilderConclusao;


public class GerenciarConclusao extends android.support.v4.app.Fragment implements Step
{


    OcorrenciaTransito ocorrenciaTransitoConclusao;

    Ocorrencia ocorrencia;

    TextView txvConclusao = null;
    LinearLayout ll_GerarODT = null;
    TextView txvPath = null;
    String conclusao;

    public GerenciarConclusao()
    {

    }

    public static GerenciarConclusao newInstance(String param1, String param2)
    {
        GerenciarConclusao fragment = new GerenciarConclusao();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_gerenciar_conclusao, container, false);
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

        ((ManterPericia) getActivity()).txvToolbarTitulo.setText("Conclusão");

        ocorrenciaTransitoConclusao = ((ManterPericia) getActivity()).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoConclusao.getOcorrenciaID());

        conclusao = BuilderConclusao.ConstruirConclusao(ocorrenciaTransitoConclusao);

        txvConclusao.setText(conclusao);
    }

    private void ConclusaoCaso()
    {

    }



    @Override
    public void onError(@NonNull VerificationError error)
    {

    }

    public interface OnFragmentInteractionListener
    {

        void onFragmentInteraction(Uri uri);
    }

    private void AssociarLayout(View v)
    {
        txvConclusao = (TextView) v.findViewById(R.id.txv_Conclusao_Text);
        txvConclusao.setMovementMethod(new ScrollingMovementMethod());

        txvPath = (TextView) v.findViewById(R.id.txv_Arquivos_Path);

        ll_GerarODT = (LinearLayout) v.findViewById(R.id.ll_Gerar_ODT);

        ll_GerarODT.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                generateNoteOnSD(getContext(), "Laudo_"+ocorrenciaTransitoConclusao.getNumIncidencia(), conclusao);


            }
        });
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody)
    {
        try
        {

            String newPath = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransitoConclusao.getDataPath() + "/" + ocorrenciaTransitoConclusao.getNumIncidencia();


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

                txvPath.setText("Caminho salvo em: "+file2.getPath());
            }

            writer.flush();
            writer.close();
            Toast.makeText(context, "Salvo com sucesso!", Toast.LENGTH_LONG).show();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
