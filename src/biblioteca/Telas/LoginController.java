/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Telas;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import biblioteca.control.UsuarioController;
import biblioteca.exception.BancoException;
import biblioteca.model.dao.UsuarioAdmDAO;
import biblioteca.model.dao.UsuarioAlunoDAO;
import biblioteca.model.dao.UsuarioFuncionarioDAO;
import biblioteca.model.usuarios.Usuario;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;

/**
 * FXML Controller class
 *
 * @author gabriela
 */
public class LoginController implements Initializable {


    @FXML
    private TextField usuario;

    @FXML
    private TextField senha;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }


    public void login(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException, IOException {


        biblioteca.control.LoginController userController = new biblioteca.control.LoginController();



        if (userController.login(usuario.getText(), senha.getText())){

            int teste = userController.tipoLogin(usuario.getText());
            int idUsuario=0;

            if(teste ==1){
                UsuarioAdm adm = UsuarioAdmDAO.buscar(usuario.getText());
                idUsuario=adm.getIdUsuario();
            }else if (teste == 2){
                UsuarioAluno alu = UsuarioAlunoDAO.buscar(usuario.getText());
                idUsuario=alu.getIdUsuario();
            }else if (teste == 3){
                UsuarioFuncionario fun = UsuarioFuncionarioDAO.buscar(usuario.getText());
                idUsuario = fun.getIdUsuario();
            }

            Stage stage = new Stage();

            FXMLLoader fxml = new FXMLLoader(getClass().getResource("telaInicial.fxml"));
            fxml.setController(new telaInicialController(idUsuario,teste));

            Parent c = fxml.load();

            Scene scene = new Scene(c);
            stage.setScene(scene);
            stage.show();

            Stage stage1 = (Stage) usuario.getScene().getWindow();
            stage1.close();
        }else{
            System.out.println("deu ruim");
        }


    }
}
