package biblioteca.Telas;

import biblioteca.control.AutorController;
import biblioteca.control.EditoraController;
import biblioteca.control.GeneroController;
import biblioteca.control.LivroController;
import biblioteca.exception.BancoException;
import biblioteca.model.livros.Autor;
import biblioteca.model.livros.Editora;
import biblioteca.model.livros.Genero;
import biblioteca.model.livros.Livro;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class livrosTelaController implements Initializable {

    @FXML
    public TextField titulo;

    @FXML
    public ComboBox<Editora> editora;

    @FXML
    public ComboBox<Autor> autor;

    @FXML
    public ComboBox<Autor> coautor;

    @FXML
    public ComboBox<Genero> genero;

    @FXML
    public TextField exemplares;

    @FXML
    public TextArea descricao;

    @FXML
    private TextField campoBuscaLivro;

    private ArrayList<Livro> livros;

    @FXML
    private javafx.scene.control.TableView<Livro> tabelaLivro;

    @FXML
    private TableColumn<Livro, Integer> colunaID;

    @FXML
    private TableColumn<Livro, String> colunaTitulo;

    @FXML
    private TableColumn<Livro, String> colunaAutor;

    @FXML
    private TableColumn<Livro, String> colunaGenero;

    @FXML
    private TableColumn<Livro, String> colunaEditora;

    ObservableList<Livro> data = FXCollections.observableArrayList();

    ObservableList<Editora> editoras = FXCollections.observableArrayList();
    ObservableList<Autor> autores = FXCollections.observableArrayList();
    ObservableList<Autor> coautores = FXCollections.observableArrayList();
    ObservableList<Genero> generos = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            carregaCombos();
        } catch (BancoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        colunaTitulo.setCellValueFactory(new PropertyValueFactory<Livro, String>("titulo"));
        colunaID.setCellValueFactory(new PropertyValueFactory<Livro, Integer>("idLivro"));
        colunaAutor.setCellValueFactory(new PropertyValueFactory<Livro, String>("autor"));
        colunaGenero.setCellValueFactory(new PropertyValueFactory<Livro, String>("genero"));
        colunaEditora.setCellValueFactory(new PropertyValueFactory<Livro, String>("editora"));

    }

    public void carregaCombos() throws BancoException, SQLException, ClassNotFoundException {
        EditoraController controleEditora = new EditoraController();
        ArrayList<Editora> editoras2 = (ArrayList<Editora>) controleEditora.listar();

        for (Editora a: editoras2) {
            editoras.add(a);
        }

        editora.setItems(editoras);

        AutorController controleAutor = new AutorController();
        ArrayList<Autor> autores2 = (ArrayList<Autor>) controleAutor.listar();
        coautores.add(new Autor(0, "-"));

        for (Autor a: autores2) {
            autores.add(a);
            coautores.add(a);
        }

        autor.setItems(autores);
        coautor.setItems(coautores);

        GeneroController controleGenero = new GeneroController();
        ArrayList<Genero> generos1 = (ArrayList<Genero>) controleGenero.listar();

        for (Genero a: generos1) {
            generos.add(a);
        }

        genero.setItems(generos);


    }

    public void buscaLivro(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        data.removeAll(data);

        //System.out.println(data.size());

        tabelaLivro.getItems().clear();

        LivroController controleLivro = new LivroController();

        livros= (ArrayList<Livro>) controleLivro.buscar(campoBuscaLivro.getText());

        for (Livro a: livros) {
            data.add(a);
        }

        //System.out.println(data.size());
        tabelaLivro.setItems(data);

        tabelaLivro.refresh();
    }

    public void alteraLivro(ActionEvent actionEvent) throws IOException {
        Livro a = tabelaLivro.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("alteraLivro.fxml"));
        fxml.setController(new alteraLivroController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();
    }

    public void deletaLivro(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        LivroController controleLivro = new LivroController();

        Livro a = tabelaLivro.getSelectionModel().getSelectedItem();

        controleLivro.remover(a);
        listaTudo();
    }

    public void listaLivros(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        listaTudo();
    }

    public void cadastra(ActionEvent actionEvent) throws BancoException, SQLException, ClassNotFoundException {
        LivroController controleLivro = new LivroController();

        if(coautor.getSelectionModel().getSelectedItem().getNome()=="-"){
            controleLivro.cadastrar(0,titulo.getText(),Integer.parseInt(exemplares.getText()),
                    descricao.getText(),autor.getSelectionModel().getSelectedItem().getNome(),editora.getSelectionModel().getSelectedItem().getNome(),
                    genero.getSelectionModel().getSelectedItem().getNome(), autor.getSelectionModel().getSelectedItem().getNome());
        }else{
            controleLivro.cadastrar(0,titulo.getText(),Integer.parseInt(exemplares.getText()),
                    descricao.getText(),autor.getSelectionModel().getSelectedItem().getNome(),editora.getSelectionModel().getSelectedItem().getNome(),
                    genero.getSelectionModel().getSelectedItem().getNome(), coautor.getSelectionModel().getSelectedItem().getNome());
        }
    }

    public void maisInformacoes(ActionEvent actionEvent) throws IOException {
        Livro a = tabelaLivro.getSelectionModel().getSelectedItem();

        Stage stage = new Stage();

        FXMLLoader fxml = new FXMLLoader(getClass().getResource("maisInfo.fxml"));
        fxml.setController(new maisInfoController(a));

        Parent c = fxml.load();

        Scene scene = new Scene(c);
        stage.setScene(scene);
        stage.show();
    }

    public void listaTudo() throws BancoException, SQLException, ClassNotFoundException {
        data.removeAll(data);

        //System.out.println(data.size());

        tabelaLivro.getItems().clear();

        LivroController controleLivro = new LivroController();

        livros= (ArrayList<Livro>) controleLivro.listar();

        for (Livro a: livros) {
            data.add(a);
        }

        //System.out.println(data.size());
        tabelaLivro.setItems(data);

        tabelaLivro.refresh();
    }
}
