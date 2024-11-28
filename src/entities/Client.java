package entities;

import java.time.LocalDate;


public class Client {
    private int id;
    private String name;
    private String cpf;
    private LocalDate birthDate;

    public Client(int id, String name, String cpf, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public String getName() {
        return name;
    }
}
