package Util;

import java.util.ArrayList;
import java.util.List;

import Enums.DocumentoPessoa;
import Enums.Transito.AtoresColisao;
import Enums.Transito.ConclusaoTransito;
import Enums.IluminacaoVia;
import Enums.Transito.TipoCNH;
import Enums.Transito.Topografia;
import Model.Transito.ColisaoTransito;
import Model.Transito.DanoVeiculo;
import Model.Transito.EnderecoTransito;
import Model.Transito.EnvolvidoTransito;
import Model.Ocorrencia;
import Model.Transito.OcorrenciaTransitoColisao;
import Model.Transito.OcorrenciaTransitoEndereco;
import Model.Transito.OcorrenciaTransitoEnvolvido;
import Model.Transito.OcorrenciaTransito;
import Model.Transito.OcorrenciaTransitoVeiculo;
import Model.Transito.Veiculo;
import Model.Transito.VestigioTransito;
import Model.Transito.VestigioColisao;

public class BuilderConclusaoTransito
{

    static OcorrenciaTransito ocorrenciaTransitoConclusao;
    static Ocorrencia ocorrencia;
    static StringBuilder builderConclusao = null;
    static List<ColisaoTransito> colisoes;
    static List<Veiculo> veiculos;
    static List<EnvolvidoTransito> envolvidos;
    static List<EnderecoTransito> enderecos;
    static List<DanoVeiculo> danosVeiculo;

    public static String ConstruirConclusao(OcorrenciaTransito ot)
    {
        ocorrenciaTransitoConclusao = ot;

        builderConclusao = new StringBuilder();

        ocorrencia = Ocorrencia.findById(Ocorrencia.class, ocorrenciaTransitoConclusao.getOcorrenciaID());

        InstanciarListas();
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
        if (ocorrenciaTransitoConclusao.getUltimaForma())
        {
            ConstruirUltimaForma();
            return;
        }
        ConstruirPreambulo();

        ConstruirHistorico();

        ConstruirVitimas();

        ConstruirVeiculos();

        ConstruirDanos();

        ConstruirLocal();

        ConstruirDinamica();

        ConstruirElementos();

        ConstruirConclusao();
    }

    private static void InstanciarListas()
    {

        veiculos = new ArrayList<>();
        colisoes = new ArrayList<>();
        envolvidos = new ArrayList<>();
        enderecos = new ArrayList<>();

        List<OcorrenciaTransitoEndereco> oen = OcorrenciaTransitoEndereco.find(OcorrenciaTransitoEndereco.class,
                "ocorrencia_transito = ?", ocorrenciaTransitoConclusao.getId().toString());

        for (OcorrenciaTransitoEndereco enderecoTransito : oen)
        {
            enderecos.add(enderecoTransito.getEnderecoTransito());
        }

        List<OcorrenciaTransitoVeiculo> ov = OcorrenciaTransitoVeiculo.find(OcorrenciaTransitoVeiculo.class,
                "ocorrencia_transito = ?", ocorrenciaTransitoConclusao.getId().toString());
        for (OcorrenciaTransitoVeiculo ocorrenciaVeiculo : ov)
        {
            veiculos.add(ocorrenciaVeiculo.getVeiculo());
        }
        List<OcorrenciaTransitoColisao> oc = OcorrenciaTransitoColisao.find(OcorrenciaTransitoColisao.class,
                "ocorrencia_transito = ?", ocorrenciaTransitoConclusao.getId().toString());
        for (OcorrenciaTransitoColisao ocorrenciaColisao : oc)
        {
            colisoes.add(ocorrenciaColisao.getColisaoTransito());
        }
        List<OcorrenciaTransitoEnvolvido> oe = OcorrenciaTransitoEnvolvido.find(OcorrenciaTransitoEnvolvido.class,
                "ocorrencia_transito = ?", ocorrenciaTransitoConclusao.getId().toString());
        for (OcorrenciaTransitoEnvolvido ocorrenciaEnvolvido : oe)
        {
            envolvidos.add(ocorrenciaEnvolvido.getEnvolvidoTransito());
        }
    }

    private static void ConstruirUltimaForma()
    {
        builderConclusao.append("\nHISTÓRICO \n");
        builderConclusao.append("\nAtendendo a solicitação supracitada por volta das ");

        builderConclusao.append(ocorrenciaTransitoConclusao.getHoraAtendimentoString());
        builderConclusao.append(" do dia ");
        builderConclusao.append(ocorrenciaTransitoConclusao.getDataAtendimentoString());
        builderConclusao.append(", esta equipe pericial composta pelo técnico acima citado, compareceu na ");

        if (enderecos.size() > 0)
        {
            if(enderecos.get(0).getTipoVia()!= null)
            builderConclusao.append(enderecos.get(0).getTipoVia().getValor() + " ");
            if(enderecos.get(0).getDescricaoEndereco()!=null)
            builderConclusao.append(enderecos.get(0).getDescricaoEndereco() + " ");
            builderConclusao.append("na cidade de ");
            builderConclusao.append(enderecos.get(0).getCidade() + " ");
            builderConclusao.append("com cooordenadas ");
            builderConclusao.append("LAT: ");
            builderConclusao.append(enderecos.get(0).getLatitude() + ", ");
            builderConclusao.append("LON: ");
            builderConclusao.append(enderecos.get(0).getLongitude());
        }

        builderConclusao.append(". \nOnde segundo informações da CIOPS havia uma ocorrência de " +
                "tráfego. Porém as partes envolvidas entraram em acordo e o caso se tornou de ÚLTIMA FORMA.\n");

    }

    private static void ConstruirPreambulo()
    {
        builderConclusao.append("PREÂMBULO\n");
        builderConclusao.append("\nEm ");
        //builderConclusao.append(ocorrencia.getDataChamado_MesExtenso());
        builderConclusao.append(TempoUtil.getDataExtenso(ocorrenciaTransitoConclusao.getDataChamado()));
        builderConclusao.append(", nesta cidade de Fortaleza, de acordo com a legislação e os dispositivos regulamentares vigentes, e na Coordenadoria de Perícia Criminal da Perícia Forense do Ceará, da Secretaria da Segurança Pública e Defesa Social do Estado do Ceará, o coordenador em exercício Rômulo de Oliveira Lima designou o Perito Criminal ");
        builderConclusao.append(ocorrencia.getPerito().getNome());
//        builderConclusao.append(" para proceder ao exame acima referido, a fim de ser atendida a " +
//                "solicitação da CIOPS I" + Calendar.getInstance().get(Calendar.YEAR) + " ");
        builderConclusao.append(" para proceder ao exame acima referido, a fim de ser atendida a " +
                "solicitação da CIOPS " + ocorrencia.getOcorrenciaTransito().getNumIncidencia() + ".\n");
        builderConclusao.append("Findos os trabalhos o infrafirmado passa a apresentar os " +
                "resultados dos procedimentos executados sistematicamente, à luz de princípios " +
                "técnico-legais do sistema Criminalístico.\n");

    }

    private static void ConstruirElementos()
    {

    }

    private static void ConstruirHistorico()
    {
        builderConclusao.append("\nHISTÓRICO \n");
        builderConclusao.append("\nAtendendo a solicitação supracitada por volta das ");
        builderConclusao.append(ocorrenciaTransitoConclusao.getHoraAtendimentoString());
        builderConclusao.append(" do dia ");
        builderConclusao.append(ocorrenciaTransitoConclusao.getDataAtendimentoString());
        builderConclusao.append(", esta equipe pericial composta pelo técnico acima citado, compareceu na ");

        if (enderecos.size() > 0)
        {
            builderConclusao.append(enderecos.get(0).getTipoVia().getValor() + " ");
            builderConclusao.append(enderecos.get(0).getDescricaoEndereco() + " ");
            builderConclusao.append("na cidade de ");
            builderConclusao.append(enderecos.get(0).getCidade() + " ");
            builderConclusao.append("com cooordenadas ");
            builderConclusao.append("LAT: ");
            builderConclusao.append(enderecos.get(0).getLatitude() + ", ");
            builderConclusao.append("LON: ");
            builderConclusao.append(enderecos.get(0).getLongitude());
        }


        builderConclusao.append(". \nOnde segundo informações da CIOPS havia uma ocorrência de " +
                "tráfego. Assim o perito deu início aos trabalhos técnicos-periciais, que se " +
                "faziam mister.\n");
    }

    private static void ConstruirVitimas()
    {
        if (envolvidos.size() == 0)
        {
            builderConclusao.append("\n (Sem envolvidos cadastrados)\n");
            return;
        }

        if (envolvidos.size() > 1)
            builderConclusao.append("\nDo acidente resultaram várias vítimas, descritas a seguir: \n");
        else
            builderConclusao.append("\nDo acidente resultou uma vítima, descrita a seguir: \n");

        for (EnvolvidoTransito envolvido : envolvidos)
        {
            if(envolvido == null)
                return;

            if (envolvido.getNome() != null && !envolvido.getNome().equals("Desconhecido(a)"))
                builderConclusao.append("\n" + StringUtil.checkValue(envolvido.getNome(), -1, "(Sem nome)"));

            else
            {
                if (envolvido.getGenero() != null)
                {
                    switch (envolvido.getGenero())
                    {
                        case FEMININO:
                            builderConclusao.append("\nDesconhecida, ");
                            break;
                        case MASCULINO:
                            builderConclusao.append("\nDesconhecido, ");
                            break;
                        case NAO_IDENTIFICADO:
                            builderConclusao.append("\nDesconhecido(a) sem gênero identificado, ");
                            break;
                    }
                }
            }

            if (envolvido.getDocumentoTipo() != null && !envolvido.getTipoEnvolvido().equals(DocumentoPessoa.NP))
                builderConclusao.append("identificado(a) com " + envolvido.getDocumentoTipo().getValor() + " de nº: "
                        + StringUtil.checkValue(envolvido.getDocumentoValor(), -1, "") + " ");

            else
                builderConclusao.append("que não possui identificação");


            if (envolvido.getTipoEnvolvido() != null)
            {
                switch (envolvido.getTipoEnvolvido())
                {

                    case MOTORISTA:
                        if (envolvido.getVeiculoEnvolvido() != null)
                        {
                            builderConclusao.append(", que conduzia o veículo ");
                            builderConclusao.append(envolvido.getVeiculoEnvolvido().toString());
                        }
                        break;
                    case PASSAGEIRO:
                        if (envolvido.getVeiculoEnvolvido() != null)
                        {
                            builderConclusao.append(", que era passageiro no veículo ");
                            builderConclusao.append(envolvido.getVeiculoEnvolvido().toString());
                        }
                        break;
                    case PEDESTRE:
                        builderConclusao.append(", que era pedestre");
                        break;
                    case NAO_IDENTIFICADO:
                        builderConclusao.append(", cuja posição não foi identificada");
                        break;
                }
            }

            if (envolvido.getLesao() != null)
            {
                switch (envolvido.getLesao())
                {
                    case GRAVE:
                        builderConclusao.append(", com lesões graves.");
                        break;
                    case LEVE:
                        builderConclusao.append(", com lesões leves.");
                        break;
                    case FATAL:
                        builderConclusao.append(", que veio à óbito.");
                        break;
                }
            }

        }
    }

    private static void ConstruirVeiculos()
    {
        builderConclusao.append("\nDOS VEÌCULOS ENVOLVIDOS \n");
        for (Veiculo veiculo : veiculos)
        {
            if (veiculo == null)
            {
                return;
            }
            builderConclusao.append("\nVeículo V" + (1 + veiculos.indexOf(veiculo)) + ": ");
            //builderConclusao.append("Marca ");

            builderConclusao.append(", modelo: " + StringUtil.checkValue(veiculo.getModelo(),-1,"(Sem modelo)"));


            if(veiculo.getAnoModelo()!=0)
                builderConclusao.append(", ano do modelo: " + StringUtil.checkValue(String.valueOf(veiculo.getAnoModelo()),-1,"(Sem ano)"));


            if (veiculo.getAnoFabricacao() != 0)
                builderConclusao.append(", ano de fabricação: " + StringUtil.checkValue(String.valueOf(veiculo.getAnoFabricacao()),-1,"(Sem ano)"));


            if(veiculo.getCor()!=null)
            builderConclusao.append(", cor " + veiculo.getCor().getValor());

            if (veiculo.getPlaca().equals("-"))
                builderConclusao.append(", sem placa.");
            else
                builderConclusao.append(", de placa: " + veiculo.getPlaca());


            if (veiculo.getNomeProprietario().equals("Desconhecido(a)"))
                builderConclusao.append(", Cujo proprietário não foi identificado.");

            else
            {
                builderConclusao.append(", Registrado em Nome de: " + veiculo.getNomeProprietario());
                if (!veiculo.getCategoriaProprietario().equals(TipoCNH.NP))
                {
                    builderConclusao.append(", habilitado com CNH de nº: " + veiculo.getNumeroDocumentoProprietario());
                    builderConclusao.append(", de Categoria: " + veiculo.getCategoriaProprietario());
                } else
                    builderConclusao.append(" que não possui habilitação");

            }


            if (veiculo.getNomeCondutor().equals("Desconhecido(a)"))
                builderConclusao.append(", cujo condutor não foi identificado.");

            else
            {
                if (veiculo.getNomeCondutor().equals(veiculo.getNomeProprietario()))
                {
                    builderConclusao.append(", guiado pelo(a) mesmo(a).");
                    continue;
                }

                builderConclusao.append(", guiado por: " + veiculo.getNomeCondutor());
                if (!veiculo.getCategoriaCondutor().equals(TipoCNH.NP))
                {
                    builderConclusao.append(", habilitado com CNH de nº: " + veiculo.getNumeroDocumentoCondutor());

                    builderConclusao.append(", de Categoria: " + veiculo.getCategoriaCondutor());
                } else
                    builderConclusao.append(" que não possui habilitação.");

            }

        }
    }

    public static void ConstruirLocal()
    {
        builderConclusao.append("\nDO LOCAL\n");

        if (enderecos.size() == 0)
        {
            builderConclusao.append("\n(Sem endereços cadastrados)\n");
            return;
        }


        builderConclusao.append("\nO local do evento é composto pelo(s) logradouro(s) a seguir: \n");

        for (EnderecoTransito endereco : enderecos)
        {
            if (endereco.getTipoVia() != null)

                builderConclusao.append("A " + endereco.getTipoVia().getValor() + " " + endereco.getDescricaoEndereco());


            if (endereco.isComposta())
            {
                builderConclusao.append(" é um logradouro composto");
                builderConclusao.append(" por " + endereco.getNumFaixas() + " faixa(s)");
                builderConclusao.append(" e " + endereco.getNumPistas() + " pista(s)");
            } else
                builderConclusao.append(", é um lgoradouro simples");

            //builderConclusao.append(", de topografia "+enderecos.get(0).getTopografia().getValor());

            if ((endereco.getTopografia() == Topografia.ACLIVE
                    || endereco.getTopografia() == Topografia.DECLIVE)
                    && endereco.getAngulo() != 0)
                builderConclusao.append("com " + endereco.getTopografia().getValor() + ", com angulação de " + endereco.getAngulo() + " graus");
            else
                builderConclusao.append(", plano");

            if (endereco.getLargura() != null)
                builderConclusao.append(", com " + endereco.getLargura().toString() + "m de largura");

            if (endereco.isCurva())
                builderConclusao.append(", curvo");
            else
                builderConclusao.append(", reto");

            if (endereco.getSemaforo() != null)
                builderConclusao.append(", com semáforo " + endereco.getSemaforo().getValor());

            if (endereco.getSinalizacaoPare() != null)
                builderConclusao.append(", com sinal de parada " + endereco.getSinalizacaoPare().getValor());

            if (endereco.getCondicao() != null)
                builderConclusao.append(", com " + endereco.getCondicao().getValor() + " condição de pista");

            if (endereco.getPavimentacao() != null)
                builderConclusao.append(", com pavimentação de " + endereco.getPavimentacao().getValor());

            if (endereco.getIluminacao() != null)
            {
                if (endereco.getIluminacao().equals(IluminacaoVia.AUSENTE))
                    builderConclusao.append(", com iluminação " + endereco.getIluminacao().getValor());
                else
                    builderConclusao.append(", com iluminação de " + endereco.getIluminacao().getValor() + " condição");
            }
            if (endereco.getSentidoVia() != null)
                builderConclusao.append(", através do qual o tráfego se processa no sentido " + endereco.getSentidoVia().getValor());

            if (endereco.isMaoDupla())
                builderConclusao.append(" e em sentido contrário.");
        }
    }

    private static void ConstruirDanos()
    {

        builderConclusao.append("\n\nDOS EXAMES\n");
        builderConclusao.append("\nProsseguindo os exames de praxe para fatos desta natureza, constatou-se o seguinte:\n");

        for (Veiculo veiculo : veiculos)
        {
            if (veiculo == null)
                return;
            builderConclusao.append("O veículo ");
            builderConclusao.append(veiculo.getModelo());

            danosVeiculo = DanoVeiculo.find(DanoVeiculo.class, "veiculo = ?", veiculo.getId().toString());
            if (danosVeiculo.size() > 0)
            {
                builderConclusao.append(" encontrava-se com Danos:\n");

                for (DanoVeiculo dv : danosVeiculo)
                    builderConclusao.append(dv.getDano().exportarLaudo() + "\n");
            } else
            {
                builderConclusao.append(" não apresentou danos.\n");
            }
        }
    }

    private static void ConstruirDinamica()
    {


        builderConclusao.append("\n\nDINÂMICA DA OCORRÊNCIA\n");


        builderConclusao.append("\nCom base nos elementos do conjunto estático do local, pode o perito informar que o acidente de tráfego obedeceu à seguinte mecânica:\n");

        for (int i = 0; i < colisoes.size(); i++)
        {
            List<VestigioColisao> vestigiosColisao = VestigioColisao.find(VestigioColisao.class, "colisao_transito = ?", colisoes.get(i).getId().toString());

            ArrayList<VestigioTransito> vestigios = new ArrayList<>();

            for (VestigioColisao vc : vestigiosColisao)
                if (vc.getVestigioId() != null)
                    vestigios.add(VestigioTransito.findById(VestigioTransito.class, vc.getVestigioId()));


            if (i == 0 && EncontrarInconclusivo())
                builderConclusao.append("\nPelos motivos que serão citados a seguir, não foi possível que o perito pudesse concluir sobre todas as interações do caso supracitado.");

            else
                builderConclusao.append("\nCom base nos elementos do conjunto estático do local, pode o perito informar que o acidente de tráfego obedeceu à seguinte mecânica:\n");

            if (!colisoes.get(i).getInconclusivo())
            {
                builderConclusao.append("O veículo " + colisoes.get(i).getVeiculo1().getModelo());

                builderConclusao.append(" trafegava na " + colisoes.get(i).getEndereco_veiculo1().toStringDoc());

                builderConclusao.append(" no sentido " + colisoes.get(i).getEndereco_veiculo1().getSentidoVia().getValor());

                switch (colisoes.get(i).getAtoresColisao())
                {
                    case VEICULO:
                        builderConclusao.append("O veículo " + colisoes.get(i).getVeiculo2().getModelo());
                        builderConclusao.append(" trafegava na " + colisoes.get(i).getEndereco_veiculo2().toStringDoc());
                        builderConclusao.append(" no sentido " + colisoes.get(i).getEndereco_veiculo2().getSentidoVia().getValor());
                        break;
                    case PEDESTRE:
                        if (colisoes.get(i).getPedestre() == null)
                        {
                            return;
                        }
                        builderConclusao.append(" O veículo " + colisoes.get(i).getPedestre().getNome());
                        //builderConclusao.append(" se encontrava " + colisoes.get(i).getPosicaoPedestre().getValor());
                        switch (colisoes.get(i).getPosicaoPedestre())
                        {
                            case PROXIMO_FAIXA:
                            case PROXIMO_PASSARELA:
                                builderConclusao.append(" encontrava-se a " + colisoes.get(i).getDistancia() + " metros da " + colisoes.get(i).getPosicaoPedestre().getValor().substring(9));
                                break;
                            case SEM_FAIXA:
                                builderConclusao.append(" encontrava-se em um local sem faixa de pedestres.");
                                break;
                            case PASSARELA:
                                builderConclusao.append(" encontrava-se na passarela da pista.");
                                break;
                            case SOBRE_FAIXA:
                                builderConclusao.append(" encontrava-se sobre a faixa de pedestres.");
                                break;
                        }
                        break;

                    case OBJETO:
                        builderConclusao.append(" O(A) " + colisoes.get(i).getObjetoDescricao());
                        //builderConclusao.append(" se encontrava " + colisoes.get(i).getPosicaoPedestre().getValor());
                        if (colisoes.get(i).getObjetoPosicao() != null)
                        {
                            switch (colisoes.get(i).getObjetoPosicao())
                            {
                                case CALCADA:
                                case PISTA:
                                case PASSARELA:
                                    builderConclusao.append(" encontrava-se na " + colisoes.get(i).getObjetoPosicao().getValor());
                                    break;
                                case ACOSTAMENTO:
                                    builderConclusao.append(" encontrava-se no " + colisoes.get(i).getObjetoPosicao().getValor());
                                    break;
                            }
                        }
                        break;
                    case ANIMAL:
                        builderConclusao.append(" O(A) " + colisoes.get(i).getAnimalDescricao());
                        //builderConclusao.append(" se encontrava " + colisoes.get(i).getPosicaoPedestre().getValor());
                        if (colisoes.get(i).getAnimalPosicao() != null)
                        {
                            switch (colisoes.get(i).getAnimalPosicao())
                            {
                                case CALCADA:
                                case PISTA:
                                case PASSARELA:
                                    builderConclusao.append(" encontrava-se na " + colisoes.get(i).getAnimalPosicao().getValor());
                                    break;
                                case ACOSTAMENTO:
                                    builderConclusao.append(" encontrava-se no " + colisoes.get(i).getAnimalPosicao().getValor());
                                    break;
                            }
                        } else
                            builderConclusao.append(" não teve sua posição descrita.");
                        break;
                    case NENHUM:
                        builderConclusao.append(" Quando ocorreu o adernamento");
                        break;
                }


                builderConclusao.append("\nDe acordo com os Vestígios:\n");
                for (VestigioTransito v : vestigios)
                {
                    if (v != null)
                        builderConclusao.append("\n" + v.toString());

                }
            } else
            {
                builderConclusao.append("\nO(A) " + colisoes.get(i).getTipoInteracao().getValor());

                if (colisoes.get(i).getAtoresColisao() != AtoresColisao.NENHUM)
                    builderConclusao.append(" entre o(a) " + colisoes.get(i).getVeiculo1().toString() + " e ");
                else
                    builderConclusao.append(" que envolve o " + colisoes.get(i).getVeiculo1().toString() + " ocorreu de uma forma em que");
//                    builderConclusao.append(" que acabou adernando de uma forma em que ");

                if (colisoes.get(i).getAtoresColisao() == AtoresColisao.VEICULO)
                    builderConclusao.append("o(a) " + colisoes.get(i).getVeiculo2().toString() + " ocorreu de uma forma em que");

                if (colisoes.get(i).getAtoresColisao() == AtoresColisao.ANIMAL)
                    builderConclusao.append("o(a) " + colisoes.get(i).getAnimalDescricao() + " ocorreu de uma forma em que");

                if (colisoes.get(i).getAtoresColisao() == AtoresColisao.OBJETO)
                    builderConclusao.append("o(a) " + colisoes.get(i).getObjetoDescricao() + " ocorreu de uma forma em que");

                if (colisoes.get(i).getAtoresColisao() == AtoresColisao.PEDESTRE)
                    builderConclusao.append("o(a) pedestre " + colisoes.get(i).getPedestre().getNome() + " ocorreu de uma forma em que");

                if (colisoes.get(i).getJustificativaInconclusao() != null)
                    builderConclusao.append(" ficou impossiblitada a obtensão de conclusões devido ao fato de: " + colisoes.get(i).getJustificativaInconclusao().getValor());

            }
        }
    }

    private static boolean EncontrarInconclusivo()
    {
        for (ColisaoTransito ct : colisoes)
        {
            if (ct.getInconclusivo())
                return true;
        }
        return false;
    }

    public static void ConstruirConclusao()
    {
        builderConclusao.append("\n\nCONCLUSÃO\n");
        builderConclusao.append("\nAssim como apresentado anteriormente, este expert entende que a ocorrência de tráfego teve como fator determinante ");
        //builderConclusao.append();

        for (ColisaoTransito colisao : colisoes)
        {
            if (colisao.getConclusaoVeiculo1() != null)
            {
                switch (colisao.getAtoresColisao())
                {
                    case NENHUM:

                        builderConclusao.append("o(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());

                        if (colisao.getConclusaoVeiculo1().equals(ConclusaoTransito.CONDUTOR_ISENTO))
                        {
                            builderConclusao.append(" não teve responsabilidade em causar o adernamento.");
                            break;
                        }
                        builderConclusao.append(", devido " + colisao.getConclusaoVeiculo1().getValor());
                        builderConclusao.append(" resultando no adernamento ocorrido.");
                        break;
                    case VEICULO:
                        if (colisao.isVeiculo1Causador() && colisao.isVeiculo2Causador())
                        {
                            //culpas concorrentes
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(", devido " + colisao.getConclusaoVeiculo1().getValor());
                            builderConclusao.append(" resultando em colisão com o " + colisao.getVeiculo2().toString());
                            builderConclusao.append(" cujo condutor, por sua vez, devido " + colisao.getConclusaoVeiculo2().getValor());
                            builderConclusao.append(" também possui responsabilidade no fato ocorrido, caracterizando culpas concorrentes.");
                        }
                        if (colisao.isVeiculo1Causador() && !colisao.isVeiculo2Causador())
                        {
                            //V1 culpado apenas
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(", devido " + colisao.getConclusaoVeiculo1().getValor());
                            builderConclusao.append(" resultando em colisão com o veículo" + colisao.getVeiculo2().getModelo());
                        }
                        if (!colisao.isVeiculo1Causador() && colisao.isVeiculo2Causador())
                        {
                            //V2 culpado apenas
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo2().toString());
                            builderConclusao.append(", devido " + colisao.getConclusaoVeiculo2().getValor());
                            builderConclusao.append(" resultando em colisão com o " + colisao.getVeiculo1().toString());
                        }
                        break;
                    case PEDESTRE:
                        if (colisao.isVeiculo1Causador() && !colisao.getCulpaPedestre())
                        {
                            //V1 culpado apenas
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(", devido " + colisao.getConclusaoVeiculo1().getValor());
                            builderConclusao.append(" e acabou atingindo " + colisao.getPedestre().getNome());
                        }
                        if (colisao.isVeiculo1Causador() && colisao.getCulpaPedestre())
                        {
                            //culpas concorrentes
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(", devido " + colisao.getConclusaoVeiculo1().getValor());
                            builderConclusao.append(" e acabou atingindo " + colisao.getPedestre().getNome());
                            builderConclusao.append(" que por sua vez também possui responsabilidade no ocorrido, caracterizando culpas concorrentes.");
                        }
                        if (!colisao.isVeiculo1Causador() && colisao.getCulpaPedestre())
                        {
                            //colisao_pedestre culpado apenas
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(", devido " + colisao.getConclusaoVeiculo1().getValor());
                            builderConclusao.append(" e acabou atingindo " + colisao.getPedestre().getNome());
                            builderConclusao.append(" que por sua vez possui total responsabilidade pelo fato ocorrido.");
                        }
                        break;
                    case OBJETO:
                        if (colisao.isVeiculo1Causador())
                        {
                            // V1 Causador
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(", devido " + colisao.getConclusaoVeiculo1().getValor());
                            builderConclusao.append(" e acabou atingindo " + colisao.getObjetoDescricao());
                        } else
                        {
                            // V1 Isento de culpa
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(" que por sua vez não possui responsabilidade pelo fato ocorrido.");
                        }
                        break;
                    case ANIMAL:
                        if (colisao.isVeiculo1Causador())
                        {
                            // V1 Causador
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(" que por sua vez possui total responsabilidade pelo fato ocorrido.");
                        } else
                        {
                            // V1 Isento de culpa
                            builderConclusao.append("do(a) condutor(a) do veículo " + colisao.getVeiculo1().toString());
                            builderConclusao.append(" que por sua vez não possui responsabilidade pelo fato ocorrido.");
                        }
                        break;
                }
            }
        }
        builderConclusao.append("\nNada mais havendo a lavrar, fica encerrado o presente laudo.");
    }


}
