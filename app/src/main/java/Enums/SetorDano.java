package Enums;

/**
 * Created by Pefoce on 05/06/2017.
 */

public enum SetorDano {
    FRONTAL("Setor Frontal"),
    POSTERIOR("Setor Posterior"),
    LATERAL_ESQUERDO ("Setor Lateral Esquerdo"),
    LATERAL_DIREITO("Setor Lateral Direito"),
    ANTERIOR_ESQUERDO("Setor Anterior Esquerdo"),
    ANTERIOR_DIREITO("Setor Anterior Direito"),
    POSTERIOR_ESQUERDO("Setor Posterior Esquerdo"),
    POSTERIOR_DIREITO ("Setor Posterior Direito");

    String valor;

    public String getValor() {
        return valor;
    }


    SetorDano(String s) {
    this.valor = s;
    }


}
