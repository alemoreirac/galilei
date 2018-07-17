package Util;

import java.util.ArrayList;
import java.util.List;

import Model.Transito.Dano;

/**
 * Created by Pefoce on 28/06/2018.
 */

public class ComparatorUtil
{
    public static boolean containsDano(Dano dano, List<Dano > listDanos)
    {
        for(Dano d : listDanos)
        {
            if (d.setor.equals(dano.setor) &&
                    d.terco.equals(dano.terco) &&
                    d.tipo.equals(dano.tipo) &&
                    d.compatibilidade == dano.compatibilidade)
                return true;
        }

        return false;
    }
    public static boolean containsId(Dano dano, List<Dano > listDanos)
    {
        for(Dano d : listDanos)
        {
            if (d.setor.equals(dano.setor) &&
                    d.terco.equals(dano.terco) &&
                    d.tipo.equals(dano.tipo) &&
                    d.compatibilidade == dano.compatibilidade &&
                    d.getId().equals(dano.getId()))
                return true;
        }

        return false;
    }
}
