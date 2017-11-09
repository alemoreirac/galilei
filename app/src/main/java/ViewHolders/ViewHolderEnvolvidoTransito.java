package ViewHolders;

import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Pefoce on 20/06/2017.
 */

public class ViewHolderEnvolvidoTransito {
    public TextView getTxvNome() {
        return txvNome;
    }

    public void setTxvNome(TextView txvNome) {
        this.txvNome = txvNome;
    }

    public TextView getTxvTipo() {
        return txvTipo;
    }

    public void setTxvTipo(TextView txvTipo) {
        this.txvTipo = txvTipo;
    }

    public TextView getTxvFatal() {
        return txvFatal;
    }

    public void setTxvFatal(TextView txvFatal) {
        this.txvFatal = txvFatal;
    }

    public TextView getTxvIdade() {
        return txvIdade;
    }

    public void setTxvIdade(TextView txvIdade) {
        this.txvIdade = txvIdade;
    }


    TextView txvNome;
    TextView txvTipo;
    TextView txvFatal;
    TextView txvIdade;

}
