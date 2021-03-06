package biblioteca.Telas;

import biblioteca.control.CargoController;
import biblioteca.exception.BancoException;
import biblioteca.model.usuarios.Cargo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.text.TabableView;
import javax.swing.text.TableView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class cargoTelaController implements Initializable {

    @FXML
    private TextField nomeCargo;

    @FXML
    private TextField campoBuscaCargo;

    private ArrayList<Cargo> cargos;

    @FXML
    private javafx.scene.control.TableView<Cargo> tabelaCargos;

    @FXML
    private TableColumn<Cargo, String> colunaID;

    @FXML
    private TableColumn<Cargo, String> colunaNome;

    public Cargo enviadoDeDeus;

//    @FXML
//    telaInicialController telaInicialController;
    

    ObservableList<Cargo> data = FXCollections.observableArrayList();
    ObservableList<Cargo> data2 = FXCollections.observableArrayList();


    public void cadastroCargo(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        // faz aqui as paradas para cadastro

        String nome;

        nome=nomeCargo.getText();

        CargoController controleCargo= new CargoController();

        controleCargo.cadastrarCargo(0, nome);

        tabelaCargos.refresh();
    }

    public void buscaCargo(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        String nome;

        data.removeAll(data);

        System.out.println(data.size());

        tabelaCargos.getItems().clear();

        nome= campoBuscaCargo.getText();

        CargoController controleCargo= new CargoController();

        cargos= (ArrayList<Cargo>) controleCargo.buscarCargo(nome);

        for (Cargo a: cargos) {
            data.add(a);
        }

        System.out.println(data.size());
        tabelaCargos.setItems(data);

        tabelaCargos.refresh();

    }

    public void deletaCargo(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {

        //Inserir aqui a confirmação


        Cargo a = tabelaCargos.getSelectionModel().getSelectedItem();

        data.remove(a);

        CargoController controleCargo= new CargoController();
        controleCargo.removerCargo(a);
        listaTudo();

    }

    public void alteraCargo(ActionEvent actionEvent) throws IOException {
        Cargo a = tabelaCargos.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("alteraCargo.fxml"));
        fxml.setController(new alteraCargoController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargos= new ArrayList<Cargo>();


        colunaNome.setCellValueFactory(new PropertyValueFactory<Cargo, String>("Nome"));
        colunaID.setCellValueFactory(new PropertyValueFactory<Cargo, String>("idCargo"));

    }

    public void listaTudo() throws BancoException, SQLException, ClassNotFoundException {
        data.removeAll(data);

        //System.out.println(data.size());

        tabelaCargos.getItems().clear();


        CargoController controleCargo= new CargoController();

        cargos= (ArrayList<Cargo>) controleCargo.listarCargos();

        for (Cargo a: cargos) {
            data.add(a);
        }

        //System.out.println(data.size());
        tabelaCargos.setItems(data);

        tabelaCargos.refresh();
    }

    public void listaCargos(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        listaTudo();

    }
}
