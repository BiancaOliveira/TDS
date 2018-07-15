/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.bd.BD;
import biblioteca.model.dao.UsuarioAlunoDAO;
import biblioteca.model.usuarios.UsuarioAluno;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class UsuarioAlunoController{
    public void cadastrar(int id, String nome, String login, String senha,int numeroRegistro ,String telefone) throws BancoException, ClassNotFoundException, SQLException{
       UsuarioAlunoDAO.inserir(new UsuarioAluno(id,nome,login,senha, numeroRegistro,telefone));
    }
    
    public void remover(UsuarioAluno ob) throws BancoException, ClassNotFoundException, SQLException{
        UsuarioAlunoDAO.excluir(ob);
    }

    public void altera(UsuarioAluno ob) throws BancoException, ClassNotFoundException, SQLException{
        UsuarioAlunoDAO.alterar(ob);
    }
    
    public List<UsuarioAluno> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  UsuarioAlunoDAO.listar();
    }
    
    public List<UsuarioAluno> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  UsuarioAlunoDAO.buscarVarios(nome);
    }
    
    
}
