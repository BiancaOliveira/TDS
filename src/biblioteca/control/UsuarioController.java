package biblioteca.control;
import biblioteca.exception.BancoException;
import biblioteca.model.bd.BD;
import biblioteca.model.dao.UsuarioDAO;
import biblioteca.model.usuarios.Usuario;
import java.sql.SQLException;
import java.util.List;
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
public class UsuarioController {
    
    public void cadastrar(int id, String nome, String login, String senha) throws BancoException, ClassNotFoundException, SQLException{
       UsuarioDAO.inserir(new Usuario(id,nome,login,senha));
    }
    
    public void remover(Usuario ob) throws BancoException, ClassNotFoundException, SQLException{
        UsuarioDAO.excluir(ob);
    }

    public List<Usuario> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  UsuarioDAO.listar();
      
    }
    
    public List<Usuario> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  UsuarioDAO.buscarVarios(nome);
    }
}
