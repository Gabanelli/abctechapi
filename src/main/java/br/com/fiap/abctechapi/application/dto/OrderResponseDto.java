package br.com.fiap.abctechapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderResponseDto {
    public final Long id;
    private final Long operatorId;
    private final List<AssistDto> assists;
    private final OrderLocationDto start;
    private final OrderLocationDto end;
}