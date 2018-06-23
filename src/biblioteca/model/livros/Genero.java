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
public class Genero implements Comparable<Genero>{
    private int idGenero;
    private String nome;
    
    /**
     * Construtor do Generos
     * @param id Código do Genero (PK)
     * @param nome Nome do Genero
    */
    public Genero(int id, String nome) {
        this.idGenero = id;
        this.nome = nome;
    }

    public int getId() {
        return idGenero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Compara dois Genero considerando o nome deles
     * @param obj Genero que será comparado
     * @return true se os Genero tem o mesmo nome e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Genero u = (Genero) obj;
        return u.nome == this.nome; 
    }
    /**
     * Calcula o hascode do Genero considerando o nome (veja equals)
     * @return O hashcode do nome do Genero
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * nome.hashCode();
        return hash; 
    }
    
    @Override
    public int compareTo(Genero o) {
       if (nome != o.nome) return 1;
       else return 0;
    }

    @Override
    public String toString() {
        return  nome+ "\n";
    }
    
    
}
