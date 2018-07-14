package biblioteca.Telas;

import biblioteca.control.EditoraController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Editora;
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

public class editoraTelaController implements Initializable {

    @FXML
    private TextField nomeEditora;

    @FXML
    private TextField campoBuscaEditora;

    private ArrayList<Editora> editoras;

    @FXML
    private javafx.scene.control.TableView<Editora> tabelaEditora;

    @FXML
    private TableColumn<Editora, Integer> colunaID;

    @FXML
    private TableColumn<Editora, String> colunaNome;

    ObservableList<Editora> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        editoras= new ArrayList<Editora>();


        colunaNome.setCellValueFactory(new PropertyValueFactory<Editora, String>("Nome"));
        colunaID.setCellValueFactory(new PropertyValueFactory<Editora, Integer>("Id"));
    }

    public void cadastroEditora(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        String nome;

        nome=nomeEditora.getText();

        EditoraController controleEditora = new EditoraController();

        controleEditora.cadastrar(0,nome);
    }

    public void buscaEditora(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        String nome;

        nome= campoBuscaEditora.getText();

        EditoraController controleEditora = new EditoraController();

        editoras= (ArrayList<Editora>) controleEditora.buscar(nome);

        for (Editora a: editoras) {
            data.add(a);
        }

        tabelaEditora.setItems(data);
    }

    public void deletaEditora(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        Editora a = tabelaEditora.getSelectionModel().getSelectedItem();
        EditoraController controleEditora = new EditoraController();
        controleEditora.remover(a);
    }
}
