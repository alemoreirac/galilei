package Enums.Vida;

/**
 * Created by Pefoce on 22/01/2018.
 */

public enum TipoRecolhimentoAmostra_Biologica
{
        SWAB("Swab"),
        APOIO("Apoio"),
        OUTRO("Outro");

        String valor;

    TipoRecolhimentoAmostra_Biologica(String s)
        {
            this.valor = s;
        }

        public String getValor()
        {
            return this.valor;
        }

}
