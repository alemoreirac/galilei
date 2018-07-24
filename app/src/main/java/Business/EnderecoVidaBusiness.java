import java.util.ArrayList;
import java.util.List;

import Model.Vida.EnderecoVida;
import Model.Vida.OcorrenciaEnderecoVida;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class EnderecoVidaBusiness
{
    public static List<EnderecoVida> findEnderecoByOcorrenciaId(Long id)
    {
        ArrayList<EnderecoVida> enderecoVida = new ArrayList<>();
        List<OcorrenciaEnderecoVida> ocorrenciaEnderecoVida = OcorrenciaEnderecoVida.find(OcorrenciaEnderecoVida.class,"ocorrencia_vida",id.toString());

        for(OcorrenciaEnderecoVida oev : ocorrenciaEnderecoVida)

            enderecoVida.add(oev.getEnderecoVida());


        return enderecoVida;
    }
}
