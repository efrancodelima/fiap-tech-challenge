package br.com.fiap.tech_challenge.external_layer.api.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Produto;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ProdutoDto;

@Tag(name = "Produtos")
public interface IProdutoApi {

        // Cadastrar produto
        @Operation(summary = "Cadastrar produto", description = ProdutoConstantes.descricaoCadastrar)
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = ProdutoConstantes.d201, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e201))),
                        @ApiResponse(responseCode = "400", description = ProdutoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e400))),
                        @ApiResponse(responseCode = "422", description = ProdutoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = ProdutoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e500))) })
        @PostMapping("/cadastrar")
        ResponseEntity<Produto> cadastrarProduto(@RequestBody ProdutoDto cadastrar) throws Exception;

        // Editar produto
        @Operation(summary = "Editar produto", description = ProdutoConstantes.descricaoEditar)
        @Parameters({
                        @Parameter(name = "codigo", description = "Código do produto a ser editado", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = ProdutoConstantes.d204, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e204))),
                        @ApiResponse(responseCode = "400", description = ProdutoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = ProdutoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = ProdutoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = ProdutoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e500))) })
        @PutMapping("/editar/{codigo}")
        ResponseEntity<Produto> editarProduto(@PathVariable long codigo, @RequestBody ProdutoDto atualizar)
                        throws Exception;

        // Remover produto
        @Operation(summary = "Remover produto", description = ProdutoConstantes.descricaoRemover)
        @Parameters({
                        @Parameter(name = "codigo", description = "Código do produto a ser removido", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = ProdutoConstantes.d204, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e204))),
                        @ApiResponse(responseCode = "400", description = ProdutoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e400))),
                        @ApiResponse(responseCode = "404", description = ProdutoConstantes.d404, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e404))),
                        @ApiResponse(responseCode = "422", description = ProdutoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = ProdutoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e500))) })
        @DeleteMapping(value = "/remover/{codigo}")
        ResponseEntity<Produto> removerProduto(@PathVariable long codigo) throws Exception;

        // Listar produtos por categoria
        @Operation(summary = "Buscar produtos por categoria", description = ProdutoConstantes.descricaoBuscarPorCategoria)
        @Parameters({
                        @Parameter(name = "categoria", description = "Categoria dos produtos que serão buscados", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = ProdutoConstantes.d200, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e200))),
                        @ApiResponse(responseCode = "204", description = ProdutoConstantes.d204, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e404))),
                        @ApiResponse(responseCode = "400", description = ProdutoConstantes.d400, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e400))),
                        @ApiResponse(responseCode = "422", description = ProdutoConstantes.d422, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e422))),
                        @ApiResponse(responseCode = "500", description = ProdutoConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProdutoConstantes.e500))) })
        @GetMapping(value = "/listar/{categoria}")
        ResponseEntity<List<Produto>> buscarProdutosPorCategoria(
                        @PathVariable("categoria") String categoria) throws Exception;

}

final class ProdutoConstantes {

        public static final String descricaoCadastrar = "Para cadastrar um novo produto, informe os dados do produto conforme o schema ProdutoDto no final desta página.<br>A descrição do produto é opcional.";
        public static final String descricaoEditar = "Para editar um produto, informe o código do produto (path) e os dados do produto (body) conforme o schema ProdutoDto ao final desta página.";
        public static final String descricaoRemover = "Para remover um produto, informe o código do produto a ser removido.";
        public static final String descricaoBuscarPorCategoria = "Para buscar os produtos por categoria, informe a categoria (lanche, acompanhamento, bebida ou sobremesa).";

        public static final String d200 = "Sucesso!";
        public static final String d201 = d200;
        public static final String d204 = d200;
        public static final String d400 = "Requisição inválida!";
        public static final String d404 = "Produto não encontrado!";
        public static final String d422 = "Operação não permitida!";
        public static final String d500 = "Erro!";

        public static final String e200 = "{ \"codigo\": 1,\"nome\": \"X-Monstrão\",\"descricao\": \"O lanche do marombeiro, repleto de whey e creatina.\",\"preco\": 35.9,\"categoria\": \"LANCHE\" }";
        public static final String e201 = "{ \"codigo\": 1,\"nome\": \"X-Monstrão\",\"descricao\": \"O lanche do marombeiro, repleto de whey e creatina.\",\"preco\": 35.9,\"categoria\": \"LANCHE\" }";
        public static final String e204 = "";
        public static final String e400 = "{ \"timestamp\": \"2024-09-08T02:05:58.036+00:00\", \"status\": 400, \"error\": \"Bad Request\", \"path\": \"/api/v2/produtos/cadastrar\" }";
        public static final String e404 = "{ \"message\": \"Nenhum produto foi encontrado para o código informado.\" }";
        public static final String e422 = "{ \"message\": \"O preço do produto deve ser maior que 0.\" }";
        public static final String e500 = "{ \"message\": \"Ocorreu um erro inesperado no servidor.\" }";
}
