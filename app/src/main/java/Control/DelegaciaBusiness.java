package Control;

import Model.Delegacia;


/**
 * Created by Pefoce on 25/07/2018.
 */

public class DelegaciaBusiness
{
    public static String findDescricaoById(Long id)
    {
        try
        {
            return Delegacia.findById(Delegacia.class,id).getDescricao();
        }
        catch (Exception e )
        {
            return "(Sem Delegacia)";
        }
    }
    public static Delegacia findByDescricao(String descricao)
    {
        try
        {
            return Delegacia.find(Delegacia.class,"descricao = ?", descricao).get(0);
        }
        catch (Exception e )
        {
            return null;
        }
    }

}
