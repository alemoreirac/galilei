package Control;

import Model.Bairro;

/**
 * Created by Pefoce on 14/08/2018.
 */

public class BairroBusiness
{
    public static Bairro findByDescricao(String descricao)
    {
        try
        {
            return Bairro.find(Bairro.class, "descricao = ?", descricao).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }


}
