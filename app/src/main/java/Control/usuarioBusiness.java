package Control;

import Model.Usuario;

/**
 * Created by Pefoce on 07/08/2018.
 */

public class usuarioBusiness
{
    public static Usuario loginUsuario(String email, String senha)
    {
       try
       {
           return Usuario.find(Usuario.class, "email = ? AND password = ?", email.trim(), senha.trim()).get(0);
       }catch (Exception e)
       {
           return null;
       }
    }
}
