package model.dao;

import model.RegistroServico;
import model.Animal;
import model.Servico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistroServicoDAO {
    private Connection connection;

    public RegistroServicoDAO() {
        this.connection = new Conexao().getConnection();
    }

    public void adiciona(RegistroServico registroServico) {
        String sql = "INSERT INTO RegistroServico (data, servico_id, animal_id, valor, observacoes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(registroServico.getData().getTime()));
            stmt.setInt(2, registroServico.getServico().getId());
            stmt.setInt(3, registroServico.getAnimal().getId());
            stmt.setDouble(4, registroServico.getValor());
            stmt.setString(5, registroServico.getObservacoes());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegistroServico> getHistoricoPorAnimal(int animalId, Date dataInicio, Date dataFim) {
        List<RegistroServico> historico = new ArrayList<>();
        String sql = "SELECT rs.id, rs.data, rs.servico_id, rs.valor, rs.observacoes, s.nome as nome_servico, a.nome as nome_animal " +
                     "FROM RegistroServico rs " +
                     "JOIN Servico s ON rs.servico_id = s.id " +
                     "JOIN Animal a ON rs.animal_id = a.id " +
                     "WHERE rs.animal_id = ? AND rs.data BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, animalId);
            stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
            stmt.setDate(3, new java.sql.Date(dataFim.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RegistroServico registro = new RegistroServico();
                registro.setId(rs.getInt("id"));
                registro.setData(rs.getDate("data"));
                registro.setValor(rs.getDouble("valor"));
                registro.setObservacoes(rs.getString("observacoes"));

                Servico servico = new Servico();
                servico.setId(rs.getInt("servico_id"));
                servico.setNome(rs.getString("nome_servico"));
                registro.setServico(servico);

                Animal animal = new Animal();
                animal.setNome(rs.getString("nome_animal"));
                registro.setAnimal(animal);

                historico.add(registro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return historico;
    }

    public List<RegistroServico> gerarRelatorio(int clienteId, Date dataInicio, Date dataFim) {
        List<RegistroServico> relatorio = new ArrayList<>();
        String sql = "SELECT rs.id, rs.data, rs.servico_id, rs.valor, rs.observacoes, s.nome as nome_servico, a.nome as nome_animal " +
                     "FROM RegistroServico rs " +
                     "JOIN Servico s ON rs.servico_id = s.id " +
                     "JOIN Animal a ON rs.animal_id = a.id " +
                     "JOIN Proprietario p ON a.proprietario_id = p.id " +
                     "WHERE p.id = ? AND rs.data BETWEEN ? AND ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.setDate(2, new java.sql.Date(dataInicio.getTime()));
            stmt.setDate(3, new java.sql.Date(dataFim.getTime()));
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                RegistroServico registro = new RegistroServico();
                registro.setId(rs.getInt("id"));
                registro.setData(rs.getDate("data"));
                registro.setValor(rs.getDouble("valor"));
                registro.setObservacoes(rs.getString("observacoes"));

                Servico servico = new Servico();
                servico.setId(rs.getInt("servico_id"));
                servico.setNome(rs.getString("nome_servico"));
                registro.setServico(servico);

                Animal animal = new Animal();
                animal.setNome(rs.getString("nome_animal"));
                registro.setAnimal(animal);

                relatorio.add(registro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return relatorio;
    }


}
