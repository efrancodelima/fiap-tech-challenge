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

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cliente;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ClienteDto;

@Tag(name = "Clientes")
public interface IClienteApi {

        // Cadastrar cliente
        @Operation(summary = "Cadastrar cliente", description = ClienteConstantes.descricaoCadastrar)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = ClienteConstantes.d201, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e201))),
                        @ApiResponse(responseCode = "400", description = ClienteConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e400))),
                        @ApiResponse(responseCode = "422", description = ClienteConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = ClienteConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e500))) })
        @PostMapping(value = "/cadastrar")
        ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteDto cadastrar) throws Exception;

        // Buscar cliente por cpf
        @Operation(summary = "Buscar cliente por CPF", description = ClienteConstantes.descricaoBuscar)
        @Parameters({
                        @Parameter(name = "cpf", description = "CPF do cliente", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = ClienteConstantes.d200, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e200))),
                        @ApiResponse(responseCode = "400", description = ClienteConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = ClienteConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = ClienteConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = ClienteConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ClienteConstantes.e500))) })
        @GetMapping(value = "/buscar/{cpf}")
        ResponseEntity<Cliente> buscarClientePorCpf(
                        @PathVariable("cpf") long cpf) throws Exception;

}

final class ClienteConstantes {

        public static final String descricaoCadastrar = "Para cadastrar um cliente, informe os dados do cliente conforme o schema ClienteDto no final desta página.<br>O nome e o email do cliente são opcionais, mas pelo menos um dos dois precisa ser informado.";
        public static final String descricaoBuscar = "Para buscar um cliente, informe o CPF (somente números, sem pontos e traço).";

        public static final String d200 = "Sucesso!";
        public static final String d201 = d200;
        public static final String d400 = "Requisição inválida!";
        public static final String d404 = "Cliente não encontrado!";
        public static final String d422 = "Operação não permitida!";
        public static final String d500 = "Erro!";

        public static final String e200 = "{ \"codigo\": 1, \"cpf\": { \"digitoVerificador\": 96, \"numeroSemDigito\": 111222333 }, \"nome\": \"Arthur Conan Doyle\", \"email\": \"conanad@gmail.com\" }";
        public static final String e201 = e200;
        public static final String e400 = "{ \"timestamp\": \"2024-09-08T02:05:58.036+00:00\", \"status\": 400, \"error\": \"Bad Request\", \"path\": \"/api/v2/clientes/cadastrar\" }";
        public static final String e404 = "{ \"message\": \"Nenhum cliente foi encontrado para o CPF informado.\" }";
        public static final String e422 = "{ \"message\": \"O número do CPF deve ser maior que 0.\" }";
        public static final String e500 = "{ \"message\": \"Ocorreu um erro inesperado no servidor.\" }";
}
