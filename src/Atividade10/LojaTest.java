package Atividade10;

import java.util.ArrayList;

public class LojaTest {

    public static void main(String[] args) {

        System.out.println("=== TESTES DA ATIVIDADE 10 ===\n");

        Pedido.resetarContador();

        Loja loja = new Loja();

        System.out.println("TESTES \n");

        // ADICIONAR PRODUTOS
        System.out.println("Teste 1: Adicionar produtos");
        loja.adicionarProduto(new Produto("SKU001", "Notebook Dell", "Eletronicos", 3500.00, 10));
        loja.adicionarProduto(new Produto("SKU002", "Mouse Logitech", "Eletronicos", 150.00, 50));
        loja.adicionarProduto(new Produto("SKU003", "Camiseta Nike", "Roupas", 120.00, 30));
        loja.adicionarProduto(new Produto("SKU004", "Tenis Adidas", "Roupas", 350.00, 20));
        loja.adicionarProduto(new Produto("SKU005", "Livro Java", "Livros", 80.00, 100));

        // LISTAR PRODUTOS
        System.out.println("Teste 2: Listar produtos por SKU");
        loja.listarProdutosPorSku();
        System.out.println();
        System.out.println("Teste 2b: Listar produtos por preco");
        loja.listarProdutosPorPreco();
        System.out.println();

        // ADICIONAR CLIENTES
        System.out.println("Teste 3: Adicionar clientes");
        loja.adicionarCliente(new ClienteLoja("C001", "Joao Silva"));
        loja.adicionarCliente(new ClienteLoja("C002", "Maria Santos"));
        loja.adicionarCliente(new ClienteLoja("C003", "Pedro Lima"));

        // CRIAR PEDIDO
        System.out.println("Teste 4: Criar pedido");
        ArrayList<ItemPedido> itens1 = new ArrayList<>();
        itens1.add(new ItemPedido(loja.buscarProduto("SKU001"), 2));
        itens1.add(new ItemPedido(loja.buscarProduto("SKU002"), 3));
        Pedido pedido1 = loja.criarPedido("C001", itens1);
        System.out.println(pedido1);

        // RESERVAR ESTOQUE
        System.out.println("Teste 5: Reservar estoque");
        pedido1.reservarEstoque();
        System.out.println("Status: " + pedido1.getStatus());

        // PAGAR PEDIDO
        System.out.println("Teste 6: Pagar pedido");
        pedido1.pagar(true);
        System.out.println("Status: " + pedido1.getStatus());

        // CRIAR PEDIDO E CANCELAR
        System.out.println("Teste 7: Criar pedido e cancelar");
        ArrayList<ItemPedido> itens2 = new ArrayList<>();
        itens2.add(new ItemPedido(loja.buscarProduto("SKU003"), 5));
        Pedido pedido2 = loja.criarPedido("C002", itens2);
        pedido2.reservarEstoque();
        System.out.println("Estoque camiseta antes do cancelamento: " + loja.buscarProduto("SKU003").getEstoque());
        pedido2.cancelar();
        System.out.println("Estoque camiseta apos cancelamento: " + loja.buscarProduto("SKU003").getEstoque());
        System.out.println("Status: " + pedido2.getStatus());

        // CRIAR MAIS PEDIDOS PARA RELATORIOS
        System.out.println("Teste 8: Criando pedidos adicionais para relatorios");
        ArrayList<ItemPedido> itens3 = new ArrayList<>();
        itens3.add(new ItemPedido(loja.buscarProduto("SKU005"), 10));
        itens3.add(new ItemPedido(loja.buscarProduto("SKU004"), 2));
        Pedido pedido3 = loja.criarPedido("C001", itens3);
        pedido3.reservarEstoque();
        pedido3.pagar(true);

        ArrayList<ItemPedido> itens4 = new ArrayList<>();
        itens4.add(new ItemPedido(loja.buscarProduto("SKU002"), 5));
        Pedido pedido4 = loja.criarPedido("C003", itens4);
        pedido4.reservarEstoque();
        pedido4.pagar(true);
        System.out.println("OK - Pedidos adicionais criados e pagos\n");

        // RELATORIOS
        System.out.println("Teste 9: Relatorios");
        loja.relatorioFaturamento();
        System.out.println();
        loja.relatorioTopProdutos();
        System.out.println();
        loja.relatorioFaturamentoPorCategoria();
        System.out.println();
        loja.relatorioClientesMaisPedidos();

        System.out.println("\n--- TESTES DE EXCECAO ---\n");

        // PRODUTO COM SKU DUPLICADO
        System.out.println("Teste E1: Produto com SKU duplicado");
        try {
            loja.adicionarProduto(new Produto("SKU001", "Duplicado", "Teste", 10.00, 5));
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // PRODUTO COM PRECO NEGATIVO
        System.out.println("\nTeste E2: Produto com preco negativo");
        try {
            new Produto("SKU999", "Teste", "Teste", -10.00, 5);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // PRODUTO COM NOME VAZIO
        System.out.println("\nTeste E3: Produto com nome vazio");
        try {
            new Produto("SKU999", "", "Teste", 10.00, 5);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // CLIENTE COM ID DUPLICADO
        System.out.println("\nTeste E4: Cliente com ID duplicado");
        try {
            loja.adicionarCliente(new ClienteLoja("C001", "Duplicado"));
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // BUSCAR PRODUTO INEXISTENTE
        System.out.println("\nTeste E5: Buscar produto inexistente");
        try {
            loja.buscarProduto("SKU999");
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // BUSCAR CLIENTE INEXISTENTE
        System.out.println("\nTeste E6: Buscar cliente inexistente");
        try {
            loja.buscarCliente("C999");
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // PAGAR PEDIDO SEM RESERVAR ESTOQUE
        System.out.println("\nTeste E7: Pagar pedido sem reservar estoque");
        try {
            ArrayList<ItemPedido> itensErro = new ArrayList<>();
            itensErro.add(new ItemPedido(loja.buscarProduto("SKU005"), 1));
            Pedido pedidoErro = loja.criarPedido("C001", itensErro);
            pedidoErro.pagar(true);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // CANCELAR PEDIDO SEM RESERVAR
        System.out.println("\nTeste E8: Cancelar pedido sem reservar");
        try {
            ArrayList<ItemPedido> itensErro2 = new ArrayList<>();
            itensErro2.add(new ItemPedido(loja.buscarProduto("SKU005"), 1));
            Pedido pedidoErro2 = loja.criarPedido("C002", itensErro2);
            pedidoErro2.cancelar();
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        // RESERVAR ESTOQUE INSUFICIENTE
        System.out.println("\nTeste E9: Reservar estoque insuficiente");
        try {
            ArrayList<ItemPedido> itensErro3 = new ArrayList<>();
            itensErro3.add(new ItemPedido(loja.buscarProduto("SKU001"), 9999));
            Pedido pedidoErro3 = loja.criarPedido("C003", itensErro3);
            pedidoErro3.reservarEstoque();
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        // ITEM COM QUANTIDADE ZERO
        System.out.println("\nTeste E10: Item com quantidade zero");
        try {
            new ItemPedido(loja.buscarProduto("SKU001"), 0);
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // PEDIDO SEM ITENS
        System.out.println("\nTeste E11: Pedido sem itens");
        try {
            loja.criarPedido("C001", new ArrayList<>());
            System.out.println("FALHOU - Deveria ter lancado excecao!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // PAGAMENTO FALHO (FAILED)
        System.out.println("\nTeste E12: Pagamento falho");
        ArrayList<ItemPedido> itensFalha = new ArrayList<>();
        itensFalha.add(new ItemPedido(loja.buscarProduto("SKU005"), 1));
        Pedido pedidoFalha = loja.criarPedido("C002", itensFalha);
        pedidoFalha.reservarEstoque();
        pedidoFalha.pagar(false);
        System.out.println(
                "Status: " + pedidoFalha.getStatus() + (pedidoFalha.getStatus().equals("FAILED") ? "OK" : "FALHOU"));

        System.out.println("\n=== TODOS OS TESTES FINALIZADOS ===");
    }
}
