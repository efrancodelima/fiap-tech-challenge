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

@Tag(name = "Pedido")
public interface IPedidoApi {

    // Criar um pedido identificando o cliente pelo CPF
    @Operation(summary = "Criar pedido", description = "Para criar um novo pedido comm a identificação "
            + "do cliente, clique em \"Try It Out\", informe o CPF do cliente em \"Parameters\" e "
            + "clique em \"Execute\".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Pedido criado com sucesso."))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Nenhum cliente foi encontrado para o CPF informado."))),
            @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao criar o pedido."))) })
    @GetMapping(value = "/criar/{cpf}")
    ResponseEntity<String> criarPedido(@PathVariable("cpf") Long cpf);

    // Criar um pedido sem identificação do cliente
    @Operation(summary = "Criar pedido sem identificação", description = "Para criar um novo pedido sem identificação do "
            + "cliente, clique em \"Try It Out\" e \"Execute\".")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Pedido criado com sucesso."))),
            @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao criar o pedido."))) })
    @GetMapping(value = "/criar/")
    ResponseEntity<String> criarPedido();
}
