package br.com.fiap.tech_challenge.core.application.service;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ProdutoEntity;
import br.com.fiap.tech_challenge.core.application.exception.produto.ErroAoConsultarProdutosPorCategoriaException;
import br.com.fiap.tech_challenge.core.application.exception.produto.*;
import br.com.fiap.tech_challenge.core.application.mapper.ProdutoMapper;
import br.com.fiap.tech_challenge.core.application.ports.repository.ProdutoRepositoryPort;
import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.core.domain.ports.in.ProdutoServicePort;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.fiap.tech_challenge.core.application.constant.ProdutoExceptionConstante.*;

@Service
@Log4j2
@AllArgsConstructor
public class ProdutoServicePortImpl implements ProdutoServicePort {

    private final ProdutoRepositoryPort produtoRepositoryPort;
    private final ProdutoMapper produtoMapper;

    @Override
    public void cadastrarProduto(Produto produto) {
        try {
            ProdutoEntity produtoEntity = produtoMapper.toEntity(produto);
            produtoRepositoryPort.save(produtoEntity);
        } catch (Exception e) {
            log.error("Erro ao cadastrar produto", e);
            throw new ErroAoCadastrarProdutoException(ERRO_AO_CADASTRAR_PRODUTO_EXCEPTION);
        }
    }

    @Override
    public List<Produto> buscarProdutosPorCategoria(CategoriaProduto categoriaProduto) {
        try {
            List<ProdutoEntity> produtoEntities = produtoRepositoryPort.findProdutosByCategoria(categoriaProduto);
            if (produtoEntities.isEmpty()) {
                log.warn("Nenhum produto encontrado para a categoria {}", categoriaProduto);
                throw new NenhumProdutoEncontradoException(PRODUTO_NAO_ENCONTRADO_EXCEPTION);
            }

            return produtoEntities.stream()
                .map(produtoMapper::toDTO)
                .collect(Collectors.toList());
        }
        catch (NenhumProdutoEncontradoException e) {
            throw e;
        }
        catch (Exception e) {
            throw new ErroAoConsultarProdutosPorCategoriaException(ERRO_AO_CONSULTAR_POR_CATEGORIA_EXCEPTION);
        }
    }

    @Override
    public void atualizarProduto(Produto produto) {
        if (produtoRepositoryPort.findById(produto.getProdutoId()).isEmpty()) {
            log.warn("Produto com ID {} não encontrado", produto.getProdutoId());
            throw new ProdutoNaoEncontradoException(PRODUTO_NAO_ENCONTRADO_EXCEPTION);
        }

        try {
            ProdutoEntity produtoEntity = produtoMapper.toEntity(produto);
            produtoRepositoryPort.save(produtoEntity);
        } catch (Exception e) {
            log.error("Erro ao atualizar produto", e);
            throw new ErroAoAtualizarProdutoException(ERRO_AO_ATUALIZAR_PRODUTO_EXCEPTION);
        }
    }

    @Override
    public void excluirProduto(Long produtoId) {
        if (produtoRepositoryPort.findById(produtoId).isEmpty()) {
            log.warn("Produto com ID {} não encontrado", produtoId);
            throw new ProdutoNaoEncontradoException(PRODUTO_NAO_ENCONTRADO_EXCEPTION);
        }

        try {
            produtoRepositoryPort.deleteById(produtoId);
        } catch (Exception exception) {
            log.error("Erro ao excluir produto", exception);
            throw new ErroAoExcluirProdutoException(ERRO_AO_EXCLUIR_PRODUTO_EXCEPTION);
        }
    }
}