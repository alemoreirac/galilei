package Enums.Transito;

/**
 * Created by Pefoce on 30/05/2017.
 */

public enum EstadoSitioColisao {
    NAO_DETERMINADO("Não Determinado")
    ,FRAGMENTOS_VITREOS("Fragmentos Vítreos")
    ,MARCACOES_SULCAGENS("Marcações de Sulcagens")
    ,VESTIGIOS_SANGUE("Vestígios de Sangue")
    ,FRAGMENTOS_CHOQUE("Fragmentos do Choque");


    String valor;

    public String getValor() {
        return valor;
    }


    EstadoSitioColisao(String s) {
        this.valor = s;
    }

}
