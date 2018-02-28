package Enums.Vida;

/**
 * Created by Pefoce on 20/11/2017.
 */
public enum ParteCorpo
{
    CABECA("Cabeça"),
    TORAX("Tórax"),
    BRACOS("Braços"),
    PERNAS("Pernas");


    String valor;

    public String getValor() {
        return valor;
    }


    ParteCorpo(String s) {
        this.valor = s;
    }



}
