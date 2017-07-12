package Model;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by Pefoce on 05/06/2017.
 */

public class Pessoa extends SugarRecord<Pessoa> implements Serializable {

    public Pessoa() {
    }

    public Pessoa(String login, String nome, Date nascimento, String senha) {
        this.login = login;
        this.nome = nome;
        this.nascimento = nascimento;
        this.senha = senha;
    }

    public Pessoa(String login, String nome, String senha) {
        String dtStart = "29/01/1994";
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = format.parse(dtStart);
 this.nascimento = date;
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        this.login = login;
        this.nome = nome;
        this.senha = senha;
    }

    private String login;
    private String nome;
    private Date nascimento;
    private String senha;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }
}
