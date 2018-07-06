/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao.postgre;

import biblioteca.model.dao.CargoDAO;
import biblioteca.model.dao.DAOFactory;

/**
 *
 * @author Bianca
 */
public class PostgreDAOFactory extends DAOFactory {
    private static CargoDAO cargoDAO;
    
    @Override
    public CargoDAO getCargoDAO(){
        if(cargoDAO == null)
           cargoDAO = new PostgreCargoDAO();
        return cargoDAO;
    }  
    
}
