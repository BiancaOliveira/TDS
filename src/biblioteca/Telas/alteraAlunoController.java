package biblioteca.Telas;

import biblioteca.control.UsuarioAlunoController;
import biblioteca.exception.BancoException;
import biblioteca.model.usuarios.UsuarioAluno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class alteraAlunoController implements Initializable {

    @FXML
    public TextField nome;

    @FXML
    public TextField usuario;

    @FXML
    public TextField senha;

    @FXML
    public TextField numeroReg;

    @FXML
    public TextField telefone;

    @FXML
    public Label id;

    private UsuarioAluno adm;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        nome.setText(adm.getNome());
        usuario.setText(adm.getLogin());
        senha.setText(adm.getSenha());
        id.setText(String.valueOf(adm.getIdUsuario()));
        numeroReg.setText(String.valueOf(adm.getNumeroRegistro()));
        telefone.setText(adm.getTelefone());
    }

    public alteraAlunoController(UsuarioAluno adm) {
        this.adm = adm;
    }

    public void altera(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        UsuarioAlunoController u = new UsuarioAlunoController();

        UsuarioAluno b = new UsuarioAluno(Integer.parseInt(id.getText()), nome.getText(),usuario.getText(),senha.getText(),Integer.parseInt(numeroReg.getText()),telefone.getText());

        u.altera(b);
    }
}
