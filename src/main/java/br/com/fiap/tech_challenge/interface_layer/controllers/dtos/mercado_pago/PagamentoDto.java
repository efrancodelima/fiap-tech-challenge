package br.com.fiap.tech_challenge.interface_layer.controllers.dtos.mercado_pago;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDto {

    @Schema(description = "Código de identificação do pagamento.", example = "10")
    public Long id;

    @Schema(description = "Situação do pagamento.", example = "approved")
    public String status;

}
