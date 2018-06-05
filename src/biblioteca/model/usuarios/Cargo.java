/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.usuarios;

/**
 *
 * @author Bianca
 */
public class Cargo implements Comparable<Cargo>{
    private int id;
    private String nome;

    /**
     * Construtor do Usuario
     * @param id Código do Cargo (PK)
     * @param nome Nome do Cargo
    */
    public Cargo(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String cargo) {
        this.nome = cargo;
    }
    
     /**
     * Compara dois Cargos considerando o nome deles
     * @param obj Cargo que será comparado
     * @return true se os Cargo tem o mesmo nome e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Cargo u = (Cargo) obj;
        return u.nome == this.nome; 
    }
    /**
     * Calcula o hascode do Cargo considerando o nome (veja equals)
     * @return O hashcode do nome do Cargo
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * nome.hashCode();
        return hash; 
    }
    
    @Override
    public int compareTo(Cargo o) {
       if (nome != o.nome) return 1;
       else return 0;
    }

    @Override
    public String toString() {
        return "Cargo: " + nome + "\n";
    }
    
}
