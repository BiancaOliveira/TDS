/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public interface GenericDAO<T> {
    public boolean salvar (T t) throws BancoException;
    
    public boolean excluir (T t) throws BancoException;
    
    public Set<T> listarTodos() throws BancoException;
    
}
