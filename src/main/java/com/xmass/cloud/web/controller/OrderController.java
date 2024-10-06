package com.xmass.cloud.web.controller;

import com.xmass.cloud.domain.account.model.Account;
import com.xmass.cloud.domain.account.repository.AccountRepository;
import com.xmass.cloud.domain.orders.model.Order;
import com.xmass.cloud.domain.orders.model.OrderChance;
import com.xmass.cloud.domain.orders.model.OrderReceipt;
import com.xmass.cloud.domain.orders.repository.OrderRepository;
import com.xmass.cloud.web.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;

    @PostMapping("/orders")
    public OrderReceipt openOrders(@RequestParam OrderRequest orderRequest) {
        Mono<OrderReceipt> orders = orderRepository.order(orderRequest);
        OrderReceipt block = orders.doOnError(e->{
            e.printStackTrace();
            String message = e.getMessage();
            System.out.println(message);
        }).block();
        System.out.println(block);
        return block;
    }
}
