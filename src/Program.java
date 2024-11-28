import entities.Client;
import entities.Vehicle;
import entities.enums.Category;
import service.RentalService;

import java.sql.SQLException;
import java.time.LocalDate;

public class Program {
    public static void main(String[] args) {
        char codigoB = Category.BASIC.getCodigo();
        char codigoI = Category.INTERMEDIATE.getCodigo();
        char codigoL = Category.LUXURY.getCodigo();

        Client cliente = new Client(2, "Luis Augusto", "12345678910", LocalDate.of(2000, 5, 15));
        Vehicle veiculo = new Vehicle(2, "ABC1234", "Fiat", "Uno", "Prata", 2019, codigoL);

        LocalDate dataLocacao = LocalDate.of(2024, 11, 28);
        LocalDate dataDevolucao = LocalDate.of(2024, 12, 3);

        try {
            RentalService.rentalRegister(cliente, veiculo, dataLocacao, dataDevolucao);
        } catch (IllegalArgumentException e) {
            System.err.println("Erro na locação: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}
