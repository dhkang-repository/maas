package com.xmass.cloud.domain.orders;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RouteServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void testGetRecommendedRoute() {
        String origin = "서울역";
        String destination = "강남역";
        String result = orderService.getRecommendedRoute(origin, destination);

        assertNotNull(result);
        System.out.println("경로 추천 결과: " + result);
    }
}