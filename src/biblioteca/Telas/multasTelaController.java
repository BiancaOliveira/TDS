package biblioteca.Telas;

import biblioteca.control.MultasController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Multas;
import com.sun.org.apache.xpath.internal.operations.Mult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class multasTelaController implements Initializable {

    private List<Multas> multas;

    @FXML
    private javafx.scene.control.TableView<Multas> tabelaMultas;

    @FXML
    private TableColumn<Multas, String> colunaID;

    @FXML
    private TableColumn<Multas, String> colunaUsuario;

    @FXML
    private TableColumn<Multas, String> colunaLivro;

    @FXML
    private TableColumn<Multas, String> colunaStatus;

    @FXML
    private TableColumn<Multas, String> colunaValor;

    ObservableList<Multas> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colunaID.setCellValueFactory(new PropertyValueFactory<Multas, String>("idMultas"));
        colunaUsuario.setCellValueFactory(new PropertyValueFactory<Multas, String>("usuario"));
        colunaLivro.setCellValueFactory(new PropertyValueFactory<Multas, String>("livro"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<Multas, String>("status"));
        colunaValor.setCellValueFactory(new PropertyValueFactory<Multas, String>("valorTotal"));

        try {
            lista();
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void pagamento(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        MultasController controleMulta = new MultasController();

        controleMulta.alteraStatus(tabelaMultas.getSelectionModel().getSelectedItem().getIdMultas());

        lista();
    }

    public void listaTudo(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        lista();
    }

    public void lista() throws BancoException, SQLException, ClassNotFoundException {
        data.removeAll(data);

        //System.out.println(data.size());

        tabelaMultas.getItems().clear();


        MultasController controleMulta = new MultasController();

        multas = controleMulta.listar();

        for (Multas a: multas) {

            data.add(a);
        }

        //System.out.println(data.size());
        tabelaMultas.setItems(data);

        tabelaMultas.refresh();
    }
}
