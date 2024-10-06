package com.xmass.cloud.domain.orders.repository;

import com.xmass.cloud.domain.orders.model.OrderChance;
import com.xmass.cloud.domain.orders.model.Order;
import com.xmass.cloud.util.ExternalAPIUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final ExternalAPIUtil externalAPIUtil;

    public Mono<OrderChance> getChance(String market) {
        return externalAPIUtil.get("/v1/orders/chance", market, null,
                new ParameterizedTypeReference<OrderChance>() {});
    }

    public Mono<List<Order>> getClosedOrders(String market) {
        return externalAPIUtil.get("/v1/orders/closed", market,
                new HashMap<>() {{put("states", List.of("done", "cancel"));}},
                new ParameterizedTypeReference<List<Order>>() {
        });
    }

    public Mono<List<Order>> getOpenOrders(String market) {
        return externalAPIUtil.get("/v1/orders/open", market,
                new HashMap<>() {{put("states", List.of("wait", "watch"));}},
                new ParameterizedTypeReference<List<Order>>() {
                });
    }



}
