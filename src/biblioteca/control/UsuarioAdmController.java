/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.bd.BD;
import biblioteca.model.dao.UsuarioAdmDAO;
import biblioteca.model.dao.UsuarioDAO;
import biblioteca.model.usuarios.Cargo;
import biblioteca.model.usuarios.Usuario;
import biblioteca.model.usuarios.UsuarioAdm;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class UsuarioAdmController 
    extends UsuarioController{
    
    public void cadastrar(int id, String nome, String login, String senha, String cargo) throws BancoException, ClassNotFoundException, SQLException{
       UsuarioAdmDAO.inserir(new UsuarioAdm(id,nome,login,senha, cargo));
    }
    
    
}
