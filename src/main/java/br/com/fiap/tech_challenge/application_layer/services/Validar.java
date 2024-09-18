package br.com.fiap.tech_challenge.application_layer.services;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.exceptions.ApplicationException;
import br.com.fiap.tech_challenge.application_layer.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;

public final class Validar {

    public static void notNull(Object objeto, EnumApplicationExceptions excecao)
            throws Exception {

        if (excecao instanceof EnumApplicationExceptions) {
            throw new ApplicationException(excecao.getMensagem());
        }
    }

    public static void notNull(Object objeto, EnumNotFoundExceptions excecao)
            throws Exception {

        if (objeto == null) {
            throw new ResourceNotFoundException(excecao.getMensagem());
        }
    }

    public static void maiorQueZero(long numero, EnumApplicationExceptions excecao) throws ApplicationException {

        if (numero <= 0) {
            throw new ApplicationException(excecao.getMensagem());
        }
    }

    public static <T> void listNotEmpty(List<T> lista, EnumNotFoundExceptions excecao)
            throws ResourceNotFoundException {

        if (lista.size() < 1) {
            throw new ResourceNotFoundException(excecao.getMensagem());
        }
    }
}
