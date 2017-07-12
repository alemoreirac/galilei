package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 30/06/2017.
 */

public class Cidade extends SugarRecord<Cidade> {

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    String Descricao;

    public Cidade()
    {

    }

    public Cidade(String descricao) {
        Descricao = descricao;
    }
}
