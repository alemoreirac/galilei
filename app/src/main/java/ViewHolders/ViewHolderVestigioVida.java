package ViewHolders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pefoce on 19/06/2017.
 */

public class ViewHolderVestigioVida
{
    ImageView imgvTipoVestigio;
    TextView txvTipoVestigio;
    TextView txvCategoriaVestigio;
    TextView txvObservacaoVestigio;


    public ImageView getImgvTipoVestigio()
    {
        return imgvTipoVestigio;
    }

    public void setImgvTipoVestigio(ImageView imgvTipoVestigio)
    {
        this.imgvTipoVestigio = imgvTipoVestigio;
    }

    public TextView getTxvTipoVestigio()
    {
        return txvTipoVestigio;
    }

    public void setTxvTipoVestigio(TextView txvTipoVestigio)
    {
        this.txvTipoVestigio = txvTipoVestigio;
    }

    public TextView getTxvCategoriaVestigio()
    {
        return txvCategoriaVestigio;
    }

    public void setTxvCategoriaVestigio(TextView txvCategoriaVestigio)
    {
        this.txvCategoriaVestigio = txvCategoriaVestigio;
    }

    public TextView getTxvObservacaoVestigio()
    {
        return txvObservacaoVestigio;
    }

    public void setTxvObservacaoVestigio(TextView txvObservacaoVestigio)
    {
        this.txvObservacaoVestigio = txvObservacaoVestigio;
    }
}
