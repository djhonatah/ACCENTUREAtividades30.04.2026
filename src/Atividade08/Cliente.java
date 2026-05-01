package Atividade08;

public class Cliente {

    private String nome;
    private String cpf;
    private String sobrenome;

    public Cliente(String nome, String cpf, String sobrenome) {
        this.nome = validarNome(nome);
        this.cpf = validarCpf(cpf);
        this.sobrenome = validarSobrenome(sobrenome);
    }

    // METODOS - VALIDACAO

    private String validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("ERRO! O nome do cliente não pode ser vazio.");
        }
        return nome;
    }

    private String validarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("ERRO! O CPF deve conter exatamente 11 digitos numericos.");
        }
        return cpf;
    }

    private String validarSobrenome(String sobrenome) {
        if (sobrenome == null || sobrenome.trim().isEmpty()) {
            throw new IllegalArgumentException("ERRO! O sobrenome do cliente não pode ser vazio.");
        }
        return sobrenome;
    }

    // GETTERS E SETTERS

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = validarNome(nome);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = validarCpf(cpf);
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = validarSobrenome(sobrenome);
    }

    public String getNomeCompleto() {
        return this.nome + " " + this.sobrenome;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNomeCompleto() + " | CPF: " + cpf;
    }
}
