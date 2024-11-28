package entities.enums;

public enum Category {
    BASIC('B', "Básico"),
    INTERMEDIATE('I', "Intermediário"),
    LUXURY('L', "Luxo");

    private final char codigo;
    private final String descricao;

    Category(char codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public char getCodigo() {
        return codigo;
    }

}