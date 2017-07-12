package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 11/07/2017.
 */

public class DanoVeiculo extends SugarRecord<DanoVeiculo> {

    private Dano dano;
    private Veiculo veiculo;

    public DanoVeiculo() {
    }

    public DanoVeiculo(Dano dano, Veiculo veiculo) {
        this.dano = dano;
        this.veiculo = veiculo;
    }

    public Dano getDano() {
        return dano;
    }

    public void setDano(Dano dano) {
        this.dano = dano;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
