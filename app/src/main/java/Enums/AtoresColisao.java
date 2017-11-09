package Enums;

/**
 * Created by Pefoce on 31/08/2017.
 */

public enum AtoresColisao
{

    VEICULO("Veículo"),OBJETO("Objeto"),PEDESTRE("Pedestre"),ANIMAL("Animal"),NENHUM("Nenhum");

    String valor;

    public String getValor() {
        return valor;
    }


    AtoresColisao(String s) {
        this.valor = s;
    }

    }