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
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Livros\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                id = res.getInt("idLivros") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        System.out.println(id);
        return id;
    }
    
    /**
     * Busca o idEditora na tabela Editora
     * @param nome
     * @return idEditora
     * @throws BancoException, ClassNotFoundException, SQLException
     */
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
            if(idEditora == 0){
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return idEditora;
    }
    /**
     * Busca o idAutor na tabela Autor
     * @param nome
     * @return idAutor
     * @throws BancoException, ClassNotFoundException, SQLException
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
            if(idAutor == 0){
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return idAutor;
    }
    /**
     * Busca o idGenero na tabela Genero
     * @param nome
     * @return idGenero
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public static int buscarIdGenero(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Genero\""
                    + " WHERE genero='"+ nome + "'";   
        
        int idGenero = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                 idGenero = res.getInt("idGenero");
            }
        }catch(SQLException ex){
            if(idGenero == 0){
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return idGenero;
    }
    /**
     * Inclui o Livro na tabela Livro 
     * @param ob
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public  static void inserir(Livro ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        
        if(buscar(ob.getTitulo()) != null){
            JOptionPane.showMessageDialog(null,"Este login ja existe");
        }else{
            ob.setIdLivro(codigo());
            
            int editora = buscarIdEditora(ob.editora);
            int autor = buscarIdAutor(ob.autor);
            int genero = buscarIdGenero(ob.genero);
            int coautor = buscarIdAutor(ob.coautores);
 
            
            String sql = "INSERT INTO \"Livros\" (\"idLivros\", titulo, id_editora, autor, \"numeroExemplares\","
                    + " id_genero, descricao, id_couator)"
                    + "VALUES(" + ob.getIdLivro() + ",'" + ob.getTitulo() + "'" 
                    + "," + editora  + "," + autor  + "," +ob.getNumeroExemplares()+","
                    + genero + ",'" + ob.getDescricao() + "'," + coautor+")";  
                    ;
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
                }

            }catch(SQLException ex){
                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            }
        }
    }
     /**
     * Exclui o Livro na tabela Livro 
     * @param ob
     * @throws BancoException, ClassNotFoundException, SQLException
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
                
                JOptionPane.showMessageDialog(null,"Erro ao remover");
            }
    }
     /**
     * Altera o Livro na tabela Livro 
     * @param ob
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public  static void alterar(Livro ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
        int editora = buscarIdEditora(ob.editora);
        int autor = buscarIdAutor(ob.autor);
        int genero = buscarIdGenero(ob.genero);
        int coautor = buscarIdAutor(ob.coautores);

        String sql = "UPDATE INTO \"Livros\" SET titulo'" + ob.getTitulo() 
                + "', id_editora = " + editora + ", autor" + autor 
                + ",\"numeroEcemplares\" =" + ob.getNumeroExemplares() 
                + ",genero =" +  genero + ",descricao = '" + ob.getDescricao()
                + "',id_couator=" + coautor
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
     /**
     * Lista os Usuario na tabela Usuario 
     * @return lista de objetos
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public static List<Livro> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Livros\"";
        
        
        List<Livro> retorno = new ArrayList<Livro>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Editora edi = EditoraDAO.buscarID(res.getInt("id_editora"));
                Genero gen = GeneroDAO.buscarID(res.getInt("id_genero"));
                Autor aut = AutorDAO.buscarID(res.getInt("autor"));
                Autor coa = AutorDAO.buscarID(res.getInt("id_couator"));
                int id = res.getInt("idLivros");
                String editora= edi.getNome();
                String genero= gen.getNome();
                String autor= aut.getNome();
                String coautor= coa.getNome();
                String titulo = res.getString("titulo");
                String descricao = res.getString("descricao");
                int numeroExemplares = res.getInt("numeroExemplares");
                Livro item = new Livro(id,titulo,numeroExemplares,descricao,autor,editora,genero,coautor);
                retorno.add(item);
            }
                
        }catch(SQLException ex){
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
             if(retorno == null){
                JOptionPane.showMessageDialog(null,"Nenhum item encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }
        }
        Collections.sort(retorno);
        return retorno;
    }
    /**
     * Constrói um objeto Livro a partir de um ResultSet
     * @param rs Result set contendo a linha que será usada
     * @return objeto 
     * @throws SQLException, BancoException, ClassNotFoundException 
     */
    private static Livro getInstance(ResultSet res)
        throws SQLException, BancoException, ClassNotFoundException {
        
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
     * Busca um Livro na tabela Livro 
     * @param nome login do usuario
     * @return um objetos
     * @throws BancoException, ClassNotFoundException, SQLException
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
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE,null,ex);
            if(item == null){
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
    
    /**
     * Busca um Usuario na tabela Usuario 
     * @param id id do cargo
     * @return um objetos
     * @throws BancoException, ClassNotFoundException, SQLException
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
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
    /**
     * Busca  por titulo Usuario na tabela Usuario 
     * @return lista de objetos
     * @throws BancoException, ClassNotFoundException, SQLException
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
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return retorno;
    }
    

}