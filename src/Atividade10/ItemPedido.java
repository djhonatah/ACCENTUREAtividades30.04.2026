package Atividade10;

public class ItemPedido {

    private Produto produto;
    private int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = validarProduto(produto);
        this.quantidade = validarQuantidade(quantidade);
    }

    // METODOS - VALIDACAO
    private Produto validarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("ERRO! O produto nao pode ser nulo.");
        }
        return produto;
    }

    private int validarQuantidade(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("ERRO! A quantidade deve ser maior que zero.");
        }
        return quantidade;
    }

    // GETTERS E SETTERS
    public double getSubtotal() {
        return this.produto.getPreco() * this.quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "  " + produto.getNome() + " x" + quantidade + " = R$ " + String.format("%.2f", getSubtotal());
    }
}
