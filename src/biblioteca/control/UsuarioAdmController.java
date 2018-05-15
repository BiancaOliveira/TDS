/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.usuarios.UsuarioAdm;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class UsuarioAdmController 
    extends UsuariosController <UsuarioAdm>{
    
    public Set<UsuarioAdm> listarUsuariosAdms() {
        return BD.getBanco().listarUsuariosAdms();
    }  
    
}
