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
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class UsuarioAdmController {
    
    public void cadastrar(int id, String nome, String login, String senha, String cargo) throws BancoException, ClassNotFoundException, SQLException{
       UsuarioAdmDAO.inserir(new UsuarioAdm(id,nome,login,senha, cargo));
    }
    public void remover(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
        UsuarioDAO.excluir(ob);
    }

    public List<UsuarioAdm> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  UsuarioAdmDAO.listarAdm();
      
    }
    
    public UsuarioAdm buscar(String login) throws BancoException, ClassNotFoundException, SQLException {
        return  UsuarioAdmDAO.buscar(login);
    }
    
}
