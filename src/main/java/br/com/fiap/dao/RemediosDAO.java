package br.com.fiap.dao;

import br.com.fiap.beans.Remedios;
import br.com.fiap.conexao.ConexaoFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RemediosDAO {

    private Connection minhaConexao;

    public RemediosDAO() throws SQLException, ClassNotFoundException {
        this.minhaConexao = new ConexaoFactory().conexao();
    }

    // INSERT
    public String inserir(Remedios remedios) throws SQLException {
        String sql = "INSERT INTO REMEDIOS (NOME_REMEDIO, DESCRICAO_REMEDIO, PRECO_REMEDIO, QUANTIDADE_REMEDIO) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, remedios.getNome_remedio());
            stmt.setString(2, remedios.getDescricao_remedio());
            stmt.setDouble(3, remedios.getPreco_remedio());
            stmt.setString(4, remedios.getQuantidade_remedio());
            stmt.executeUpdate();
            return "Remédio cadastrado com sucesso ✅";
        }
    }

    // DELETE
    public String deletar(int idRemedios) throws SQLException {
        String sql = "DELETE FROM REMEDIOS WHERE ID_REMEDIO = ?";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setInt(1, idRemedios);
            stmt.executeUpdate();
            return "Remédio deletado com sucesso ✅";
        }
    }

    // UPDATE
    public String atualizar(Remedios remedios) throws SQLException {
        String sql = "UPDATE REMEDIOS SET NOME_REMEDIO = ?, DESCRICAO_REMEDIO = ?, PRECO_REMEDIO = ? WHERE ID_REMEDIO = ?";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql)) {
            stmt.setString(1, remedios.getNome_remedio());
            stmt.setString(2, remedios.getDescricao_remedio());
            stmt.setDouble(3, remedios.getPreco_remedio());
            stmt.setInt(4, remedios.getId_remedio());
            stmt.executeUpdate();
            return "Remédio atualizado com sucesso ✅";
        }
    }

    // SELECT - TODOS
    public List<Remedios> selecionar() throws SQLException {
        List<Remedios> listRemedios = new ArrayList<>();
        String sql = "SELECT * FROM REMEDIOS";

        try (PreparedStatement stmt = minhaConexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Remedios objRemedios = new Remedios();
                objRemedios.setId_remedio(rs.getInt("ID_REMEDIO"));
                objRemedios.setNome_remedio(rs.getString("NOME_REMEDIO"));
                objRemedios.setDescricao_remedio(rs.getString("DESCRICAO_REMEDIO"));
                objRemedios.setPreco_remedio(rs.getDouble("PRECO_REMEDIO"));
                objRemedios.setQuantidade_remedio(rs.getString("QUANTIDADE_REMEDIO"));
                listRemedios.add(objRemedios);
            }
        }

        return listRemedios;
    }

    // SELECT - POR ID
    public Remedios buscarPorId(int idRemedio) throws SQLException, ClassNotFoundException {
        String sql = "SELECT ID_REMEDIO, NOME_REMEDIO, DESCRICAO_REMEDIO, PRECO_REMEDIO, QUANTIDADE_REMEDIO FROM REMEDIOS WHERE ID_REMEDIO = ?";

        try (Connection conn = new ConexaoFactory().conexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idRemedio);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Remedios remedio = new Remedios();
                    remedio.setId_remedio(rs.getInt("ID_REMEDIO"));
                    remedio.setNome_remedio(rs.getString("NOME_REMEDIO"));
                    remedio.setDescricao_remedio(rs.getString("DESCRICAO_REMEDIO"));
                    remedio.setPreco_remedio(rs.getDouble("PRECO_REMEDIO"));
                    remedio.setQuantidade_remedio(rs.getString("QUANTIDADE_REMEDIO"));
                    return remedio;
                }
            }
        }

        return null; // Caso não encontre
    }

    // ENCERRAR CONEXÃO
    public void fecharConexao() throws SQLException {
        if (minhaConexao != null && !minhaConexao.isClosed()) {
            minhaConexao.close();
        }
    }
}
