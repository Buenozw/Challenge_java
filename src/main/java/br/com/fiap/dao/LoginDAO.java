package br.com.fiap.dao;

import br.com.fiap.beans.Login;
import br.com.fiap.conexao.ConexaoFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginDAO {

    // Construtor vazio — a conexão será aberta e fechada dentro de cada método
    public LoginDAO() {}

    // Insert
    public String inserir(Login login) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO LOGIN (USUARIO, SENHA) VALUES (?, ?)";
        try (Connection conn = new ConexaoFactory().conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login.getUsuario());
            stmt.setString(2, login.getSenha());
            stmt.executeUpdate();

            return "Login cadastrado com sucesso ✅";
        }
    }

    // Delete
    public String deletar(int idLogin) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM LOGIN WHERE ID_LOGIN = ?";
        try (Connection conn = new ConexaoFactory().conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idLogin);
            stmt.executeUpdate();

            return "Login deletado com sucesso ✅!";
        }
    }

    // Update
    public String atualizar(Login login) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE LOGIN SET USUARIO = ?, SENHA = ? WHERE ID_LOGIN = ?";
        try (Connection conn = new ConexaoFactory().conexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, login.getUsuario());
            stmt.setString(2, login.getSenha());
            stmt.setInt(3, login.getIdLogin());
            stmt.executeUpdate();

            return "Login atualizado com sucesso ✅!";
        }
    }

    // Select
    public List<Login> selecionar() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM LOGIN";
        List<Login> listLogin = new ArrayList<>();

        try (Connection conn = new ConexaoFactory().conexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Login objLogin = new Login();
                objLogin.setUsuario(rs.getString("USUARIO"));
                objLogin.setSenha(rs.getString("SENHA"));
                objLogin.setIdLogin(rs.getInt("ID_LOGIN"));
                listLogin.add(objLogin);
            }
        }

        return listLogin;
    }
}
