/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.MultasDAO;
import biblioteca.model.livros.Multas;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Bianca
 */
public class MultasController {
    public void alteraTaxa(double taxa) throws BancoException, ClassNotFoundException, SQLException{
        MultasDAO.alterarTaxa(taxa);
    }
    
    public void alteraStatus(int id) throws BancoException, ClassNotFoundException, SQLException{
        MultasDAO.alterarStatus(id);
    }
   
    public List<Multas> listar() throws BancoException, ClassNotFoundException, SQLException {
        return  MultasDAO.listar();
    }
    
    public List<Multas> buscarStatus(boolean status) throws BancoException, ClassNotFoundException, SQLException {
        return  MultasDAO.buscarStatus(status);
    }
    
    public Multas buscarUsuario(String usuario) throws BancoException, ClassNotFoundException, SQLException {
        return  MultasDAO.buscarUuario(usuario);
    }
    
}
