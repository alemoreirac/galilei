package Model;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import Enums.DocumentoPessoa;
import Enums.TipoVitimaTransito;
import Util.TempoUtil;


/**
 * Created by Pefoce on 30/05/2017.
 */

public class EnvolvidoTransito extends SugarRecord<EnvolvidoTransito> {
    String nome;
    DocumentoPessoa documentoTipo;
    String documentoValor;
    TipoVitimaTransito vitima;
    Boolean isFatal;
    Boolean isProprietario;
    Boolean isCondutor;
    Date nascimento;

    public EnvolvidoTransito() {
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public DocumentoPessoa getDocumentoTipo() {
        return documentoTipo;
    }

    public void setDocumentoTipo(DocumentoPessoa documentoTipo) {
        this.documentoTipo = documentoTipo;
    }


    public void setDataNascimentoString(String s)
    {
        final Calendar c  = Calendar.getInstance();
        c.setTime(TempoUtil.stringToDate(s));

        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        final Calendar cSave = Calendar.getInstance();

        cSave.set(Calendar.YEAR,year);
        cSave.set(Calendar.MONTH,month);
        cSave.set(Calendar.DAY_OF_MONTH,day);

        this.nascimento = cSave.getTime();
    }

    public String getDocumentoValor() {
        return documentoValor;
    }

    public void setDocumentoValor(String documentoValor) {
        this.documentoValor = documentoValor;
    }

    public TipoVitimaTransito getVitima() {
        return vitima;
    }

    public void setVitima(TipoVitimaTransito vitima) {
        this.vitima = vitima;
    }

    public Boolean isFatal() {
        return isFatal;
    }

    public void setFatal(Boolean fatal) {
        isFatal = fatal;
    }

    public Boolean getProprietario() {
        return isProprietario;
    }

    public void setProprietario(Boolean proprietario) {
        isProprietario = proprietario;
    }

    public Boolean getCondutor() {
        return isCondutor;
    }

    public void setCondutor(Boolean condutor) {
        isCondutor = condutor;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getNascimentoString(){
        // Get Current Date

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        final Calendar c = Calendar.getInstance();
        c.setTime(this.getNascimento());


        String data = Calendar.DAY_OF_MONTH + "/" + Calendar.MONTH + "/" + Calendar.YEAR;

        data = format.format(c.getTime());

        return data;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
}
