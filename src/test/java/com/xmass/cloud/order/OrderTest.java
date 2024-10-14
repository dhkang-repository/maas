package com.xmass.cloud.order;

import com.xmass.cloud.CloudApplicationTests;
import com.xmass.cloud.domain.orders.model.OrderReceipt;
import com.xmass.cloud.domain.orders.model.OrderRequest;
import com.xmass.cloud.domain.orders.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderTest extends CloudApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void testPlaceOrder() {
        // 주문할 수량 계산
        BigDecimal currentValue = new BigDecimal("0.02376");
        BigDecimal orderValue = new BigDecimal("8000");
        BigDecimal divide = orderValue.divide(currentValue, 8, RoundingMode.HALF_UP); // 8자리 소수점까지 설정

        // 주문 요청 생성
        OrderRequest request = OrderRequest.builder()
                .market("KRW-SHIB")
                .price("8000")
                .side("bid")
                .ordType("price")
                .build();

        // 주문 요청 및 응답 처리
        Mono<String> orderMono = orderRepository.order(request);

        orderMono
                .doOnSuccess(response -> System.out.println("Order Response: " + response))
                .doOnError(error -> System.err.println("Order Failed: " + error.getMessage()))
                .block();  // 실제 주문을 요청하고 응답을 받음
    }
}
