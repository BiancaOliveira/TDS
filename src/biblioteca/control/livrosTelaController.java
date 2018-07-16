package biblioteca.control;

import biblioteca.exception.BancoException;
import biblioteca.model.livros.Autor;
import biblioteca.model.livros.Editora;
import biblioteca.model.livros.Genero;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class livrosTelaController implements Initializable {

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

    }

    public void carregaCombos() throws BancoException, SQLException, ClassNotFoundException {
        EditoraController controleEditora = new EditoraController();
        ArrayList<Editora> editoras2 = (ArrayList<Editora>) controleEditora.listar();

        for (Editora a: editoras2) {
            editoras.add(a);
        }

        editora.setItems(editoras);
    }

    public void buscaLivro(ActionEvent actionEvent) {
    }

    public void alteraLivro(ActionEvent actionEvent) {
    }

    public void deletaLivro(ActionEvent actionEvent) {
    }

    public void listaLivros(ActionEvent actionEvent) {
    }

    public void cadastra(ActionEvent actionEvent) {

    }

    public void maisInformacoes(ActionEvent actionEvent) {
    }
}
