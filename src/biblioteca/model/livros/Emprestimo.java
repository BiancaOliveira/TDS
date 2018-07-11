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
public class Emprestimo {
    private  int idEmprestimo;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private boolean status;

    public Emprestimo(int idEmprestimo, LocalDate dataEmprestimo, LocalDate  dataDevolucao, boolean status) {
        this.idEmprestimo = idEmprestimo;
        this.status = status;
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

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public String getDataDevolucao() {
        return dataFormato.format(dataDevolucao);
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
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
}
