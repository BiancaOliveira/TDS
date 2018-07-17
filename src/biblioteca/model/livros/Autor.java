/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.livros;

/**
 *
 * @author Bianca
 */
public class Autor implements Comparable<Autor>{
    private  int idAutor;
    private String nome;
    
    /**
     * Construtor do Autor
     * @param id Código do Autor (PK)
     * @param nome Nome do Autor
    */

    public Autor(int id, String nome) {
        this.idAutor = id;
        this.nome = nome;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

     /**
     * Compara dois Autores considerando o nome deles
     * @param obj Autor que será comparado
     * @return true se os Autores tem o mesmo nome e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Autor u = (Autor) obj;
        return u.nome == this.nome; //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Calcula o hascode do Autor considerando o nome (veja equals)
     * @return O hashcode do nome do autor
     */
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * nome.hashCode();
        return hash; 
    }
    
    @Override
    public int compareTo(Autor o) {
        if (idAutor < o.idAutor) return -1;
        else if (idAutor > o.idAutor) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    
}
