package ViewHolders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pefoce on 21/06/2017.
 */


public class ViewHolderGaleria {
    TextView txvTitulo;
    ImageView imgvFoto;

    public TextView getTxvTitulo() {
        return txvTitulo;
    }

    public void setTxvTitulo(TextView txvTitulo) {
        this.txvTitulo = txvTitulo;
    }

    public ImageView getImgvFoto() {
        return imgvFoto;
    }

    public void setImgvFoto(ImageView imgvFoto) {
        this.imgvFoto = imgvFoto;
    }
}
