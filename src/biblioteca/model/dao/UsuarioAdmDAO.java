/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import static biblioteca.model.dao.UsuarioDAO.buscar;
import static biblioteca.model.dao.UsuarioDAO.codigo;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.usuarios.Cargo;
import biblioteca.model.usuarios.Usuario;
import biblioteca.model.usuarios.UsuarioAdm;
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
public class UsuarioAdmDAO {
    
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
    
    public  static void inserir(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        if(buscar(ob.getLogin()) != null){
            JOptionPane.showMessageDialog(null,"Este login ja existe");
        }else{
            ob.setIdUsuario(codigo());
            int idCargo = buscarIdCargo(ob.cargo);
            String sql = "INSERT INTO \"Usuario\" (\"idUsuario\",usuario,login,senha)"
                    + "VALUES(" + ob.getIdUsuario() + ",'" + ob.getNome() + "'" 
                    + ",'" + ob.getLogin() + "'" + ",'" + ob.getSenha() + "');"
                    + "INSERT INTO \"Administrador\" (id_Usuario,id_Cargo)"
                    + "VALUES(" + ob.getIdUsuario() + ",'" + idCargo + "')";
            
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
    
    public static void excluir(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " DELETE  FROM \"Administrador\""
                + "WHERE \"idUsuario\" =" + ob.getIdUsuario() +";"
                + " DELETE  FROM \"Usuario\""
                + "WHERE \"idUsuario\" =" + ob.getIdUsuario();
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
    
    public static int buscarIdCargo(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Cargo\""
                    + " WHERE cargo='"+ nome + "'";   
        
        int idCargo = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                 idCargo = res.getInt("idCargo");
            }
        }catch(SQLException ex){
            if(idCargo == 0){
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return idCargo;
    }
    
    public  static void alterar(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
        String sql = "UPDATE INTO \"Usuario\" SET usuario'" + ob.getNome() 
                + "', senha = " + ob.getSenha() + 
                " WHERE \"idUsuario\"=" + ob.getIdUsuario();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);

        int idCargo = buscarIdCargo(ob.cargo);
      
        sql = "UPDATE INTO \"Administrador\" SET id_cargo= " + idCargo + ""
                + " WHERE id_Usuario =" + ob.getIdUsuario();
        
       PostgreDAO.getConnection().prepareStatement(sql);
        try{
            if (stmt.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            }

        }catch(SQLException ex){
//                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null,"Erro ao Alterar");
        }
    }
    
    public static List<UsuarioAdm> listarAdm() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Administrador\"";
        
        List<UsuarioAdm> retorno = new ArrayList<UsuarioAdm>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                UsuarioAdm item = getInstance(res);
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
    
     private static UsuarioAdm getInstance(ResultSet res)
        throws SQLException, BancoException, ClassNotFoundException {
        
//        int idCargo = res.getInt("id_cargo");
        int idUsuario = res.getInt("idUsuario");
        String nome = res.getString("usuario");
        String login = res.getString("login");
        String senha = "**********";
        String sql = "SELECT * FROM \"Adiministrador\""
                    + " WHERE id_usuario='"+ idUsuario + "'";
        
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        ResultSet y = stmt.executeQuery();
        int idCargo = y.getInt("idCargo");
        
        sql = "SELECT * FROM \"Cargo\""
                    + " WHERE =\"idCargo\" ="+ idCargo ;
        
        stmt = PostgreDAO.getConnection().prepareStatement(sql);
        ResultSet x = stmt.executeQuery();
        
        String cargo = x.getString("cargo");
        UsuarioAdm item = new UsuarioAdm(idUsuario,nome,login,senha,cargo);
           
        return item;
    }
    
    public static UsuarioAdm buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE login='"+ nome + "'";   
        
        UsuarioAdm item = null;
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
                Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
    
    public static List<UsuarioAdm> buscarVariosAdm(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE usuario='"+ nome + "'";   
        
        UsuarioAdm item = null;
        List<UsuarioAdm> retorno = new ArrayList<UsuarioAdm>();

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
