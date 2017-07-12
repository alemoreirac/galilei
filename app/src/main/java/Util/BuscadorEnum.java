package Util;

import android.widget.Spinner;

import Enums.CondicaoPista;
import Enums.DocumentoPessoa;
import Enums.DocumentoSolicitacao;
import Enums.EstadoSitioColisao;
import Enums.Iluminacao;
import Enums.Orgao;
import Enums.Pavimentacao;
import Enums.PreservacaoLocal;
import Enums.Semaforo;
import Enums.SinalizacaoPare;
import Enums.TipoVia;
import Enums.TipoVitimaTransito;
import Enums.Topografia;

/**
 * Created by Pefoce on 22/06/2017.
 */

public class BuscadorEnum {

    public static DocumentoSolicitacao BuscarDocSolicitacao(String value)
    {
        if(value == "Inquérito Policial")
            return DocumentoSolicitacao.IP;

        if(value == "Boletim de Ocorrência")
            return DocumentoSolicitacao.BOC;

        if(value == "Termo Circunstanciado de Ocorrência")
            return DocumentoSolicitacao.TCO;

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
        if(value == "Ruim")
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
        if(value == "Horizontal Apagado")
            return SinalizacaoPare.HORIZONTAL_APAGADO;
        if(value == "Ausente")
            return SinalizacaoPare.AUSENTE;
        else
            return null;
    }

    public static TipoVia BuscarTipoVia(String value)
    {
        if(value == "Rua")
            return TipoVia.RUA;
        if(value == "Avenida")
            return TipoVia.AVENIDA;
        if(value == "Estrada")
            return TipoVia.ESTRADA;
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



    public static int getIndex(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }

}
