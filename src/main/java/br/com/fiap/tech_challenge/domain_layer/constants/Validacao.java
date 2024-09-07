package br.com.fiap.tech_challenge.domain_layer.constants;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Validacao {
    public static LocalDate dataMinima = LocalDate.of(2020, 1, 1);
    public static LocalDateTime dataHoraMinima = dataMinima.atStartOfDay();
}
