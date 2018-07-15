package biblioteca.Telas;

import biblioteca.control.AutorController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Autor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

        listaTudo();
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

        data.removeAll(data);

        //System.out.println(data.size());

        tabelaAutor.getItems().clear();

        AutorController controleAutor = new AutorController();

        autores= (ArrayList<Autor>) controleAutor.buscar(nome);

        for (Autor a: autores) {
            data.add(a);
        }

        //System.out.println(data.size());
        tabelaAutor.setItems(data);

        tabelaAutor.refresh();
    }

    public void alteraAutor(ActionEvent actionEvent) throws IOException {
        Autor a = tabelaAutor.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("alteraAutor.fxml"));
        fxml.setController(new alteraAutorController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();
    }

    public void listaTudo() throws BancoException, SQLException, ClassNotFoundException {
        data.removeAll(data);

        //System.out.println(data.size());

        tabelaAutor.getItems().clear();

        AutorController controleAutor = new AutorController();

        autores= (ArrayList<Autor>) controleAutor.listar();

        for (Autor a: autores) {
            data.add(a);
        }

        //System.out.println(data.size());
        tabelaAutor.setItems(data);

        tabelaAutor.refresh();
    }

    public void listaAutores(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        listaTudo();
    }
}
