/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
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
    
    /**
     * Busca o idUsuario na tabela Usuario
     * @return id pk do Usuario que vai ser utilizado para salvar na tabela Usuario
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT MAX(\"idUsuario\") AS \"idUsuario\" from \"Usuario\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                id = res.getInt("idUsuario") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }
    
     /**
     * Inclui o UsuarioAdm na tabela Adiministrador e o Usuario na tabela Usuario
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    
    public  static void inserir(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        if(buscar(ob.getLogin()) != null){
            JOptionPane.showMessageDialog(null,"Este usuário já esta sendo utilizado");
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
//                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            }
        }
    }
    
    /**
     * Exclui o UsuarioAdm na tabela Adiministrador e o Usuario na tabela Usuario
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    
    public static void excluir(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " DELETE  FROM \"Administrador\""
                + "WHERE \"id_usuario\" =" + ob.getIdUsuario() +";"
                + " DELETE  FROM \"Usuario\""
                + "WHERE \"idUsuario\" =" + ob.getIdUsuario();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Removido com sucesso");
                }

            }catch(SQLException ex){
//                Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE,null,ex);
                
                JOptionPane.showMessageDialog(null,"Erro ao remover");
            }
    }
    /**
     * Busca o idCargo na tabela Cargo
     * @param nome nome do cargo
     * @return idCargo
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     
     */
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
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
        }
        return idCargo;
    }
    
    /**
     * Altera o UsuarioAdm na tabela Adiministrador e o Usuario na tabela Usuario
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    
    public  static void alterar(UsuarioAdm ob) throws BancoException, ClassNotFoundException, SQLException{

        int idCargo = buscarIdCargo(ob.cargo);
        String sql = "UPDATE \"Usuario\" SET usuario'" + ob.getNome() 
                + "', senha = " + ob.getSenha() 
                + " WHERE \"idUsuario\"=" + ob.getIdUsuario() + ";"
                +"UPDATE INTO \"Administrador\" SET id_cargo= " + idCargo + ""
                + " WHERE id_Usuario =" + ob.getIdUsuario();
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
    
    /**
     * Lista o UsuarioAdm na tabela Adiministrador atravez do usuario(login) na tabela Usuario e do cargo na tabela Cargo
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    
    public static List<UsuarioAdm> listarAdm() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Administrador\"";
        
        List<UsuarioAdm> retorno = new ArrayList<UsuarioAdm>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){

                Cargo cargo = CargoDAO.buscarID(res.getInt("id_cargo"));
                Usuario usu = UsuarioDAO.buscarID(res.getInt("id_usuario"));
                int idUsuario = usu.getIdUsuario();
                String nome = usu.getNome();
                String login = usu.getLogin();
                String senha = "**********";
                String nomeCargo = cargo.getNome();
                UsuarioAdm item = new UsuarioAdm(idUsuario,nome,login,senha,nomeCargo);
                retorno.add(item);
            }
                
        }catch(SQLException ex){
             if(retorno == null){
                JOptionPane.showMessageDialog(null,"Nenhum usuário encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }
        }
        Collections.sort(retorno);
        return retorno;
    }
    
    /**
     * Constrói um objeto UsuarioAdm a partir de um idLogin
     * @param idLogin id do Usuario na tabela Usuario
     * @return UsuarioAdm 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
     private static UsuarioAdm getInstance(int idLogin)
        throws SQLException, BancoException, ClassNotFoundException {
        String sql = "SELECT * FROM \"Administrador\""
                    + " WHERE id_usuario="+ idLogin ;   
        UsuarioAdm item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                Cargo cargo = CargoDAO.buscarID(res.getInt("id_cargo"));
                Usuario usu = UsuarioDAO.buscarID(res.getInt("id_usuario"));
                int idUsuario = usu.getIdUsuario();
                String nome = usu.getNome();
                String login = usu.getLogin();
                String senha = "**********";
                String nomeCargo = cargo.getNome();
                item = new UsuarioAdm(idUsuario,nome,login,senha,nomeCargo);
            }

        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
//                Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
   
    /**
     * Busca um UsuarioAdm na tabela Adiministrador atravez do Usuario na tabela Usuario e do cargo na tabela Cargo
     * @param nome nome do login do usuario
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static UsuarioAdm buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE login='"+ nome + "'";   
        
        UsuarioAdm item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                int id = res.getInt("idUsuario");
//                System.out.println(id);
                item = getInstance(id);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
//                Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
    /**
     * Busca um UsuarioaAdm na tabela Administrado 
     * @param id id do usuario
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static UsuarioAdm buscarID(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE \"idUsuario\"="+ id ;   
        
        UsuarioAdm item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                int idUsuario = res.getInt("idUsuario");
//                System.out.println(id);
                item = getInstance(idUsuario);            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
     /**
     * Busca varios UsuarioAdm na tabela Adiministrador atravez do Usuario na tabela Usuario e do cargo na tabela Cargo
     * @param nome nome do usuario
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    
    public static List<UsuarioAdm> buscarVarios(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE usuario LIKE '%"+ nome + "%'";   
        
        UsuarioAdm item = null;
        List<UsuarioAdm> retorno = new ArrayList<UsuarioAdm>();

        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("idUsuario");
                item = getInstance(id);
                if(item != null){
                    retorno.add(item);
                }
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return retorno;
    }
}
