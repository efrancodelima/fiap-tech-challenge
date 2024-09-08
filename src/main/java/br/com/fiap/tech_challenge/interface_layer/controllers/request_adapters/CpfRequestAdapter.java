package br.com.fiap.tech_challenge.interface_layer.controllers.request_adapters;

import br.com.fiap.tech_challenge.domain_layer.business_entities.Cpf;
import br.com.fiap.tech_challenge.domain_layer.exceptions.MyBusinessException;

public final class CpfRequestAdapter {

    public static Cpf adaptar(Long cpf) throws MyBusinessException {

        int numero;
        byte digitoVerificador;

        if (cpf != null) {
            numero = (int) (cpf / 100);
            digitoVerificador = (byte) (cpf % 100);
        } else {
            numero = 0;
            digitoVerificador = (byte) 0;
        }

        return new Cpf(numero, digitoVerificador);
    }

}
