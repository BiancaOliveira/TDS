package biblioteca.control;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class cargoTelaController implements Initializable {

    @FXML
    private TextField nomeCargo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cadastroCargo(ActionEvent actionEvent) {
        // faz aqui as paradas para cadastro

        String nome;

        nome=nomeCargo.getText();

        System.out.println("fffff "+nome);
    }
}
