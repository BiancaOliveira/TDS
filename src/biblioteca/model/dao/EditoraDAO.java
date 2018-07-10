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
import java.util.logging.Logger;
import javax.swing.JOptionPane;



/**
 *
 * @author Bianca
 */
public class EditoraDAO {
    
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Autor\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                id = res.getInt("idEditora") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }

    public  static void inserir(Editora ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();
        
        
        if(buscar(ob.getNome()) != null){
            JOptionPane.showMessageDialog(null,"Cadastro existente");
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
    public  static void alterar(Editora ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
       
        ob.setIdEditora(codigo());
        String sql = "UPDATE INTO \"Editora\" SET editora'" + ob.getNome() + "'"
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
    
    public static List<Editora> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Editora\"";
        
        
        List<Editora> retorno = new ArrayList<Editora>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                int id = res.getInt("idEditora");
                String nome = res.getString("editora");
                Editora item = new Editora(id,nome);
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
    
    private static Editora getInstance(ResultSet res)
        throws SQLException {
        int id = res.getInt("idEditora");
        String nome = res.getString("editora");
        Editora item = new Editora(id, nome);
           
        return item;
    }
    
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
            if(item == null){
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
}
