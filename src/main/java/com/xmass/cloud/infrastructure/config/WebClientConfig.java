package com.xmass.cloud.infrastructure.config;

import com.xmass.cloud.domain.global.SecretKeyInfo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {

    private final SecretKeyInfo secretKeyInfo;

    @Bean
    public WebClient webClient() throws Exception {

        // HMAC256으로 서명 생성
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyInfo.getSecretKey().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKeySpec);
        String jwtToken = Jwts.builder()
                .claim("access_key", secretKeyInfo.getAccessKey())
                .claim("nonce", UUID.randomUUID().toString())
                .signWith(secretKeySpec, SignatureAlgorithm.HS256)
                .compact();

        String authenticationToken = "Bearer " + jwtToken;

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000) // 연결 타임아웃 설정 (5초)
                .responseTimeout(Duration
                        .ofMillis(5000)) // 응답 타임아웃 설정 (5초)
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS)) // 읽기 타임아웃 설정
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))); // 쓰기 타임아웃 설정

        return WebClient.builder()
                .baseUrl(secretKeyInfo.getServerUrl()) // 기본 base URL 설정
                .defaultHeader("Content-Type", "application/json") // 기본 헤더 설정
                .defaultHeader("Authorization", authenticationToken) // 필요 시 추가 헤더
                .exchangeStrategies(ExchangeStrategies.builder()
                        .codecs(configurer -> configurer
                                .defaultCodecs()
                                .maxInMemorySize(16 * 1024 * 1024)) // 최대 메모리 버퍼 크기 조정 (예: 16MB)
                        .build())
                .clientConnector(new ReactorClientHttpConnector(httpClient)) // 커스텀 HttpClient 설정
                .build();
    }

}
