/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.livros;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Bianca
 */
public class Emprestimo implements Comparable<Emprestimo>{
    public  int idEmprestimo;
    public Date dataEmprestimo;
    public Date dataDevolucao;
    public String usuario;
    public String livro;
    public boolean status;

    public Emprestimo(int idEmprestimo, Date dataEmprestimo, Date  dataDevolucao, String livro, String usuario,boolean status) {
        this.idEmprestimo = idEmprestimo;
        this.status = status;
        this.livro = livro;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }

    
    DateFormat dataFormato =  DateFormat.getDateInstance(DateFormat.MEDIUM);   
    public int getIdEmprestimo() {
        return  idEmprestimo;
    }

    public void setIdEmprestimo(int idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public String getDataEmprestimo() {
        return dataFormato.format(dataEmprestimo);
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataFormato.format(dataDevolucao);
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    public boolean status( LocalDate dataEmprestimo, LocalDate  dataDevolucao){
        LocalDate dataAtual = LocalDate.now();//data atual
        
        if(dataDevolucao.compareTo(dataAtual)>= 0){
            this.status = true;
        }else{
            this.status = false;
        }
        return status;
    }
    
    
    /**
     * Compara dois Emprestimos considerando o nome deles
     * @param obj Emprestimos que será comparado
     * @return true se os Genero tem o mesmo nome e false, caso contrário
     */
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Emprestimo u = (Emprestimo) obj;
        return u.idEmprestimo == this.idEmprestimo; 
    }
    /**
     * Calcula o hascode do Genero considerando o nome (veja equals)
     * @return O hashcode do nome do Genero
     */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * idEmprestimo;
        return hash; 
    }
    
    @Override
    public int compareTo(Emprestimo o) {
       if (idEmprestimo < o.idEmprestimo) return -1;
        else if (idEmprestimo > o.idEmprestimo) return 1;
        else return 0;
    }

    @Override
    public String toString() {
        return "Codigo: " + idEmprestimo + ", dataEmprestimo: " + dataEmprestimo 
                + "dataDevolucao: " + dataDevolucao + ", Livro: " + livro 
                +"Usuario: " + usuario + ", status: " + status + "\n";    
    }
}
