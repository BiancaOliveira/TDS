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
    public int numeroRegistro;
    public String telefone;
        
        /**
     * Construtor do Usuario Aluno
     * @param idUsuario Código do Usuario (PK)
     * @param nome Nome do Usuario
     * @param login Login do usiario
     * @param senha senha do usuario
     * @param numeroRegistro numero de registro do usuario aluno
     * @param Telefone telefone do usuario aluno
    */
        
    public UsuarioAluno(int idUsuario, String nome, String login, String senha, int numeroRegistro, String Telefone) {
        super(idUsuario, nome, login, senha);
        this.numeroRegistro = numeroRegistro;
        this.telefone = Telefone;
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
        return "Codigo: "+ idUsuario +", Nome: "+ nome +","
                + " Login: "+ login +", Senha: "+ senha +","
                + " NumeroRegistro"+ numeroRegistro +", Telefone: "+ telefone+"\n";
    }
}
