package Model;

import com.orm.SugarRecord;

/**
 * Created by Pefoce on 09/06/2017.
 */

public class Foto extends SugarRecord<Foto> {

    private byte[] arquivo;

    public Foto() {
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public Foto(byte[] arquivo) {
        this.arquivo = arquivo;
    }
}
