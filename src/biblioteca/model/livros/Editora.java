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
public class Editora implements Comparable<Editora>{
    private int idEditora;
    private String nome;
    
    /**
     * Construtor do Editora
     * @param id Código da Editora (PK)
     * @param nome Nome da Editora
    */
    public Editora(int id, String nome) {
        this.idEditora = id;
        this.nome = nome;
    }

    public int getId() {
        return idEditora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Compara duas Editoras considerando o nome delas
     * @param obj Editoras que será comparado
     * @return true se as Editoras tem o mesmo nome e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Editora u = (Editora) obj;
        return u.nome == this.nome;
    }
    
    /**
     * Calcula o hascode da Editora considerando o nome (veja equals)
     * @return O hashcode do nome da Editotra
     */
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * nome.hashCode();
        return hash; 
    }
    
    @Override
    public int compareTo(Editora o) {
       if (nome != o.nome) return 1;
       else return 0;
    }

    @Override
    public String toString() {
        return nome +"\n";
    }
    
}
