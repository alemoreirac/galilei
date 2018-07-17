package Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.pefoce.peritolocal.R;
import com.frosquivel.magicalcamera.MagicalCamera;

/**
 * Created by Pefoce on 03/01/2018.
 */

public class TipoFotoDialog
{
    static Fragment fragment;
    static Context context;
    static ImageView imgvCamera, imgvGaleria;
    static Dialog dialog;
    static MagicalCamera magicalCamera;

    public static void show(Fragment f, Context ctx, MagicalCamera mc)
    {
        fragment = f;
        context = ctx;
        magicalCamera = mc;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_tipo_foto);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Escolha a fonte da Fotografia");
        dialog.show();

        AssociarLayout();
        AssociarEventos();
    }

    private static void AssociarLayout()
    {
        imgvCamera = (ImageView) dialog.findViewById(R.id.imgv_Opcao_camera);
        imgvGaleria = (ImageView) dialog.findViewById(R.id.imgv_Opcao_galeria);
    }

    public static void AssociarEventos()
    {
        imgvCamera.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                magicalCamera.takeFragmentPhoto(fragment);
                dialog.dismiss();
            }
        });

        imgvGaleria.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                magicalCamera.selectFragmentPicture(fragment, "Selecione Uma Imagem");
                dialog.dismiss();
            }
        });
    }
}