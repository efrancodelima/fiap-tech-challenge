package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import br.com.fiap.tech_challenge.core.domain.model.enums.StatusPedido;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.PedidoExceptionsMsg;

public class Pedido {
    private long id;
    private LocalDate data;
    private Cliente cliente;
    private List<ItemPedido> itens;
    private LocalDateTime dataHoraCheckout;
    private StatusPedido status;
    private LocalDateTime dataHoraStatus;

    private static final LocalDate DATA_MINIMA = LocalDate.of(2020, 1, 1);
    private static final LocalDate DATA_MAXIMA = LocalDate.now();

    // Construtor usado para criar um novo pedido
    public Pedido(Cliente cliente) {
        this.data = LocalDate.now();
        this.cliente = cliente;
        this.itens = new ArrayList<ItemPedido>();
        this.status = StatusPedido.AGUARDANDO_CHECKOUT;
        this.dataHoraStatus = LocalDateTime.now();
    }

    // Construtor usado para instanciar um pedido já existente
    public Pedido(long id, LocalDate data, Cliente cliente, List<ItemPedido> itens, LocalDateTime dataHoraCheckout,
            StatusPedido status, LocalDateTime dataHoraStatus) throws BusinessRulesExceptions {
        try {
            setId(id);
            setData(data);
            this.cliente = cliente;
            setItens(itens);
            setDataHoraCheckout(dataHoraCheckout);
            setStatus(status);
            setDataHoraStatus(dataHoraStatus);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }

    }

    // Getters
    public long getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
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

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getDataHoraStatus() {
        return dataHoraStatus;
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

    private void setData(LocalDate data) throws BusinessRulesExceptions {
        validarData(data);
        this.data = data;
    }

    private void setItens(List<ItemPedido> itens) {
        if (itens == null) {
            this.itens = new ArrayList<ItemPedido>();
        } else {
            this.itens = itens;
        }
    }

    private void setDataHoraCheckout(LocalDateTime dataHoraCheckout) throws BusinessRulesExceptions {
        validarDataHoraCheckout(dataHoraCheckout);
        this.dataHoraCheckout = dataHoraCheckout;
    }

    private void setStatus(StatusPedido status) throws BusinessRulesExceptions {
        validarStatus(status);
        this.status = status;
    }

    private void setDataHoraStatus(LocalDateTime dataHoraStatus) throws BusinessRulesExceptions {
        validarDataHoraStatus(dataHoraStatus);
        this.dataHoraStatus = dataHoraStatus;
    }

    // Métodos de validação
    private void validarId(long id) throws BusinessRulesExceptions {
        if (id < 1) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.ID_MIN.getMensagem());
        }
    }

    private void validarData(LocalDate data) throws BusinessRulesExceptions {
        if (data == null) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_NULA.getMensagem());
        } else if (data.isBefore(DATA_MINIMA)) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_MIN.getMensagem());
        } else if (data.isAfter(DATA_MAXIMA)) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_MAX.getMensagem());
        }
    }

    private void validarDataHoraCheckout(LocalDateTime dataHora) throws BusinessRulesExceptions {
        LocalDate data = dataHora == null ? null : dataHora.toLocalDate();
        if (data == null) {
            return;
        } else if (data.isBefore(DATA_MINIMA)) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_CHECKOUT_MIN.getMensagem());
        } else if (data.isAfter(DATA_MAXIMA)) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_CHECKOUT_MAX.getMensagem());
        }
    }

    private void validarStatus(StatusPedido status) throws BusinessRulesExceptions {
        if (status == null) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.STATUS_NULO.getMensagem());
        }
    }

    private void validarDataHoraStatus(LocalDateTime dataHora) throws BusinessRulesExceptions {
        LocalDate data = dataHora == null ? null : dataHora.toLocalDate();

        if (data == null) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_STATUS_NULA.getMensagem());
        } else if (data.isBefore(DATA_MINIMA)) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_STATUS_MIN.getMensagem());
        } else if (data.isAfter(DATA_MAXIMA)) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.DATA_STATUS_MAX.getMensagem());
        }
    }

    private void validarItem(ItemPedido item) throws BusinessRulesExceptions {
        if (item == null) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.ITEM_NULO.getMensagem());
        }
    }

    private void validarNumeroItem(int numeroItem) throws BusinessRulesExceptions {
        if (numeroItem < 1 || numeroItem > itens.size()) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.NUMERO_ITEM.getMensagem());
        }
    }

    private void validarCheckoutNaoRealizado() throws BusinessRulesExceptions {
        if (dataHoraCheckout != null) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.CHECKOUT_REALIZADO.getMensagem());
        }
    }

    private void validarPedidoContemItem() throws BusinessRulesExceptions {
        if (itens.size() == 0) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.PEDIDO_VAZIO.getMensagem());
        }
    }

    private void validarPedidoNaoFinalizado() throws BusinessRulesExceptions {
        if (status == StatusPedido.FINALIZADO) {
            throw new BusinessRulesExceptions(PedidoExceptionsMsg.PEDIDO_FINALIZADO.getMensagem());
        }
    }

    // Outros métodos
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

    public void realizarCheckout() throws BusinessRulesExceptions {
        try {
            validarCheckoutNaoRealizado();
            validarPedidoContemItem();
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao realizar o checkout! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }

        setDataHoraCheckout(LocalDateTime.now());
        mudarStatus();
    }

    public void mudarStatus() throws BusinessRulesExceptions {
        try {
            validarPedidoNaoFinalizado();
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao alterar o status do pedido! " + e.getMessage();
            throw new BusinessRulesExceptions(msg);
        }

        StatusPedido[] statusArray = StatusPedido.values();
        int posicaoAtual = status.ordinal();
        int proximaPosicao = (posicaoAtual + 1) % statusArray.length;
        this.status = statusArray[proximaPosicao];
    }
}
