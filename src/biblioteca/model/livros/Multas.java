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
public class Multas implements Comparable<Multas> {
    private int idMultas;
    private double taxa;
    private double valorTotal;
    private String usuario;
    private boolean status;
    private String livro;

    public Multas(int idMultas, double taxa, double valorTotal, String usuario, boolean status, String livro) {
        this.idMultas = idMultas;
        this.taxa = taxa;
        this.valorTotal = valorTotal;
        this.usuario = usuario;
        this.status = status;
        this.livro = livro;
    }

    public int getIdMultas() {
        return idMultas;
    }

    public void setIdMultas(int idMultas) {
        this.idMultas = idMultas;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(float taxa) {
        this.taxa = taxa;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }
    
    /**
     * Compara dois Multas considerando o nome deles
     * @param obj Multas que será comparado
     * @return true se os Genero tem o mesmo nome e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Multas u = (Multas) obj;
        return u.idMultas == this.idMultas; 
    }
    /**
     * Calcula o hascode do Emprestimos considerando o nome (veja equals)
     * @return O hashcode do nome do Emprestimos
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * idMultas;
        return hash; 
    }
    
    @Override
    public int compareTo(Multas o) {
       if (idMultas < o.idMultas) return -1;
        else if (idMultas > o.idMultas) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Codigo: " + idMultas + ", Livro: " + livro 
                +", Usuario: " + usuario + ", taxa: " 
                + ", Valor: " + valorTotal + ", Status: " + status + "\n";    
    }
}
