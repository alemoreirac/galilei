package Util;

import android.location.Location;

import com.squareup.picasso.Picasso;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pefoce on 20/02/2018.
 */

public class StringUtil
{
    public static boolean isNotNullAndEmpty(String value)
    {
        if (value == null)
            return false;
        else if (value.isEmpty())
            return false;

        return true;
    }

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

    public static String converterLatitude(double latitude)
    {
        StringBuilder builderLatitude = new StringBuilder();
        String latitudeDegrees = Location.convert(Math.abs(latitude), Location.FORMAT_SECONDS);
        String[] latitudeSplit = latitudeDegrees.split(":");
        builderLatitude.append(latitudeSplit[0]);
        builderLatitude.append("°");
        builderLatitude.append(latitudeSplit[1]);
        builderLatitude.append("'");
        builderLatitude.append(latitudeSplit[2]);
        builderLatitude.append("\"");

        if (latitude < 0)
            builderLatitude.append("S");

        else
            builderLatitude.append("N");

        return builderLatitude.toString();
    }

    public static String converterLongitude(double longitude)
    {

        StringBuilder builderLongitude = new StringBuilder();

        String longitudeDegrees = Location.convert(Math.abs(longitude), Location.FORMAT_SECONDS);
        String[] longitudeSplit = longitudeDegrees.split(":");
        builderLongitude.append(longitudeSplit[0]);
        builderLongitude.append("°");
        builderLongitude.append(longitudeSplit[1]);
        builderLongitude.append("'");
        builderLongitude.append(longitudeSplit[2]);
        builderLongitude.append("\"");

        if (longitude < 0)
        {
            builderLongitude.append("W");
        } else
        {
            builderLongitude.append("E");
        }
        return builderLongitude.toString();
    }


    public static String checkValue(String value, int maxLength, String emptyValue)
    {
        if (value == null || value.isEmpty())
            return emptyValue;

        if (maxLength > 0)
        {
            if (value.length() > maxLength)
                return (value.substring(0, maxLength) + "...");
        }

        return value;
    }
}
