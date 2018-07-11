/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import static biblioteca.model.dao.EditoraDAO.buscar;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.livros.Autor;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public class AutorDAO {
    /**
     * Busca o idAutor na tabela Autor
     * @return id pk do Auto que vai ser utilizado para salvar na tabela Autor
     * @throws BancoException, ClassNotFoundException, SQLException
     */   
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Autor\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                id = res.getInt("idAutor") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }

    /**
     * Inclui o Autor na tabela Autor 
     * @param ob
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public  static void inserir(Autor ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        
        if(buscar(ob.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Cadastro existente");
        }else{
            ob.setIdAutor(codigo());
            String sql = "INSERT INTO \"Autor\" (\"idAutor\",autor)"
                    + "VALUES(" + ob.getIdAutor() + ",'" + ob.getNome() + "')";
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
     * Exclui o Autor na tabela Autor 
     * @param ob
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public static void excluir(Autor ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " Delete  FROM \"Autor\""
                + "WHERE autor ='" + ob.getNome() + "'";
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
     * Altera o Autor na tabela Autor 
     * @param ob
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public  static void alterar(Autor ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
        
        if(buscar(ob.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Autor existente");
        }else{
            String sql = "UPDATE INTO \"Autor\" SET autor = '" + ob.getNome() + "'"
                    + " WHERE \"idAutor\"=" + ob.getIdAutor();
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
     * Lista o Autor na tabela Autor 
     * @retun lista de objetos
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    public static List<Autor> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Autor\"";
        
        
        List<Autor> retorno = new ArrayList<Autor>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                int id = res.getInt("idAutor");
                String nome = res.getString("autor");
                Autor item = new Autor(id,nome);
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
     * Constrói um objeto Autor a partir de um ResultSet
     * @param rs Result set contendo a linha que será usada
     * @return objeto 
     * @throws SQLException, BancoException, ClassNotFoundException 
     */
    
    private static Autor getInstance(ResultSet res)
        throws SQLException {
        int id = res.getInt("idAutor");
        String nome = res.getString("autor");
        Autor item = new Autor(id, nome);
           
        return item;
    }
    
    /**
     * busca um Autor na tabela Autor 
     * @retun um objetos
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    
    public static Autor buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Autor\""
                    + " WHERE autor='"+ nome + "'";   
        
        Autor item = null;
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
     * busca Autor na tabela Autor 
     * @retun lista de objetos
     * @throws BancoException, ClassNotFoundException, SQLException
     */
    
    public static List<Autor> buscarVarios(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Autor\""
                    + " WHERE autor LIKE '%"+ nome + "%'";   
        
        Autor item = null;
        List<Autor> retorno = new ArrayList<Autor>();

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
