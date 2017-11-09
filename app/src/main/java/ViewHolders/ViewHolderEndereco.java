package ViewHolders;

import android.widget.TextView;

/**
 * Created by Pefoce on 28/06/2017.
 */

public class ViewHolderEndereco {
    public TextView getEndereco() {
        return endereco;
    }

    public void setEndereco(TextView endereco) {
        this.endereco = endereco;
    }

    TextView endereco;
    TextView bairro;
    TextView tipoVia;


    public TextView getBairro() {
        return bairro;
    }

    public void setBairro(TextView bairro) {
        this.bairro = bairro;
    }

    public TextView getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(TextView tipoVia) {
        this.tipoVia = tipoVia;
    }

}
