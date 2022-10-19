package br.com.fiap.abctechapi.service;

import br.com.fiap.abctechapi.model.Assistance;
import br.com.fiap.abctechapi.repository.AssistanceRepository;
import br.com.fiap.abctechapi.repository.OrderRepository;
import br.com.fiap.abctechapi.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private AssistanceRepository assistanceRepository;

    private OrderService orderService;

    @BeforeEach
    public void init() {
        orderService = new OrderServiceImpl(orderRepository, assistanceRepository);
        when(assistanceRepository.findById(any())).
                thenReturn(
                        Optional.of(new Assistance(1L, "Teste", "Teste description:")));
    }

    @Test
    public void order_service_not_null() {
        Assertions.assertNotNull(orderService);
    }

}
