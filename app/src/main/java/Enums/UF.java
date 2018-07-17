package Enums;

/**
 * Created by Pefoce on 04/06/2018.
 */

public enum UF

    {
        DESCONHECIDO("Desconhecido"),
        AC("AC - Acre"),
        AL("AL - Alagoas"),
        AP("AP - Amapá"),
        AM("AM - Amazonas"),
        BA("BA - Bahia"),
        CE("CE - Ceará"),
        DF("DF - Distrito Federal"),
        ES("ES - Espirito Santo"),
        GO("GO - Goiás"),
        MA("MA - Maranhão"),
        MT("MT - Mato Grosso"),
        MS("MS - Mato Grosso do Sul"),
        MG("MG - Minas Gerais"),
        PA("PA - Pará"),
        PB("PB - Paraíba"),
        PR("PR - Paraná"),
        PE("PE - Pernambuco"),
        PI("PI - Piauí"),
        RJ("RJ - Rio de Janeiro"),
        RN("RN - Rio Grande do Norte"),
        RS("RS - Rio Grande do Sul"),
        RO("RO - Roraima"),
        RR("RR - Rondônia"),
        SC("SC - Santa Catarina"),
        SP("SP - São Paulo"),
        SE("SE - Sergipe"),
        TO("TO - Tocantins");

        String valor;

    public String getValor() {
        return valor;
    }

    UF(String s) {
        this.valor = s;
    }
}
