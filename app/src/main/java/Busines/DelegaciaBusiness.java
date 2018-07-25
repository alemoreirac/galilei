package Busines;

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
}
