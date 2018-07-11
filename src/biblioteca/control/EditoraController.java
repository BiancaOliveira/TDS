/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.bd.BD;
import biblioteca.model.dao.EditoraDAO;
import biblioteca.model.livros.Editora;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author gabriela
 */
public class EditoraController{

    public void cadastrar(int id, String nome) throws BancoException, ClassNotFoundException, SQLException{
       EditoraDAO.inserir(new Editora(id,nome));
    }
    
    public void remover(Editora ob) throws BancoException, ClassNotFoundException, SQLException{
        EditoraDAO.excluir(ob);
    }

    public List<Editora> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  EditoraDAO.listar();
      
    }
    
    public List<Editora> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  EditoraDAO.buscarVarios(nome);
    } 
}
