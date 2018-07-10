/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.control.CargoController;
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
    
    static Connection con = null;
    PreparedStatement PST = null;
    ResultSet rs = null;
    private boolean teste;
    public static void main(String[] args) throws BancoException, ClassNotFoundException, SQLException  {
        con = PostgreDAO.getConnection();

        System.out.println("\n---------- Funçoes do cargo ------------\n");
         //Funções Cargo
        CargoController<Cargo> controleCargos = 
                new CargoController();
    //        
        Cargo cargo = new Cargo(1,"Administrador");
        //cadastrando Cargos
        teste = controleCargos.cadastrarCargo(cargo);

        if(teste == true){
            System.out.println("Cadastro realizado com sucesso");

        }else{
           System.out.println("Erro ao cadastrar");

        }

        
//        controleCargos.cadastrarCargo(new Cargo(2, "Bibliotecario"));
//        //listar Cargos
//        System.out.println("Lista de cargos: " + controleCargos.listarCargos());
//        //busca cargo pelo login
//        System.out.println("Buscar cargo 'Bibliotecario': " + controleCargos.buscarCargo("Bibliotecario"));
//        //remover cargo
//        controleCargos.removerCargo("Bibliotecario");
//        //busca cargo pelo login
//        System.out.println("Buscar cargo 'Bibliotecario': " + controleCargos.buscarCargo("Bibliotecario"));
    }
}
