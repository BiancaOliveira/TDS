/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.usuarios.UsuarioAluno;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class UsuarioAlunoController 
    extends UsuariosController <UsuarioAluno>{
    
    public Set<UsuarioAluno> listarUsuariosAlunos() {
        return BD.getBanco().listarUsuariosAlunos();
    }
    
    
}
