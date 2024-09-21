package br.com.fiap.tech_challenge.interface_layer.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    @Schema(description = "CPF do cliente.", example = "11122233396")
    public Long cpf;

    @Schema(description = "Nome do cliente.", example = "Arthur Conan Doyle")
    public String nome;

    @Schema(description = "Email do cliente.", example = "conanad@gmail.com")
    public String email;

}
