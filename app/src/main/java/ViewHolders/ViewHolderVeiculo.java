package ViewHolders;

import android.widget.TextView;

/**
 * Created by Pefoce on 19/06/2017.
 */

public class ViewHolderVeiculo  {
    public TextView getTxvModelo() {
        return txvModelo;
    }

    public void setTxvModelo(TextView txvModelo) {
        this.txvModelo = txvModelo;
    }

    public TextView getTxvPlaca() {
        return txvPlaca;
    }

    public void setTxvPlaca(TextView txvPlaca) {
        this.txvPlaca = txvPlaca;
    }

    TextView txvModelo;
    TextView txvPlaca;

    public TextView getTxvTipo() {
        return txvTipo;
    }

    public void setTxvTipo(TextView txvTipo) {
        this.txvTipo = txvTipo;
    }

    TextView txvTipo;

}
