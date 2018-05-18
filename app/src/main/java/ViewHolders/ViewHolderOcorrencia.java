package ViewHolders;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Pefoce on 19/06/2017.
 */

public class ViewHolderOcorrencia {

    TextView txtNumeroDoc;
    TextView txtEndereco;
    TextView txtOrigem;
    TextView txtDestino;
    ImageView imgvTipoOcorrencia;

    public ImageView getImgvTipoOcorrencia()
    {
        return imgvTipoOcorrencia;
    }

    public void setImgvTipoOcorrencia(ImageView imgvTipoOcorrencia)
    {
        this.imgvTipoOcorrencia = imgvTipoOcorrencia;
    }

    public TextView getTxtNumeroDoc() {
        return txtNumeroDoc;
    }

    public void setTxtNumeroDoc(TextView txtNumeroDoc) {
        this.txtNumeroDoc = txtNumeroDoc;
    }

    public TextView getTxtEndereco() {
        return txtEndereco;
    }

    public void setTxtEndereco(TextView txtEndereco) {
        this.txtEndereco = txtEndereco;
    }

    public TextView getTxtOrigem() {
        return txtOrigem;
    }

    public void setTxtOrigem(TextView txtOrigem) {
        this.txtOrigem = txtOrigem;
    }

    public TextView getTxtDestino() {
        return txtDestino;
    }

    public void setTxtDestino(TextView txtDestino) {
        this.txtDestino = txtDestino;
    }


}
