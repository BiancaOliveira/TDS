package biblioteca.Telas;

import biblioteca.control.AutorController;
import biblioteca.model.livros.Autor;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
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

    public void altera(ActionEvent actionEvent) {
        AutorController controleAutor = new AutorController();

        System.out.println("Finge que alterou");

        //põe a função de alterar aqui
    }
}
