package Control.Vida;

import java.util.ArrayList;
import java.util.List;

import Model.Vida.EnderecoVida;
import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.OcorrenciaVidaEndereco;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class EnvolvidoVidaBusiness
{
    public static List<EnvolvidoVida> findEnvolvidosByOcorrenciaId(Long id)
    {
        ArrayList<EnvolvidoVida> envolvidosVida = new ArrayList<>();
        List<OcorrenciaEnvolvidoVida> ocorrenciaVidaEnvolvido = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class,"ocorrencia_vida = ?",id.toString());

        for(OcorrenciaEnvolvidoVida oev : ocorrenciaVidaEnvolvido)

            envolvidosVida.add(oev.getEnvolvidoVida());

        return envolvidosVida;
    }
}