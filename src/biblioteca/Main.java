/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




/**
 *
 * @author Bianca
 */
public class Main extends Application{



    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent p = FXMLLoader.load(getClass().getResource("Telas/telaInicial.fxml"));
        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Biblioteca");

        //Para tela inicial


        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */




    public static void main(String[] args) throws ClassNotFoundException {

        launch(args);
        
//        System.out.println("\n---------- Funçoes do cargo ------------\n");
//        //Funções Cargo
//        CargoController<Cargo> controleCargos =
//                new CargoController();
//
//        //cadastrando Cargos
//        controleCargos.cadastrarCargo(new Cargo(1, "Administrador"));
//        controleCargos.cadastrarCargo(new Cargo(2, "Bibliotecario"));
//        //listar Cargos
//        System.out.println("Lista de cargos: " + controleCargos.listarCargos());
//        //busca cargo pelo login
//        System.out.println("Buscar cargo 'Bibliotecario': " + controleCargos.buscarCargo("Bibliotecario"));
//        //remover cargo
//        controleCargos.removerCargo("Bibliotecario");
//        //busca cargo pelo login
//        System.out.println("Buscar cargo 'Bibliotecario': " + controleCargos.buscarCargo("Bibliotecario"));
//
//        System.out.println("\n---------- Funçoes do Usuario ------------\n");
//        //Funções Usuario
//        UsuarioController<Usuario> controleUsuarios =
//                new UsuarioController();
//
//        //cadastrando usuarios
//        controleUsuarios.cadastrarUsuario(new UsuarioAdm(1, "Administrador", "Adm","123456"));
//        controleUsuarios.cadastrarUsuario(new UsuarioFuncionario(1, "Funcionario", "Fun","8542",controleCargos.buscarCargo("Bibliotecario")));
//        controleUsuarios.cadastrarUsuario(new UsuarioFuncionario(2, "Funcionario2", "Fun2","854222",controleCargos.buscarCargo("Bibliotecario")));
//        controleUsuarios.cadastrarUsuario(new UsuarioAluno(1, "Aluno", "Aluno","123456",15420,"(45)9999-9999"));
//        //listar usuarios
//        System.out.println("Lista de usuarios: " + controleUsuarios.listarUsuarios());
//        //busca usuario pelo login
//        System.out.println("Buscar usuario 'Fun2': " + controleUsuarios.buscarUsuario("Fun2"));
//        //remover usuario
//        controleUsuarios.removerUsuario("Fun2");
//        //busca usuario pelo login
//        System.out.println("Buscar usuario 'Fun2': " + controleUsuarios.buscarUsuario("Fun2"));
//
//        System.out.println("\n---------- Funçoes do Gernero ------------\n");
//        //Funções de genero
//        GeneroController<Genero> controleGeneros =
//                new GeneroController();
//
//        //cadastrando generos
//        controleGeneros.cadastrarGenero(new Genero(1,"Ação"));
//        controleGeneros.cadastrarGenero(new Genero(2,"Ficção"));
//        controleGeneros.cadastrarGenero(new Genero(3,"Drama"));
//        //listar generos
//        System.out.println("Lista Generos: "+ controleGeneros.listarGeneros());
//        //busca genero pelo nome
//        System.out.println("Buscar Genero 'Drama': "+ controleGeneros.buscarGenero("Drama"));
//        //remover genero
//        controleGeneros.removerGenero("Drama");
//        //busca genero pelo nome
//        System.out.println("Buscar Genero 'Drama': "+ controleGeneros.buscarGenero("Drama"));
//
//        System.out.println("---------- Funçoes do Autor ------------\n");
//        //Funções de autores
//        AutorController<Autor> controleAutor =
//                new AutorController();
//
//        //cadastrando autores
//        controleAutor.cadastrarAutor(new Autor(1, "Autor1"));
//        controleAutor.cadastrarAutor(new Autor(2, "Autor2"));
//        controleAutor.cadastrarAutor(new Autor(3, "Autor3"));
//        //listar autores
//        System.out.println("Listar autores: "+ controleAutor.listarAutores());
//        //busca autor pelo nome
//        System.out.println("Buscar autor 'Autor3': "+ controleAutor.buscarAutor("Autor3"));
//        //remover autor
//        controleAutor.removerAutor("Autor3");
//        //busca autor pelo nome
//        System.out.println("Buscar autor 'Autor3': "+ controleAutor.buscarAutor("Autor3"));
//
//       System.out.println("---------- Funçoes da Editora ------------\n");
//        //Funções de editora
//        EditoraController<Editora> controleEditora =
//                new EditoraController();
//
//        //cadastrando Editoras
//        controleEditora.cadastrarEditora(new Editora(1, "Editora 1"));
//        controleEditora.cadastrarEditora(new Editora(2, "Editora 2"));
//        controleEditora.cadastrarEditora(new Editora(3, "Editora 3"));
//        //listar editoras
//        System.out.println("Listar editoras: "+ controleEditora.listarEditoras());
//        //busca editora pelo nome
//            System.out.println("Buscar editora 'Editora 3': "+ controleEditora.buscarEditora("Editora 3"));
//        //remover Editora
//        controleEditora.removerEditora("Editora 3");
//        //busca editora pelo nome
//        System.out.println("Buscar editora 'Editora 3': "+ controleEditora.buscarEditora("Editora 3"));
//
//        System.out.println("---------- Funçoes do Livro ------------\n");
//        //Funções de livro
//        LivroController<Livro> controleLivros =
//                new LivroController();
//
//        //cadastrando Livros
//        controleLivros.cadastrarLivro(new Livro(1, "Livro1", 4, "Livro livro livro", controleAutor.buscarAutor("Autor1"), controleEditora.buscarEditora("Editora 1"), controleGeneros.buscarGenero("Drama")));
//        controleLivros.cadastrarLivro(new Livro(2, "Livro2", 4, "Livro livro livro", controleAutor.buscarAutor("Autor2"), controleEditora.buscarEditora("Editora 2"), controleGeneros.buscarGenero("Drama")));
//        //listar Livro
//        System.out.println("Listar livros: "+ controleLivros.listarLivros());
//        //buscar livro pelo titulo
//        System.out.println("Buscar livro 'Livro1': "+ controleLivros.buscarLivro("Livro2"));
//        //remover livros
//        controleLivros.removerLivro("Livro2");
//        //buscar livro pelo titulo
//        System.out.println("Buscar livro 'Livro1': "+ controleLivros.buscarLivro("Livro2"));
        
        System.out.println("---------- Funçoes do Autor ------------\n");
        //Funções de autores
        AutorController<Autor> controleAutor = 
                new AutorController();
        
        //cadastrando autores
        controleAutor.cadastrarAutor(new Autor(1, "Autor1"));
        controleAutor.cadastrarAutor(new Autor(2, "Autor2"));
        controleAutor.cadastrarAutor(new Autor(3, "Autor3"));
        //listar autores
        System.out.println("Listar autores: "+ controleAutor.listarAutores());
        //busca autor pelo nome
        System.out.println("Buscar autor 'Autor3': "+ controleAutor.buscarAutor("Autor3"));   
        //remover autor
        controleAutor.removerAutor("Autor3");
        //busca autor pelo nome
        System.out.println("Buscar autor 'Autor3': "+ controleAutor.buscarAutor("Autor3")); 
        
       System.out.println("---------- Funçoes da Editora ------------\n");
        //Funções de editora
        EditoraController<Editora> controleEditora = 
                new EditoraController();
        
        //cadastrando Editoras
        controleEditora.cadastrarEditora(new Editora(1, "Editora 1"));
        controleEditora.cadastrarEditora(new Editora(2, "Editora 2"));
        controleEditora.cadastrarEditora(new Editora(3, "Editora 3"));
        //listar editoras
        System.out.println("Listar editoras: "+ controleEditora.listarEditoras());
        //busca editora pelo nome
            System.out.println("Buscar editora 'Editora 3': "+ controleEditora.buscarEditora("Editora 3")); 
        //remover Editora
        controleEditora.removerEditora("Editora 3");
        //busca editora pelo nome
        System.out.println("Buscar editora 'Editora 3': "+ controleEditora.buscarEditora("Editora 3")); 
        
        System.out.println("---------- Funçoes do Livro ------------\n");
        //Funções de livro
        LivroController<Livro> controleLivros = 
                new LivroController();
        
        //cadastrando Livros
        controleLivros.cadastrarLivro(new Livro(1, "Livro1", 4, "Livro livro livro", controleAutor.buscarAutor("Autor1"), controleEditora.buscarEditora("Editora 1"), controleGeneros.buscarGenero("Drama")));
        controleLivros.cadastrarLivro(new Livro(2, "Livro2", 4, "Livro livro livro", controleAutor.buscarAutor("Autor2"), controleEditora.buscarEditora("Editora 2"), controleGeneros.buscarGenero("Drama")));
        //listar Livro
        System.out.println("Listar livros: "+ controleLivros.listarLivros());
        //buscar livro pelo titulo
        System.out.println("Buscar livro 'Livro1': "+ controleLivros.buscarLivro("Livro2")); 
        //remover livros
        controleLivros.removerLivro("Livro2");
        //buscar livro pelo titulo
        System.out.println("Buscar livro 'Livro1': "+ controleLivros.buscarLivro("Livro2"));      
        
    }
}
