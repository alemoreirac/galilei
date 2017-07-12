package Enums;

/**
 * Created by Pefoce on 30/05/2017.
 */
public enum DocumentoPessoa {
    RG("Carteira de Identidade")
    ,CPF("Cadastro de Pessoa Física")
    ,CNH("Carteira Nacional de Habilitação")
    ,PASSAPORTE("Passaporte")
    ,CTPS("Carteira de Trabalho e Previdência Social");


    String valor;

    public String getValor() {
        return valor;
    }

    DocumentoPessoa(String s) {
        this.valor = s;
    }
}
