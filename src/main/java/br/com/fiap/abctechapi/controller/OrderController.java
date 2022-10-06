package br.com.fiap.abctechapi.controller;

import br.com.fiap.abctechapi.application.OrderApplication;
import br.com.fiap.abctechapi.application.dto.OrderDto;
import br.com.fiap.abctechapi.application.dto.OrderResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriBuilder;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderApplication orderApplication;

    public OrderController(
            @Autowired OrderApplication orderApplication
    ){
        this.orderApplication = orderApplication;
    }

    @PostMapping
    public ResponseEntity createOrder(@RequestBody OrderDto orderDto) throws Exception {
        var createdId = orderApplication.createOrder(orderDto);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdId).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/operator/{operatorId}")
    public ResponseEntity<List<OrderResponseDto>> listOrdersOperator(@PathVariable Long operatorId){
        return ResponseEntity.ok(orderApplication.listOrderByOperatorId(operatorId));
    }
}