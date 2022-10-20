package br.com.fiap.abctechapi.application.impl;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.AssistDto;
import br.com.fiap.abctechapi.application.dto.OrderDto;
import br.com.fiap.abctechapi.application.dto.OrderLocationDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.model.OrderLocation;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderApplicationImpl implements OrderApplication {

    private final OrderService orderService;

    public OrderApplicationImpl(@Autowired OrderService orderService){
        this.orderService = orderService;
    }

    @Override
    public Long createOrder(OrderDto orderDto) {
        var order = new Order(orderDto.getOperatorId(),
                getOrderLocationFromOrderLocationDto(orderDto.getStart()),
                getOrderLocationFromOrderLocationDto(orderDto.getEnd()));
        var storedOrderId = this.orderService.saveOrder(order, orderDto.getAssists());
        return storedOrderId;
    }

    @Override
    public List<OrderResponseDto> listOrderByOperatorId(Long operatorId) {
        return orderService.listOrderByOperator(operatorId).stream().map(this::mapOrderToOrderDto).collect(Collectors.toList());
    }

    private OrderResponseDto mapOrderToOrderDto(Order order) {
        var assists = order.getAssists().stream().map(this::mapAssistToDto).collect(Collectors.toList());
        var startLocationOrder = mapOrderLocationToDto(order.getStartOrderLocation());
        var endLocationOrder = mapOrderLocationToDto(order.getEndOrderLocation());
        return new OrderResponseDto(order.getId(), order.getOperatorId(), assists, startLocationOrder, endLocationOrder);
    }

    private AssistDto mapAssistToDto(Assistance assist){
        return new AssistDto(assist.getId(), assist.getName(), assist.getDescription());
    }


    private OrderLocation getOrderLocationFromOrderLocationDto(OrderLocationDto orderLocationDto) {
        return new OrderLocation(orderLocationDto.getLatitude(),
                orderLocationDto.getLongitude(),
                orderLocationDto.getDateTime());
    }

    private OrderLocationDto mapOrderLocationToDto(OrderLocation orderLocation){
        OrderLocationDto orderLocationDto = new OrderLocationDto();
        orderLocationDto.setLatitude(orderLocation.getLatitude());
        orderLocationDto.setLongitude(orderLocation.getLongitude());
        orderLocationDto.setDateTime(orderLocation.getDate());
        return orderLocationDto;
    }
}
