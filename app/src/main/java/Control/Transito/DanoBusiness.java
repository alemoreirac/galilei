package Control.Transito;

import java.util.ArrayList;
import java.util.List;

import Model.Transito.Dano;
import Model.Transito.DanoVeiculo;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class DanoBusiness
{
    public static List<Dano> findDanosByVeiculo(Long id)
    {
        ArrayList<Dano> danos = new ArrayList<>();

        List<DanoVeiculo> danosVeiculo = DanoVeiculo.find(DanoVeiculo.class,"veiculo = ?",id.toString());

        for(DanoVeiculo dv : danosVeiculo)

            danos.add(dv.getDano());

        return danos;
    }
}

