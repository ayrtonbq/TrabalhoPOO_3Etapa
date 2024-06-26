package model.dao;

import model.Animal;
import model.Proprietario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public Animal buscarPorId(int id) {
        String sql = "SELECT * FROM Animal WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Animal animal = new Animal();
                    animal.setId(rs.getInt("id"));
                    animal.setNome(rs.getString("nome"));
                    animal.setEspecie(rs.getString("especie"));
                    animal.setRaca(rs.getString("raca"));
                    animal.setIdade(rs.getInt("idade"));
                    animal.setSexo(rs.getString("sexo").charAt(0));
                    animal.setPeso(rs.getDouble("peso"));
                    animal.setFoto(rs.getBytes("foto"));
                    Proprietario proprietario = new Proprietario();
                    proprietario.setId(rs.getInt("proprietario_id"));
                    animal.setProprietario(proprietario);
                    return animal;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void atualizar(Animal animal) {
        String sql = "UPDATE Animal SET nome = ?, especie = ?, raca = ?, idade = ?, sexo = ?, peso = ?, foto = ?, proprietario_id = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getIdade());
            stmt.setString(5, String.valueOf(animal.getSexo()));
            stmt.setDouble(6, animal.getPeso());
            stmt.setBytes(7, animal.getFoto());
            stmt.setInt(8, animal.getProprietario().getId());
            stmt.setInt(9, animal.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Animal WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
