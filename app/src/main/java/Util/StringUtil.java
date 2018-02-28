package Util;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pefoce on 20/02/2018.
 */

public class StringUtil
{


    public static String normalize(String entrada)
    {
        entrada = Normalizer.normalize(entrada, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

        entrada = entrada.replace(" ", "_");

        entrada = entrada.toLowerCase();

        return entrada;
    }

    public static List<String> tokenize(String entrada)
    {
        List<String> tokens = new ArrayList<>();

        for (String s : entrada.split("_"))
        {
            if (!(s.length() == 2 && s.charAt(0) == 'd')
                    && (!s.contains("setor")))
            {
                if (s.contains("direit") || s.contains("esquerd")
                        || s.contains("feminin") || s.contains("masculin"))
                    tokens.add(s.substring(0, s.length() - 1));

                else
                    tokens.add(s);
            }
        }

        return tokens;
    }
}
