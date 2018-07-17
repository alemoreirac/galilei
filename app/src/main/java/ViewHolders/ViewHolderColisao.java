package ViewHolders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pefoce on 19/06/2017.
 */

public class ViewHolderColisao
{
    TextView txvAtor1;
    TextView txvAtor2;
    TextView txvTipoInteracao;
    ImageView imgvTipoInteracao;


    public TextView getTxvAtor1() {
        return txvAtor1;
    }

    public void setTxvAtor1(TextView txvAtor1) {
        this.txvAtor1 = txvAtor1;
    }

    public TextView getTxvAtor2() {
        return txvAtor2;
    }

    public void setTxvAtor2(TextView txvAtor2) {
        this.txvAtor2 = txvAtor2;
    }

    public TextView getTxvTipoInteracao() {
        return txvTipoInteracao;
    }

    public void setTxvTipoInteracao(TextView txvTipoInteracao) {
        this.txvTipoInteracao = txvTipoInteracao; }

    public ImageView getImgvTipoInteracao() {
        return imgvTipoInteracao;
    }

    public void setImgvTipoInteracao(ImageView imgvTipoInteracao) {
        this.imgvTipoInteracao = imgvTipoInteracao;
    }
}
