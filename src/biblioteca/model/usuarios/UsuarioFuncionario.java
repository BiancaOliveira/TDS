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
public class UsuarioFuncionario 
    extends Usuario{
    private Cargo cargo;

    /**
     * Construtor do Usuario Funcionario
     * @param id Código do Usuario (PK)
     * @param nome Nome do Usuario
     * @param login Login do usiario
     * @param senha senha do usuario
     * @param senha senha do usuario
     * @param cargo cargo do funcionario
    */
    
    public UsuarioFuncionario(int id, String nome, String login, String senha, Cargo cargo) {
        super(id, nome, login, senha);
        this.cargo = cargo;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
 
    @Override
    public String toString() {
        return "Nome: "+ nome +", Cargo: "+ cargo +", Login: "+ login +", Senha: "+ senha +"\n";
    }
}
