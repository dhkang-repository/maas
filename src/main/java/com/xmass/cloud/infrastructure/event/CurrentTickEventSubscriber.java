package com.xmass.cloud.infrastructure.event;

import com.xmass.cloud.domain.global.SecretKeyInfo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.ReactorNettyWebSocketClient;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Component
@RequiredArgsConstructor
public class CurrentTickEventSubscriber {

    private final SecretKeyInfo secretKeyInfo;
    private final ApplicationEventPublisher eventPublisher;
    private static final String UPBIT_WS_URL = "wss://api.upbit.com/websocket/v1";

    @PostConstruct
    public void subscribe() throws Exception {
        ReactorNettyWebSocketClient client = new ReactorNettyWebSocketClient();

        // Upbit WebSocket에 연결하고 데이터 처리
        client.execute(
                URI.create(UPBIT_WS_URL),
                session -> {
                    // WebSocket 연결 후 JSON 형식으로 티켓 및 구독 요청 전송
                    String subscriptionMessage = "[{\"ticket\":\"test\"},{\"type\":\"ticker\",\"codes\":[\"KRW-SHIB\"]}]";
                    WebSocketMessage subscribeMessage = session.textMessage(subscriptionMessage);

                    // 메시지 보내기
                    session.send(Mono.just(subscribeMessage))
                            .thenMany(
                                    session.receive()
                                            .map(WebSocketMessage::getPayloadAsText)
                                            .doOnNext(message -> {
                                                // 데이터를 이벤트로 발행
                                                eventPublisher.publishEvent(new WebSocketDataEvent(this, message));
                                            })
                            )
                            .then()
                            .block(Duration.ofSeconds(10)); // 수신 시간 설정

                    return Mono.empty();
                }).subscribe();
    }

}
