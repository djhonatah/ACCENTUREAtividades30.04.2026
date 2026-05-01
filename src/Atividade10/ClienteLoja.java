package Atividade10;

public class ClienteLoja {

    private String id;
    private String nome;

    public ClienteLoja(String id, String nome) {
        this.id = validarTexto(id, "ID do cliente");
        this.nome = validarTexto(nome, "nome do cliente");
    }

    // METODOS - VALIDACAO
    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("ERRO! O " + campo + " nao pode ser vazio.");
        }
        return valor.trim();
    }

    // GETTERS E SETTERS
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return "Cliente: " + id + " - " + nome;
    }
}
