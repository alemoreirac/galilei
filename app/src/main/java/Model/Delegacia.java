package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 23/06/2017.
 */


public  class Delegacia extends SugarRecord
{
    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Delegacia() {
    }

    public Delegacia(String descricao) {
        Descricao = descricao;
    }

    String Descricao;
}