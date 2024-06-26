package model.dao;

import model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            throw new RuntimeException("Erro ao inserir proprietário: " + e.getMessage());
        }
    }

    public void atualiza(Proprietario proprietario) {
        String sql = "UPDATE Proprietario SET nome = ?, endereco = ?, telefone = ?, email = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, proprietario.getNome());
            stmt.setString(2, proprietario.getEndereco());
            stmt.setString(3, proprietario.getTelefone());
            stmt.setString(4, proprietario.getEmail());
            stmt.setInt(5, proprietario.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar proprietário: " + e.getMessage());
        }
    }

    public void deleta(int id) {
        String sql = "DELETE FROM Proprietario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir proprietário: " + e.getMessage());
        }
    }

    public Proprietario buscaPorId(int id) {
        String sql = "SELECT * FROM Proprietario WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Proprietario proprietario = new Proprietario();
                proprietario.setId(rs.getInt("id"));
                proprietario.setNome(rs.getString("nome"));
                proprietario.setEndereco(rs.getString("endereco"));
                proprietario.setTelefone(rs.getString("telefone"));
                proprietario.setEmail(rs.getString("email"));
                return proprietario;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar proprietário por ID: " + e.getMessage());
        }
        return null; // Retorna null se não encontrar o proprietário
    }

    // Métodos para listagem, outras consultas, se necessário...
}
