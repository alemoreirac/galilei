package Util;

import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.example.pefoce.peritolocal.R;

import java.lang.reflect.Field;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Enums.Genero;
import Enums.Vida.Secao;

import static Util.StringUtil.normalize;
import static Util.StringUtil.tokenize;
import static java.util.stream.Collectors.toList;

/**
 * Created by Pefoce on 27/01/2018.
 */

public class SecaoDrawableUtil
{

    public static List<String> todosDrawables = getAllDrawablesString();

    public static int getOrdinal(String drawable)
    {
        return Integer.valueOf(drawable.split("_")[1]);
    }

    public static int findByOrdinal(int ordinal, String genero)
    {
        if(genero==Genero.NAO_IDENTIFICADO.getValor())
            genero = Genero.MASCULINO.getValor();
        for (String d : todosDrawables)
        {
            if (getOrdinal(d) == ordinal)
            {
                if (d.contains(genero.substring(0, genero.length() - 1).toLowerCase()))
                    return getResId(d, Drawable.class);
            }
        }
        return 0;
    }


    public static int findIdByString(String secao_, String genero)
    {
        if (genero.equals(Genero.NAO_IDENTIFICADO.getValor()))
            genero = "masculin";
        else
            genero = normalize(genero.substring(0, genero.length() - 1));

        List<String> tokens = tokenize(normalize(secao_));

        if (secao_.equals(Secao.ILIACO_ANTERIOR_DIREITO))
            secao_.toString();

        String match = "";

        tokens.add(genero);
        ////            if (drawableName.equals("perna_superior_esquerda_masculina") && secao_.equals(Secao.PE_ESQUERDO.getValor()))
////            {
////                for (String token : tokens)
////                {
////                    if (token.charAt(token.length() - 1) == 'o' ||
////                            token.charAt(token.length() - 1) == 'a')
////
////                        token = token.substring(0, token.length() - 1);
////
////                    if (drawableName.contains(token))
////                        //|| drawableName.contains(genero))
////                        acertos++;
////                    else
////                    {acertos = 0;
////                        continue;}
////                }
////                if (acertos == tokens.size())
////                {
////                    if (drawableName.contains(genero.substring(0, genero.length() - 1)))
////                    {
////                        //if(tokenize(drawableName).size() == acertos)
////                        match = drawableName;
////                    }
////                    else
////                        acertos = 0;
////                } else
////                    acertos = 0;
////            }
////
////            for (String token : tokens)
////            {
////                if (token.charAt(token.length() - 1) == 'o' ||
////                        token.charAt(token.length() - 1) == 'a')
////
////                    token = token.substring(0, token.length() - 1);
////
////                if (drawableName.contains(token))
////                    //|| drawableName.contains(genero))
////                    acertos++;
////                else
////                {acertos = 0;
////                continue;}
////            }
////            if (acertos == tokens.size())
////            {
////                if (drawableName.contains(genero.substring(0, genero.length() - 1)))
////                {
////                    //if(tokenize(drawableName).size() == acertos)
////                            match = drawableName;
////                }
////                else
////                    acertos = 0;
////            } else
////                acertos = 0;
////        }
//                if (acertos == tokens.size())
//                {
//                    match = drawableName;
//                    break;
//                }
//                else
//                    acertos = 0;
//
//            }
//        }

        List<String> result = new ArrayList<>();

        result.addAll(todosDrawables);

        for (String t : tokens)
        {
            for (String res : result)
            {
                if (!res.contains(t))
                    result.remove(result.indexOf(res));
            }
//            for(int i = 0 ; i<result.size();i++)
//            {
//                if (!result.get(i).contains(t))
//                    result.remove(i);
//            }
            result.size();
        }

        if (result.size() == 1)
            match = result.get(0);

        System.gc();
        if (match == "")
            match.toString();

        return getResId(match, Drawable.class);
    }

    public static List<String> getAllDrawablesString()
    {
        Field[] drawablesFields = R.drawable.class.getFields();
        ArrayList<String> drawableNames = new ArrayList<>();

        for (Field field : drawablesFields)
        {
            try
            {
                if (field.getName().contains("masculin") ||
                        field.getName().contains("feminin"))
                {
                    normalize(field.getName());

                    drawableNames.add(field.getName());
                }

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return drawableNames;
    }

    public static int getResId(String resName, Class<?> c)
    {
        try
        {
            Field[] drawablesFields = R.drawable.class.getFields();
            for (Field f : drawablesFields)
            {
                if (f.getName().toString().equals(resName))
                    return f.getInt(f);
            }

            return R.drawable.molde_vazio;

        } catch (Exception e)
        {
            e.printStackTrace();

            return R.drawable.molde_vazio;
        }
    }
}
