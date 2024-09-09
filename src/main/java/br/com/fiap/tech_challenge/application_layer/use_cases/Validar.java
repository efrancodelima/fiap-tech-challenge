package br.com.fiap.tech_challenge.application_layer.use_cases;

import java.util.List;

import br.com.fiap.tech_challenge.application_layer.exceptions.ApplicationException;
import br.com.fiap.tech_challenge.application_layer.exceptions.ResourceNotFoundException;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumApplicationExceptions;
import br.com.fiap.tech_challenge.application_layer.exceptions.messages.EnumNotFoundExceptions;

public final class Validar {

    // ApplicationException
    public static void notNull(Object objeto, EnumApplicationExceptions excecao)
            throws ApplicationException {

        if (objeto == null) {
            throw new ApplicationException(excecao.getMensagem());
        }
    }

    public static void maiorQueZero(long numero, EnumApplicationExceptions excecao) throws ApplicationException {

        if (numero <= 0) {
            throw new ApplicationException(EnumApplicationExceptions.PEDIDO_NUMERO_MIN.getMensagem());
        }
    }

    // ResourceNotFoundException
    public static void notNull(Object objeto, EnumNotFoundExceptions excecao)
            throws ResourceNotFoundException {

        if (objeto == null) {
            throw new ResourceNotFoundException(excecao.getMensagem());
        }
    }

    public static <T> void listNotEmpty(List<T> lista, EnumNotFoundExceptions excecao)
            throws ResourceNotFoundException {

        if (lista.size() < 1) {
            throw new ResourceNotFoundException(excecao.getMensagem());
        }
    }
}
