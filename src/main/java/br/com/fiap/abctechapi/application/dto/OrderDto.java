package br.com.fiap.abctechapi.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * {
 * "operator_id" : 0,
 * "services" : [1, 2, 3],
 * "start" : {
 * 	"latitude" : 0.0,
 * 	"longitude" : 0.0,
 * 	"datetime" : "2022-02-19 00:00:00"
 * },
 * "end" : {
 * 	"latitude" : 0.0,
 * 	"longitude" : 0.0,
 * 	"datetime" : "2022-02-19 00:00:00"
 * }
 */
@Data
@AllArgsConstructor
public class OrderDto {
    @NonNull
    private final Long operatorId;
    @NonNull
    private final List<Long> assists;
    @NonNull
    private final OrderLocationDto start;
    @NonNull
    private final OrderLocationDto end;
}
