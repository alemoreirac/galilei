package Enums;

/**
 * Created by Pefoce on 04/10/2017.
 */

public enum CategoriaFoto
{
    VEICULOS("Veículos"),
    ENVOLVIDOS("Envolvidos"),
    ENDERECOS("Endereços"),
    OUTROS("Outros");

    String valor;

    public String getValor() {
        return valor;
    }

    CategoriaFoto(String s) {
        this.valor = s;
    }
}
