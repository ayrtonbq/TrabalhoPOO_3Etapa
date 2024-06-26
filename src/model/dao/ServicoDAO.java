package model.dao;

import model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicoDAO {
    private Connection connection;

    public ServicoDAO() {
        this.connection = new Conexao().getConnection();
    }

    public void adiciona(Servico servico) {
        String sql = "INSERT INTO Servico (nome, descricao, preco) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getPreco());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos para ler, atualizar e deletar...
}
