package Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;

import com.example.pefoce.peritolocal.ManterPericiaTransito;
import com.example.pefoce.peritolocal.R;
import com.frosquivel.magicalcamera.MagicalCamera;

import Fragments.FragmentsTransito.GerenciarEnvolvido;
import Model.Transito.OcorrenciaTransito;

/**
 * Created by Pefoce on 03/01/2018.
 */

public class TipoFotoDialog
{
    Fragment fragment;
    Activity activity = null;
    Context context;
    ImageView imgvCamera, imgvGaleria;
   // OcorrenciaTransito ot;
    Dialog dialog;
MagicalCamera magicalCamera;
    public TipoFotoDialog(Fragment f,Activity a, MagicalCamera mc)
    {
        fragment = f;
        context = a;
        magicalCamera = mc;
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_tipo_foto);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setTitle("Escolha a fonte da Fotografia");
        dialog.show();

        activity = a;
     //   ot = ((ManterPericiaTransito) context).ocorrenciaTransito;

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
                magicalCamera.takeFragmentPhoto(fragment);
                dialog.dismiss();}
        });

        imgvGaleria.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) throws IllegalArgumentException,
                    SecurityException, IllegalStateException
            {
                magicalCamera.selectFragmentPicture(fragment, "Selecione Uma Imagem");
                dialog.dismiss();}
        });
    }
}