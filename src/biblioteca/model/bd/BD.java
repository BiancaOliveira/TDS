
package biblioteca.model.bd;

import biblioteca.model.livros.Autor;
import biblioteca.model.livros.Editora;
import biblioteca.model.livros.Genero;
import biblioteca.model.livros.Livro;
import biblioteca.model.usuarios.Cargo;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import biblioteca.model.usuarios.Usuario;
import java.util.Set;
import java.util.TreeSet;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Bianca
 */
public class BD {
    private Set<Usuario> usuarios;
    private Set<Genero> generos;
    private Set<Autor> autores;
    private Set<Livro> livros;
    private Set<Editora> editoras;
    private Set<Cargo> cargos;

    //begin Singleton
    private static BD banco;
    
    public static BD getBanco() {
        if (banco == null) 
            banco = new BD();
        return banco;
    }
    
    //construtor private  - somente getBanco tem acesso
    private BD() {
        usuarios = new TreeSet<>();
        generos = new TreeSet<>();
        autores = new TreeSet<>();
        livros = new TreeSet<>();
        editoras = new TreeSet<>();
    }
    //end singleton
    
    // Funçõe dos Usuario
    
    public boolean addUsuario(Usuario usuario) {
        return usuarios.add(usuario);
    }
    
    public boolean removerUsuario(Usuario usuario) {
        return usuarios.remove(usuario);      
    }
    
    public Set<Usuario> listarUsuarios() {
        return usuarios;
    }
    
    public Set<UsuarioAdm> listarUsuariosAdms() {
        Set<UsuarioAdm> usuarios = new TreeSet<>();
        for (UsuarioAdm c : usuarios) {
            if (c instanceof UsuarioAdm) {
                usuarios.add((UsuarioAdm)c);
            }
        }
        return usuarios;
    }

    public Set<UsuarioFuncionario> listarUsuariosFuncionarios() {
        Set<UsuarioFuncionario> usuarios = new TreeSet<>();
        for (UsuarioFuncionario c : usuarios) {
            if (c instanceof UsuarioFuncionario) {
                usuarios.add((UsuarioFuncionario)c);
            }
        }
        return usuarios;
    }
    
    public Set<UsuarioAluno> listarUsuariosAlunos() {
        Set<UsuarioAluno> usuarios = new TreeSet<>();
        for (UsuarioAluno c : usuarios) {
            if (c instanceof UsuarioAluno) {
                usuarios.add((UsuarioAluno)c);
            }
        }
        return usuarios;
    }
    
   // Funções do generos 
    
    public boolean addGenero(Genero genero) {
        return generos.add(genero);
    }
    
    public boolean removerGenero(Genero genero) {
        return generos.remove(genero);      
    }
    
    public Set<Genero> listarGeneros() {
        return generos;
    }
    
    // Funções dos autores 
    
    public boolean addAutor(Autor autor) {
        return autores.add(autor);
    }
    
    public boolean removerAutor(Autor autor) {
        return autores.remove(autor);      
    }
    
    public Set<Autor> listarAutores() {
        return autores;
    }
    
    // Funções dos livros 
    
    public boolean addLivro(Livro livro) {
        return livros.add(livro);
    }
    
    public boolean removerLivro(Livro livro) {
        return livros.remove(livro);      
    }
    
    public Set<Livro> listarLivros() {
        return livros;
    }
    
    // Funções da editora 
    
    public boolean addEditora(Editora editora) {
        return editoras.add(editora);
    }
    
    public boolean removerEditora(Editora editora) {
        return editoras.remove(editora);      
    }
    
    public Set<Editora> listarEditoras() {
        return editoras;
    }
    
    // Funções da editora 
    
    public boolean addCargo(Cargo cargo) {
        return cargos.add(cargo);
    }
    
    public boolean removerCargo(Cargo cargo) {
        return cargos.remove(cargo);      
    }
    
    public Set<Cargo> listarCargos() {
        return cargos;
    }
}
