/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.livros.Autor;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class AutorController <C extends Autor>{
    public boolean cadastrarAutor(C autor){
       return BD.getBanco().addAutor(autor);    
    }
    
    public boolean removerAutor(String nome){
        C autor = buscarAutor(nome);
        return BD.getBanco().removerAutor(autor);
    }
   
    public  Set<Autor> listarAutores(){
        return BD.getBanco().listarAutores();
    }
    
    public C buscarAutor(String nome) {
        for (Autor autor : listarAutores()) {
            if (autor.getNome() == nome)
                return (C) autor;
        }
        return null;
    } 
}
