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
public class UsuarioAluno 
        extends Usuarios{
        private int numeroRegistro;
        private String telefone;
        
    public UsuarioAluno(int id, String nome, String login, String senha, int numeroRegistro, String Telefone) {
        super(id, nome, login, senha);
        this.numeroRegistro = numeroRegistro;
        this.telefone = telefone;
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
       @Override
    public String toString() {
        return "\nNumero de Registro: "+ numeroRegistro +", Nome: "+ nome +", Login: "+ login +", Senha: "+ senha;
    }
}
