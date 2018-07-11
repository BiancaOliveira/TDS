/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.bd.BD;
import biblioteca.model.dao.UsuarioFuncionarioDAO;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioFuncionario;
import java.sql.SQLException;
import java.util.List;

import java.util.Set;

/**
 *
 * @author Bianca
 */
public class UsuarioFuncionarioController {
    
    public void cadastrar(int id, String nome, String login, String senha, String cargo) throws BancoException, ClassNotFoundException, SQLException{
       UsuarioFuncionarioDAO.inserir(new UsuarioFuncionario(id,nome,login,senha, cargo));
    }
    public void remover(UsuarioFuncionario ob) throws BancoException, ClassNotFoundException, SQLException{
        UsuarioFuncionarioDAO.excluir(ob);
    }

    public List<UsuarioFuncionario> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  UsuarioFuncionarioDAO.listar();
      
    }
    
    public List<UsuarioFuncionario> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  UsuarioFuncionarioDAO.buscarVarios(nome);
    }
    
}
