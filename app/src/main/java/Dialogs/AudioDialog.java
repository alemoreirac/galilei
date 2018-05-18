package Dialogs;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;

import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pefoce.peritolocal.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import Enums.TipoOcorrencia;
import Model.Transito.ColisaoTransito;
import Model.Gravacao;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransito;
import Model.Vida.EnderecoVida;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Util.SecaoDrawableUtil;
import Util.StringUtil;

import static android.Manifest.permission.RECORD_AUDIO;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

/**
 * Created by Pefoce on 21/08/2017.
 */

public class AudioDialog implements ActivityCompat.OnRequestPermissionsResultCallback
{

    public static Dialog dialog;

  static public Activity activity = null;
  static public boolean Sobrescrever = false;
  static public LinearLayout buttonStart, buttonStop, buttonPlayLastRecordAudio, buttonStopPlayingRecording;
  static public String pathArquivo = null;
  static public MediaRecorder mediaRecorder;
  static public   final int RequestPermissionCode = 2;
  static public MediaPlayer mediaPlayer;
  static public TextView txvPath = null;
  static public Bundle bundle;
  static public OcorrenciaTransito ocorrenciaTransito;
  static Ocorrencia ocorrencia;
  static public ColisaoTransito colisaoTransito;
  static public boolean Colisao;
  static EnvolvidoVida envolvidoVida;
  static String path;
  static TipoOcorrencia tipoOcorrencia;
  static public EnderecoVida enderecoVida;
  static public OcorrenciaVida ocorrenciaVida;
  static String secaoVida;

    public static void show(Activity a,Bundle bundle)
    {
        dialog = new Dialog(a);
        dialog.setContentView(R.layout.dialog_audio);
        dialog.getWindow().setLayout(500,300);
        dialog.setTitle("Gravações");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        Bundle bd = bundle;

        activity = a;

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, bd.getLong("OcorrenciaId"));

        AssociarLayout(dialog);

        AssociarEventos();

        tipoOcorrencia = ocorrencia.getTipoOcorrencia();

        switch (tipoOcorrencia)
        {
            case TRANSITO:

                ocorrenciaTransito = OcorrenciaTransito.find(OcorrenciaTransito.class, "ocorrencia_id = ?", ocorrencia.getId().toString()).get(0);

                Colisao = bd.getBoolean("Colisao");

                if (Colisao)
                {
                    colisaoTransito = ColisaoTransito.findById(ColisaoTransito.class, bd.getLong("ColisaoId"));

                    path = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransito.getDataPath() + "/" + ocorrenciaTransito.getNumIncidencia() + "/Audio_Colisao/";
                } else
                    path = Environment.getExternalStorageDirectory() +
                            "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Transito/" + ocorrenciaTransito.getDataPath() + "/" + ocorrenciaTransito.getNumIncidencia() + "/Audio_Geral/";

                break;

            case VIDA:

                ocorrenciaVida = OcorrenciaVida.find(OcorrenciaVida.class, "ocorrencia_id = ?", ocorrencia.getId().toString()).get(0);

                secaoVida = bd.getString("SecaoVida");

                switch (secaoVida)
                {
                    case "Endereco":

                        enderecoVida = EnderecoVida.find(EnderecoVida.class, "ocorrencia_id = ?", ocorrenciaVida.getId().toString()).get(0);

                        path = Environment.getExternalStorageDirectory() +
                                "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() + "/Audio_Endereco/";
                        break;

                    case "Envolvido":

                        envolvidoVida = EnvolvidoVida.findById(EnvolvidoVida.class, bd.getLong("EnvolvidoId"));

                        path = Environment.getExternalStorageDirectory() +
                                "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome() + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() + "/Audio_Envolvido_" + StringUtil.normalize(envolvidoVida.getNome()) + "/";
                        break;
                }

                break;
            default:
                break;
        }
        File folder = null;
        try
        {
            folder = new File(path);
        } catch (Exception e)
        {
            folder.mkdirs();
        }

        if (!folder.exists())
        {
            folder.mkdirs();
        } else
        {
            switch (tipoOcorrencia)
            {
                case TRANSITO:
                    if (Colisao)
                    {
                        if (colisaoTransito.getGravacaoObservacoes() != null)
                        {
                            File file = new File(colisaoTransito.getGravacaoObservacoes().getArquivo());
                            if (file.exists())
                            {
                                Sobrescrever = true;
                                buttonPlayLastRecordAudio.setEnabled(true);
                                txvPath.setText(file.getName());
                            } else
                            {
                                txvPath.setText("Não há arquivo");
                            }
                        }
                    } else
                    {
                        // A pasta já existeif(ocorrenciaTransito.getGravacaoConclusao() != null){
                        File file = new File(ocorrenciaTransito.getGravacaoConclusao().getArquivo());
                        if (file.exists())
                        {
                            Sobrescrever = true;
                            buttonPlayLastRecordAudio.setEnabled(true);
                            txvPath.setText(file.getName());
                        } else
                        {
                            Sobrescrever = false;
                            buttonPlayLastRecordAudio.setEnabled(false);
                            txvPath.setText("Não há arquivo");
                        }
                    }
                    break;
                case VIDA:
                    switch (secaoVida)
                    {
                        case "Endereco":

                            if (enderecoVida.getGravacaoEndereco() != null)
                            {
                                File file = new File(enderecoVida.getGravacaoEndereco().getArquivo());
                                if (file.exists())
                                {
                                    Sobrescrever = true;
                                    buttonPlayLastRecordAudio.setEnabled(true);
                                    txvPath.setText(file.getName());
                                } else
                                {
                                    txvPath.setText("Não há arquivo");
                                }
                            }
                            else
                            {
                                txvPath.setText("Não há arquivo");
                                buttonPlayLastRecordAudio.setEnabled(false);
                            }
                            break;
                        case "Envolvido":

                            if (envolvidoVida.getGravacaoEnvolvido() != null)
                            {
                                File file = new File(envolvidoVida.getGravacaoEnvolvido().getArquivo());
                                if (file.exists())
                                {
                                    Sobrescrever = true;
                                    buttonPlayLastRecordAudio.setEnabled(true);
                                    txvPath.setText(file.getName());
                                } else
                                {
                                    txvPath.setText("Não há arquivo");
                                }
                            }
                            else
                            {
                                txvPath.setText("Não há arquivo");
                                buttonPlayLastRecordAudio.setEnabled(false);
                            }

                            break;
                    }

                    break;
            }
        }

    }

    private static void AssociarLayout(Dialog d)
    {

        buttonStart = (LinearLayout) d.findViewById(R.id.btn_dialog_REC);
        buttonStop = (LinearLayout) d.findViewById(R.id.btn_dialog_Stop);
        buttonPlayLastRecordAudio = (LinearLayout) d.findViewById(R.id.btn_dialog_Play);
        buttonStopPlayingRecording = (LinearLayout) d.findViewById(R.id.btn_dialog_Stop_Reproduction);
        txvPath = (TextView) d.findViewById(R.id.txv_Gravacao_Path);

        buttonStop.setEnabled(false);
        buttonPlayLastRecordAudio.setEnabled(false);
        buttonStopPlayingRecording.setEnabled(false);


    }

    private static void AssociarEventos()
    {
        buttonStart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (checkPermission())
                {
                    File folder = new File(path);

                    for (File file : folder.listFiles())
                        if (!file.isDirectory())
                            file.delete();


                    boolean success = true;
                    if (!folder.exists())
                    {
                        success = folder.mkdirs();
                    }
                    if (success)
                    {
                        pathArquivo = path + "/" +
                                DateFormat.format("yyyy_MM_dd hh-mm-ss", Calendar.getInstance().getTime()).toString() + ".3gp";
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

                    Toast.makeText(activity, "Início de gravação", Toast.LENGTH_LONG).show();
                } else
                {
                    requestPermission();
                }

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                mediaRecorder.stop();
                mediaRecorder.reset();
                mediaRecorder.release();
                buttonStop.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);

                File f = new File(pathArquivo);
                if (f.exists())
                {
                    switch (tipoOcorrencia)
                    {
                        case TRANSITO:
                            if (Colisao)
                            {
                                colisaoTransito.setGravacaoObservacoes(new Gravacao(f.getName(), pathArquivo));
                                colisaoTransito.getGravacaoObservacoes().save();
                                colisaoTransito.save();
                            } else
                            {
                                ocorrenciaTransito.setGravacaoConclusao(new Gravacao(f.getName(), pathArquivo));
                                ocorrenciaTransito.getGravacaoConclusao().save();
                                ocorrenciaTransito.save();
                            }
                            break;
                        case VIDA:
                            switch (secaoVida)
                            {
                                case "Endereco":
                                    enderecoVida.setGravacaoEndereco(new Gravacao(f.getName(), pathArquivo));
                                    enderecoVida.getGravacaoEndereco().save();
                                    enderecoVida.save();
                                    break;
                                case "Envolvido":
                                    envolvidoVida.setGravacaoEnvolvido(new Gravacao(f.getName(), pathArquivo));
                                    envolvidoVida.getGravacaoEnvolvido().save();
                                    envolvidoVida.save();
                                    break;
                            }
                            break;
                    }

                }

                Toast.makeText(activity, "Gravação Concluída",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonPlayLastRecordAudio.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {

                buttonStop.setEnabled(false);
                buttonStart.setEnabled(false);
                buttonStopPlayingRecording.setEnabled(true);

                mediaPlayer = new MediaPlayer();

                try
                {
                    switch (tipoOcorrencia)
                    {
                        case TRANSITO:
                            if (Colisao)
                            {
                                if (colisaoTransito.getGravacaoObservacoes() != null)
                                {
                                    File file = new File(colisaoTransito.getGravacaoObservacoes().getArquivo());
                                    if (file.exists())
                                        mediaPlayer.setDataSource(colisaoTransito.getGravacaoObservacoes().getArquivo());

                                } else
                                {
                                    mediaPlayer.setDataSource(pathArquivo);
                                }
                            } else
                            {
                                if (ocorrenciaTransito.getGravacaoConclusao() != null)
                                {
                                    File file = new File(ocorrenciaTransito.getGravacaoConclusao().getArquivo());

                                    if (file.exists())
                                        mediaPlayer.setDataSource(ocorrenciaTransito.getGravacaoConclusao().getArquivo());
                                } else
                                    mediaPlayer.setDataSource(pathArquivo);
                            }

                            break;
                        case VIDA:

                            switch (secaoVida)
                            {
                                case "Endereco":
                                    if(enderecoVida.getGravacaoEndereco()!=null)
                                    {
                                        File file = new File(enderecoVida.getGravacaoEndereco().getArquivo());

                                        if (file.exists())
                                            mediaPlayer.setDataSource(enderecoVida.getGravacaoEndereco().getArquivo());
                                    }else
                                        mediaPlayer.setDataSource(pathArquivo);

                                    break;
                                case "Envolvido":
                                    if(envolvidoVida.getGravacaoEnvolvido()!=null)
                                    {
                                        File file = new File(envolvidoVida.getGravacaoEnvolvido().getArquivo());

                                        if (file.exists())
                                            mediaPlayer.setDataSource(envolvidoVida.getGravacaoEnvolvido().getArquivo());
                                    }else
                                        mediaPlayer.setDataSource(pathArquivo);

                                    break;
                            }
                            break;
                    }


                    mediaPlayer.prepare();
                } catch (IOException e)
                {
                    e.printStackTrace();
                } catch (Exception e)
                {

                }

                mediaPlayer.start();
                Toast.makeText(activity, "Reproduzindo Áudio",
                        Toast.LENGTH_LONG).show();
            }
        });

        buttonStopPlayingRecording.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                buttonStop.setEnabled(false);
                buttonStart.setEnabled(true);
                buttonStopPlayingRecording.setEnabled(false);
                buttonPlayLastRecordAudio.setEnabled(true);

                if (mediaPlayer != null)
                {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    MediaRecorderReady();
                }
            }
        });

    }


    public static void MediaRecorderReady()
    {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(pathArquivo);

    }


    private static void requestPermission()
    {
        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, RequestPermissionCode);
        dialog.dismiss();
    }


    public static boolean checkPermission()
    {
        int result = ContextCompat.checkSelfPermission(activity,
                WRITE_EXTERNAL_STORAGE);
        int result1 = ContextCompat.checkSelfPermission(activity,
                RECORD_AUDIO);
        return result == PackageManager.PERMISSION_GRANTED &&
                result1 == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case RequestPermissionCode:
            {

                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED)
                {

                } else
                {
                }
                return;
            }
        }
    }


}