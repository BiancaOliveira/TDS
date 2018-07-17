package biblioteca.Telas;

import biblioteca.control.CargoController;
import biblioteca.control.UsuarioAdmController;
import biblioteca.control.UsuarioAlunoController;
import biblioteca.control.UsuarioFuncionarioController;
import biblioteca.exception.BancoException;
import biblioteca.model.usuarios.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class usuarioTelaController implements Initializable {

    @FXML
    public TextField nome1;

    @FXML
    public TextField nome2;

    @FXML
    public TextField nome3;

    @FXML
    public TextField usuario1;

    @FXML
    public TextField usuario2;

    @FXML
    public TextField usuario3;

    @FXML
    public TextField senha1;

    @FXML
    public TextField senha2;

    @FXML
    public TextField senha3;

    @FXML
    public ComboBox<Cargo> cargo1;

    @FXML
    public ComboBox<Cargo> cargo2;

    @FXML
    public TextField numeroReg;

    @FXML
    public TextField telefone;

    ObservableList<Cargo> coxinha = FXCollections.observableArrayList();
    ObservableList<Cargo> coxinha2 = FXCollections.observableArrayList();

    @FXML
    public TableView<UsuarioAdm> tabelaAdmin;

    @FXML
    public TableColumn<UsuarioAdm, String> colunaIDAdmin;

    @FXML
    public TableColumn<UsuarioAdm, String> colunaNomeAdmin;

    @FXML
    public TableColumn<UsuarioAdm, String> colunaUsuarioAdmin;

    @FXML
    public TableColumn<UsuarioAdm, String> colunaCargoAdmin;

    ObservableList<UsuarioAdm> dataAdmin = FXCollections.observableArrayList();

    public ArrayList<UsuarioAdm> adms;

    @FXML
    public TextField campoBuscaAdmin;

    @FXML
    public TableView<UsuarioFuncionario> tabelaFunc;

    @FXML
    public TableColumn<UsuarioFuncionario, String> colunaIDFunc;

    @FXML
    public TableColumn<UsuarioFuncionario, String> colunaNomeFunc;

    @FXML
    public TableColumn<UsuarioFuncionario, String> colunaUsuarioFunc;

    @FXML
    public TableColumn<UsuarioFuncionario, String> colunaCargoFunc;

    ObservableList<UsuarioFuncionario> dataFunc = FXCollections.observableArrayList();

    public ArrayList<UsuarioFuncionario> funcs;

    @FXML
    public TextField campoBuscaFunc;

    @FXML
    public TableView<UsuarioAluno> tabelaAluno;

    @FXML
    public TableColumn<UsuarioAluno, String> colunaIDAluno;

    @FXML
    public TableColumn<UsuarioAluno, String> colunaNomeAluno;

    @FXML
    public TableColumn<UsuarioAluno, String> colunaUsuarioAluno;

    @FXML
    public TableColumn<UsuarioAluno, String> colunaNumAluno;

    @FXML
    public TableColumn<UsuarioAluno, String> colunaTelefoneAluno;

    ObservableList<UsuarioAluno> dataAluno = FXCollections.observableArrayList();

    public ArrayList<UsuarioAluno> alunos;

    @FXML
    public TextField campoBuscaAluno;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregaCombo();
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colunaNomeAdmin.setCellValueFactory(new PropertyValueFactory<UsuarioAdm, String>("Nome"));
        colunaIDAdmin.setCellValueFactory(new PropertyValueFactory<UsuarioAdm, String>("idUsuario"));
        colunaUsuarioAdmin.setCellValueFactory(new PropertyValueFactory<UsuarioAdm, String>("login"));
        colunaCargoAdmin.setCellValueFactory(new PropertyValueFactory<UsuarioAdm, String>("cargo"));

        colunaNomeFunc.setCellValueFactory(new PropertyValueFactory<UsuarioFuncionario, String>("Nome"));
        colunaIDFunc.setCellValueFactory(new PropertyValueFactory<UsuarioFuncionario, String>("idUsuario"));
        colunaUsuarioFunc.setCellValueFactory(new PropertyValueFactory<UsuarioFuncionario, String>("login"));
        colunaCargoFunc.setCellValueFactory(new PropertyValueFactory<UsuarioFuncionario, String>("cargo"));

        colunaNomeAluno.setCellValueFactory(new PropertyValueFactory<UsuarioAluno, String>("Nome"));
        colunaIDAluno.setCellValueFactory(new PropertyValueFactory<UsuarioAluno, String>("idUsuario"));
        colunaUsuarioAluno.setCellValueFactory(new PropertyValueFactory<UsuarioAluno, String>("login"));
        colunaNumAluno.setCellValueFactory(new PropertyValueFactory<UsuarioAluno, String>("numeroRegistro"));
        colunaTelefoneAluno.setCellValueFactory(new PropertyValueFactory<UsuarioAluno, String>("Telefone"));
    }

    public void cadastraAdmin(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioAdmController u = new UsuarioAdmController();
        u.cadastrar(0, nome1.getText(),usuario1.getText(), senha1.getText(),cargo1.getSelectionModel().getSelectedItem().getNome());
    }

    public void cadastraFunc(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioFuncionarioController u = new UsuarioFuncionarioController();
        u.cadastrar(0, nome2.getText(),usuario2.getText(), senha2.getText(),cargo2.getSelectionModel().getSelectedItem().getNome());
    }

    public void cadastraAluno(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioAlunoController u = new UsuarioAlunoController();
        u.cadastrar(0, nome3.getText(), usuario3.getText(), senha3.getText(), Integer.parseInt(numeroReg.getText()),telefone.getText());
    }

    public void carregaCombo() throws BancoException, SQLException, ClassNotFoundException {
        CargoController controleCargo = new CargoController();
        ArrayList<Cargo> cargos = (ArrayList<Cargo>) controleCargo.listarCargos();

        for (Cargo a: cargos) {
            coxinha.add(a);
            coxinha2.add(a);
        }

        cargo1.setItems(coxinha);
        cargo2.setItems(coxinha2);
    }

    public void alteraAdmin(ActionEvent actionEvent) throws IOException {
        UsuarioAdm a = tabelaAdmin.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("alteraUsuario.fxml"));
        fxml.setController(new alteraAdmController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();
    }

    public void buscaAdmin(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        dataAdmin.removeAll(dataAdmin);

        //System.out.println(data.size());

        tabelaAdmin.getItems().clear();


        UsuarioAdmController u = new UsuarioAdmController();

        adms= (ArrayList<UsuarioAdm>) u.buscar(campoBuscaAdmin.getText());

        for (UsuarioAdm a: adms) {
            dataAdmin.add(a);
        }

        //System.out.println(data.size());
        tabelaAdmin.setItems(dataAdmin);

        tabelaAdmin.refresh();
    }

    public void listarAdmin(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        listaAdminTudo();
    }

    public void removeAdmin(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioAdm a = tabelaAdmin.getSelectionModel().getSelectedItem();
        UsuarioAdmController u = new UsuarioAdmController();

        dataAdmin.remove(a);

        u.remover(a);
        listaAdminTudo();
    }

    public void alteraFunc(ActionEvent actionEvent) throws IOException {
        UsuarioFuncionario a = tabelaFunc.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("alteraUsuario.fxml"));
        fxml.setController(new alteraFuncController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();
    }

    public void buscaFunc(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        dataFunc.removeAll(dataFunc);

        //System.out.println(data.size());

        tabelaFunc.getItems().clear();


        UsuarioFuncionarioController u = new UsuarioFuncionarioController();

        funcs= (ArrayList<UsuarioFuncionario>) u.buscar(campoBuscaFunc.getText());

        for (UsuarioFuncionario a: funcs) {
            dataFunc.add(a);
        }

        //System.out.println(data.size());
        tabelaFunc.setItems(dataFunc);

        tabelaFunc.refresh();
    }

    public void listarFunc(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        listaFuncTudo();
    }

    public void removeFunc(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioFuncionario a = tabelaFunc.getSelectionModel().getSelectedItem();
        UsuarioFuncionarioController u = new UsuarioFuncionarioController();

        dataFunc.remove(a);

        u.remover(a);
        listaFuncTudo();
    }

    public void alteraAluno(ActionEvent actionEvent) throws IOException {
        UsuarioAluno a = tabelaAluno.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("alteraAluno.fxml"));
        fxml.setController(new alteraAlunoController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();
    }

    public void buscaAluno(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        dataAluno.removeAll(dataAluno);

        //System.out.println(data.size());

        tabelaAluno.getItems().clear();


        UsuarioAlunoController u = new UsuarioAlunoController();

        alunos = (ArrayList<UsuarioAluno>) u.buscar(campoBuscaAluno.getText());

        for (UsuarioAluno a: alunos) {
            dataAluno.add(a);
        }

        //System.out.println(data.size());
        tabelaAluno.setItems(dataAluno);

        tabelaAluno.refresh();
    }

    public void listarAluno(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        listaAlunoTudo();
    }

    public void removeAluno(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioAluno a = tabelaAluno.getSelectionModel().getSelectedItem();
        UsuarioAlunoController u = new UsuarioAlunoController();

        dataAluno.remove(a);

        u.remover(a);
        listaAlunoTudo();
    }

    public void listaAdminTudo() throws BancoException, SQLException, ClassNotFoundException {
        dataAdmin.removeAll(dataAdmin);

        //System.out.println(data.size());

        tabelaAdmin.getItems().clear();


        UsuarioAdmController u = new UsuarioAdmController();

        adms= (ArrayList<UsuarioAdm>) u.listar();

        for (UsuarioAdm a: adms) {
            dataAdmin.add(a);
        }

        //System.out.println(data.size());
        tabelaAdmin.setItems(dataAdmin);

        tabelaAdmin.refresh();
    }

    public void listaFuncTudo() throws BancoException, SQLException, ClassNotFoundException {
        dataFunc.removeAll(dataFunc);

        //System.out.println(data.size());

        tabelaFunc.getItems().clear();


        UsuarioFuncionarioController u = new UsuarioFuncionarioController();

        funcs= (ArrayList<UsuarioFuncionario>) u.listar();

        for (UsuarioFuncionario a: funcs) {
            dataFunc.add(a);
        }

        //System.out.println(data.size());
        tabelaFunc.setItems(dataFunc);

        tabelaFunc.refresh();
    }

    public void listaAlunoTudo() throws BancoException, SQLException, ClassNotFoundException {
        dataAluno.removeAll(dataAluno);

        //System.out.println(data.size());

        tabelaAluno.getItems().clear();


        UsuarioAlunoController u = new UsuarioAlunoController();

        alunos = (ArrayList<UsuarioAluno>) u.listar();

        for (UsuarioAluno a: alunos) {
            dataAluno.add(a);
        }

        //System.out.println(data.size());
        tabelaAluno.setItems(dataAluno);

        tabelaAluno.refresh();
    }
}
