/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.control.UsuariosController;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import biblioteca.model.usuarios.Usuarios;

/**
 *
 * @author Bianca
 */
public class Main {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
    
    UsuariosController<Usuarios> controleUsuarios = 
            new UsuariosController();
    controleUsuarios.cadastrarUsuario(new UsuarioAdm(1, "Administrador", "Adm","123456"));
    controleUsuarios.cadastrarUsuario(new UsuarioFuncionario(1, "Funcionario", "Fun","8542","Bibliotecario"));
    controleUsuarios.cadastrarUsuario(new UsuarioAluno(1, "Aluno", "Aluno","123456",15420,"(45)9999-9999"));
       
        
        
        
    }
}
