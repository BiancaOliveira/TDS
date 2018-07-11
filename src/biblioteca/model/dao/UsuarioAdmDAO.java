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
import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public class UsuarioAdmDAO extends UsuarioDAO{
    
    public  static void inserir(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        if(buscar(ob.getLogin()) != null){
            JOptionPane.showMessageDialog(null,"Este login ja existe");
        }else{
            ob.setIdUsuario(codigo());
            String sql = "INSERT INTO \"Usuario\" (\"idUsuario\",usuario,login,senha)"
                    + "VALUES(" + ob.getIdUsuario() + ",'" + ob.getNome() + "'" 
                    + ",'" + ob.getLogin() + "'" + ",'" + ob.getSenha() + "')";
            
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
          
            int idCargo = buscarIdCargo(ob.cargo);
                    
            sql = "INSERT INTO \"Administrador\" (id_Usuario,id_Cargo)"
                    + "VALUES(" + ob.getIdUsuario() + ",'" + idCargo + "')";
            
            PostgreDAO.getConnection().prepareStatement(sql);
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
    
    public static void excluir(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
        
        String sql = " Delete  FROM \"Usuario\""
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
        
        sql = "UPDATE INTO \"Cargo\" SET cargo= '" + ob.getNome() + "'"
                + " WHERE \"idCargo\"=" + idCargo;
        
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
    
    public  List<UsuarioAdm> listarAdm() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Administrador\"";
        
        List<UsuarioAdm> retorno = new ArrayList<UsuarioAdm>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                int idUsuario = res.getInt("id_usuario");
                int idCargo = res.getInt("id_cargo");
                sql = "SELECT * FROM \"Usuario\""
                    + " WHERE \"idUsuadio\"="+ idUsuario + ""; 
                PostgreDAO.getConnection().prepareStatement(sql);
                String nome = res.getString("usuario");
                String login = res.getString("login");
                String senha = "**********";
                
                sql = "SELECT * FROM \"Cargo\""
                    + " WHERE \"idCargo\"="+ idCargo + ""; 
                PostgreDAO.getConnection().prepareStatement(sql);
                String cargo = res.getString("cargo");;
                UsuarioAdm item = new UsuarioAdm(idUsuario,nome,login,senha,cargo);
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
}
