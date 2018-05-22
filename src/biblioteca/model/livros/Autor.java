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
    private int id;
    private String nome;

    public Autor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Autor u = (Autor) obj;
        return u.nome == this.nome; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * nome.hashCode();
        return hash; //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int compareTo(Autor o) {
       if (nome != o.nome) return -1;
       else return 0;
    }

    @Override
    public String toString() {
        return "\nAutor: " + nome;
    }
    
    
}