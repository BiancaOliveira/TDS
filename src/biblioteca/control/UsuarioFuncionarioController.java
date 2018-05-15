/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.usuarios.UsuarioFuncionario;

import java.util.Set;

/**
 *
 * @author Bianca
 */
public class UsuarioFuncionarioController 
    extends UsuariosController <UsuarioFuncionario>{
    
    public Set<UsuarioFuncionario> listarUsuariosFuncionarios() {
        return BD.getBanco().listarUsuariosFuncionarios();
    }
    
}
