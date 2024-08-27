package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.math.BigDecimal;

import br.com.fiap.tech_challenge.core.domain.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.exceptions.BusinessRulesExceptions;
import br.com.fiap.tech_challenge.domain_layer.exceptions.ProdutoExceptions;

public class Produto {
    private long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;

    // Construtores
    public Produto(String nome, String descricao, BigDecimal preco, CategoriaProduto categoria)
            throws BusinessRulesExceptions {
        try {
            setNome(nome);
            setDescricao(descricao);
            setPreco(preco);
            setCategoria(categoria);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o produto! ";
            throw new BusinessRulesExceptions(msg + e.getMessage());
        }
    }

    public Produto(long id, String nome, String descricao, BigDecimal preco, CategoriaProduto categoria)
            throws BusinessRulesExceptions {
        this(nome, descricao, preco, categoria);
        try {
            setId(id);
        } catch (BusinessRulesExceptions e) {
            String msg = "Erro ao instanciar o produto! ";
            throw new BusinessRulesExceptions(msg + e.getMessage());
        }
    }

    // Getters
    public long getId() {
        return id;
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
    private void setId(long id) throws BusinessRulesExceptions {
        validarId(id);
        this.id = id;
    }

    private void setNome(String nome) throws BusinessRulesExceptions {
        nome = nome == null ? null : nome.trim();
        validarNome(nome);
        this.nome = nome;
    }

    private void setDescricao(String descricao) throws BusinessRulesExceptions {
        descricao = descricao == null ? null : descricao.trim();
        validarDescricao(descricao);
        this.descricao = descricao;
    }

    private void setPreco(BigDecimal preco) throws BusinessRulesExceptions {
        validarPreco(preco);
        this.preco = preco;
    }

    private void setCategoria(CategoriaProduto categoria) throws BusinessRulesExceptions {
        validarCategoria(categoria);
        this.categoria = categoria;
    }

    // Métodos de validação
    public void validarId(long id) throws BusinessRulesExceptions {
        if (id < 1) {
            throw new BusinessRulesExceptions(ProdutoExceptions.ID_MIN.getMensagem());
        }
    }

    public void validarNome(String nome) throws BusinessRulesExceptions {
        if (nome == null || nome.isEmpty()) {
            throw new BusinessRulesExceptions(ProdutoExceptions.NOME_VAZIO.getMensagem());
        } else if (nome.length() < 5) {
            throw new BusinessRulesExceptions(ProdutoExceptions.NOME_MIN_CHAR.getMensagem());
        } else if (nome.length() > 20) {
            throw new BusinessRulesExceptions(ProdutoExceptions.NOME_MAX_CHAR.getMensagem());
        }
    }

    public void validarDescricao(String descricao) throws BusinessRulesExceptions {
        if (descricao == null) {
            return;
        } else if (descricao.length() < 20) {
            throw new BusinessRulesExceptions(ProdutoExceptions.DESCRICAO_MIN.getMensagem());
        } else if (descricao.length() > 150) {
            throw new BusinessRulesExceptions(ProdutoExceptions.DESCRICAO_MAX.getMensagem());
        }
    }

    public void validarPreco(BigDecimal preco) throws BusinessRulesExceptions {
        if (preco == null) {
            throw new BusinessRulesExceptions(ProdutoExceptions.PRECO_NULO.getMensagem());
        } else if (preco.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessRulesExceptions(ProdutoExceptions.PRECO_MIN.getMensagem());
        } else if (preco.compareTo(BigDecimal.ZERO) > 300) {
            throw new BusinessRulesExceptions(ProdutoExceptions.PRECO_MAX.getMensagem());
        }
    }

    public void validarCategoria(CategoriaProduto categoria) throws BusinessRulesExceptions {
        // A categoria não pode ser nula
        if (categoria == null) {
            throw new BusinessRulesExceptions(ProdutoExceptions.CATEGORIA_NULA.getMensagem());
        }
    }
}
