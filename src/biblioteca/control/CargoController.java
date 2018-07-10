/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.CargoDAO;
import biblioteca.model.usuarios.Cargo;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class CargoController{
    
    public void cadastrarCargo(int id, String nome) throws BancoException, ClassNotFoundException, SQLException{
       CargoDAO.inserir(new Cargo(id,nome));
    }
    
    public void removerCargo(Cargo ob) throws BancoException, ClassNotFoundException, SQLException{
        CargoDAO.excluir(ob);
    }
//    
//    public boolean removerCargo(String nome){
//        C cargo = buscarCargo(nome);
//        return BD.getBanco().removerCargo(cargo);
//    }
//   
    public List<Cargo> listarCargos() throws BancoException, ClassNotFoundException, SQLException{
      return  CargoDAO.listar();
      
    }
    
    public Cargo buscarCargo(String nome) throws BancoException, ClassNotFoundException, SQLException {
        return  CargoDAO.buscar(nome);
    } 
    
}
