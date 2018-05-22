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
public class Generos implements Comparable<Generos>{
    private int id;
    private String nome;
    
    /**
     * Construtor do Generos
     * @param id Código do Genero ---> PK
     * @param nome Nome do Genero
    */
    public Generos(int id, String nome) {
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

    /**
     * Compara dois Generos considerando o nome deles
     * @param obj Generos que será comparado
     * @return true se os Generos tem o mesmo nome e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Generos u = (Generos) obj;
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
    public int compareTo(Generos o) {
       if (nome != o.nome) return 1;
       else return 0;
    }

    @Override
    public String toString() {
        return "Gênero: " + nome+ "\n";
    }
    
    
}
