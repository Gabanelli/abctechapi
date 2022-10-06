package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Order;

import java.util.List;

public interface OrderService {
    Long saveOrder(Order order, List<Long> arrayAssists);
    List<Order> listOrderByOperator(Long operatorId);
}
