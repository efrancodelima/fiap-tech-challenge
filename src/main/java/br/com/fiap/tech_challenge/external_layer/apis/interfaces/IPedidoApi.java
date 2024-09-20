package br.com.fiap.tech_challenge.external_layer.apis.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.tech_challenge.business_layer.entities.pedido.Pedido;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.mercado_pago.PagamentoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido.PedidoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido.StatusPagamentoDto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.pedido.StatusPedidoDto;

@Tag(name = "Pedidos")
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
        @PutMapping(value = "/status/{numeroPedido}")
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
        @GetMapping(value = "/pagamento/{numeroPedido}")
        ResponseEntity<StatusPagamentoDto> consultarStatusPagamento(@PathVariable("numeroPedido") long numeroPedido)
                        throws Exception;

        // Listar pedidos
        @Operation(summary = "Listar pedidos", description = PedidoConstantes.descricaoListarPedidos)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = PedidoConstantes.d200, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e200listar))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @GetMapping(value = "/listar")
        ResponseEntity<List<Pedido>> listarPedidos() throws Exception;

        // Webhook do Mercado Pago
        @Operation(summary = "Webhook para receber notificações de pagamento", description = PedidoConstantes.descricaoWebhook)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = PedidoConstantes.d204, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e204))),
                        @ApiResponse(responseCode = "400", description = PedidoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = PedidoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = PedidoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = PedidoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = PedidoConstantes.e500))) })
        @PutMapping(value = "/webhook/")
        ResponseEntity<Void> webhookMercadoPago(@RequestBody PagamentoDto pagamentoDto) throws Exception;

}

final class PedidoConstantes {

        public static final String descricaoFazerCheckout = "Para realizar o checkout do pedido, informe os dados do pedido conforme o schema PedidoDto no final desta página.<br>O CPF do cliente é opcional, deixe em branco se não quiser identificar o cliente.";
        public static final String descricaoAtualizarStatusPedido = "Para atualizar o status do pedido, informe o número do pedido pelo path.<br>Os status possuem uma ordem sequencial, então ele mudará automaticamente para o valor seguinte.";
        public static final String descricaoConsultarStatusPagamento = "Para consultar o status do pagamento, informe o número do pedido pelo path.";
        public static final String descricaoListarPedidos = "Lista os pedidos recebidos, em preparação e prontos.<br>Os pedidos mais antigos são exibidos primeiro e os mais novos depois.<br>A lista também é ordenada pelo status do pedido: pedidos prontos no topo da lista e recebidos no final.";
        public static final String descricaoWebhook = "Webhook criado para receber notificações de pagamento enviadas pelo Mercado Pago.<br>A notificação do Mercado pago tem muitos campos, mas nós só mapeamos aqueles que iremos usar na lógica do nosso sistema.<br>Status possíveis: pending, in process, approved e rejected.<br>O id do pagamento é o número do pedido * 10 (mock).";

        public static final String d200 = "Sucesso!";
        public static final String d201 = d200;
        public static final String d204 = d200;
        public static final String d400 = "Requisição inválida!";
        public static final String d404 = "Pedido não encontrado!";
        public static final String d422 = "Operação não permitida!";
        public static final String d500 = "Erro!";

        public static final String e200 = "{ \"numeroPedido\": 4, \"statusPedido\": \"PRONTO\", \"dataHora\": \"2024-09-08 15:31:59\" }";
        public static final String e200listar = "[ { \"numero\": 1, \"cliente\": null, \"itens\": [ { \"produto\": { \"codigo\": 1, \"nome\": \"X-Monstrão\", \"descricao\": null, \"preco\": 20, \"categoria\": \"LANCHE\" }, \"quantidade\": 1, \"valorItem\": 20 } ], \"dataHoraCheckout\": \"2024-09-20T10:22:09.175124\", \"statusPagamento\": { \"codigo\": 0, \"status\": \"AGUARDANDO_PAGAMENTO\", \"dataHora\": \"2024-09-20T10:22:09.246543\" }, \"statusPedido\": { \"status\": \"RECEBIDO\", \"dataHora\": \"2024-09-20T10:22:09.175173\" }, \"valorPedido\": 20 }, { \"numero\": 2, \"cliente\": null, \"itens\": [ { \"produto\": { \"codigo\": 2, \"nome\": \"X-Monstrinho\", \"descricao\": null, \"preco\": 20, \"categoria\": \"LANCHE\" }, \"quantidade\": 2, \"valorItem\": 40 } ], \"dataHoraCheckout\": \"2024-09-20T10:22:15.888514\", \"statusPagamento\": { \"codigo\": 0, \"status\": \"AGUARDANDO_PAGAMENTO\", \"dataHora\": \"2024-09-20T10:22:15.899493\" }, \"statusPedido\": { \"status\": \"RECEBIDO\", \"dataHora\": \"2024-09-20T10:22:15.888543\" }, \"valorPedido\": 40 }]";
        public static final String e201 = e200;
        public static final String e204 = "";
        public static final String e400 = "{ \"code\": 400, \"status\": \"Bad Request\", \"message\": \"JSON parse error: Cannot deserialize value of type `java.lang.Long` from String \\\"x\\\": not a valid `java.lang.Long` value\", \"timestamp\": \"2024-09-08T15:39:12.091139608\" }";
        public static final String e404 = "{ \"code\": 404, \"status\": \"Not Found\", \"message\": \"Não foi encontrado nenhum pedido para o número informado.\", \"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
        public static final String e422 = "{ \"code\": 422, \"status\": \"Unprocessable Entity\", \"message\": \"O pedido precisa conter, pelo menos, um item.\", \"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
        public static final String e500 = "{ \"code\": 500, \"status\": \"Internal Server Error\", \"message\": \"Ocorreu um erro inesperado no servidor.\", \"timestamp\": \"2024-09-08T15:37:05.129042146\"}";

}
