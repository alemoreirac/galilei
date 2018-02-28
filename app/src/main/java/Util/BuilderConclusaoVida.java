package Util;

import java.util.ArrayList;
import java.util.List;

import Enums.DocumentoPessoa;
import Enums.Genero;
import Enums.TipoLocal;
import Enums.TipoLocalCrime;
import Enums.TipoVegetacao;
import Enums.Vida.TipoAberturaLocal;
import Model.Endereco;
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
    static OcorrenciaVida ocorrenciaVida;
    static EnderecoVida enderecoVida;
    static Ocorrencia ocorrencia;
    static List<EnvolvidoVida> envolvidoVidaList;
    static List<VestigioVida> vestigioVidaList;
    static List<VestigioVidaOcorrencia> vestigioVidaOcorrenciaList;
    static List<Lesao> lesaoList;
    static List<LesaoEnvolvido> lesaoEnvolvido;
    static StringBuilder builderConclusao;

    public static String ConstruirConclusao(OcorrenciaVida ov)
    {
        ocorrenciaVida = ov;

        try
        {
            enderecoVida = EnderecoVida.find(EnderecoVida.class, "ocorrencia_id = ?", ocorrenciaVida.getId().toString()).get(0);
        } catch (Exception e)
        {

        }
        builderConclusao = new StringBuilder();

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaVida.getOcorrenciaID());


        InstanciarListas();
        try
        {
            ConstruirTexto();
        } catch (Exception e)
        {
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
    }

    public static void InstanciarListas()
    {
        lesaoList = new ArrayList<>();
        envolvidoVidaList = new ArrayList<>();
        vestigioVidaList = new ArrayList<>();


        List<OcorrenciaEnvolvidoVida> oev = OcorrenciaEnvolvidoVida.find(OcorrenciaEnvolvidoVida.class,
                "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        for (OcorrenciaEnvolvidoVida ocorrenciaEnvolvidoVida : oev)
            envolvidoVidaList.add(ocorrenciaEnvolvidoVida.getEnvolvidoVida());


        List<VestigioVidaOcorrencia> vvo = VestigioVidaOcorrencia.find(VestigioVidaOcorrencia.class,
                "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        for (VestigioVidaOcorrencia vestigioVidaOcorrencia : vvo)
            vestigioVidaList.add(vestigioVidaOcorrencia.getVestigioVida());

    }

    private static void ConstruirPreambulo()
    {
        builderConclusao.append("PREÂMBULO\n");
        builderConclusao.append("\nEm ");
        //builderConclusao.append(ocorrencia.getDataChamado_MesExtenso());
        if (ocorrencia.getDataChamado() != null)
            builderConclusao.append(TempoUtil.getDataExtenso(ocorrenciaVida.getDataChamado()));
        builderConclusao.append(", de acordo com a legislação e os dispositivos regulamentares vigentes, e na Coordenadoria de Perícia Criminal da Perícia Forense do Ceará, da Secretaria da Segurança Pública e Defesa Social do Estado do Ceará, o coordenador em exercício Franklin Delano Magalhães Leite designou o Perito Criminal ");
        if (ocorrencia.getPerito() != null)
        {
            if (ocorrencia.getPerito().getNome() != null)
                builderConclusao.append(ocorrencia.getPerito().getNome());
        }

        if (ocorrenciaVida.getOrgaoOrigem() != null && ocorrenciaVida.getNumIncidencia() != null)
            builderConclusao.append(" para proceder ao exame acima referido, a fim de ser atendida a " +
                    "solicitação da " + ocorrenciaVida.getOrgaoOrigem() + "com número de incidência " + ocorrencia.getOcorrenciaVida().getNumIncidencia() + ".\n");

        builderConclusao.append("Findos os trabalhos o infrafirmado passa a apresentar os " +
                "resultados dos procedimentos executados sistematicamente, à luz de princípios " +
                "técnico-legais do sistema Criminalístico.\n");

    }

    private static void ConstruirHistorico()
    {
        builderConclusao.append("\nHISTÓRICO \n");
        builderConclusao.append("\nAtendendo a solicitação supracitada por volta das ");
        builderConclusao.append(ocorrenciaVida.getHoraAtendimentoString());
        builderConclusao.append(" do dia ");
        builderConclusao.append(ocorrenciaVida.getDataAtendimentoString());
        builderConclusao.append(", esta equipe pericial composta pelo técnico acima citado, compareceu na ");

        if (enderecoVida.getEndereco() != null)
        {
            builderConclusao.append(enderecoVida.getTipoVia().getValor() + " ");
            builderConclusao.append(enderecoVida.getEndereco().getDescricao() + " ");
            builderConclusao.append("no bairro: ");
            builderConclusao.append(enderecoVida.getEndereco().getBairro().toString() + " ");
            builderConclusao.append("na cidade de ");
            builderConclusao.append(enderecoVida.getEndereco().getCidade().toString() + " ");
            builderConclusao.append("com cooordenadas ");
            builderConclusao.append("LAT: ");
            builderConclusao.append(enderecoVida.getLatitude() + ", ");
            builderConclusao.append("LON: ");
            builderConclusao.append(enderecoVida.getLongitude());
        }

        if (ocorrenciaVida.getAutoridadePresente() != null)
        {
            builderConclusao.append(".\n\nQuando da chegada da perícia, o local estava guarnecido por agentes da ");
            builderConclusao.append(ocorrenciaVida.getAutoridadePresente().getValor());
            builderConclusao.append(", sendo comandados pelo(a) " + ocorrenciaVida.getComandante());
            builderConclusao.append(". com prefixo de viatura " + ocorrenciaVida.getViatura());
        } else
            builderConclusao.append(".\nQuando da chegada da perícia, o local não estava guarnecido por agentes da lei.");

        if(ocorrenciaVida.getDelegado()!=null)
        {
            if(ocorrenciaVida.getDelegado().length()>0)
                builderConclusao.append(" Também era presente o(a) Delegado(a): " + ocorrenciaVida.getDelegado());
        }
    }

    private static void ConstruirCadaveres()
    {
        if (envolvidoVidaList.size() == 1)
            builderConclusao.append("\n\nDo cadáver");
        else
            builderConclusao.append("\n\nDos cadáveres");

        for (EnvolvidoVida envolvidoVida : envolvidoVidaList)
        {
            builderConclusao.append("\nDa posição");
            builderConclusao.append("\nJazia na posição " + envolvidoVida.getPosicaoCorpo().getValor().toLowerCase());
            builderConclusao.append(", com a cabeça na posição " + envolvidoVida.getPosicaoCorpo().getValor().toLowerCase());
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
                if (envolvidoVida.getNome().equals("Desconhecido(a)"))
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
                    if (envolvidoVida.getDataNascimentoString().contains("0002"))
                        builderConclusao.append(", nascido em: " + envolvidoVida.getDataNascimentoString());
                    else
                        builderConclusao.append(", cuja data de nascimento não foi informada");
                }
            }

            if (envolvidoVida.getUnidadeTempo() != null && envolvidoVida.getPeriodoMorte() != 0)
                builderConclusao.append(", o tempo aproximado de morte é de " + envolvidoVida.getPeriodoMorte() + " " + envolvidoVida.getUnidadeTempo().getValor());

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

            lesaoEnvolvido = LesaoEnvolvido.find(LesaoEnvolvido.class, "envolvido_vida = ?", envolvidoVida.getId().toString());

            for (LesaoEnvolvido le : lesaoEnvolvido)
                lesaoList.add(le.getLesao());

            if (lesaoList.size() > 0)
            {
                builderConclusao.append("\n\nDas Lesões");
                for (Lesao l : lesaoList)
                    builderConclusao.append("\n" + l.toString());
            }
        }
    }

    private static void ConstruirVestigios()
    {
        builderConclusao.append(".\n\nDos vestígios ");
        builderConclusao.append(".\nForam encontados os seguintes vestígios: ");

        vestigioVidaOcorrenciaList = VestigioVidaOcorrencia.find(VestigioVidaOcorrencia.class, "ocorrencia_vida = ?", ocorrenciaVida.getId().toString());

        for (VestigioVidaOcorrencia vvo : vestigioVidaOcorrenciaList)
            vestigioVidaList.add(vvo.getVestigioVida());

        for (VestigioVida v : vestigioVidaList)
            builderConclusao.append("\n" + v.toString());

    }

    private static void ConstruirLocal()
    {
        builderConclusao.append(".\n\nTrata-se de um local ");

        if (enderecoVida.getLocalAberto() != null)
            builderConclusao.append(enderecoVida.getLocalAberto().getValor());

        if (enderecoVida.getTipoLocalCrime() != null)
        {
            switch (enderecoVida.getTipoLocalCrime())
            {
                case VIA_PUBLICA:
                    builderConclusao.append(", mais precisamente, uma via pública");
                    builderConclusao.append(", de pavimentação ");

                    if (enderecoVida.getPavimentacao() != null)
                        builderConclusao.append(enderecoVida.getPavimentacao().getValor());
                    break;
                case OUTRO:
                    if (enderecoVida.getTipoLocalCrime().equals(TipoLocalCrime.OUTRO))
                        builderConclusao.append(", mais precisamente, um(a) " + enderecoVida.getObservacao());
                    break;
                case PRAIA:
                    builderConclusao.append(", mais precisamente, na faixa litorânea em um local de ");
                    if (enderecoVida.getLocalPraia() != null)
                        builderConclusao.append(enderecoVida.getLocalPraia().getValor());
                    if (enderecoVida.getTipoVegetacao() != null)
                    {
                        switch (enderecoVida.getTipoVegetacao())
                        {
                            case SEM_VEGETACAO:
                                builderConclusao.append(" sem vegetação.");
                                break;
                            case CLAREIRA:
                                builderConclusao.append(" situado em uma clareira");
                                break;
                            default:
                                builderConclusao.append(" com " + enderecoVida.getTipoVegetacao().getValor());
                                break;
                        }
                    }
                    break;

                case RURAL:
                    builderConclusao.append(", mais precisamente, em uma zona rural ");

                    if (enderecoVida.getTipoVegetacao() != null)
                    {
                        switch (enderecoVida.getTipoVegetacao())
                        {
                            case SEM_VEGETACAO:
                                builderConclusao.append(" sem vegetação.");
                                break;
                            case CLAREIRA:
                                builderConclusao.append(" situado em uma clareira");
                                break;
                            default:
                                builderConclusao.append(" com " + enderecoVida.getTipoVegetacao().getValor().toLowerCase());
                                break;
                        }
                    }

                    break;

                case RESIDENCIAL:
                    builderConclusao.append(", mais precisamente, residência ");

                    if (enderecoVida.getLocalResidencia() != null)
                        builderConclusao.append(", no lado " + enderecoVida.getLocalResidencia().getValor().toLowerCase());

                    if (enderecoVida.getComodo() != null)
                        builderConclusao.append(", na parte do(a): " + enderecoVida.getLocalResidencia().getValor().toLowerCase());
                    break;

            }
        }


        builderConclusao.append(", situado(a) na ");

        if (enderecoVida.getEndereco() != null)
        {
            builderConclusao.append(enderecoVida.getTipoVia().getValor() + " ");
            builderConclusao.append(enderecoVida.getEndereco().getDescricao() + " ");
            builderConclusao.append("no bairro de ");
            builderConclusao.append(enderecoVida.getEndereco().getBairro().toString() + " ");
            builderConclusao.append("na cidade de ");
            builderConclusao.append(enderecoVida.getEndereco().getCidade().toString() + " ");
        }

        builderConclusao.append("\nDurante os exames, as condições climáticas eram de: ");
        builderConclusao.append(enderecoVida.getCondicoesClimaticas().getValor().toLowerCase());

        builderConclusao.append(", com ");
        builderConclusao.append(enderecoVida.getTipoIluminacao().getValor().toLowerCase());

        if (enderecoVida.getObservacao() != null)
            builderConclusao.append(", observa-se que " + enderecoVida.getObservacao());
    }
}


