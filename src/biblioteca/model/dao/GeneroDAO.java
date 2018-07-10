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

    public  static void inserir(Genero genero) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        
        if(buscar(genero.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Cadastro existente");
        }else{
            genero.setIdGenero(codigo());
            String sql = "INSERT INTO \"Genero\" (\"idGeneros\", genero)"
                    + "VALUES(" + genero.getIdGenero() + ",'" + genero.getNome() + "')";
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

    public static void excluir(Genero genero) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " Delete  FROM \"Genero\""
                + "WHERE genero ='" + genero.getNome() + "'";
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
    public  static void alterar(Genero genero) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
        if(buscar(genero.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Cadastro existente");
        }else{
            genero.setIdGenero(codigo());
            String sql = "UPDATE INTO \"Genero\" SET genero'" + genero.getNome() + "'"
                    + " WHERE \"idGeneros\"=" + genero.getIdGenero();
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
    
    private static Genero getInstance(ResultSet res)
        throws SQLException {
        int id = res.getInt("idGeneros");
        String nome = res.getString("genero");
        Genero item = new Genero(id, nome);
           
        return item;
    }
    
    public static Genero buscar(String genero) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Genero\""
                    + " WHERE genero='"+ genero + "'";   
        
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
}
