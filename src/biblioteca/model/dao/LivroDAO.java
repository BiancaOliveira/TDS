/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.livros.Autor;
import biblioteca.model.livros.Editora;
import biblioteca.model.livros.Genero;
import biblioteca.model.livros.Livro;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public class LivroDAO {
    /**
     * Busca o idLivro na tabela Livro
     * @return id pk do Livro que vai ser utilizado para salvar na tabela Livro
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT MAX(\"idLivros\") AS \"idLivros\" from \"Livros\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                id = res.getInt("idLivros") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }
    
    /**
     * Busca o idEditora na tabela Editora
     * @param nome nome da editora
     * @return idEditora
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    public static int buscarIdEditora(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Editora\""
                    + " WHERE editora='"+ nome + "'";   
        
        int idEditora = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                 idEditora = res.getInt("idEditora");
            }
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
        }
        return idEditora;
    }
  
    /**
     * Busca o idAutor na tabela Autor
     * @param nome nome da autor
     * @return idAutor
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int buscarIdAutor(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Autor\""
                    + " WHERE autor='"+ nome + "'";   
        
        int idAutor = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                 idAutor = res.getInt("idAutor");
            }
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Erro ao buscar");  
        }
        return idAutor;
    }
    
    /**
     * Busca o idGenero na tabela Genero
     * @param nome nome da genero
     * @return idGenero
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int buscarIdGenero(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Genero\""
                    + " WHERE genero='"+ nome + "'";   
        
        int idGenero = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                 idGenero = res.getInt("idGeneros");
            }
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
//                Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return idGenero;
    }
    
    /**
     * Inclui o Livro na tabela Livros 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void inserir(Livro ob) throws BancoException, ClassNotFoundException, SQLException{  
        if(buscar(ob.getTitulo()) != null){
            JOptionPane.showMessageDialog(null,"Esse livro já possui cadastro");
        }else{
            ob.setIdLivro(codigo());      
            int editora = buscarIdEditora(ob.getEditora());
            int autor = buscarIdAutor(ob.getAutor());
            int genero = buscarIdGenero(ob.getGenero());
            int coautor = buscarIdAutor(ob.getCoautores());
            
            String sql = "INSERT INTO \"Livros\" (\"idLivros\", titulo, id_editora, autor, \"numeroExemplares\","
                    + " id_genero, descricao, id_couator)"
                    + "VALUES(" + ob.getIdLivro() + ",'" + ob.getTitulo() + "'" 
                    + "," + editora  + "," + autor  + "," +ob.getNumeroExemplares()+","
                    + genero + ",'" + ob.getDescricao() + "'," + coautor+")";  
                    
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
                }
            }catch(SQLException ex){
//                Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            }
        }
    }
  
    /**
     * Exclui o Livro na tabela Livros 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static void excluir(Livro ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " Delete  FROM \"Livros\""
                + "WHERE titulo ='" + ob.getTitulo() + "'";
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Removido com sucesso");
                }
            }catch(SQLException ex){
//                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao excluir, livro associado a um emprestimo");
            }
    }
   
    /**
     * Altera o Livro na tabela Livros 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void alterar(Livro ob) throws BancoException, ClassNotFoundException, SQLException{
        int editora = buscarIdEditora(ob.getEditora());
        int autor = buscarIdAutor(ob.getAutor());
        int genero = buscarIdGenero(ob.getGenero());
        int coautor = buscarIdAutor(ob.getCoautores());
        
        Livro liv = buscar(ob.getTitulo());
        
        if((liv != null) && (liv.getIdLivro() != ob.getIdLivro())){
            JOptionPane.showMessageDialog(null,"Esse livro já possui cadastro");
        }else{
            String sql = "UPDATE \"Livros\" SET titulo='" + ob.getTitulo() 
                    + "', id_editora = " + editora + ", autor =" + autor 
                    + ",\"numeroExemplares\" = " + ob.getNumeroExemplares() 
                    + ",id_genero = " +  genero + ",descricao = '" + ob.getDescricao()
                    + "',id_couator = " + coautor
                    + " WHERE \"idLivros\"='" + ob.getIdLivro() + "'";

            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Alterado com sucesso");
                }
            }catch(SQLException ex){
                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao Alterar");
            }
        }
    }
    
    /**
     * Constrói um objeto Livro a partir de um ResultSet
     * @param rs Result set contendo a linha que será usada
     * @return objeto 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    private static Livro getInstance(ResultSet res) throws  BancoException, ClassNotFoundException, SQLException {
        Editora edi = EditoraDAO.buscarID(res.getInt("id_editora"));
        Genero gen = GeneroDAO.buscarID(res.getInt("id_genero"));
        Autor aut = AutorDAO.buscarID(res.getInt("autor"));
        Autor coa = AutorDAO.buscarID(res.getInt("id_couator"));
        int id = res.getInt("idLivros");
        String editora= edi.getNome();
        String autor= aut.getNome();
        String coautor= coa.getNome();
        String titulo = res.getString("titulo");
        String genero= gen.getNome();
        String descricao = res.getString("descricao");
        int numeroExemplares = res.getInt("numeroExemplares");
        Livro item = new Livro(id,titulo,numeroExemplares,descricao,autor,editora,genero,coautor);
        
        return item;
    }
    
    /**
     * Lista todos os livros na tabela Livros 
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Livro> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Livros\"";
  
        Livro item = null;
        List<Livro> retorno = new ArrayList<Livro>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                item = getInstance(res);
                retorno.add(item);
            }
        }catch(SQLException ex){
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
             if(retorno == null){
                JOptionPane.showMessageDialog(null,"Nenhum livro encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }
        }
        Collections.sort(retorno);
        return retorno;
    }
    
    /**
     * Busca por titulo um livro na tabela Livros 
     * @param nome titulo do livro
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static Livro buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Livros\""
                    + " WHERE titulo='"+ nome + "'";   
        
        Livro item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                item = getInstance(res);
            }
        }catch(SQLException ex){
                JOptionPane.showMessageDialog(null,"Erro ao buscar");  
        }
        return item;
    }
    
    /**
     * Busca po id um livro na tabela Livros
     * @param id id do Livro
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static Livro buscarID(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Livros\""
                    + " WHERE \"idLivros\"='"+ id + "'";   
        
        Livro item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                item = getInstance(res);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Livro não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
    
    /**
     * Busca por titulo(parte) um livro na tabela Livros
     * @param nome nome do livro
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Livro> buscarVariosTitulo(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Livros\""
                    + " WHERE titulo LIKE '%"+ nome + "%'";   
        
        Livro item = null;
        List<Livro> retorno = new ArrayList<Livro>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                item = getInstance(res);
                retorno.add(item);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Livro não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        Collections.sort(retorno);
        return retorno;
    }
    

}
