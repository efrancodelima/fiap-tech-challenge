package br.com.fiap.tech_challenge.business_layer.entities;

import java.util.List;

import br.com.fiap.tech_challenge.business_layer.constants.Validacao;
import br.com.fiap.tech_challenge.business_layer.entities.enums.StatusPagamentoEnum;
import br.com.fiap.tech_challenge.business_layer.entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.PedidoExceptions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Pedido {
    private long numero;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataHoraCheckout;
    private StatusPagamento statusPagamento;
    private StatusPedido statusPedido;

    // Construtor usado para criar um pedido antes do checkout
    public Pedido(Cliente cliente, List<ItemPedido> itens) throws BusinessRuleException {

        var statusPagamento = new StatusPagamento(StatusPagamentoEnum.AGUARDANDO_PAGAMENTO, LocalDateTime.now());
        var statusPedido = new StatusPedido(StatusPedidoEnum.AGUARDANDO_CHECKOUT, LocalDateTime.now());

        setCliente(cliente);
        setItens(itens);
        setStatusPagamento(statusPagamento);
        setStatusPedido(statusPedido);
    }

    // Construtor usado para instanciar um pedido já existente
    public Pedido(Long numero, Cliente cliente, List<ItemPedido> itens, LocalDateTime dataHoraCheckout,
            StatusPagamento pagamento, StatusPedido statusPedido)
            throws BusinessRuleException {

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
    private void setNumero(Long numero) throws BusinessRuleException {
        validarNumero(numero);
        this.numero = numero;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void setItens(List<ItemPedido> itens) throws BusinessRuleException {
        validarItens(itens);
        this.itens = itens;
    }

    private void setDataHoraCheckout(LocalDateTime dataHoraCheckout, StatusPedidoEnum statusPedido)
            throws BusinessRuleException {
        validarDataHoraCheckout(dataHoraCheckout, statusPedido);
        this.dataHoraCheckout = dataHoraCheckout;
    }

    private void setStatusPagamento(StatusPagamento status) throws BusinessRuleException {
        validarStatus(status);
        this.statusPagamento = status;
    }

    private void setStatusPedido(StatusPedido status) throws BusinessRuleException {
        validarStatus(status);
        this.statusPedido = status;
    }

    // Métodos de validação
    private void validarNumero(Long numero) throws BusinessRuleException {
        if (numero == null) {
            throw new BusinessRuleException(PedidoExceptions.NUMERO_NULO.getMensagem());
        }
        if (numero < 1) {
            throw new BusinessRuleException(PedidoExceptions.NUMERO_MIN.getMensagem());
        }
    }

    private void validarItens(List<ItemPedido> itens) throws BusinessRuleException {
        if (itens == null || itens.size() == 0) {
            throw new BusinessRuleException(PedidoExceptions.ITENS_VAZIO.getMensagem());
        }

        for (ItemPedido item : itens) {
            validarItem(item);
        }
    }

    private void validarDataHoraCheckout(LocalDateTime dataHora, StatusPedidoEnum statusPedido)
            throws BusinessRuleException {
        if (dataHora == null && statusPedido != StatusPedidoEnum.AGUARDANDO_CHECKOUT) {
            throw new BusinessRuleException(PedidoExceptions.DATA_CHECKOUT_NULA.getMensagem());
        }

        if (dataHora.toLocalDate().isBefore(Validacao.dataMinima)) {
            throw new BusinessRuleException(PedidoExceptions.DATA_CHECKOUT_MIN.getMensagem());
        }

        if (dataHora.isAfter(LocalDateTime.now())) {
            throw new BusinessRuleException(PedidoExceptions.DATA_CHECKOUT_MAX.getMensagem());
        }
    }

    private void validarStatus(StatusPagamento status) throws BusinessRuleException {
        if (status == null) {
            throw new BusinessRuleException(PedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarStatus(StatusPedido status) throws BusinessRuleException {
        if (status == null) {
            throw new BusinessRuleException(PedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarItem(ItemPedido item) throws BusinessRuleException {
        if (item == null) {
            throw new BusinessRuleException(PedidoExceptions.ITEM_NULO.getMensagem());
        }
    }

    private void validarCheckoutNaoRealizado() throws BusinessRuleException {
        if (dataHoraCheckout != null) {
            throw new BusinessRuleException(PedidoExceptions.CHECKOUT_REALIZADO.getMensagem());
        }
    }

    private void validarPedidoContemItem() throws BusinessRuleException {
        if (itens.size() == 0) {
            throw new BusinessRuleException(PedidoExceptions.PEDIDO_VAZIO.getMensagem());
        }
    }

    private void validarPedidoNaoFinalizado() throws BusinessRuleException {
        if (statusPedido.getStatus() == StatusPedidoEnum.FINALIZADO) {
            throw new BusinessRuleException(PedidoExceptions.PEDIDO_FINALIZADO.getMensagem());
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
    public void fazerCheckout() throws BusinessRuleException {
        validarCheckoutNaoRealizado();
        validarPedidoContemItem();
        setDataHoraCheckout(LocalDateTime.now(), StatusPedidoEnum.AGUARDANDO_CHECKOUT);
        atualizarStatusPedido();
    }

    public void atualizarStatusPedido() throws BusinessRuleException {
        validarPedidoNaoFinalizado();

        var nextStatus = getNextStatusPedido();
        var novoStatus = new StatusPedido(nextStatus, LocalDateTime.now());
        setStatusPedido(novoStatus);
    }

}
