/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca;

import biblioteca.control.AutorController;
import biblioteca.control.CargoController;
import biblioteca.control.EditoraController;
import biblioteca.control.EmprestimoController;
import biblioteca.control.GeneroController;
import biblioteca.control.LivroController;
import biblioteca.control.LoginController;
import biblioteca.control.UsuarioAdmController;
import biblioteca.control.UsuarioAlunoController;
import biblioteca.control.UsuarioController;
import biblioteca.control.UsuarioFuncionarioController;
import biblioteca.exception.BancoException;
import biblioteca.model.dao.UsuarioAdmDAO;
import biblioteca.model.dao.UsuarioAlunoDAO;
import biblioteca.model.dao.UsuarioFuncionarioDAO;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.usuarios.Cargo;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Date;
/**
 *
 * @author Bianca
 */
public class Main extends Application{

    

    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent p = FXMLLoader.load(getClass().getResource("Telas/cargo.fxml"));
        //Parent p = FXMLLoader.load(getClass().getResource("Telas/Login.fxml"));
        Scene scene = new Scene(p);
        primaryStage.setScene(scene);
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Biblioteca");

        //Para tela inicial


        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    
    static Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private static boolean retorno = false;
    
    public static void main(String[] args) throws BancoException, ClassNotFoundException, SQLException  {
        con = PostgreDAO.getConnection();

        System.out.println("\n---------- Funçoes do cargo ------------\n");
         //Funções Cargo
        CargoController controleCargos = 
                new CargoController();

          controleCargos.cadastrarCargo(88,"Administrador");

        
        //listar Cargos
        System.out.println("Lista de cargos: " + controleCargos.listarCargos());
        //busca cargo pelo login
        System.out.println("Buscar cargo 'Bibliotecario': " + controleCargos.buscarCargo("Administrador"));
//      //remover cargo
//        controleCargos.removerCargo(controleCargos.buscarCargo("Administrador"));
        
        System.out.println("\n---------- Funçoes do Genero ------------\n");
         //Funções Cargo
        GeneroController controleGenero = 
                new GeneroController();

          controleGenero.cadastrar(0,"terror");

        
        //listar Cargos
        System.out.println("Lista de generos: " + controleGenero.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleGenero.buscar("terror"));
//      //remover cargo
//        controleGenero.remover( controleGenero.buscar("terror"));
        
        System.out.println("\n---------- Funçoes do Autor ------------\n");
         //Funções Cargo
        AutorController controleAutor = 
                new AutorController();

          controleAutor.cadastrar(0,"bart");
          controleAutor.cadastrar(0,"bart sasa");
          controleAutor.cadastrar(0,"bart boi");

        
        //listar Cargos
        System.out.println("Lista de generos: " + controleAutor.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleAutor.buscar("bart"));
//      //remover cargo
        
        
        System.out.println("\n---------- Funçoes do Editora ------------\n");
         //Funções Cargo
        EditoraController controleEditora = 
                new EditoraController();

          controleEditora.cadastrar(0,"teste");

        //listar Cargos
        System.out.println("Lista : " + controleEditora.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleEditora.buscar("teste"));
//      //remover cargo
//        controleEditora.remover( controleEditora.buscar("teste"));
        
        System.out.println("\n---------- Funçoes do Usuario ------------\n");
         //Funções Cargo
        UsuarioController controleUsuario= 
                new UsuarioController();

          controleUsuario.cadastrar(0,"Muito Quero chorar","mi","matar");

        
        //listar Cargos
        System.out.println("Lista : " + controleUsuario.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleUsuario.buscar("Quero"));
//      //remover cargo
//        controleUsuario.remover(controleUsuario.buscar("me"));

        System.out.println("\n---------- Funçoes do Adm ------------\n");
        UsuarioAdmController controleAdm = 
                new UsuarioAdmController();

          controleAdm.cadastrar(0,"Quero","muito","morrer","Administrador");
          controleAdm.cadastrar(0,"Quero","la","gente","Administrador");
        
        //listar Cargos
        System.out.println("Lista de generos: " + controleAdm.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleAdm.buscar("Quero"));
//        controleAdm.remover(controleAdm.buscar("la"));

    System.out.println("\n---------- Funçoes do Funcionario ------------\n");
        UsuarioFuncionarioController controleFun = 
                new UsuarioFuncionarioController();

          controleFun.cadastrar(0,"dadad","da","dada","Administrador");
          controleFun.cadastrar(0,"teste","te","ters","Administrador");
        
        //listar Cargos
        System.out.println("Lista de generos: " + controleFun.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleFun.buscar("teste"));
//        controleFun.remover(controleFun.buscar("te"));
    System.out.println("\n---------- Funçoes do Aluno ------------\n");
        UsuarioAlunoController controleAl = 
                new UsuarioAlunoController();

          controleAl.cadastrar(0,"faz de fas","fas","dada",4558,"9999999");
          controleAl.cadastrar(0,"faz de","afsa","dada",4558,"(45)9999999");        
        //listar Cargos
        System.out.println("Lista de generos: " + controleAl.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleAl.buscar("faz"));
//        controleAl.remover(controleAl.buscar("fas"));
    
    
     System.out.println("\n---------- Funçoes do livro ------------\n");
        LivroController controleLiv= 
                new LivroController();
        
        

          controleLiv.cadastrar(0,"terte",23,"dsadsa", "bart" ,"teste","terror","bart");
          controleLiv.cadastrar(0,"sadsa terte dfdaf",23,"dsadsa", "bart" ,"teste","terror","bart");
//        
        //listar Cargos
        System.out.println("Lista de generos: " + controleLiv.listar());
        //busca cargo pelo login
        System.out.println("Buscar : " + controleLiv.buscar("terte"));
//        controleLiv.remover(controleLiv.buscar("terte"));


        
        System.out.println("\n---------- Funçoes do login ------------\n");
        LoginController controleLogin = 
                new LoginController();
        
        boolean teste = controleLogin.login("muito", "matar");//ve se o usuario e senha tao ok
        System.out.println(teste);
        int teste1 = controleLogin.tipoLogin("muito");
        System.out.println(teste1);//      retorna o tipo de usiario 1 = adm, 2 = aluno, 3 = fun
        //cria um objto do tipo usuario certo
        if(teste1 == 1){
            UsuarioAdm adm = UsuarioAdmDAO.buscar("muito");
            //        se precisard de um certo campo é só fazer assim
            int id = adm.getIdUsuario();
        }else if(teste1 == 2){
            UsuarioAluno alu = UsuarioAlunoDAO.buscar("muito");
        }else if(teste1 == 3){
            UsuarioFuncionario fun = UsuarioFuncionarioDAO.buscar("muito");
        }
      
        System.out.println("\n---------- Funçoes do Emprestimo ------------\n");
            EmprestimoController controleEmpre = 
                    new EmprestimoController();

        Date dataEmprestimo  = new Date();
        Date dataDevolucao  = new Date();
        SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println(formato.format(dataEmprestimo));
        Calendar c = Calendar.getInstance(); 
        c.setTime(dataDevolucao); 
        c.add(Calendar.DATE, 15);
        dataDevolucao = c.getTime();
  
        System.out.println(formato.format(dataDevolucao));
    
        
        controleEmpre.cadastrar(0,dataEmprestimo,dataDevolucao,"BID","muito",true);
//          controleAl.cadastrar(0,"faz de","afsa","dada",4558,"(45)9999999");        
//        //listar Cargos
//        System.out.println("Lista de generos: " + controleAl.listar());
//        //busca cargo pelo login
//        System.out.println("Buscar : " + controleAl.buscar("faz"));
////        controleAl.remover(controleAl.buscar("fas"));
//    }
    }
}
