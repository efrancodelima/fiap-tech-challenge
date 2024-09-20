package br.com.fiap.tech_challenge.application_layer.use_cases.produto;

import br.com.fiap.tech_challenge.application_layer.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;
import br.com.fiap.tech_challenge.business_layer.entities.produto.Produto;

public final class EditarProduto {

    public static void editar(IProdutoGateway gateway, Produto produto) throws Exception {
        Validar.notNull(produto, EnumApplicationExceptions.PRODUTO_NULO);

        boolean produtoExiste = gateway.produtoExiste(produto.getCodigo());
        if (!produtoExiste) {
            throw new ResourceNotFoundException(EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem());
        } else {
            gateway.atualizarProduto(produto);
        }
    }

}
