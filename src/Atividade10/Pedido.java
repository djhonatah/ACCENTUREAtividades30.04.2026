package Atividade10;

import java.util.ArrayList;

public class Pedido {

    private static int contadorPedidos = 0;
    private int id;
    private ClienteLoja cliente;
    private ArrayList<ItemPedido> itens;
    private String status;

    public Pedido(ClienteLoja cliente, ArrayList<ItemPedido> itens) {
        this.cliente = validarCliente(cliente);
        this.itens = validarItens(itens);
        contadorPedidos++;
        this.id = contadorPedidos;
        this.status = "CREATED";
    }

    // METODOS DE VALIDACAO
    private ClienteLoja validarCliente(ClienteLoja cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Erro: O cliente nao pode ser nulo.");
        }
        return cliente;
    }

    private ArrayList<ItemPedido> validarItens(ArrayList<ItemPedido> itens) {
        if (itens == null || itens.isEmpty()) {
            throw new IllegalArgumentException("Erro: O pedido deve ter pelo menos 1 item.");
        }
        return itens;
    }

    private void validarStatus(String esperado, String operacao) {
        if (!this.status.equals(esperado)) {
            throw new IllegalStateException("Erro: So e possivel " + operacao
                    + " pedidos com status " + esperado + ". Status atual: " + this.status);
        }
    }

    // METODOS DO PEDIDO
    public double calcularTotal() {
        double total = 0;
        for (int i = 0; i < itens.size(); i++) {
            total += itens.get(i).getSubtotal();
        }
        return total;
    }

    public void reservarEstoque() {
        validarStatus("CREATED", "reservar estoque de");
        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).getProduto().reservarEstoque(itens.get(i).getQuantidade());
        }
        this.status = "RESERVED";
        System.out.println("Estoque reservado para o pedido #" + this.id);
    }

    public void pagar(boolean sucesso) {
        validarStatus("RESERVED", "pagar");
        if (sucesso) {
            this.status = "PAID";
            System.out.println("Pagamento do pedido #" + this.id + " realizado com sucesso!");
        } else {
            this.status = "FAILED";
            System.out.println("Pagamento do pedido #" + this.id + " falhou!");
        }
    }

    public void cancelar() {
        validarStatus("RESERVED", "cancelar");
        for (int i = 0; i < itens.size(); i++) {
            itens.get(i).getProduto().liberarEstoque(itens.get(i).getQuantidade());
        }
        this.status = "CANCELLED";
        System.out.println("Pedido #" + this.id + " cancelado. Estoque liberado.");
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public ClienteLoja getCliente() {
        return cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public String getStatus() {
        return status;
    }

    public static void resetarContador() {
        contadorPedidos = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido #").append(id).append(" | Cliente: ").append(cliente.getNome())
                .append(" | Status: ").append(status)
                .append(" | Total: R$ ").append(String.format("%.2f", calcularTotal()))
                .append("\n  Itens:\n");
        for (int i = 0; i < itens.size(); i++) {
            sb.append("    ").append(itens.get(i)).append("\n");
        }
        return sb.toString();
    }
}
