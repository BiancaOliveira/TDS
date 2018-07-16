package biblioteca.Telas;

import biblioteca.control.AutorController;
import biblioteca.control.EditoraController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Editora;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class alteraEditoraController implements Initializable {

    @FXML
    public TextField nome;

    @FXML
    public Label id;

    private Editora editora;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nome.setText(editora.getNome());
        id.setText(String.valueOf(editora.getIdEditora()));
    }

    public alteraEditoraController(Editora editora) {
        this.editora = editora;
    }

    public void altera(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        EditoraController controleEditora = new EditoraController();

        Editora b = new Editora(Integer.parseInt(id.getText()), nome.getText());

        controleEditora.altera(b);
    }
}
