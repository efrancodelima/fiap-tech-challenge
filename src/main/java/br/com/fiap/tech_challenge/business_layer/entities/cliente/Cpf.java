package br.com.fiap.tech_challenge.business_layer.entities.cliente;

import org.apache.commons.lang3.StringUtils;

import br.com.fiap.tech_challenge.business_layer.exceptions.BusinessRuleException;
import br.com.fiap.tech_challenge.business_layer.exceptions.messages.CpfExceptions;

public class Cpf {

    private final int numero;
    private final byte digitoVerificador;

    // Construtor
    public Cpf(Integer numero, Byte digitoVerificador) throws BusinessRuleException {

        validarNumeroEDigitoVerificador(numero, digitoVerificador);
        this.numero = numero;
        this.digitoVerificador = digitoVerificador;
    }

    public Cpf(Long cpf) throws BusinessRuleException {

        validarCpf(cpf);

        int numero = (int) (cpf / 100);
        byte digitoVerificador = (byte) (cpf % 100);

        validarNumeroEDigitoVerificador(numero, digitoVerificador);
        this.numero = numero;
        this.digitoVerificador = digitoVerificador;
    }

    // Getters
    public long pegarNumeroComDigito() {
        return Long.parseLong(this.toString());
    }

    public int getNumeroSemDigito() {
        return numero;
    }

    public byte getDigitoVerificador() {
        return digitoVerificador;
    }

    // Métodos de validação
    private void validarNumeroEDigitoVerificador(Integer numero, Byte digitoVerificador) throws BusinessRuleException {

        validarNumero(numero);
        validarDigitoVerificador(numero, digitoVerificador);
    }

    private void validarNumero(Integer numero) throws BusinessRuleException {

        if (numero == null) {
            throw new BusinessRuleException(CpfExceptions.NUMERO_NULO.getMensagem());
        }
        if (numero < 1) {
            throw new BusinessRuleException(CpfExceptions.NUMERO_MIN.getMensagem());
        }
        if (numero > Math.pow(10, 10) - 1) {
            throw new BusinessRuleException(CpfExceptions.NUMERO_MAX.getMensagem());
        }
    }

    private void validarDigitoVerificador(int numero, Byte digitoVerificador) throws BusinessRuleException {

        if (digitoVerificador == null) {
            throw new BusinessRuleException(CpfExceptions.DIGITO_NULO.getMensagem());
        }
        if (digitoVerificador != calcularDigitoVerificador(numero)) {
            throw new BusinessRuleException(CpfExceptions.DIGITO_INVALIDO.getMensagem());
        }
    }

    private void validarCpf(Long cpf) throws BusinessRuleException {

        if (cpf == null) {
            throw new BusinessRuleException(CpfExceptions.CPF_NULO.getMensagem());
        }
    }

    // Métodos auxiliares
    private int calcularDigitoVerificador(int numero) {
        // Transforma a entrada em um array
        int[] numeroArr = new int[11];
        for (int i = 8; i >= 0; i--) {
            numeroArr[i] = numero % 10;
            numero /= 10;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += numeroArr[i] * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }
        numeroArr[9] = primeiroDigitoVerificador;

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += numeroArr[i] * (11 - i);
        }
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }
        numeroArr[10] = segundoDigitoVerificador;

        // Retorna
        return (primeiroDigitoVerificador * 10) + segundoDigitoVerificador;
    }

    @Override
    public String toString() {
        String numeroStr = String.valueOf(numero);
        numeroStr = StringUtils.leftPad(numeroStr, 9, '0');

        String digitoVerificadorStr = String.valueOf(digitoVerificador);
        digitoVerificadorStr = StringUtils.leftPad(digitoVerificadorStr, 2, '0');

        return numeroStr + digitoVerificadorStr;
    }
}
