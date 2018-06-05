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
        extends Usuario{
    private int numeroRegistro;
    private String telefone;
        
        /**
     * Construtor do Usuario Aluno
     * @param id Código do Usuario (PK)
     * @param nome Nome do Usuario
     * @param login Login do usiario
     * @param senha senha do usuario
     * @param numeroRegistro numero de registro do usuario aluno
     * @param Telefone telefone do usuario aluno
    */
        
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
        return "Numero de Registro: "+ numeroRegistro +", Nome: "+ nome +", Login: "+ login +", Senha: "+ senha +"\n";
    }
}
