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
public class Usuario {
    private int id;
    String nome;
    String login;
    String senha;
    
    public Usuario (int id, String nome, String login, String senha){
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;           
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this || obj.getClass() != this.getClass()) return false;
        Usuario u = (Usuario) obj;
        return u.login == this.login;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = hash * 13 * login.hashCode();
        return hash;
    }
    
    @Override
    public String toString() {
        return " nome: "+ nome +",login: " + login;
    }
}
