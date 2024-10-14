package com.xmass.cloud.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmass.cloud.domain.orders.model.OrderReceipt;
import com.xmass.cloud.domain.orders.repository.OrderRepository;
import com.xmass.cloud.domain.orders.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final ObjectMapper objectMapper;
    @PostMapping("/orders")
    public OrderReceipt openOrders(@RequestParam OrderRequest orderRequest) {
        try {
            Mono<String> orders = orderRepository.order(orderRequest);
            String block = orders.doOnError(e->{
                e.printStackTrace();
                String message = e.getMessage();
                System.out.println(message);
            }).block();

            return objectMapper.readValue(block, OrderReceipt.class);
        } catch (Exception e) {
            return null;
        }
    }
}
