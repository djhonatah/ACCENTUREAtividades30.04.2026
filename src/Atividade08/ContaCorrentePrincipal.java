package Atividade08;

public class ContaCorrentePrincipal {

    public static void main(String[] args) {

        // CLIENTES
        System.out.println("--- Criando clientes ---");
        Cliente cliente1 = new Cliente("Joao", "12345678901", "Silva");
        Cliente cliente2 = new Cliente("Maria", "98765432100", "Santos");
        System.out.println("Cliente 1: " + cliente1);
        System.out.println("Cliente 2: " + cliente2);

        // CONTAS CORRENTES
        System.out.println("\n--- Criando contas correntes ---");
        ContaCorrente conta1 = new ContaCorrente(1001, cliente1, 500.00);
        ContaCorrente conta2 = new ContaCorrente(1002, cliente2, 300.00);
        System.out.println("Conta 1 criada - Numero: " + conta1.getNumero()
                + " | Saldo: R$ " + String.format("%.2f", conta1.getSaldo()));
        System.out.println("Conta 2 criada - Numero: " + conta2.getNumero()
                + " | Saldo: R$ " + String.format("%.2f", conta2.getSaldo()));

        // DEPOSITO
        System.out.println("\n--- Deposito ---");
        conta1.depositar(200.00);
        System.out.println("Saldo apos deposito: R$ " + String.format("%.2f", conta1.getSaldo()));

        // SAQUE
        System.out.println("\n--- Saque ---");
        conta1.sacar(100.00);
        System.out.println("Saldo apos saque: R$ " + String.format("%.2f", conta1.getSaldo()));

        // TRANSFERENCIA
        System.out.println("\n--- Transferencia ---");
        conta1.transferir(conta2, 150.00);
        System.out.println("Saldo conta 1 apos transferencia: R$ " + String.format("%.2f", conta1.getSaldo()));
        System.out.println("Saldo conta 2 apos transferencia: R$ " + String.format("%.2f", conta2.getSaldo()));

        // EXTRATO
        System.out.println("\n--- Extrato Conta 1 ---");
        conta1.exibirExtrato();
        System.out.println();

        System.out.println("\n--- Extrato Conta 2 ---");
        conta2.exibirExtrato();

        // TESTES DE EXCECAO
        System.out.println("\n=== TESTES DE EXCECAO ===\n");

        // CLIENTE COM NOME VAZIO
        System.out.println("Teste 1: Criar cliente com nome vazio");
        try {
            Cliente clienteErro = new Cliente("", "12345678901", "Teste");
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - Excecao capturada: " + e.getMessage());
        }

        // CLIENTE COM CPF INVALIDO
        System.out.println("\nTeste 2: Criar cliente com CPF invalido");
        try {
            Cliente clienteErro = new Cliente("Ana", "123", "Lima");
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - Excecao capturada: " + e.getMessage());
        }

        // DEPOSITO DE VALOR NEGATIVO
        System.out.println("\nTeste 3: Depositar valor negativo");
        try {
            conta1.depositar(-50.00);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - Excecao capturada: " + e.getMessage());
        }

        // SAQUE DE VALOR MAIOR DO QUE O SALDO
        System.out.println("\nTeste 4: Sacar mais do que o saldo");
        try {
            conta1.sacar(999999.00);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - Excecao capturada: " + e.getMessage());
        }

        // TRANSFERIR PARA CONTA NULA
        System.out.println("\nTeste 5: Transferir para conta nula");
        try {
            conta1.transferir(null, 100.00);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - Excecao capturada: " + e.getMessage());
        }

        // CONTA COM NUMERO NEGATIVO
        System.out.println("\nTeste 6: Criar conta com numero negativo");
        try {
            ContaCorrente contaErro = new ContaCorrente(-1, cliente1, 100.00);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - Excecao capturada: " + e.getMessage());
        }

        // SAQUE DE VALOR ZERO
        System.out.println("\nTeste 7: Sacar valor zero");
        try {
            conta1.sacar(0);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println("OK - Excecao capturada: " + e.getMessage());
        }

        System.out.println("\n=== TODOS OS TESTES FINALIZADOS ===");
    }
}
