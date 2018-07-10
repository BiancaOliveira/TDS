/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.control.AutorController;
import biblioteca.control.CargoController;
import biblioteca.control.EditoraController;
import biblioteca.control.GeneroController;
import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.usuarios.Cargo;
import java.sql.*;
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

        Parent p = FXMLLoader.load(getClass().getResource("Telas/cargo.fxml"));
        //Parent p = FXMLLoader.load(getClass().getResource("Telas/Login.fxml"));
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
    
    static Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static boolean retorno = false;
    
    public static void main(String[] args) throws BancoException, ClassNotFoundException, SQLException  {
        con = PostgreDAO.getConnection();

        System.out.println("\n---------- Funçoes do cargo ------------\n");
         //Funções Cargo
        CargoController controleCargos = 
                new CargoController();

          controleCargos.cadastrarCargo(88,"Administrador");

        
        //listar Cargos
        System.out.println("Lista de cargos: " + controleCargos.listarCargos());
        //busca cargo pelo login
        System.out.println("Buscar cargo 'Bibliotecario': " + controleCargos.buscarCargo("Administrador"));
//      //remover cargo
        controleCargos.removerCargo( controleCargos.buscarCargo("Administrador"));
        
        System.out.println("\n---------- Funçoes do Genero ------------\n");
         //Funções Cargo
        GeneroController controleGenero = 
                new GeneroController();

          controleGenero.cadastrar(0,"terror");

        
        //listar Cargos
        System.out.println("Lista de generos: " + controleGenero.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleGenero.buscar("terror"));
//      //remover cargo
        controleGenero.remover( controleGenero.buscar("terror"));
        
        System.out.println("\n---------- Funçoes do Autor ------------\n");
         //Funções Cargo
        AutorController controleAutor = 
                new AutorController();

          controleAutor.cadastrar(0,"bart");

        
        //listar Cargos
        System.out.println("Lista de generos: " + controleAutor.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleAutor.buscar("bart"));
//      //remover cargo
        controleAutor.remover( controleAutor.buscar("bart"));
        
        System.out.println("\n---------- Funçoes do Editora ------------\n");
         //Funções Cargo
        EditoraController controleEditora = 
                new EditoraController();

          controleEditora.cadastrar(0,"teste");

        
        //listar Cargos
        System.out.println("Lista : " + controleEditora.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleEditora.buscar("teste"));
//      //remover cargo
        controleEditora.remover( controleEditora.buscar("teste"));
    }
}
