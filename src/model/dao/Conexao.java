package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/PetShop", "root", "senh@nova123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

