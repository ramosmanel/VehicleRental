package service;

import entities.Client;
import entities.Vehicle;

import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class RentalService {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/locadora";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";

    public static boolean validateMajority(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears() >= 18;
    }

    public static boolean datesValidation(LocalDate dataLocacao, LocalDate dataDevolucao) {
        return dataDevolucao == null || !dataDevolucao.isBefore(dataLocacao);
    }

    public static void rentalRegister(Client cliente, Vehicle veiculo, LocalDate rentalDate, LocalDate returnDate) throws SQLException {
        if (!validateMajority(cliente.getBirthDate())) {
            throw new IllegalArgumentException("Cliente deve ser maior de 18 anos para realizar a locação.");
        }

        if (!datesValidation(rentalDate, returnDate)) {
            throw new IllegalArgumentException("A data de devolução não pode ser anterior à data de locação.");
        }

        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "INSERT INTO locacoes (cliente_id, veiculo_id, data_locacao, data_devolucao) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, cliente.getId());
                stmt.setInt(2, veiculo.getId());
                stmt.setDate(3, Date.valueOf(rentalDate));
                stmt.setDate(4, returnDate != null ? Date.valueOf(returnDate) : null);

                stmt.executeUpdate();
                System.out.println("Locação registrada com sucesso!");
            }

            String sqlupdateRental = "UPDATE veiculos SET disponivel = disponivel - 1 WHERE id = ?";
            try (PreparedStatement stmtAtualizaDisponibilidade = conn.prepareStatement(sqlupdateRental)) {
                stmtAtualizaDisponibilidade.setInt(1, veiculo.getId());
                int rowsAffected = stmtAtualizaDisponibilidade.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Quantidade de veículos disponíveis atualizada com sucesso.");
                } else {
                    System.out.println("Erro ao atualizar a disponibilidade do veículo.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Erro ao registrar locação no banco de dados.");
        }

    }
}
