package Enums;

/**
 * Created by Pefoce on 04/10/2017.
 */

public enum CategoriaFoto
{
    DESENHO("Desenho de local"),
    LOCAL_OCORRENCIA("Local Ocorrência"),
    VESTIGIOS("Vestígios"),
    ENVOLVIDOS("Envolvidos"),
    VEICULOS("Veículos"),
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
