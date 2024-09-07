package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesException;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.ProdutoExceptions;

public class Produto {
    private long codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;

    // Construtores
    public Produto(String nome, String descricao, BigDecimal preco, CategoriaProduto categoria)
            throws BusinessRulesException {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setCategoria(categoria);

    }

    public Produto(long codigo, String nome, String descricao, BigDecimal preco, CategoriaProduto categoria)
            throws BusinessRulesException {
        this(nome, descricao, preco, categoria);
        setCodigo(codigo);

    }

    // Getters
    public long getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public CategoriaProduto getCategoria() {
        return categoria;
    }

    // Setters
    public void setCodigo(long codigo) throws BusinessRulesException {
        validarCodigo(codigo);
        this.codigo = codigo;
    }

    private void setNome(String nome) throws BusinessRulesException {
        nome = nome == null ? null : nome.trim();
        validarNome(nome);
        this.nome = nome;
    }

    private void setDescricao(String descricao) throws BusinessRulesException {
        descricao = descricao == null ? null : descricao.trim();
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    private void setPreco(BigDecimal preco) throws BusinessRulesException {
        validarPreco(preco);
        this.preco = preco;
    }

    private void setCategoria(CategoriaProduto categoria) throws BusinessRulesException {
        validarCategoria(categoria);
        this.categoria = categoria;
    }

    // Métodos de validação
    private void validarCodigo(long codigo) throws BusinessRulesException {
        if (codigo < 1) {
            throw new BusinessRulesException(ProdutoExceptions.ID_MIN.getMensagem());
        }
    }

    private void validarNome(String nome) throws BusinessRulesException {
        if (nome == null || nome.isEmpty()) {
            throw new BusinessRulesException(ProdutoExceptions.NOME_VAZIO.getMensagem());
        } else if (nome.length() < 5) {
            throw new BusinessRulesException(ProdutoExceptions.NOME_MIN_CHAR.getMensagem());
        } else if (nome.length() > 20) {
            throw new BusinessRulesException(ProdutoExceptions.NOME_MAX_CHAR.getMensagem());
        }
    }

    private void validarDescricao(String descricao) throws BusinessRulesException {
        if (descricao == null) {
            return;
        } else if (descricao.length() < 20) {
            throw new BusinessRulesException(ProdutoExceptions.DESCRICAO_MIN.getMensagem());
        } else if (descricao.length() > 150) {
            throw new BusinessRulesException(ProdutoExceptions.DESCRICAO_MAX.getMensagem());
        }
    }

    private void validarPreco(BigDecimal preco) throws BusinessRulesException {
        if (preco == null) {
            throw new BusinessRulesException(ProdutoExceptions.PRECO_NULO.getMensagem());
        } else if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRulesException(ProdutoExceptions.PRECO_MIN.getMensagem());
        } else if (preco.compareTo(BigDecimal.ZERO) > 300) {
            throw new BusinessRulesException(ProdutoExceptions.PRECO_MAX.getMensagem());
        }
    }

    private void validarCategoria(CategoriaProduto categoria) throws BusinessRulesException {
        if (categoria == null) {
            throw new BusinessRulesException(ProdutoExceptions.CATEGORIA_NULA.getMensagem());
        }
    }
}
