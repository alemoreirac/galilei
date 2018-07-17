package Enums;

/**
 * Created by Pefoce on 23/01/2018.
 */

public enum TiposMunicao
{
    NAO_DEFLAGRADA("Não deflagrada"),
    ESTOJO("Estojo"),
    PROJETIL("Projétil"),
    JAQUETA("Jaqueta"),
    NUCLEO("Núcleo"),
    FRAGMENTO("Fragmento");

    String valor;

    public String getValor() {
        return valor;
    }

    TiposMunicao(String s) {
        this.valor = s;
    }
}
