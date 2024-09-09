package br.com.fiap.tech_challenge.external_layer.apis.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.PagamentoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.Pedido.StatusPedidoDto;

@Tag(name = "Pedido")
public interface IPedidoApi {

        // Fazer checkout
        @Operation(summary = "Fazer checkout", description = PedidoConstantes.descricaoFazerCheckout)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = PedidoConstantes.d201, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e201))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @PostMapping(value = "/checkout/")
        ResponseEntity<StatusPedidoDto> fazerCheckout(@RequestBody PedidoDto pedidoDto) throws Exception;

        // Atualizar status do pedido
        @Operation(summary = "Atualizar o status do pedido", description = PedidoConstantes.descricaoAtualizarStatusPedido)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = PedidoConstantes.d200, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e200))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @PostMapping(value = "/status/{numeroPedido}")
        ResponseEntity<StatusPedidoDto> atualizarStatusPedido(@PathVariable("numeroPedido") long numeroPedido)
                        throws Exception;

        // Consultar status do pagamento
        @Operation(summary = "Consultar o status do pagamento", description = PedidoConstantes.descricaoConsultarStatusPagamento)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = PedidoConstantes.d200, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e200))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @PostMapping(value = "/pagamento/{numeroPedido}")
        ResponseEntity<StatusPedidoDto> consultarStatusPagamento(@PathVariable("numeroPedido") long numeroPedido)
                        throws Exception;

        // Listar pedidos
        @Operation(summary = "Listar pedidos", description = PedidoConstantes.descricaoListarPedidos)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = PedidoConstantes.d200, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.l200))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @PostMapping(value = "/listar")
        ResponseEntity<List<StatusPedidoDto>> listarPedidos() throws Exception;

        // Webhook do Mercado Pago
        @Operation(summary = "Webhook para receber notificações de pagamento", description = PedidoConstantes.descricaoWebhook)
        @PostMapping(value = "/webhook/")
        void webhookMercadoPago(@RequestBody PagamentoDto pagamentoDto) throws Exception;

}

final class PedidoConstantes {

        public static final String descricaoFazerCheckout = "Para realizar o checkout do pedido, informe os dados do pedido conforme o schema PedidoDto no final desta página.<br>O CPF do cliente é opcional, coloque zero se não quiser identificar o cliente.";
        public static final String descricaoAtualizarStatusPedido = "Para atualizar o status do pedido, informe o número do pedido pelo path.<br>Os status possuem uma ordem sequencial, então ele mudará automaticamente para o valor seguinte.";
        public static final String descricaoConsultarStatusPagamento = "Para consultar o status do pagamento, informe o número do pedido pelo path.";
        public static final String descricaoListarPedidos = "Lista os pedidos recebidos, em preparação e prontos.<br>Os pedidos mais antigos são exibidos primeiro e os mais novos depois.<br>A lista também é ordenada pelo status do pedido: pedidos prontos no topo da lista e recebidos no final.";
        public static final String descricaoWebhook = "Webhook criado para receber notificações de pagamento enviadas pelo Mercado Pago.";

        public static final String d200 = "Sucesso!";
        public static final String d201 = d200;
        public static final String d204 = d200;
        public static final String d400 = "Requisição inválida!";
        public static final String d404 = "Pedido não encontrado!";
        public static final String d422 = "Operação não permitida!";
        public static final String d500 = "Erro!";

        public static final String e200 = "{ \"numeroPedido\": 4, \"statusPedido\": \"PRONTO\", \"dataHora\": \"2024-09-08 15:31:59\" }";
        public static final String l200 = "[ { \"numeroPedido\": 4, \"statusPedido\": \"PRONTO\", \"dataHora\": \"2024-09-08 15:31:59\" }, { \"numeroPedido\": 1, \"statusPedido\": \"EM_PREPARACAO\", \"dataHora\": \"2024-09-08 15:31:52\" }]";
        public static final String e201 = e200;
        public static final String e204 = "";
        public static final String e400 = "{ \"code\": 400, \"status\": \"Bad Request\", \"message\": \"JSON parse error: Cannot deserialize value of type `java.lang.Long` from String \\\"x\\\": not a valid `java.lang.Long` value\", \"timestamp\": \"2024-09-08T15:39:12.091139608\" }";
        public static final String e404 = "{ \"code\": 404, \"status\": \"Not Found\", \"message\": \"Não foi encontrado nenhum pedido para o número informado.\", \"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
        public static final String e422 = "{ \"code\": 422, \"status\": \"Unprocessable Entity\", \"message\": \"O pedido precisa conter, pelo menos, um item.\", \"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
        public static final String e500 = "{ \"code\": 500, \"status\": \"Internal Server Error\", \"message\": \"Ocorreu um erro inesperado no servidor.\", \"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
}
