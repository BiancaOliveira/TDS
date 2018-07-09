/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biblioteca.model.dao.postgre;

import biblioteca.exception.BancoException;
import biblioteca.model.dao.CargoDAO;
import biblioteca.model.usuarios.Cargo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 *
 * @author Bianca
 */
public class PostgreCargoDAO<C extends Cargo> 
        extends PostgreDAO
        implements CargoDAO<C>{
    
    private boolean inserir(Cargo cargo) 
            throws SQLException, BancoException {
        String sql = "INSERT INTO cargo (nome) "
                + " VALUES (?, ?)";
        PreparedStatement stmt = getConnection()
                .prepareStatement(sql);
        stmt.setString(1, cargo.getNome());
        int rs = stmt.executeUpdate();
        if (rs == 1) {//1 = sucesso, 0 = nao inseriu
            return true;
        }
        return false;
    }
    
    private boolean atualizar(Cargo cargo)
            throws BancoException {
        String sql = "UPDATE clientepf "
                + " SET  nome=? "
                + " WHERE id=?";
        try {
            PreparedStatement stmt = getConnection()
                    .prepareStatement(sql);
            stmt.setString(1, cargo.getNome());
            stmt.setInt(2, cargo.getId());//where
            int rs = stmt.executeUpdate();
            if (rs == 1)
                return true;
        } catch(Exception ex) {
            throw new BancoException(ex);
        }
        return false;
    }

    /**
     * Salva o cargo na tabela Cliente
     * @param cargo
     * @return
     * @throws BancoException 
     */
   
    @Override
    public boolean salvar(C cargo) throws BancoException {
        cargo = buscar(cargo.getId());
        try {
            if (cargo != null) {//atualizar
                return atualizar(cargo);
            } else {//inserir
                return inserir(cargo);
            }
        } catch (Exception ex) {
            throw new BancoException(ex);
        }
    }
    
//    /**
//     * Exclui o cliente da tabela Cliente
//     * @param cliente
//     * @return
//     * @throws BancoException 
//     */
//    
//     @Override
//    public boolean excluir(C t) throws BancoException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//    
    /**
     * Constrói um objeto Cargo a partir de um ResultSet
     * @param rs Result set contendo a linha que será usada
     * @return Cargo com os campos do rs
     * @throws SQLException Se alguma coluna não for encontrada
     */
    private Cargo getInstance(ResultSet rs) 
            throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        Cargo cargo = new Cargo(id, nome);

        return cargo;
    }
    
    /**
     * Busca o cliente pelo codigo nas tabelas Cargo
     * @param id Código do cliente
     * @return O cargo encontrato ou null
     * @throws BancoException 
     */

    @Override
    public C buscar(int id) throws BancoException {
        C cargo = null;
        try {
            PreparedStatement stmt = 
                    getConnection().prepareStatement(
                            " select * from cargo"
                           + " where id="+id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cargo = (C) getInstance(rs);
            }
        } catch(SQLException ex) {
            throw new BancoException(ex);
        }
        return cargo;
    }


    @Override
    public boolean excluir(C t) throws BancoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Set<C> listarTodos() throws BancoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
