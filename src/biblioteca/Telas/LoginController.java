/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.Telas;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import biblioteca.control.UsuarioController;
import biblioteca.exception.BancoException;
import biblioteca.model.usuarios.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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


    public void login(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {

        String nome, senhasenha;
        nome=usuario.getText();
        senhasenha=senha.getText();

        UsuarioController userController = new UsuarioController();

        Usuario user =  userController.login(nome);

        System.out.println(user.getNome());


//        if(){
//            Stage stage = (Stage) usuario.getScene().getWindow();
//            stage.close();
//        }else{
//            JOptionPane.showMessageDialog(null,"Usuário ou senha incorretos!");
//        }

    }
}
