package com.xmass.cloud.infrastructure.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    private final ObjectMapper objectMapper;

    @EventListener
    public void handleWebSocketDataEvent(WebSocketDataEvent event) {
        String jsonMessage = event.getMessage();

        try {
            // JSON 데이터 파싱
            UpbitResponse response = objectMapper.readValue(jsonMessage, UpbitResponse.class);
            System.out.println("Received Data:");
            System.out.println("Market: " + response.getMarket());
            System.out.println("Trade Price: " + response.getTradePrice());
        } catch (Exception e) {
            System.err.println("Failed to parse message: " + jsonMessage);
            e.printStackTrace();
        }
    }

    // JSON 파싱을 위한 클래스
    @Data
    public static class UpbitResponse {
        @JsonProperty("code")
        private String market;
        @JsonProperty("trade_price")
        private double tradePrice;
    }
}
