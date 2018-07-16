package biblioteca.Telas;

import biblioteca.control.GeneroController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Genero;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class alteraGeneroController implements Initializable {

    @FXML
    public TextField nome;

    @FXML
    public Label id;

    private Genero genero;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nome.setText(genero.getNome());
        id.setText(String.valueOf(genero.getIdGenero()));
    }

    public alteraGeneroController(Genero genero) {
        this.genero = genero;
    }

    public void altera(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        GeneroController controleGenero = new GeneroController();

        Genero b = new Genero(Integer.parseInt(id.getText()), nome.getText());

        controleGenero.altera(b);
    }
}
