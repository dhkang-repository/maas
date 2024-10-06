package com.xmass.cloud.web.controller;

import com.xmass.cloud.domain.account.model.Account;
import com.xmass.cloud.domain.account.repository.AccountRepository;
import com.xmass.cloud.domain.orders.model.OrderChance;
import com.xmass.cloud.domain.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InfoController {
    private final AccountRepository accountRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/account")
    public List<Account> account() {
        Mono<List<Account>> result = accountRepository.get();
        List<Account> block = result.block();
        System.out.println(block);
        return block;
    }

    @GetMapping("/orders/chance")
    public OrderChance orderChance(@RequestParam(value = "market", required = true) String market) {
        Mono<OrderChance> chance = orderRepository.getChance(market);
        OrderChance block = chance.doOnError(e->{
            e.printStackTrace();
            String message = e.getMessage();
            System.out.println(message);
        }).block();
        System.out.println(block);
        return block;
    }
}
