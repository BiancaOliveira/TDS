package biblioteca.Telas;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.UsuarioAdmDAO;
import biblioteca.model.dao.UsuarioAlunoDAO;
import biblioteca.model.dao.UsuarioFuncionarioDAO;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * FXML Controller class
 *
 * @author gabriela
 */

public class telaInicialController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private int idUsuario;
    private int tipoUsuario; //1 = adm, 2 = aluno, 3 = fun
    private UsuarioAdm usuarioAdm;
    private UsuarioAluno usuarioAluno;
    private UsuarioFuncionario usuarioFuncionario;

    @FXML
    public Label labelUsuario;


//    @FXML
//    cargoTelaController cargoTelaController;
//
//    @FXML
//    alteraCargoController alteraCargoController;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        System.out.println("Tipo="+tipoUsuario);

        if(tipoUsuario ==1){
            try {
                UsuarioAdm adm = UsuarioAdmDAO.buscarID(idUsuario);
                labelUsuario.setText("Usuario: "+adm.getNome());
                System.out.println("entrou 1");
            } catch (BancoException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if (tipoUsuario == 2){
            try {
                UsuarioAluno alu = UsuarioAlunoDAO.buscarID(idUsuario);
                labelUsuario.setText("Usuario: "+alu.getNome());
                System.out.println("entrou 2");
            } catch (BancoException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }else if (tipoUsuario == 3){
            try {
                UsuarioFuncionario fun = UsuarioFuncionarioDAO.buscarID(idUsuario);
                labelUsuario.setText("Usuario: "+fun.getNome());
                System.out.println("entrou 3");
            } catch (BancoException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        try {
            carregaJanela("livros");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public telaInicialController(int idUsuario, int tipoUsuario) {
        this.idUsuario = idUsuario;
        this.tipoUsuario = tipoUsuario;
    }

    private void close(){
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }


    public void chamaCargos(ActionEvent actionEvent) throws IOException {
        carregaJanela("cargo");

    }

    private void carregaJanela(String janela) throws IOException {
        Parent root;

        root = FXMLLoader.load(getClass().getResource(janela+".fxml"));
        borderPane.setCenter(root);

    }

    public void chamaLivros(ActionEvent actionEvent) throws IOException {
        carregaJanela("livros");
    }

    public void chamaAutores(ActionEvent actionEvent) throws IOException {
        carregaJanela("autor");
    }

    public void chamaGeneros(ActionEvent actionEvent) throws IOException {
        carregaJanela("genero");
    }

    public void chamaUsuarios(ActionEvent actionEvent) throws IOException {
        carregaJanela("usuario");
    }

    public void chamaEditoras(ActionEvent actionEvent) throws IOException {
        carregaJanela("editora");
    }

    public void chamaEmprestimo(ActionEvent actionEvent) throws IOException {
        carregaJanela("emprestimo");
    }

    public void chamaMultas(ActionEvent actionEvent) throws IOException {
        carregaJanela("multas");
    }
}
