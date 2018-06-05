/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.livros;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bianca
 */
public class Livro implements Comparable<Livro>{
    private int id;
    private String titulo;
    private int numeroExemplares;
    private String descricao;
    private Autor autor;
    private List<Autor> coautores;
    private Editora editora;
    private Genero genero;
        

    public int getId() {
        return id;
    }

    public Livro() {
    }
    
    /**
     * Construtor do Livros
     * @param id Código do Livros (PK)
     * @param titulo Titulo do livro 
     * @param numeroExemplares Numero de Exemplares
     * @param descricao Descrição do livro
     * @param autor Autor do livro
     * @param editora Editora do livro
     * @param genero Genero do livro
    */
    
    public Livro(int id, String titulo, int numeroExemplares, String descricao, Autor autor, Editora editora, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.numeroExemplares = numeroExemplares;
        this.descricao = descricao;
        this.autor = autor;
        this.editora = editora;
        this.genero = genero;
        this.coautores= new ArrayList<>();
    }
    
    

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroExemplares() {
        return numeroExemplares;
    }

    public void setNumeroExemplares(int numeroExemplares) {
        this.numeroExemplares = numeroExemplares;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Autor> getCoautores() {
        return coautores;
    }

    public void setCoautores(List<Autor> coautores) {
        this.coautores = coautores;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
    
    /**
     * Compara dois Livro considerando o titulo delas
     * @param obj Livro que será comparado
     * @return true se os Livro tem o mesmo titulo e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Livro u = (Livro) obj;
        return u.titulo == this.titulo; 
    }
    
    /**
     * Calcula o hascode do Livro considerando o titulo (veja equals)
     * @return O hashcode do titulo do livro
     */
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * titulo.hashCode();
        return hash; 
    }
    
    @Override
    public int compareTo(Livro o) {
       if (titulo != o.titulo) return -1;
       else return 0;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + "\n" ;
    }
       
}
