package Control.Transito;

import java.util.ArrayList;
import java.util.List;

import Model.Transito.EnderecoTransito;
import Model.Transito.OcorrenciaTransitoEndereco;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class EnderecoTransitoBusiness
{
    public static List<EnderecoTransito> findEnderecoByOcorrenciaId(Long id)
    {
        ArrayList<EnderecoTransito> enderecoTransito = new ArrayList<>();
        List<OcorrenciaTransitoEndereco> ocorrenciaTransitoEndereco = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class,"ocorrencia_transito = ?",id.toString());

        for(OcorrenciaTransitoEndereco oet : ocorrenciaTransitoEndereco)
            enderecoTransito.add(oet.getEnderecoTransito());

        return enderecoTransito;
    }

}
