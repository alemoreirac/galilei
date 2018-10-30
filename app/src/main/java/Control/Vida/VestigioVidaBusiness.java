package Control.Vida;

import java.util.ArrayList;
import java.util.List;

import Model.Vida.EnvolvidoVida;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.VestigioVida;
import Model.Vida.VestigioVidaOcorrencia;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class VestigioVidaBusiness
{
    public static List<VestigioVida> findVestigioByOcorrenciaId(Long id)
    {
        ArrayList<VestigioVida> vestigiosVida = new ArrayList<>();
        List<VestigioVidaOcorrencia> vestigioOcorrencia = VestigioVidaOcorrencia.find(VestigioVidaOcorrencia.class,"ocorrencia_vida = ?",id.toString());

        for(VestigioVidaOcorrencia vvo : vestigioOcorrencia)

            vestigiosVida.add(vvo.getVestigioVida());

        return vestigiosVida;
    }
}