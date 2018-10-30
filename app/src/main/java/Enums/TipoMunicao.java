package Enums;

/**
 * Created by Pefoce on 23/01/2018.
 */

public enum TipoMunicao
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

    TipoMunicao(String s) {
        this.valor = s;
    }
}
