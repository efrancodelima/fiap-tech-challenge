package br.com.fiap.tech_challenge.external_layer.api.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;

@Tag(name = "Pedido")
public interface IPedidoApi {

        // Fazer checkout
        @Operation(summary = "Fazer checkout do pedido", description = "Para realizar o checkout do pedido, "
                        + "clique em \"Try It Out\" e informe os dados do pedido em \"Request Body\" conforme "
                        + "os schema PedidoDto ao final desta página. Após, clique em \"Execute\".<br>O CPF "
                        + "do cliente é opcional, deixe como zero se não quiser identificar o cliente.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Checkout realizado com sucesso."))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
                        @ApiResponse(responseCode = "404", description = "Cliente não encontrado!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Nenhum cliente foi encontrado para o CPF informado."))),
                        @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao realizar o checkout."))) })
        @PostMapping(value = "/checkout/")
        ResponseEntity<String> fazerCheckout(@RequestBody PedidoDto pedidoDto);

}
