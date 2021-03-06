package biblioteca.Telas;

import biblioteca.control.CargoController;
import biblioteca.exception.BancoException;
import biblioteca.model.usuarios.Cargo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class alteraCargoController implements Initializable{

    @FXML
    public TextField nomeCargo;

    @FXML
    public Label id;

    private Cargo cargo;


    public alteraCargoController(Cargo cargo) {
        this.cargo = cargo;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeCargo.setText(cargo.getNome());
        id.setText(String.valueOf(cargo.getIdCargo()));
    }


    public void altera(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        CargoController controleCargo= new CargoController();

        Cargo b = new Cargo(Integer.parseInt(id.getText()), nomeCargo.getText());

        controleCargo.altera(b);

    }
}
