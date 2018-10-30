package Model;

import com.google.gson.annotations.Expose;
import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by Pefoce on 05/06/2017.
 */

public class Usuario extends SugarRecord implements Serializable
{
    private Long idGalileu;

    @Expose
    private String email;
    @Expose
    private String nome;

    private String password;


    public Usuario()
    {
    }

    public Usuario(String email, String nome, String senha, Long galileuId)
    {
        this.email = email;
        this.nome = nome;
        this.idGalileu = galileuId;
        this.password = senha;
    }


    public Long getIdGalileu()
    {
        return idGalileu;
    }

    public void setIdGalileu(Long idGalileu)
    {
        this.idGalileu = idGalileu;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getNome()
    {
        return nome;
    }


    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public void setNome(String nome)
    {
        this.nome = nome;
    }


}
