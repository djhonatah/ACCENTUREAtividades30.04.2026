package Atividade10;

public class Produto {

    private String sku;
    private String nome;
    private String categoria;
    private double preco;
    private int estoque;

    public Produto(String sku, String nome, String categoria, double preco, int estoque) {
        this.sku = validarTexto(sku, "SKU");
        this.nome = validarTexto(nome, "nome do produto");
        this.categoria = validarTexto(categoria, "categoria");
        this.preco = validarPreco(preco);
        this.estoque = validarEstoque(estoque);
    }

    // METODOS - VALIDACAO
    private String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException("ERRO! O " + campo + " nao pode ser vazio.");
        }
        return valor.trim();
    }

    private double validarPreco(double preco) {
        if (preco <= 0) {
            throw new IllegalArgumentException("ERRO! O preco deve ser maior que zero.");
        }
        return preco;
    }

    private int validarEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("ERRO! O estoque nao pode ser negativo.");
        }
        return estoque;
    }

    private void validarQuantidade(int qtd, String op) {
        if (qtd <= 0) {
            throw new IllegalArgumentException("ERRO! A quantidade a " + op + " deve ser maior que zero.");
        }
    }

    // METODOS - ESTOQUE
    public void reservarEstoque(int quantidade) {
        validarQuantidade(quantidade, "reservar");
        if (quantidade > this.estoque) {
            throw new IllegalArgumentException("ERRO! Estoque insuficiente para o produto '"
                    + this.nome + "'. Disponivel: " + this.estoque + ", solicitado: " + quantidade);
        }
        this.estoque -= quantidade;
    }

    public void liberarEstoque(int quantidade) {
        validarQuantidade(quantidade, "liberar");
        this.estoque += quantidade;
    }

    // GETTERS
    public String getSku() {
        return sku;
    }

    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPreco() {
        return preco;
    }

    public int getEstoque() {
        return estoque;
    }

    @Override
    public String toString() {
        return "SKU: " + sku + " | " + nome + " | Categoria: " + categoria
                + " | Preco: R$ " + String.format("%.2f", preco) + " | Estoque: " + estoque;
    }
}
