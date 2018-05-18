package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import Enums.DocumentoSolicitacao;

/**
 * Created by Pefoce on 12/06/2017.
 */

public class DocumentoOcorrencia extends SugarRecord
{

    @Expose
    DocumentoSolicitacao tipoDocumento;
    @Expose
    String valor;

    public DocumentoOcorrencia(DocumentoSolicitacao tipodocumento, String valor) {
        this.tipoDocumento = tipodocumento;
        this.valor = valor;
    }

    public DocumentoOcorrencia(){}

    public void setTipodocumento(DocumentoSolicitacao tipodocumento) {
        this.tipoDocumento = tipodocumento;
    }

    public DocumentoSolicitacao getTipodocumento() {
        return tipoDocumento;
    }




    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
