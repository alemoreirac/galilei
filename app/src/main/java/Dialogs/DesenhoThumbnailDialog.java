package Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.pefoce.peritolocal.R;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.squareup.picasso.Picasso;

import java.io.File;

import Model.Ocorrencia;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Util.StringUtil;

/**
 * Created by Pefoce on 28/06/2018.
 */

public class DesenhoThumbnailDialog

{
 static  Context context;
 static  ImageView imgvDesenho;
 static  Dialog dialog;

    public static void criarDialog( Context ctx, EnvolvidoVida envolvidoVida, Long ocorrenciaVidaId)
    {

        context = ctx;

        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_preview_desenho);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setTitle("Escolha a fonte da Fotografia");
        dialog.show();



        AssociarLayout();
        AssociarEventos();

        OcorrenciaVida ocorrenciaVida = OcorrenciaVida.findById(OcorrenciaVida.class,ocorrenciaVidaId);

        Ocorrencia ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

        String path = Environment.getExternalStorageDirectory() +
                "/Galilei/" + ocorrencia.getPerito().getId() + "_" + ocorrencia.getPerito().getNome()
                + "/Vida/" + ocorrenciaVida.getDataPath() + "/" + ocorrenciaVida.getNumIncidencia() +
                "/Fotos_Envolvidos/" + envolvidoVida.getId().toString() + "_" + StringUtil.normalize(envolvidoVida.getNome()) + ".jpeg";


        File imgFile = new File(path);
        if (imgFile.exists())
        {
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgvDesenho.setImageBitmap(myBitmap);
        }
        else
            Picasso.with(ctx).load(R.drawable.placeholder_error).into(imgvDesenho);

    }


    private static void AssociarLayout()
    {
        imgvDesenho = (ImageView) dialog.findViewById(R.id.imgv_dialog_Desenho);
    }

    public static void AssociarEventos()
    {
        imgvDesenho.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {

                dialog.dismiss();
            }
        });
    }
}
