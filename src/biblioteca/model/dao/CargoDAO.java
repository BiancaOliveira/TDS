/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.usuarios.Cargo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Bianca
 */
public class CargoDAO {
    
    public static boolean inserir(Cargo cargo) throws BancoException, ClassNotFoundException, SQLException{
        String sql ="INSERT INTO Cargo(idCargo,cargo)"
                + "VALUES(?,?)";
        boolean retorno = false;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            stmt.setInt(1, cargo.getIdCargo());
            stmt.setString(2, cargo.getNome());
            if(stmt.executeUpdate()==0){
                retorno = true;
            }
                
        }catch(SQLException ex){
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,  ex);
            retorno = false;
        }
        
        return retorno;
        
    }
    
    public List<Cargo> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql ="SELECT * FROM Cargo";
        
        List<Cargo> retorno = new ArrayList<Cargo>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Cargo item = new Cargo();
                item.setIdCargo(res.getInt("id"));
                item.setNome(res.getString("nome"));  
            }
                
        }catch(SQLException ex){
            Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,  ex);
        }
        
        return retorno;
    }
    
    
//    public C buscar(String nome) throws BancoException;
    
}
