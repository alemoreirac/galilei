package Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.pefoce.peritolocal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import Enums.SecaoImagem;
import Enums.Vida.Secao;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;

public class ImageUtil
{

//    public static byte[] getByteFromString(String s)
//    {
//        String[] byteValues = s.substring(1, s.length() - 1).split(",");
//        byte[] bytes = new byte[byteValues.length];
//
//        for (int i = 0, len = bytes.length; i < len; i++)
//        {
//            bytes[i] = Byte.parseByte(byteValues[i].trim());
//        }
//        return bytes;
//    }
//
//
//    public static byte[] GetByteFromBitmap(Bitmap bmp)
//    {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bmp.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//        return stream.toByteArray();
//    }
//
//    public static Bitmap convertByteArrayToBitmap(byte[] byteArrayToBeCOnvertedIntoBitMap)
//    {
//        Bitmap bitMapImage = BitmapFactory.decodeByteArray(
//                byteArrayToBeCOnvertedIntoBitMap, 0,
//                byteArrayToBeCOnvertedIntoBitMap.length);
//        return bitMapImage;
//    }
//
//    public static Bitmap byteToBitmap(String s)
//    {
//        byte[] decodedString = Base64.decode(s, Base64.DEFAULT);
//        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
//        decodedString = null;
//        return bitmap;
//    }
//
//    public static String bitmapToString(Bitmap bitmap)
//    {
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
//        byte[] byteArray = byteArrayOutputStream.toByteArray();
//        String encoded = Base64.encodeToString(byteArray, Base64.DEFAULT);
//        byteArrayOutputStream = null;
//        return encoded;
//    }

    public static Bitmap gerarImagens(EnvolvidoVida envolvidoVida, SecaoImagem parte, Context ctx)
    {
        ArrayList<Lesao> lesaoList = new ArrayList<>();

        int width, height;

        if (parte.equals(SecaoImagem.CABECA_FEMININO_DIREITA) || parte.equals(SecaoImagem.CABECA_FEMININO_ESQUERDA) ||
                parte.equals(SecaoImagem.CABECA_MASCULINO_ESQUERDA) || parte.equals(SecaoImagem.CABECA_MASCULINO_DIREITA))
        {
//            width = 327;
//            height = 400;
            width = 531;
            height = 650;
        } else
        {
            width = 327;
            height = 650;
        }

        //List<Bitmap> imagens = new ArrayList<Bitmap>();

        Context context = ctx;
        Bitmap molde = null;
        try
        {
            List<LesaoEnvolvido> lesaoEnvolvidoList = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", envolvidoVida.getId().toString());
            for (LesaoEnvolvido le : lesaoEnvolvidoList)
            {
                if (le.getLesao() != null)
                {
                    if (le.getLesao().getSecaoLesao() != null)
                    {
                        switch (parte)
                        {
                            case CABECA_FEMININO_DIREITA:
                            case CABECA_MASCULINO_DIREITA:
                                if (le.getLesao().getSecaoLesao().equals(Secao.PARIETAL_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.AURICULAR_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.FRONTAL_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.OCULAR_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.MALAR_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.NASAL_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.BUCAL_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.MENTONIANA_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.CAROTIDIANA_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.OCCIPITAL_DIREITA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.CERVICAL_DIREITA))
                                    lesaoList.add(le.getLesao());
                                break;

                            case CABECA_FEMININO_ESQUERDA:
                            case CABECA_MASCULINO_ESQUERDA:
                                if (le.getLesao().getSecaoLesao().equals(Secao.PARIETAL_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.AURICULAR_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.FRONTAL_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.OCULAR_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.MALAR_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.NASAL_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.BUCAL_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.MENTONIANA_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.CAROTIDIANA_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.OCCIPITAL_ESQUERDA) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.CERVICAL_ESQUERDA))
                                    lesaoList.add(le.getLesao());
                                break;

                            case ANTERIOR_FEMININO:
                            case ANTERIOR_MASCULINO:

                                if (!le.getLesao().getSecaoLesao().equals(Secao.PARIETAL_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.AURICULAR_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.FRONTAL_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.OCULAR_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.MALAR_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.NASAL_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.BUCAL_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.MENTONIANA_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.CAROTIDIANA_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.OCCIPITAL_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.CERVICAL_DIREITA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.PARIETAL_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.AURICULAR_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.FRONTAL_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.OCULAR_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.MALAR_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.NASAL_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.BUCAL_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.MENTONIANA_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.CAROTIDIANA_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.OCCIPITAL_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.CERVICAL_ESQUERDA) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.ESCAPULAR_ESQUERDO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.CLAVICULAR_POSTERIOR_ESQUERDO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.LOMBAR_ESQUERDO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.ILIACO_POSTERIOR_ESQUERDO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.GLUTEO_ESQUERDO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.ESCAPULAR_DIREITO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.CLAVICULAR_POSTERIOR_DIREITO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.LOMBAR_DIREITO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.ILIACO_POSTERIOR_DIREITO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.GLUTEO_DIREITO) &&
                                        !le.getLesao().getSecaoLesao().equals(Secao.ANUS))
                                    lesaoList.add(le.getLesao());
                                break;

                            case POSTERIOR_FEMININO:
                            case POSTERIOR_MASCULINO:
                                if (le.getLesao().getSecaoLesao().equals(Secao.ESCAPULAR_ESQUERDO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.CLAVICULAR_POSTERIOR_ESQUERDO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.LOMBAR_ESQUERDO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.ILIACO_POSTERIOR_ESQUERDO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.GLUTEO_ESQUERDO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.ESCAPULAR_DIREITO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.CLAVICULAR_POSTERIOR_DIREITO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.LOMBAR_DIREITO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.ILIACO_POSTERIOR_DIREITO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.GLUTEO_DIREITO) ||
                                        le.getLesao().getSecaoLesao().equals(Secao.ANUS))

                                       lesaoList.add(le.getLesao());
                                break;
                        }
                    }
                }
            }
            molde = Picasso.with(context).load(R.drawable.molde_vazio).resize(width, height).get();

            Lesao lesaoExcepcional = null;


            for (Lesao l : lesaoList)
            {
                molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(SecaoDrawableUtil.findByOrdinal(l.getSecaoLesao().ordinal(), envolvidoVida.getGenero().getValor())).resize(width, height).get());
                if (l.getSecaoLesao().equals(Secao.GENITAL) || l.getSecaoLesao().equals(Secao.ANUS))
                    lesaoExcepcional = l;
            }

            switch (parte)
            {
                case CABECA_FEMININO_DIREITA:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_feminino_rosto_direito).resize(width, height).get());
                    break;
                case CABECA_FEMININO_ESQUERDA:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_feminino_rosto_esquerdo).resize(width, height).get());
                    break;
                case CABECA_MASCULINO_DIREITA:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_masculino_rosto_direito).resize(width, height).get());
                    break;
                case CABECA_MASCULINO_ESQUERDA:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_masculino_rosto_esquerdo).resize(width, height).get());
                    break;
                case ANTERIOR_MASCULINO:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_masculino_frontal).resize(width, height).get());
                    break;
                case POSTERIOR_MASCULINO:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_masculino_costas).resize(width, height).get());
                    break;
                case ANTERIOR_FEMININO:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_feminino_frontal).resize(width, height).get());
                    break;
                case POSTERIOR_FEMININO:
                    molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(R.drawable.molde_feminino_costas).resize(width, height).get());
                    break;

            }

            if (lesaoExcepcional != null)

                molde = createSingleImageFromMultipleImages(molde, Picasso.with(context).load(SecaoDrawableUtil.findByOrdinal(lesaoExcepcional.getSecaoLesao().ordinal(), envolvidoVida.getGenero().getValor())).resize(width, height).get());


            return escreverNome(molde, envolvidoVida.getNome());

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap createSingleImageFromMultipleImages(Bitmap firstImage, Bitmap secondImage)
    {
        Bitmap result = Bitmap.createBitmap(firstImage.getWidth(), firstImage.getHeight(), firstImage.getConfig());
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(firstImage, 0f, 0f, null);
        canvas.drawBitmap(secondImage, 0f, 0f, null);
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static Bitmap Overlay(Bitmap Bitmap1, Bitmap Bitmap2)
    {
        Bitmap bmp1 = Bitmap.createScaledBitmap(Bitmap2, Bitmap1.getWidth(), Bitmap1.getHeight(), true);
        Bitmap bmp2 = Bitmap.createBitmap(Bitmap1.getWidth(), Bitmap1.getHeight(), Bitmap1.getConfig());
        Paint localPaint = new Paint();
        localPaint.setAlpha(255);

        Canvas localCanvas = new Canvas(bmp2);
        Matrix localMatrix = new Matrix();

        localCanvas.drawBitmap(Bitmap1, localMatrix, null);
        localCanvas.drawBitmap(bmp1, localMatrix, localPaint);

        //  bmp2.recycle();
        bmp1.recycle();
        System.gc();
        return bmp2;
    }

    private static Bitmap escreverNome(Bitmap bmp, String nome)
    {
        Canvas cs2 = new Canvas(bmp);
        Paint tPaint2 = new Paint();
        tPaint2.setTextSize(20);
        tPaint2.setColor(Color.BLACK);
        tPaint2.setStyle(Paint.Style.FILL);
        cs2.drawBitmap(bmp, 0f, 0f, null);
        float height2 = bmp.getHeight();


        if(nome.length()<40)
        cs2.drawText(nome, 2f, height2 - 15f, tPaint2); // 15f is to put space between top edge and the text, if you want to change it, you can
        else
        cs2.drawText(nome.substring(0,36)+"...", 2f, height2 - 15f, tPaint2); // 15f is to put space between top edge and the text, if you want to change it, you can
        return bmp;
    }
}