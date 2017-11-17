package Model.Transito;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 17/07/2017.
 */

public class EnderecoVeiculo extends SugarRecord<EnderecoVeiculo> {

    public EnderecoVeiculo() {
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public EnderecoTransito getEnderecoTransito() {
        return endereco;
    }

    public void setEnderecoTransito(EnderecoTransito Endereco) {
        this.endereco = Endereco;
    }

    private Veiculo veiculo;
    private EnderecoTransito endereco;

}
