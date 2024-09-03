package br.com.fiap.tech_challenge.external_layer.api.mapper;

import br.com.fiap.tech_challenge.core.domain.model.Produto;
import br.com.fiap.tech_challenge.interface_layer.controller.model.request.AtualizarProdutoDTO;
import br.com.fiap.tech_challenge.interface_layer.controller.model.request.CadastrarProdutoDTO;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProdutoDTOMapper {
  Produto cadastrarToProdutoDTO(CadastrarProdutoDTO cadastrar);
  Produto atualizarToProdutoDTO(AtualizarProdutoDTO atualizar);

}
