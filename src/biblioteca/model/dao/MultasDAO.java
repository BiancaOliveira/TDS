/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.livros.Emprestimo;
import biblioteca.model.livros.Livro;
import biblioteca.model.livros.Multas;
import biblioteca.model.usuarios.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public class MultasDAO {
    /**
     * Busca o idMultas na tabela Multas
     * @return id pk do Multas que vai ser utilizado para salvar na tabela Multas
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT MAX(\"idMultas\") AS \"idMultas\" from \"Multas\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                id = res.getInt("idMultas") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id + 1;
    }
    
    /**
     * Busca um Emprestimo na tabela Multas 
     * @param id id do emprestimo 
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static Multas buscarIdEmprestimos(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Multas\""
                    + " WHERE id_emprestimos="+ id;   
        
        Multas item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                item = getInstance(res);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao buscar");  
             Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }
        return item;
    }
    
    /**
     * Busca o taxa na tabela Multas
     * @return taxa da Multa 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    public static double taixa() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT MAX taxa AS taxa from \"Multas\"";
        
        double taxa = 0.50;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                taxa = res.getInt("taxa");
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return taxa;
    }
    
    /**
     * Calcula o valor da multa
     * @param id id do emprestimo
     * @return taxa da Multa 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    public static double calculaValor(int id) throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Emprestimos\""
                + " WHERE \"idEmprestimos\"= "+ id ;  
        
        DateTimeFormatter formato =
                        DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataAtual = LocalDate.now();//data atual 
        double valor = 0;       
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {              
                LocalDate dataDevolucao =  LocalDate.parse(res.getString("dataDevolucao"), formato);
                Period periodo = Period.between(dataDevolucao, dataAtual);
                valor = taixa() * periodo.getDays();
            }
        }catch(SQLException ex){
            System.out.println("Erro"); 
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }   
        return valor;
    }

    /**
     * Atualiza o valor das multas na tabela Multas 
     * @param id pk da tabela multas
     * @param vt valor da multa
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void atualizaValorTabela(int id, double vt) throws BancoException, ClassNotFoundException, SQLException{
        String sql = "UPDATE  \"Multas\" SET \"valorTotal\" =" + vt 
                + " WHERE id_emprestimos=" + id;

        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            stmt.executeUpdate();
        }catch(SQLException ex){
//            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("erro oa atualizar");
        }     
    }
    
    /**
     * Inclui a Multas na tabela Multas
     * @param res Result set contendo a linha que será usada
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */ 
    public  static void inserir(ResultSet res) throws BancoException, ClassNotFoundException, SQLException{         
       double taxa = taixa();
       double valor = calculaValor(res.getInt("idEmprestimos"));
        if(buscarIdEmprestimos(res.getInt("idEmprestimos")) == null){
            String sql = "INSERT INTO \"Multas\" (\"idMultas\",taxa, "
                    + "\"valorTotal\", id_usuario, status, id_emprestimos)"
                    + "VALUES(" + codigo()+ "," + taxa
                    + "," + valor + "," + res.getInt("id_usuario") + ","+ false +","
                    + res.getInt("idEmprestimos") + ")";  

            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
               stmt.executeUpdate();
            }catch(SQLException ex){
                Logger.getLogger(MultasDAO.class.getName()).log(Level.SEVERE,null,ex);
//                JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            }
        }else{
            atualizaValorTabela(res.getInt("idEmprestimos"), valor);
        }
    }
   
    /**
     * Constrói um objeto Multas a partir de um ResultSet
     * @param res Result set contendo a linha que será usada
     * @return objeto 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    private static Multas getInstance(ResultSet res) throws SQLException, BancoException, ClassNotFoundException {
        Emprestimo emp = EmprestimoDAO.buscarID(res.getInt("id_emprestimos"));
        Usuario usu = UsuarioDAO.buscarID(res.getInt("id_usuario"));
        int id = res.getInt("idMultas");
        String livro = emp.getLivro();
        String usuario= usu.getLogin();
        double taxa = res.getDouble("taxa");
        double valorTotal = res.getDouble("valorTotal");
        boolean status = res.getBoolean("status");
        Multas item = new Multas(id,taxa,valorTotal,usuario,status,livro);
      
        return item;
    }
    
     /**
     * Atualiza o valor da multa do emprestimo 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     * @throws java.text.ParseException  Exeções do Date 
     */
    public static void atualizaValor() throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Multas\""
                + " WHERE status = "+ false ;  
       
        double valorTotal;       
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {              
                valorTotal = calculaValor(res.getInt("id_emprestimos"));
                atualizaValorTabela(res.getInt("id_emprestimos"),valorTotal);
            }
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar status"); 
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }      
    }

    /**
     * Lista todas as multas na tabela Multas 
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Multas> listar() throws BancoException, ClassNotFoundException, SQLException{
        atualizaValor();
        String sql = "SELECT * FROM \"Multas\"";
        
        Multas item = null;
        List<Multas> retorno = new ArrayList<Multas>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                item = getInstance(res);
                retorno.add(item);
            }           
        }catch(SQLException ex){
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
            if(retorno == null){
                JOptionPane.showMessageDialog(null,"Nenhum item encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }
        }
        Collections.sort(retorno);
        return retorno;
    }
    
     /**
     * Busca por status as multas na tabela Multas 
     * @param status
     * @param nome nome da editora(parte do nome)
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Multas> buscarVarios(boolean status) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Multas\""
                    + " WHERE status = "+ status ;   
        
        Multas item = null;
        List<Multas> retorno = new ArrayList<Multas>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                item = getInstance(res);
                retorno.add(item);
            }
        }catch(SQLException ex){
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
            if(item == null){
                JOptionPane.showMessageDialog(null,"Multa não encontrada");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return retorno;
    }   


}
