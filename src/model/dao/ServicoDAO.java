package model.dao;

import model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            throw new RuntimeException("Erro ao inserir o serviço: " + e.getMessage());
        }
    }

    public void atualiza(Servico servico) {
        String sql = "UPDATE Servico SET nome = ?, descricao = ?, preco = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, servico.getNome());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getPreco());
            stmt.setInt(4, servico.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o serviço: " + e.getMessage());
        }
    }

    public void deleta(int id) {
        String sql = "DELETE FROM Servico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o serviço: " + e.getMessage());
        }
    }

    public Servico buscaPorId(int id) {
        String sql = "SELECT * FROM Servico WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extraiServico(rs);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar o serviço por ID: " + e.getMessage());
        }
    }

    public List<Servico> listaTodos() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Servico servico = extraiServico(rs);
                servicos.add(servico);
            }
            return servicos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar os serviços: " + e.getMessage());
        }
    }

    private Servico extraiServico(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String descricao = rs.getString("descricao");
        double preco = rs.getDouble("preco");
        return new Servico(id, nome, descricao, preco);
    }
}
