package biblioteca.control;
import biblioteca.model.bd.BD;
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
public class UsuariosController <C extends Usuarios> {
    
    public  boolean cadastrarUsuario(C usuario){
      return BD.getBanco().addUsuario(usuario);
    }
    
    public boolean removerUsuario(String login){
        C usuario = buscarUsuario(login);     
        return BD.getBanco().removerUsuario(usuario);
    }
    
     public C buscarUsuario(String login) {
        for (Usuarios usuario : listarUsuarios()) {
            if (usuario.getLogin() == login)
                return (C) usuario;
        }
        return null;
    }
    
    public  Set<Usuarios> listarUsuarios(){
        return BD.getBanco().listarUsuarios();
    }
  
}
