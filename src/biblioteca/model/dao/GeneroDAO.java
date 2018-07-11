/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.livros.Genero;
import java.sql.*;
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
public class GeneroDAO {
    /**
     * Busca o idGenero na tabela Genero
     * @return id pk do Genero que vai ser utilizado para salvar na tabela Genero
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Genero\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                id = res.getInt("idGeneros") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }
    /**
     * Inclui o Genero na tabela Genero 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void inserir(Genero ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        
        if(buscar(ob.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Cadastro existente");
        }else{
            ob.setIdGenero(codigo());
            String sql = "INSERT INTO \"Genero\" (\"idGeneros\", genero)"
                    + "VALUES(" + ob.getIdGenero() + ",'" + ob.getNome() + "')";
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
     * Exclui o Genero na tabela Genero 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static void excluir(Genero ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " Delete  FROM \"Genero\""
                + "WHERE genero ='" + ob.getNome() + "'";
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
     * Altera o Genero na tabela Genero 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void alterar(Genero ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
        if(buscar(ob.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Genero existente");
        }else{
            String sql = "UPDATE INTO \"Genero\" SET genero= '" + ob.getNome() + "'"
                    + " WHERE \"idGeneros\"=" + ob.getIdGenero();
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Alterado com sucesso");
                }

            }catch(SQLException ex){
    //               Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao Alterar");
            }
        }
    }
    /**
     * Lista os Genero na tabela Genero 
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Genero> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Genero\"";
        
        
        List<Genero> retorno = new ArrayList<Genero>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                int id = res.getInt("idGeneros");
                String cargo = res.getString("genero");
                Genero item = new Genero(id,cargo);
//                item.setIdCargo(res.getInt("idCargo"));
//                item.setNome(res.getString("nome"));
                retorno.add(item);
            }
                
        }catch(SQLException ex){
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
     * Constrói um objeto Genero a partir de um ResultSet
     * @param rs Result set contendo a linha que será usada
     * @return objeto 
     * @throws java.sql.SQLException    Exeções Sql

     */
    private static Genero getInstance(ResultSet res)
        throws SQLException {
        int id = res.getInt("idGeneros");
        String nome = res.getString("genero");
        Genero item = new Genero(id, nome);
           
        return item;
    }
    /**
     * Busca um Genero na tabela Genero 
     * @param nome nome do genero
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql

     */
    public static Genero buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Genero\""
                    + " WHERE genero LIKE '"+ nome + "%'";   
        
        Genero item = null;
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
     * Busca um Genero na tabela Genero 
     * @param id id do Genero
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static Genero buscarID(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Genero\""
                    + " WHERE \"idGeneros\"="+ id ;   
        
        Genero item = null;
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
     * Busca  Genero na tabela Genero 
     * @param nome nome do genero
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Genero> buscarVarios(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Genero\""
                    + " WHERE genero LIKE '%"+ nome + "%'";   
        
        Genero item = null;
        List<Genero> retorno = new ArrayList<Genero>();

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
