package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 30/06/2017.
 */

public class Municipio extends SugarRecord
{
    String descricao;

    Long idGalileu;

    public String getDescricao()
    {
        return descricao;
    }

    public Long getIdGalileu()
    {
        return idGalileu;
    }

    public void setIdGalileu(Long idGalileu)
    {
        this.idGalileu = idGalileu;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }


    public Municipio()
    {

    }

    public Municipio(String descricao)
    {
        this.descricao = descricao;
    }

    public Municipio(String descricao, Long galileuId)
    {
        this.idGalileu = galileuId;
        this.descricao = descricao;
    }
}
