package service;

import config.DatabaseConstants;
import entities.Client;
import entities.Vehicle;

import java.sql.*;
import java.time.LocalDate;

public class RentalReturn {
    public static void rentalReturn(Client cliente, Vehicle veiculo, LocalDate returnDate) throws SQLException {
        if (returnDate == null) {
            throw new IllegalArgumentException("A data de devolução não pode ser nula.");
        }

        try (Connection conn = DriverManager.getConnection(DatabaseConstants.JDBC_URL, DatabaseConstants.JDBC_USER, DatabaseConstants.JDBC_PASSWORD)) {

            String sqlUpdateAvailability = "UPDATE veiculos SET disponivel = TRUE WHERE id = ?";
            try (PreparedStatement stmtUpdateAvailability = conn.prepareStatement(sqlUpdateAvailability)) {
                stmtUpdateAvailability.setInt(1, veiculo.getId());
                int rowsAffected = stmtUpdateAvailability.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Disponibilidade do veículo atualizada para 'disponível'.");
                } else {
                    System.out.println("Erro ao atualizar a disponibilidade do veículo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao processar a devolução do veículo no banco de dados.");
        }
    }
}
