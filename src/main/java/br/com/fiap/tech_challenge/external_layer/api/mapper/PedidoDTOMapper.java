package br.com.fiap.tech_challenge.external_layer.api.mapper;

// import br.com.fiap.tech_challenge.adapters.driver.controller.model.response.PedidoResponseDTO;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import br.com.fiap.tech_challenge.interface_layer.controller.model.request.CadastrarPedidoDTO;
import br.com.fiap.tech_challenge.interface_layer.controller.model.response.ItemPedidoResponseDTO;

import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
    unmappedSourcePolicy = ReportingPolicy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PedidoDTOMapper {
  Pedido cadastrarToPedido(CadastrarPedidoDTO cadastrar);

  @Mapping(source = "pedidos.itens", target = "itens", qualifiedByName = "itensDomainToItensResponse")
  // List<PedidoResponseDTO> pedidosToPedidosResponseDTO(List<Pedido> pedidos);

  @Named("itensDomainToItensResponse")
  default List<ItemPedidoResponseDTO> itensDomainToItensResponse(List<ItemPedido> itens) {
    List<ItemPedidoResponseDTO> itensResponseDto = new ArrayList<>();
    itens.forEach(item -> {
      ItemPedidoResponseDTO itemPedidoResponseDTO = new ItemPedidoResponseDTO();
      BeanUtils.copyProperties(item, itemPedidoResponseDTO);
      itensResponseDto.add(itemPedidoResponseDTO);
    });

    return itensResponseDto;
  }

}
