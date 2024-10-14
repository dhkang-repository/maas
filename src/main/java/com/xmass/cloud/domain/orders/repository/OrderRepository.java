package com.xmass.cloud.domain.orders.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmass.cloud.domain.orders.model.OrderChance;
import com.xmass.cloud.domain.orders.model.Order;
import com.xmass.cloud.infrastructure.util.ExternalAPIUtil;
import com.xmass.cloud.domain.orders.model.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final ExternalAPIUtil externalAPIUtil;
    private final ObjectMapper objectMapper;

    public Mono<OrderChance> getChance(String market) {
        Map<String, String> params = Map.of("market", market);
        return externalAPIUtil.get("/v1/orders/chance", params, null, new ParameterizedTypeReference<>() {});
    }

    public Mono<List<Order>> getClosedOrders(String market) {
        Map<String, String> params = Map.of("market", market);
        Map<String, List<String>> states = Map.of("states", List.of("done", "cancel"));
        return externalAPIUtil.get("/v1/orders/closed", params, states, new ParameterizedTypeReference<>() {});
    }

    public Mono<List<Order>> getOpenOrders(String market) {
        Map<String, String> params = Map.of("market", market);
        Map<String, List<String>> states = Map.of("states", List.of("wait", "watch"));
        return externalAPIUtil.get("/v1/orders/open", params, states, new ParameterizedTypeReference<>() {});
    }

    public Mono<String> order(OrderRequest orderRequest) {
        return externalAPIUtil.post("/v1/orders", orderRequest.param(), null, String.class);
    }
}
