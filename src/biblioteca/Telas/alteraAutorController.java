package biblioteca.Telas;

import biblioteca.control.AutorController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Autor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class alteraAutorController implements Initializable {

    @FXML
    public TextField nome;

    @FXML
    public Label id;

    private Autor autor;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nome.setText(autor.getNome());
        id.setText(String.valueOf(autor.getIdAutor()));
    }

    public alteraAutorController(Autor autor) {
        this.autor = autor;
    }

    public void altera(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        AutorController controleAutor = new AutorController();

        Autor b = new Autor(Integer.parseInt(id.getText()), nome.getText());

        controleAutor.altera(b);
    }
}
