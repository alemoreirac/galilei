package Control;

import Model.Bairro;
import Model.DadosTerritoriais;

/**
 * Created by Pefoce on 14/08/2018.
 */

public class DadosTerritoriaisBusiness
{
    public static DadosTerritoriais findByBairro(String descricaoBairro)
    {
        try
        {
            Bairro b = BairroBusiness.findByDescricao(descricaoBairro);
            return DadosTerritoriais.find(DadosTerritoriais.class, "bairro = ?", b.getId().toString()).get(0);
        }
        catch (Exception e)
        {
            return null;
        }
    }


}
