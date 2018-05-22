/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.control.AutorController;
import biblioteca.control.EditoraController;
import biblioteca.control.EditoraController;
import biblioteca.control.GenerosController;
import biblioteca.control.LivrosController;
import biblioteca.control.UsuariosController;
import biblioteca.model.livros.Autor;
import biblioteca.model.livros.Editora;
import biblioteca.model.livros.Generos;
import biblioteca.model.livros.Livros;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import biblioteca.model.usuarios.Usuarios;

/**
 *
 * @author Bianca
 */
public class Main {
        /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        //Funções Usuario
        UsuariosController<Usuarios> controleUsuarios = 
                new UsuariosController();
        
        //cadastrando usuarios
        controleUsuarios.cadastrarUsuario(new UsuarioAdm(1, "Administrador", "Adm","123456"));
        controleUsuarios.cadastrarUsuario(new UsuarioFuncionario(1, "Funcionario", "Fun","8542","Bibliotecario"));
        controleUsuarios.cadastrarUsuario(new UsuarioFuncionario(2, "Funcionario2", "Fun2","854222","Bibliotecario2"));
        controleUsuarios.cadastrarUsuario(new UsuarioAluno(1, "Aluno", "Aluno","123456",15420,"(45)9999-9999"));
        //listar usuarios
        System.out.println("Lista de usuarios: " + controleUsuarios.listarUsuarios());
        //busca usuario pelo login
        System.out.println("Buscar usuario 'Fun2': " + controleUsuarios.buscarUsuario("Fun2"));
        //remover usuario
        controleUsuarios.removerUsuario("Fun2");
        //busca usuario pelo login
        System.out.println("Buscar usuario 'Fun2': " + controleUsuarios.buscarUsuario("Fun2"));
        
        //Funções de genero
        GenerosController<Generos> controleGeneros = 
                new GenerosController();
    
        controleGeneros.cadastrarGenero(new Generos(1,"Ação"));
        controleGeneros.cadastrarGenero(new Generos(2,"Ficção"));
        controleGeneros.cadastrarGenero(new Generos(3,"Drama"));
        //listar generos
        System.out.println("Lista Generos: "+ controleGeneros.listarGeneros());
        //busca genero pelo nome
        System.out.println("Buscar Genero 'Ação': "+ controleGeneros.buscarGenero("Ação"));    
        //remover genero
        controleGeneros.removerGenero("Ação");
        //busca usuario pelo login
        System.out.println("Buscar Genero 'Ação': "+ controleGeneros.buscarGenero("Ação"));   
        
        
        //Funções de autores
        AutorController<Autor> controleAutor = new AutorController();
        
        controleAutor.cadastrarAutor(new Autor(1, "Autor1"));
        controleAutor.cadastrarAutor(new Autor(2, "Autor2"));
        controleAutor.cadastrarAutor(new Autor(3, "Autor3"));
        //listar autores
        System.out.println("Autores: "+ controleAutor.listarAutores());
        //remover autores
        controleAutor.removerAutor("Autor3");
        System.out.println("Autores: "+ controleAutor.listarAutores());
        
        //Funções de editoras
        EditoraController<Editora> controleEditora = new EditoraController();
        
        controleEditora.cadastrarEditora(new Editora(1, "Editora 1"));
        controleEditora.cadastrarEditora(new Editora(2, "Editora 2"));
        controleEditora.cadastrarEditora(new Editora(3, "Editora 3"));
        //listar editoras
        System.out.println("Editoras: "+ controleEditora.listarEditora());
        //remover autores
        controleEditora.removerEditora("Editora 3");
        System.out.println("Editora: "+ controleEditora.listarEditora());
        
        //Funções de livros
        LivrosController<Livros> controleLivros = new LivrosController();
        
        controleLivros.cadastrarLivro(new Livros(1, "Livro1", 4, "Livro livro livro", controleAutor.buscarAutor("Autor1"), controleEditora.buscarEditora("Editora 1"), controleGeneros.buscarGenero("Drama")));
        controleLivros.cadastrarLivro(new Livros(2, "Livro2", 4, "Livro livro livro", controleAutor.buscarAutor("Autor2"), controleEditora.buscarEditora("Editora 2"), controleGeneros.buscarGenero("Drama")));
        
        //listar Livros
        System.out.println("Autores: "+ controleLivros.listarLivros());
        //remover livros
        controleLivros.removerLivro("Livro2");
        
        System.out.println("Autores: "+ controleLivros.listarLivros());
       
        
        
        
    }
}
