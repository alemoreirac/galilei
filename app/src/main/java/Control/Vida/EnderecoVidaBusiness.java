package Control.Vida;

import java.util.ArrayList;
import java.util.List;

import Model.Vida.EnderecoVida;
import Model.Vida.OcorrenciaVidaEndereco;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class EnderecoVidaBusiness
{
    public static List<EnderecoVida> findEnderecosByOcorrenciaId(Long id)
    {
        ArrayList<EnderecoVida> enderecoVida = new ArrayList<>();
        List<OcorrenciaVidaEndereco> ocorrenciaVidaEndereco = OcorrenciaVidaEndereco.find(OcorrenciaVidaEndereco.class,"ocorrencia_vida = ?",id.toString());

        for(OcorrenciaVidaEndereco oev : ocorrenciaVidaEndereco)

            enderecoVida.add(oev.getEnderecoVida());

        return enderecoVida;
    }
}
