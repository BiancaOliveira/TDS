
import biblioteca.model.usuarios.Usuarios;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bianca
 */
public abstract class UsuariosController <C extends Usuarios> {
    
    public abstract boolean cadastrarUsuario(C usuario);
    
    public abstract boolean removerUsuario(C usuario);
   
    public abstract Set<C> listarUsuarios();
    
    public C buscarUsuario(String login) {
        for (C usuario : listarUsuarios()) {
            if (usuario.getLogin() == login)
                return usuario;
        }
        return null;
    }  
}
