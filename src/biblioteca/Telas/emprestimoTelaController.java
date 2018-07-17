package biblioteca.Telas;

import biblioteca.control.EmprestimoController;
import biblioteca.control.LivroController;
import biblioteca.control.UsuarioAlunoController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Emprestimo;
import biblioteca.model.livros.Livro;
import biblioteca.model.usuarios.UsuarioAluno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class emprestimoTelaController implements Initializable {

    @FXML
    public TextField nomeUsuario;

    @FXML
    public TextField codLivro;

    private ArrayList<UsuarioAluno> users;

    private List<Emprestimo> emprestimos;

    @FXML
    private javafx.scene.control.TableView<UsuarioAluno> tabelaUsuarios;

    @FXML
    private TableColumn<UsuarioAluno, String> colunaIDU;

    @FXML
    private TableColumn<UsuarioAluno, String> colunaNomeU;

    @FXML
    private TableView<Emprestimo> tabelaEmprestimo;

    @FXML
    private TableColumn<Emprestimo, String> colunaID;

    @FXML
    private TableColumn<Emprestimo, String> colunaUsuario;

    @FXML
    private TableColumn<Emprestimo, String> colunaLivro;

    @FXML
    private TableColumn<Emprestimo, String> colunaStatus;

    @FXML
    private TableColumn<Emprestimo, String> colunaData;

    ObservableList<Emprestimo> data = FXCollections.observableArrayList();
    ObservableList<UsuarioAluno> data2 = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colunaNomeU.setCellValueFactory(new PropertyValueFactory<UsuarioAluno, String>("nome"));
        colunaIDU.setCellValueFactory(new PropertyValueFactory<UsuarioAluno, String>("idUsuario"));

        colunaID.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("idEmprestimo"));
        colunaUsuario.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("usuario"));
        colunaLivro.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("livro"));
        colunaStatus.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("status"));
        colunaData.setCellValueFactory(new PropertyValueFactory<Emprestimo, String>("dataDevolucao"));


    }

    public void buscaUsuario(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {

        data2.removeAll(data2);

        System.out.println(data2.size());

        tabelaUsuarios.getItems().clear();

        UsuarioAlunoController controleUsuario= new UsuarioAlunoController();

        users= (ArrayList<UsuarioAluno>) controleUsuario.buscar(nomeUsuario.getText());

        for (UsuarioAluno a: users) {
            data2.add(a);
        }

        tabelaUsuarios.setItems(data2);

        tabelaUsuarios.refresh();
    }

    public void cadastraEmprestimo(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        //tabelaCargos.getSelectionModel().getSelectedItem();

        EmprestimoController controleEmpre = new EmprestimoController();

        LocalDate dataEmprestimo = LocalDate.now();//data atual
        LocalDate dataDevolucao = dataEmprestimo.plusDays(15); //adiciona 15 dias a data atual

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LivroController l = new LivroController();

        Livro livro = l.buscarID(Integer.parseInt(codLivro.getText()));
        UsuarioAluno u = tabelaUsuarios.getSelectionModel().getSelectedItem();

        System.out.println(livro.getTitulo());
        System.out.println(u.getNome());
        System.out.println(dataEmprestimo.format(formato));

        controleEmpre.cadastrar(0,dataEmprestimo.format(formato),dataDevolucao.format(formato),livro.getTitulo(),u.getNome(),true);
    }

    public void lista() throws ClassNotFoundException, SQLException, BancoException, ParseException {
        data.removeAll(data);

        //System.out.println(data.size());

        tabelaEmprestimo.getItems().clear();


        EmprestimoController controleEmpre = new EmprestimoController();

        emprestimos = controleEmpre.listar();

        for (Emprestimo a: emprestimos) {

            data.add(a);
        }

        //System.out.println(data.size());
        tabelaEmprestimo.setItems(data);

        tabelaEmprestimo.refresh();
    }

    public void listaTudo(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException, ParseException {
        lista();
    }

    public void devolve(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException, ParseException {
        Emprestimo emp = tabelaEmprestimo.getSelectionModel().getSelectedItem();
        EmprestimoController controleEmpre = new EmprestimoController();
        controleEmpre.atualiza(emp.getIdEmprestimo());

        lista();
    }
}
