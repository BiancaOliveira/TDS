/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.usuarios.Cargo;

/**
 *
 * @author Bianca
 */
public interface CargoDAO <C extends Cargo> extends GenericDAO<C>{
    public C buscar(int id) throws BancoException;    
}
