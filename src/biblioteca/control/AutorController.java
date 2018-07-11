/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.bd.BD;
import biblioteca.model.dao.AutorDAO;
import biblioteca.model.livros.Autor;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class AutorController {
     public void cadastrar(int id, String nome) throws BancoException, ClassNotFoundException, SQLException{
       AutorDAO.inserir(new Autor(id,nome));
    }
    
    public void remover(Autor ob) throws BancoException, ClassNotFoundException, SQLException{
        AutorDAO.excluir(ob);
    }

    public List<Autor> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  AutorDAO.listar();
    }
    
    public List<Autor> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  AutorDAO.buscarVarios(nome);
    }
}
