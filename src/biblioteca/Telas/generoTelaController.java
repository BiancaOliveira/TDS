package biblioteca.Telas;

import biblioteca.control.GeneroController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Genero;
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

public class generoTelaController implements Initializable {

    @FXML
    private TextField nomeGenero;

    @FXML
    private TextField campoBuscaGenero;

    private ArrayList<Genero> generos;

    @FXML
    private javafx.scene.control.TableView<Genero> tabelaGenero;

    @FXML
    private TableColumn<Genero, Integer> colunaID;

    @FXML
    private TableColumn<Genero, String> colunaNome;

    ObservableList<Genero> data = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generos= new ArrayList<Genero>();


        colunaNome.setCellValueFactory(new PropertyValueFactory<Genero, String>("Nome"));
        colunaID.setCellValueFactory(new PropertyValueFactory<Genero, Integer>("idGenero"));
    }

    public void cadastroGenero(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        String nome;

        nome=nomeGenero.getText();

        GeneroController controleGenero = new GeneroController();

        controleGenero.cadastrar(0,nome);
    }

    public void buscaGenero(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        String nome;

        nome= campoBuscaGenero.getText();

        data.removeAll(data);

        //System.out.println(data.size());

        tabelaGenero.getItems().clear();

        GeneroController controleGenero = new GeneroController();

        generos= (ArrayList<Genero>) controleGenero.buscar(nome);

        for (Genero a: generos) {
            data.add(a);
        }

        //System.out.println(data.size());
        tabelaGenero.setItems(data);

        tabelaGenero.refresh();
    }

    public void deletaGenero(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        //Inserir aqui a confirmação


        Genero a = tabelaGenero.getSelectionModel().getSelectedItem();
        GeneroController controleGenero = new GeneroController();
        controleGenero.remover(a);

        listaTudo();
    }

    public void listaTudo() throws BancoException, SQLException, ClassNotFoundException {
        data.removeAll(data);

        //System.out.println(data.size());

        tabelaGenero.getItems().clear();

        GeneroController controleGenero = new GeneroController();

        generos= (ArrayList<Genero>) controleGenero.listar();

        for (Genero a: generos) {
            data.add(a);
        }

        //System.out.println(data.size());
        tabelaGenero.setItems(data);

        tabelaGenero.refresh();
    }

    public void listaGeneros(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        listaTudo();
    }

    public void alteraGenero(ActionEvent actionEvent) throws IOException {
        Genero a = tabelaGenero.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("alteraGenero.fxml"));
        fxml.setController(new alteraGeneroController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();
    }
}
