package Control.Transito;

import java.util.ArrayList;
import java.util.List;

import Model.Transito.EnvolvidoColisao;
import Model.Transito.EnvolvidoTransito;
import Model.Transito.OcorrenciaTransitoEnvolvido;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class EnvolvidoTransitoBusiness
{
    public static List<EnvolvidoTransito> findEnvolvidoByOcorrenciaId(Long id)
    {
        ArrayList<EnvolvidoTransito> envolvidosTransito = new ArrayList<>();
        List<OcorrenciaTransitoEnvolvido> ocorrenciaTransitoEnvolvido = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class,"ocorrencia_transito = ?",id.toString());

        for(OcorrenciaTransitoEnvolvido oet : ocorrenciaTransitoEnvolvido)
            envolvidosTransito.add(oet.getEnvolvidoTransito());

        return envolvidosTransito;
    }

    public static List<EnvolvidoTransito> findEnvolvidosByColisao(Long colisaoId)
    {
        ArrayList< EnvolvidoTransito> pedestres = new ArrayList<>();

        List<EnvolvidoColisao> envolvidosColisao = EnvolvidoColisao.find(EnvolvidoColisao.class,"colisao_transito = ?",colisaoId.toString());

        for(EnvolvidoColisao ec : envolvidosColisao)
            pedestres.add(ec.getEnvolvidoTransito());

        return pedestres;
    }
}
