package Atividade10;

import java.util.ArrayList;
import java.util.Scanner;

public class LojaPrincipal {

    public static void main(String[] args) {

        Loja loja = new Loja();
        Scanner scanner = new Scanner(System.in);

        boolean rodando = true;
        while (rodando) {

            System.out.println("\n--- MENU ---");
            System.out.println();
            System.out.println("1  - Adicionar produto");
            System.out.println("2  - Listar produtos por SKU");
            System.out.println("3  - Listar produtos por preco");
            System.out.println("4  - Adicionar cliente");
            System.out.println("5  - Criar pedido");
            System.out.println("6  - Reservar estoque do pedido");
            System.out.println("7  - Pagar pedido");
            System.out.println("8  - Cancelar pedido");
            System.out.println("9  - Relatorio: Faturamento total");
            System.out.println("10 - Relatorio: Top 3 produtos");
            System.out.println("11 - Relatorio: Faturamento por categoria");
            System.out.println("12 - Relatorio: Clientes com mais pedidos");
            System.out.println("0  - Sair");
            System.out.print("Escolha uma opcao: ");

            String opcao = scanner.nextLine().trim();

            try {
                switch (opcao) {

                    case "1": // Adicionar produto
                        System.out.print("SKU: ");
                        String sku = scanner.nextLine().trim();
                        System.out.print("Nome: ");
                        String nomeProd = scanner.nextLine().trim();
                        System.out.print("Categoria: ");
                        String categoria = scanner.nextLine().trim();
                        System.out.print("Preco: ");
                        double preco = Double.parseDouble(scanner.nextLine().trim());
                        System.out.print("Estoque: ");
                        int estoque = Integer.parseInt(scanner.nextLine().trim());
                        Produto produto = new Produto(sku, nomeProd, categoria, preco, estoque);
                        loja.adicionarProduto(produto);
                        break;

                    case "2": // Listar por SKU
                        loja.listarProdutosPorSku();
                        break;

                    case "3": // Listar por preco
                        loja.listarProdutosPorPreco();
                        break;

                    case "4": // Adicionar cliente
                        System.out.print("ID do cliente: ");
                        String clienteId = scanner.nextLine().trim();
                        System.out.print("Nome do cliente: ");
                        String nomeCliente = scanner.nextLine().trim();
                        ClienteLoja clienteNovo = new ClienteLoja(clienteId, nomeCliente);
                        loja.adicionarCliente(clienteNovo);
                        break;

                    case "5": // Criar pedido
                        System.out.print("ID do cliente: ");
                        String idCliente = scanner.nextLine().trim();
                        ArrayList<ItemPedido> itens = new ArrayList<>();
                        boolean adicionandoItens = true;
                        while (adicionandoItens) {
                            System.out.print("SKU do produto (ou 'fim' para finalizar): ");
                            String skuItem = scanner.nextLine().trim();
                            if (skuItem.equalsIgnoreCase("fim")) {
                                adicionandoItens = false;
                            } else {
                                Produto prod = loja.buscarProduto(skuItem);
                                System.out.print("Quantidade: ");
                                int qtd = Integer.parseInt(scanner.nextLine().trim());
                                itens.add(new ItemPedido(prod, qtd));
                                System.out.println("Item adicionado: " + prod.getNome() + " x" + qtd);
                            }
                        }
                        if (!itens.isEmpty()) {
                            Pedido pedido = loja.criarPedido(idCliente, itens);
                            System.out.println(pedido);
                        } else {
                            System.out.println("Nenhum item adicionado. Pedido nao criado.");
                        }
                        break;

                    case "6": // Reservar estoque
                        System.out.print("ID do pedido: ");
                        int idPedidoReserva = Integer.parseInt(scanner.nextLine().trim());
                        Pedido pedidoReserva = loja.buscarPedido(idPedidoReserva);
                        pedidoReserva.reservarEstoque();
                        break;

                    case "7": // Pagar pedido
                        System.out.print("ID do pedido: ");
                        int idPedidoPagar = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Pagamento aprovado? (sim/nao): ");
                        String aprovado = scanner.nextLine().trim();
                        Pedido pedidoPagar = loja.buscarPedido(idPedidoPagar);
                        pedidoPagar.pagar(aprovado.equalsIgnoreCase("sim"));
                        break;

                    case "8": // Cancelar pedido
                        System.out.print("ID do pedido: ");
                        int idPedidoCancelar = Integer.parseInt(scanner.nextLine().trim());
                        Pedido pedidoCancelar = loja.buscarPedido(idPedidoCancelar);
                        pedidoCancelar.cancelar();
                        break;

                    case "9":
                        loja.relatorioFaturamento();
                        break;

                    case "10":
                        loja.relatorioTopProdutos();
                        break;

                    case "11":
                        loja.relatorioFaturamentoPorCategoria();
                        break;

                    case "12":
                        loja.relatorioClientesMaisPedidos();
                        break;

                    case "0":
                        rodando = false;
                        System.out.println("Saindo do sistema. Ate logo!");
                        break;

                    default:
                        System.out.println("Opcao invalida. Tente novamente.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Valor numerico invalido. Tente novamente.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }
}
