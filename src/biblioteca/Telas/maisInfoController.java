package biblioteca.Telas;

import biblioteca.model.livros.Livro;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class maisInfoController implements Initializable {

    private Livro livro;

    @FXML
    public Label id;

    @FXML
    public Label titulo;

    @FXML
    public Label editora;

    @FXML
    public Label autor;

    @FXML
    public Label coautor;

    @FXML
    public Label genero;

    @FXML
    public Label exemplares;

    @FXML
    public Label descricao;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setText(String.valueOf(livro.getIdLivro()));
        titulo.setText(livro.getTitulo());
        editora.setText(livro.getEditora());
        autor.setText(livro.getAutor());
        coautor.setText(livro.getCoautores());
        genero.setText(livro.getGenero());
        exemplares.setText(String.valueOf(livro.getNumeroExemplares()));
        descricao.setText(livro.getDescricao());
    }

    public maisInfoController(Livro livro) {
        this.livro = livro;
    }
}
