package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.domain_layer.business_entities.interfaces.IPedido;
import br.com.fiap.tech_challenge.domain_layer.constants.Validacao;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.PedidoExceptions;

public class Pedido implements IPedido {
    private long numero;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataHoraCheckout;
    private StatusPagamento statusPagamento;
    private StatusPedido statusPedido;

    // Construtor usado para criar um pedido antes do checkout
    public Pedido(Cliente cliente, List<ItemPedido> itens) throws Exception {

        var statusPedido = new StatusPedido(StatusPedidoEnum.AGUARDANDO_CHECKOUT, LocalDateTime.now());

        setCliente(cliente);
        setItens(itens);
        setStatusPedido(statusPedido);
    }

    // Construtor usado para instanciar um pedido já existente
    public Pedido(long numero, Cliente cliente, List<ItemPedido> itens, LocalDateTime dataHoraCheckout,
            StatusPagamento pagamento, StatusPedido statusPedido)
            throws Exception {

        setNumero(numero);
        setCliente(cliente);
        setItens(itens);
        setDataHoraCheckout(dataHoraCheckout, statusPedido.getStatus());
        setStatusPagamento(pagamento);
        setStatusPedido(statusPedido);
    }

    // Getters
    public long getNumero() {
        return numero;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public LocalDateTime getDataHoraCheckout() {
        return dataHoraCheckout;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public BigDecimal getValorPedido() {
        BigDecimal result = BigDecimal.valueOf(0);
        for (ItemPedido item : itens) {
            result = result.add(item.getValorItem());
        }
        return result;
    }

    // Setters
    private void setNumero(long numero) throws Exception {
        validarNumero(numero);
        this.numero = numero;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void setItens(List<ItemPedido> itens) throws Exception {
        validarItens(itens);
        this.itens = itens;
    }

    private void setDataHoraCheckout(LocalDateTime dataHoraCheckout, StatusPedidoEnum statusPedido)
            throws Exception {
        validarDataHoraCheckout(dataHoraCheckout, statusPedido);
        this.dataHoraCheckout = dataHoraCheckout;
    }

    private void setStatusPagamento(StatusPagamento status) throws Exception {
        this.statusPagamento = status;
    }

    private void setStatusPedido(StatusPedido status) throws Exception {
        validarStatusPedido(status);
        this.statusPedido = status;
    }

    // Métodos de validação
    private void validarNumero(long numero) throws Exception {
        if (numero < 1) {
            throw new Exception(PedidoExceptions.NUMERO_MIN.getMensagem());
        }
    }

    private void validarItens(List<ItemPedido> itens) throws Exception {
        if (itens == null || itens.size() == 0) {
            throw new Exception(PedidoExceptions.ITENS_VAZIO.getMensagem());
        }
    }

    private void validarDataHoraCheckout(LocalDateTime dataHora, StatusPedidoEnum statusPedido)
            throws Exception {
        if (dataHora == null && statusPedido != StatusPedidoEnum.AGUARDANDO_CHECKOUT) {
            throw new Exception(PedidoExceptions.DATA_CHECKOUT_NULA.getMensagem());
        }

        if (dataHora.isBefore(Validacao.dataHoraMinima)) {
            throw new Exception(PedidoExceptions.DATA_CHECKOUT_MIN.getMensagem());
        }

        if (dataHora.isAfter(LocalDateTime.now())) {
            throw new Exception(PedidoExceptions.DATA_CHECKOUT_MAX.getMensagem());
        }
    }

    private void validarStatusPedido(StatusPedido status) throws Exception {
        if (status == null) {
            throw new Exception(PedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarItem(ItemPedido item) throws Exception {
        if (item == null) {
            throw new Exception(PedidoExceptions.ITEM_NULO.getMensagem());
        }
    }

    private void validarNumeroItem(int numeroItem) throws Exception {
        if (numeroItem < 1 || numeroItem > itens.size()) {
            throw new Exception(PedidoExceptions.NUMERO_ITEM.getMensagem());
        }
    }

    private void validarCheckoutNaoRealizado() throws Exception {
        if (dataHoraCheckout != null) {
            throw new Exception(PedidoExceptions.CHECKOUT_REALIZADO.getMensagem());
        }
    }

    private void validarPedidoContemItem() throws Exception {
        if (itens.size() == 0) {
            throw new Exception(PedidoExceptions.PEDIDO_VAZIO.getMensagem());
        }
    }

    private void validarPedidoNaoFinalizado() throws Exception {
        if (statusPedido.getStatus() == StatusPedidoEnum.FINALIZADO) {
            throw new Exception(PedidoExceptions.PEDIDO_FINALIZADO.getMensagem());
        }
    }

    // Métodos públicos
    @Override
    public void adicionarItem(ItemPedido item) throws Exception {
        try {
            validarCheckoutNaoRealizado();
            validarItem(item);
        } catch (Exception e) {
            String msg = "Erro ao adicionar o item ao pedido! " + e.getMessage();
            throw new Exception(msg);
        }
        this.itens.add(item);
    }

    @Override
    public void alterarItem(int numeroItem, ItemPedido item) throws Exception {
        try {
            validarCheckoutNaoRealizado();
            validarNumeroItem(numeroItem);
            validarItem(item);
        } catch (Exception e) {
            String msg = "Erro ao alterar o item do pedido! " + e.getMessage();
            throw new Exception(msg);
        }
        this.itens.set(numeroItem - 1, item);
    }

    @Override
    public void removerItem(int numeroItem) throws Exception {
        try {
            validarCheckoutNaoRealizado();
            validarNumeroItem(numeroItem);
        } catch (Exception e) {
            String msg = "Erro ao remover o item do pedido! " + e.getMessage();
            throw new Exception(msg);
        }
        this.itens.remove(numeroItem - 1);
    }

    @Override
    public void fazerCheckout() throws Exception {
        validarCheckoutNaoRealizado();
        validarPedidoContemItem();
        setDataHoraCheckout(LocalDateTime.now(), StatusPedidoEnum.AGUARDANDO_CHECKOUT);
        atualizarStatusPedido();
    }

    @Override
    public void atualizarStatusPedido() throws Exception {
        validarPedidoNaoFinalizado();

        StatusPedidoEnum[] statusArray = StatusPedidoEnum.values();
        int posicaoAtual = statusPedido.getStatus().ordinal();
        int proximaPosicao = (posicaoAtual + 1) % statusArray.length;
        var novoStatus = new StatusPedido(statusArray[proximaPosicao], LocalDateTime.now());
        setStatusPedido(novoStatus);
    }
}
