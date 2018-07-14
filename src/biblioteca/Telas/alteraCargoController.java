package biblioteca.Telas;

import biblioteca.model.usuarios.Cargo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;


import java.net.URL;
import java.util.ResourceBundle;

public class alteraCargoController{

    @FXML
    public TextField nomeCargo;

    private Cargo cargo;

//    @FXML
//    telaInicialController main;


    public alteraCargoController(Cargo cargo) {
        this.cargo = cargo;
        System.out.println(cargo.getNome());
        nomeCargo.setText("Meu pau");

    }

    public void init(Cargo cargo){

    }
}
