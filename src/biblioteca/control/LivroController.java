/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.livros.Livro;
import java.util.Set;

/**
 *
 * @author gabriela
 */
public class LivroController <C extends Livro>{
    public boolean cadastrarLivro(C livro){
       return BD.getBanco().addLivro(livro);    
    }
    
    public boolean removerLivro(String titulo){
        C livros = buscarLivro(titulo);
        return BD.getBanco().removerLivro(livros);
    }
   
    public  Set<Livro> listarLivros(){
        return BD.getBanco().listarLivros();
    }
    
    public C buscarLivro(String titulo) {
        for (Livro livro : listarLivros()) {
            if (livro.getTitulo() == titulo)
                return (C) livro;
        }
        return null;
    }
}
