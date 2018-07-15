/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.postgre.PostgreDAO;
import biblioteca.model.usuarios.Cargo;
import biblioteca.model.usuarios.Usuario;
import biblioteca.model.usuarios.UsuarioAdm;
import biblioteca.model.usuarios.UsuarioAluno;
import biblioteca.model.usuarios.UsuarioFuncionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Bianca
 */
public class LoginDAO {
    /**
     * Busca um Usuario na tabela Usuario 
     * @param nome usuario do usuario
     * @param  senha senha do usuario
     * @return  true se usuario e senha são validos, caso contrario, retorna false
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */    
    public static boolean login(String nome , String senha) throws BancoException, ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE login='"+ nome + "' and senha= '"+ senha +"';";   
        
        boolean retorno = false;
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                retorno = true;
            }
        }catch(SQLException ex){
            if(retorno == false){
                JOptionPane.showMessageDialog(null,"Usuario e senha não compativeis");
            }else{
                JOptionPane.showMessageDialog(null,"Erro"); 
            }   
        }
        return retorno;
    }
    
    /**
     * Busca qual é o tipo de usuario atravez do idUsuario da tabela Usuario 
     * @param usuario usuario do usuario
     * @return  1 - Adiministrador, 2- Aluno e 3 -Funcionario
     * @throws biblioteca.exception.BancoException Exeção geral do banco
     * @throws java.lang.ClassNotFoundException Exeçõe conexao(driver)
     * @throws java.sql.SQLException    Exeções Sql
     */   
    public static int tipoUsuario(String usuario) throws BancoException, ClassNotFoundException, SQLException{
        String sql = "SELECT * FROM \"Usuario\""
                    + " WHERE login='"+ usuario +"'";
        PreparedStatement stmt = PostgreDAO.getConnection().prepareStatement(sql);
        int tipo = 0;
        try{
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                int id = res.getInt("idUsuario");
                UsuarioAdm adm = UsuarioAdmDAO.buscarID(id);
                UsuarioAluno alu = UsuarioAlunoDAO.buscarID(id);
                UsuarioFuncionario func = UsuarioFuncionarioDAO.buscarID(id);
                if(adm != null){
                    tipo = 1;      //Adiministrador            
                }else if(alu != null){
                    tipo = 2;     //Aluno 
                }else if(func != null){
                    tipo = 3;     //Funcionario 
                }
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro");   
        }
        return tipo;
    }
}