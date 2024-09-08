package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.domain_layer.business_entities.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;
import br.com.fiap.tech_challenge.domain_layer.exceptions.enums.ProdutoExceptions;

public class Produto {
    private long codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;

    // Construtores
    public Produto(String nome, String descricao, BigDecimal preco, CategoriaProduto categoria)
            throws MyBusinessException {
        setNome(nome);
        setDescricao(descricao);
        setPreco(preco);
        setCategoria(categoria);

    }

    public Produto(long codigo, String nome, String descricao, BigDecimal preco, CategoriaProduto categoria)
            throws MyBusinessException {
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
    public void setCodigo(long codigo) throws MyBusinessException {
        validarCodigo(codigo);
        this.codigo = codigo;
    }

    private void setNome(String nome) throws MyBusinessException {
        nome = nome == null ? null : nome.trim();
        validarNome(nome);
        this.nome = nome;
    }

    private void setDescricao(String descricao) throws MyBusinessException {

        if (descricao == null || descricao.isBlank()) {
            descricao = null;
        } else {
            descricao = descricao.trim();
        }

        validarDescricao(descricao);
        this.descricao = descricao;
    }

    private void setPreco(BigDecimal preco) throws MyBusinessException {
        validarPreco(preco);
        this.preco = preco;
    }

    private void setCategoria(CategoriaProduto categoria) throws MyBusinessException {
        validarCategoria(categoria);
        this.categoria = categoria;
    }

    // Métodos de validação
    private void validarCodigo(long codigo) throws MyBusinessException {
        if (codigo < 1) {
            throw new MyBusinessException(ProdutoExceptions.CODIGO_MIN.getMensagem());
        }
    }

    private void validarNome(String nome) throws MyBusinessException {
        if (nome == null || nome.isEmpty()) {
            throw new MyBusinessException(ProdutoExceptions.NOME_VAZIO.getMensagem());
        } else if (nome.length() < 5) {
            throw new MyBusinessException(ProdutoExceptions.NOME_MIN_CHAR.getMensagem());
        } else if (nome.length() > 20) {
            throw new MyBusinessException(ProdutoExceptions.NOME_MAX_CHAR.getMensagem());
        }
    }

    private void validarDescricao(String descricao) throws MyBusinessException {
        if (descricao == null) {
            return;
        } else if (descricao.length() < 20) {
            throw new MyBusinessException(ProdutoExceptions.DESCRICAO_MIN.getMensagem());
        } else if (descricao.length() > 150) {
            throw new MyBusinessException(ProdutoExceptions.DESCRICAO_MAX.getMensagem());
        }
    }

    private void validarPreco(BigDecimal preco) throws MyBusinessException {
        if (preco == null) {
            throw new MyBusinessException(ProdutoExceptions.PRECO_NULO.getMensagem());
        } else if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MyBusinessException(ProdutoExceptions.PRECO_MIN.getMensagem());
        } else if (preco.compareTo(BigDecimal.ZERO) > 300) {
            throw new MyBusinessException(ProdutoExceptions.PRECO_MAX.getMensagem());
        }
    }

    private void validarCategoria(CategoriaProduto categoria) throws MyBusinessException {
        if (categoria == null) {
            throw new MyBusinessException(ProdutoExceptions.CATEGORIA_NULA.getMensagem());
        }
    }
}
