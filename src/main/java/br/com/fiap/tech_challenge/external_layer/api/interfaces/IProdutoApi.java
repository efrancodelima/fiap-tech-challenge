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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.fiap.tech_challenge.interface_layer.dtos.ProdutoDto;

@Tag(name = "Produtos")
public interface IProdutoApi {

        // Cadastrar produto
        @Operation(summary = "Cadastrar produto", description = "Para cadastrar um novo produto, clique em "
                        + "\"Try It Out\", informe os dados do produto em \"Request Body\", conforme o schema "
                        + "ProdutoDto ao final desta página.")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Produto cadastrado com sucesso."))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
                        @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao cadastrar o produto."))) })
        @PostMapping("/cadastrar")
        ResponseEntity<String> cadastrarProduto(@RequestBody ProdutoDto cadastrar);

        // Editar produto
        @Operation(summary = "Editar produto", description = "Para editar um produto, clique em \"Try It Out\", "
                        + "informe o código em \"Parameters\" e os dados do produto em \"Request Body\", conforme "
                        + "o schema ProdutoDto ao final desta página.")
        @Parameters({
                        @Parameter(name = "codigo", description = "Código do produto a ser editado", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Produto atualizado com sucesso."))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Nenhum produto foi encontrado para o código informado."))),
                        @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao editar o produto."))) })
        @PutMapping("/editar/{codigo}")
        ResponseEntity<String> editarProduto(@PathVariable long id, @RequestBody ProdutoDto atualizar);

        // Remover produto
        @Operation(summary = "Remover produto", description = "Para remover um produto, clique em \"Try It Out\" "
                        + "e informe o código do produto a ser removido em \"Paremeters\".")
        @Parameters({
                        @Parameter(name = "codigo", description = "Código do produto a ser removido", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "204", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Produto removido com sucesso."))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
                        @ApiResponse(responseCode = "404", description = "Produto não encontrado!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Nenhum produto foi encontrado para o código informado."))),
                        @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao remover o produto."))) })
        @DeleteMapping(value = "/remover/{codigo}")
        ResponseEntity<String> removerProduto(@PathVariable long id);

        // Listar produtos por categoria
        @Operation(summary = "Buscar produtos por categoria", description = "Para buscar os produtos por categoria, "
                        + "clique em \"Try It Out\" e informe a categoria (lanche, acompanhamento, bebida ou "
                        + "sobremesa) em \"Parameters\".")
        @Parameters({
                        @Parameter(name = "categoria", description = "Categoria dos produtos que serão buscados", required = true)
        })
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Sucesso!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Produtos encontrados com sucesso."))),
                        @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Nenhum produto foi encontrado para a categoria informada."))),
                        @ApiResponse(responseCode = "400", description = "Requisição inválida!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Verifique os dados enviados e tente novamente."))),
                        @ApiResponse(responseCode = "500", description = "Erro!", content = @Content(mediaType = "application/text", examples = @ExampleObject(value = "Ocorreu um erro inesperado ao buscar os produtos."))) })
        @GetMapping(value = "/listar/{categoria}")
        ResponseEntity<String> buscarProdutosPorCategoria(
                        @PathVariable("categoria") String categoria);

}
