package Atividade10;

import java.util.ArrayList;

public class Loja {

    private ArrayList<Produto> produtos;
    private ArrayList<ClienteLoja> clientes;
    private ArrayList<Pedido> pedidos;

    public Loja() {
        this.produtos = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.pedidos = new ArrayList<>();
    }

    // METODOS - PRODUTOS
    public void adicionarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("ERRO! O produto nao pode ser nulo.");
        }
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getSku().equals(produto.getSku())) {
                throw new IllegalArgumentException("ERRO! Ja existe um produto com o SKU '" + produto.getSku() + "'.");
            }
        }
        produtos.add(produto);
        System.out.println("Produto adicionado: " + produto.getNome());
    }

    public void listarProdutosPorSku() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        // Copia para ordenar sem alterar a lista original
        ArrayList<Produto> copia = new ArrayList<>(produtos);
        // Bubble sort por SKU
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - i - 1; j++) {
                if (copia.get(j).getSku().compareTo(copia.get(j + 1).getSku()) > 0) {
                    Produto temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        System.out.println("--- Produtos (ordenados por SKU) ---");
        for (int i = 0; i < copia.size(); i++) {
            System.out.println(copia.get(i));
        }
    }

    public void listarProdutosPorPreco() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        ArrayList<Produto> copia = new ArrayList<>(produtos);
        // Bubble sort por preco
        for (int i = 0; i < copia.size() - 1; i++) {
            for (int j = 0; j < copia.size() - i - 1; j++) {
                if (copia.get(j).getPreco() > copia.get(j + 1).getPreco()) {
                    Produto temp = copia.get(j);
                    copia.set(j, copia.get(j + 1));
                    copia.set(j + 1, temp);
                }
            }
        }
        System.out.println("--- Produtos (ordenados por preco) ---");
        for (int i = 0; i < copia.size(); i++) {
            System.out.println(copia.get(i));
        }
    }

    public Produto buscarProduto(String sku) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).getSku().equals(sku)) {
                return produtos.get(i);
            }
        }
        throw new IllegalArgumentException("ERRO! Produto com SKU '" + sku + "' nao encontrado.");
    }

    // METODOS - CLIENTES
    public void adicionarCliente(ClienteLoja cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("ERRO! O cliente nao pode ser nulo.");
        }
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(cliente.getId())) {
                throw new IllegalArgumentException("ERRO! Ja existe um cliente com o ID '" + cliente.getId() + "'.");
            }
        }
        clientes.add(cliente);
        System.out.println("Cliente adicionado: " + cliente.getNome());
    }

    public ClienteLoja buscarCliente(String id) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getId().equals(id)) {
                return clientes.get(i);
            }
        }
        throw new IllegalArgumentException("ERRO! Cliente com ID '" + id + "' nao encontrado.");
    }

    // METODOS - PEDIDOS
    public Pedido criarPedido(String clienteId, ArrayList<ItemPedido> itens) {
        ClienteLoja cliente = buscarCliente(clienteId);
        Pedido pedido = new Pedido(cliente, itens);
        pedidos.add(pedido);
        System.out.println("Pedido #" + pedido.getId() + " criado para " + cliente.getNome());
        return pedido;
    }

    public Pedido buscarPedido(int id) {
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getId() == id) {
                return pedidos.get(i);
            }
        }
        throw new IllegalArgumentException("ERRO! Pedido #" + id + " nao encontrado.");
    }

    // METODOS - RELATORIOS
    public void relatorioFaturamento() {
        double faturamento = 0;
        int quantidadePagos = 0;

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getStatus().equals("PAID")) {
                faturamento += pedidos.get(i).calcularTotal();
                quantidadePagos++;
            }
        }

        System.out.println();
        System.out.println("     RELATORIO DE FATURAMENTO");
        System.out.println("========================================");
        System.out.println("Pedidos pagos: " + quantidadePagos);
        System.out.println("Faturamento total: R$ " + String.format("%.2f", faturamento));

    }

    public void relatorioTopProdutos() {
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        // Arrays paralelos para contar vendas por produto
        String[] skus = new String[produtos.size()];
        String[] nomes = new String[produtos.size()];
        int[] vendas = new int[produtos.size()];

        for (int i = 0; i < produtos.size(); i++) {
            skus[i] = produtos.get(i).getSku();
            nomes[i] = produtos.get(i).getNome();
            vendas[i] = 0;
        }

        // Conta as vendas em pedidos PAID
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getStatus().equals("PAID")) {
                ArrayList<ItemPedido> itens = pedidos.get(i).getItens();
                for (int j = 0; j < itens.size(); j++) {
                    String skuItem = itens.get(j).getProduto().getSku();
                    for (int k = 0; k < skus.length; k++) {
                        if (skus[k].equals(skuItem)) {
                            vendas[k] += itens.get(j).getQuantidade();
                        }
                    }
                }
            }
        }

        // Bubble sort decrescente por vendas
        for (int i = 0; i < vendas.length - 1; i++) {
            for (int j = 0; j < vendas.length - i - 1; j++) {
                if (vendas[j] < vendas[j + 1]) {
                    int tempVenda = vendas[j];
                    vendas[j] = vendas[j + 1];
                    vendas[j + 1] = tempVenda;
                    String tempSku = skus[j];
                    skus[j] = skus[j + 1];
                    skus[j + 1] = tempSku;
                    String tempNome = nomes[j];
                    nomes[j] = nomes[j + 1];
                    nomes[j + 1] = tempNome;
                }
            }
        }

        System.out.println();
        System.out.println(" TOP 3 PRODUTOS MAIS VENDIDOS");
        System.out.println("========================================");
        int limite = Math.min(3, vendas.length);
        for (int i = 0; i < limite; i++) {
            System.out.println((i + 1) + " - " + nomes[i] + " (SKU: " + skus[i] + ") - " + vendas[i] + " unidades");
        }
    }

    public void relatorioFaturamentoPorCategoria() {
        ArrayList<String> categorias = new ArrayList<>();
        ArrayList<Double> faturamentos = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getStatus().equals("PAID")) {
                ArrayList<ItemPedido> itens = pedidos.get(i).getItens();
                for (int j = 0; j < itens.size(); j++) {
                    String categoria = itens.get(j).getProduto().getCategoria();
                    double subtotal = itens.get(j).getSubtotal();

                    int indice = -1;
                    for (int k = 0; k < categorias.size(); k++) {
                        if (categorias.get(k).equals(categoria)) {
                            indice = k;
                        }
                    }

                    if (indice >= 0) {
                        faturamentos.set(indice, faturamentos.get(indice) + subtotal);
                    } else {
                        categorias.add(categoria);
                        faturamentos.add(subtotal);
                    }
                }
            }
        }

        System.out.println();
        System.out.println("FATURAMENTO POR CATEGORIA");
        System.out.println("========================================");
        if (categorias.isEmpty()) {
            System.out.println("Nenhum faturamento registrado.");
        } else {
            for (int i = 0; i < categorias.size(); i++) {
                System.out.println(categorias.get(i) + ": R$ " + String.format("%.2f", faturamentos.get(i)));
            }
        }
    }

    // METODOS - RELATORIOS

    public void relatorioClientesMaisPedidos() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        String[] idsClientes = new String[clientes.size()];
        String[] nomesClientes = new String[clientes.size()];
        int[] qtdPedidos = new int[clientes.size()];

        for (int i = 0; i < clientes.size(); i++) {
            idsClientes[i] = clientes.get(i).getId();
            nomesClientes[i] = clientes.get(i).getNome();
            qtdPedidos[i] = 0;
        }

        for (int i = 0; i < pedidos.size(); i++) {
            String clienteId = pedidos.get(i).getCliente().getId();
            for (int j = 0; j < idsClientes.length; j++) {
                if (idsClientes[j].equals(clienteId)) {
                    qtdPedidos[j]++;
                }
            }
        }

        // Bubble sort decrescente por quantidade de pedidos
        for (int i = 0; i < qtdPedidos.length - 1; i++) {
            for (int j = 0; j < qtdPedidos.length - i - 1; j++) {
                if (qtdPedidos[j] < qtdPedidos[j + 1]) {
                    int tempQtd = qtdPedidos[j];
                    qtdPedidos[j] = qtdPedidos[j + 1];
                    qtdPedidos[j + 1] = tempQtd;
                    String tempId = idsClientes[j];
                    idsClientes[j] = idsClientes[j + 1];
                    idsClientes[j + 1] = tempId;
                    String tempNome = nomesClientes[j];
                    nomesClientes[j] = nomesClientes[j + 1];
                    nomesClientes[j + 1] = tempNome;
                }
            }
        }

        System.out.println();
        System.out.println("CLIENTES COM MAIS PEDIDOS");
        System.out.println("========================================");
        for (int i = 0; i < idsClientes.length; i++) {
            System.out.println(nomesClientes[i] + " (ID: " + idsClientes[i] + ") - " + qtdPedidos[i] + " pedido(s)");
        }

    }

    // GETTERS
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public ArrayList<ClienteLoja> getClientes() {
        return clientes;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }
}
