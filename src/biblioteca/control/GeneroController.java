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
    
    public void remover(Genero ob) throws BancoException, ClassNotFoundException, SQLException{
        GeneroDAO.excluir(ob);
    }
    
    public void altera(Genero ob) throws BancoException, ClassNotFoundException, SQLException{
        GeneroDAO.alterar(ob);
    }

    public List<Genero> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  GeneroDAO.listar();
      
    }
    
    public List<Genero> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  GeneroDAO.buscarVarios(nome);
    }

    public Genero buscarUnico(String nome) throws BancoException, SQLException, ClassNotFoundException {
        return GeneroDAO.buscar(nome);
    }
    
}
