/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.model.bd.BD;
import biblioteca.model.livros.Editora;
import java.util.Set;

/**
 *
 * @author gabriela
 */
public class EditoraController <C extends Editora>{
    public boolean cadastrarEditora(C editora){
       return BD.getBanco().addEditora(editora);    
    }
    
    public boolean removerEditora(String nome){
        C editora = buscarEditora(nome);
        return BD.getBanco().removerEditora(editora);
    }
   
    public  Set<Editora> listarEditora(){
        return BD.getBanco().listarEditora();
    }
    
    public C buscarEditora(String nome) {
        for (Editora editora : listarEditora()) {
            if (editora.getNome() == nome)
                return (C) editora;
        }
        return null;
    } 
}
