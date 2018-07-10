/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;
import biblioteca.exception.BancoException;
import biblioteca.model.bd.BD;
import biblioteca.model.dao.GeneroDAO;
import biblioteca.model.livros.Genero;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class GeneroController{
    
    public void cadastrar(int id, String nome) throws BancoException, ClassNotFoundException, SQLException{
       GeneroDAO.inserir(new Genero(id,nome));
    }
    
    public void remover(Genero genero) throws BancoException, ClassNotFoundException, SQLException{
        GeneroDAO.excluir(genero);
    }

    public List<Genero> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  GeneroDAO.listar();
      
    }
    
    public Genero buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  GeneroDAO.buscar(nome);
    } 
    
}
