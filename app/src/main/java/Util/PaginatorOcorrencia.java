package Util;

import java.util.ArrayList;

import Model.Ocorrencia;


public class PaginatorOcorrencia
{
    public static int maxPages;

    private static ArrayList<Ocorrencia> ocorrencias;

    private static ArrayList<Ocorrencia> ocorrenciaResultado = null;

    public static void refreshOcorrencias(Long peritoId)
    {
        ocorrencias = (ArrayList<Ocorrencia>) Ocorrencia.find(Ocorrencia.class, "perito = ?", peritoId.toString());
        ocorrenciaResultado = new ArrayList<Ocorrencia>();

    }

    public static ArrayList<Ocorrencia> findByFilterPaginated(String filtro, int pageRequested)
    {

        if (filtro.isEmpty())
        {
            //indica o máximo de páginas, dividindo o size do array por 10 e somando 1 para acertar o número de páginas

            maxPages = (ocorrencias.size() / 10);

            if (ocorrencias.size() % 10 > 0 || maxPages == 0)
                maxPages++;

            //pageAux é o deslocamento de 10 itens na página
            int pageAux = pageRequested * 10;


//            if(pageRequested<(pageAux/10)+1)
            if (pageRequested < maxPages)
                return new ArrayList<Ocorrencia>(ocorrencias.subList(pageAux - 10, pageAux));

            else
                return new ArrayList<Ocorrencia>(ocorrencias.subList(pageAux - 10, ocorrencias.size()));
        }

        if (ocorrenciaResultado.size() > 0)
            ocorrenciaResultado.clear();

        for (Ocorrencia o : ocorrencias)
        {
            if (o.getTipoOcorrencia() != null)
            {
                switch (o.getTipoOcorrencia())
                {
                    case TRANSITO:
                        if (o.getOcorrenciaTransito() != null)
                        {
                            if (o.getOcorrenciaTransito().getNumIncidencia() != null && o.getOcorrenciaTransito().getNumIncidencia().contains(filtro)
                                    || o.getOcorrenciaTransito().getDataAtendimentoString() != null && o.getOcorrenciaTransito().getDataAtendimentoString().contains(filtro)
                                    || o.getOcorrenciaTransito().getDataChamadoString() != null && o.getOcorrenciaTransito().getDataChamadoString().contains(filtro)
                                    || o.getOcorrenciaTransito().getOrgaoDestino() != null && o.getOcorrenciaTransito().getOrgaoDestino().getDescricao() != null && o.getOcorrenciaTransito().getOrgaoDestino().getDescricao().contains(filtro)
                                    || o.getOcorrenciaTransito().getOrgaoOrigem() != null && o.getOcorrenciaTransito().getOrgaoOrigem().getDescricao() != null && o.getOcorrenciaTransito().getOrgaoOrigem().getDescricao().contains(filtro))
                            {
                                if (!ocorrenciaResultado.contains(o))
                                    ocorrenciaResultado.add(o);
                            }
                        }
                        break;

                    case VIDA:
                        if (o.getOcorrenciaVida() != null)
                        {
                            if (o.getOcorrenciaVida().getNumIncidencia() != null && o.getOcorrenciaVida().getNumIncidencia().contains(filtro)
                                    || o.getOcorrenciaVida().getDataAtendimentoString() != null && o.getOcorrenciaVida().getDataAtendimentoString().contains(filtro)
                                    || o.getOcorrenciaVida().getDataChamadoString() != null && o.getOcorrenciaVida().getDataChamadoString().contains(filtro)
                                    || o.getOcorrenciaVida().getOrgaoOrigem()!=null && o.getOcorrenciaVida().getOrgaoOrigem().getDescricao().contains(filtro)
                                    || o.getOcorrenciaVida().getOrgaoDestino()!=null && o.getOcorrenciaVida().getOrgaoDestino().getDescricao().contains(filtro))
//                                    || DelegaciaBusiness.findDescricaoById(o.getOcorrenciaVida().getOrgaoDestinoId()).contains(filtro)
//                                    || DelegaciaBusiness.findDescricaoById(o.getOcorrenciaVida().getOrgaoOrigemId()).contains(filtro))
                            {
                                if (!ocorrenciaResultado.contains(o))
                                    ocorrenciaResultado.add(o);
                            }
                        }
                        break;
                }
            }
        }

        maxPages = (ocorrenciaResultado.size() / 10);

        if (ocorrenciaResultado.size() % 10 > 0)
            maxPages++;

        //pageAux é o deslocamento de 10 itens na página
        int pageAux = pageRequested * 10;

//            if(pageRequested<(pageAux/10)+1)
        if (pageRequested < maxPages)
            return new ArrayList<Ocorrencia>(ocorrenciaResultado.subList(pageAux - 10, pageAux));

        else
            return new ArrayList<Ocorrencia>(ocorrenciaResultado.subList(pageAux - 10, ocorrenciaResultado.size()));
    }
}
