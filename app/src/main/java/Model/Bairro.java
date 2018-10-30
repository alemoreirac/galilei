package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 30/06/2017.
 */

public class Bairro extends SugarRecord
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

    String descricao;

    Long idGalileu;

    public Bairro()
    {}

    public Bairro(String descricao, Long idGalileu)
    {
        this.descricao = descricao;
        this.idGalileu = idGalileu;
    }

    public Bairro(String descricao) {
        this.descricao = descricao;
    }
}
