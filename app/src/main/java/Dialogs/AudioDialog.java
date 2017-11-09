package Dialogs;

import android.Manifest;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.ManterPericia;
import com.example.pefoce.peritolocal.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import Model.ColisaoTransito;
import Model.Gravacao;
import Model.Ocorrencia;
import Model.OcorrenciaTransito;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Pefoce on 21/08/2017.
 */

public class AudioDialog  extends  DialogFragment implements ActivityCompat.OnRequestPermissionsResultCallback
{

   public Activity activity = null;
   public boolean Sobrescrever = false;
   public LinearLayout buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording ;
   public String pathArquivo = null;
   public MediaRecorder mediaRecorder ;
   public   static final int RequestPermissionCode = 2;
   public MediaPlayer mediaPlayer ;
   public TextView txvPath = null;
   public Bundle bundle;
   public OcorrenciaTransito ocorrenciaTransito;
    Ocorrencia ocorrencia;
    public ColisaoTransito colisaoTransito;
    public boolean Colisao;
    String path;

    public AudioDialog()
    {
    }

    public static AudioDialog newInstance(String title, String local)
    {
        AudioDialog frag = new AudioDialog();

        Bundle args = new Bundle();

        args.putString("Gravar Áudio", title);
        args.putString("Local", local);

        frag.setArguments(args);

        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.dialog_audio, container, false);

        Bundle bd = getArguments();

      //  Arquivo = bd.getByteArray("Arquivo");

        activity =getActivity();
        ocorrenciaTransito =  ((ManterPericia)activity).ocorrenciaTransito;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class,ocorrenciaTransito.getOcorrenciaID());

        Colisao = bd.getBoolean("Colisao");

        if(Colisao)
        {
            colisaoTransito = ColisaoTransito.findById(ColisaoTransito.class,bd.getLong("ColisaoId"));
        }

        AssociarLayout(view);

        if(Colisao)
            path = Environment.getExternalStorageDirectory() +
                    "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransito.getDataPath()+"/"+ ocorrenciaTransito.getNumIncidencia() + "/Audio_Colisao/";
        else
            path = Environment.getExternalStorageDirectory() +
                   "/Galilei/" + ocorrencia.getPerito().getId() +"_"+ ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransito.getDataPath()+"/"+ ocorrenciaTransito.getNumIncidencia() + "/Audio_Geral/";

            File folder = new File(path);

            if (!folder.exists())
            {
                folder.mkdirs();
            }
            else
            {
                if(Colisao)
                {
                    if(colisaoTransito.getGravacaoObservacoes() != null)
                    {
                        File file = new File(colisaoTransito.getGravacaoObservacoes().getArquivo());
                        if(file.exists())
                        {
                            Sobrescrever = true;
                            buttonPlayLastRecordAudio.setEnabled(true);
                            txvPath.setText(file.getName());
                        }
                        else
                        {
                            txvPath.setText("Não há arquivo");
                          //  if(true)
                          //  {
                             //   try
                             //   {
                             //       //convert(colisaoTransito.getGravacaoObservacoes().getArquivo(),colisaoTransito.getGravacaoObservacoes().getTitulo());
                             //    //   File outputFile = File.createTempFile("file", ".mp3",folder);
                             //    //   outputFile.deleteOnExit();
                             //    //   FileOutputStream fileoutputstream = new FileOutputStream(colisaoTransito.getGravacaoObservacoes().getTitulo());
                             //    //   fileoutputstream.write(colisaoTransito.getGravacaoObservacoes().getArquivo());
                             //       //   fileoutputstream.close();
//
                             //       txvPath.setText(colisaoTransito.getGravacaoObservacoes().getTitulo());
                             //       buttonPlayLastRecordAudio.setEnabled(true);
                             //   }
                             //   catch (IOException e)
                             //   {
                             //       Sobrescrever = false;
                             //       buttonPlayLastRecordAudio.setEnabled(false);
                             //       txvPath.setText("Não há arquivo");
                             //   }
                           // }
                        }
                }
            }
            else
                {
                    // A pasta já existeif(ocorrenciaTransito.getGravacaoConclusao() != null){
                    File file = new File(ocorrenciaTransito.getGravacaoConclusao().getArquivo());
                    if(file.exists())
                    {
                        Sobrescrever = true;
                        buttonPlayLastRecordAudio.setEnabled(true);
                        txvPath.setText(file.getName());
                    }
                    else
                    {
                        Sobrescrever = false;
                        buttonPlayLastRecordAudio.setEnabled(true);
                        txvPath.setText("Não há arquivo");
                    }}
            }

        return view;
    }

    private void AssociarLayout(View view)
    {

        buttonStart = (LinearLayout) view.findViewById(R.id.btn_dialog_REC);
        buttonStop = (LinearLayout)  view.findViewById(R.id.btn_dialog_Stop);
        buttonPlayLastRecordAudio = (LinearLayout)  view.findViewById(R.id.btn_dialog_Play);
        buttonStopPlayingRecording = (LinearLayout)  view.findViewById(R.id.btn_dialog_Stop_Reproduction);
        txvPath = (TextView) view.findViewById(R.id.txv_Gravacao_Path);

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);
    }

    private void CarregarArquivo()
    {

    }


    @Override

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {

        super.onViewCreated(view, savedInstanceState);

        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(checkPermission())
                {
                    File folder = new File(path);

                    for(File file: folder.listFiles())
                        if (!file.isDirectory())
                            file.delete();


                    boolean success = true;
                    if (!folder.exists()) {
                        success = folder.mkdirs();
                    }
                    if (success)
                    {
                        pathArquivo = path+ "/" +
                                        DateFormat.format("yyyy_MM_dd hh-mm-ss",Calendar.getInstance().getTime()).toString() + ".3gp";
                    }
                    MediaRecorderReady();
                    try
                    {
                        mediaRecorder.prepare();
                        mediaRecorder.start();
                    } catch (IllegalStateException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e)
                    {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    buttonStart.setEnabled(false);
                    buttonStop.setEnabled(true);

                    Toast.makeText(activity, "Início de gravação",  Toast.LENGTH_LONG).show();
                }
                else
                {
                    requestPermission();
                }

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                mediaRecorder.stop();
                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);

                File f  = new File(pathArquivo);
                if(f.exists())
                {
//                    try
//                    {
                        if(Colisao)
                        {
                            colisaoTransito.setGravacaoObservacoes(new Gravacao(f.getName(), pathArquivo));
                            colisaoTransito.getGravacaoObservacoes().save();
                            colisaoTransito.save();
                        }
                         else
                         {
                             ocorrenciaTransito.setGravacaoConclusao(new Gravacao(f.getName(), pathArquivo));
                             ocorrenciaTransito.getGravacaoConclusao().save();
                             ocorrenciaTransito.save();
                         }
                         //Arquivo = FileUtil.fileToBytes(f);

//                    } catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
                }

                Toast.makeText(activity, "Gravação Concluída",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException {

                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();

                try
                {
                    if(Colisao)
                    {
                        if(colisaoTransito.getGravacaoObservacoes() != null)
                        {
                            File file = new File(colisaoTransito.getGravacaoObservacoes().getArquivo());

                            if(file.exists())

                                mediaPlayer.setDataSource(colisaoTransito.getGravacaoObservacoes().getArquivo());
                          // else
                          // {
                          //     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
                          //     bos.write(colisaoTransito.getGravacaoObservacoes().getArquivo());
                          //     bos.flush();
                          //     bos.close();
                          //     mediaPlayer.setDataSource(file.getPath());
                          // }
                        }
                        else
                        {
                            mediaPlayer.setDataSource(pathArquivo);
                        }
                    }
                    else
                    {
                        if(ocorrenciaTransito.getGravacaoConclusao() != null)
                        {
                            File file = new File(ocorrenciaTransito.getGravacaoConclusao().getArquivo());

                            if(file.exists())

                                mediaPlayer.setDataSource(ocorrenciaTransito.getGravacaoConclusao().getArquivo());
//                            else
//                            {
//                                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
//                                bos.write(ocorrenciaTransito.getGravacaoConclusao().getArquivo());
//                                bos.flush();
//                                bos.close();
//                                mediaPlayer.setDataSource(file.getPath());
//                            }
                        }
                        else
                        {
                            mediaPlayer.setDataSource(pathArquivo);
                        }
                    }


                 //   if(Sobrescrever)
                 //   mediaPlayer.setDataSource(pathArquivo);
                 //   else
                 //   mediaPlayer.setDataSource(ocorrenciaTransito.getGravacaoConclusao().getTitulo());

                   mediaPlayer.prepare();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                catch(Exception e)
                {

                }

                mediaPlayer.start();
                Toast.makeText(activity, "Reproduzindo Áudio",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);

                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    MediaRecorderReady();
                }
            }
        });


    }


    public void MediaRecorderReady()
    {
        mediaRecorder=new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(pathArquivo);

    }


    private void requestPermission()
    {
        ActivityCompat.requestPermissions(activity, new
                String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, RequestPermissionCode);
    }


    public boolean checkPermission() {

        int result = ContextCompat.checkSelfPermission(activity,
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(activity,
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case RequestPermissionCode: {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {

                } else {
                }
                return;
            }
        }
    }


}