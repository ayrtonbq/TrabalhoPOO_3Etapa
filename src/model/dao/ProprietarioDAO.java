package model.dao;

import model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProprietarioDAO {
    private Connection connection;

    public ProprietarioDAO() {
        this.connection = new Conexao().getConnection();
    }

    public void adiciona(Proprietario proprietario) {
        String sql = "INSERT INTO Proprietario (nome, endereco, telefone, email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, proprietario.getNome());
            stmt.setString(2, proprietario.getEndereco());
            stmt.setString(3, proprietario.getTelefone());
            stmt.setString(4, proprietario.getEmail());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos para ler, atualizar e deletar...
}
