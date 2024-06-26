package model.dao;

import model.RegistroServico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.Date;

public class RegistroServicoDAO {
    private Connection connection;

    public RegistroServicoDAO() {
        this.connection = new Conexao().getConnection();
    }

    public void adiciona(RegistroServico registroServico) {
        String sql = "INSERT INTO RegistroServico (data, servico_id, animal_id, valor, observacoes) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new Date(registroServico.getData().getTime()));
            stmt.setInt(2, registroServico.getServico().getId());
            stmt.setInt(3, registroServico.getAnimal().getId());
            stmt.setDouble(4, registroServico.getValor());
            stmt.setString(5, registroServico.getObservacoes());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegistroServico> getHistoricoPorAnimal(int proprietarioId, java.util.Date dataInicio,
            java.util.Date dataFim) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getHistoricoPorAnimal'");
    }

    // Métodos para ler, atualizar e deletar...
}
