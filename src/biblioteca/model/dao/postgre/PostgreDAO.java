/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao.postgre;

import biblioteca.exception.BancoException;
import java.sql.*;

import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public abstract class PostgreDAO {
    private static Connection conn;
    private static final String URL 
            = "jdbc:postgresql://localhost:5432/";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "batata";
    
    public static Connection conectaBD() throws BancoException {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(URL, USUARIO, SENHA);
                JOptionPane.showMessageDialog(null, "conectado com sucesso");
            } catch (SQLException ex) {
                throw new BancoException(ex);
            }
        }
        return conn;            
    }
    
}
