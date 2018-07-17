package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum PreservacaoLocal {
    MARCACOES_AMC("Marcações da AMC")
    ,AUTORIDADE_LOCAL("Preservado pela autoridade presente")
    ,POPULARES("Preservado por Populares")
    ,NAO_PRESERVADO("Não Preservado");

    String valor;

    public String getValor() {
        return valor;
    }



    PreservacaoLocal(String s) {
        this.valor = s;
    }
}
