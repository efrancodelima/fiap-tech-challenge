package br.com.fiap.tech_challenge.external_layer.apis.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Probes")
public interface IProbesApi {

    // Liveness probe
    @Operation(summary = "Consultar liveness probe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ProbesConstantes.d204, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProbesConstantes.e204))),
            @ApiResponse(responseCode = "500", description = ProbesConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProbesConstantes.e500))) })
    @GetMapping(value = "/health")
    ResponseEntity<Void> consultarLivenessProbe();

    // Readiness probe
    @Operation(summary = "Consultar readiness probe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ProbesConstantes.d204, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProbesConstantes.e204))),
            @ApiResponse(responseCode = "500", description = ProbesConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProbesConstantes.e500))) })
    @GetMapping(value = "/ready")
    ResponseEntity<Void> consultarReadinessProbe();

    // Startup probe
    @Operation(summary = "Consultar startup probe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = ProbesConstantes.d204, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProbesConstantes.e204))),
            @ApiResponse(responseCode = "500", description = ProbesConstantes.d500, content = @Content(mediaType = "application/json", examples = @ExampleObject(value = ProbesConstantes.e500))) })
    @GetMapping(value = "/startup")
    ResponseEntity<Void> consultarStartupProbe();
}

final class ProbesConstantes {

    public static final String d204 = "Sucesso!";
    public static final String d500 = "Erro!";

    public static final String e204 = "";
    public static final String e500 = "{ \"code\": 500, \"status\": \"Internal Server Error\", \"message\": \"Ocorreu um erro inesperado no servidor.\", \"timestamp\": \"2024-09-08T15:37:05.129042146\"}";
}
