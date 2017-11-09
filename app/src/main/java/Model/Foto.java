package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Enums.CategoriaFoto;

/**
 * Created by Pefoce on 09/06/2017.
 */

public class Foto extends SugarRecord<Foto>
{
    @Expose
    private String descricao;
    @Expose
    private String arquivo;

    @Expose
    private CategoriaFoto categoriaFoto;

    public CategoriaFoto getCategoriaFoto() {
        return categoriaFoto;
    }

    public void setCategoriaFoto(CategoriaFoto categoriaFoto) {
        this.categoriaFoto = categoriaFoto;
    }

    public Foto(String descricao, String arquivo, CategoriaFoto categoriaFoto) {
        this.descricao = descricao;
        this.arquivo = arquivo;
        this.categoriaFoto = categoriaFoto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Foto() {
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public Foto(String descricao, String arquivo) {
        this.descricao = descricao;
        this.arquivo = arquivo;
    }
}
