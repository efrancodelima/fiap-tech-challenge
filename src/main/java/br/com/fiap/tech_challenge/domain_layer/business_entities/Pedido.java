package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.StatusPedidoEnum;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.PedidoExceptions;
import br.com.fiap.tech_challenge.domain_layer.interfaces.IPedido;

public class Pedido implements IPedido {
    private long id;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataHoraCheckout;
    private StatusPagamento statusPagamento;
    private StatusPedido statusPedido;

    // Construtor usado para criar um novo pedido
    public Pedido(Cliente cliente) throws BusinessRulesExceptions {
        try {
            var status = new StatusPedido(StatusPedidoEnum.AGUARDANDO_CHECKOUT, LocalDateTime.now());
            setCliente(cliente);
            setItens(null, null);
            setStatusPedido(status);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }
    }

    // Construtor usado para instanciar um pedido já existente
    public Pedido(long id, Cliente cliente, List<ItemPedido> itens, LocalDateTime dataHoraCheckout,
            StatusPagamento pagamento, StatusPedido status)
            throws BusinessRulesExceptions {
        try {
            setId(id);
            setCliente(cliente);
            setItens(itens, dataHoraCheckout);
            setDataHoraCheckout(dataHoraCheckout, statusPedido.getStatus());
            setStatusPagamento(pagamento);
            setStatusPedido(status);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }
    }

    // Getters
    public long getId() {
        return id;
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
    private void setId(long id) throws BusinessRulesExceptions {
        validarId(id);
        this.id = id;
    }

    private void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private void setItens(List<ItemPedido> itens, LocalDateTime dataHoraCheckout) throws BusinessRulesExceptions {
        validarItens(itens, dataHoraCheckout);
        this.itens = itens;
    }

    private void setDataHoraCheckout(LocalDateTime dataHoraCheckout, StatusPedidoEnum statusPedido)
            throws BusinessRulesExceptions {
        validarDataHoraCheckout(dataHoraCheckout, statusPedido);
        this.dataHoraCheckout = dataHoraCheckout;
    }

    private void setStatusPagamento(StatusPagamento status) throws BusinessRulesExceptions {
        this.statusPagamento = status;
    }

    private void setStatusPedido(StatusPedido status) throws BusinessRulesExceptions {
        validarStatusPedido(status);
        this.statusPedido = status;
    }

    // Métodos de validação
    private void validarId(long id) throws BusinessRulesExceptions {
        if (id < 1) {
            throw new BusinessRulesExceptions(PedidoExceptions.ID_MIN.getMensagem());
        }
    }

    private void validarItens(List<ItemPedido> itens, LocalDateTime dataHoraCheckout) throws BusinessRulesExceptions {
        if (dataHoraCheckout != null) {
            if (itens == null || itens.size() == 0) {
                throw new BusinessRulesExceptions(PedidoExceptions.ITENS_VAZIO.getMensagem());
            }
        }
    }

    private void validarDataHoraCheckout(LocalDateTime dataHora, StatusPedidoEnum statusPedido)
            throws BusinessRulesExceptions {
        if (dataHora == null && statusPedido == StatusPedidoEnum.AGUARDANDO_CHECKOUT) {
            return;
        }

        if (dataHora == null) {
            throw new BusinessRulesExceptions(PedidoExceptions.DATA_CHECKOUT_NULA.getMensagem());
        }

        if (dataHora.isBefore(Constantes.dataHoraMinima)) {
            throw new BusinessRulesExceptions(PedidoExceptions.DATA_CHECKOUT_MIN.getMensagem());
        }

        if (dataHora.isAfter(LocalDateTime.now())) {
            throw new BusinessRulesExceptions(PedidoExceptions.DATA_CHECKOUT_MAX.getMensagem());
        }
    }

    private void validarStatusPedido(StatusPedido status) throws BusinessRulesExceptions {
        if (status == null) {
            throw new BusinessRulesExceptions(PedidoExceptions.STATUS_NULO.getMensagem());
        }
    }

    private void validarItem(ItemPedido item) throws BusinessRulesExceptions {
        if (item == null) {
            throw new BusinessRulesExceptions(PedidoExceptions.ITEM_NULO.getMensagem());
        }
    }

    private void validarNumeroItem(int numeroItem) throws BusinessRulesExceptions {
        if (numeroItem < 1 || numeroItem > itens.size()) {
            throw new BusinessRulesExceptions(PedidoExceptions.NUMERO_ITEM.getMensagem());
        }
    }

    private void validarCheckoutNaoRealizado() throws BusinessRulesExceptions {
        if (dataHoraCheckout != null) {
            throw new BusinessRulesExceptions(PedidoExceptions.CHECKOUT_REALIZADO.getMensagem());
        }
    }

    private void validarPedidoContemItem() throws BusinessRulesExceptions {
        if (itens.size() == 0) {
            throw new BusinessRulesExceptions(PedidoExceptions.PEDIDO_VAZIO.getMensagem());
        }
    }

    private void validarPedidoNaoFinalizado() throws BusinessRulesExceptions {
        if (statusPedido.getStatus() == StatusPedidoEnum.FINALIZADO) {
            throw new BusinessRulesExceptions(PedidoExceptions.PEDIDO_FINALIZADO.getMensagem());
        }
    }

    // Métodos públicos
    @Override
    public void adicionarItem(ItemPedido item) throws BusinessRulesExceptions {
        try {
            validarCheckoutNaoRealizado();
            validarItem(item);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao adicionar o item ao pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }
        this.itens.add(item);
    }

    @Override
    public void alterarItem(int numeroItem, ItemPedido item) throws BusinessRulesExceptions {
        try {
            validarCheckoutNaoRealizado();
            validarNumeroItem(numeroItem);
            validarItem(item);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao alterar o item do pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }
        this.itens.set(numeroItem - 1, item);
    }

    @Override
    public void removerItem(int numeroItem) throws BusinessRulesExceptions {
        try {
            validarCheckoutNaoRealizado();
            validarNumeroItem(numeroItem);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao remover o item do pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }
        this.itens.remove(numeroItem - 1);
    }

    @Override
    public void fazerCheckout() throws BusinessRulesExceptions {
        try {
            validarCheckoutNaoRealizado();
            validarPedidoContemItem();
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao realizar o checkout! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }

        setDataHoraCheckout(LocalDateTime.now(), StatusPedidoEnum.AGUARDANDO_CHECKOUT);
        atualizarStatusPedido();
    }

    @Override
    public void atualizarStatusPedido() throws BusinessRulesExceptions {
        try {
            validarPedidoNaoFinalizado();
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao alterar o status do pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }

        StatusPedidoEnum[] statusArray = StatusPedidoEnum.values();
        int posicaoAtual = statusPedido.getStatus().ordinal();
        int proximaPosicao = (posicaoAtual + 1) % statusArray.length;
        var novoStatus = new StatusPedido(statusArray[proximaPosicao], LocalDateTime.now());
        setStatusPedido(novoStatus);
    }
}
