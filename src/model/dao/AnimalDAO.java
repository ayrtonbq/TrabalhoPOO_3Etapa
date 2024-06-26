package model.dao;

import model.Animal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AnimalDAO {
    private Connection connection;

    public AnimalDAO() {
        this.connection = new Conexao().getConnection();
    }

    public void adiciona(Animal animal) {
        String sql = "INSERT INTO Animal (nome, especie, raca, idade, sexo, peso, foto, proprietario_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getIdade());
            stmt.setString(5, String.valueOf(animal.getSexo()));
            stmt.setDouble(6, animal.getPeso());
            stmt.setBytes(7, animal.getFoto());
            stmt.setInt(8, animal.getProprietario().getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Métodos para ler, atualizar e deletar...
}
