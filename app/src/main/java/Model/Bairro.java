package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 30/06/2017.
 */

public class Bairro extends SugarRecord
{

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    String Descricao;

    public Bairro()
    {

    }

    public Bairro(String descricao) {
        Descricao = descricao;
    }
}
