/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.EmprestimoDAO;
import biblioteca.model.livros.Emprestimo;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author Bianca
 */
public class EmprestimoController {
    public void cadastrar(int idEmprestimo, String dataEmprestimo, String  dataDevolucao, String livro, String usuario,boolean status) 
        throws BancoException, ClassNotFoundException, SQLException{
        EmprestimoDAO.inserir(new Emprestimo(idEmprestimo,dataEmprestimo,dataDevolucao,livro,usuario,status));
    }
    
    public void remover(Emprestimo ob) throws BancoException, ClassNotFoundException, SQLException{
        EmprestimoDAO.excluir(ob);
    }

    public List<Emprestimo> listar() throws BancoException, ClassNotFoundException, SQLException, ParseException{
      return  EmprestimoDAO.listar();
      
    }
    
    public List<Emprestimo> buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  EmprestimoDAO.buscarVarios(nome);
    }
}
