/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.usuarios.Cargo;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class CargoController <C extends Cargo> {
    
    public boolean cadastrarCargo(C cargo){
       return BD.getBanco().addCargo(cargo);    
    }
    
    public boolean removerCargo(String nome){
        C cargo = buscarCargo(nome);
        return BD.getBanco().removerCargo(cargo);
    }
   
    public  Set<Cargo> listarCargos(){
        return BD.getBanco().listarCargos();
    }
    
    public C buscarCargo(String nome) {
        for (Cargo cargo : listarCargos()) {
            if (cargo.getNome() == nome)
                return (C) cargo;
        }
        return null;
    } 
    
}
