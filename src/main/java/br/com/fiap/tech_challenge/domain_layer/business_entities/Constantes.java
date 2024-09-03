package br.com.fiap.tech_challenge.domain_layer.business_entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Constantes {
    public static LocalDate dataMinimaValidacao = LocalDate.of(2020, 1, 1);
    public static LocalDateTime dataHoraMinimaValidacao = dataMinimaValidacao.atStartOfDay();
}
