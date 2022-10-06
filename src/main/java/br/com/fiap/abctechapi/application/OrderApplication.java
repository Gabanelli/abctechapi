package br.com.fiap.abctechapi.application;

import br.com.fiap.abctechapi.application.dto.OrderDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;

import java.util.List;

public interface OrderApplication {
    Long createOrder(OrderDto orderDto);
    List<OrderResponseDto> listOrderByOperatorId(Long operatorId);
}
