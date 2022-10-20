package br.com.fiap.abctechapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
public class OrderLocationDto {
    private Double latitude;
    private Double longitude;
    private Date dateTime;
}