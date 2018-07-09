/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.model.dao.postgre.PostgreDAOFactory;

/**
 *
 * @author Bianca
 */
public abstract class DAOFactory {
    public static final int POSTGRE = 1;
    
    public abstract CargoDAO getCargoDAO();
   
    public static DAOFactory getDAOFactory(int op){
        switch (op) {
            case POSTGRE : return new PostgreDAOFactory();
            default: return null;
        }
    }
    
    public static DAOFactory getDefaultDAOFactory(){
        return getDAOFactory(POSTGRE);
    }
}
