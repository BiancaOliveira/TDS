/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.usuarios.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public class UsuarioDAO {
    
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Usuario\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                id = res.getInt("idUsuario") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }

    public  static void inserir(Usuario ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        
        if(buscar(ob.getLogin()) != null){
            JOptionPane.showMessageDialog(null,"Este login ja existe");
        }else{
            ob.setIdUsuario(codigo());
            String sql = "INSERT INTO \"Usuario\" (\"idUsuario\",usuario,login,senha)"
                    + "VALUES(" + ob.getIdUsuario() + ",'" + ob.getNome() + "'" 
                    + ",'" + ob.getLogin() + "'" + ",'" + ob.getSenha() + "')";
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

    public static void excluir(Usuario ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " Delete  FROM \"Usuario\""
                + "WHERE usuario ='" + ob.getNome() + "'";
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
    public  static void alterar(Usuario ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
        
        String sql = "UPDATE INTO \"Usuario\" SET usuario'" + ob.getNome() 
                + "', senha = " + ob.getSenha() + 
                " WHERE \"idUsuario\"=" + ob.getIdUsuario();
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
    
    public static List<Usuario> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Usuario\"";
        
        
        List<Usuario> retorno = new ArrayList<Usuario>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                int id = res.getInt("idUsuario");
                String nome = res.getString("usuario");
                String login = res.getString("login");
                String senha = "**********";
                Usuario item = new Usuario(id,nome,login,senha);
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
    
    private static Usuario getInstance(ResultSet res)
        throws SQLException {
        int id = res.getInt("idUsuario");
        String nome = res.getString("usuario");
        String login = res.getString("login");
        String senha = "**********";
        Usuario item = new Usuario(id,nome,login,senha);
           
        return item;
    }
    
    public static Usuario buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE login='"+ nome + "'";   
        
        Usuario item = null;
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
