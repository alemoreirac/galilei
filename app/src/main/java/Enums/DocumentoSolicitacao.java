package Enums;

/**
 * Created by Pefoce on 29/05/2017.
 */

public enum DocumentoSolicitacao
{
    NL("Nº Laudo"), IP("Inquérito Policial"), BO("Boletim de Ocorrência"), TCO("Termo Circunstanciado de Ocorrência");

    String valor;

    public String getValor()
    {
        return valor;
    }

    DocumentoSolicitacao(String s)
    {
        this.valor = s;
    }

}
