package br.com.fiap.tech_challenge.core.application.mapper;

import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.ItemPedidoEntity;
import br.com.fiap.tech_challenge.adapters.driven.infrastructure.entity.PedidoEntity;
import br.com.fiap.tech_challenge.core.domain.model.ItemPedido;
import br.com.fiap.tech_challenge.core.domain.model.Pedido;
import org.mapstruct.*;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface PedidoMapper {

    @Mapping(source = "entity.itensPedido", target = "itens", qualifiedByName = "itensPedidoEntityToItensPedido")
    @Mapping(source = "entity.situacao", target = "situacaoPedido")
    Pedido toDTO(PedidoEntity entity);

    @Mapping(source = "pedido.itens", target = "itensPedido", qualifiedByName = "itensPedidoToItensPedidoEntity")
    @Mapping(source = "pedido.situacaoPedido", target = "situacao")
    PedidoEntity toEntity(Pedido pedido);

    @Named("itensPedidoEntityToItensPedido")
    default List<ItemPedido> itensPedidoEntityToItensPedido (List<ItemPedidoEntity> entities) {
        List<ItemPedido> itemPedidoList = new ArrayList<>();
        entities.forEach(item -> {
            ItemPedido itemPedido = new ItemPedido();
            BeanUtils.copyProperties(item, itemPedido);
            itemPedido.setIdProduto(item.getId());
            itemPedidoList.add(itemPedido);
        });
        return itemPedidoList;
    }

    @Named("itensPedidoToItensPedidoEntity")
    default List<ItemPedidoEntity> itensPedidoToItensPedidoEntity (List<ItemPedido> itens) {
        List<ItemPedidoEntity> itemPedidoEntities = new ArrayList<>();
        itens.forEach(item -> {
            ItemPedidoEntity entity = new ItemPedidoEntity();
            BeanUtils.copyProperties(item, entity);
            entity.setId(item.getIdProduto());
            itemPedidoEntities.add(entity);
        });

        return itemPedidoEntities;
    }

}
