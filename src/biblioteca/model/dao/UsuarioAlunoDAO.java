/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.usuarios.Usuario;
import biblioteca.model.usuarios.UsuarioAluno;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public class UsuarioAlunoDAO {
    /**
     * Busca o idUsuario na tabela Usuario
     * @return id pk do Usuario que vai ser utilizado para salvar na tabela Usuario
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */  
     public static int codigo() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT MAX(\"idUsuario\") AS \"idUsuario\" from \"Usuario\"";
        
        int id = 0;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if(res.next()){
                id = res.getInt("idUsuario") + 1;
            }         
        }catch(SQLException ex){
          Logger.getLogger("Erro");
        }
        return id;
    }
    
     /**
     * Inclui o UsuarioAluno na tabela Aluno e o Usuario na tabela Usuario
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void inserir(UsuarioAluno ob) throws BancoException, ClassNotFoundException, SQLException{
        if(buscar(ob.getLogin()) != null){
            JOptionPane.showMessageDialog(null,"Este usuário já esta sendo utilizado");
        }else{
            ob.setIdUsuario(codigo());
            String sql = "INSERT INTO \"Usuario\" (\"idUsuario\",usuario,login,senha)"
                    + "VALUES(" + ob.getIdUsuario() + ",'" + ob.getNome() + "'" 
                    + ",'" + ob.getLogin() + "'" + ",'" + ob.getSenha() + "');"
                    + "INSERT INTO \"Aluno\" (id_Usuario,\"numeroRegistro\",telefone)"
                    + "VALUES(" + ob.getIdUsuario() + "," + ob.getNumeroRegistro()
                    + ",'"+ ob.getTelefone() +"')";
            
            PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() == 1){
                    JOptionPane.showMessageDialog(null,"Cadastrado com sucesso");
                }
            }catch(SQLException ex){
//                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao cadastrar");
            }
        }
    }
    
    /**
     * Exclui o UsuarioAluno na tabela Aluno e o Usuario na tabela Usuario
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static void excluir(UsuarioAluno ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = " DELETE  FROM \"Aluno\""
                + "WHERE \"id_usuario\" =" + ob.getIdUsuario() +";"
                + " DELETE  FROM \"Usuario\""
                + "WHERE \"idUsuario\" =" + ob.getIdUsuario();
        
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
            try{
                if (stmt.executeUpdate() > 0){
                    JOptionPane.showMessageDialog(null,"Removido com sucesso");
                }
            }catch(SQLException ex){
//                Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao excluir, usuario associado a um emprestimo");
            }
    }
    
    /**
     * Altera o UsuarioAluno na tabela Aluno e o Usuario na tabela Usuario
     * @param ob objeto
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public  static void alterar(UsuarioAluno ob) throws BancoException, ClassNotFoundException, SQLException{
        String sql = "UPDATE \"Usuario\" SET usuario ='" + ob.getNome()
                + "', senha = " + ob.getSenha() 
                + " WHERE \"idUsuario\"=" + ob.getIdUsuario() + ";"
                +"UPDATE \"Aluno\" SET telefone= " + ob.getTelefone() + ""
                + " WHERE id_Usuario =" + ob.getIdUsuario();
        
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            if (stmt.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"Alterado com sucesso");
            }
        }catch(SQLException ex){
//                Logger.getLogger(CargoDAO.class.getName()).log(Level.SEVERE,null,ex);
            JOptionPane.showMessageDialog(null,"Erro ao Alterar");
        }
    }
    
    /**
     * Constrói um objeto UsuarioAluno a partir de um idLogin
     * @param idLogin id do Usuario na tabela Usuario
     * @return UsuarioFuncionario 
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
     private static UsuarioAluno getInstance(int idLogin) throws SQLException, BancoException, ClassNotFoundException {
        String sql = "SELECT * FROM \"Aluno\""
                    + " WHERE id_usuario='"+ idLogin + "';";   
        
        UsuarioAluno item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                Usuario usu = UsuarioDAO.buscarID(res.getInt("id_usuario"));
                int idUsuario = usu.getIdUsuario();
                String nome = usu.getNome();
                String login = usu.getLogin();
                String senha = "**********";
                int numeroRegistro = res.getInt("numeroRegistro");
                String telefone = res.getString("telefone");
                item = new UsuarioAluno(idUsuario,nome,login,senha,numeroRegistro,telefone);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
//                Logger.getLogger(UsuarioAdmDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
   
     /**
     * Lista todos os UsuarioFuncionario na tabela Funcionario atravez do Usuario na tabela Usuario 
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<UsuarioAluno> listar() throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Aluno\"";
        
        UsuarioAluno item = null;
        List<UsuarioAluno> retorno = new ArrayList<UsuarioAluno>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while(res.next()){
                item = getInstance(res.getInt("id_usuario"));
                retorno.add(item);
            }
        }catch(SQLException ex){
             if(retorno == null){
                JOptionPane.showMessageDialog(null,"Nenhum Usuário encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }
        }
        Collections.sort(retorno);
        return retorno;
    }

    /**
     * Busca por usuario um UsuarioAluno na tabela Aluno atravez do Usuario(login) na tabela Usuario
     * @param nome nome do login(user) do usuario
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static UsuarioAluno buscar(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE login='"+ nome + "'";   
        
        UsuarioAluno item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                int id = res.getInt("idUsuario");
//                System.out.println(id);
                item = getInstance(id);
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
//                Logger.getLogger(UsuarioAlunoDAO.class.getName()).log(Level.SEVERE,null,ex);
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }
    
    /**
      * Busca um id UsuarioAluno na tabela Aluno atravez do Usuario(login) na tabela Usuario
     * @param id id do usuario
     * @return um objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static UsuarioAluno buscarID(int id) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE \"idUsuario\"="+ id ;   
        
        UsuarioAluno item = null;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                int idUsuario = res.getInt("idUsuario");
                item = getInstance(idUsuario);            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        return item;
    }

     /**
     * Busca por nome(parte) UsuarioAluno na tabela Aluno atravez do Usuario na tabela Usuario 
     * @param nome nome do usuario
     * @return lista de objetos
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */
    public static List<UsuarioAluno> buscarVarios(String nome) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE usuario LIKE '%"+ nome + "%'";   
        
        UsuarioAluno item = null;
        List<UsuarioAluno> retorno = new ArrayList<UsuarioAluno>();
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("idUsuario");
                item = getInstance(id);
                if(item != null){
                    retorno.add(item);
                }
            }
        }catch(SQLException ex){
            if(item == null){
                JOptionPane.showMessageDialog(null,"Usuário não encontrado");
            }else{
                JOptionPane.showMessageDialog(null,"Erro ao buscar"); 
            }   
        }
        Collections.sort(retorno);
        return retorno;
    }
}
