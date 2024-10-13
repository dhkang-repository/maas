package com.xmass.cloud.infrastructure.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xmass.cloud.application.TradingApplication;
import com.xmass.cloud.domain.trading.service.TradingDTO;
import com.xmass.cloud.infrastructure.enums.TradingEnum;
import com.xmass.cloud.domain.global.PriceInfo;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WebSocketEventListener {
    static final Logger LOGGER = LoggerFactory.getLogger(WebSocketEventListener.class);

    /**
     * @see ObjectMapper objectMapper
     * @see SimpMessagingTemplate messagingTemplate
     * @see TradingApplication tradingApplication
     * @see PriceInfo priceInfo;
     */
    private final ObjectMapper objectMapper;
    private final SimpMessagingTemplate messagingTemplate;
    private final TradingApplication tradingApplication;
    private final PriceInfo priceInfo;

    @EventListener
    public void add(WebSocketDataEvent event) {
        String jsonMessage = event.getMessage();
        try {
            // JSON 데이터 파싱
            UpbitResponse response = objectMapper.readValue(jsonMessage, UpbitResponse.class);

            messagingTemplate.convertAndSend("/topic/market", event.getMessage());
            priceInfo.add(response.getTradePrice());

            System.out.println("Received Data:");
            System.out.println("Market: " + response.getMarket());
            System.out.println("Trade Price: " + response.getTradePrice());
        } catch (Exception e) {
            System.err.println("Failed to parse message: " + jsonMessage);
            e.printStackTrace();
        }
    }


    @EventListener
    public void strategy(WebSocketDataEvent event) throws Exception {
        List<Double> prices = priceInfo.getPrices();
        if (!prices.isEmpty() && prices.size() > 20) {
            TradingDTO ma14 = tradingApplication.calculate(TradingEnum.MA.getStrategy(), prices, 14);
            TradingDTO ma100 = tradingApplication.calculate(TradingEnum.MA.getStrategy(), prices, 100);
            TradingDTO blg14 = tradingApplication.calculate(TradingEnum.BLG.getStrategy(), prices, 14);
            TradingDTO blg100 = tradingApplication.calculate(TradingEnum.BLG.getStrategy(), prices, 100);
            TradingDTO rsi14 = tradingApplication.calculate(TradingEnum.RSI.getStrategy(), prices, 14);
            TradingDTO rsi100 = tradingApplication.calculate(TradingEnum.RSI.getStrategy(), prices, 100);
            LOGGER.info("ma14 : {}, blg14 : {}, rsi14 : {}", ma14, blg14, rsi14);
            LOGGER.info("ma100 : {}, blg100 : {}, rsi100 : {}", ma100, blg100, rsi100);
        } else {
            System.out.println("No price data available.");
        }
    }


}
