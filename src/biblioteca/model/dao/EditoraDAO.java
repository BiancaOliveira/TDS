/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.livros.Editora;
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
public class EditoraDAO {
    /**
     * Busca o idEditora na tabela Editora
     * @return id pk da Editora que vai ser utilizado para salvar na tabela Editora
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT MAX(\"idEditora\") AS \"idEditora\" from \"Editora\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                id = res.getInt("idEditora") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }
   
    /**
     * Inclui a Editora na tabela Editora 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void inserir(Editora ob) throws BancoException, ClassNotFoundException, SQLException{ 
        if(buscar(ob.getNome()) != null){         
            JOptionPane.showMessageDialog(null,"Essa editora já possui cadastro");
        }else{
            ob.setIdEditora(codigo());
            String sql = "INSERT INTO \"Editora\" (\"idEditora\",editora)"
                    + "VALUES(" + ob.getIdEditora() + ",'" + ob.getNome() + "')";
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
                }
            }catch(SQLException ex){
//                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            }
        }
        
    }
    
    /**
     * Exclui a Editora na tabela Editora 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static void excluir(Editora ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " Delete  FROM \"Editora\""
                + "WHERE editora ='" + ob.getNome() + "'";
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
     * Altera o Editora na tabela Editora 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void alterar(Editora ob) throws BancoException, ClassNotFoundException, SQLException{
       if(buscar(ob.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Essa editora já possui cadastro");
        }else{
            String sql = "UPDATE  \"Editora\" SET editora = '" + ob.getNome() + "'"
                    + " WHERE \"idEditora\"=" + ob.getIdEditora();
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Alterado com sucesso");
                }
            }catch(SQLException ex){
    //                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao Alterar");
            }
       }
    }
   
    /**
     * Constrói um objeto Editora a partir de um ResultSet
     * @param res Result set contendo a linha que será usada
     * @return objeto 
     * @throws SQLException
     */
    private static Editora getInstance(ResultSet res)
        throws SQLException {
        int id = res.getInt("idEditora");
        String nome = res.getString("editora");
        Editora item = new Editora(id, nome);
           
        return item;
    }
    
    /**
     * Lista todas as editoras na tabela Editora 
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Editora> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Editora\"";   
        
        Editora item = null;
        List<Editora> retorno = new ArrayList<Editora>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                item = getInstance(res);
                retorno.add(item);
            }       
        }catch(SQLException ex){
             if(retorno == null){
                JOptionPane.showMessageDialog(null,"Nenhuma editora encontrada");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }
        }
        Collections.sort(retorno);
        return retorno;
    }
    
    /**
     * Busca por nome uma editora na tabela Editora 
     * @param nome nome da editora
     * @return um objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static Editora buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Editora\""
                    + " WHERE editora='"+ nome + "'";   
        
        Editora item = null;
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
     * Busca por id uma editora na tabela Editora 
     * @param id id do Editora
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static Editora buscarID(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Editora\""
                    + " WHERE \"idEditora\"="+ id ;   
        
        Editora item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                item = getInstance(res);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Editora não encontrada");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
    
    /**
     * Busca por nomw(parte) Editora na tabela Editora 
     * @param nome nome da editora(parte do nome)
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Editora> buscarVarios(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Editora\""
                    + " WHERE editora LIKE '%"+ nome + "%'";   
        
        Editora item = null;
        List<Editora> retorno = new ArrayList<Editora>();

        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                item = getInstance(res);
                retorno.add(item);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Editora não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        Collections.sort(retorno);
        return retorno;
    }
}
