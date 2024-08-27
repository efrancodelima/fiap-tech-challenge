package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.google.gson.Gson;

import br.com.fiap.tech_challenge.core.domain.model.enums.StatusPedido;

public class TesteEntities {
    public static void main(String[] args) {
        try {
            Cpf cpf = new Cpf(123456789, 9);
            Cliente anaJulia = new Cliente("Ana Júlia Nascimento", cpf, "anajulia@gmail.com");

            Pedido pedido = new Pedido(1, LocalDate.now(), null, null, null, StatusPedido.AGUARDANDO_CHECKOUT,
                    LocalDateTime.now());

            Gson gson = new Gson();
            String json = gson.toJson(pedido.getItens());
            System.out.println(json);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
