package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */
public enum DocumentoPessoa {
    NP("Não possui")
    ,RG("Carteira de identidade")
    ,CPF("Cadastro de pessoa física")
    ,CNH("Carteira nacional de habilitação")
    ,PASSAPORTE("Passaporte")
    ,CTPS("Carteira de trabalho e previdência social");

    String valor;

    public String getValor() {
        return valor;
    }

    DocumentoPessoa(String s) {
        this.valor = s;
    }
}
