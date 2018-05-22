
package biblioteca.model.bd;

import biblioteca.model.livros.Autor;
import biblioteca.model.livros.Editora;
import biblioteca.model.livros.Generos;
import biblioteca.model.livros.Livros;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import biblioteca.model.usuarios.Usuarios;
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
    private Set<Usuarios> usuarios;
    private Set<Generos> generos;
    private Set<Autor> autores;
    private Set<Livros> livros;
    private Set<Editora> editoras;

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
    
    // Funçõe dos Usuarios
    
    public boolean addUsuario(Usuarios usuario) {
        return usuarios.add(usuario);
    }
    
    public boolean removerUsuario(Usuarios usuario) {
        return usuarios.remove(usuario);      
    }
    
    public Set<Usuarios> listarUsuarios() {
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
    
    public boolean addGenero(Generos genero) {
        return generos.add(genero);
    }
    
    public boolean removerGenero(Generos genero) {
        return generos.remove(genero);      
    }
    
    public Set<Generos> listarGeneros() {
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
    
    public boolean addLivro(Livros livro) {
        return livros.add(livro);
    }
    
    public boolean removerLivro(Livros livro) {
        return livros.remove(livro);      
    }
    
    public Set<Livros> listarLivros() {
        return livros;
    }
    
    // Funções da editora 
    
    public boolean addEditora(Editora editora) {
        return editoras.add(editora);
    }
    
    public boolean removerEditora(Editora editora) {
        return editoras.remove(editora);      
    }
    
    public Set<Editora> listarEditora() {
        return editoras;
    }
}
