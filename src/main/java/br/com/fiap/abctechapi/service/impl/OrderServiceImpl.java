package br.com.fiap.abctechapi.service.impl;

import br.com.fiap.abctechapi.handler.exception.AssistanceNotFoundException;
import br.com.fiap.abctechapi.handler.exception.MaxAssistException;
import br.com.fiap.abctechapi.handler.exception.MinimumAssistRequiredException;
import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.model.Order;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final AssistanceRepository assistanceRepository;

    public OrderServiceImpl(
            @Autowired OrderRepository orderRepository,
            @Autowired AssistanceRepository assistanceRepository) {
        this.assistanceRepository = assistanceRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Long saveOrder(Order order, List<Long> arrayAssists) {
        var assistances = arrayAssists.stream().map(assistId -> {
           var assistance = assistanceRepository.findById(assistId);
           if (!assistance.isPresent()) {
               throw new AssistanceNotFoundException("Assistência não encontrada", assistId);
           }
           return assistance.get();
        });
        order.setAssists(assistances.collect(Collectors.toList()));

        if (!order.hasMinAssists()) {
            throw new MinimumAssistRequiredException("Invalid Assists", "Necessário no minimo 1 assistencia");
        }
        if (order.exceedsMaxAssists()){
            throw new MaxAssistException("Invalid Assists", "Número máximo de assistências é 15");
        }
        var storedOrder = orderRepository.save(order);
        return storedOrder.getId();
    }

    @Override
    public List<Order> listOrderByOperator(Long operatorId) {
        return orderRepository.getOrdersByOperatorId(operatorId);
    }
}
