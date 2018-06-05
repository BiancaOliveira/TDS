/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.livros.Genero;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class GeneroController <C extends Genero>{
    
    public boolean cadastrarGenero(C genero){
       return BD.getBanco().addGenero(genero);    
    }
    
    public boolean removerGenero(String nome){
        C genero = buscarGenero(nome);
        return BD.getBanco().removerGenero(genero);
    }
   
    public  Set<Genero> listarGeneros(){
        return BD.getBanco().listarGeneros();
    }
    
    public C buscarGenero(String nome) {
        for (Genero genero : listarGeneros()) {
            if (genero.getNome() == nome)
                return (C) genero;
        }
        return null;
    } 
    
}
