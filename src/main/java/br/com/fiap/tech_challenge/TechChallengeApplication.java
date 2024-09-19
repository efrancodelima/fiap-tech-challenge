package br.com.fiap.tech_challenge;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.fiap.tech_challenge.external_layer.apis.ProdutoApi;
import br.com.fiap.tech_challenge.interface_layer.controllers.dtos.ProdutoDto;

@SpringBootApplication
public class TechChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechChallengeApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(ProdutoApi produtoApi) {
		return args -> {
			var p = new ProdutoDto();
			p.setNome("X-Monstrão");
			p.setPreco(BigDecimal.valueOf(20));
			p.setCategoria("lanche");

			produtoApi.cadastrarProduto(p);

			p.setNome("X-Monstrinho");
			produtoApi.cadastrarProduto(p);
		};
	}
}
