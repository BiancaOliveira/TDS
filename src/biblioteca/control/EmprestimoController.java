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
import javax.swing.JOptionPane;

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
    
    public void altera(Emprestimo ob) throws BancoException, ClassNotFoundException, SQLException{
        EmprestimoDAO.alterar(ob);
    }
    
    public List<Emprestimo> buscarLivro(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  EmprestimoDAO.buscarVarios(nome);
    }
    
    public List<Emprestimo> buscarStatus(boolean status) throws BancoException, ClassNotFoundException, SQLException {
        return  EmprestimoDAO.buscarStatus(status);
    }

    public void atualiza(int id) throws BancoException, SQLException, ClassNotFoundException {
        boolean x = EmprestimoDAO.atualizaTabela(id, false);
        if(x == true){
            JOptionPane.showMessageDialog(null,"Devolução concluída");
        }else{
            JOptionPane.showMessageDialog(null,"Erro ao executar a devolução");
        }
    }
}
