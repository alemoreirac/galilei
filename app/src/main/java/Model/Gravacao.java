package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

/**
 * Created by Pefoce on 24/08/2017.
 */

public class Gravacao extends SugarRecord

{
    public Gravacao() {
    }

    public Gravacao(String titulo, String arquivo) {
        this.titulo = titulo;
        this.arquivo = arquivo;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arq) {
        this.arquivo = arq;
    }
    @Expose
    String titulo;
    @Expose
    String arquivo;
}
