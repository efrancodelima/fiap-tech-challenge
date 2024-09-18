package br.com.fiap.tech_challenge.application_layer.use_cases.produto;

import br.com.fiap.tech_challenge.application_layer.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;
import br.com.fiap.tech_challenge.application_layer.interfaces.gateway.IProdutoGateway;
import br.com.fiap.tech_challenge.application_layer.services.Validar;

public final class RemoverProduto {

    // Métodos públicos
    public static void remover(IProdutoGateway gateway, Long codigoProduto) throws Exception {
        Validar.notNull(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_NULO);
        Validar.maiorQueZero(codigoProduto, EnumApplicationExceptions.PRODUTO_CODIGO_MIN);

        boolean produtoExiste = gateway.produtoExiste(codigoProduto);
        if (!produtoExiste) {
            throw new ResourceNotFoundException(EnumNotFoundExceptions.PRODUTO_NAO_ENCONTRADO.getMensagem());
        } else {
            gateway.removerProduto(codigoProduto);
        }

    }

}
