/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.LivroDAO;
import biblioteca.model.livros.Livro;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author gabriela
 */
public class LivroController {
     public void cadastrar(int id, String titulo, int numeroExemplares, String descricao, String autor, String editora, String genero, String coautor)
            throws  SQLException, BancoException, ClassNotFoundException{
       LivroDAO.inserir(new Livro(id,titulo,numeroExemplares,descricao,autor,editora,genero,coautor));
    }
    
    public void remover(Livro ob) throws BancoException, ClassNotFoundException, SQLException{
        LivroDAO.excluir(ob);
    }

    public List<Livro> listar() throws BancoException, ClassNotFoundException, SQLException{
      return  LivroDAO.listar();
      
    }
    
    public List<Livro> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  LivroDAO.buscarVariosTitulo(nome);
    }
}
