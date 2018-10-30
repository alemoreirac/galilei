package Control.Vida;

import java.util.ArrayList;
import java.util.List;

import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Model.Vida.VestigioVida;
import Model.Vida.VestigioVidaOcorrencia;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class LesaoBusiness
{
    public static List<Lesao> findLesaoByEnvolvido(Long id)
    {
        ArrayList<Lesao> lesoes = new ArrayList<>();
        List<LesaoEnvolvido> lesoesEnvolvido = LesaoEnvolvido.find(LesaoEnvolvido.class,"envolvido_vida = ?",id.toString());

        for(LesaoEnvolvido le : lesoesEnvolvido)

            lesoes.add(le.getLesao());

        return lesoes;
    }
}