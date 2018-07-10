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
    private static String Driver = "org.postgresql.Driver";
    private static final String URL 
            = "jdbc:postgresql://localhost:5432/tds";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "batata";
    
    public static Connection getConnection() throws BancoException, ClassNotFoundException {
        if (conn == null) {
            try {
                Class.forName(Driver);
                conn = DriverManager.getConnection(URL, USUARIO, SENHA);
//                System.out.println("conectado com sucesso");
            } catch (SQLException ex) {
                throw new BancoException("Erro na conexão", ex);
            }
        }
        return conn;            
    }
    
}
