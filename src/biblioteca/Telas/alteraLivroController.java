package biblioteca.Telas;

import biblioteca.control.AutorController;
import biblioteca.control.EditoraController;
import biblioteca.control.GeneroController;
import biblioteca.control.LivroController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Autor;
import biblioteca.model.livros.Editora;
import biblioteca.model.livros.Genero;
import biblioteca.model.livros.Livro;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class alteraLivroController implements Initializable {

    private Livro livro;

    @FXML
    public Label id;

    @FXML
    public TextField titulo;

    @FXML
    public ComboBox<Editora> editora;

    @FXML
    public ComboBox<Autor> autor;

    @FXML
    public ComboBox<Autor> coautor;

    @FXML
    public ComboBox<Genero> genero;

    @FXML
    public TextField exemplares;

    @FXML
    public TextArea descricao;

    ObservableList<Editora> editoras = FXCollections.observableArrayList();
    ObservableList<Autor> autores = FXCollections.observableArrayList();
    ObservableList<Autor> coautores = FXCollections.observableArrayList();
    ObservableList<Genero> generos = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregaCombos();
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        EditoraController controleEditora = new EditoraController();
        AutorController controleAutor = new AutorController();
        GeneroController controleGenero = new GeneroController();

        id.setText(String.valueOf(livro.getIdLivro()));
        titulo.setText(livro.getTitulo());
        try {
            editora.setValue(controleEditora.buscarUnico(livro.getEditora()));
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            autor.setValue(controleAutor.buscaUnico(livro.getAutor()));
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            coautor.setValue(controleAutor.buscaUnico(livro.getCoautores()));
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            genero.setValue(controleGenero.buscarUnico(livro.getGenero()));
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        exemplares.setText(String.valueOf(livro.getNumeroExemplares()));
        descricao.setText(livro.getDescricao());

    }

    public alteraLivroController(Livro livro) {
        this.livro = livro;
    }

    public void carregaCombos() throws BancoException, SQLException, ClassNotFoundException {
        EditoraController controleEditora = new EditoraController();
        ArrayList<Editora> editoras2 = (ArrayList<Editora>) controleEditora.listar();

        for (Editora a: editoras2) {
            editoras.add(a);
        }

        editora.setItems(editoras);

        AutorController controleAutor = new AutorController();
        ArrayList<Autor> autores2 = (ArrayList<Autor>) controleAutor.listar();
        coautores.add(new Autor(0, "-"));

        for (Autor a: autores2) {
            autores.add(a);
            coautores.add(a);
        }

        autor.setItems(autores);
        coautor.setItems(coautores);

        GeneroController controleGenero = new GeneroController();
        ArrayList<Genero> generos1 = (ArrayList<Genero>) controleGenero.listar();

        for (Genero a: generos1) {
            generos.add(a);
        }

        genero.setItems(generos);


    }

    public void altera(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        LivroController controleLivro = new LivroController();

        Livro b = new Livro(Integer.parseInt(id.getText()), titulo.getText(), Integer.parseInt(exemplares.getText()),
                descricao.getText(), autor.getSelectionModel().getSelectedItem().getNome(),
                editora.getSelectionModel().getSelectedItem().getNome(), genero.getSelectionModel().getSelectedItem().getNome(),
                coautor.getSelectionModel().getSelectedItem().getNome());

        controleLivro.altera(b);


    }
}
