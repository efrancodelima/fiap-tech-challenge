package br.com.fiap.tech_challenge.interface_layer.controllers.adapters.response_adapters.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class DateTimeService {

    public static String formatar(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return dateTime.format(formatter);
    }
}
