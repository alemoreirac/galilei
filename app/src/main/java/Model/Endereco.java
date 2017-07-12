package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 30/05/2017.
 */
public class Endereco extends SugarRecord<Endereco> {

    public Endereco(String endereco) {
        this.descricao = endereco;
    }

    public Endereco() {
    }

    String cidade;

    String bairro;
    String descricao;

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setEndereco(String endereco) {
        this.descricao = endereco;
    }
}
