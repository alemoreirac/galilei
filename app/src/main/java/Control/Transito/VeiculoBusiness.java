package Control.Transito;

import java.util.ArrayList;
import java.util.List;

import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;

/**
 * Created by Pefoce on 18/07/2018.
 */

public class VeiculoBusiness
{
    public static List<Veiculo> findVeiculosByOcorrenciaId(Long id)
    {
        ArrayList<Veiculo> veiculos = new ArrayList<>();

        List<OcorrenciaTransitoVeiculo> ocorrenciaVidaEndereco = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class,"ocorrencia_transito = ?",id.toString());

        for(OcorrenciaTransitoVeiculo oev : ocorrenciaVidaEndereco)

            veiculos.add(oev.getVeiculo());

        return veiculos;
    }
}
