package Control;

import java.util.ArrayList;
import java.util.List;

import Model.Foto;
import Model.Transito.OcorrenciaTransitoFoto;
import Model.Vida.OcorrenciaVidaFoto;

/**
 * Created by Pefoce on 14/08/2018.
 */

public class FotoBusiness
{
    public static List<Foto> findFotosByOcorrenciaTransitoId(Long id)
    {
        ArrayList<Foto> fotos = new ArrayList<>();
        List<OcorrenciaTransitoFoto> ocorrenciaFoto = OcorrenciaTransitoFoto.find(OcorrenciaTransitoFoto.class,"ocorrencia_transito = ?",id.toString());

        for(OcorrenciaTransitoFoto oet : ocorrenciaFoto)
            fotos.add(oet.getFoto());

        return fotos;
    }
    public static List<Foto> findFotosByOcorrenciaVidaId(Long id)
    {
        ArrayList<Foto> fotos = new ArrayList<>();
        List<OcorrenciaVidaFoto> ocorrenciaFoto = OcorrenciaVidaFoto.find(OcorrenciaVidaFoto.class,"ocorrencia_vida = ?",id.toString());

        for(OcorrenciaVidaFoto oev : ocorrenciaFoto)
            fotos.add(oev.getFoto());

        return fotos;
    }
}
