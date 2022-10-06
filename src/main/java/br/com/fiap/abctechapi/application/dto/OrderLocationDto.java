package br.com.fiap.abctechapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
@AllArgsConstructor
public class OrderLocationDto {
    @NonNull
    private final Double latitude;
    @NonNull
    private final Double longitude;
    @NonNull
    private final Date dateTime;
}