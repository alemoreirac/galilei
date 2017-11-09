package Util;

import android.widget.Spinner;

import java.util.List;

import Enums.AreaIntegradaSeguranca;
import Enums.AtoresColisao;
import Enums.CategoriaFoto;
import Enums.Conclusao;
import Enums.CondicaoPista;
import Enums.Cor;
import Enums.DocumentoPessoa;
import Enums.DocumentoSolicitacao;
import Enums.EstadoSitioColisao;
import Enums.Genero;
import Enums.Iluminacao;
import Enums.Lesao;
import Enums.LocalObjeto;
import Enums.LocalPedestre;
import Enums.Orgao;
import Enums.OrientacaoGeografica;
import Enums.OrientacaoGeograficaComposta;
import Enums.Pavimentacao;
import Enums.PreservacaoLocal;
import Enums.Semaforo;
import Enums.SetorDano;
import Enums.SinalizacaoPare;
import Enums.TercoDano;
import Enums.TipoCNH;
import Enums.TipoDano;
import Enums.TipoInteracao;
import Enums.TipoJustificativa_Inconclusao;
import Enums.TipoOcorrencia;
import Enums.TipoVeiculo;
import Enums.TipoVestigio;
import Enums.TipoVia;
import Enums.TipoEnvolvidoTransito;
import Enums.Topografia;
import Model.ColisaoTransito;
import Model.EnderecoTransito;
import Model.EnvolvidoTransito;
import Model.Foto;
import Model.Veiculo;

/**
 * Created by Pefoce on 22/06/2017.
 */

public class BuscadorEnum {

    public static TipoOcorrencia BuscarTipoOcorrencia(String value)
    {
        if(value == "Trânsito")
            return TipoOcorrencia.TRANSITO;
        if(value == "Patrimônio")
            return TipoOcorrencia.PATRIMONIO;
        if(value == "Crime contra a vida")
            return TipoOcorrencia.VIDA;
        return null;
    }

    public static TipoVestigio BuscarTipoVestigio(String value)
    {
        if(value == "Derrapagem")
            return TipoVestigio.DERRAPAGEM;
        if(value == "Derrapagem em curva")
            return TipoVestigio.DERRAPAGEM_CURVA;
        if(value == "Fragmentos metálicos")
            return TipoVestigio.FRAGMENTOS_METALICOS;
        if(value == "Fragmentos vítreos")
            return TipoVestigio.FRAGMENTOS_VITREOS;
        if(value == "Sulcagem")
            return TipoVestigio.SULCAGEM;
        if(value == "Frenagem")
            return TipoVestigio.FRENAGEM;
        if(value == "Marcas de sangue")
            return TipoVestigio.SANGUE;
        return null;
    }


    public static Conclusao BuscarConclusao(String value)
    {
        if(value == "ao Condutor estar desatento às condições de tráfego à frente")
            return Conclusao.CONDUTOR_DESATENTO;
        if(value == "ao avanço de preferencial em cruzamento")
            return Conclusao.AVANCO_PREFERENCIAL;
        if(value == "à mudança de faixa definida pelo local dos danos")
            return Conclusao.MUDANCA_FAIXA;
        if(value == "à conversão em momento inapropriado")
            return Conclusao.CONVERSAO_INAPROPRIADA;
        if(value == "ao veiculo se deslocar na contra-mão")
            return Conclusao.CONTRA_MAO;
        if(value == "à ultrapassagem mal executada")
            return Conclusao.ULTRAPASSAGEM_INDEVIDA;
        if(value == "à manobra de retorno em momento inapropriado")
            return Conclusao.RETORNO_INAPROPRIADO;
        if(value == "O condutor não teve responsabilidade no ocorrido")
            return Conclusao.CONDUTOR_ISENTO;

            return null;
    }

    public static TipoEnvolvidoTransito BuscarTipoEnvolvido(String value)
    {
        if(value == "Pedestre")
            return TipoEnvolvidoTransito.PEDESTRE;
        if(value == "Passageiro")
            return TipoEnvolvidoTransito.PASSAGEIRO;
        if(value == "Motorista")
            return TipoEnvolvidoTransito.MOTORISTA;
        if(value == "Não Identificado")
            return  TipoEnvolvidoTransito.NAO_IDENTIFICADO;
        else
            return null;
    }

    public static AreaIntegradaSeguranca BuscarAIS(String value)
    {
        if(value == "AIS 1")
            return AreaIntegradaSeguranca.AIS_1;
        if(value == "AIS 2")
            return AreaIntegradaSeguranca.AIS_2;
        if(value == "AIS 3")
            return AreaIntegradaSeguranca.AIS_3;
        if(value == "AIS 4")
            return AreaIntegradaSeguranca.AIS_4;
        if(value == "AIS 5")
            return AreaIntegradaSeguranca.AIS_5;
        if(value == "AIS 6")
            return AreaIntegradaSeguranca.AIS_6;
        if(value == "AIS 7")
            return AreaIntegradaSeguranca.AIS_7;
        if(value == "AIS 8")
            return AreaIntegradaSeguranca.AIS_8;
        if(value == "AIS 9")
            return AreaIntegradaSeguranca.AIS_9;
        if(value == "AIS 10")
            return AreaIntegradaSeguranca.AIS_10;
        else
            return null;
    }

    public static LocalPedestre BuscarLocalPedestre(String value)
    {
        if(value == "Local sem faixa")
            return LocalPedestre.SEM_FAIXA;
        if(value == "Pedestre na faixa")
            return LocalPedestre.SEM_FAIXA;
        if(value == "Passarela de pedestres")
            return LocalPedestre.PASSARELA;
        if(value == "Próximo à faixa de pedestres")
            return LocalPedestre.PROXIMO_FAIXA;
        if(value == "Próximo à passarela de pedestres")
            return LocalPedestre.PROXIMO_PASSARELA;
        else
            return null;
    }

    public static LocalObjeto BuscarLocalObjeto(String value)
    {
        if(value == "Acostamento")
            return LocalObjeto.ACOSTAMENTO;
        if(value == "Calçada")
            return LocalObjeto.CALCADA;
        if(value == "Passarela")
            return LocalObjeto.PASSARELA;
        if(value == "Pista")
            return LocalObjeto.PISTA;
        else
            return null;
    }

    public static TipoJustificativa_Inconclusao BuscarJustificativa(String value)
    {
        if(value == "Condutor se evadiu")
            return TipoJustificativa_Inconclusao.CONDUTOR_EVADIU;
        if(value == "Envolvido se evadiu")
            return TipoJustificativa_Inconclusao.ENVOLVIDO_EVADIU;
        if(value == "Vestígios insuficientes para determinar o fato")
            return TipoJustificativa_Inconclusao.VESTIGIOS_INSUFICIENTES;
        if(value == "Local violado")
            return TipoJustificativa_Inconclusao.LOCAL_VIOLADO;
        if(value == "Posição final dos veículos alterada")
            return TipoJustificativa_Inconclusao.POSICAO_ALTERADA;
        else
            return null;
    }

    public static DocumentoSolicitacao BuscarDocSolicitacao(String value)
    {
        if(value.equalsIgnoreCase("Inquérito Policial"))
            return DocumentoSolicitacao.IP;

        if(value.equalsIgnoreCase("Boletim de Ocorrência"))
            return DocumentoSolicitacao.BO;

        if(value.equalsIgnoreCase("Termo Circunstanciado de Ocorrência"))
            return DocumentoSolicitacao.TCO;

        else
            return null;
    }

    public static Genero BuscarGenero(String value)
    {
        if(value == "Masculino")
            return  Genero.MASCULINO;
        if(value == "Feminino")
            return Genero.FEMININO;
        if(value == "Não Identificado")
            return Genero.NAO_IDENTIFICADO;
        else
            return null;
    }

    public static AtoresColisao BuscarAtoresColisao(String value)
    {
        if(value == "Nenhum")
            return  AtoresColisao.NENHUM;
        if(value == "Veículo")
            return  AtoresColisao.VEICULO;
        if(value == "Pedestre")
            return  AtoresColisao.PEDESTRE;
        if(value == "Objeto")
            return  AtoresColisao.OBJETO;
        else
            return null;
    }

    public static SetorDano BuscarSetorDano(String value)
    {
        if(value.equalsIgnoreCase("AAD"))
            return SetorDano.AAD;

        if(value.equalsIgnoreCase( "AAE"))
            return SetorDano.AAE;

        if(value.equalsIgnoreCase( "APD"))
            return SetorDano.APD;

        if(value.equalsIgnoreCase("APE"))
            return SetorDano.APE;

        if(value.equalsIgnoreCase("LAD"))
            return SetorDano.LAD;

        if(value.equalsIgnoreCase("LAE"))
            return SetorDano.LAE;

        if(value.equalsIgnoreCase("LMD"))
            return SetorDano.LMD;

        if(value.equalsIgnoreCase("LPD"))
            return SetorDano.LPD;

        if(value.equalsIgnoreCase("LPE"))
            return SetorDano.LPE;

        if(value.equalsIgnoreCase("LME"))
            return SetorDano.LME;

        if(value.equalsIgnoreCase("PAE"))
            return SetorDano.PAE;

        if(value.equalsIgnoreCase("PPM"))
            return SetorDano.PPM;

        if(value.equalsIgnoreCase("PAD"))
            return SetorDano.PAD;

        if(value.equalsIgnoreCase("PPE"))
            return SetorDano.PPE;

        if(value.equalsIgnoreCase("PPD"))
            return SetorDano.PPD;

        if(value.equalsIgnoreCase("PAM"))
            return SetorDano.PAM;

        else
            return null;

    }

    public static TipoCNH BuscarTipoCNH(String value)
    {
        if(value == "A")
            return TipoCNH.A;
        if(value == "B")
            return TipoCNH.B;
        if(value == "C")
            return TipoCNH.C;
        if(value == "D")
            return TipoCNH.D;
        if(value == "E")
            return TipoCNH.E;
        if(value == "AB")
            return TipoCNH.AB;
        if(value == "AC")
            return TipoCNH.AC;
        if(value == "AD")
            return TipoCNH.AD;
        if(value == "AB")
            return TipoCNH.AE;
        if(value == "Não Possui")
            return TipoCNH.NP;
        else
            return null;
    }

    public static EstadoSitioColisao BuscarEstadoColisao(String value)
    {
        if(value == "Fragmentos do Choque")
            return EstadoSitioColisao.FRAGMENTOS_CHOQUE;
        if(value == "Vestígios de Sangue")
            return EstadoSitioColisao.VESTIGIOS_SANGUE;
        if(value == "Marcações de Sulcagens")
            return EstadoSitioColisao.MARCACOES_SULCAGENS;
        if(value == "Fragmentos Vítreos")
            return EstadoSitioColisao.FRAGMENTOS_VITREOS;
        if(value == "Não Determinado")
            return EstadoSitioColisao.NAO_DETERMINADO;
        else
            return null;
    }

    public static PreservacaoLocal BuscarPreservacaoLocal(String value)
    {
        if(value == "Marcações da AMC")
            return PreservacaoLocal.MARCACOES_AMC;
        if(value == "Polícia Militar")
            return PreservacaoLocal.POLICIA_MILITAR;
        if(value == "Preservado por Populares")
            return PreservacaoLocal.POPULARES;
        if(value == "Não Preservado")
            return PreservacaoLocal.NAO_PRESERVADO;
        else
            return null;
    }

    public static CondicaoPista BuscarCondicaoPista(String value)
    {
        if(value == "Boa")
            return CondicaoPista.BOA;
        if(value == "Má")
            return CondicaoPista.RUIM;
        else
            return null;
    }

    public static Pavimentacao BuscarPavimentacao(String value)
    {
        if(value == "Asfalto")
            return Pavimentacao.ASFALTO;
        if(value == "Paralelepipedo")
            return Pavimentacao.PARALELEPIPEDO;
        if(value == "Terra")
            return Pavimentacao.TERRA;
        if(value == "Concreto")
            return Pavimentacao.CONCRETO;
        else
            return null;
    }

    public static Semaforo BuscarSemaforizacao (String value)
    {
        if(value == "Ativo")
            return Semaforo.ATIVO;
        if(value == "Inativo")
            return Semaforo.INATIVO;
        if(value == "Ausente")
            return Semaforo.AUSENTE;
        else
            return null;
    }


    public static SinalizacaoPare BuscarSinalizacaoPare (String value)
    {
        if(value == "Vertical")
            return SinalizacaoPare.VERTICAL;
        if(value == "Horizontal")
            return SinalizacaoPare.HORIZONTAL;
        if(value == "Vertical e Horizontal")
            return SinalizacaoPare.VERTICAL_E_HORIZONTAL;
        if(value == "Más Condições")
            return SinalizacaoPare.MAS_CONDICOES;
        if(value == "Ausente")
            return SinalizacaoPare.AUSENTE;
        else
            return null;
    }

    public static Lesao BuscarLesao(String value)
    {
        if(value == "Leve")
            return Lesao.LEVE;
        if(value == "Grave")
            return Lesao.GRAVE;
        if(value == "Fatal")
            return Lesao.FATAL;
        else
            return null;
    }

    public static TipoVia BuscarTipoVia(String value)
    {
        if(value == "Rua")
            return TipoVia.RUA;
        if(value == "Avenida")
            return TipoVia.AVENIDA;
        if(value == "BR")
            return TipoVia.BR;
        if(value == "CE")
            return TipoVia.CE;
        else
            return null;
    }

    public static Topografia BuscarTopografia (String value)
    {
        if(value == "Plana")
            return Topografia.RETA_PLANA;
        if(value == "Aclive")
            return Topografia.ACLIVE;
        if(value == "Declive")
            return Topografia.DECLIVE;
        else
            return null;
    }


    public static Iluminacao BuscarIluminacao (String value)
    {
        if(value == "Ausente")
            return Iluminacao.AUSENTE;
        if(value == "Boa")
            return Iluminacao.BOA;
        if(value == "Ruim")
            return Iluminacao.RUIM;
        else
            return null;
    }

    public static Orgao BuscarOrgao (String value)
    {
        if(value == "Autarquia Municipal")
            return Orgao.AM;
        if(value == "Polícia Rodoviária Federal")
            return Orgao.PRF;
        if(value == "Polícia Rodoviária Estadual")
            return Orgao.PRE;
        if(value == "Polícia Militar")
            return Orgao.PM;
        if(value == "Guarda Municipal")
            return Orgao.GM;
        else
            return null;
    }

    public static OrientacaoGeografica BuscarOrientacao(String value)
    {
        if(value == "Norte")
            return OrientacaoGeografica.NORTE;
        if(value == "Sul")
            return OrientacaoGeografica.SUL;
        if(value == "Leste")
            return OrientacaoGeografica.LESTE;
        if(value == "Oeste")
            return OrientacaoGeografica.OESTE;
        if(value == "Sudeste")
            return OrientacaoGeografica.SUDESTE;
        if(value == "Nordeste")
            return OrientacaoGeografica.NORDESTE;
        if(value == "Noroeste")
            return OrientacaoGeografica.NOROESTE;
        if(value == "Sudoeste")
            return OrientacaoGeografica.SUDOESTE;
        else
            return null;
    }
    public static OrientacaoGeograficaComposta BuscarOrientacaoComposta(String value)
    {
        if(value == "Norte-Sul")
            return OrientacaoGeograficaComposta.NORTE_SUL;
        if(value == "Sul-Norte")
            return OrientacaoGeograficaComposta.SUL_NORTE;
        if(value == "Leste-Oeste")
            return OrientacaoGeograficaComposta.LESTE_OESTE;
        if(value == "Oeste-Leste")
            return OrientacaoGeograficaComposta.OESTE_LESTE;
        if(value == "Sudeste-Noroeste")
            return OrientacaoGeograficaComposta.SUDESTE_NOROESTE;
        if(value == "Nordeste-Sudoeste")
            return OrientacaoGeograficaComposta.NORDESTE_SUDOESTE;
        if(value == "Noroeste-Sudeste")
            return OrientacaoGeograficaComposta.NOROESTE_SUDESTE;
        if(value == "Sudoeste-Nordeste")
            return OrientacaoGeograficaComposta.SUDOESTE_NORDESTE;
        else
            return null;
    }
    public static TipoInteracao BuscarTipoInteracao(String value)
    {
        if(value == "Colisão em Cruzamento")
            return TipoInteracao.COLISAO_CRUZ;
        if(value == "Colisão")
            return TipoInteracao.COLISAO;
        if(value == "Colisão Frontal")
            return TipoInteracao.COLISAO_FRONTAL;
        if(value == "Colisão Traseira")
            return TipoInteracao.COLISAO_TRASEIRA;
        if(value == "Colisão Lateral")
            return TipoInteracao.COLISAO_LATERAL;
        if(value == "Abalroamento")
            return TipoInteracao.ABALROAMENTO;
        if(value == "Adernamento")
            return TipoInteracao.ADERNAMENTO;
        if(value == "Choque")
            return TipoInteracao.CHOQUE;
        if(value == "Atropelamento")
            return TipoInteracao.ATROPELAMENTO;
        else
            return null;
    }

    public static DocumentoPessoa BuscarTipoDocumento(String value) {
        if(value == "Carteira de Identidade")
            return DocumentoPessoa.RG;
        if(value == "Cadastro de Pessoa Física")
            return DocumentoPessoa.CPF;
        if(value == "Passaporte")
            return DocumentoPessoa.PASSAPORTE;
        if(value == "Carteira de Trabalho e Previdência Social")
            return  DocumentoPessoa.CTPS;
        if(value == "Carteira Nacional de Habilitação")
            return DocumentoPessoa.CNH;
        else
            return null;
    }


    public static TipoVeiculo BuscarTipoVeiculo(String value) {
        if(value == "Caminhão")
            return TipoVeiculo.CAMINHAO;
        if(value == "Moto")
            return TipoVeiculo.MOTO;
        if(value == "Carro")
            return TipoVeiculo.CARRO;
        if(value == "Ônibus")
            return  TipoVeiculo.ONIBUS;
        else
            return null;
    }

    public static TercoDano BuscarTercoDano(String value) {
        if(value == "Superior")
            return TercoDano.SUPERIOR;
        if(value == "Inferior")
            return TercoDano.INFERIOR;
        if(value == "Médio")
            return TercoDano.MEDIO;
        else
            return null;
    }

    public static CategoriaFoto BuscarCategoriaFoto(String value) {
        if(value == "Veículos")
            return CategoriaFoto.VEICULOS;
        if(value == "Envolvidos")
            return CategoriaFoto.ENVOLVIDOS;
        if(value == "Endereços")
            return CategoriaFoto.ENDERECOS;
        if(value == "Outros")
            return CategoriaFoto.OUTROS;
        else
            return null;
    }

    public static TipoDano BuscarTipoDano(String value) {
        if(value == "Contuso")
            return TipoDano.CONTUSO;
        if(value == "Perfurante")
            return TipoDano.PERFURANTE;
        if(value == "Fricção")
            return TipoDano.FRICCAO;
        if(value == "Cortante")
            return TipoDano.CORTANTE;
        else
            return null;
    }

    public static Cor BuscarCor(String value)
    {
        if(value == "Amarela")
            return Cor.AMARELO;
        if (value == "Azul")
            return Cor.AZUL;
        if(value=="Bege")
            return Cor.BEGE;
        if(value=="Branca")
            return Cor.BRANCO;
        if(value=="Roxa")
            return Cor.ROXO;
        if(value=="Cinza")
            return Cor.CINZA;
        if(value=="Rosa")
            return Cor.ROSA;
        if(value=="Preta")
            return Cor.PRETO;
        if(value=="Laranja")
            return Cor.LARANJA;
        if(value == "Vermelha")
            return Cor.VERMELHO;
        if(value == "Verde")
            return Cor.VERDE;
        else
            return null;
    }

    public static Integer getIndex(Spinner spinner, String myString)
    {
        for (int i=0;i<spinner.getCount();i++)
        {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString))
            {
                return i;
            }
        }
        return 0;
    }

    public static Integer getVeiculoIndexById(Spinner spinner, Long id)
    {
        for (int i=0;i<spinner.getCount();i++)
        {
            if (((Veiculo)spinner.getItemAtPosition(i)).getId() == id)
            {
                return i;
            }
        }
        return null;
    }

    public static Integer getEnvolvidoIndexById(Spinner spinner, Long id)
    {
        for (int i=0;i<spinner.getCount();i++)
        {
            if (((EnvolvidoTransito)spinner.getItemAtPosition(i)).getId() == id)
            {
                return i;
            }
        }
        return null;
    }

    public static Integer getEnderecoIndexById(Spinner spinner, Long id)
    {
        for (int i=0;i<spinner.getCount();i++)
        {
            if (((EnderecoTransito)spinner.getItemAtPosition(i)).getId() == id)
            {
                return i;
            }
        }
        return null;
    }

    public static int PegarPosicaoVeiculo(List<Veiculo> veiculos, Veiculo v)
    {
        for(int i = 0; i<veiculos.size();i++)
        {
            if(veiculos.get(i).getId().equals(v.getId()))
            {
                return i;
            }
        }
        return -1;
    }
    public static int PegarPosicaoEndereco(List<EnderecoTransito> enderecos, EnderecoTransito et)
    {
        for(int i = 0; i<enderecos.size();i++)
        {
            if(enderecos.get(i).getId().equals(et.getId()))
            {
                return i;
            }
        }
        return -1;
    }
    public static int PegarPosicaoEnvolvido(List<EnvolvidoTransito> envolvidos, EnvolvidoTransito et)
    {
        for(int i = 0; i<envolvidos.size();i++)
        {
            if(envolvidos.get(i).getId().equals(et.getId()))
            {
                return i;
            }
        }
        return -1;
    }
    public static int PegarPosicaoFoto(List<Foto> fotos, Foto foto)
    {
        for(int i = 0; i<fotos.size();i++)
        {
            if(fotos.get(i).getId().equals(foto.getId()))
            {
                return i;
            }
        }
        return -1;
    }
    public static int PegarPosicaoColisao(List<ColisaoTransito> colisoes, ColisaoTransito ct)
    {
        for(int i = 0; i<colisoes.size();i++)
        {
            if(colisoes.get(i).getId().equals(ct.getId()))
            {
                return i;
            }
        }
        return -1;
    }

}
