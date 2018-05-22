/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.livros.Livros;
import java.util.Set;

/**
 *
 * @author gabriela
 */
public class LivrosController <C extends Livros>{
    public boolean cadastrarLivro(C livro){
       return BD.getBanco().addLivro(livro);    
    }
    
    public boolean removerLivro(String titulo){
        C livros = buscarLivro(titulo);
        return BD.getBanco().removerLivro(livros);
    }
   
    public  Set<Livros> listarLivros(){
        return BD.getBanco().listarLivros();
    }
    
    public C buscarLivro(String titulo) {
        for (Livros livro : listarLivros()) {
            if (livro.getTitulo() == titulo)
                return (C) livro;
        }
        return null;
    }
}
