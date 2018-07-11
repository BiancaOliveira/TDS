/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.EditoraDAO;
import biblioteca.model.dao.LoginDAO;
import biblioteca.model.livros.Editora;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author gabriela
 */
public class LoginController implements Initializable {

    public boolean login (String usuario, String senha) throws BancoException, ClassNotFoundException, SQLException{
        return LoginDAO.login(usuario, senha);
    }
    public int tipoLogin (String usuario) throws BancoException, ClassNotFoundException, SQLException{
        return LoginDAO.tipoUsuario(usuario);
    }
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
