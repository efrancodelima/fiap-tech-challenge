package br.com.fiap.tech_challenge.application_layer.interfaces.gateway;

import java.util.ArrayList;

import br.com.fiap.tech_challenge.adapters.driver.controller.model.enums.CategoriaProduto;
import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;

public interface IProdutoGateway {
    public Produto gravarProduto(Produto produto);

    public Produto atualizarProduto(Produto produto);

    public void removerProduto(Produto produto);

    public ArrayList<Produto> buscarPorCategoria(CategoriaProduto categoria);
}
