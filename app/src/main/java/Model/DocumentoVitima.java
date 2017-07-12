package Model;

import Enums.DocumentoPessoa;

/**
 * Created by Pefoce on 29/05/2017.
 */

public class DocumentoVitima {
    DocumentoPessoa tipodocumento;
    String valor;

    public DocumentoVitima(DocumentoPessoa tipodocumento, String valor) {
        this.tipodocumento = tipodocumento;
        this.valor = valor;
    }
}
