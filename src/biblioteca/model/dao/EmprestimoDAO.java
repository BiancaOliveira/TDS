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
import biblioteca.model.usuarios.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
public class EmprestimoDAO {
    /**
     * Busca o idEmprestimo na tabela Emprestimo
     * @return id pk do Emprestimo que vai ser utilizado para salvar na tabela Emprestimo
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql     */
    public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT MAX(\"idEmprestimos\") AS \"idEmprestimos\" from \"Emprestimos\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                id = res.getInt("idEmprestimos") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
//        System.out.println(id);
        return id + 1;
    }

    /**
     * Busca o idLivro na tabela Livro
     * @param nome nome da livro
     * @return idLivro
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int buscarIdLivro(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Livros\""
                    + " WHERE titulo='"+ nome + "'";   
        
        int idLivros = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                 idLivros = res.getInt("idLivros");
            }
        }catch(SQLException ex){
            if(idLivros == 0){
                JOptionPane.showMessageDialog(null,"Livro não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar livro"); 
            }   
        }
        return idLivros;
    }
    
    /**
     * Busca o idUsuario na tabela Usuario
     * @param nome nome da usuario
     * @return idUsuario
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int buscarIdUsuario(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE login ='"+ nome + "'";   
        
        int idUsuario = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                 idUsuario = res.getInt("idUsuario");
            }
        }catch(SQLException ex){
            if(idUsuario == 0){
                JOptionPane.showMessageDialog(null,"Usuario não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar usuario"); 
            }   
        }
        return idUsuario;
    }
    
    /**
     * Busca o numero de exemplares de um livro  
     * @param id id do livro
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int buscarNumeroExemplares(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Livros\""
                    + " WHERE \"idLivros\"="+ id ;   
        
        int numeroExemplares = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                
                numeroExemplares  = res.getInt("numeroExemplares");
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
 
        }
        return numeroExemplares;
    }
 
    /**
     * Busca o numero de exemplares disponiveis  
     * @param ob objeto
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static int buscarNumeroExemplaresDisponiveis(Emprestimo ob) throws BancoException, ClassNotFoundException, SQLException {
        int livro = buscarIdLivro(ob.livro);
        int numeroExemplares = buscarNumeroExemplares(livro);

        String sql = "SELECT * FROM \"Emprestimos\""
                    + " WHERE \"id_livros\"="+ livro ;      
        int exemplaresDisponiveis = 0;
        
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                exemplaresDisponiveis++;
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
 
        }

        return numeroExemplares-exemplaresDisponiveis;
    }
    
    /**
     * Inclui o Emprestimo na tabela Emprestimo 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */ 
    public  static void inserir(Emprestimo ob) throws BancoException, ClassNotFoundException, SQLException{
        int livro = buscarIdLivro(ob.livro);
        int usuario = buscarIdUsuario(ob.usuario);
        
//        System.out.println("Emp: " + ob.dataEmprestimo);
//        System.out.println("Dev: " + ob.getDataDevolucao());
        if(buscarNumeroExemplaresDisponiveis(ob) == 0){
            JOptionPane.showMessageDialog(null,"Livro sem exemplares disponiveis");
        }else{
            ob.setIdEmprestimo(codigo());
//            System.out.println(buscarNumeroExemplaresDisponiveis(ob));
            String sql = "INSERT INTO \"Emprestimos\" (\"idEmprestimos\", \"dataEmprestimo\", "
                    + "\"dataDevolucao\",id_livros, id_usuario, status)"
                    + "VALUES(" + ob.getIdEmprestimo()+ ",'" + ob.getDataEmprestimo()
                    + "','" +  ob.getDataDevolucao() + "'," + livro  + "," +usuario +","
                    + ob.getStatus() + ")";  
                  
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
                }

            }catch(SQLException ex){
                Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            }
        }
    }
    
     /**
     * Exclui o Emprestimo na tabela Emprestimo 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static void excluir(Emprestimo ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " Delete  FROM \"Emprestimos\""
                + "WHERE idEmprestimos =" + ob.getIdEmprestimo();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Removido com sucesso");
                }

            }catch(SQLException ex){
//                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                
                JOptionPane.showMessageDialog(null,"Erro ao remover");
            }
    }
   
     /**
     * Altera o Emprestimo na tabela Emprestimo 
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void alterar(Emprestimo ob) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  
        String sql = "UPDATE INTO \"Emprestimos\" SET dataDevolucao'" 
                + ob.getDataDevolucao() + "'," + "status" + ob.getStatus() 
                + " WHERE \"idEmprestimos\"='" + ob.getIdEmprestimo()+ "'";

        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);

        try{
            if (stmt.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            }

        }catch(SQLException ex){
//                    Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null,"Erro ao Alterar");
        }
    }  
    
    /**
     * Lista os Emprestimo na tabela Emprestimo 
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     * @throws java.text.ParseException Exeções do Date
     */
    public static List<Emprestimo> listar() throws BancoException, ClassNotFoundException, SQLException, ParseException{
        atualizaStatus();
        String sql = "SELECT * FROM \"Emprestimos\"";
               
        List<Emprestimo> retorno = new ArrayList<Emprestimo>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                Livro liv = LivroDAO.buscarID(res.getInt("id_livros"));
                Usuario usu = UsuarioDAO.buscarID(res.getInt("id_usuario"));
                int id = res.getInt("idEmprestimos");
                String livro = liv.getTitulo();
                String usuario= usu.getLogin();
                String dataEmprestimo = res.getString("dataEmprestimo");
                String dataDevolucao = res.getString("dataDevolucao");
                boolean status = res.getBoolean("status");
                Emprestimo item = new Emprestimo(id,dataEmprestimo,dataDevolucao,livro,usuario,status);
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
     * Constrói um objeto Emprestimo a partir de um ResultSet
     * @param rs Result set contendo a linha que será usada
     * @return objeto 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    private static Emprestimo getInstance(ResultSet res)
        throws SQLException, BancoException, ClassNotFoundException {

        Livro liv = LivroDAO.buscarID(res.getInt("id_livros"));
        Usuario usu = UsuarioDAO.buscarID(res.getInt("id_usuario"));
        int id = res.getInt("idEmprestimos");
        String livro = liv.getTitulo();
        String usuario= usu.getLogin();
        String dataEmprestimo = res.getString("dataEmprestimo");
        String dataDevolucao = res.getString("dataDevolucao");
        boolean status = res.getBoolean("status");
        Emprestimo item = new Emprestimo(id,dataEmprestimo,dataDevolucao,livro,usuario,status);
        return item;
    }
    
    /**
     * Busca um Emprestimo na tabela Emprestimo 
     * @param id id do emprestimo
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static Emprestimo buscarID(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Emprestimos\""
                    + " WHERE \"idEmprestimos\"="+ id;   
        
        Emprestimo item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                item = getInstance(res);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Item não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
                        Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);

        }
        return item;
    }
     /**
     * Atualiza a status do emprestimo 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     * @throws java.text.ParseException  Exeções do Date 
     */
    public static void atualizaStatus() throws BancoException, ClassNotFoundException, SQLException, ParseException{
        String sql = "SELECT * FROM \"Emprestimos\"";
        
        boolean status;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                SimpleDateFormat formato= new SimpleDateFormat("dd/MM/yyyy");
                Date data = formato.parse(res.getString("dataDevolucao"));             
                Date dataAtual = new Date();//data atual
        
                if(data.compareTo(dataAtual)>= 0){
                    status = true;
                }else{
                    status = false;
                }
                atualizaTabela(res.getInt("idEmprestimos"),status);
            }
        }catch(SQLException ex){
            System.out.println("Erro ao atualizar status"); 
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
        }      
    }
     /**
     * Atualiza a tabela Emprestimo 
     * @param id pk da tabela Emprestimos
     * @param status satatus do emprestimo
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void atualizaTabela(int id, boolean status) throws BancoException, ClassNotFoundException, SQLException{
//        con = PostgreDAO.getConnection();  

        String sql = "UPDATE  \"Emprestimos\" SET status =" + status 
                + " WHERE \"idEmprestimos\"=" + id;

        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);

        try{
            stmt.executeUpdate();
//                System.out.println("atualizado com sucesso");
        }catch(SQLException ex){
            Logger.getLogger(EmprestimoDAO.class.getName()).log(Level.SEVERE,null,ex);
            System.out.println("atulizado ao Alterar");
        }
    } 
     /**
     * Busca Editora na tabela Editora 
     * @param nome nome da editora(parte do nome)
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<Emprestimo> buscarVarios(String nome) throws BancoException, ClassNotFoundException, SQLException {
        int livro = buscarIdLivro(nome);
        
        String sql = "SELECT * FROM \"Emprestimos\""
                    + " WHERE id_livros = "+ livro ;   
        
        Emprestimo item = null;
        List<Emprestimo> retorno = new ArrayList<Emprestimo>();

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
                JOptionPane.showMessageDialog(null,"Emprestimo não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return retorno;
    }   

}
