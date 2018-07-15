package biblioteca.Telas;

import biblioteca.control.CargoController;
import biblioteca.model.usuarios.Cargo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class alteraCargoController implements Initializable{

    @FXML
    public TextField nomeCargo;

    @FXML
    public Label id;

    private Cargo cargo;

//    @FXML
//    telaInicialController main;


    public alteraCargoController(Cargo cargo) {
        this.cargo = cargo;
        System.out.println(cargo.getNome());


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nomeCargo.setText(cargo.getNome());
        id.setText(String.valueOf(cargo.getIdCargo()));
    }


    public void altera(ActionEvent actionEvent) {
        CargoController controleCargo= new CargoController();

        System.out.println("Finge que alterou");

        //põe a função de alterar aqui
    }
}
