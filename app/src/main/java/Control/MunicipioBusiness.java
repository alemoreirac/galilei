package Control;

import Model.Municipio;

/**
 * Created by Pefoce on 14/08/2018.
 */

public class MunicipioBusiness
{
    public static Municipio findByDescricao(String descricao)
    {
        try
        {
            return Municipio.find(Municipio.class, "descricao = ?", descricao).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
