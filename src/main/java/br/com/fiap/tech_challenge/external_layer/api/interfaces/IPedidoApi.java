package br.com.fiap.tech_challenge.external_layer.api.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Pedido;
import br.com.fiap.tech_challenge.interface_layer.dtos.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.dtos.Pedido.StatusPedidoDto;

@Tag(name = "Pedido")
public interface IPedidoApi {

        // Fazer checkout
        @Operation(summary = "Fazer checkout do pedido", description = PedidoConstantes.descricaoCheckout)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = PedidoConstantes.d201, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e201))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @PostMapping(value = "/checkout/")
        ResponseEntity<StatusPedidoDto> fazerCheckout(@RequestBody PedidoDto pedidoDto) throws Exception;

        // Atualizar status
        @Operation(summary = "Atualizar o status do pedido", description = PedidoConstantes.descricaoAtualizarStatus)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = PedidoConstantes.d200, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e200))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @PostMapping(value = "/status/{numeroPedido}")
        ResponseEntity<StatusPedidoDto> atualizarStatusPedido(@PathVariable("numeroPedido") Long numeroPedido)
                        throws Exception;

}

final class PedidoConstantes {

        public static final String descricaoCheckout = "Para realizar o checkout do pedido, informe os dados do pedido conforme o schema PedidoDto no final desta página.<br>O CPF do cliente é opcional, coloque zero se não quiser identificar o cliente.";
        public static final String descricaoAtualizarStatus = "Para atualizar o status do pedido, informe o número do pedido pelo path.<br>Os status possuem uma ordem sequencial, então ele mudará automaticamente para o valor seguinte.";

        public static final String d200 = "Sucesso!";
        public static final String d201 = d200;
        public static final String d204 = d200;
        public static final String d400 = "Requisição inválida!";
        public static final String d404 = "Pedido não encontrado!";
        public static final String d422 = "Operação não permitida!";
        public static final String d500 = "Erro!";

        public static final String e200 = "{ \"numero\": 1, \"cliente\": null, \"itens\": [ { \"produto\": { \"codigo\": 1, \"nome\": \"X-Monstrão\", \"descricao\": \"O lanche do marombeiro, "
                        + "repleto de whey e creatina.\", \"preco\": 35.9, \"categoria\": \"LANCHE\" }, \"quantidade\": 2, \"valorItem\": 71.8 } ], \"dataHoraCheckout\": "
                        + "null, \"statusPagamento\": null, \"statusPedido\": { \"status\": \"RECEBIDO\", \"dataHora\": \"2024-09-08T00:12:59.95886677\" }, \"valorPedido\": 71.8 }";
        public static final String e201 = e200;
        public static final String e204 = "";
        public static final String e400 = "{ \"timestamp\": \"2024-09-08T02:05:58.036+00:00\", \"status\": 400, \"error\": \"Bad Request\", \"path\": \"/api/v2/pedidos/checkout\" }";
        public static final String e404 = "{ \"message\": \"Nenhum pedido foi encontrado para o número informado.\" }";
        public static final String e422 = "{ \"message\": \"O pedido precisa conter, pelo menos, 1 item.\" }";
        public static final String e500 = "{ \"message\": \"Ocorreu um erro inesperado no servidor.\" }";
}
