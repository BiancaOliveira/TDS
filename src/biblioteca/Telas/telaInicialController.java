package biblioteca.Telas;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author gabriela
 */

public class telaInicialController implements Initializable {

    @FXML
    private BorderPane borderPane;

//    @FXML
//    cargoTelaController cargoTelaController;
//
//    @FXML
//    alteraCargoController alteraCargoController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {


    }

    private void close(){
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }


    public void chamaCargos(ActionEvent actionEvent) throws IOException {
        carregaJanela("cargo");

    }

    private void carregaJanela(String janela) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource(janela+".fxml"));
        borderPane.setCenter(root);

    }

    public void chamaLivros(ActionEvent actionEvent) throws IOException {
        carregaJanela("livros");
    }

    public void chamaAutores(ActionEvent actionEvent) throws IOException {
        carregaJanela("autor");
    }

    public void chamaGeneros(ActionEvent actionEvent) throws IOException {
        carregaJanela("genero");
    }

    public void chamaUsuarios(ActionEvent actionEvent) throws IOException {
        //carregaJanela("usuario");
    }

    public void chamaEditoras(ActionEvent actionEvent) throws IOException {
        carregaJanela("editora");
    }


}
