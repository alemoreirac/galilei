package Model.Vida;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 21/11/2017.
 */

public class LesaoEnvolvido extends SugarRecord<LesaoEnvolvido>
{
    Lesao lesao;
    EnvolvidoVida envolvidoVida;

    public LesaoEnvolvido()
    {
    }

    public LesaoEnvolvido(Lesao lesao, EnvolvidoVida envolvidoVida)
    {
        this.lesao = lesao;
        this.envolvidoVida = envolvidoVida;
    }

    public Lesao getLesao()
    {
        return lesao;
    }

    public void setLesao(Lesao lesao)
    {
        this.lesao = lesao;
    }

    public EnvolvidoVida getEnvolvidoVida()
    {
        return envolvidoVida;
    }

    public void setEnvolvidoVida(EnvolvidoVida envolvidoVida)
    {
        this.envolvidoVida = envolvidoVida;
    }
}
