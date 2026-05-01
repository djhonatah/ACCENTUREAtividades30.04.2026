package Atividade08;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContaCorrente {

    private int numero;
    private String nome;
    private double saldo;
    private String data;
    private Cliente cliente;
    private ArrayList<String> extrato;

    public ContaCorrente(int numero, Cliente cliente, double saldo) {
        this.numero = validarNumero(numero);
        this.cliente = validarCliente(cliente);
        this.saldo = validarSaldoInicial(saldo);
        this.nome = cliente.getNomeCompleto();
        this.data = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.extrato = new ArrayList<>();
        this.extrato.add("Conta criada com saldo inicial de R$ " + String.format("%.2f", saldo));
    }

    // METODOS - VALIDACAO

    private int validarNumero(int numero) {
        if (numero <= 0) {
            throw new IllegalArgumentException("ERRO! O numero da conta deve ser positivo.");
        }
        return numero;
    }

    private Cliente validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("ERRO! O cliente não pode ser nulo.");
        }
        return cliente;
    }

    private double validarSaldoInicial(double saldo) {
        if (saldo < 0) {
            throw new IllegalArgumentException("ERRO! O saldo inicial não pode ser negativo.");
        }
        return saldo;
    }

    private void validarValorPositivo(double valor, String operacao) {
        if (valor <= 0) {
            throw new IllegalArgumentException("ERRO! O valor do " + operacao + " deve ser maior que zero.");
        }
    }

    private void validarSaldoSuficiente(double valor) {
        if (valor > this.saldo) {
            throw new IllegalArgumentException("ERRO! Saldo insuficiente. Saldo atual: R$ "
                    + String.format("%.2f", this.saldo));
        }
    }

    private void validarDestino(ContaCorrente destino) {
        if (destino == null) {
            throw new IllegalArgumentException("ERRO! A conta de destino não pode ser nula.");
        }
        if (destino.getNumero() == this.numero) {
            throw new IllegalArgumentException("ERRO! Não é possível transferir para a mesma conta.");
        }
    }

    // METODOS

    public void depositar(double valor) {
        validarValorPositivo(valor, "deposito");
        this.saldo += valor;
        this.extrato.add("Deposito: + R$ " + String.format("%.2f", valor));
        System.out.println("Deposito de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
    }

    public void sacar(double valor) {
        validarValorPositivo(valor, "saque");
        validarSaldoSuficiente(valor);
        this.saldo -= valor;
        this.extrato.add("Saque: - R$ " + String.format("%.2f", valor));
        System.out.println("Saque de R$ " + String.format("%.2f", valor) + " realizado com sucesso!");
    }

    public void transferir(ContaCorrente destino, double valor) {
        validarDestino(destino);
        this.sacar(valor);
        destino.depositar(valor);
        this.extrato.add("Transferencia enviada para conta " + destino.getNumero()
                + ": - R$ " + String.format("%.2f", valor));
        destino.extrato.add("Transferencia recebida da conta " + this.numero
                + ": + R$ " + String.format("%.2f", valor));
        System.out.println("Transferencia de R$ " + String.format("%.2f", valor)
                + " para conta " + destino.getNumero() + " realizada!");
    }

    public void exibirExtrato() {

        System.out.println("         EXTRATO DA CONTA");
        System.out.println();
        System.out.println("Conta: " + this.numero);
        System.out.println("Titular: " + this.nome);
        System.out.println("Data de abertura: " + this.data);
        System.out.println();
        for (String movimentacao : this.extrato) {
            System.out.println("  " + movimentacao);
        }
        System.out.println("----------------------------------------");
        System.out.println("Saldo atual: R$ " + String.format("%.2f", this.saldo));
    }

    // GETTERS

    public int getNumero() {
        return numero;
    }

    public String getNome() {
        return nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public ArrayList<String> getExtrato() {
        return extrato;
    }
}
