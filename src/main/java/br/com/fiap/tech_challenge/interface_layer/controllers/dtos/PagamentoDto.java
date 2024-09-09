package br.com.fiap.tech_challenge.interface_layer.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagamentoDto {

    @Schema(description = "Código de identificação do pagamento.", example = "1")
    public Long id;

    @Schema(description = "Situação do pagamento.", example = "pending")
    public String status;

}
