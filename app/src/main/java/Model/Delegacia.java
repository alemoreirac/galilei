package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 23/06/2017.
 */


public  class Delegacia extends SugarRecord
{
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdGalileu()
    {
        return idGalileu;
    }

    public void setIdGalileu(Long idGalileu)
    {
        this.idGalileu = idGalileu;
    }

    public Delegacia() {
    }

    public Delegacia(String descricao) {
        this.descricao = descricao;
    }

    String descricao;

    Long idGalileu;

    public Delegacia(String descricao, Long idGalileu)
    {
        this.descricao = descricao;
        this.idGalileu = idGalileu;
    }
}