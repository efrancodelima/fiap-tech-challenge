package br.com.fiap.tech_challenge.domain_layer.business_entities;

import com.google.gson.Gson;

public class TesteEntities {
    public static void main(String[] args) {
        try {
            Cpf cpf = new Cpf(123456789, (byte) 9);
            Cliente anaJulia = new Cliente(cpf, "Ana Júlia Nascimento", "anajulia@gmail.com");

            Gson gson = new Gson();
            String json = gson.toJson(anaJulia);
            System.out.println(json);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
