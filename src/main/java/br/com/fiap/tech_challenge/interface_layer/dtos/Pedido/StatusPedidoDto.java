package br.com.fiap.tech_challenge.interface_layer.dtos.Pedido;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusPedidoDto {

    @Schema(description = "Número do pedido.", example = "123")
    public Long numeroPedido;

    @Schema(description = "Status do pedido.", example = "RECEBIDO")
    public String statusPedido;

    @Schema(description = "Data e hora da última atualização do status.", example = "2024-09-08T09:10:00")
    public LocalDateTime dataHoraStatus;

}
