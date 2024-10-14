package com.xmass.cloud.application;

import com.xmass.cloud.domain.account.model.Account;
import com.xmass.cloud.domain.account.repository.AccountRepository;
import com.xmass.cloud.domain.global.ApplicationInfo;
import com.xmass.cloud.domain.orders.model.OrderReceipt;
import com.xmass.cloud.domain.orders.model.OrderRequest;
import com.xmass.cloud.domain.orders.repository.OrderRepository;
import com.xmass.cloud.domain.trading.service.RuleDTO;
import com.xmass.cloud.infrastructure.enums.RuleEnum;
import com.xmass.cloud.infrastructure.event.UpbitResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderApplication {
    private final OrderRepository orderRepository;
    private final AccountRepository accountRepository;
    private final ApplicationInfo applicationInfo;

    public boolean order(RuleDTO ruleDTO,
                         UpbitResponse upbitResponse) {
        Mono<List<Account>> listMono = accountRepository.get();
        Account findAccount = listMono.block().stream()
                .filter(acc->acc.getBalance().equals(applicationInfo.getTarget()))
                .findFirst()
                .orElse(null);
        if(ruleDTO.getRule() == RuleEnum.BUY) {
            OrderRequest orderRequest = OrderRequest.builder()
                    .market(upbitResponse.getMarket())
                    .side("ask")
                    .volume("10")
                    .price(upbitResponse.getTradePrice()+"")
                    .ordType("price")
                    .build();
            String order = orderRepository.order(orderRequest).block();
            System.out.println(order);
            return true;
        } else if(ruleDTO.getRule() == RuleEnum.SELL) {
            if(findAccount.getBalance().compareTo(BigDecimal.ZERO) > 0) {
                OrderRequest orderRequest = OrderRequest.builder()
                        .market(upbitResponse.getMarket())
                        .side("bid")
                        .volume(findAccount.getBalance().toPlainString())
                        .price(upbitResponse.getTradePrice()+"")
                        .ordType("price")
                        .build();
                Mono<String> order = orderRepository.order(orderRequest);
                System.out.println(order.block());
            }

            return true;
        }

        return false;
    }
}
