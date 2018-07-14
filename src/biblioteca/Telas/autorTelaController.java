package biblioteca.Telas;

import biblioteca.control.AutorController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class autorTelaController implements Initializable {

    @FXML
    private TextField nomeAutor;

    @FXML
    private TextField campoBuscaAutor;

    private ArrayList<Autor> autores;

    @FXML
    private javafx.scene.control.TableView<Autor> tabelaAutor;

    @FXML
    private TableColumn<Autor, Integer> colunaID;

    @FXML
    private TableColumn<Autor, String> colunaNome;

    ObservableList<Autor> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        autores= new ArrayList<Autor>();


        colunaNome.setCellValueFactory(new PropertyValueFactory<Autor, String>("Nome"));
        colunaID.setCellValueFactory(new PropertyValueFactory<Autor, Integer>("idAutor"));
    }

    public void deletaAutor(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        //Inserir aqui a confirmação


        Autor a = tabelaAutor.getSelectionModel().getSelectedItem();
        AutorController controleAutor = new AutorController();
        controleAutor.remover(a);
    }

    public void cadastroAutor(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        String nome;

        nome=nomeAutor.getText();

        AutorController controleAutor = new AutorController();

        controleAutor.cadastrar(0,nome);
    }


    public void buscaAutor(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {

        String nome;

        nome= campoBuscaAutor.getText();

        AutorController controleAutor = new AutorController();

        autores= (ArrayList<Autor>) controleAutor.buscar(nome);

        for (Autor a: autores) {
            data.add(a);
        }

        tabelaAutor.setItems(data);
    }
}
