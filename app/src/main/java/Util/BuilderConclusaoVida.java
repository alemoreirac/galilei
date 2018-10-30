package Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Control.Vida.EnderecoVidaBusiness;
import Control.Vida.EnvolvidoVidaBusiness;
import Control.Vida.LesaoBusiness;
import Control.Vida.VestigioVidaBusiness;
import Enums.DocumentoPessoa;
import Enums.Genero;
import Enums.TipoLocalCrime;
import Enums.Vida.ParteCorpo;
import Model.Ocorrencia;
import Model.Vida.EnderecoVida;
import Model.Vida.EnvolvidoVida;
import Model.Vida.Lesao;
import Model.Vida.LesaoEnvolvido;
import Model.Vida.OcorrenciaEnvolvidoVida;
import Model.Vida.OcorrenciaVida;
import Model.Vida.VestigioVida;
import Model.Vida.VestigioVidaOcorrencia;


/**
 * Created by Pefoce on 24/01/2018.
 */

public class BuilderConclusaoVida
{

    static EnderecoVida primeiroEndereco;
    static OcorrenciaVida ocorrenciaVida;
    static List<EnderecoVida> enderecosVidaList;
    static Ocorrencia ocorrencia;
    static List<EnvolvidoVida> envolvidoVidaList;
    static List<VestigioVida> vestigioVidaList;
    static List<Lesao> lesaoList;
//    static List<LesaoEnvolvido> lesaoEnvolvido;
    static StringBuilder builderConclusao;

    public static String ConstruirLaudo(OcorrenciaVida ov)
    {
        ocorrenciaVida = ov;

        try
        {
            enderecosVidaList = EnderecoVidaBusiness.findEnderecosByOcorrenciaId(ocorrenciaVida.getId());
        } catch (Exception e)
        {

        }
        builderConclusao = new StringBuilder();

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());

        envolvidoVidaList = EnvolvidoVidaBusiness.findEnvolvidosByOcorrenciaId(ocorrenciaVida.getId());
        vestigioVidaList = VestigioVidaBusiness.findVestigioByOcorrenciaId(ocorrenciaVida.getId());

        try
        {
            ConstruirTexto();
        } catch (Exception e)
        {
            builderConclusao.append("\n -------------Ocorreu uma falha ao gerar o laudo, apresente este tablet à CTI-------------");
        }
        return builderConclusao.toString();
    }


    private static void ConstruirTexto()
    {

        ConstruirPreambulo();

        ConstruirHistorico();

        ConstruirLocal();

        ConstruirCadaveres();

        ConstruirVestigios();

        ConstruirConclusao();

    }


    private static void ConstruirPreambulo()
    {
        builderConclusao.append("PREÂMBULO\n");
        builderConclusao.append("\nEm ");
        //builderConclusao.append(ocorrencia.getDataChamado_MesExtenso());
        if (ocorrencia.getDataChamado() != null)
            builderConclusao.append(TempoUtil.getDataExtenso(ocorrenciaVida.getDataChamado()));
        builderConclusao.append(", de acordo com a legislação e os dispositivos regulamentares vigentes, e na Coordenadoria de Perícia Criminal da Perícia Forense do Ceará, da Secretaria da Segurança Pública e Defesa Social do Estado do Ceará, o coordenador em exercício Rômulo de Oliveira Lima designou o Perito Criminal ");
        if (ocorrencia.getPerito() != null)
        {
            if (ocorrencia.getPerito().getNome() != null)
                builderConclusao.append(ocorrencia.getPerito().getNome());
        }

        if (ocorrenciaVida.getOrgaoOrigem() != null && ocorrenciaVida.getNumIncidencia() != null)
            builderConclusao.append(" para proceder ao exame acima referido, a fim de ser atendida a " +
                    "solicitação da " + ocorrenciaVida.getOrgaoOrigem().getDescricao() + " com número de incidência " + ocorrencia.getOcorrenciaVida().getNumIncidencia() + ".\n");

        builderConclusao.append("Findos os trabalhos o infrafirmado passa a apresentar os " +
                "resultados dos procedimentos executados sistematicamente, à luz de princípios " +
                "técnico-legais do sistema Criminalístico.\n");

    }


    private static void ConstruirConclusao()
    {
        builderConclusao.append("\nCONCLUSÃO \n");
        builderConclusao.append("\nAnte o visto e exposto, este(a) perito(a) sugere haver ocorrido no local em pauta, objeto do presente laudo: \n");
        for (EnvolvidoVida ev : envolvidoVidaList)
        {
            if (ev.getNome() != null)
            {
                if (ev.getNome().equals("Desconhecido(a)"))
                    builderConclusao.append("\nA vítima desconhecida");
                else
                    builderConclusao.append("\n" + StringUtil.checkValue(ev.getNome(),-1,"(sem nome)"));
            } else
                builderConclusao.append("\nA vítima desconhecida");

            if (ev.getTipoMorte() != null)
            {
                switch (ev.getTipoMorte())
                {
                    case HOMICIDIO:
                        builderConclusao.append(" fora vítima de morte violenta (homicídio).");
                        break;
                    case SUICIDIO:
                        builderConclusao.append(" haveria cometido suicídio.");
                        break;
                    case MORTE_NATURAL:
                        builderConclusao.append(" fora vítima de morte natural.");
                        break;
                    case MORTE_SUSPEITA:
                        builderConclusao.append(" fora vítima de uma morte com suspeita de violência.");
                        break;
                    case ACHADO_DE_CADAVER:
                        builderConclusao.append(" fora vítima de uma morte não-violenta");
                        break;
                    case AFOGAMENTO:
                        builderConclusao.append(" fora vítima de um afogamento.");
                        break;
                    case ACIDENTE:
                        builderConclusao.append(" fora vítima de um acidente.");
                        break;
                }
            }
        }
    }


    private static void ConstruirHistorico()
    {
        builderConclusao.append("\nHISTÓRICO \n");
        builderConclusao.append("\nAtendendo a solicitação supracitada por volta das ");
        builderConclusao.append(ocorrenciaVida.getHoraAtendimentoString());
        builderConclusao.append(" do dia ");
        builderConclusao.append(ocorrenciaVida.getDataAtendimentoString());
        builderConclusao.append(", esta equipe pericial composta pelo técnico acima citado, compareceu na ");

        if(enderecosVidaList.size()>0)
        primeiroEndereco = enderecosVidaList.get(0);

        if(primeiroEndereco.getTipoVia()!=null)
        builderConclusao.append(primeiroEndereco.getTipoVia().getValor() + " ");

        builderConclusao.append(primeiroEndereco.getDescricaoEndereco() + " ");
        builderConclusao.append("no bairro: ");
        if(primeiroEndereco.getBairro()!=null)
        builderConclusao.append(primeiroEndereco.getBairro().getDescricao() + " ");
        else
            builderConclusao.append("(sem bairro) ");
        builderConclusao.append("na cidade de ");
        if(primeiroEndereco.getMunicipio()!=null)
        builderConclusao.append(primeiroEndereco.getMunicipio().getDescricao() + " ");
        else
            builderConclusao.append("(sem município) ");
        builderConclusao.append("com cooordenadas ");
        builderConclusao.append("LAT: ");
        if(primeiroEndereco.getLatitude()!=null)
        builderConclusao.append(primeiroEndereco.getLatitude() + ", ");
        else
            builderConclusao.append("- ");

        builderConclusao.append("LONG: ");
        if(primeiroEndereco.getLongitude()!=null)
            builderConclusao.append(primeiroEndereco.getLongitude()+".");
        else
            builderConclusao.append("- ");


        if (ocorrenciaVida.getAutoridadePresente() != null)
        {
            builderConclusao.append("\n\nQuando da chegada da perícia, o local estava guarnecido por agentes da ");
            builderConclusao.append(StringUtil.checkValue(ocorrenciaVida.getAutoridadePresente().getValor(),-1,"(Sem autoridade)"));
            if(ocorrenciaVida.getComandante()!=null)
            builderConclusao.append(", sendo comandados pelo(a) " + StringUtil.checkValue(ocorrenciaVida.getComandante(),-1,"(Sem comandante)"));
            if(ocorrenciaVida.getViatura()!=null)
            builderConclusao.append(". com prefixo de viatura " + ocorrenciaVida.getViatura());
        } else
            builderConclusao.append(".\nQuando da chegada da perícia, o local não estava guarnecido por agentes da lei.");

        if (ocorrenciaVida.getDelegado() != null)
        {
            if (ocorrenciaVida.getDelegado().length() > 0)
                builderConclusao.append(" Também era presente o(a) Delegado(a): " + ocorrenciaVida.getDelegado());
        }
    }

    private static void ConstruirCadaveres()
    {
        if (envolvidoVidaList.size() == 1)
            builderConclusao.append("\n\nDo cadáver");
        else
            builderConclusao.append("\n\nDos cadáveres");

        HashMap<ParteCorpo, ArrayList<Lesao>> hmLesoes;

        for (EnvolvidoVida envolvidoVida : envolvidoVidaList)
        {
            builderConclusao.append("\nDa posição");
            builderConclusao.append("\nJazia na posição " + envolvidoVida.getPosicaoCorpo().getValor().toLowerCase());
            builderConclusao.append(", com a cabeça na posição " + envolvidoVida.getPosicaoCabeca().getValor().toLowerCase());
            builderConclusao.append(", com o braço esquerdo " + envolvidoVida.getPosicaoBracoEsquerdo().getValor().toLowerCase());
            builderConclusao.append(", com o braço direito " + envolvidoVida.getPosicaoBracoDireito().getValor().toLowerCase());
            builderConclusao.append(", com a perna esquerda " + envolvidoVida.getPosicaoPernaEsquerda().getValor().toLowerCase());
            builderConclusao.append(", com a perna direita " + envolvidoVida.getPosicaoPernaDireita().getValor().toLowerCase());

            builderConclusao.append("\nDa descrição");

            if (envolvidoVida.getGenero() != null)
            {
                if (!envolvidoVida.getGenero().equals(Genero.NAO_IDENTIFICADO))
                    builderConclusao.append("\nA vítima era do sexo " + envolvidoVida.getGenero().getValor());
                else
                    builderConclusao.append("\nNão foi possível identificar o gênero da vítima");
            }

            if (envolvidoVida.getNome() != null)
            {
                if (envolvidoVida.getNome().equals("Desconhecido(a)") || envolvidoVida.getNome().trim().isEmpty())
                    builderConclusao.append(", e não foi identificada até o momento.");
                else
                    builderConclusao.append(", se chamava " + envolvidoVida.getNome());

                if (envolvidoVida.getDocumentoTipo() != null)
                {
                    if (!envolvidoVida.getDocumentoTipo().equals(DocumentoPessoa.NP))
                    {
                        builderConclusao.append(", que portava documento do tipo: " + envolvidoVida.getDocumentoTipo().getValor());
                        builderConclusao.append(", de numeração " + envolvidoVida.getDocumentoValor());
                    } else
                        builderConclusao.append(", que não portava documentação.");
                }

                if (envolvidoVida.getNascimento() != null)
                {
                    if (!envolvidoVida.getDataNascimentoString().contains("0002"))
                        builderConclusao.append(", nascida em " + envolvidoVida.getDataNascimentoString());
                    else
                        builderConclusao.append(", cuja data de nascimento não foi informada");
                }
            }

            if (envolvidoVida.getIndiciosTempoMorte() != null)
                builderConclusao.append(", os indícios encontrados no cadáver serão listados a seguir, indicando uma estimativa aproximada de tempo de morte: " + envolvidoVida.getIndiciosTempoMorte().getValor()+".");

            if (envolvidoVida.getVestes() != null)
            {
                if (envolvidoVida.getVestes().length() > 0)
                {
                    builderConclusao.append("\n\nDas vestes");
                    builderConclusao.append("\nA vítima vestia: " + envolvidoVida.getVestes());
                }
            }

            if (envolvidoVida.getObservacoes() != null)
            {
                if (envolvidoVida.getObservacoes().length() > 0)
                    builderConclusao.append("\nObservações gerais: " + envolvidoVida.getObservacoes());
            }

            lesaoList = LesaoBusiness.findLesaoByEnvolvido(envolvidoVida.getId());

            hmLesoes = new HashMap<ParteCorpo, ArrayList<Lesao>>();


            if (lesaoList.size() > 0)
            {
                for(Lesao l : lesaoList)
                {
                    if(hmLesoes.containsKey(l.getParteCorpo()))

                        hmLesoes.get(l.getParteCorpo()).add(l);
                    else
                    {
                        hmLesoes.put(l.getParteCorpo(), new ArrayList<>());
                        hmLesoes.get(l.getParteCorpo()).add(l);
                    }
                }
                Set set = hmLesoes.entrySet();
                Iterator iterator = set.iterator();

                while(iterator.hasNext())
                {
                    Map.Entry entry = (Map.Entry)iterator.next();

                    builderConclusao.append("\n\n " +((ParteCorpo)entry.getKey()).getValor()+": ");

                    for(Lesao l : (ArrayList<Lesao>)entry.getValue())
                    {
                        builderConclusao.append(l.exportarLaudo());
                    }
                }
            }
        }
    }

    private static void ConstruirVestigios()
    {
        if (vestigioVidaList.size() == 0)
            return;

        builderConclusao.append(".\n\nDos vestígios\n");
        builderConclusao.append("\nForam encontados os seguintes vestígios: ");

//        for (VestigioVidaOcorrencia vvo : vestigioVidaOcorrenciaList)
//            vestigioVidaList.add(vvo.getVestigioVida());

        for (VestigioVida v : vestigioVidaList)
            builderConclusao.append("\n" + v.toString());

    }

    private static void ConstruirLocal()
    {
        for(EnderecoVida ev : enderecosVidaList)
        {
            builderConclusao.append("\n\nTrata-se de um local ");

            if (ev.getLocalAberto() != null)
                builderConclusao.append(ev.getLocalAberto().getValor());

            if (ev.getTipoLocalCrime() != null)
            {
                switch (ev.getTipoLocalCrime())
                {
                    case VIA_PUBLICA:
                        builderConclusao.append(", mais precisamente, uma via pública");
                        builderConclusao.append(", de pavimentação ");

                        if (ev.getPavimentacao() != null)
                            builderConclusao.append(ev.getPavimentacao().getValor());
                        break;
                    case OUTRO:
                        if (ev.getTipoLocalCrime().equals(TipoLocalCrime.OUTRO))
                            builderConclusao.append(", mais precisamente, um(a) " + ev.getObservacao());
                        break;
                    case PRAIA:
                        builderConclusao.append(", mais precisamente, na faixa litorânea em um local de ");
                        if (ev.getLocalPraia() != null)
                            builderConclusao.append(ev.getLocalPraia().getValor());
                        if (ev.getTipoVegetacao() != null)
                        {
                            switch (ev.getTipoVegetacao())
                            {
                                case SEM_VEGETACAO:
                                    builderConclusao.append(" sem vegetação.");
                                    break;
                                case CLAREIRA:
                                    builderConclusao.append(" situado em uma clareira");
                                    break;
                                default:
                                    builderConclusao.append(" com " + ev.getTipoVegetacao().getValor());
                                    break;
                            }
                        }
                        break;

                    case RURAL:
                        builderConclusao.append(", mais precisamente, em uma zona rural ");

                        if (ev.getTipoVegetacao() != null)
                        {
                            switch (ev.getTipoVegetacao())
                            {
                                case SEM_VEGETACAO:
                                    builderConclusao.append(" sem vegetação.");
                                    break;
                                case CLAREIRA:
                                    builderConclusao.append(" situado em uma clareira");
                                    break;
                                default:
                                    builderConclusao.append(" com " + ev.getTipoVegetacao().getValor().toLowerCase());
                                    break;
                            }
                        }

                        break;

                    case RESIDENCIAL:
                        builderConclusao.append(", mais precisamente, residência ");

                        if (ev.getLocalResidencia() != null)
                            builderConclusao.append(", no lado " + ev.getLocalResidencia().getValor().toLowerCase());

                        if (ev.getComodo() != null)
                            builderConclusao.append(", na parte do(a): " + ev.getLocalResidencia().getValor().toLowerCase());
                        break;
                }
            }

            builderConclusao.append(", situado(a) na ");

            builderConclusao.append(ev.getTipoVia().getValor() + " ");
            builderConclusao.append(ev.getDescricaoEndereco() + " ");
            builderConclusao.append("no bairro: ");
            if(primeiroEndereco.getBairro()!=null)
                builderConclusao.append(primeiroEndereco.getBairro().getDescricao() + " ");
            else
                builderConclusao.append("(sem bairro) ");
            builderConclusao.append("na cidade de ");
            if(primeiroEndereco.getMunicipio()!=null)
                builderConclusao.append(primeiroEndereco.getMunicipio().getDescricao() + " ");
            else
                builderConclusao.append("(sem município) ");

            if (ev.getCondicoesClimaticas() != null)
            {
                builderConclusao.append("\nDurante os exames, as condições climáticas eram de: ");
                builderConclusao.append(ev.getCondicoesClimaticas().getValor().toLowerCase());

                if (ev.getTipoIluminacao() != null)
                {
                    builderConclusao.append(", com ");
                    builderConclusao.append(ev.getTipoIluminacao().getValor().toLowerCase());
                }
                if (ev.getObservacao() != null)
                    builderConclusao.append(", observa-se que " + ev.getObservacao());
            }

        }


    }
}


