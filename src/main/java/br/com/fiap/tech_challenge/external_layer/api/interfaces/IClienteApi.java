package br.com.fiap.tech_challenge.external_layer.api.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.fiap.tech_challenge.interface_layer.dtos.ClienteDto;

@Tag(name = "Clientes")
public interface IClienteApi {

        // Cadastrar cliente
        @Operation(summary = "Cadastrar cliente", description = "Para cadastrar um novo cliente, clique em "
                        + "\"Try It Out\", informe os dados do cliente em \"Request Body\", conforme o schema "
                        + "ClienteDto ao final desta página.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Cliente cadastrado com sucesso."))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
                        @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao cadastrar o cliente."))) })
        @PostMapping("/cadastrar")
        ResponseEntity<String> cadastrarCliente(@RequestBody ClienteDto cadastrar);

        // Buscar cliente por cpf
        @Operation(summary = "Buscar cliente por CPF", description = "Para buscar um cliente pelo CPF, "
                        + "clique em \"Try It Out\" e informe o CPF (somente números, sem pontos e traço) "
                        + "em \"Parameters\".")
        @Parameters({
                        @Parameter(name = "cpf", description = "CPF do cliente", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Cliente encontrado com sucesso."))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
                        @ApiResponse(responseCode = "404", description = "Cliente não encontrado!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Nenhum cliente foi encontrado para o CPF informado."))),
                        @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao buscar o cliente."))) })
        @GetMapping(value = "/buscar/{cpf}")
        ResponseEntity<String> buscarClientePorCpf(
                        @PathVariable("cpf") Long cpf);

}
