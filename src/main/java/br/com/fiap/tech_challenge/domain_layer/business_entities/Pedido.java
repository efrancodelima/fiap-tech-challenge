package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPagamentoEnum;
import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.domain_layer.business_entities.interfaces.IPedido;
import br.com.fiap.tech_challenge.domain_layer.constants.Validacao;
import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.PedidoExceptions;

public class Pedido implements IPedido {
    private long numero;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataHoraCheckout;
    private StatusPagamento statusPagamento;
    private StatusPedido statusPedido;

    // Construtor usado para criar um pedido antes do checkout
    public Pedido(Cliente cliente, List<ItemPedido> itens) throws MyBusinessException {

        var statusPagamento = new StatusPagamento(StatusPagamentoEnum.AGUARDANDO_PAGAMENTO, LocalDateTime.now());
        var statusPedido = new StatusPedido(StatusPedidoEnum.AGUARDANDO_CHECKOUT, LocalDateTime.now());

        setCliente(cliente);
        setItens(itens);
        setStatusPagamento(statusPagamento);
        setStatusPedido(statusPedido);
    }

    // Construtor usado para instanciar um pedido já existente
    public Pedido(long numero, Cliente cliente, List<ItemPedido> itens, LocalDateTime dataHoraCheckout,
            StatusPagamento pagamento, StatusPedido statusPedido)
            throws MyBusinessException {

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
    private void setNumero(long numero) throws MyBusinessException {
        validarNumero(numero);
        this.numero = numero;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void setItens(List<ItemPedido> itens) throws MyBusinessException {
        validarItens(itens);
        this.itens = itens;
    }

    private void setDataHoraCheckout(LocalDateTime dataHoraCheckout, StatusPedidoEnum statusPedido)
            throws MyBusinessException {
        validarDataHoraCheckout(dataHoraCheckout, statusPedido);
        this.dataHoraCheckout = dataHoraCheckout;
    }

    private void setStatusPagamento(StatusPagamento status) throws MyBusinessException {
        validarStatus(status);
        this.statusPagamento = status;
    }

    private void setStatusPedido(StatusPedido status) throws MyBusinessException {
        validarStatus(status);
        this.statusPedido = status;
    }

    // Métodos de validação
    private void validarNumero(long numero) throws MyBusinessException {
        if (numero < 1) {
            throw new MyBusinessException(PedidoExceptions.NUMERO_MIN.getMensagem());
        }
    }

    private void validarItens(List<ItemPedido> itens) throws MyBusinessException {
        if (itens == null || itens.size() == 0) {
            throw new MyBusinessException(PedidoExceptions.ITENS_VAZIO.getMensagem());
        }
    }

    private void validarDataHoraCheckout(LocalDateTime dataHora, StatusPedidoEnum statusPedido)
            throws MyBusinessException {
        if (dataHora == null && statusPedido != StatusPedidoEnum.AGUARDANDO_CHECKOUT) {
            throw new MyBusinessException(PedidoExceptions.DATA_CHECKOUT_NULA.getMensagem());
        }

        if (dataHora.isBefore(Validacao.dataHoraMinima)) {
            throw new MyBusinessException(PedidoExceptions.DATA_CHECKOUT_MIN.getMensagem());
        }

        if (dataHora.isAfter(LocalDateTime.now())) {
            throw new MyBusinessException(PedidoExceptions.DATA_CHECKOUT_MAX.getMensagem());
        }
    }

    private void validarStatus(StatusPagamento status) throws MyBusinessException {
        if (status == null) {
            throw new MyBusinessException(PedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarStatus(StatusPedido status) throws MyBusinessException {
        if (status == null) {
            throw new MyBusinessException(PedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarItem(ItemPedido item) throws MyBusinessException {
        if (item == null) {
            throw new MyBusinessException(PedidoExceptions.ITEM_NULO.getMensagem());
        }
    }

    private void validarNumeroItem(int numeroItem) throws MyBusinessException {
        if (numeroItem < 1 || numeroItem > itens.size()) {
            throw new MyBusinessException(PedidoExceptions.NUMERO_ITEM.getMensagem());
        }
    }

    private void validarCheckoutNaoRealizado() throws MyBusinessException {
        if (dataHoraCheckout != null) {
            throw new MyBusinessException(PedidoExceptions.CHECKOUT_REALIZADO.getMensagem());
        }
    }

    private void validarPedidoContemItem() throws MyBusinessException {
        if (itens.size() == 0) {
            throw new MyBusinessException(PedidoExceptions.PEDIDO_VAZIO.getMensagem());
        }
    }

    private void validarPedidoNaoFinalizado() throws MyBusinessException {
        if (statusPedido.getStatus() == StatusPedidoEnum.FINALIZADO) {
            throw new MyBusinessException(PedidoExceptions.PEDIDO_FINALIZADO.getMensagem());
        }
    }

    // Outros métodos privados
    private StatusPedidoEnum getNextStatusPedido() {
        StatusPedidoEnum[] statusArray = StatusPedidoEnum.values();
        int posicaoAtual = statusPedido.getStatus().ordinal();
        int proximaPosicao = (posicaoAtual + 1) % statusArray.length;
        return statusArray[proximaPosicao];
    }

    // Métodos públicos
    @Override
    public void adicionarItem(ItemPedido item) throws MyBusinessException {
        try {
            validarCheckoutNaoRealizado();
            validarItem(item);
        } catch (Exception e) {
            String msg = "Erro ao adicionar o item ao pedido! " + e.getMessage();
            throw new MyBusinessException(msg);
        }
        this.itens.add(item);
    }

    @Override
    public void alterarItem(int numeroItem, ItemPedido item) throws MyBusinessException {
        try {
            validarCheckoutNaoRealizado();
            validarNumeroItem(numeroItem);
            validarItem(item);
        } catch (Exception e) {
            String msg = "Erro ao alterar o item do pedido! " + e.getMessage();
            throw new MyBusinessException(msg);
        }
        this.itens.set(numeroItem - 1, item);
    }

    @Override
    public void removerItem(int numeroItem) throws MyBusinessException {
        try {
            validarCheckoutNaoRealizado();
            validarNumeroItem(numeroItem);
        } catch (Exception e) {
            String msg = "Erro ao remover o item do pedido! " + e.getMessage();
            throw new MyBusinessException(msg);
        }
        this.itens.remove(numeroItem - 1);
    }

    @Override
    public void fazerCheckout() throws MyBusinessException {
        validarCheckoutNaoRealizado();
        validarPedidoContemItem();
        setDataHoraCheckout(LocalDateTime.now(), StatusPedidoEnum.AGUARDANDO_CHECKOUT);
        atualizarStatusPedido();
    }

    @Override
    public void atualizarStatusPedido() throws MyBusinessException {
        validarPedidoNaoFinalizado();

        var nextStatus = getNextStatusPedido();
        var novoStatus = new StatusPedido(nextStatus, LocalDateTime.now());
        setStatusPedido(novoStatus);
    }

}
