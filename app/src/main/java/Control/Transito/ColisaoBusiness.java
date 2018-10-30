package Control.Transito;

import java.util.ArrayList;
import java.util.List;

import Model.Transito.ColisaoTransito;
import Model.Transito.EnvolvidoColisao;
import Model.Transito.OcorrenciaTransitoColisao;

public class ColisaoBusiness {
    public static List<String> findAllPedestresByColisao(Long colisaoId)
    {
        ArrayList< String> pedestres = new ArrayList<>();

        ColisaoTransito colisaoTransito = ColisaoTransito.findById(ColisaoTransito.class,colisaoId);


        if(colisaoTransito!=null)
        {
            List<EnvolvidoColisao> envolvidosColisao = EnvolvidoColisao.find(EnvolvidoColisao.class,"colisao_transito = ?", colisaoId.toString());

            for(EnvolvidoColisao ec : envolvidosColisao)
                pedestres.add(ec.getEnvolvidoTransito().getNome());
        }

        return pedestres;
    }
    public static String findAllPedestresByColisaoString(Long colisaoId)
    {
        ArrayList< String> pedestres = new ArrayList<>();
        String resultado = "";

        ColisaoTransito colisaoTransito = ColisaoTransito.findById(ColisaoTransito.class,colisaoId);

        if(colisaoTransito!=null)
        {
            List<EnvolvidoColisao> envolvidosColisao = EnvolvidoColisao.find(EnvolvidoColisao.class,"colisao_transito = ?", colisaoId.toString());

            for(EnvolvidoColisao ec : envolvidosColisao)
                pedestres.add(ec.getEnvolvidoTransito().getNome());
        }

        for(String s : pedestres)
            resultado += s+", ";

        if(resultado.endsWith(", "))
            return resultado.substring(0,resultado.length()-2);
        else
            return resultado;
    }

    public static List<ColisaoTransito> findColisoesByOcorrenciaId(Long id)
    {
        ArrayList<ColisaoTransito> coliseosTransito = new ArrayList<>();
        List<OcorrenciaTransitoColisao> ocorrenciaTransitoColisoes = OcorrenciaTransitoColisao.find(OcorrenciaTransitoColisao.class,"ocorrencia_transito = ?",id.toString());

        for(OcorrenciaTransitoColisao oet : ocorrenciaTransitoColisoes)
            coliseosTransito.add(oet.getColisaoTransito());

        return coliseosTransito;
    }
}
