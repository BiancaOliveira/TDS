package biblioteca.Telas;

import biblioteca.control.CargoController;
import biblioteca.control.UsuarioAdmController;
import biblioteca.exception.BancoException;
import biblioteca.model.usuarios.Cargo;
import biblioteca.model.usuarios.UsuarioAdm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class alteraAdmController implements Initializable {

    @FXML
    public Label labelTitulo;

    @FXML
    public TextField nome;

    @FXML
    public TextField usuario;

    @FXML
    public TextField senha;

    @FXML
    public ComboBox<Cargo> cargo;

    @FXML
    public Label id;

    private UsuarioAdm adm;

    ObservableList<Cargo> data = FXCollections.observableArrayList();

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

        labelTitulo.setText("Alterar Usuário Administrador");
        nome.setText(adm.getNome());
        usuario.setText(adm.getLogin());
        senha.setText(adm.getSenha());
        id.setText(String.valueOf(adm.getIdUsuario()));

        CargoController controleCargo = new CargoController();
        try {
            cargo.setValue(controleCargo.buscarUnico(adm.getCargo()));
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public alteraAdmController(UsuarioAdm adm) {
        this.adm = adm;
    }

    public void altera(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioAdmController u = new UsuarioAdmController();

        UsuarioAdm b = new UsuarioAdm(Integer.parseInt(id.getText()), nome.getText(),usuario.getText(),senha.getText(),cargo.getSelectionModel().getSelectedItem().getNome());

        u.altera(b);

    }

    public void carregaCombo() throws BancoException, SQLException, ClassNotFoundException {
        CargoController controleCargo = new CargoController();
        ArrayList<Cargo> cargos = (ArrayList<Cargo>) controleCargo.listarCargos();

        for (Cargo a: cargos) {
            data.add(a);
        }

        cargo.setItems(data);
    }
}
