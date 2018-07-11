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
public class UsuarioAdm
    extends Usuario{
    public String cargo;

    public UsuarioAdm(int id, String nome, String login, String senha, String cargo) {
        super(id, nome, login, senha);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
   @Override
    public String toString() {
        return "Nome: "+ nome +" ,Login: "+ login +" ,Senha: "+ senha+ ",Cargo:"+ cargo +"\n";
    }
    
}
